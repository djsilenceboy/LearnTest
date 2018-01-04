
package com.djs.learn.maven_sample;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.djs.learn.wsclient.sessionmanager.SessionManagerServiceStub;

public class ASessionManagerTest
{
	private final Logger log = Logger.getLogger( ASessionManagerTest.class );

	@Before
	public void setUp()
	{
	}

	// @Ignore("Ignored")
	@Test
	public void testGetSessionManager()
	{
		String urlPart = "session_manager/SessionManager";
		SessionManagerServiceStub svc = null;

		try
		{
			svc = new SessionManagerServiceStub( GeneralDefines.makeUrl( urlPart ) );
			GeneralDefines.setupStubAxis( log, svc, urlPart );
			GeneralDefines.setSessionToken( svc.getSession() );
		}
		catch (Exception e)
		{
			InfoHelper.showException( log, "", e );
		}
	}
}
