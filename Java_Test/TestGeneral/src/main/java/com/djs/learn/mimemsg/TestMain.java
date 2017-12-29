
package com.djs.learn.mimemsg;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class TestMain
{
	public static void main(String[] args){
		try {
			String[][] szayMimeHeader = {{"HdName1", "Big"}, {"HdName2", "Small"}, {"HdName3", "Red"}, {"HdName4", "Green"}};
			// "text/plain; charset=us-ascii"
			String[] szayContentType = {"text/plain", "text/html", "image/gif", "image/jpeg"};
			String[] szayContent =
			                       {"Hello", "<html><head></head><body>Welcome</body></html>",
			                        "D:\\WorkSingTel\\Project_Test\\Java_Test\\MimeMessage\\temp\\sample.gif",
			                        "D:\\WorkSingTel\\Project_Test\\Java_Test\\MimeMessage\\temp\\sample.jpg"};

			Session session = Session.getInstance(new Properties());
			MimeMessage mimeMsg = new MimeMessage(session);
			MimeMultipart mmp = new MimeMultipart();

			MimeBodyPart[] aymbp = new MimeBodyPart[szayContentType.length];
			int i = 0;

			aymbp[i] = new MimeBodyPart();
			aymbp[i].setContent(szayContent[i], szayContentType[i]);
			aymbp[i].setHeader("Content-Transfer-Encoding", "binary");
			mmp.addBodyPart(aymbp[i]);

			i++;
			aymbp[i] = new MimeBodyPart();
			// aymbp[i].setHeader( "Content-Transfer-Encoding", "binary" );
			ByteArrayDataSource bads0 = new ByteArrayDataSource(szayContent[i].getBytes(), szayContentType[i]);
			DataHandler dh0 = new DataHandler(bads0);
			aymbp[i].setDataHandler(dh0);
			// Must add after setDataHandler()! Not before!
			// aymbp[i].setHeader( "Content-Transfer-Encoding", "binary" );
			mmp.addBodyPart(aymbp[i]);

			i++;
			aymbp[i] = new MimeBodyPart();
			InputStream is1 = new BufferedInputStream(new FileInputStream(szayContent[i]));
			ByteArrayDataSource bads1 = new ByteArrayDataSource(is1, szayContentType[i]);
			DataHandler dh1 = new DataHandler(bads1);
			aymbp[i].setDataHandler(dh1);
			// Must add after setDataHandler()! Not before!
			aymbp[i].setHeader("Content-Transfer-Encoding", "binary");
			mmp.addBodyPart(aymbp[i]);

			/*
			 * i++;
			 * aymbp[i] = new MimeBodyPart();
			 * InputStream is2 = new BufferedInputStream( new FileInputStream( szayContent[i] ) );
			 * ByteArrayDataSource bads2 = new ByteArrayDataSource( is2, szayContentType[i] );
			 * DataHandler dh2 = new DataHandler( bads2 );
			 * aymbp[i].setDataHandler( dh2 );
			 * // Must add after setDataHandler()! Not before!
			 * aymbp[i].setHeader( "Content-Transfer-Encoding", "binary" );
			 * mmp.addBodyPart( aymbp[i] );
			 */

			mimeMsg.setContent(mmp);
			mimeMsg.saveChanges();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			mimeMsg.writeTo(baos);
			System.out.println(baos);

			//

			/*
			 * ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			 * ObjectOutputStream oos2 = new ObjectOutputStream( baos2 );
			 * oos2.writeObject( mimeMsg );
			 * oos2.flush();
			 * oos2.close();
			 * System.out.println( "MimeMessage object size = " + baos2.size() );
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
