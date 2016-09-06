
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

import org.apache.log4j.Logger;

public class AddRating extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger( AddRating.class );

	public AddRating()
	{
		super();

		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}
	}

	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		if (log.isInfoEnabled())
		{
			log.info( "Enter..." );
		}

		try
		{
			String analyst = request.getParameter( "analysts" );
			String ticker = request.getParameter( "stocks" );
			String rating = request.getParameter( "ratings" );

			Vector<String> v = new Vector<String>();
			v.add( analyst );
			v.add( ticker );
			v.add( rating );

			ArrayList<Vector<String>> ratings = (ArrayList<Vector<String>>)request.getAttribute( "data" );
			ratings.add( v );

			if (log.isInfoEnabled())
			{
				log.info( "ratings = " + ratings );
			}

			ArrayList<String> unratedStocks = (ArrayList<String>)request.getAttribute( "unrated" );
			unratedStocks.remove( unratedStocks.indexOf( ticker ) );

			if (log.isInfoEnabled())
			{
				log.info( "unratedStocks = " + unratedStocks );
			}

			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getNamedDispatcher( "RatingsForm" );

			if (log.isInfoEnabled())
			{
				log.info( "dispatcher = " + dispatcher );
			}

			dispatcher.forward( request, response );
		}
		catch (Exception e)
		{
			log( "AddRating.doGet() failed. Exception = " + e );
		}
	}
}
