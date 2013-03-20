//		CISC 324 - Operating Systems
//			Lab 5 & 6
//		Cliff Coulter 04192150
//		Last edited: March 20, 2013

// This program demonstrates synchronization of processes that simulating an operating system
// with N CPUs, 1 DiskDrive and K processes (aka UserJobs) performing several iterations of work.
// Different CPUs may be accessed in parallel with each CPU running one process at a time.
// The DiskDrive may only be accessed by one processes at a time and simulates the time
// it takes to move the disk head and read/write a sector. 
//
// The Main program creates an instance of the CPU and DiskDrive and passes these to the UserJob to share.
// The DiskDrive and CPUs make use of the synchronization keyword to lock the methods which need
// to block other methods.  Since the DiskDrive is an exclusive method, wait and notify are not needed
// since usage of the method is exclusive, ie implicity blocking other methods.  In contrast,
// the CPU class uses wait() and notify() in its synchronized blocks since it needs to coordinate the
// parallel usage of other CPUs.  The other CPUs are tracked in a static array as part of the CPU class.
// UserJob extends the tread class so it may run in parallel to other instances of itself.
// The Output.txt file will show that only one instance of the UserJob accesses the DiskDrive at a time,
// while multiple CPUs work in parallel.

public class MainMethod {
	
  	public static void main (String argv[]) {
	int numberOfProcesses = 20;
	UserJob[] processes = new UserJob[numberOfProcesses + 1]; 
	UserJob.Job job;
	CPUmonitor cpuMon = new CPUmonitor();
	DiskDrive diskMon = new DiskDrive();

	// Create an array of processes and pass instances of CPU and DiskDrive to them to share (thanks DM)
	for (int i = 1; i <= numberOfProcesses; i++) {		// initialized to 1 since CPUs array will consider 0 a free CPU
		// Half the processes are CPU_BOUND and half are IO_BOUND taking different random times to execute
		if (i % 2 == 0) processes[i] = new UserJob(i, UserJob.Job.CPU_BOUND, cpuMon, diskMon);
		else processes[i] = new UserJob(i, UserJob.Job.IO_BOUND, cpuMon, diskMon);
		processes[i].start(); 
	}
  } 
} 
