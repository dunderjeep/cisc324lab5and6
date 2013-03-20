import java.util.ArrayList;
import java.util.*;

public class CPUmonitor {
	
	// CPUs array shared among instances of CPUmonitor
	public static int[] CPUs = new int[0];	
	public static final int NUMBER_OF_CPUS = 3;
	
	public CPUmonitor() {
		init(NUMBER_OF_CPUS);		
	}

	// If no CPUs exist, initialize static array of free(ie =0) CPUs to 0
	private void init(int numberOfCPUs) {
		if (CPUs.length == 0) {
			CPUs = new int[numberOfCPUs];
			for (int i = 0; i < numberOfCPUs; i++) CPUs[i] = 0;
		}
	}
	
	// if all CPUs in use (ie != 0) then wait, otherwise use a CPU by setting the free CPU to pid
	// when finished wake up waiting UserJobs in case some are free
	public synchronized void startCPUuse(int pid) {
		while (full()) try { wait(); } catch(Exception e) {};
		replace(0, pid);
		System.out.println("UserJob " + pid + " is exectuing on CPU " + getPIDindex(pid));
		notifyAll();
	}

	// free CPU by setting pid to 0, if there are free CPUs, wake up any waiting UserJobs 
	public synchronized void endCPUuse(int pid) {
		replace(pid, 0);
		if (!full())
			notifyAll(); 		
	}

	// find a CPU containing pid or 0
	public int getPIDindex(int pid){
		for (int i = 0; i < CPUs.length; i++) {
			if (CPUs[i] == pid) return i;
		}
		return -1;
	}

	// swap values in CPU array
	private void replace(int target, int newValue) {
		for (int i = 0; i < CPUs.length; i++) {
			if (CPUs[i] == target) {
				CPUs[i] = newValue;
				return;
			} 
		}
	}

	// check if there are any free CPUs (ie any 0 elements)
	public static boolean full() {
		for (int i = 0; i < CPUs.length; i++) {
			if (CPUs[i] == 0) return false;
		}
		return true;
	}

	// debugging output
	private void printCPUs() {
		for (int i = 0; i < CPUs.length; i++) System.out.println("CPU " + i + " is running PID " + CPUs[i]);
	}

}
