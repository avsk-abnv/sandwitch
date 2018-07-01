/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TravellerDao;

import TravellerBean.*;
import java.util.ArrayList;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface DislikeINTF {
    public boolean useraction_dislike(RecordBean record);
    public boolean useraction_undodislike(RecordBean record);
    public boolean check_userdislikes(RecordBean record);
    public ArrayList<Integer> getAllUserDislikedLocations(String email_id);
    
}
