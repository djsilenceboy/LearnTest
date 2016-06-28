
package dj.test.jdbc.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import oracle.jdbc.pool.OracleDataSource;

public class TestMain
{
	// private static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	// private static final String JDBC_CONNECTION_URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	// private static final String DB_USER_NAME = "root";
	// private static final String DB_PASSWORD = "root";
	private static final String JDBC_CONNECTION_URL = "jdbc:oracle:thin:@10.252.89.30:1521:ndp3";
	private static final String DB_USER_NAME = "IN_NDPDEV41_ADM";
	private static final String DB_PASSWORD = "in_ndpdev41_adm123";
	private OracleDataSource ods = null;
	private Connection conn = null;

	public boolean createConnection(){
		boolean bRet = false;

		try {
			ods = new OracleDataSource();
			ods.setURL(JDBC_CONNECTION_URL);
			ods.setUser(DB_USER_NAME);
			ods.setPassword(DB_PASSWORD);
			conn = ods.getConnection();

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

	public boolean getDataSet(){
		boolean bRet = false;
		Statement stmt = null;
		ResultSet rset = null;
		String szSql = null;
		// Date date = null;
		// Time time = null;
		long lID;
		Timestamp ts = null;

		try {
			szSql = "select * from SLEE_ALARM order by IDENTIFIER";
			System.out.println("SQL = " + szSql);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(szSql);

			System.out.println("IDENTIFIER  TIMESTAMP");

			while (rset.next()) {
				// System.out.println( "ALARM_ID = " + rset.getFloat( "ALARM_ID" ) );

				lID = rset.getLong("IDENTIFIER");
				// System.out.println( "IDENTIFIER = " + rset.getLong( "IDENTIFIER" ) );

				// date = new Date( rset.getLong( "TIMESTAMP" ) );
				// System.out.println( "Date = " + date );

				// time = new Time( rset.getLong( "TIMESTAMP" ) );
				// System.out.println( "Time = " + time );

				ts = new Timestamp(rset.getLong("TIMESTAMP"));
				// System.out.println( "TIMESTAMP = " + ts );

				System.out.println(lID + "      " + ts);
			}

			rset.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		return bRet;
	}

	public boolean getPreferredDataSet(){
		boolean bRet = false;
		Statement stmt = null;
		ResultSet rset = null;
		String szSql = null;
		long lID;
		Calendar cl1 = null;
		Calendar cl2 = null;
		Timestamp ts = null;

		/*
		 * Calendar 1 =
		 * java.util.GregorianCalendar[time=?,areFieldsSet=false,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="GMT+08:00",offset=28800000
		 * ,dstSavings=0,useDaylight=false
		 * ,transitions
		 * =0,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2008,MONTH=4,WEEK_OF_YEAR=35,WEEK_OF_MONTH=5,DAY_OF_MONTH=15,DAY_OF_YEAR=241,
		 * DAY_OF_WEEK=5,DAY_OF_WEEK_IN_MONTH
		 * =4,AM_PM=0,HOUR=9,HOUR_OF_DAY=11,MINUTE=41,SECOND=0,MILLISECOND=0,ZONE_OFFSET=28800000,DST_OFFSET=0] Calendar 2 =
		 * java.util.GregorianCalendar[time=?,areFieldsSet=false,areAllFieldsSet=true,lenient
		 * =true,zone=sun.util.calendar.ZoneInfo[id="GMT+08:00",offset=28800000,dstSavings=0,useDaylight=false,transitions
		 * =0,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2008,MONTH
		 * =4,WEEK_OF_YEAR=35,WEEK_OF_MONTH=5,DAY_OF_MONTH=15,DAY_OF_YEAR=241,DAY_OF_WEEK=5,DAY_OF_WEEK_IN_MONTH=4,AM_PM
		 * =0,HOUR=9,HOUR_OF_DAY=11,MINUTE=45,SECOND=0,MILLISECOND=0,ZONE_OFFSET=28800000,DST_OFFSET=0] SQL = select from SLEE_ALARM where TIMESTAMP between
		 * 1210822860000 and 1210823100000
		 */

		try {
			stmt = conn.createStatement();

			cl1 = Calendar.getInstance();
			cl1.set(2008, 4, 15, 11, 41, 0);
			cl2 = Calendar.getInstance();
			cl2.set(2008, 4, 15, 11, 45, 0);
			System.out.println("Calendar 1 = " + cl1);
			System.out.println("Calendar 2 = " + cl2);

			szSql = "select * from SLEE_ALARM where TIMESTAMP between " + cl1.getTimeInMillis() + " and " + cl2.getTimeInMillis();
			System.out.println("SQL = " + szSql);

			rset = stmt.executeQuery(szSql);

			System.out.println("IDENTIFIER  TIMESTAMP");

			while (rset.next()) {
				lID = rset.getLong("IDENTIFIER");

				ts = new Timestamp(rset.getLong("TIMESTAMP"));
				// System.out.println( "TIMESTAMP = " + ts );

				System.out.println(lID + "      " + ts);
			}

			rset.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		return bRet;
	}

	public boolean getPreferredDataSet2(){
		boolean bRet = false;
		Statement stmt = null;
		ResultSet rset = null;
		String szSql = null;
		long lID;
		long lCount;

		try {
			szSql = "select IDENTIFIER, COUNT(IDENTIFIER) as NUM from SLEE_ALARM group by IDENTIFIER order by IDENTIFIER";
			System.out.println("SQL = " + szSql);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(szSql);

			System.out.println("IDENTIFIER  NUM");

			while (rset.next()) {
				lID = rset.getLong("IDENTIFIER");
				lCount = rset.getLong("NUM");

				System.out.println(lID + "      " + lCount);
			}

			rset.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		return bRet;
	}

	public boolean insertDate(){
		boolean bRet = false;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String szSql = null;
		long lNo;
		Date dt = null;
		Timestamp ts = null;

		// Insert.

		// SQL = insert into TEST_DATE (NO, DT, TS) values (1, TO_DATE('2008-08-28', 'YYYY-MM-DD'), TO_TIMESTAMP('2008-08-28 09:15:03.031', 'YYYY-MM-DD HH:MI:SS.FF'))
		// SQL = insert into TEST_DATE (NO, DT, TS) values (?, ?, ?)

		lNo = 1;
		ts = new Timestamp(System.currentTimeMillis());
		dt = new Date(ts.getTime());

		try {
			szSql =
			        "insert into TEST_DATE (NO, DT, TS) values (" + lNo + ", TO_DATE('" + dt + "', 'YYYY-MM-DD'), TO_TIMESTAMP('" + ts
			                + "', 'YYYY-MM-DD HH:MI:SS.FF'))";
			System.out.println("SQL = " + szSql);

			stmt = conn.createStatement();
			stmt.execute(szSql);

			stmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		lNo++;

		try {
			szSql =
			        "insert into TEST_DATE (NO, DT, TS) values (" + lNo + ", TO_DATE('" + new Date(ts.getTime()) + "', 'YYYY-MM-DD'), TO_TIMESTAMP('"
			                + new Timestamp(dt.getTime()) + "', 'YYYY-MM-DD HH:MI:SS.FF'))";
			System.out.println("SQL = " + szSql);

			stmt = conn.createStatement();
			stmt.execute(szSql);

			stmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		lNo++;

		try {
			szSql = "insert into TEST_DATE (NO, DT, TS) values (?, ?, ?)";
			System.out.println("SQL = " + szSql);

			pstmt = conn.prepareStatement(szSql);
			pstmt.setLong(1, lNo);
			pstmt.setDate(2, dt);
			pstmt.setTimestamp(3, ts);
			pstmt.execute();

			pstmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		lNo++;

		try {
			szSql = "insert into TEST_DATE (NO, DT, TS) values (?, ?, ?)";
			System.out.println("SQL = " + szSql);

			pstmt = conn.prepareStatement(szSql);
			pstmt.setLong(1, lNo);
			pstmt.setTimestamp(2, ts);
			pstmt.setDate(3, dt);
			pstmt.execute();

			pstmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		return bRet;
	}

	public boolean getDate(){
		boolean bRet = false;
		Statement stmt = null;
		ResultSet rset = null;
		String szSql = null;
		long lNo;
		Date dt = null;
		Timestamp ts = null;

		/*
		 * ------------------------------ 1 | 2008-07-02 -> 2008-07-02 00:00:00.0 1 | 2008-07-02 10:26:34.218 -> 2008-07-02 ------------------------------ 2 |
		 * 2008-07-02 -> 2008-07-02 00:00:00.0 2 |
		 * 2008-07-02 10:26:34.218 -> 2008-07-02 ------------------------------ 3 | 2008-07-02 -> 2008-07-02 00:00:00.0 3 | 2008-07-02 10:26:34.218 ->
		 * 2008-07-02 ------------------------------ 4 |
		 * 2008-07-02 -> 2008-07-02 10:26:34.0 4 | 2008-07-02 00:00:00.0 -> 2008-07-02 ------------------------------ Notes: Write DATE with DATE, only date, no
		 * time. Write TIMESTAMP with TIMESTAMP,
		 * have date and time, and time includes millisecond. Write DATE with TIMESTAMP, have date and time, but time dose not include millisecond. Write
		 * TIMESTAMP with DATE, only date, no time.
		 */

		try {
			szSql = "select * from TEST_DATE";
			System.out.println("SQL = " + szSql);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(szSql);

			while (rset.next()) {
				System.out.println("------------------------------");

				lNo = rset.getLong("NO");
				dt = rset.getDate("DT");
				ts = rset.getTimestamp("DT");

				System.out.println(lNo + "  |  " + dt + "  ->  " + ts);

				ts = rset.getTimestamp("TS");
				dt = rset.getDate("TS");

				System.out.println(lNo + "  |  " + ts + "  ->  " + dt);
			}

			rset.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		return bRet;
	}

	public boolean getDate2(){
		boolean bRet = false;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rset = null;
		String szSql = null;
		Date dt = null;
		Timestamp ts = null;
		long lTs = System.currentTimeMillis();

		System.out.println("TS = " + lTs);

		try {
			szSql = "update NDP_CP_EXCEED_SLA set LAST_MONITORTING_TIME = ? where SERVICE_PROVIDER_ID = 'Sms'";
			System.out.println("SQL = " + szSql);

			pstmt = conn.prepareStatement(szSql);
			pstmt.setTimestamp(1, new Timestamp(lTs));
			pstmt.execute();

			pstmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		try {
			szSql = "select * from NDP_CP_EXCEED_SLA";
			System.out.println("SQL = " + szSql);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(szSql);

			while (rset.next()) {
				System.out.println("------------------------------");

				dt = rset.getDate("LAST_MONITORTING_TIME");
				ts = rset.getTimestamp("LAST_MONITORTING_TIME");
				lTs = ts.getTime();

				System.out.println(dt + "  ->  " + ts + " (" + lTs + ")");
			}

			rset.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		return bRet;
	}

	public boolean updateBlob(){
		boolean bRet = false;
		PreparedStatement pstmt = null;
		String szSql = null;
		String szRemarks = null;

		// Update.

		try {
			// szSql = "update NDP_STATISTICS set (REQUEST_END_TIMESTAMP = ?), (REQUEST_STATUS = ?), (REMARKS = ?) where (STAT_ID = ?)";
			szSql = "update NDP_STATISTICS set REQUEST_END_TIMESTAMP = ?, REQUEST_STATUS = ?, REMARKS = ? where (STAT_ID = ?)";
			System.out.println("SQL = " + szSql);
			szRemarks = "Test";

			pstmt = conn.prepareStatement(szSql);
			pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(2, "F");
			pstmt.setBytes(3, szRemarks.getBytes());
			pstmt.setLong(4, 3);
			pstmt.execute();

			pstmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		return bRet;
	}

	public boolean testComplexSql1(){
		boolean bRet = false;
		Statement stmt = null;
		ResultSet rset = null;
		String szaySql[] = null;
		StringBuffer szaySqlPart[] = null;
		String szSql = null;
		int iSet = 2;
		long layTs[] = null;
		long lTsLength = 0;
		long lAlertThreshold = 1;
		String szID = null;
		long layCount[] = null;
		StringBuffer szColumn = null;
		StringBuffer szOutput = null;
		int i;

		try {
			layTs = new long[iSet + 1];

			// layTs[0] = 1206600808213L;
			// layTs[0] = 1208849677631L;
			layTs[0] = 1215755834485L - 60 * 24 * 60 * 60 * 1000L;
			// layTs[layTs.length - 1] = 1215759336203L;
			layTs[layTs.length - 1] = 1215928634485L;
			lTsLength = (layTs[layTs.length - 1] - layTs[0]) / iSet;

			System.out.println("lTsLength = " + lTsLength);

			for (i = 1; i < layTs.length - 1; i++) {
				layTs[i] = layTs[i - 1] + lTsLength;
			}

			for (i = 0; i < layTs.length; i++) {
				System.out.println("Ts[" + i + "] = " + layTs[i]);
			}

			// Make SQL sub-queries.
			// sub-queries are for alarm table. 

			szaySql = new String[iSet];
			for (i = 0; i < szaySql.length; i++) {
				szaySql[i] =
				             "select SOURCE, COUNT(SOURCE) as NUM from SLEE_ALARM where SOURCE is not null and TIMESTAMP between " + (layTs[i] + 1) + " and "
				                     + layTs[i + 1] + " group by SOURCE order by SOURCE";

				System.out.println("SQL[" + i + "] = " + szaySql[i]);

				try {
					stmt = conn.createStatement();
					rset = stmt.executeQuery(szaySql[i]);

					while (rset.next()) {
						System.out.println(rset.getString("SOURCE") + " -> " + rset.getString("NUM"));
					}

					rset.close();
					stmt.close();
				} catch (Exception e) {

				}
			}

			// Make SQL parts. There are 5 parts.

			szaySqlPart = new StringBuffer[5];
			for (i = 0; i < szaySqlPart.length; i++) {
				szaySqlPart[i] = new StringBuffer();
			}

			for (i = 0; i < szaySql.length; i++) {
				// Generate temporary table name with format "Tx", where "x" is a number.
				String szTableName = "T" + i;

				// Part 0: part for "select" clause.
				// Only for table T0, get its SOURCE field. 
				// For all tables, get their NUM fields as format "NUMx", where "x" is a number.
				// So, part0 should be "T0.SOURCE, T0.NUM as NUM0, T1.NUM as NUM1, T2.NUM as NUM2...". 
				if (i == 0) {
					szaySqlPart[0].append(szTableName + ".SOURCE, ");
				}
				szaySqlPart[0].append(szTableName + ".NUM as NUM" + i);
				if (i < szaySql.length - 1) {
					szaySqlPart[0].append(", ");
				}

				// Part 1: part for "from" clause.
				// Combine all sub tables generated by sub-queries together with format "(SubQuery_x) Tx" for each one, where "x" is a number, "Tx" is virtual table name.
				// So, part1 should be "(SubQuery0) T0, (SubQuery1) T1, (SubQuery2) T2...". 
				szaySqlPart[1].append("(" + szaySql[i] + ") " + szTableName);
				if (i < szaySql.length - 1) {
					szaySqlPart[1].append(", ");
				}

				// Part 2: one of condition of "where" clause.
				// To make sure a CP's name to appear in all sub tables.
				// For each sub table except T0, the format is "(T0.SOURCE = Tx.SOURCE)", where "x" is a number and > 0.
				// So, part2 should be "(T0.SOURCE = T1.SOURCE) and (T0.SOURCE = T2.SOURCE) and (T0.SOURCE = T3.SOURCE)...".
				if (i > 0) {
					szaySqlPart[2].append("(T0.SOURCE = " + szTableName + ".SOURCE)");

					if (i < szaySql.length - 1) {
						szaySqlPart[2].append(" and ");
					}
				}

				szaySqlPart[3].append("(" + szTableName + ".NUM >= " + lAlertThreshold + ")");
				if (i < szaySql.length - 1) {
					szaySqlPart[3].append(" and ");
				}
			}

			// Part 3: one of condition of "where" clause.
			// To make sure the numbers of alerts of each sample set (sub table) are matching the threshold.
			// For each set (sub table), the format is "(Tx.NUM >= y)", where "x" is a number and "y" is threshold.
			// So, part3 should be "(T0.NUM >= y) and (T1.NUM >= y) and (T2.NUM >= y)...".
			for (i = 0; i < szaySqlPart.length; i++) {
				System.out.println("SQL part[" + i + "] = " + szaySqlPart[i]);
			}

			// Part 4: one of condition of "where" clause.
			// To make sure the available CPs after part 2 and 3 are not existing in monitoring table.
			// The "SOURCE" field of alarm table equals to the "SERVICE_PROVIDER_ID" field of monitoring table.
			// So, part4 should be "T0.SOURCE not in (select SERVICE_PROVIDER_ID from TABLE_NAME_MONITORING )".
			// szaySqlPart[4].append( "T0.SOURCE not in (select SERVICE_PROVIDER_ID from NDP_CP_EXCEED_SLA)" );

			// Make full SQL statement.
			// It may looks like:
			// "select T0.SOURCE, T0.NUM as NUM0, T1.NUM as NUM1 from (select SOURCE, COUNT(SOURCE) as NUM from SLEE_ALARM where SOURCE is not null and TIMESTAMP between 1204700975281 and 1209884975280 group by SOURCE order by SOURCE) T0, (select SOURCE, COUNT(SOURCE) as NUM from SLEE_ALARM where SOURCE is not null and TIMESTAMP between 1209884975281 and 1215068975280 group by SOURCE order by SOURCE) T1 where (T0.SOURCE = T1.SOURCE) and (T0.NUM >= 1) and (T1.NUM >= 1) and T0.SOURCE not in (select SERVICE_PROVIDER_ID from NDP_CP_EXCEED_SLA)"

			// szSql = "select " + szaySqlPart[0] + " from " + szaySqlPart[1] + " where " + szaySqlPart[2] + " and " + szaySqlPart[3] + " and " + szaySqlPart[4];
			szSql = "select " + szaySqlPart[0] + " from " + szaySqlPart[1] + " where " + szaySqlPart[2] + " and " + szaySqlPart[3];
			System.out.println("Full SQL = " + szSql);

			stmt = conn.createStatement();
			rset = stmt.executeQuery(szSql);

			szColumn = new StringBuffer();
			szColumn.append("SOURCE");
			for (i = 0; i < szaySql.length; i++) {
				szColumn.append(" -> NUM" + i);
			}

			System.out.println(szColumn);

			Set<String> szstCp = new LinkedHashSet<String>();

			while (rset.next()) {
				layCount = new long[szaySql.length];
				szOutput = new StringBuffer();

				szID = rset.getString("SOURCE");
				szOutput.append(szID);

				for (i = 0; i < szaySql.length; i++) {

					layCount[i] = rset.getLong("NUM" + i);
					szOutput.append(" -> " + layCount[i]);
				}

				System.out.println(szOutput);

				//

				szstCp.add(szID);
			}

			String[] szayCp = szstCp.toArray(new String[0]);
			// System.out.println( szayCp[0] );
			// System.out.println( szayCp[1] );
			System.out.println(szstCp);

			rset.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		return bRet;
	}

	public static void main(String[] args){
		boolean bRet = false;
		TestMain test = new TestMain();

		bRet = test.createConnection();

		/*
		 * bRet = test.getDataSet(); System.out.println( "----------------------------------------" ); bRet = test.getPreferredDataSet(); System.out.println(
		 * "----------------------------------------"
		 * ); bRet = test.getPreferredDataSet2(); System.out.println( "----------------------------------------" ); System.out.println(
		 * "----------------------------------------" ); bRet =
		 * test.insertDate(); System.out.println( "----------------------------------------" ); bRet = test.getDate(); System.out.println(
		 * "----------------------------------------" ); bRet =
		 * test.getDate2(); System.out.println( "----------------------------------------" ); bRet = test.updateBlob(); System.out.println(
		 * "----------------------------------------" ); bRet =
		 * test.testComplexSql1(); System.out.println( "----------------------------------------" );
		 */

		bRet = test.closeConnection();
	}
}
