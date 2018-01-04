
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

	private String userName;
	private String password;
	private boolean addSessionId;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public boolean isAddSessionId()
	{
		return addSessionId;
	}

	public void setAddSessionId( boolean addSessionId )
	{
		this.addSessionId = addSessionId;
	}

	@Override
	public boolean handleMessage( SOAPMessageContext context )
	{
		Boolean isRequest = (Boolean)context.get( MessageContext.MESSAGE_OUTBOUND_PROPERTY );

		if (isRequest)
		{
			log.debug( "Username = " + userName );
			log.debug( "Password = " + password );

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
				SOAPElement userNameElement = usernameToken.addChildElement( "Username", "wsee" );
				userNameElement.addTextNode( userName );
				SOAPElement passwordElement = usernameToken.addChildElement( "Password", "wsee" );
				passwordElement.addTextNode( password );

				if (addSessionId)
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

					log.debug( "SessionId = " + GeneralDefines.getSessionToken() );

					Name headerElementName2 = envelope.createName( "session", "", "http://schemas.xmlsoap.org/soap/envelope/" );
					SOAPHeaderElement headerElement2 = header.addHeaderElement( headerElementName2 );
					SOAPElement sessionId = headerElement2.addChildElement( "SessionId" );
					sessionId.addTextNode( GeneralDefines.getSessionToken() );
				}

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
