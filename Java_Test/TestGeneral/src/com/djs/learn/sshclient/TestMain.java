
package com.djs.learn.sshclient;

import java.util.List;

import com.sshtools.j2ssh.SftpClient;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.sftp.SftpFile;
import com.sshtools.j2ssh.transport.ConsoleKnownHostsKeyVerification;

public class TestMain
{
	public static void main(String[] args){
		String szKnownHosts = "./etc/config/known_hosts";

		String szHost = "10.252.84.21";
		String szUserName = "ndp";
		String szPassword = "ndp";
		String szSrcPath = "deploy41/standalone";

		/*
		 * String szHost = "10.252.84.26";
		 * String szUserName = "ndp";
		 * String szPassword = "ND9pp@55";
		 * String szSrcPath = "deploy41";
		 */

		String szDstPath = "./etc/config/temp/data/";

		try {
			SshClient ssh = new SshClient();
			// ssh.connect( szHost );
			ssh.connect(szHost, new ConsoleKnownHostsKeyVerification(szKnownHosts));

			System.out.println("Authenticate...");

			// Authenticate.
			PasswordAuthenticationClient passwordAuthenticationClient = new PasswordAuthenticationClient();
			passwordAuthenticationClient.setUsername(szUserName);
			passwordAuthenticationClient.setPassword(szPassword);
			int iResult = ssh.authenticate(passwordAuthenticationClient);

			if (iResult != AuthenticationProtocolState.COMPLETE) {
				throw new Exception("Login to " + szHost + " with " + szUserName + "/" + szPassword + " failed.");
			}

			// Open the SFTP channel.
			SftpClient client = ssh.openSftpClient();

			client.cd(szSrcPath);

			List<SftpFile> files = client.ls();

			for (SftpFile sf : files) {
				System.out.println(sf.getFilename());

				if (!sf.getFilename().equals(".") && !sf.getFilename().equals("..")) {
					client.get(sf.getFilename(), szDstPath + sf.getFilename());
				}
			}

			client.quit();

			ssh.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
