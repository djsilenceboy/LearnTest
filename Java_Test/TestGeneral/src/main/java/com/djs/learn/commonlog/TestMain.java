
package com.djs.learn.commonlog;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.djs.learn.commonlog.sub.AnotherOne;

/**
 * <p>
 * Try this pattern for log4j: <%d{HH:mm:ss.SSS}>{%-5p}[%t:%c:%M] %m%n
 * <p>
 */
public class TestMain
{
	protected Log log = LogFactory.getLog(TestMain.class);

	public static void setJDKLog(String szFileName){
		Logger log = null;
		// ConsoleHandler chr = null;
		// StreamHandler shr = null;
		FileHandler fhr = null;

		try {
			// "" means root.
			log = Logger.getLogger("");

			// chr = new ConsoleHandler();
			// shr = new StreamHandler( System.out, new SimpleFormatter() );
			fhr = new FileHandler(szFileName);
			// If not add SimpleFormatter, it will use XML formatter.
			fhr.setFormatter(new SimpleFormatter());

			// log.addHandler( chr );
			log.addHandler(fhr);
			log.setLevel(Level.ALL);
		} catch (Exception e) {

		}
	}

	// Called repeatedly to process a particular argument value
	// which you want logged if debugging is enabled
	public void process_debug(String value){
		// Do the string concatenation only if logging is enabled
		// if (log.isDebugEnabled())
		{
			log.debug("[TestCommLog_DEBUG] " + value);
		}
	}

	public void process_info(String value){
		// Do the string concatenation only if logging is enabled
		// if (log.isInfoEnabled())
		{
			log.info("[TestCommLog_INFO] " + value);
		}
	}

	public void process_other(){
		AnotherOne tOne = new AnotherOne();

		tOne.process_debug("Bye");
		tOne.process_info("Bye");
	}

	public static void main(String[] args){
		// 1st log gets all output.
		// TestCommLog.setJDKLog( "jtest.log" );

		// When not load property file for Log4J, simple format will be used. 

		// Use full path with prefix "file:///".
		// System.setProperty( "log4j.configuration", "file:///F:/WorkMy/WorkDjs/Project_Learn/Java_Test/GeneralTestGroup/etc/config/log4j_1.properties" );

		// Same as System.setProperty for "log4j.configuration". But can use relative path.
		PropertyConfigurator.configure("./etc/config/log4j_2.properties");

		TestMain tTest = new TestMain();
		AnotherOne tOne = new AnotherOne();

		tTest.process_debug("Hello");
		tTest.process_info("Hello");
		tTest.process_other();

		// 2nd log only gets tOne's output.
		// TestCommLog.setJDKLog( "jtest2.log" );

		tOne.process_debug("Bye");
		tOne.process_info("Bye");
	}
}
