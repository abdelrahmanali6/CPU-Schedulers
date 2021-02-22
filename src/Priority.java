import java.util.ArrayList;

public class Priority {
	private ArrayList<Process> processes = new ArrayList<Process>();
	private double[] initialBurstTime;
	
    public Priority(ArrayList<Process> p)
    {
    	processes = p;
            
    	initialBurstTime = new double [processes.size()];
    	for (int i=0 ; i<processes.size() ; i++)
    	{
    		initialBurstTime[i] = processes.get(i).ProcessBurstTime;
    	}
    }
    public void scheduling( ) 
	{
		int currentTime = 0;
		System.out.println("\nProcesses execution order :");
		while (true) 
        {
			ArrayList<Process> tempArrivedProcesses = new ArrayList<Process>();
			for (int i=0 ; i<processes.size() ; i++)
            {
				if(processes.get(i).ProcessArrivalTime <= currentTime && processes.get(i).isFinished == false)
                {
                    tempArrivedProcesses.add(processes.get(i));
                }
			}        
                        
			int index = processes.indexOf(optimalProcess(currentTime));
			processes.get(index).start = currentTime;
			int serviceTime= (int) processes.get(index).ProcessBurstTime;
			for (int i=0 ; i<serviceTime ; i++) 
            {
				currentTime++;
				processes.get(index).ProcessBurstTime--;
				if (processes.get(index).ProcessBurstTime == 0) 
                {
					processes.get(index).isFinished = true;
					processes.get(index).finish = currentTime;
				}
				else if (optimalProcess(currentTime) != processes.get(index))
                {
					break; 
				}
			}
			if (processes.get(index).isFinished) 
            {
                System.out.println(processes.get(index).ProcessName + ": start=" + (int)processes.get(index).start + " & finish=" + (int)processes.get(index).finish);
            }
			else
            {
                System.out.println(processes.get(index).ProcessName + ": start=" + (int)processes.get(index).start + " & interrupted " + (int)currentTime);
            }
			
			if (isAllFinished())
            {
				break;
            }
		}
		for (int i=0 ; i<processes.size() ; i++)
        {
			processes.get(i).ProcessBurstTime = initialBurstTime[i];
        }
		displayScheduling();
	}
        
	private boolean isAllFinished () 
	{
		boolean finished = true;
		for (int i=0 ; i<processes.size() ; i++) 
        {
			if (processes.get(i).isFinished == false)
            {
                return false;
            }
		}
		return finished;
	}

	private Process optimalProcess(int CurrntTime)//1
    {
		ArrayList<Process> tempArrivedProcesses = new ArrayList<Process>();
		for (int i=0 ; i<processes.size() ; i++)
        {                    
            if(processes.get(i).ProcessArrivalTime <= CurrntTime  &&  processes.get(i).isFinished == false)
            {
                tempArrivedProcesses.add(processes.get(i));
            }
		}
		for (int i=0 ; i<tempArrivedProcesses.size()-1 ; i++) 
		{
			///Compare BurstTime Of Processes To decide the Smallest one TO Enter
			int least = i; 
			for (int j=i+1 ; j<tempArrivedProcesses.size(); j++) 
			{
				if (tempArrivedProcesses.get(j).ProcessPriorityNumber < tempArrivedProcesses.get(least).ProcessPriorityNumber)
				{
					least = j;
				}
			}
			///Swapping 2 processes
			Process temp = tempArrivedProcesses.get(i);
			tempArrivedProcesses.set( i , tempArrivedProcesses.get(least));
			tempArrivedProcesses.set( least, temp );
		}
		return tempArrivedProcesses.get(0);
	}
        
	private void displayScheduling() 
	{
		System.out.println("\nWaiting Time for each Process :");
		double waitingTime = 0;
		double tempWaitingTime = 0;
		for (int i=0 ; i<processes.size() ; i++) 
		{
			tempWaitingTime =processes.get(i).finish - (processes.get(i).ProcessArrivalTime + processes.get(i).ProcessBurstTime);
			waitingTime += tempWaitingTime;
			System.out.println(processes.get(i).ProcessName + " = " + (int)tempWaitingTime);
		}
		System.out.println("Average Waiting Time = " + waitingTime / processes.size());
		
		System.out.println("\nTurnaround  Time  for each process :");
		double turnaroundTime = 0;
		double tempTurnaroundTime = 0;
		for (int i=0 ; i<processes.size() ; i++) 
		{
			tempTurnaroundTime =processes.get(i).finish - processes.get(i).ProcessArrivalTime;
			turnaroundTime += tempTurnaroundTime;
			System.out.println(processes.get(i).ProcessName + " = " + (int)tempTurnaroundTime);
		}
		System.out.println("Average Turnaround Time = " + turnaroundTime / processes.size());
	}
}
