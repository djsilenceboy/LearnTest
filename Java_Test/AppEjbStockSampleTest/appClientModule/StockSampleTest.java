import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.djs.ejb.stock_sample.StockQueryRemote;

public class StockSampleTest
{
	private StockQueryRemote svc = null;

	// public static final String jndiName = "EjbStockSample/StockQuery/remote-com.djs.ejb.stock_sample.StockQueryRemote";
	public static final String jndiName = "EjbStockSample/StockQuery/remote";

	public StockSampleTest() throws NamingException
	{
		System.out.println( "[StockSampleClient] Enter..." );

		Hashtable env = new Hashtable();

		env.put( Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" );
		// env.put( Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces" );
		env.put( Context.URL_PKG_PREFIXES, "org.jboss.naming.client" );
		// env.put( Context.PROVIDER_URL, "localhost" );
		// env.put( Context.PROVIDER_URL, "jnp://localhost:1099" );
		env.put( Context.PROVIDER_URL, "localhost:9099" );
		// env.put( Context.PROVIDER_URL, "jnp://localhost:9099" );

		Context ctx = new InitialContext( env );

		System.out.println( "[StockSampleClient] loopup()..." );

		svc = (StockQueryRemote)ctx.lookup( jndiName );

		System.out.println( "[StockSampleClient] Leave..." );
	}

	public void test( String tickerSymbol, String name ) throws Exception
	{
		System.out.println( "[test] add = " + tickerSymbol + " / " + name );

		svc.addStockInfo( tickerSymbol, name );

		System.out.println( "[test] get = " + tickerSymbol );

		name = svc.getStockInfo( tickerSymbol );

		System.out.println( "[test] got = " + tickerSymbol + " / " + name );

		svc.deleteStockInfo( tickerSymbol );

		System.out.println( "[test] delete = " + tickerSymbol );
	}

	public static void main( String [] args )
	{
		try
		{
			StockSampleTest theClient = new StockSampleTest();

			theClient.test( "intc", "Intel" );
		}
		catch (Exception e)
		{
			System.out.println( "Exception = " + e );
			e.printStackTrace();
		}
	}
}
