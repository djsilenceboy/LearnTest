
package dj.test.queuemanage;

public class TestMain
{
	public static void main(String[] args){
		TaskQueue<TaskInfo> tq = new TaskQueue<TaskInfo>();

		TaskEnqueueThread teq = new TaskEnqueueThread(tq);
		TaskDequeueThread tdq = new TaskDequeueThread(tq);

		tdq.start();
		teq.start();
	}
}
