
package com.djs.learn.maven_sample;

import org.apache.log4j.Logger;

public class GeneralDefines
{
	private static Logger log = Logger.getLogger( GeneralDefines.class );

	// Change this index for different host.
	public static final int HOST_INDEX = 0;

	public static final String [] REMOTE_HOSTS = new String []
	{ "127.0.0.1", "10.252.84.21", "10.252.84.23", "10.252.84.24", "10.252.84.25", "10.252.84.26" };
	public static final int [] REMOTE_PORTS = new int []
	{ 10080, 10080, 10081, 10081, 80, 10081 };
	public static final String [] URL_PREFIXS = new String [REMOTE_HOSTS.length];

	public static String sessionToken = null;

	static
	{
		for (int i = 0; i < URL_PREFIXS.length; i++)
		{
			URL_PREFIXS[i] = "http://" + REMOTE_HOSTS[i] + ":" + REMOTE_PORTS[i] + "/";
		}
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
