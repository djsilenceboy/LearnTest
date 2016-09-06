
package com.djs.learn.commonmail;

import java.io.File;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class TestMain
{
	public static final String szHostName = "EXGL06B.singtel.corp.root";
	public static final int iPort = -1;
	public static final String szFromAddr = "NoBody@noreply.com";
	public static final String szFromName = "Test";
	public static final String szToAddr = "dujiang@singtel.com";
	public static final String szToName = "Du Jiang";

	// All string parameters better not be null.
	// Address format must be "xxx@yyy", but can be an invalid content.
	// Subject can be empty or null.
	// Message can be empty.

	public void testSimpleEmail(){
		try {
			System.out.println("----- SimpleEmail -----");

			SimpleEmail email = new SimpleEmail();
			email.setHostName(szHostName);
			email.setFrom(szFromAddr, szFromName);
			email.addTo(szToAddr, szToName);

			System.out.println("HostName = " + szHostName);
			System.out.println("Port     = " + (iPort <= 0 ? email.getSmtpPort() : iPort));
			System.out.println("FromAddr = " + szFromAddr);
			System.out.println("FromName = " + szFromName);
			System.out.println("ToAddr = " + szToAddr);
			System.out.println("ToName = " + szToName);

			String szSubject = "Test message";
			String szMsg = "This is a simple test of commons-email";

			email.setSubject(szSubject);
			email.setMsg(szMsg);

			System.out.println("Subject = " + szSubject);
			System.out.println("Msg = " + szMsg);

			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testAttachment(){
		try {
			System.out.println("----- Attachment -----");

			// Create the email message
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(szHostName);
			email.setFrom(szFromAddr, szFromName);
			email.addTo(szToAddr, szToName);

			System.out.println("HostName = " + szHostName);
			System.out.println("Port     = " + (iPort <= 0 ? email.getSmtpPort() : iPort));
			System.out.println("FromAddr = " + szFromAddr);
			System.out.println("FromName = " + szFromName);
			System.out.println("ToAddr = " + szToAddr);
			System.out.println("ToName = " + szToName);

			String szSubject = "Test message with attachment";
			String szMsg = "This is a simple test of commons-email with attachment";

			email.setSubject(szSubject);
			email.setMsg(szMsg);

			System.out.println("Subject = " + szSubject);
			System.out.println("Msg = " + szMsg);

			// Create the attachment
			EmailAttachment attachment = new EmailAttachment();

			String szAttachmentPath = "./res/EdrLog.txt";
			String szAttachmentName = "EdrLog.txt";
			String szAttachmentDescription = "A sample Edr log file";

			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setPath(szAttachmentPath);
			attachment.setName(szAttachmentName);
			attachment.setDescription(szAttachmentDescription);

			System.out.println("AttachmentPath = " + szAttachmentPath);
			System.out.println("AttachmentName = " + szAttachmentName);
			System.out.println("AttachmentDescription = " + szAttachmentDescription);

			// add the attachment
			email.attach(attachment);

			// send the email
			email.send();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testHtmlEmail(){
		try {
			System.out.println("----- HtmlEmail -----");

			// Create the email message
			HtmlEmail email = new HtmlEmail();
			email.setHostName(szHostName);
			email.setFrom(szFromAddr, szFromName);
			email.addTo(szToAddr, szToName);

			System.out.println("HostName = " + szHostName);
			System.out.println("Port     = " + (iPort <= 0 ? email.getSmtpPort() : iPort));
			System.out.println("FromAddr = " + szFromAddr);
			System.out.println("FromName = " + szFromName);
			System.out.println("ToAddr = " + szToAddr);
			System.out.println("ToName = " + szToName);

			String szSubject = "Test message in HTML";

			email.setSubject(szSubject);

			System.out.println("Subject = " + szSubject);

			// set the alternative message
			String szTextMsg = "Your email client does not support HTML messages";

			System.out.println("TestMsg = " + szTextMsg);

			email.setTextMsg(szTextMsg);

			// embed the image and get the content id
			/*
			 * String szUrl = "http://www.google.com.sg/options/icons/alerts.gif";
			 * String szUrlName = "Google bell (url)";
			 * System.out.println( "Url = " + szUrl );
			 * System.out.println( "UrlName = " + szUrlName );
			 * URL url = new URL( szUrl );
			 * String szUrlCid = email.embed( url, szUrlName );
			 */

			// embed the image and get the content id
			String szFilePath = "./res/alerts.gif";
			String szFileId = "Google bell (file)";

			System.out.println("FilePath = " + szFilePath);
			System.out.println("FileId = " + szFileId);

			File fileImg = new File(szFilePath);
			String szFileCid = email.embed(fileImg);

			// set the html message
			// String szHtmlMsg = "<html>The apache logo - <img src=\"cid:" + szUrlCid + "\"></html>";
			String szHtmlMsg = "<html>The apache logo - <img src=\"cid:" + szFileCid + "\"></html>";

			System.out.println("HtmlMsg = " + szHtmlMsg);

			email.setHtmlMsg(szHtmlMsg);

			// Create the attachment
			EmailAttachment attachment = new EmailAttachment();

			String szAttachmentPath = "./res/EdrLog.txt";
			String szAttachmentName = "EdrLog.txt";
			String szAttachmentDescription = "A sample Edr log file";

			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setPath(szAttachmentPath);
			attachment.setName(szAttachmentName);
			attachment.setDescription(szAttachmentDescription);

			System.out.println("AttachmentPath = " + szAttachmentPath);
			System.out.println("AttachmentName = " + szAttachmentName);
			System.out.println("AttachmentDescription = " + szAttachmentDescription);

			// add the attachment
			email.attach(attachment);

			// send the email
			email.send();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		TestMain te = new TestMain();

		te.testSimpleEmail();
		// te.testAttachment();
		// te.testHtmlEmail();
	}
}
