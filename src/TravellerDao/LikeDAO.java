/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TravellerDao;
import TravellerBean.*;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class LikeDAO implements LikeINTF{

    @Override
    public boolean useraction_like(RecordBean record) {
        Connection con=DBConnection.getCon();
        try {
            PreparedStatement ps= con.prepareCall("insert into likes values(?,?)");
            ps.setString(1, record.getEmail_id());             ps.setInt(2, record.getLocation_id());
            int k= ps.executeUpdate();
            if(k!=0)
                return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(LikeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public boolean useraction_undolike(RecordBean record) {
        Connection con= DBConnection.getCon();
        try {
            PreparedStatement ps= con.prepareCall("delete from likes where email_id=? and location_id=?");
            ps.setString(1, record.getEmail_id());          ps.setInt(2, record.getLocation_id());
            int k= ps.executeUpdate();
            if(k!=0)
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(LikeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean check_userlikes(RecordBean record) {
        Connection con= DBConnection.getCon();
        try {
            PreparedStatement ps= con.prepareCall("select * from likes where email_id=? and location_id=?");
            ps.setString(1,record.getEmail_id());           ps.setInt(2, record.getLocation_id());
            ResultSet rs= ps.executeQuery();
            if(rs.next())
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(LikeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<Integer> getAllUserLikedLocations(String email_id) {
        Connection con= DBConnection.getCon();
        ArrayList<Integer>  locids=new ArrayList();
        try {
            PreparedStatement ps = con.prepareCall("select location_id from likes where email_id=?");
            ps.setString(1, email_id);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                locids.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LikeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return locids;
    }


    
}
