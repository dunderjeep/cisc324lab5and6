JCC = javac
JFLAGS = -g
default: MainMethod.class UserJob.class DiskDrive.class CPUmonitor.class

MainMethod.class: MainMethod.java
	$(JCC) $(JFLAGS) MainMethod.java

UserJob.class: UserJob.java
	$(JCC) $(JFLAGS) UserJob.java

DiskDrive.class: DiskDrive.java
	$(JCC) $(JFLAGS) DiskDrive.java

CPUmonitor.class: CPUmonitor.java
	$(JCC) $(JFLAGS) CPUmonitor.java

clean:
	rm *.class 
