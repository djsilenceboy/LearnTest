
package com.djs.learn.maven_sample;

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

public class LoginSoapHandler implements SOAPHandler<SOAPMessageContext>
{
	private final Logger log = Logger.getLogger( LoginSoapHandler.class );

	// Change this index for different user.
	public static final int USER_INDEX = 0;

	public static final String [] USER_IDS = new String []
	{ "app_test_user", "lbsE2e", "lbsE2eAds", "tester1" };
	public static final String [] PASSWORDS = new String []
	{ "app_test_user", "lbsE2e", "lbsE2eAds", "tester1" };

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
				       <ns1:Username>app_test_user</ns1:Username>
				       <ns1:Password>app_test_user</ns1:Password>
				    </ns1:UsernameToken>
				 </ns1:Security>
				</soapenv:Header>
				<soapenv:Body>
				 <getSession xmlns="http://www.bea.com/wsdl/wlcp/wlng/session_manager/service"/>
				</soapenv:Body>
				</soapenv:Envelope>
				 */

				SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();
				if (header == null)
				{
					header = envelope.addHeader();
				}

				Name headerElementName =
				                         envelope.createName( "Security", "wsee",
				                                              "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" );
				SOAPHeaderElement headerElement = header.addHeaderElement( headerElementName );

				// Must add "wsee" for those elements.
				SOAPElement usernameToken = headerElement.addChildElement( "UsernameToken", "wsee" );
				SOAPElement userName = usernameToken.addChildElement( "Username", "wsee" );
				userName.addTextNode( USER_IDS[USER_INDEX] );
				SOAPElement password = usernameToken.addChildElement( "Password", "wsee" );
				password.addTextNode( PASSWORDS[USER_INDEX] );

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
