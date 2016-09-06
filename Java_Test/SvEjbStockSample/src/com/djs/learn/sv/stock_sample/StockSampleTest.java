
package com.djs.learn.sv.stock_sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.djs.ejb.stock_sample.StockQueryRemote;

/**
 * Servlet implementation class StockSampleTest
 */
public class StockSampleTest extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private StockQueryRemote svc = null;

	// public static final String jndiName = "EjbStockSample/StockQuery/remote-com.djs.ejb.stock_sample.StockQueryRemote";
	public final String jndiName = "EjbStockSample/StockQuery/remote";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StockSampleTest()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	private void setup( PrintWriter out ) throws NamingException
	{
		Hashtable env = new Hashtable();

		env.put( Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" );
		// env.put( Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces" );
		env.put( Context.URL_PKG_PREFIXES, "org.jboss.naming.client" );
		// env.put( Context.PROVIDER_URL, "localhost" );
		// env.put( Context.PROVIDER_URL, "jnp://localhost:1099" );
		env.put( Context.PROVIDER_URL, "localhost:9099" );
		// env.put( Context.PROVIDER_URL, "jnp://localhost:9099" );

		Context ctx = new InitialContext( env );

		svc = (StockQueryRemote)ctx.lookup( jndiName );
	}

	private void ejbTest( PrintWriter out, String tickerSymbol, String name ) throws Exception
	{
		out.println( "add = " + tickerSymbol + " / " + name + "<br>" );

		svc.addStockInfo( tickerSymbol, name );

		out.println( "get = " + tickerSymbol + "<br>" );

		name = svc.getStockInfo( tickerSymbol );

		out.println( "got = " + tickerSymbol + " / " + name + "<br>" );

		svc.deleteStockInfo( tickerSymbol );

		out.println( "delete = " + tickerSymbol + "<br>" );
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init( ServletConfig config ) throws ServletException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		response.setContentType( "text/html" );

		PrintWriter out = response.getWriter();
		out.println( "<html>" );
		out.println( "<head>" );
		out.println( "<title>Result</title>" );
		out.println( "</head>" );
		out.println( "<body bgcolor = \"white\">" );
		out.println( "<a href = \"index.html\">" );
		out.println( "Return</a>" );
		out.println( "<h1>Stock test</h1>" );
		out.println( "<p>" );

		try
		{
			String symbol = request.getParameter( "symbol" );
			String name = request.getParameter( "name" );

			out.println( "Symbol = " + symbol + "<br>" );
			out.println( "Name = " + name + "<br>" );

			setup( out );
			ejbTest( out, symbol, name );
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e.printStackTrace( out );
		}

		out.println( "<p>" );
		out.println( "</body>" );
		out.println( "</html>" );
	}
}
