public class MainMethod {

  	public static void main (String argv[]) {
	int numberOfProcesses = 6;	
	UserJob[] processes = new UserJob[numberOfProcesses]; 
	UserJob.Job job;	 	
	
	for (int i=0; i<6; i++) {
		if (i % 2 == 0) processes[i] = new UserJob(i, UserJob.Job.CPU_BOUND);
		else processes[i]= new UserJob(i, UserJob.Job.IO_BOUND);
		processes[i].start(); 
	}
	System.out.println("This is main speaking");
  } 
} 
