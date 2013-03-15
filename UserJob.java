public class UserJob extends Thread {
	int myName;
	int sleepytime;
	int trackNumber;
	public enum Job { CPU_BOUND, IO_BOUND};
	Job myJob;
	DiskDrive DD;
	
	public UserJob(int name, Job job, int time) {
		myName = name;	
		myJob = job;
		sleepytime = time;
		trackNumber = (int)(Math.random() * ((DiskDrive.DISK_SIZE) + 1));
	}

	public void run() {
		System.out.println("UserJob " + myName + " is starting.");	
		work();	
	}

	public void work() {
		for (int i = 0; i < 3; i++) {
			
			if (myJob == Job.CPU_BOUND) {
				System.out.println("UserJob " + myName + " starting CPU burst of length " + sleepytime + ".");
				try {sleep(sleepytime);} catch(Exception e) {};
			} else {
				DD.useTheDisk(trackNumber);
				System.out.println("UserJob " + myName + " starting IO burst of length CPUtime " + sleepytime + ".");
			}
			
		}
	}
}
