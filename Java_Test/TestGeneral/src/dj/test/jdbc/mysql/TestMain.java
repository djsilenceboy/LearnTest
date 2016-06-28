
package dj.test.jdbc.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class TestMain
{
	private static final String JDBC_CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/test";
	private static final String DB_USER_NAME = "tester";
	private static final String DB_PASSWORD = "tester";
	private Connection conn = null;

	public boolean createConnection(){
		boolean bRet = false;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// jdbc:mysql://127.0.0.1:3306/test?user=root&password=root
			// String szUrl = JDBC_CONNECTION_URL + "?user=" + DB_USER_NAME + "&password=" + DB_PASSWORD;
			// System.out.println( "URL = " + szUrl );
			// conn = DriverManager.getConnection( szUrl );

			conn = DriverManager.getConnection(JDBC_CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);

			bRet = true;
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		return bRet;
	}

	public boolean closeConnection(){
		boolean bRet = false;

		try {
			conn.close();

			bRet = true;
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		return bRet;
	}

	public void insertTest1(String szTableName, int iTasksNumber, long lStartId){
		String szSql = null;
		PreparedStatement pstmt = null;
		int iayUpdateCount[] = null;
		int i;

		try {
			szSql = "INSERT INTO " + szTableName + " (content_id,process_status) VALUES (?,0)";
			System.out.println("SQL = " + szSql);

			pstmt = conn.prepareStatement(szSql);

			for (i = 0; i < iTasksNumber; i++) {
				pstmt.setLong(1, lStartId + i);
				pstmt.addBatch();
			}

			iayUpdateCount = pstmt.executeBatch();

			for (i = 0; i < iayUpdateCount.length; i++) {
				System.out.println("Update count [" + i + "] = " + iayUpdateCount[i]);
			}

			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			System.err.println("Exception = " + e);
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {

			}
		}
	}

	public void insertTest2(String szTableName, int iTasksNumber, long lStartId){
		String szSql = null;
		Statement stmt = null;
		int iayUpdateCount[] = null;
		int i;

		try {
			stmt = conn.createStatement();

			szSql = "DELETE FROM " + szTableName;
			System.out.println("SQL = " + szSql);
			stmt.addBatch(szSql);

			for (i = 0; i < iTasksNumber; i++) {
				szSql = "INSERT INTO " + szTableName + " (content_id,process_status) VALUES (" + (lStartId + i) + ",0)";
				System.out.println("SQL = " + szSql);
				stmt.addBatch(szSql);
			}

			iayUpdateCount = stmt.executeBatch();

			for (i = 0; i < iayUpdateCount.length; i++) {
				System.out.println("Update count [" + i + "] = " + iayUpdateCount[i]);
			}

			stmt.close();
			stmt = null;
		} catch (Exception e) {
			System.err.println("Exception = " + e);
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {

			}
		}
	}

	public void getTest1(String szTableName){
		String szSql = null;
		Statement stmt = null;
		ResultSet rset = null;
		ResultSetMetaData rsmd = null;

		try {
			szSql = "select * from " + szTableName;
			System.out.println("SQL = " + szSql);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(szSql);

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
			rset = null;

			stmt.close();
			stmt = null;
		} catch (Exception e) {
			System.err.println("Exception = " + e);
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}

				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {

			}
		}
	}

	public void getTest2(String szOwner, int iTasksNumber){
		boolean bRet = false;
		CallableStatement cstmt = null;
		String szSql = null;
		ResultSet rset = null;
		ResultSetMetaData rsmd = null;

		try {
			// Do not use format like { CALL ndp_mms_bc_getNewTasks(?,?) }!
			// For PreparedStatement, the '?' place holder only works when it is on the right-hand-side of an operator (i.e. '=', '<', '>', 'like' etc.). 

			szSql = "{ CALL ndp_mms_bc_getNewTasks(\"" + szOwner + "\"," + iTasksNumber + ") }";
			System.out.println("SQL = " + szSql);

			cstmt = conn.prepareCall(szSql);
			bRet = cstmt.execute(szSql);

			do {
				// If ResultSet.
				if (bRet) {
					rset = cstmt.getResultSet();
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
					rset = null;
				}
				// If UpdateCount.
				else {
					System.out.println("Update count = " + cstmt.getUpdateCount());
				}

				System.out.println("------------------------------");

				bRet = cstmt.getMoreResults();
			} while (!bRet && (cstmt.getUpdateCount() == -1));

			cstmt.close();
			cstmt = null;
		} catch (Exception e) {
			System.err.println("Exception = " + e);
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}

				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception e) {

			}
		}
	}

	public void getTest3(String szOwner, int iTasksNumber){
		boolean bRet = false;
		CallableStatement cstmt = null;
		String szSql = null;
		ResultSet rset = null;
		ResultSetMetaData rsmd = null;

		try {
			// Do not use format like { CALL ndp_mms_bc_getNewTasks(?,?) }!
			// For PreparedStatement, the '?' place holder only works when it is on the right-hand-side of an operator (i.e. '=', '<', '>', 'like' etc.). 

			szSql = "{ CALL ndp_mms_bc_getNewTasks(\"" + szOwner + "\"," + iTasksNumber + ") }";
			System.out.println("SQL = " + szSql);

			cstmt = conn.prepareCall(szSql);
			bRet = cstmt.execute(szSql);

			do {
				System.out.println("Update count = " + cstmt.getUpdateCount());

				// If ResultSet.
				if (bRet) {
					rset = cstmt.getResultSet();

					// Only get first one.
					break;
				}

				bRet = cstmt.getMoreResults();
			} while (!bRet && (cstmt.getUpdateCount() == -1));

			// If found.
			if (rset != null) {
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
				rset = null;
			}

			cstmt.close();
			cstmt = null;
		} catch (Exception e) {
			System.err.println("Exception = " + e);
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}

				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception e) {

			}
		}
	}

	public void getTest4(String szTableName, String szOwner, int iTasksNumber){
		String szSql = null;
		Statement stmt = null;
		int iayUpdateCount[] = null;
		int i;

		try {
			stmt = conn.createStatement();

			// SELECT cannot be in batch with UPDATE.

			szSql =
			        "UPDATE " + szTableName + " SET process_owner = '" + szOwner + "', process_status = 1 WHERE (process_status = 0) ORDER BY seq_id LIMIT "
			                + iTasksNumber;
			System.out.println("SQL = " + szSql);
			stmt.addBatch(szSql);

			szSql = "SELECT * FROM " + szTableName + " WHERE (process_owner = '" + szOwner + "') AND (process_status = 1) ORDER BY seq_id";
			System.out.println("SQL = " + szSql);
			stmt.addBatch(szSql);

			szSql = "UPDATE " + szTableName + " SET process_status = 2 WHERE (process_owner = '" + szOwner + "') AND (process_status = 1)";
			System.out.println("SQL = " + szSql);
			stmt.addBatch(szSql);

			iayUpdateCount = stmt.executeBatch();

			for (i = 0; i < iayUpdateCount.length; i++) {
				System.out.println("Update count [" + i + "] = " + iayUpdateCount[i]);
			}

			stmt.close();
			stmt = null;
		} catch (Exception e) {
			System.err.println("Exception = " + e);
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {

			}
		}
	}

	public void deleteTest1(String szTableName, String szOwner, int iTasksNumber, long lStartId){
		String szSql = null;
		Statement stmt = null;
		int iayUpdateCount[] = null;
		int i;

		try {
			stmt = conn.createStatement();

			for (i = 0; i < iTasksNumber; i++) {
				szSql =
				        "UPDATE " + szTableName + " SET process_status = 3 WHERE (process_owner = '" + szOwner
				                + "') AND (process_status = 2) AND (content_id = " + (lStartId + i) + ")";
				System.out.println("SQL = " + szSql);
				stmt.addBatch(szSql);
			}

			szSql = "DELETE FROM " + szTableName + " WHERE (process_owner = '" + szOwner + "') AND (process_status = 3)";
			System.out.println("SQL = " + szSql);
			stmt.addBatch(szSql);

			iayUpdateCount = stmt.executeBatch();

			for (i = 0; i < iayUpdateCount.length; i++) {
				System.out.println("Update count [" + i + "] = " + iayUpdateCount[i]);
			}

			stmt.close();
			stmt = null;
		} catch (Exception e) {
			System.err.println("Exception = " + e);
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {

			}
		}
	}

	public static void main(String[] args){
		boolean bRet = false;
		TestMain test = new TestMain();

		bRet = test.createConnection();

		System.out.println("----------------------------------------");
		// test.insertTest1( "ndp_mms_bc_q", 5, 220 );
		System.out.println("----------------------------------------");
		// Normal.
		test.insertTest2("ndp_mms_bc_q", 10, 100);
		// System.out.println( "----------------------------------------" );
		// test.getTest1( "ndp_mms_bc_q" );
		// System.out.println( "----------------------------------------" );
		// test.getTest2( "app1", 2 );
		System.out.println("----------------------------------------");
		// Normal.
		test.getTest3("app1", 4);
		// System.out.println( "----------------------------------------" );
		// test.getTest4( "ndp_mms_bc_q", "app2", 2 );
		System.out.println("----------------------------------------");
		// Normal.
		test.deleteTest1("ndp_mms_bc_q", "app1", 2, 102);
		System.out.println("----------------------------------------");

		bRet = test.closeConnection();
	}
}
