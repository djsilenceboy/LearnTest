import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcJndiClient
{
	private DataSource dataSource = null;
	// public static final String szDsName = "java:comp/env/jdbc/OracleXeSource";
	// public static final String szDsName = "jdbc/OracleXeSource";
	// public static final String szDsName = "jdbc/OracleSource21";
	// public static final String szDsName = "java:/MySqlDS";
	public static final String szDsName = "MySqlDS";

	public JdbcJndiClient() throws NamingException
	{
		System.out.println( "[JdbcJndiClient] Enter..." );

		Hashtable env = new Hashtable();

		env.put( Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory" );
		env.put( Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces" );
		env.put( Context.PROVIDER_URL, "localhost" );

		InitialContext ic = new InitialContext( env );

		System.out.println( "[JdbcJndiClient] loopup()..." );

		dataSource = (DataSource)ic.lookup( szDsName );

		System.out.println( "[JdbcJndiClient] Leave..." );
	}

	public void printTable( String table ) throws SQLException
	{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try
		{
			con = dataSource.getConnection();
			stmt = con.prepareStatement( "SELECT * FROM " + table );

			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numCols = rsmd.getColumnCount();

			// get column header info
			for (int i = 0; i < numCols; i++)
			{
				System.out.print( rsmd.getColumnLabel( i + 1 ) );

				if (i < (numCols - 1))
				{
					System.out.print( ", " );
				}
			}
			System.out.println();

			while (rs.next())
			{
				for (int i = 0; i < numCols; i++)
				{
					System.out.print( rs.getString( i + 1 ) );

					if (i < (numCols - 1))
					{
						System.out.print( ", " );
					}
				}
				System.out.println();
			}
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				stmt.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				con.close();
			}
			catch (Exception e)
			{
			}
		}
	}

	public static void main( String [] args )
	{
		try
		{
			JdbcJndiClient theClient = new JdbcJndiClient();

			if (args == null)
			{
				theClient.printTable( "MYTEST" );
			}
			else
			{
				theClient.printTable( args[0] );
			}
		}
		catch (Exception e)
		{
			System.out.println( "Exception = " + e );
			e.printStackTrace();
		}
	}
}
