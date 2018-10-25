
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mohan
 */
public class DatabaseHandler {
    
     void addBook(String book,String author,String username){
         if(book.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Add a Book");
        }
        else if(username.equals(""))

        {
            JOptionPane.showMessageDialog(null,"Add a Username");
        }

        
        else{
            PreparedStatement ps;
            String query="INSERT INTO `user_books`(`bk_name`, `bk_auth`, `uname`) VALUES (?,?,?)";
            try {
                ps= Myconnection.getConnection().prepareStatement(query);

                ps.setString(1,book);
                ps.setString(2,author);
                ps.setString(3,username);
           

                if(ps.executeUpdate()>0)
                {
                    JOptionPane.showMessageDialog(null,"Book added successfully");
//                    add_book s=new add_book();
//                    s.setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(sign_up.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     void loginUser(String username,String password,javax.swing.JFrame frame){
       PreparedStatement ps;
       ResultSet rs;
       String query="SELECT * FROM users WHERE`username`=? AND `password`=?";
        try {
            ps=Myconnection.getConnection().prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            
            rs=ps.executeQuery();
            
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null,"logged in successfull");
                frame.dispose();
                homepage s=new homepage();
                s.setUser(username);
                s.setVisible(true);
             }
            else{
                JOptionPane.showMessageDialog(null,"invalid user");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login_form.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    String[] getBookHistory(String currentUser) {
         String[] data = new String[10] ;
         PreparedStatement ps;
         ResultSet rs;
         currentUser="nishant";
         String query="SELECT * FROM user_books WHERE`uname`='"+currentUser+"'";
        
         try{
            ps=Myconnection.getConnection().prepareStatement(query);
           // ps.setString(1,currentUser);
            
            rs=ps.executeQuery(query);
            int i=0;
            while(rs.next()){
                
                String book=rs.getString(1);
                String author=rs.getString(2);
                data[i]=book+"  "+author;
                System.out.println(data[i]);
                i++;
            }
             
         }catch(SQLException e){
             System.out.println("error in getBookHistory");
         }
        return data;
    }
    
}
