public class MainMethod {

  	public static void main (String argv[]) {
		
	UserJob UJ; 
	UserJob.Job job;  
	
	for (int i=0; i<6; i++) {
		if (i % 2 == 0) UJ = new UserJob(i, UserJob.Job.CPU_BOUND);
		else UJ = new UserJob(i, UserJob.Job.IO_BOUND);
		UJ.start(); 
	}
	System.out.println("This is main speaking");
  } 
} 
