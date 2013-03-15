public class MainMethod {

	public static final int MIN_CPU_TIME = 100;
	public static final int MAX_CPU_TIME = 1000;
	public static final int MIN_IO_TIME = 1;
	public static final int MAX_IO_TIME = 200;

  	public static void main (String argv[]) {
		
	UserJob UJ; 
	UserJob.Job job;  
	
	for (int i=0; i<6; i++) {
		if (i % 2 == 0) {
			int CPUtime = MIN_CPU_TIME + (int)(Math.random() * ((MAX_CPU_TIME - MIN_CPU_TIME) + 1));
			UJ = new UserJob(i, UserJob.Job.CPU_BOUND, CPUtime);
		} else {
			int IOtime = MIN_IO_TIME + (int)(Math.random() * ((MAX_IO_TIME - MIN_IO_TIME) + 1));				
			UJ = new UserJob(i, UserJob.Job.IO_BOUND, IOtime);
		}	
		UJ.start(); 
	}
	System.out.println("This is main speaking");
  } 
} 
