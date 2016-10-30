
package com.djs.learn.maven_sample;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.Stub;
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
	public static final int HOST_INDEX = 1;

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
		<soapenv:Header>
		 <wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
		    <wsse:UsernameToken>
		       <wsse:Username>app_ndp_test_user</wsse:Username>
		       <wsse:Password>app_ndp_test_user</wsse:Password>
		    </wsse:UsernameToken>
		 </wsse:Security>
		</soapenv:Header>
		*/

		SOAPFactory fac = OMAbstractFactory.getSOAP12Factory();
		OMNamespace omNs = fac.createOMNamespace( "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse" );
		OMElement securityToken = fac.createOMElement( "Security", omNs );
		OMElement usernameToken = fac.createOMElement( "UsernameToken", omNs );
		OMElement userName = fac.createOMElement( "Username", omNs );
		userName.setText( USER_IDS[USER_INDEX] );
		OMElement password = fac.createOMElement( "Password", omNs );
		password.setText( PASSWORDS[USER_INDEX] );
		usernameToken.addChild( userName );
		usernameToken.addChild( password );
		securityToken.addChild( usernameToken );
		ServiceClient serviceClient = stub._getServiceClient();
		serviceClient.addHeader( securityToken );
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
		SOAPFactory fac = OMAbstractFactory.getSOAP12Factory();
		OMNamespace omNs = fac.createOMNamespace( "http://schemas.xmlsoap.org/soap/envelope/", "wsse" );
		OMElement sessionToken = fac.createOMElement( "session", omNs );
		OMElement sessionId = fac.createOMElement( "SessionId", omNs );
		sessionId.setText( GeneralDefines.getSessionToken() );
		sessionToken.addChild( sessionId );
		ServiceClient serviceClient = stub._getServiceClient();
		serviceClient.addHeader( sessionToken );

		/*
		SOAPHeaderElement headerElement = new SOAPHeaderElement( "http://schemas.xmlsoap.org/soap/envelope/", "session" );
		SOAPElement sessionId = headerElement.addChildElement( "SessionId" );
		sessionId.addTextNode( GeneralDefines.getSessionToken() );
		((org.apache.axis.client.Stub)stub).setHeader( headerElement );
		*/
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
