
package com.djs.learn.maven_sample;

import java.net.URL;

import javax.xml.rpc.Stub;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.djs.learn.wsclient.sessionmanager.SessionManager;
import com.djs.learn.wsclient.sessionmanager.SessionManagerService;
import com.djs.learn.wsclient.sessionmanager.SessionManagerServiceLocator;

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
		SessionManagerService svc = null;
		SessionManager api = null;

		try
		{
			svc = new SessionManagerServiceLocator();
			api = svc.getSessionManager( new URL( GeneralDefines.makeUrl( urlPart ) ) );
			GeneralDefines.setupStubAxis( log, (Stub)api, urlPart );
			GeneralDefines.setSessionToken( api.getSession() );
		}
		catch (Exception e)
		{
			InfoHelper.showException( log, "", e );
		}
	}
}
