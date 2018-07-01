/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TravellerDao;

import TravellerBean.LocationCommentsBean;
import java.util.ArrayList;

/**
 *
 * @author Prince
 */
public interface LocationCommentsINF {
    
    //location comments details
    public boolean addCommensToLocation(LocationCommentsBean comment);
    public ArrayList getLocationCommentsByLocationId(int location_id);
    public ArrayList getLocationCommentsByEmailId(int email_id);
}
