
package com.djs.learn.socketserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TestMain
{
	public static void main(String[] args){
		int iPort = 1111;
		ServerSocket svrSock = null;
		Socket clientSock = null;
		BufferedReader bufReader = null;
		int iTimeSlice = 1000; // milliseconds.
		String szMessage = null;

		try {
			svrSock = new ServerSocket(iPort);
			svrSock.setSoTimeout(iTimeSlice);

			System.out.println("Server starts listening port " + iPort + " with time slice " + iTimeSlice + " milliseconds...");

			do {
				try {
					// Block listening.
					clientSock = svrSock.accept();

					System.out.println("Receive connection from IP " + clientSock.getInetAddress().toString());

					bufReader = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));

					szMessage = bufReader.readLine();

					System.out.println("Receive message = " + szMessage);
				} catch (SocketTimeoutException e) {
					// Ignored timeout.
				} catch (Exception e) {
					System.err.println("Client socket failed. Ignored exception = " + e);
				} finally {
					try {
						if (bufReader != null) {
							bufReader.close();
							bufReader = null;
						}

						if (clientSock != null) {
							clientSock.close();
							clientSock = null;
						}
					} catch (Exception e) {
						System.err.println("Clean client streams failed. Ignored exception = " + e);
					}
				}
			} while (true);
		} catch (Exception e) {
			System.err.println("Create server socket failed. Ignored exception = " + e);
		} finally {
			try {
				if (svrSock != null) {
					svrSock.close();
					svrSock = null;
				}
			} catch (Exception e) {
				System.err.println("Clean server streams failed. Ignored exception = " + e);
			}
		}
	}
}
