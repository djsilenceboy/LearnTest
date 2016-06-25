import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class JndiTreeTest
{
	private InitialContext ctx = null;

	public JndiTreeTest() throws NamingException
	{
		System.out.println( "[JndiTreeClient] Enter..." );

		Hashtable env = new Hashtable();

		env.put( Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" );
		// env.put( Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces" );
		env.put( Context.URL_PKG_PREFIXES, "org.jboss.naming.client" );
		// env.put( Context.PROVIDER_URL, "localhost" );
		// env.put( Context.PROVIDER_URL, "jnp://localhost:1099" );
		env.put( Context.PROVIDER_URL, "localhost:9099" );
		// env.put( Context.PROVIDER_URL, "jnp://localhost:9099" );

		ctx = new InitialContext( env );

		System.out.println( "[JndiTreeTest] Leave..." );
	}

	private void listContext( Context ctx, String indent ) throws NamingException
	{
		System.out.println( "[listContext] Enter... " );

		// NamingEnumeration list = ctx.listBindings( "" );
		// NamingEnumeration list = ctx.listBindings( "queue" );
		NamingEnumeration list = ctx.listBindings( "EjbStockSample/StockQuery" );

		while (list.hasMore())
		{
			Binding item = (Binding)list.next();
			String className = item.getClassName();
			String name = item.getName();

			System.out.println( indent + className + " " + name );

			Object obj = item.getObject();

			if (obj instanceof Context)
			{
				listContext( (Context)obj, indent + "  " );
			}
		}

		/*
		Proxy for: com.djs.ejb.stock_sample.StockQueryRemote remote-com.djs.ejb.stock_sample.StockQueryRemote
		Proxy for: com.djs.ejb.stock_sample.StockQueryRemote remote
		*/

		System.out.println( "[listContext] Leave... " );
	}

	public void listContext( String indent ) throws NamingException
	{
		listContext( ctx, indent );
	}

	public static void main( String [] args )
	{
		try
		{
			JndiTreeTest theClient = new JndiTreeTest();

			theClient.listContext( "" );
		}
		catch (Exception e)
		{
			System.out.println( "Exception = " + e );
			e.printStackTrace();
		}
	}
}