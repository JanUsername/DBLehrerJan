import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDB {

    public static void main(String[] args) {
	
    	InsertMethod insert = new InsertMethod();
    	String[] arr = new String[6];
    	arr[0]="Gandalf";
    	arr[1]="Scata";
    	arr[2]="Sinich";
    	arr[3]="Sascha 3";
    	arr[4]="8959588";
    	arr[5]="ASddf23r43qf";
    	
    	String tableNames[] = {"name", "surname", "location", "address", "telefonnumber", "taxnumber"};
    	//insert.insert("tbl_customer",tableNames,arr);
    	UpdateMethod method = new UpdateMethod();
    	//method.update("tbl_Customer", "surname", "Brunner", "customer_ID", 7);
    	DeleteMethod delete = new DeleteMethod();
    	//delete.delete("tbl_Customer", "customer_ID", 8);
    	QueryMethod query = new QueryMethod();
    	query.query("tbl_Customer",1,"customer_ID");
    }
}