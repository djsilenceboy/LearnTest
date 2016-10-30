
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
import org.springframework.ws.client.core.WebServiceTemplate;

import com.djs.learn.wsclient.example_test.SendDataRequest;
import com.djs.learn.wsclient.example_test.SendDataResponse;
import com.djs.learn.wsclient.session_manager.GetSession;
import com.djs.learn.wsclient.session_manager.GetSessionResponse;
import com.djs.learn.wsclient.wap_push.SendWapPush;
import com.djs.learn.wsclient.wap_push.SendWapPushResponse;
import com.djs.learn.wsclient.wap_push.WapPushReqData;

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
			loadPropertiesAndContext( "test_ws/test_springWs.properties" );

			WebServiceTemplate svc = (WebServiceTemplate)appContext.getBean( "sessionManager" );
			LoginSoapHandler soapHandler = (LoginSoapHandler)appContext.getBean( "loginSoapHandler" );

			GetSessionResponse response = (GetSessionResponse)svc.marshalSendAndReceive( new GetSession(), soapHandler );

			log.debug( "Response = " + response );

			if (response != null)
			{
				log.debug( "Session ID = " + response.getGetSessionReturn() );

				GeneralDefines.setSessionToken( response.getGetSessionReturn() );
			}
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	@Ignore
	@Test
	public void testExampleTest()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "test_ws/test_springWs.properties" );

			WebServiceTemplate svc = (WebServiceTemplate)appContext.getBean( "exampleTest" );
			LoginSoapHandler soapHandler = (LoginSoapHandler)appContext.getBean( "loginWithSessionSoapHandler" );

			SendDataRequest request = new SendDataRequest();
			SendDataResponse response = null;

			request.setData( "6512345678" );
			request.setAddress( "tel:6512345678" );

			log.debug( "Data = " + request.getData() );
			log.debug( "Address = " + request.getAddress() );

			response = (SendDataResponse)svc.marshalSendAndReceive( request, soapHandler );

			log.debug( "Response = " + response );
		}
		catch (Exception e)
		{
			log.error( "Exception = " + e, e );
		}
	}

	// @Ignore
	@Test
	public void testWapPush()
	{
		log.trace( "Enter..." );

		try
		{
			loadPropertiesAndContext( "test_ws/test_springWs.properties" );

			WebServiceTemplate svc = (WebServiceTemplate)appContext.getBean( "wapPush" );
			LoginSoapHandler soapHandler = (LoginSoapHandler)appContext.getBean( "loginWithSessionSoapHandler" );

			// svc.setFaultMessageResolver( new SimpleFaultMessageResolver() );
			// svc.setFaultMessageResolver( new LocalFaultMessageResolver() );

			SendWapPush request = new SendWapPush();
			SendWapPushResponse response = null;

			WapPushReqData reqestData = new WapPushReqData();
			Calendar cal = Calendar.getInstance();

			reqestData.setPushType( new BigInteger( "3" ) );
			reqestData.getAddresses().add( "tel:6596539588" );
			reqestData.setUrl( "http://test.test_&_a.com?test=?&b=c" );
			reqestData.setMessage( "This is a test. Hello from CME! :)" );
			cal.add( Calendar.SECOND, 30 );
			reqestData.setDeliveryTimestamp( cal );
			request.setWapPushRequest( reqestData );

			log.debug( "PushType = " + reqestData.getPushType() );
			log.debug( "Addresses = " + reqestData.getAddresses() );
			log.debug( "Url = " + reqestData.getUrl() );
			log.debug( "Message = " + reqestData.getMessage() );
			log.debug( "DeliveryTimestamp = " + reqestData.getDeliveryTimestamp() );

			response = (SendWapPushResponse)svc.marshalSendAndReceive( request, soapHandler );

			log.debug( "Response = " + response );

			if ((response != null) && (response.getWapPushResponse() != null))
			{
				log.debug( "RequestIdentifier = " + response.getWapPushResponse().getRequestIdentifier() );
			}
		}
		catch (Exception e)
		{
			InfoHelper.showException( log, "", e );

			// log.error( "Exception = " + e, e );
		}
	}
}
