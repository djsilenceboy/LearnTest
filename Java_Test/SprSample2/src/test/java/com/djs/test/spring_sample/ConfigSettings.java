
package com.djs.test.spring_sample;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ConfigSettings
{
	/**
	 * The logger.
	 */
	private final Logger log = Logger.getLogger( ConfigSettings.class );

	/**
	 * Properties loaded from file.
	 */
	private Properties properties = null;

	/**
	 * Constructor.
	 * 
	 * @param fileName
	 *        Relative path in classpath.
	 * @throws Exception
	 */
	public ConfigSettings( String fileName ) throws Exception
	{
		InputStream is = null;

		try
		{
			if (log.isDebugEnabled())
			{
				log.debug( "Load file \"" + fileName + "\"." );
			}

			is = getClass().getResourceAsStream( fileName );

			properties = new Properties();
			properties.load( is );

			if (log.isDebugEnabled())
			{
				log.debug( "Loaded file \"" + fileName + "\"." );
			}
		}
		catch (Exception e)
		{
			if (log.isEnabledFor( Level.ERROR ))
			{
				log.error( "Load file \"" + fileName + "\" failed. Exception = " + e, e );
			}

			throw e;
		}
		finally
		{
			if (is != null)
			{
				is.close();
			}
		}
	}

	public Properties getProperties()
	{
		return properties;
	}

	/**
	 * Get setting value by key.
	 * 
	 * @param key
	 * @return String - value.
	 * @throws Exception
	 */
	public String getValue( String key ) throws Exception
	{
		if (!properties.containsKey( key ))
		{
			if (log.isEnabledFor( Level.ERROR ))
			{
				log.error( "Key \"" + key + "\" is not found." );
			}

			throw new Exception( "Key \"" + key + "\" is not found." );
		}

		String value = properties.getProperty( key );

		if (log.isTraceEnabled())
		{
			log.trace( key + " = " + value );
		}

		return value;
	}
}
