
package com.djs.learn.maven_sample;

import java.util.Iterator;

import javax.xml.soap.MimeHeader;
import javax.xml.soap.SOAPElement;
import javax.xml.transform.dom.DOMResult;

import org.apache.log4j.Logger;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceFaultException;
import org.springframework.ws.soap.SoapFaultDetailElement;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.w3c.dom.NodeList;

/**
 * Exception info.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2008-08-20 / Du Jiang : Creation
 * <li>2010-04-28 / Du Jiang : Impl.
 * </ul>
 */
public class InfoHelper
{
	/**
	 * Show exception.
	 * 
	 * @param log
	 *        Logger.
	 * @param paddingSpace
	 *        String.
	 * @param t
	 *        Throwable.
	 */
	public static void showException( Logger log, String paddingSpace, Throwable t )
	{
		int i;

		log.error( paddingSpace + "failed: class: " + t.getClass().getName() );

		if (t instanceof SoapFaultClientException)
		{
			// log.error( paddingSpace + "failed: SoapFault: " + ((SoapFaultClientException)t).getSoapFault() );

			Iterator<SoapFaultDetailElement> items = ((SoapFaultClientException)t).getSoapFault().getFaultDetail().getDetailEntries();
			if (items != null)
			{
				/*
				<2011.11.08 16:42:02.249>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: fault details: [0]: {http://www.csapi.org/schema/parlayx/common/v2_1}ServiceException
				<2011.11.08 16:42:02.249>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: fault details: [0]: result: ServiceException
				<2011.11.08 16:42:02.249>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: fault details: [0]: Node [0]: messageId/SVC0003
				<2011.11.08 16:42:02.249>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: fault details: [0]: Node [1]: text/Invalid input value for message part %1, valid values are %2.
				<2011.11.08 16:42:02.249>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: fault details: [0]: Node [2]: variables/Push Type
				<2011.11.08 16:42:02.249>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: fault details: [0]: Node [3]: variables/[1 (SI), 2 (SL)] 
				*/

				i = 0;
				while (items.hasNext())
				{
					SoapFaultDetailElement item = items.next();

					log.error( paddingSpace + "failed: fault details: [" + i + "]: " + item.getName() );

					DOMResult result = (DOMResult)item.getResult();
					log.error( paddingSpace + "failed: fault details: [" + i + "]: result: " + result.getNode().getLocalName() );

					NodeList nl = result.getNode().getChildNodes();
					for (int j = 0; j < nl.getLength(); j++)
					{
						log.error( paddingSpace + "failed: fault details: [" + i + "]: Node [" + j + "]: " + nl.item( j ).getLocalName() + "/"
						           + nl.item( j ).getTextContent() );
					}

					i++;
				}
			}
		}
		else if (t instanceof WebServiceFaultException)
		{
			WebServiceMessage wsm = ((WebServiceFaultException)t).getWebServiceMessage();

			if ((wsm != null) && (wsm instanceof SaajSoapMessage))
			{
				/*
				<2011.11.08 16:46:08.161>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: mime header: [0]: Date: Tue
				<2011.11.08 16:46:08.161>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: mime header: [1]: Date: 08 Nov 2011 08:46:08 GMT
				<2011.11.08 16:46:08.161>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: mime header: [2]: Transfer-Encoding: chunked
				<2011.11.08 16:46:08.161>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: mime header: [3]: Content-Type: text/xml; charset=utf-8
				<2011.11.08 16:46:08.161>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: mime header: [4]: X-Powered-By: Servlet/2.5 JSP/2.1
				<2011.11.08 16:46:08.161>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: mime header: [5]: SOAPAction: ""
				<2011.11.08 16:46:08.161>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: mime header: [6]: Accept: text/xml
				<2011.11.08 16:46:08.161>{ERROR}[com.djs.learn.maven_sample.AppTest:showException] failed: mime header: [7]: Content-Length: 481
				<2011.11.08 16:46:08.161>{DEBUG}[com.djs.learn.maven_sample.AppTest:showSoapElement]  <env:Body> = null
				<2011.11.08 16:46:08.161>{DEBUG}[com.djs.learn.maven_sample.AppTest:showSoapElement] - <env:Fault> = null
				<2011.11.08 16:46:08.161>{DEBUG}[com.djs.learn.maven_sample.AppTest:showSoapElement] -- <faultcode> = env:Server
				<2011.11.08 16:46:08.176>{DEBUG}[com.djs.learn.maven_sample.AppTest:showSoapElement] -- <faultstring> = null
				<2011.11.08 16:46:08.176>{DEBUG}[com.djs.learn.maven_sample.AppTest:showSoapElement] -- <detail> = null
				<2011.11.08 16:46:08.176>{DEBUG}[com.djs.learn.maven_sample.AppTest:showSoapElement] --- <v2:ServiceException> = null
				<2011.11.08 16:46:08.176>{DEBUG}[com.djs.learn.maven_sample.AppTest:showSoapElement] ---- <messageId> = SVC0003
				<2011.11.08 16:46:08.176>{DEBUG}[com.djs.learn.maven_sample.AppTest:showSoapElement] ---- <text> = Invalid input value for message part %1, valid values are %2.
				<2011.11.08 16:46:08.176>{DEBUG}[com.djs.learn.maven_sample.AppTest:showSoapElement] ---- <variables> = Push Type
				<2011.11.08 16:46:08.176>{DEBUG}[com.djs.learn.maven_sample.AppTest:showSoapElement] ---- <variables> = [1 (SI), 2 (SL)] 
				*/

				Iterator items = ((SaajSoapMessage)wsm).getSaajMessage().getMimeHeaders().getAllHeaders();
				if (items != null)
				{
					i = 0;
					while (items.hasNext())
					{
						MimeHeader item = (MimeHeader)items.next();

						log.error( paddingSpace + "failed: mime header: [" + i++ + "]: " + item.getName() + ": " + item.getValue() );
					}
				}

				try
				{
					showSoapElement( log, "", ((SaajSoapMessage)wsm).getSaajMessage().getSOAPBody() );
				}
				catch (Exception e)
				{

				}
			}
		}
		else
		{
			log.error( paddingSpace + "failed: message: " + t.getMessage() );
		}

		if (t.getCause() != null)
		{
			showException( log, paddingSpace + "  ", t.getCause() );
		}
	}

	/**
	 * Show SOAPElement contents.
	 * 
	 * @param log
	 *        Logger.
	 * @param paddingSpace
	 *        String.
	 * @param elem
	 *        SOAPElement.
	 */
	public static void showSoapElement( Logger log, String paddingSpace, SOAPElement elem )
	{
		// log.debug( paddingSpace + "Node name : " + elem.getNodeName() );
		// log.debug( paddingSpace + "Local name: " + elem.getLocalName() );
		// log.debug( paddingSpace + "Value     : " + elem.getValue() );

		log.debug( paddingSpace + " <" + elem.getNodeName() + "> = " + elem.getValue() );

		for (Iterator item = elem.getChildElements(); item.hasNext();)
		{
			Object obj = item.next();

			if (obj instanceof SOAPElement)
			{
				showSoapElement( log, paddingSpace + "-", (SOAPElement)obj );
			}
		}
	}
}
