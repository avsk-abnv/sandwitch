/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TravellerDao;

//import static TravellerDao.ConnectionDetails.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class DBConnection implements ConnectionDetails{
    
    //creating static abd final variables
    private static Connection con=null;
    
    //static section
    static{
        
        try{
            
            Class.forName(DRIVER);
            con=DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
        } 
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    //connection method
    
    public static Connection getCon(){
        return con;
    }
}
