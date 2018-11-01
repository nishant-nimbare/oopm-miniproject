
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
    
     void addBook(String book,String author,String username,String email){
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
            String query="INSERT INTO `user_books`(`bk_name`, `bk_auth`, `uname`, `email`) VALUES (?,?,?,?)";
            try {
                ps= Myconnection.getConnection().prepareStatement(query);

                ps.setString(1,book);
                ps.setString(2,author);
                ps.setString(3,username);
                ps.setString(4, email);
           

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
                homepage s=new homepage(username);
              //  s.setUser(username);
                s.setVisible(true);
             }
            else{
                JOptionPane.showMessageDialog(null,"invalid user");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login_form.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    String[][] getBookHistory(String currentUser) {
         String[][] data = new String[10][2] ;
         PreparedStatement ps;
         ResultSet rs;
         String query="SELECT * FROM user_books WHERE`uname`='"+currentUser+"'";
        
         try{
            ps=Myconnection.getConnection().prepareStatement(query);
           // ps.setString(1,currentUser);
            
            rs=ps.executeQuery(query);
            int i=0;
            while(rs.next()){
                
                String book=rs.getString(1);
                String author=rs.getString(2);
                data[i][0]=book;
                data[i][1]=author;
                System.out.println(data[i][0]);
                i++;
            }
             
         }catch(SQLException e){
             System.out.println("error in getBookHistory");
         }
        return data;
    }

    String[][] searchBooks(String bookname) {
         String[][] data = new String[10][4] ;
         PreparedStatement ps;
         ResultSet rs;
         String query="SELECT * FROM user_books WHERE`bk_name`='"+bookname+"'";
        
         try{
            ps=Myconnection.getConnection().prepareStatement(query);
           // ps.setString(1,currentUser);
            
            rs=ps.executeQuery(query);
            int i=0;
            while(rs.next()){
                
                String book=rs.getString(1);
                String author=rs.getString(2);
                String user=rs.getString(3);
                String email=rs.getString(4);
                data[i][0]=book;
                data[i][1]=author;
                data[i][2]=user;
                data[i][3]=email;
                System.out.println(data[i][2]);
                i++;
            }
             
         }catch(SQLException e){
             System.out.println("error in getBookHistory");
         }
        return data;
    }

    String getUserEmail(String user) {
       String email=null;
       
       PreparedStatement ps;
       ResultSet rs;
       String query="SELECT `email` FROM users WHERE`username`='"+user+"'";
       
         try{
            ps=Myconnection.getConnection().prepareStatement(query);
           // ps.setString(1,currentUser);
            
            rs=ps.executeQuery(query);
            int i=0;
            if(rs.next()){
                
                email=rs.getString(1);
               
                System.out.println(email);
              
            }
             
         }catch(SQLException e){
             System.out.println("error in getBookHistory");
         }
       
       return email;
    }

    void deleteBook(String bookName, String user) {
        
        System.out.println("inside delete book= "+bookName+" user= "+user);
        PreparedStatement ps;
       ResultSet rs;
       String query="DELETE FROM `user_books` WHERE `bk_name`='"+bookName+"' AND `uname`='"+user+"'";
       //DELETE FROM `user_books` WHERE `bk_name`="lotr" AND `uname`="nimit"
      // String query="DELETE FROM `user_books` WHERE `bk_name`='lotr'";
       try{
            ps=Myconnection.getConnection().prepareStatement(query);
            ps.executeUpdate();
            
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null,"Book deleted");
            }
           
           
       }catch(SQLException e){
             System.out.println("error in deleteBook");
       }
    }


    
    
    
    
}
