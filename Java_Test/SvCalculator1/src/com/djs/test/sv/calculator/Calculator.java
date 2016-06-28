
package com.djs.test.sv.calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class Calculator
 */
public class Calculator extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public static Logger log = Logger.getLogger( Calculator.class );

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Calculator()
	{
		super();

		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}

	/**
	 * @see Servlet#init()
	 */
	@Override
	public void init() throws ServletException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy()
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}

		try
		{
			int v1 = Integer.parseInt( request.getParameter( "value1" ) );
			int v2 = Integer.parseInt( request.getParameter( "value2" ) );
			String op = request.getParameter( "submit" );
			int result = -1;

			response.setContentType( "text/html" );
			PrintWriter writer = response.getWriter();
			writer.println( "<html><body>" );
			if (op.equalsIgnoreCase( "Plus" ))
			{
				result = v1 + v2;

				if (log.isInfoEnabled())
				{
					log.info( result + " = " + v1 + " + " + v2 );
				}

				writer.println( result + " = " + v1 + " + " + v2 );
			}
			else
			{
				result = v1 - v2;

				if (log.isInfoEnabled())
				{
					log.info( result + " = " + v1 + " - " + v2 );
				}

				writer.println( result + " = " + v1 + " - " + v2 );
			}
			writer.println( "</body></html>" );
			writer.close();
		}
		catch (Exception e)
		{
			if (log.isInfoEnabled())
			{
				log.error( "Process request failed. Exception = " + e, e );
			}
		}
	}
}
