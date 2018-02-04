
package com.djs.learn.stocklist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class StockListServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger( StockListServlet.class );

	static ArrayList<String> analysts = new ArrayList<String>();
	static ArrayList<String> unratedStocks = new ArrayList<String>();
	static ArrayList<Vector<String>> ratings = new ArrayList<Vector<String>>();

	public StockListServlet()
	{
		super();

		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}

	@Override
	public void init() throws ServletException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}

		analysts.add( "Fred" );
		analysts.add( "Leonard" );
		analysts.add( "Sarah" );
		analysts.add( "Nancy" );

		// unratedStocks.add( "AIG" );
		unratedStocks.add( "BAC" );
		unratedStocks.add( "CSCO" );
		unratedStocks.add( "FB" );
		unratedStocks.add( "GE" );
		unratedStocks.add( "INTC" );
		unratedStocks.add( "MRK" );
		unratedStocks.add( "MSFT" );
		unratedStocks.add( "ORCL" );

		Vector<String> v = new Vector<String>();
		v.add( "Fred" );
		v.add( "AIG" );
		v.add( "Smashing!" );
		ratings.add( v );

		if (log.isInfoEnabled())
		{
			log.info( "analysts = " + analysts );
			log.info( "unratedStocks = " + unratedStocks );
			log.info( "ratings = " + ratings );
		}
	}

	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		doGet( request, response );
	}

	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}

		try
		{
			ArrayList<String> data = null;

			ServletContext context = getServletContext();
			String name = request.getPathInfo();
			name = name.substring( 1 );

			if (log.isInfoEnabled())
			{
				log.info( "Name = " + name );
			}

			if (name.equals( "AnalystForm" ))
			{
				data = analysts;
				request.setAttribute( "data", data );
			}
			else if (name.equals( "RatingsForm" ))
			{
				request.setAttribute( "data", ratings );
				request.setAttribute( "analysts", analysts );
				request.setAttribute( "unrated", unratedStocks );
			}
			else if (name.equals( "AddRating" ))
			{
				request.setAttribute( "data", ratings );
				request.setAttribute( "analysts", analysts );
				request.setAttribute( "unrated", unratedStocks );
			}
			else if (name.equals( "ProcessAnalyst" ))
			{
				// No need to set anything.
			}
			else
			{
				name = "Error";
			}

			RequestDispatcher dispatcher = context.getNamedDispatcher( name );

			if (log.isInfoEnabled())
			{
				log.info( "dispatcher = " + dispatcher );
			}

			if (dispatcher == null)
			{
				dispatcher = context.getNamedDispatcher( "Error" );
			}

			dispatcher.forward( request, response );
		}
		catch (Exception e)
		{
			if (log.isEnabledFor( Level.ERROR ))
			{
				log.error( "Exception = " + e, e );
			}
		}
	}
}
