
package TravellerDao;

import TravellerBean.LocationBean;
import java.util.ArrayList;



public interface LocationINF {
    
    //location details methods
    public boolean registerLocation(LocationBean location);
    public ArrayList getAllLocations();
    public LocationBean getLocationByLocationId(int location_id);
    public ArrayList getLocationsByLocationName(String location_name);
    public ArrayList getLocationsByLocationType(String location_type);
    public ArrayList getLocationsByLocationClimate(String location_climate);
    public ArrayList getLocationsByLocationState(String location_state);
    public boolean updateLocation(LocationBean Location);
    public boolean deleteLocation(LocationBean location);
    public boolean likeordislike(String what_u_want,int location_id);
    public ArrayList getAllLocations(int forpage);
}
