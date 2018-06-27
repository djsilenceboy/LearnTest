
package com.djs.learn.maven_sample;

import java.net.URL;

import javax.xml.rpc.Stub;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.djs.learn.wsclient.example_test.SendDataService;
import com.djs.learn.wsclient.example_test.SendDataServiceLocator;
import com.djs.sample.ext.example_test.v1_0._interface.SendDataPort;
import com.djs.schema.example_test.local.SendDataRequest;
import com.djs.schema.example_test.local.SendDataResponse;

public class BExampleTestTest
{
	private static Logger log = Logger.getLogger( BExampleTestTest.class );

	private SendDataPort api = null;

	@Before
	public void setUp() throws Exception
	{
		String urlPart = "example_test/SendDataPort";
		SendDataService svc = null;

		try
		{
			svc = new SendDataServiceLocator();
			api = svc.getSendDataPort( new URL( GeneralDefines.makeUrl( urlPart ) ) );
			GeneralDefines.setupStubAxis( log, (Stub)api, urlPart );
			GeneralDefines.addSoapSessionId( (Stub)api );
		}
		catch (Exception e)
		{
			InfoHelper.showException( log, "", e );
		}
	}

	// @Ignore("Ignored")
	@Test
	public void testSendData()
	{
		SendDataRequest request = null;
		SendDataResponse response = null;

		try
		{
			request = new SendDataRequest();

			request.setData( "6512345678" );
			request.setAddress( new org.apache.axis.types.URI( "tel:6512345678" ) );

			log.debug( "Data: " + request.getData() );
			log.debug( "Address: " + request.getAddress() );

			response = api.sendData( request );

			log.debug( "Response: " + response );
		}
		catch (Exception e)
		{
			InfoHelper.showException( log, "", e );
		}
	}
}
