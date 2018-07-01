/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TravellerDao;
import java.util.ArrayList;
import TravellerBean.*;
/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface LikeINTF {
    public boolean useraction_like(RecordBean record);
    public boolean useraction_undolike(RecordBean record);
    public boolean check_userlikes(RecordBean record);
    public ArrayList<Integer> getAllUserLikedLocations(String email_id);
}
