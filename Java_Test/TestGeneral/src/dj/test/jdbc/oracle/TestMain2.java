
package dj.test.jdbc.oracle;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class TestMain2
{
	/*
	 * private static final String JDBC_CONNECTION_URL = "jdbc:oracle:thin:@10.252.89.30:1521:ndp3";
	 * private static final String DB_USER_NAME = "IN_NDPDEV41_ADM";
	 * private static final String DB_PASSWORD = "in_ndpdev41_adm123";
	 */
	private static final String JDBC_CONNECTION_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER_NAME = "ocsg41user";
	private static final String DB_PASSWORD = "ocsg41user";
	private OracleDataSource ods = null;
	private Connection conn = null;

	public void createConnection() throws Exception{
		ods = new OracleDataSource();
		ods.setURL(JDBC_CONNECTION_URL);
		ods.setUser(DB_USER_NAME);
		ods.setPassword(DB_PASSWORD);
		conn = ods.getConnection();

		System.out.println("ImplicitCachingEnabled = " + ods.getImplicitCachingEnabled());
		System.out.println("ExplicitCachingEnabled = " + ods.getExplicitCachingEnabled());
	}

	public void closeConnection() throws Exception{
		conn.close();
	}

	public void testTableStructure(String tableName) throws Exception{
		String sql = null;
		Statement stmt = null;
		ResultSet rset = null;
		ResultSetMetaData rsmd = null;

		sql = "select * from " + tableName;
		System.out.println("SQL = " + sql);

		stmt = conn.createStatement();
		rset = stmt.executeQuery(sql);

		rsmd = rset.getMetaData();
		int numCols = rsmd.getColumnCount();

		// get column header info
		for (int i = 0; i < numCols; i++) {
			System.out.print(rsmd.getColumnLabel(i + 1));
			if (i < (numCols - 1)) {
				System.out.print(", ");
			}
		}
		System.out.println();

		while (rset.next()) {
			for (int i = 0; i < numCols; i++) {
				System.out.print(rset.getString(i + 1));
				if (i < (numCols - 1)) {
					System.out.print(", ");
				}
			}

			System.out.println();
		}

		rset.close();
		stmt.close();
	}

	public void testArray(String tableName) throws Exception{
		String names[] = {"Tom", "Cat", "Jerry", "Mouse"};

		for (int i = 0; i < names.length; i++) {
			System.out.println("Name [" + i + "] = " + names[i]);
		}

		// When calling from JDBC, Oracle SQL types must be global ones, not in package.

		// Wrong: ArrayDescriptor.createDescriptor( "TABLE_TYPES.TEST_STRING_ARRAY", conn );
		ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor("TEST_STRING_ARRAY", conn);
		ARRAY inputArray = new ARRAY(descriptor, conn, names);

		OracleCallableStatement cstmt = (OracleCallableStatement)conn.prepareCall("CALL sp_test_array( ?, ? )");
		cstmt.setARRAY(1, inputArray);
		// Wrong: cstmt.registerOutParameter( 2, OracleTypes.ARRAY, "TABLE_TYPES.TEST_INT_ARRAY" );
		cstmt.registerOutParameter(2, OracleTypes.ARRAY, "TEST_INT_ARRAY");
		cstmt.execute();

		ARRAY outputArray = cstmt.getARRAY(2);
		System.out.println("Array type = " + outputArray.getSQLTypeName());
		System.out.println("Array base type = " + outputArray.getBaseType());
		System.out.println("Array length = " + outputArray.length());

		// Object is BigDecimal, not int.
		BigDecimal[] ids = (BigDecimal[])outputArray.getArray();

		for (int i = 0; i < ids.length; i++) {
			System.out.println("ID [" + i + "] = " + ids[i]);
		}

		cstmt.close();
	}

	public static void main(String[] args){
		TestMain2 test = new TestMain2();

		try {
			test.createConnection();

			System.out.println("----------------------------------------");
			test.testTableStructure("TEST_ARRAY");

			System.out.println("----------------------------------------");
			test.testArray("TEST_ARRAY");

			System.out.println("----------------------------------------");
			test.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				test.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
