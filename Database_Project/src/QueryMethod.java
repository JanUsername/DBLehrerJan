import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryMethod {

	private PreparedStatement pst = null;
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	String url = null;
	String user = null;
	String password = null;

	public QueryMethod() {
		url = "jdbc:postgresql://alcor.inf.unibz.it:5432/dbs_g07";
		user = "dbs_g07";
		password = "Gioshjuc";

	}

	public String[] query(String table, int ID, String nameID) {
		String[] result = null;
		try {
			String stm = null;
			stm = "select * from " + table + " WHERE " + nameID + "=" + ID
					+ " order by " + nameID;
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(stm);
			rs = pst.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int n = rsmd.getColumnCount();
			result = new String[n];
			while (rs.next()) {
				for (int j = 1; j <= n; j++) {
					result[j - 1] = "" + rs.getString(j);
				}
			}

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(QueryMethod.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(QueryMethod.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}

		}
		return result;
	}

	public int maxID(String table, String nameID) {
		int n = 0;
		try {
			con = DriverManager.getConnection(url, user, password);
			Statement stmt;
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from " + table
					+ " order by " + nameID);
			rs.last();
			n = rs.getInt(1);
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(QueryMethod.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(QueryMethod.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}

		}
		return n;
	}
}
