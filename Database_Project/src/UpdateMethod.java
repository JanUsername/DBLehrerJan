import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateMethod {
	private PreparedStatement pst = null;
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	String url = null;
	String user = null;
	String password = null;

	public UpdateMethod() {
		url = "jdbc:postgresql://alcor.inf.unibz.it:5432/dbs_g07";
		user = "dbs_g07";
		password = "Gioshjuc";
	}

	public void update(String table, String nameColumn, String valueColumn,
			String nameID, int valueID) {

		try {
			con = DriverManager.getConnection(url, user, password);

			String stm = null;

			stm = "UPDATE " + table + " SET " + nameColumn + " = '" + valueColumn + "' WHERE "
					+ nameID + " = " + valueID;
			pst = con.prepareStatement(stm);

			// execute statement me
			pst.executeUpdate();
			System.out.println("Successful");

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(UpdateMethod.class.getName());
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
				Logger lgr = Logger.getLogger(UpdateMethod.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}
}
