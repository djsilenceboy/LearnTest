
package dj.test.javalang.thread;

public class SampleSubThread extends Thread
{
	@Override
	public void run(){
		String logId = "[L2:" + Thread.currentThread().getName() + "] ";

		System.out.println(logId + "Name = " + this.getName());
		System.out.println(logId + "ID = " + this.getId());
		System.out.println(logId + "Priority = " + this.getPriority());
		System.out.println(logId + "Daemon = " + this.isDaemon());
	}
}
