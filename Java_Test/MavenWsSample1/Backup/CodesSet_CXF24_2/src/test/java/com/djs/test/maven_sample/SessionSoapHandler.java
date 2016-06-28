
package com.djs.test.maven_sample;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

public class SessionSoapHandler implements SOAPHandler<SOAPMessageContext>
{
	private final Logger log = Logger.getLogger( SessionSoapHandler.class );

	@Override
	public boolean handleMessage( SOAPMessageContext context )
	{
		Boolean isRequest = (Boolean)context.get( MessageContext.MESSAGE_OUTBOUND_PROPERTY );

		if (isRequest)
		{
			try
			{
				/*
				<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
				<soapenv:Header>
				 <ns1:Security soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next" soapenv:mustUnderstand="0" xmlns:ns1="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
				    <ns1:UsernameToken>
				       <ns1:Username>app_ndp_test_user</ns1:Username>
				       <ns1:Password>app_ndp_test_user</ns1:Password>
				    </ns1:UsernameToken>
				 </ns1:Security>
				 <soapenv:session soapenv:actor="http://schemas.xmlsoap.org/soap/actor/next" soapenv:mustUnderstand="0">
				    <soapenv:SessionId>app:-2849803122594975884</soapenv:SessionId>
				 </soapenv:session>
				</soapenv:Header>
				<soapenv:Body>
				 <sendDataRequest xmlns="http://singtel.com/schema/example_test/local">
				    <data>6512345678</data>
				    <address>tel:6512345678</address>
				 </sendDataRequest>
				</soapenv:Body>
				</soapenv:Envelope> 
				*/

				SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();
				if (header == null)
				{
					header = envelope.addHeader();
				}

				Name headerElementName = envelope.createName( "session", "", "http://schemas.xmlsoap.org/soap/envelope/" );
				SOAPHeaderElement headerElement = header.addHeaderElement( headerElementName );
				SOAPElement sessionId = headerElement.addChildElement( "SessionId" );
				sessionId.addTextNode( GeneralDefines.getSessionToken() );

				InfoHelper.showSoapElement( log, "", envelope );
			}
			catch (Exception e)
			{
				InfoHelper.showException( log, "", e );
			}
		}

		return true;
	}

	@Override
	public boolean handleFault( SOAPMessageContext context )
	{
		return true;
	}

	@Override
	public Set<QName> getHeaders()
	{
		return null;
	}

	@Override
	public void close( MessageContext context )
	{
	}
}
