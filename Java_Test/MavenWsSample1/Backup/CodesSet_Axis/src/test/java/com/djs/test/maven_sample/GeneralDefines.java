
package com.djs.learn.maven_sample;

import javax.xml.rpc.Stub;
import javax.xml.soap.SOAPElement;

import org.apache.axis.message.SOAPHeaderElement;
import org.apache.log4j.Logger;

public class GeneralDefines
{
	private static Logger log = Logger.getLogger( GeneralDefines.class );

	// Change this index for different user.
	public static final int USER_INDEX = 0;

	public static final String [] USER_IDS = new String []
	{ "app_ndp_test_user", "lbsE2e", "lbsE2eAds", "tester1" };
	public static final String [] PASSWORDS = new String []
	{ "app_ndp_test_user", "lbsE2e", "lbsE2eAds", "tester1" };

	// Change this index for different host.
	public static final int HOST_INDEX = 0;

	public static final String [] REMOTE_HOSTS = new String []
	{ "127.0.0.1", "10.252.84.21", "10.252.84.23", "10.252.84.24", "10.252.84.25", "10.252.84.26" };
	public static final int [] REMOTE_PORTS = new int []
	{ 10080, 10080, 10081, 10081, 80, 10081 };
	public static final String [] URL_PREFIXS = new String [REMOTE_HOSTS.length];

	public static final int CONNECTION_TIMEOUT = 10000;
	public static final int READ_TIMEOUT = 10000;

	public static final boolean setReadTimeout = false;

	public static String sessionToken = null;

	static
	{
		for (int i = 0; i < URL_PREFIXS.length; i++)
		{
			URL_PREFIXS[i] = "http://" + REMOTE_HOSTS[i] + ":" + REMOTE_PORTS[i] + "/";
		}
	}

	/**
	 * Setup Stub (Axis).
	 * 
	 * @param log
	 *        Logger.
	 * @param stub
	 *        Stub.
	 * @param urlPart
	 */
	public static void setupStubAxis( Logger log, Stub stub, String urlPart ) throws Exception
	{
		String url = URL_PREFIXS[HOST_INDEX] + urlPart;

		log.debug( "User ID : " + USER_IDS[USER_INDEX] );
		log.debug( "Password: " + PASSWORDS[USER_INDEX] );
		log.debug( "URL     : " + url );
		log.debug( "Connection timeout: " + CONNECTION_TIMEOUT );

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

		SOAPHeaderElement headerElement =
		                                  new SOAPHeaderElement( "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
		                                          "Security" );
		SOAPElement usernameToken = headerElement.addChildElement( "UsernameToken" );
		SOAPElement userName = usernameToken.addChildElement( "Username" );
		userName.addTextNode( USER_IDS[USER_INDEX] );
		SOAPElement password = usernameToken.addChildElement( "Password" );
		password.addTextNode( PASSWORDS[USER_INDEX] );
		((org.apache.axis.client.Stub)stub).setHeader( headerElement );

		/*
		stub._setProperty( Call.USERNAME_PROPERTY, USER_IDS[USER_INDEX] );
		stub._setProperty( Call.PASSWORD_PROPERTY, PASSWORDS[USER_INDEX] );
		// ((org.apache.axis.client.Stub)stub).setUsername( USER_IDS[USER_INDEX] );
		// ((org.apache.axis.client.Stub)stub).setPassword( PASSWORDS[USER_INDEX] );
		// If set, set to a larger number, such as 60000.
		// ((org.apache.axis.client.Stub)stub).setTimeout( GeneralDefines.CONNECTION_TIMEOUT );
		// stub._setProperty( Stub.ENDPOINT_ADDRESS_PROPERTY, url );
		*/
	}

	/**
	 * Add SOAP SessionId (Axis).
	 * 
	 * @param funcId
	 * @param stub
	 *        Stub.
	 * @param urlPart
	 */
	public static void addSoapSessionId( Stub stub ) throws Exception
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

		SOAPHeaderElement headerElement = new SOAPHeaderElement( "http://schemas.xmlsoap.org/soap/envelope/", "session" );
		SOAPElement sessionId = headerElement.addChildElement( "SessionId" );
		sessionId.addTextNode( GeneralDefines.getSessionToken() );
		((org.apache.axis.client.Stub)stub).setHeader( headerElement );
	}

	/**
	 * Make URL.
	 * 
	 * @param urlPart
	 * @return String
	 */
	public static String makeUrl( String urlPart )
	{
		String url = URL_PREFIXS[HOST_INDEX] + urlPart;

		log.debug( "URL: " + url );

		return url;
	}

	/**
	 * Make URL with WSDL.
	 * 
	 * @param urlPart
	 * @return String
	 */
	public static String makeWsdlUrl( String urlPart )
	{
		String url = URL_PREFIXS[HOST_INDEX] + urlPart + "?WSDL";

		log.debug( "WSDL URL: " + url );

		return url;
	}

	/**
	 * Set session token.
	 * 
	 * @param token
	 */
	public static void setSessionToken( String token )
	{
		sessionToken = token;

		log.debug( "Session token: " + sessionToken );
	}

	/**
	 * Get session token.
	 * 
	 * @return String
	 */
	public static String getSessionToken()
	{
		return sessionToken;
	}
}
