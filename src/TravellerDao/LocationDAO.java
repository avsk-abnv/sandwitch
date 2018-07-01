
package TravellerDao;

import TravellerBean.LocationBean;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Prince
 */
public class LocationDAO implements LocationINF {

    @Override
    public ArrayList getAllLocations() {
        
        //arralist object
        ArrayList arr=new ArrayList();
        
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("Select * from location order by location_id");
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                
                //creating locaton object
                LocationBean locbn=new LocationBean();
                locbn.setLocation_id(rs.getInt(1));
                locbn.setLocation_name(rs.getString(2));
                locbn.setLocation_desc(rs.getString(3));
                locbn.setLocation_likes(rs.getInt(4));
                locbn.setLocation_dislikes(rs.getInt(5));
                locbn.setLocation_type(rs.getString(6));
                locbn.setLocation_climate(rs.getString(7));
                locbn.setLocation_address(rs.getString(8));
                locbn.setLocation_state(rs.getString(9));
                locbn.setLocation_imageurl(rs.getString(10));
                locbn.setLocation_thumbnail(rs.getString(11));
                arr.add(locbn);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return arr;
    }

    @Override
    public LocationBean getLocationByLocationId(int location_id) {
        
        //getting connection
         Connection con=DBConnection.getCon();
         
          LocationBean locbn=new LocationBean();
        try {
            
            PreparedStatement ps=con.prepareCall("Select * from location where location_id=?");
            ps.setInt(1, location_id);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                
                //creating locaton object
               
                locbn.setLocation_id(rs.getInt(1));
                locbn.setLocation_name(rs.getString(2));
                locbn.setLocation_desc(rs.getString(3));
                locbn.setLocation_likes(rs.getInt(4));
                locbn.setLocation_dislikes(rs.getInt(5));
                locbn.setLocation_type(rs.getString(6));
                locbn.setLocation_climate(rs.getString(7));
                locbn.setLocation_address(rs.getString(8));
                locbn.setLocation_state(rs.getString(9));
                locbn.setLocation_imageurl(rs.getString(10));
                locbn.setLocation_thumbnail(rs.getString(11));
            }
            else{
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return locbn;
    }
    @Override
    public boolean likeordislike(String what_u_want, int location_id) {
        Connection con = DBConnection.getCon();
        int likes=0,dislikes=0;
        try {
            PreparedStatement pso=con.prepareCall("select location_likes,location_dislikes from location where location_id=?");
            pso.setInt(1, location_id);
            ResultSet rs;
            rs= pso.executeQuery();
            if(rs.next()){
                likes=rs.getInt(1);
                dislikes= rs.getInt(2);
            }
            PreparedStatement ps=null;
            if(what_u_want.equalsIgnoreCase("like")){
                ps= con.prepareCall("update location set location_likes=location_likes+1 where location_id=?");
            }else if(what_u_want.equalsIgnoreCase("dislike")){
                ps= con.prepareCall("update location set location_dislikes=location_dislikes+1 where location_id=?");
            }else if(what_u_want.equalsIgnoreCase("undo_like") && likes>0){
                ps= con.prepareCall("update location set location_likes=location_likes-1 where location_id=?");
            }else if(what_u_want.equalsIgnoreCase("undo_dislike") && dislikes>0){
                ps= con.prepareCall("update location set location_dislikes=location_dislikes-1 where location_id=?");
            }
            if(ps!=null){
                ps.setInt(1, location_id);
                int k= ps.executeUpdate();
                if(k!=0)
                    return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList getLocationsByLocationName(String location_name) {
        
        //arralist object
        ArrayList arr=new ArrayList();
        
        //getting connection
        Connection con=DBConnection.getCon();
        try {
           // location_name=location_name.toLowerCase();
            PreparedStatement ps=con.prepareCall("Select * from location where substring(location_name from  position('(' in location_name)+1) LIKE ? "
                    + "or location_name LIKE ?");
            ps.setString(1, location_name+"%");
            ps.setString(2, location_name+"%");
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                LocationBean locbn=new LocationBean();
                locbn.setLocation_id(rs.getInt(1));
                locbn.setLocation_name(rs.getString(2));
                locbn.setLocation_desc(rs.getString(3));
                locbn.setLocation_likes(rs.getInt(4));
                locbn.setLocation_dislikes(rs.getInt(5));
                locbn.setLocation_type(rs.getString(6));
                locbn.setLocation_climate(rs.getString(7));
                locbn.setLocation_address(rs.getString(8));
                locbn.setLocation_state(rs.getString(9));
                locbn.setLocation_imageurl(rs.getString(10));
                locbn.setLocation_thumbnail(rs.getString(11));
                arr.add(locbn);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println("There is some error");
        }
        return arr;   
    }
            @Override
    public ArrayList getLocationsByLocationType(String location_type) {
         //arralist object
        ArrayList arr=new ArrayList();
        
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("Select * from location where location_type=?");
            ps.setString(1, location_type);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                LocationBean locbn=new LocationBean();
                locbn.setLocation_id(rs.getInt(1));
                locbn.setLocation_name(rs.getString(2));
                locbn.setLocation_desc(rs.getString(3));
                locbn.setLocation_likes(rs.getInt(4));
                locbn.setLocation_dislikes(rs.getInt(5));
                locbn.setLocation_type(rs.getString(6));
                locbn.setLocation_climate(rs.getString(7));
                locbn.setLocation_address(rs.getString(8));
                locbn.setLocation_state(rs.getString(9));
                locbn.setLocation_imageurl(rs.getString(10));
                locbn.setLocation_thumbnail(rs.getString(11));
                arr.add(locbn);
            }
            return arr;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;   
    }

    @Override
    public ArrayList getLocationsByLocationClimate(String location_climate) {
         //arralist object
        ArrayList arr=new ArrayList();
        
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("Select * from location where location_climate=?");
            ps.setString(1, location_climate);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                LocationBean locbn=new LocationBean();
                locbn.setLocation_id(rs.getInt(1));
                locbn.setLocation_name(rs.getString(2));
                locbn.setLocation_desc(rs.getString(3));
                locbn.setLocation_likes(rs.getInt(4));
                locbn.setLocation_dislikes(rs.getInt(5));
                locbn.setLocation_type(rs.getString(6));
                locbn.setLocation_climate(rs.getString(7));
                locbn.setLocation_address(rs.getString(8));
                locbn.setLocation_state(rs.getString(9));
                locbn.setLocation_imageurl(rs.getString(10));
                locbn.setLocation_thumbnail(rs.getString(11));
                arr.add(locbn);
            }
            
            
            return arr;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;   
    }

    @Override
    public ArrayList getLocationsByLocationState(String location_state) {
         //arralist object
        ArrayList arr=new ArrayList();
        
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("Select * from location where location_state=?");
            ps.setString(1, location_state);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                LocationBean locbn=new LocationBean();
                locbn.setLocation_id(rs.getInt(1));
                locbn.setLocation_name(rs.getString(2));
                locbn.setLocation_desc(rs.getString(3));
                locbn.setLocation_likes(rs.getInt(4));
                locbn.setLocation_dislikes(rs.getInt(5));
                locbn.setLocation_type(rs.getString(6));
                locbn.setLocation_climate(rs.getString(7));
                locbn.setLocation_address(rs.getString(8));
                locbn.setLocation_state(rs.getString(9));
                locbn.setLocation_imageurl(rs.getString(10));
                locbn.setLocation_thumbnail(rs.getString(11));
                arr.add(locbn);
            }
            
            
            return arr;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return arr;   
    }

    
    @Override
    public boolean updateLocation(LocationBean location) {
        
        
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("update location set location_desc=?,location_name=?,location_likes=?,location_dislikes=?,"
                    + "location_type=?,location_climate=?,location_address=?,location_state=?,location_imageurl=?,location_thumbnail=? where location_id=?");
            ps.setString(1, location.getLocation_desc());
            ps.setString(2,location.getLocation_name());
            ps.setInt(3,location.getLocation_likes());
            ps.setInt(4, location.getLocation_dislikes());
            ps.setString(5, location.getLocation_type());
            ps.setString(6, location.getLocation_climate());
            ps.setString(7, location.getLocation_address());
            ps.setString(8, location.getLocation_state());
            ps.setString(9, location.getLocation_imageurl());
            ps.setString(10, location.getLocation_thumbnail());
            ps.setInt(11, location.getLocation_id());
            
            
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
    public boolean deleteLocation(LocationBean location) {
        
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("delete from location where location_id=?");
            ps.setInt(1, location.getLocation_id());
            
            int k=ps.executeUpdate();
            con.close();
            if(k!=0){
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean registerLocation(LocationBean location) {
         //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("insert into location values(?,?,?,?,?,?,?,?,?,?,?)");
            
            ps.setInt(1, location.getLocation_id());
            ps.setString(2, location.getLocation_name());
            ps.setString(3,location.getLocation_desc());
            ps.setInt(4,location.getLocation_likes());
            ps.setInt(5, location.getLocation_dislikes());
            ps.setString(6, location.getLocation_type());
            ps.setString(7, location.getLocation_climate());
            ps.setString(8, location.getLocation_address());
            ps.setString(9, location.getLocation_state());
            ps.setString(10, location.getLocation_imageurl());
            ps.setString(11, location.getLocation_thumbnail());
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
    public ArrayList getAllLocations(int forpage) {
    ArrayList arr=new ArrayList();
        int lower=9*(forpage-1);
        int upper=(9*forpage)+1;
        //getting connection
        Connection con=DBConnection.getCon();
        try {
            
            PreparedStatement ps=con.prepareCall("Select * from location where location_id>? and location_id<? order by location_id");
            ps.setInt(1, lower);
            ps.setInt(2, upper);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                
                //creating locaton object
                LocationBean locbn=new LocationBean();
                locbn.setLocation_id(rs.getInt(1));
                locbn.setLocation_name(rs.getString(2));
                locbn.setLocation_desc(rs.getString(3));
                locbn.setLocation_likes(rs.getInt(4));
                locbn.setLocation_dislikes(rs.getInt(5));
                locbn.setLocation_type(rs.getString(6));
                locbn.setLocation_climate(rs.getString(7));
                locbn.setLocation_address(rs.getString(8));
                locbn.setLocation_state(rs.getString(9));
                locbn.setLocation_imageurl(rs.getString(10));
                locbn.setLocation_thumbnail(rs.getString(11));
                arr.add(locbn);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return arr;        
    }
}

   
   
    

