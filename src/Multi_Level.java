import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Multi_Level 
{
	private ArrayList<Process> Multi_level = new ArrayList<Process>();
	private ArrayList<Process> RR = new ArrayList<Process>();
	private ArrayList<Process> FCFS = new ArrayList<Process>();
	private Map<Process,Double> initialBurstTime = new HashMap<Process,Double>();
	private double context_Switching;
	int Quantum_Time;
	double multi_time=0;
	static int i=0;
	
    public Multi_Level(ArrayList<Process> pr,int Quantum ,Double cs)
    {
    	Multi_level = pr;
    	Collections.sort(Multi_level);
    	Quantum_Time = Quantum;
    	context_Switching = cs;
    	for (int i=0 ; i<Multi_level.size() ; i++)
    	{
    		initialBurstTime.put(Multi_level.get(i),Multi_level.get(i).ProcessBurstTime);
    	}
    }
    
    public void Multi_Scheduling()
    {
    	//ArrayList<Process> temp = new ArrayList<Process>();
    	for(i=0 ; i<Multi_level.size() ; i++)
    	{
    		if(Multi_level.get(i).Queue_No == 1)
    		{
    			if(Multi_level.get(i).ProcessArrivalTime > context_Switching && FCFS.size() != 0)
    			{
    				if(RR.size() != 0)
            		{
        				RoundRobin round_robin;
            			round_robin = new RoundRobin(RR,context_Switching);
                		round_robin.roundRobin(Quantum_Time,RR);
                		RR.clear();
                		this.context_Switching = RoundRobin.contextSwitching;
            		}
        			if(RR.size() == 0)
        			{
        				FCFS.add(Multi_level.get(i));
                		multi_time = next_RR_process();
            			FCFS_Scheduling();
        			}
    			}
    			RR.add(Multi_level.get(i));
    			continue;
    		}
    		else
    		{	
    			if(RR.size() != 0)
        		{
    				RoundRobin round_robin;
        			round_robin = new RoundRobin(RR,context_Switching);
            		round_robin.roundRobin(Quantum_Time,RR);
            		RR.clear();
            		this.context_Switching = RoundRobin.contextSwitching;
        		}
    			if(RR.size() == 0)
    			{
    				FCFS.add(Multi_level.get(i));
            		multi_time = next_RR_process();
        			FCFS_Scheduling();
        			continue;
    			}
    		}
    	}
    	if(RR.size() != 0)
		{
    		RoundRobin round_robin;
        	round_robin = new RoundRobin(RR,context_Switching);
    		round_robin.roundRobin(Quantum_Time,RR);
    		RR.clear();
    		this.context_Switching = RoundRobin.contextSwitching;
		}
		
		FCFS_Scheduling();
		displayScheduling(Multi_level);
		/*
		for(int j=0 ; j<Multi_level.size() ; j++)
    	{
			System.out.println("\n"+Multi_level.get(j).ProcessName);
			System.out.println("arrival = "+Multi_level.get(j).ProcessArrivalTime);
			System.out.println("burst = "+initialBurstTime.get(Multi_level.get(j)));
			System.out.println("finish = "+Multi_level.get(j).finish);
			System.out.println();
    	}*/
    }
    
    public void FCFS_Scheduling()
    {
    	Collections.sort(FCFS);
    	int time = (int)context_Switching;
    	
    	for(int j=0 ; j<FCFS.size() ; j++)
    	{
			while(FCFS.get(j).ProcessBurstTime != 0)
    		{
				if(time == multi_time)
	    		{
					if(FCFS.get(j).start != 0)
					{
						System.out.println(FCFS.get(j).ProcessName + ": start=" + (int)FCFS.get(j).start + " & finish=" + time);
					}
	    			break;
	    		}
				if(FCFS.get(j).start == 0)
				{
					FCFS.get(j).start = time;
				}
    			FCFS.get(j).ProcessBurstTime--;
    			time++;
    			context_Switching++;
    			if(FCFS.get(j).ProcessBurstTime == 0)
    			{
    				FCFS.get(j).isFinished = true;
    				FCFS.get(j).finish = time;
    	            System.out.println(FCFS.get(j).ProcessName + ": start=" + (int)FCFS.get(j).start + " & finish=" + (int)FCFS.get(j).finish);
    	            FCFS.clear();
    	            break;
    			}
    		}
    	}
    }
    
    double next_RR_process()
    {
    	for(int j=i ; j<Multi_level.size() ; j++)
    	{
    		if(Multi_level.get(j).Queue_No == 1)
    		{
    			return Multi_level.get(j).ProcessArrivalTime;
    		}
    	}
    	return 0;
    }
    
    
    
    public void displayScheduling(ArrayList<Process> processes) 
	{
		double averageWaiting = 0 ,averageTurnaroundTime = 0;
		for(int i = 0 ; i < processes.size(); i++) {
			processes.get(i).waitingTime = processes.get(i).finish - processes.get(i).ProcessArrivalTime - initialBurstTime.get(processes.get(i));
			averageWaiting += processes.get(i).waitingTime;
		}
		for(int i = 0 ; i < processes.size(); i++) {
			processes.get(i).turnaroundTime = processes.get(i).waitingTime + initialBurstTime.get(processes.get(i));
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
