
package com.djs.learn.maven_sample;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.djs.learn.wsclient.example_test.SendDataPort;
import com.djs.learn.wsclient.example_test.SendDataRequest;
import com.djs.learn.wsclient.example_test.SendDataResponse;
import com.djs.learn.wsclient.session_manager.SessionManager;
import com.djs.learn.wsclient.wap_push.ExtWapPush;
import com.djs.learn.wsclient.wap_push.WapPushReqData;
import com.djs.learn.wsclient.wap_push.WapPushRespData;

public class AppTest
{
	private final Logger log = Logger.getLogger( AppTest.class );

	private ApplicationContext appContext = null;

	@Before
	public void setUp()
	{
	}

	private void loadPropertiesAndContext( String propFileName ) throws Exception
	{
		// This one doesn't have "classpath" prefix. Because it is loaded by Java.
		ConfigSettings cs = new ConfigSettings( "/spring_config/" + propFileName );
		Properties pp = cs.getProperties();
		Set<Object> keys = pp.keySet();

		for (Object item : keys)
		{
			String key = (String)item;
			String value = pp.getProperty( key );

			System.setProperty( key, value );

			if (log.isTraceEnabled())
			{
				log.trace( key + " = " + value );
			}
		}

		// Notes: AOP doesn't work while using XmlBeanFactory!
		appContext = new ClassPathXmlApplicationContext( System.getProperty( "file_path.context.entry_xml" ) );
	}

	// @Ignore
	@Test
	public void testSessionManager()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "test_ws/test_cxf.properties" );

			SessionManager api = (SessionManager)appContext.getBean( "sessionManager" );

			GeneralDefines.setSessionToken( api.getSession() );
		}
		catch (Exception e)
		{
			InfoHelper.showException( log, "", e );
		}
	}

	@Ignore
	@Test
	public void testExampleTest()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "test_ws/test_cxf.properties" );

			SendDataPort api = (SendDataPort)appContext.getBean( "exampleTest" );

			SendDataRequest request = new SendDataRequest();
			SendDataResponse response = null;

			request.setData( "6512345678" );
			request.setAddress( "tel:6512345678" );

			log.debug( "Data = " + request.getData() );
			log.debug( "Address = " + request.getAddress() );

			response = api.sendData( request );

			log.debug( "Reponse = " + response );
		}
		catch (Exception e)
		{
			if (e instanceof com.djs.learn.wsclient.example_test.ServiceException)
			{
				InfoHelper.showException( log, "", ((com.djs.learn.wsclient.example_test.ServiceException)e).getFaultInfo() );
			}
			else if (e instanceof com.djs.learn.wsclient.example_test.PolicyException)
			{
				InfoHelper.showException( log, "", ((com.djs.learn.wsclient.example_test.PolicyException)e).getFaultInfo() );
			}
			else
			{
				InfoHelper.showException( log, "", e );
			}
		}
	}

	// @Ignore
	@Test
	public void testWapPush()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "test_ws/test_cxf.properties" );

			ExtWapPush api = (ExtWapPush)appContext.getBean( "wapPush" );

			WapPushReqData reqest = new WapPushReqData();
			WapPushRespData response = null;
			Calendar cal = Calendar.getInstance();

			reqest.setPushType( new BigInteger( "1" ) );
			reqest.getAddresses().add( "tel:6596539588" );
			reqest.setUrl( "http://test.test_&_a.com?test=?&b=c" );
			reqest.setMessage( "This is a test. Hello from CME! :)" );
			cal.add( Calendar.SECOND, 30 );
			reqest.setDeliveryTimestamp( cal );

			log.debug( "PushType = " + reqest.getPushType() );
			log.debug( "Addresses = " + reqest.getAddresses() );
			log.debug( "Url = " + reqest.getUrl() );
			log.debug( "Message = " + reqest.getMessage() );
			log.debug( "DeliveryTimestamp = " + reqest.getDeliveryTimestamp() );

			response = api.sendWapPush( reqest );

			log.debug( "Reponse = " + response );

			if (response != null)
			{
				log.debug( "RequestIdentifier = " + response.getRequestIdentifier() );
			}
		}
		catch (Exception e)
		{
			if (e instanceof com.djs.learn.wsclient.wap_push.ServiceException)
			{
				InfoHelper.showException( log, "", ((com.djs.learn.wsclient.wap_push.ServiceException)e).getFaultInfo() );
			}
			else if (e instanceof com.djs.learn.wsclient.wap_push.PolicyException)
			{
				InfoHelper.showException( log, "", ((com.djs.learn.wsclient.wap_push.PolicyException)e).getFaultInfo() );
			}
			else
			{
				InfoHelper.showException( log, "", e );
			}
		}
	}
}
