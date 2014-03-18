import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DeleteMethod {
	private PreparedStatement pst = null;
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	String url = null;
	String user = null;
	String password = null;

	public DeleteMethod() {
		url = "jdbc:postgresql://alcor.inf.unibz.it:5432/dbs_g07";
		user = "dbs_g07";
		password = "Gioshjuc";
	}

	public void delete(String table, String nameColumn, int valueColumn) {

		try {
			con = DriverManager.getConnection(url, user, password);

			String stm = null;

			stm = "DELETE FROM " + table + " WHERE " + nameColumn + " = " + valueColumn;
			pst = con.prepareStatement(stm);

			// execute statement me
			pst.executeUpdate();
			System.out.println("Delete Successful");

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DeleteMethod.class.getName());
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
				Logger lgr = Logger.getLogger(DeleteMethod.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}
}
