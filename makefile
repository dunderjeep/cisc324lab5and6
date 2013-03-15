JCC = javac
JFLAGS = -g
default: MainMethod.class UserJob.class DiskDrive.class

MainMethod.class: MainMethod.java
	$(JCC) $(JFLAGS) MainMethod.java

UserJob.class: UserJob.java
	$(JCC) $(JFLAGS) UserJob.java

DiskDrive.class: DiskDrive.java
	$(JCC) $(JFLAGS) DiskDrive.java

clean:
	$(RM) *.class *~
