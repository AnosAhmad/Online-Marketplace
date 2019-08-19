import java.util.concurrent.Callable;
import java.sql.*;

/*
* Futuree class to write asynchronous code in java; 
* in this application the long running task is the 
* connecting to DB. Returns a lightwieght promise obj
* and when the object is avilabe return it. 
*/

public class ConnectionFuture  implements Callable {
@Override
    public Connection call() {
	    Connection conn = null;		
	    try {
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
            String DB_URL = "jdbc:mysql://in-csci-rrpc01.cs.iupui.edu:3306/ealikhas_db";
            //Database credentials
            String USER = "ealikhas";
            String PASS = "ealikhas";
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to the Database");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected Database successfully....");
  			} catch (Exception ex) {
                System.out.println("Exception: " + ex.getCause() );
				ex.printStackTrace();
				System.out.println();
            }            
        return conn;
        }		
}