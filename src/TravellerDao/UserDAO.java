/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TravellerDao;

import TravellerBean.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prince
 */
public class UserDAO implements UserINF {

    @Override
    public boolean registerUser(UserBean user) {
         //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("insert into traveluser values(?,?,?,?,?,?)");
            ps.setString(1,user.getEmail_id());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getDisplay_name());
            ps.setString(4, user.getFirst_name());
            ps.setString(5,user.getLast_name());
            ps.setString(6, user.getGender());
            
            int k=ps.executeUpdate();
            if(k!=0){
                return true;
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean loginUser(String uname, String pass) {
         //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("select * from traveluser where email_id=? and password=?");
            ps.setString(1,uname);
            ps.setString(2, pass);
           
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList getAllUsers() {
        
        ArrayList arr=new ArrayList();
        
     //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("select * from traveluser");
           
             ResultSet rs=ps.executeQuery();
             while(rs.next()){
                UserBean usbn=new UserBean();
                usbn.setEmail_id(rs.getString(1));
                usbn.setPassword(rs.getString(2));
                usbn.setDisplay_name(rs.getString(3));
                usbn.setFirst_name(rs.getString(4));
                usbn.setLast_name(rs.getString(5));
                usbn.setGender(rs.getString(6));
                 
                arr.add(usbn);
                
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }

    @Override
    public UserBean getUserById(String email_id) {
       
        //getting connection
         Connection con=DBConnection.getCon();
         
          UserBean usbn=null;
        try {
            
            PreparedStatement ps=con.prepareCall("Select * from traveluser where email_id=?");
            ps.setString(1, email_id);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                
                usbn=new UserBean();
               usbn.setEmail_id(rs.getString(1));
                usbn.setPassword(rs.getString(2));
                usbn.setDisplay_name(rs.getString(3));
                usbn.setFirst_name(rs.getString(4));
                usbn.setLast_name(rs.getString(5));  
                usbn.setGender(rs.getString(6));
            }
            else{
                
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usbn;
    }
    
    @Override
    public boolean updateUser(UserBean user) {
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("update traveluser set password=?,display_name=?, first_name=?,second_name=?, "
                    + "gender=? where email_id=?");
          
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getDisplay_name());
            ps.setString(3, user.getFirst_name());
            ps.setString(4,user.getLast_name());
            ps.setString(5, user.getGender());
            ps.setString(6,user.getEmail_id());
            
            
            int k=ps.executeUpdate();
            if(k!=0){
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(UserBean user) {
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("delete from traveluser where email_id=?");
            ps.setString(1, user.getEmail_id());
            
            int k=ps.executeUpdate();
            if(k!=0){
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
            @Override
    public boolean checkifemail_is_unique(String email) {
          Connection con=DBConnection.getCon();
        try {
            PreparedStatement ps=con.prepareCall("Select * from traveluser where email_id=?");
            ps.setString(1, email);
            ResultSet rs= ps.executeQuery();
            if(rs.next())
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
}
