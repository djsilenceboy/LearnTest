
package com.djs.learn.maven_sample;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.apache.log4j.Logger;
import org.csapi.schema.parlayx.djs.sample.ext.wappush.v1_0.WapPushReqData;
import org.csapi.schema.parlayx.djs.sample.ext.wappush.v1_0.WapPushRespData;
import org.csapi.wsdl.parlayx.djs.sample.ext.wappush.v1_0._interface.ExtWapPush;
import org.csapi.wsdl.parlayx.djs.sample.ext.wappush.v1_0.service.WapPushService;
import org.junit.Before;
import org.junit.Test;

public class CWapPushTest
{
	private static Logger log = Logger.getLogger( CWapPushTest.class );

	private ExtWapPush api = null;

	@Before
	public void setUp() throws Exception
	{
		String urlPart = "wappush/ExtWapPush";
		WapPushService svc = null;

		try
		{
			svc =
			      new WapPushService( new URL( GeneralDefines.makeWsdlUrl( urlPart ) ), new QName(
			              "http://www.csapi.org/wsdl/parlayx/djs/sample/ext/wappush/v1_0/service", "WapPushService" ) );

			svc.setHandlerResolver( new HandlerResolver()
			{
				@Override
				public List<Handler> getHandlerChain( PortInfo portInfo )
				{
					List<Handler> handlerList = new ArrayList<Handler>();
					handlerList.add( new LoginSoapHandler() );
					handlerList.add( new SessionSoapHandler() );

					return handlerList;
				}
			} );

			api = svc.getExtWapPush();
		}
		catch (Exception e)
		{
			InfoHelper.showException( log, "", e );
		}
	}

	// @Ignore("Ignored")
	@Test
	public void testSendWapPush()
	{
		WapPushReqData reqest = new WapPushReqData();
		WapPushRespData response = null;
		Calendar cal = Calendar.getInstance();
		try
		{
			reqest.setPushType( new BigInteger( "1" ) );
			reqest.getAddresses().add( "tel:6596539588" );
			reqest.setUrl( "http://test.test_&_a.com?test=?&b=c" );
			reqest.setMessage( "This is a test. Hello from CME! :)" );
			cal.add( Calendar.SECOND, 30 );
			reqest.setDeliveryTimestamp( cal );

			log.debug( "PushType : " + reqest.getPushType() );
			log.debug( "Addresses: " + reqest.getAddresses() );
			log.debug( "Url      : " + reqest.getUrl() );
			log.debug( "Message  : " + reqest.getMessage() );
			log.debug( "DeliveryTimestamp: " + reqest.getDeliveryTimestamp() );

			response = api.sendWapPush( reqest );

			log.debug( "Reponse = " + response );

			if (response != null)
			{
				log.debug( "RequestIdentifier = " + response.getRequestIdentifier() );
			}
		}
		catch (Exception e)
		{
			InfoHelper.showException( log, "", e );
		}
	}
}
