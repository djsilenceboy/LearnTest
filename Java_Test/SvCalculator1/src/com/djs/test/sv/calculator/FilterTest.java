/**
 * 
 */

package com.djs.test.sv.calculator;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * @author dj
 */
public class FilterTest implements Filter
{
	public static Logger log = Logger.getLogger( FilterTest.class );

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init( FilterConfig arg0 ) throws ServletException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy()
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}

		try
		{
			HttpServletRequest req = (HttpServletRequest)request;

			if (log.isInfoEnabled())
			{
				log.info( "URI = " + req.getRequestURI() );
			}

			String name = req.getRemoteUser();

			if (log.isInfoEnabled())
			{
				log.info( "User = " + name );
			}

			int v1 = Integer.parseInt( request.getParameter( "value1" ) );
			int v2 = Integer.parseInt( request.getParameter( "value2" ) );
			String op = request.getParameter( "submit" );

			if (op.equalsIgnoreCase( "Plus" ))
			{
				if (log.isInfoEnabled())
				{
					log.info( "? = " + v1 + " + " + v2 );
				}
			}
			else
			{
				if (log.isInfoEnabled())
				{
					log.info( "? = " + v1 + " - " + v2 );
				}
			}
		}
		catch (Exception e)
		{
			if (log.isInfoEnabled())
			{
				log.error( "Process failed. Exception = " + e, e );
			}
		}

		chain.doFilter( request, response );
	}
}
