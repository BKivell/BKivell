import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brad Kivell (20115449) 
 * 
 */
public class DBManager {

    Connection conn = null;

    String url = "jdbc:derby:PlayerDB;create=true";  //url of the DB host

    String dbusername = "pdc";  // DB username
    String dbpassword = "pdc";   // DB password

    // Default constructor
    public DBManager() {

    }

    // Initializes database, checks if table exists and creates a table if it doesn't
    public void dbsetup() {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "UserInfo";

            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (userid VARCHAR(12), password VARCHAR(12), score INT)");
            }
            statement.close();

        } catch (Throwable e) {
            System.out.println("error");

        }
    }

    // Returns true if the table already exists, false if not found
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            //Statement dropStatement=null;
            if (rsDBMeta != null) {
                while (rsDBMeta.next()) {

                    String tableName = rsDBMeta.getString("TABLE_NAME");
                    if (tableName.compareToIgnoreCase(newTableName) == 0) {
                        System.out.println(tableName + "  is there");
                        flag = true;
                    }
                }
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error verifying tables");
        }
        return flag;
    }

    // Returns result set based on query
    public ResultSet dBQuery(String statementString) {
        ResultSet rs = null;
        try {
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(statementString);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Query Failed");
        }
        return rs;
    }

    // Updates database based on input statement
    public void dBUpdate(String statementString) {
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(statementString);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
