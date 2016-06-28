
package dj.test.javalang.concurrency;

public class TestDeadLock2
{
	static class Friend
	{
		String name;

		public Friend(String name){
			this.name = name;
		}

		public String getName(){
			return name;
		}

		public synchronized void bow(Friend friend){
			System.out.println("[" + name + "] bow to friend [" + friend.getName() + "]");

			friend.bowBack(this);
		}

		public synchronized void bowBack(Friend friend){
			System.out.println("[" + name + "] was bowed back from friend [" + friend.getName() + "]");
		}
	}

	static class SampleClient implements Runnable
	{
		Friend friendA;
		Friend friendB;

		public SampleClient(Friend friendA, Friend friendB){
			this.friendA = friendA;
			this.friendB = friendB;
		}

		public void run(){
			String logId = "[" + Thread.currentThread().getName() + "] ";
			System.out.println(logId + "Start.");

			System.out.println(logId + "[" + friendA.getName() + "] bow to friend [" + friendB.getName() + "]");
			friendA.bow(friendB);

			System.out.println(logId + "Stop.");
		}
	}

	public static void main(String[] args){
		String logId = "[" + Thread.currentThread().getName() + "] ";

		Friend friendA = new Friend("Tom");
		Friend friendB = new Friend("Jerry");

		Thread[] threads = new Thread[]{new Thread(new SampleClient(friendA, friendB)), new Thread(new SampleClient(friendB, friendA))};

		for (Thread thread : threads) {
			thread.setDaemon(true);
			thread.start();
		}

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
	}
}
