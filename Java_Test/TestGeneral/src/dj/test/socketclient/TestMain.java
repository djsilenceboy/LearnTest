
package dj.test.socketclient;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TestMain
{
	public static void main(String[] args){
		String szHost = "localhost";
		final int iPort = 7770;
		final String szMessage = "DieHard\r\n";

		Socket so = null;
		BufferedWriter bw = null;

		try {
			System.out.println("Host = " + szHost);
			System.out.println("Port = " + iPort);

			so = new Socket(szHost, iPort);
			bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));

			System.out.println("Message = " + szMessage);
			bw.write(szMessage);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		} finally {
			try {
				if (bw != null) {
					bw.close();
					bw = null;
				}

				if (so != null) {
					so.close();
					so = null;
				}

				System.out.println("Clean client socket.");
			} catch (Exception e) {
				System.err.println("Clean client socket failed. Ignored exception = " + e);
			}
		}
	}
}
