public class DiskDrive {
	private int track = 0;
	private int moveTime;
	private static final int READ_TIME = 1;
	public static final int DISK_SIZE = 1024;
		
	public DiskDrive() {

	}

	public synchronized void useTheDisk(int t) {
		moveTime = Math.abs(t - track);	
		System.out.println("Disk is at track number " + track + ", and will move " + " tracks taking " + t + 1 + "  milliseconds.");			
		try {Thread.sleep(moveTime + READ_TIME);} catch(Exception e) {};
	}
}
