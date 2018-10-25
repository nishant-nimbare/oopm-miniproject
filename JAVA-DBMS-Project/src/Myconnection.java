
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Myconnection {
    public static Connection getConnection(){  
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
            
       
        }
        catch(Exception e){                
           JOptionPane.showMessageDialog(null,"Not connected");
        } 
        return con;
}
}
