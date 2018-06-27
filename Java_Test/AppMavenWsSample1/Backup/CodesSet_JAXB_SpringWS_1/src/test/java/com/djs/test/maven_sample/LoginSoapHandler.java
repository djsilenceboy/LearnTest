
package com.djs.learn.maven_sample;

import java.io.IOException;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

public class LoginSoapHandler implements WebServiceMessageCallback
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
	public void doWithMessage( WebServiceMessage message ) throws IOException, TransformerException
	{
		log.debug( "Username = " + userName );
		log.debug( "Password = " + password );

		try
		{
			/*
			<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
			<SOAP-ENV:Header>
			<wsee:Security xmlns:wsee="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
			<wsee:UsernameToken>
			<wsee:Username>app_test_user</wsee:Username>
			<wsee:Password>app_test_user</wsee:Password>
			</wsee:UsernameToken>
			</wsee:Security>
			</SOAP-ENV:Header>
			<SOAP-ENV:Body>
			<ns3:getSession xmlns:ns3="http://www.bea.com/wsdl/wlcp/wlng/session_manager/service" xmlns:ns4="http://djs.com/schema/example_test/local" xmlns:ns5="http://www.csapi.org/schema/parlayx/common/v2_1"/>
			</SOAP-ENV:Body></SOAP-ENV:Envelope>
			*/

			SOAPEnvelope envelope = ((SaajSoapMessage)message).getSaajMessage().getSOAPPart().getEnvelope();
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
				<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
				<SOAP-ENV:Header>
				<wsee:Security xmlns:wsee="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
				<wsee:UsernameToken>
				<wsee:Username>app_test_user</wsee:Username>
				<wsee:Password>app_test_user</wsee:Password>
				</wsee:UsernameToken>
				</wsee:Security>
				<SOAP-ENV:session>
				<SessionId>app:-2849803122594975884</SessionId>
				</SOAP-ENV:session>
				</SOAP-ENV:Header>
				<SOAP-ENV:Body>
				<ns5:sendDataRequest xmlns:ns3="http://www.csapi.org/schema/parlayx/common/v2_1" xmlns:ns4="http://www.bea.com/wsdl/wlcp/wlng/session_manager/service" xmlns:ns5="http://djs.com/schema/example_test/local">
				<ns5:data>6512345678</ns5:data>
				<ns5:address>tel:6512345678</ns5:address>
				</ns5:sendDataRequest>
				</SOAP-ENV:Body></SOAP-ENV:Envelope> 
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

			throw new TransformerException( e );
		}
	}
}
