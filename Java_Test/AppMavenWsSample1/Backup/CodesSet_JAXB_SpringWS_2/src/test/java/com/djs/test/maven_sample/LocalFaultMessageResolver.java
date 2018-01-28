
package com.djs.learn.maven_sample;

import java.io.IOException;
import java.util.Iterator;

import javax.xml.soap.MimeHeader;

import org.apache.log4j.Logger;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.FaultMessageResolver;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

public class LocalFaultMessageResolver implements FaultMessageResolver
{
	private final Logger log = Logger.getLogger( LocalFaultMessageResolver.class );

	@Override
	public void resolveFault( WebServiceMessage message ) throws IOException
	{
		if ((message != null) && (message instanceof SaajSoapMessage))
		{
			/*
			<2011.11.08 16:44:12.702>{ERROR}[com.djs.learn.maven_sample.LocalFaultMessageResolver:resolveFault] MIME header: [0]: Date: Tue
			<2011.11.08 16:44:12.702>{ERROR}[com.djs.learn.maven_sample.LocalFaultMessageResolver:resolveFault] MIME header: [1]: Date: 08 Nov 2011 08:44:12 GMT
			<2011.11.08 16:44:12.702>{ERROR}[com.djs.learn.maven_sample.LocalFaultMessageResolver:resolveFault] MIME header: [2]: Transfer-Encoding: chunked
			<2011.11.08 16:44:12.702>{ERROR}[com.djs.learn.maven_sample.LocalFaultMessageResolver:resolveFault] MIME header: [3]: Content-Type: text/xml; charset=utf-8
			<2011.11.08 16:44:12.702>{ERROR}[com.djs.learn.maven_sample.LocalFaultMessageResolver:resolveFault] MIME header: [4]: X-Powered-By: Servlet/2.5 JSP/2.1
			<2011.11.08 16:44:12.702>{ERROR}[com.djs.learn.maven_sample.LocalFaultMessageResolver:resolveFault] MIME header: [5]: SOAPAction: ""
			<2011.11.08 16:44:12.702>{ERROR}[com.djs.learn.maven_sample.LocalFaultMessageResolver:resolveFault] MIME header: [6]: Accept: text/xml
			<2011.11.08 16:44:12.702>{ERROR}[com.djs.learn.maven_sample.LocalFaultMessageResolver:resolveFault] MIME header: [7]: Content-Length: 481
			<2011.11.08 16:44:12.702>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement]  <env:Envelope> = null
			<2011.11.08 16:44:12.718>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] - <env:Header> = null
			<2011.11.08 16:44:12.733>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] - <env:Body> = null
			<2011.11.08 16:44:12.733>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] -- <env:Fault> = null
			<2011.11.08 16:44:12.733>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] --- <faultcode> = env:Server
			<2011.11.08 16:44:12.733>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] --- <faultstring> = null
			<2011.11.08 16:44:12.733>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] --- <detail> = null
			<2011.11.08 16:44:12.733>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] ---- <v2:ServiceException> = null
			<2011.11.08 16:44:12.733>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] ----- <messageId> = SVC0003
			<2011.11.08 16:44:12.733>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] ----- <text> = Invalid input value for message part %1, valid values are %2.
			<2011.11.08 16:44:12.733>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] ----- <variables> = Push Type
			<2011.11.08 16:44:12.733>{DEBUG}[com.djs.learn.maven_sample.LocalFaultMessageResolver:showSoapElement] ----- <variables> = [1 (SI), 2 (SL)] 
			*/

			Iterator items = ((SaajSoapMessage)message).getSaajMessage().getMimeHeaders().getAllHeaders();
			if (items != null)
			{
				int i = 0;
				while (items.hasNext())
				{
					MimeHeader item = (MimeHeader)items.next();

					log.error( "MIME header: [" + i++ + "]: " + item.getName() + ": " + item.getValue() );
				}
			}

			try
			{
				InfoHelper.showSoapElement( log, "", ((SaajSoapMessage)message).getSaajMessage().getSOAPBody().getParentElement() );
			}
			catch (Exception e)
			{

			}
		}

	}
}
