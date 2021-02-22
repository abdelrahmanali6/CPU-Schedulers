import java.util.ArrayList;
import java.util.Collections;

public class RoundRobin {

	public static ArrayList<Process> processes = new ArrayList<Process>();
	public static double contextSwitching = 0;
	private double[] initialBurstTime;
	
	public RoundRobin(ArrayList<Process> pr,double cs) 
    {
		Collections.sort(processes);
    	processes = pr;
    	contextSwitching = cs;
    	initialBurstTime = new double [processes.size()];
    	for (int i=0 ; i<processes.size() ; i++)
    	{
    		initialBurstTime[i] = processes.get(i).ProcessBurstTime;
    	}
    }
	
	static Process runProcess(Process process,int quantumTime) 
	{
		if(process.isFinished == false && contextSwitching >= process.ProcessArrivalTime ) 
		{
			if(process.ProcessBurstTime >= quantumTime) {
				process.ProcessBurstTime -= quantumTime;
				process.start = contextSwitching;
				contextSwitching += quantumTime;
				System.out.println(process.ProcessName + ": start=" + (int)process.start + " & finish=" + (int)contextSwitching);
				if(process.ProcessBurstTime == 0) {
					process.isFinished = true;
					process.finish = contextSwitching;
				}
			}
			else 
			{
				process.start = contextSwitching;
				contextSwitching += process.ProcessBurstTime;
				process.ProcessBurstTime = 0;
				process.isFinished = true;
				process.finish = contextSwitching;
				System.out.println(process.ProcessName + ": start=" + (int)process.start + " & finish=" + (int)contextSwitching);
			}
		}
		return process;
	}
	static boolean isAllFinished (ArrayList<Process>processes) 
	{
		boolean isFinished = true;
		for (int i = 0 ; i < processes.size() ; i++) 
        {
			if (processes.get(i).isFinished == false)
            {
                isFinished = false;
            }
		}
		return isFinished;
	}
	public void roundRobin(int quantumTime,ArrayList<Process>processes) 
	{
		Collections.sort(processes);
		boolean isFinished = false;
		int i = 0;
		while(isFinished == false) 
		{
			Process p = new Process();
			p = runProcess(processes.get(i),quantumTime);
			processes.get(i).isFinished = p.isFinished;
			processes.get(i).finish = p.finish;
			processes.get(i).ProcessBurstTime = p.ProcessBurstTime;
			isFinished = isAllFinished(processes);
			i++;
			if(i == processes.size())
				i = 0;
		}
		displayScheduling(processes);
	}
	private void displayScheduling(ArrayList<Process>processes) 
	{
		double averageWaiting = 0 ,averageTurnaroundTime = 0;
		for(int i = 0 ; i < processes.size(); i++) {
			processes.get(i).waitingTime = processes.get(i).finish - processes.get(i).ProcessArrivalTime - initialBurstTime[i];
			averageWaiting += processes.get(i).waitingTime;
		}
		for(int i = 0 ; i < processes.size(); i++) {
			processes.get(i).turnaroundTime = processes.get(i).waitingTime + initialBurstTime[i];
			averageTurnaroundTime += processes.get(i).turnaroundTime;
		}
		averageWaiting = averageWaiting / processes.size();
		averageTurnaroundTime = averageTurnaroundTime / processes.size();
		
		System.out.println("Process name"+"		"+"Waiting time"+"		"+"Turnaround time");
		for(int i = 0 ; i < processes.size(); i++) {
			System.out.println(processes.get(i).ProcessName+"			"+(int)processes.get(i).waitingTime+"			"+(int)processes.get(i).turnaroundTime);
		}
		System.out.println("average waiting = "+averageWaiting);
		System.out.println("average turnaround time = "+averageTurnaroundTime);
	}
}