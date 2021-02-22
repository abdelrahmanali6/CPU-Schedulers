
public class Process implements Comparable<Process>{
	public String ProcessName ;
	public Double ProcessArrivalTime ;
	public double ProcessBurstTime ;
	public int Queue_No;
	public boolean isFinished = false ;
	public double waitingTime;
	public double turnaroundTime;
	public double start ;
    public double finish ;
        	public double ProcessPriorityNumber ;

        
    public Process() {}	
    

    public Process(String ProcessName, double ProcessArrivalTime, double ProcessBurstTime) 
    {
        this.ProcessName = ProcessName;
        this.ProcessArrivalTime = ProcessArrivalTime;
        this.ProcessBurstTime = ProcessBurstTime;
        this.isFinished = false;
        this.start = 0;
        this.finish = 0;
    }
    
    public Process(String ProcessName, double ProcessArrivalTime, double ProcessBurstTime, int Queue_No) 
    {
        this.ProcessName = ProcessName;
        this.ProcessArrivalTime = ProcessArrivalTime;
        this.ProcessBurstTime = ProcessBurstTime;
        this.Queue_No = Queue_No;
        this.isFinished = false;
        this.start = 0;
        this.finish = 0;
                this.ProcessPriorityNumber = ProcessPriorityNumber;

    }
    public Process(String ProcessName, double ProcessArrivalTime, double ProcessBurstTime, double ProcessPriorityNumber) 
    {
        this.ProcessName = ProcessName;
        this.ProcessArrivalTime = ProcessArrivalTime;
        this.ProcessBurstTime = ProcessBurstTime;
        this.isFinished = false;
        this.start = 0;
        this.finish = 0;
        this.ProcessPriorityNumber = ProcessPriorityNumber;

    }

   

    void setBurstTime(int b)
    {
        ProcessBurstTime=b;
    }
    void setPriority(int p)
    {
        ProcessPriorityNumber = p;
    }
    void setEndTime(int f)
    {
        finish = f;
    }
    public int compareTo(Process process) {  
	    return ProcessArrivalTime.compareTo(process.ProcessArrivalTime);      
	 }
    
    @Override
	public String toString() {
		return "  " + ProcessName /*+ "\t" + ProcessColor */+ "\t\t" + ProcessArrivalTime + "\t\t" + ProcessBurstTime + "\t\t" + ProcessPriorityNumber ;
	}
}
