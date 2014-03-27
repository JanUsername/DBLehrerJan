import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.ranges.RangeException;

public class InsertMethod {
	private PreparedStatement pst = null;
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	String url = null;
	String user = null;
	String password = null;

	public InsertMethod() {
		url = "jdbc:postgresql://alcor.inf.unibz.it:5432/dbs_g07";
		user = "dbs_g07";
		password = "Gioshjuc";
	}

	public void insert(String table, String name[], String value[]) {
		if(name.length != value.length){
			String message = "not the same length";
			throw new RangeException((short) 0, message);
		}
		try {
			con = DriverManager.getConnection(url, user, password);
			String questionMarkStm = "";

			String stm = null;
			String tableName = "";
			
			
			//dynamically concat the tables for the insert statement 
			for (String tN : name) {
				tableName += "," + tN;
			}
			tableName = tableName.replaceFirst(",","");
			
			//dynamically create the right amount of question marks for the value
			for(int i = 0; i<value.length;i++){
				questionMarkStm += ", " + "?";
			}
			questionMarkStm = questionMarkStm.replaceFirst(", ","");
			
			//create statement
			stm = "INSERT INTO " + table + "(" + tableName
					+ ") VALUES("+ questionMarkStm +")";
			pst = con.prepareStatement(stm);
			
			//set values
			for(int i = 0; i<value.length; i++){
				pst.setString(i+1, value[i]);
			}
			
			//execute statement me
			pst.executeUpdate();
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(InsertMethod.class.getName());
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
				Logger lgr = Logger.getLogger(InsertMethod.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

	}
	
}
