
package com.djs.learn.maven_sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.djs.learn.wsclient.session_manager.SessionManager;
import com.djs.learn.wsclient.session_manager.SessionManagerService;

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
			svc =
			      new SessionManagerService( new URL( GeneralDefines.makeWsdlUrl( urlPart ) ), new QName(
			              "http://www.bea.com/wsdl/wlcp/wlng/session_manager/service", "SessionManagerService" ) );

			svc.setHandlerResolver( new HandlerResolver()
			{
				@Override
				public List<Handler> getHandlerChain( PortInfo portInfo )
				{
					List<Handler> handlerList = new ArrayList<Handler>();
					handlerList.add( new LoginSoapHandler() );

					return handlerList;
				}
			} );

			api = svc.getSessionManager();
			GeneralDefines.setSessionToken( api.getSession() );
		}
		catch (Exception e)
		{
			InfoHelper.showException( log, "", e );
		}
	}
}
