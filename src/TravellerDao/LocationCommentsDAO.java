/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TravellerDao;

import TravellerBean.LocationBean;
import TravellerBean.LocationCommentsBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Prince
 */
public class LocationCommentsDAO implements LocationCommentsINF{

    @Override
    public ArrayList getLocationCommentsByLocationId(int location_id) {
         //arralist object
        ArrayList arr=new ArrayList();
        
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("Select * from locationcomment where location_id=? order by locationcomment_id desc");
            ps.setInt(1,location_id);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
               LocationCommentsBean loc= new LocationCommentsBean();
                loc.setEmailId(rs.getString("EMAIL_ID"));
                loc.setLocationComment(rs.getString("LOCATIONCOMMENT"));
                loc.setLocationCommentId(rs.getInt("LOCATIONCOMMENT_ID"));
                loc.setLocationId(rs.getInt("LOCATION_ID"));
                arr.add(loc);
            }
            
            return arr;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
    }

    @Override
    public ArrayList getLocationCommentsByEmailId(int email_id) {
        
         //arralist object
        ArrayList arr=new ArrayList();
        
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("Select location_comment from locationcomment where email_id=?");
            ps.setInt(1,email_id);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
               
                arr.add(rs.getString(3));
                
            }
            
            return arr;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;
        
    }

    @Override
    public boolean addCommensToLocation(LocationCommentsBean comment) {
     
          //getting connection
        Connection con=DBConnection.getCon();
        try {
            int id;
            PreparedStatement ps=con.prepareCall("insert into locationcomment values(?,?,?,?)");
            PreparedStatement chk= con.prepareCall("select max(locationcomment_id) from locationcomment");
            ResultSet rrs= chk.executeQuery();
            if(rrs.next()){
                id=rrs.getInt(1);
                id+=1;
            }else{
                id=0;
            }
            ps.setInt(1,id);
            ps.setString(2, comment.getLocationComment());
            ps.setInt(3, comment.getLocationId());
            ps.setString(4,comment.getEmailId());
            
            int k=ps.executeUpdate();
            if(k!=0){
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
}
