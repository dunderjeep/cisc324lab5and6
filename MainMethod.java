public class MainMethod {
	
  	public static void main (String argv[]) {
	int numberOfProcesses = 6;
	UserJob[] processes = new UserJob[numberOfProcesses + 1]; 
	UserJob.Job job;
	for (int i = 1; i <= numberOfProcesses; i++) {
		if (i % 2 == 0) processes[i] = new UserJob(i, UserJob.Job.CPU_BOUND);
		else processes[i] = new UserJob(i, UserJob.Job.IO_BOUND);
		processes[i].start(); 
	}
  } 
} 
