package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.net.*;

/**
 *
 * @author
 */
public class DBHandler {

    Connection con = null;

    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //heroku sql server (ohunm00fjsjs1uzy.cbetxkdyhwsb.us-east-1.rds.amazonaws.com), quite slow.
            Connection con = DriverManager.getConnection("jdbc:mysql://ohunm00fjsjs1uzy.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/jnymjcp0uyvpzyu9", "y9orfx05kz2e7dmx", "tlnfjwlet36s75oy");
            return con;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.toString());
            return null;
        }
    }
}

//"jdbc:mysql://sql4.freesqldatabase.com/sql4484367", "sql4484367", "QJvLefjJC3"
//"jdbc:mysql://localhost/sql4484367", "root", ""
//b66d5719cf168c:b9d3b231@us-cdbr-east-05.cleardb.net
//Connection con = DriverManager.getConnection("jdbc:mysql://ohunm00fjsjs1uzy.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/jnymjcp0uyvpzyu9", "y9orfx05kz2e7dmx", "tlnfjwlet36s75oy");

