import java.util.ArrayList;
import java.util.*;

public class CPUmonitor {
	
	public static int[] CPUs = new int[0];	
	public static final int NUMBER_OF_CPUS = 3;
	
	public CPUmonitor() {
		init(NUMBER_OF_CPUS);		
	}

	private void init(int numberOfCPUs) {
		if (CPUs.length == 0) {
			CPUs = new int[numberOfCPUs];
			for (int i = 0; i < numberOfCPUs; i++) CPUs[i] = 0;
		}
	}

	public synchronized void startCPUuse(int pid) {
		while (full()) try { wait(); } catch(Exception e) {};
		printCPUs();
		replace(0, pid);
		System.out.println("UserJob " + pid + " starting to use CPU " + getPIDindex(pid));
		printCPUs();
		notifyAll();
	}

	public synchronized void endCPUuse(int pid) {
		printCPUs();
		replace(pid, 0);
		printCPUs();
		if (!full())
			notifyAll(); 		
	}

	public int getPIDindex(int pid){
		for (int i = 0; i < CPUs.length; i++) {
			if (CPUs[i] == pid) return i;
		}
		return -1;
	}

	private void replace(int target, int newValue) {
		for (int i = 0; i < CPUs.length; i++) {
			if (CPUs[i] == target) {
				CPUs[i] = newValue;
				return;
			} 
		}
	}

	public static boolean full() {
		for (int i = 0; i < CPUs.length; i++) {
			if (CPUs[i] == 0) return false;
		}
		return true;
	}

	private void printCPUs() {
		for (int i = 0; i < CPUs.length; i++) System.out.println("CPU " + i + " is running PID " + CPUs[i]);
	}

}
