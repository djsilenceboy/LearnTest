
package com.djs.test.sv.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class StudentTest
 */
public class StudentTest extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger( StudentTest.class );

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

		ArrayList<Vector<Object>> data = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet rset = null;

		try
		{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup( "java:comp/env/jdbc/MySQL" );
			connection = dataSource.getConnection();
			connection.setAutoCommit( true );
			statement = connection.createStatement();
			rset = statement.executeQuery( "SELECT * FROM student" );

			while (rset.next())
			{
				if (data == null)
				{
					data = new ArrayList<Vector<Object>>();
				}

				Vector<Object> temp = new Vector<Object>();

				temp.add( rset.getString( "name" ) );
				temp.add( rset.getString( "sex" ) );
				temp.add( rset.getInt( "age" ) );
				temp.add( rset.getInt( "score" ) );

				if (log.isInfoEnabled())
				{
					log.info( "Student = " + temp );
				}

				data.add( temp );
			}

			if (data != null)
			{
				request.setAttribute( "data", data );
			}

			RequestDispatcher dispatcher = getServletContext().getNamedDispatcher( "ShowStudentList" );

			if (log.isInfoEnabled())
			{
				log.info( "dispatcher = " + dispatcher );
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
		finally
		{
			if (rset != null)
			{
				try
				{
					rset.close();
				}
				catch (Exception e)
				{
				}
			}

			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (Exception e)
				{
				}
			}

			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (Exception e)
				{
				}
			}
		}
	}
}
