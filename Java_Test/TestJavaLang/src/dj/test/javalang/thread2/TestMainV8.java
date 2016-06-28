
package dj.test.javalang.thread2;

public class TestMainV8
{
	public void testRunnable(){
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
			}
		}).start();
	}

	public static void main(String[] args){
		TestMainV8 test = new TestMainV8();

		test.testRunnable();

		System.out.println("============================================================");
	}
}
