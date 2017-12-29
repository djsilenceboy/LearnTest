
package com.djs.learn.jdklog;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class TestMain
{
	public static void main(String[] args){
		Logger logA = null;
		Logger logB = null;
		Logger logC = null;
		ConsoleHandler chr = null;
		StreamHandler shr = null;
		FileHandler fhr = null;

		try {
			// chr = new ConsoleHandler();
			// shr = new StreamHandler( System.out, new SimpleFormatter() );
			fhr = new FileHandler("mylog.txt");
			// If not add SimpleFormatter, it will use XML formatter.
			fhr.setFormatter(new SimpleFormatter());

			logA = Logger.getLogger("LA");
			// logB = Logger.getLogger( "LB", "LA" );
			logC = Logger.getLogger("LC");

			// logA.addHandler( chr );
			logA.addHandler(fhr);
			logA.setLevel(Level.ALL);
			// logB.setLevel( Level.ALL );
			logC.setLevel(Level.ALL);
			logC.setParent(logA);

			logA.log(Level.INFO, "Testing A info.");
			logA.log(Level.INFO, "Testing A info " + 123 + ".");
			// logB.log( Level.INFO, "Testing B info." );
			logC.log(Level.INFO, "Testing C info.");

			System.out.println("os.name: " + System.getProperty("os.name"));
			System.out.println("user.dir: " + System.getProperty("user.dir"));
			System.out.println("java.class.path: " + System.getProperty("usjava.class.path"));
		} catch (Exception e) {

		}
	}
}
