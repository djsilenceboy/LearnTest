
package com.djs.test.maven_sample;

import org.apache.log4j.Logger;

public class GeneralDefines
{
	private static Logger log = Logger.getLogger( GeneralDefines.class );

	private static String sessionToken = null;

	/**
	 * Set session token.
	 * 
	 * @param token
	 */
	public static void setSessionToken( String token )
	{
		sessionToken = token;

		log.debug( "Session token = " + sessionToken );
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
