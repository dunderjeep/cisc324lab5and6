public class UserJob extends Thread {

	public static final int MIN_CPU_TIME = 100;
	public static final int MAX_CPU_TIME = 1000;
	public static final int MIN_IO_TIME = 1;
	public static final int MAX_IO_TIME = 200;

	private int myName;
	private int sleepytime;
	public int trackNumber;
	public enum Job { CPU_BOUND, IO_BOUND };
	Job myJob;
	private DiskDrive DD;
	private CPUmonitor CPU;
	
	public UserJob(int name, Job job, CPUmonitor CPUmon, DiskDrive diskMon) {
		myName = name;	
		myJob = job;		
		CPU = CPUmon;
		DD = diskMon;
	}

	public void run() {
		System.out.println("UserJob " + myName + " is starting.");	
		for (int i = 1; i < 3; i++) {	
			if (myJob == Job.CPU_BOUND) {
				sleepytime = MIN_CPU_TIME + (int)(Math.random() * ((MAX_CPU_TIME - MIN_CPU_TIME) + 1));
				System.out.println("UserJob " + myName + " starting CPU burst of length " + sleepytime + ".");
			}
			else {
				sleepytime = MIN_IO_TIME + (int)(Math.random() * ((MAX_IO_TIME - MIN_IO_TIME) + 1));
				System.out.println("UserJob " + myName + " starting IO burst of length CPUtime " + sleepytime + ".");
			}
			CPU.startCPUuse(myName);
			try { sleep(sleepytime); } catch(Exception e) {};
			CPU.endCPUuse(myName);
			System.out.println("UserJob " + myName + " requesting to access disk track " + trackNumber);
			trackNumber = (int)(Math.random() * ((DiskDrive.DISK_SIZE) + 1));
			DD.useTheDisk(trackNumber);
			System.out.println("UserJob " + myName + " finished reading disk track " + trackNumber);			
		}	
	}
}
