public class DiskDrive {
	public static int oldTrack;
	public static final int DISK_SIZE = 1024;

	private int moveTime;
	private static final int READ_TIME = 1;	
		
	public DiskDrive() {}

	public synchronized void useTheDisk(int newTrack) {
		moveTime = Math.abs(newTrack - oldTrack);			
		System.out.println("Disk is at track number " + oldTrack + ", and will move " + moveTime + " tracks taking " + (moveTime + 1) + "  milliseconds.");			
		oldTrack = newTrack;
		try {Thread.sleep(moveTime + READ_TIME);} catch(Exception e) {};
	}
}
