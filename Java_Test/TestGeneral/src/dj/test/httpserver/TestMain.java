
package dj.test.httpserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TestMain
{
	/*
	 * test in IE with:
	 * http://localhost/temp/ok.txt
	 */

	public static void main(String[] args) throws Exception{
		final int httpd = 80;
		ServerSocket ssock = new ServerSocket(httpd);

		System.out.println("have opened port 80 locally");
		Socket sock = ssock.accept();

		System.out.println("client has made socket connection");
		OneConnection_B client = new OneConnection_B(sock);

		String s = client.getRequest();
		client.sendFile(s);
	}
}

class OneConnection
{
	Socket sock;
	BufferedReader in = null;
	DataOutputStream out = null;

	OneConnection(Socket sock) throws Exception{
		this.sock = sock;

		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));

		out = new DataOutputStream(sock.getOutputStream());
	}

	String getRequest() throws Exception{
		String s = null;

		while ((s = in.readLine()) != null) {
			System.out.println("got: " + s);
		}

		return s;
	}
}

class OneConnection_A extends OneConnection
{
	OneConnection_A(Socket sock) throws Exception{
		super(sock);
	}

	@Override
	String getRequest() throws Exception{
		String s = null;

		while ((s = in.readLine()) != null) {
			System.out.println("got: " + s);

			if (s.indexOf("GET") > -1) {
				out.writeBytes("HTTP-1.0 200 OK\r\n");

				s = s.substring(4);

				int i = s.indexOf(" ");

				System.out.println("file: " + s.substring(0, i));

				return s.substring(0, i);
			}
		}

		return null;
	}
}

class OneConnection_B extends OneConnection_A
{
	OneConnection_B(Socket sock) throws Exception{
		super(sock);
	}

	void sendFile(String fname) throws Exception{
		String where = fname;

		if (where.indexOf("..") > -1) {
			throw new SecurityException("No access to parent dirs");
		}

		System.out.println("looking for " + where);

		File f = new File(where);

		DataInputStream din = new DataInputStream(new FileInputStream(f));
		int len = (int)f.length();
		byte[] buf = new byte[len];

		din.readFully(buf);

		out.writeBytes("Content-Length: " + len + "\r\n");
		out.writeBytes("Content-Type: text/html\r\n\r\n");
		out.write(buf);
		out.flush();
		out.close();
	}
}
