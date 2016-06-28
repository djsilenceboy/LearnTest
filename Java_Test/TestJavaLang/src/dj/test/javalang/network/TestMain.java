
package dj.test.javalang.network;

import java.net.InetAddress;

public class TestMain
{
	public static void main(String a[]) throws Exception{
		InetAddress me = InetAddress.getByName("localhost");

		System.out.println("getByName( localhost ) = " + me);
		System.out.println("localhost name = " + me.getHostName());
		System.out.println("localhost IP = " + me.getHostAddress());

		InetAddress me2 = InetAddress.getLocalHost();

		System.out.println("getLocalHost() = " + me2);
		System.out.println("localhost name = " + me2.getHostName());
		System.out.println("localhost IP = " + me2.getHostAddress());

		InetAddress[] many = InetAddress.getAllByName("microsoft.com");

		for (int i = 0; i < many.length; i++) {
			System.out.println(many[i]);
		}
	}
}
