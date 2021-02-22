import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
SRTF lecture ex1
P1,0,8
P2,1,4
P3,2,9
P4,3,5

SRTF lecture ex2
p1,0,7
p2,2,4
p3,4,1
p4,5,4
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<Process>();
        int numberOfProcesses;
        double contextSwitching;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of processes : ");
        numberOfProcesses = Integer.parseInt(sc.nextLine());
        String ProcessName;
        double ProcessArrivalTime;
        double ProcessBurstTime;
        double ProcessPriorityNumber;
        int Queue_No;

        int Technique;
        System.out.println("Choose Your Technique or Algorithm :");
        System.out.println("1-preemptive Shortest job first(SRTF)");
        System.out.println("2-Round Robin");
        System.out.println("3-Priority Scheduling");
        System.out.println("4-Multi Level Scheduling");

        Technique = Integer.parseInt(sc.nextLine());

        if (Technique == 1 || Technique == 2) {
            System.out.println("For next " + numberOfProcesses + " Processes :");
            System.out.println("Enter [ Name, Arrival Time, Burst Time]\nSeparated by Comma(,)");
            for (int i = 0; i < numberOfProcesses; i++) {
                System.out.println("\nProcess " + (i + 1) + " : ");
                String in = sc.nextLine();
                String[] Fields = in.split(",");
                ProcessName = Fields[0];
                ProcessArrivalTime = Double.parseDouble(Fields[1]);
                ProcessBurstTime = Double.parseDouble(Fields[2]);
                Process tempProcess = new Process(ProcessName, ProcessArrivalTime, ProcessBurstTime);
                processes.add(tempProcess);
            }
        } else if (Technique == 3) {
            System.out.println("For next " + numberOfProcesses + " Processes :");
            System.out.println("Enter [ Name, Arrival Time, Burst Time ,Priority ]\nSeparated by Comma(,)");
            for (int i = 0; i < numberOfProcesses; i++) {
                System.out.println("\nProcess " + (i + 1) + " : ");
                String in = sc.nextLine();
                String[] Fields = in.split(",");
                ProcessName = Fields[0];
                ProcessArrivalTime = Double.parseDouble(Fields[1]);

                ProcessBurstTime = Double.parseDouble(Fields[2]);

                ProcessPriorityNumber = Double.parseDouble(Fields[3]);

                Process tempProcess = new Process(ProcessName, ProcessArrivalTime, ProcessBurstTime, ProcessPriorityNumber);
                processes.add(tempProcess);
            }
        } else if (Technique == 4) {
            System.out.println("For next " + numberOfProcesses + " Processes :");
            System.out.println("Enter [ Name, Arrival Time, Burst Time, Queue Number(1 or 2)]\nSeparated by Comma(,)");
            for (int i = 0; i < numberOfProcesses; i++) {
                System.out.println("\nProcess " + (i + 1) + " : ");
                String in = sc.nextLine();
                String[] Fields = in.split(",");
                ProcessName = Fields[0];
                ProcessArrivalTime = Double.parseDouble(Fields[1]);
                ProcessBurstTime = Double.parseDouble(Fields[2]);
                Queue_No = Integer.parseInt(Fields[3]);
                Process tempProcess = new Process(ProcessName, ProcessArrivalTime, ProcessBurstTime, Queue_No);
                processes.add(tempProcess);
            }
        }

        if (Technique == 1) {
            System.out.println("Enter Context Switching : ");
            contextSwitching = Double.parseDouble(sc.nextLine());
            preemptiveShortestJobFirst ob = new preemptiveShortestJobFirst(processes, contextSwitching);
            ob.scheduling();
        }
//        p1,0,4
//        p2,0,3
//        p3,0,8
//        p4,10,5
        if (Technique == 2) {
            System.out.println("Enter quantum time");
            int quantumTime = sc.nextInt();
            System.out.println("Enter Context Switching : ");
            contextSwitching = sc.nextDouble();
            RoundRobin rr = new RoundRobin(processes, contextSwitching);
            rr.roundRobin(quantumTime, processes);
        }

        /*
        p1,10,3
        p2,1,1
        p3,2,4
        p4,1,5
        p5,5,2
         */
        if (Technique == 3) {
            Priority scheduler = new Priority(processes);
            scheduler.scheduling();

        }

        if (Technique == 4) {
            System.out.println("\nEnter quantum time : ");
            int quantumTime = sc.nextInt();
            System.out.println("Enter Context Switching : ");
            contextSwitching = sc.nextDouble();
            Multi_Level ml = new Multi_Level(processes, quantumTime, contextSwitching);
            ml.Multi_Scheduling();
        }
    }
}
