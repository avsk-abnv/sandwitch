/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TravellerDao;

import TravellerBean.UserBean;
import java.util.ArrayList;

/**
 *
 * @author Prince
 */
public interface UserINF {
    
    //user details
    public boolean registerUser(UserBean user);
    public boolean loginUser(String uname,String pass);
    public ArrayList getAllUsers();
    public UserBean getUserById(String email_id);
    public boolean updateUser(UserBean user);
    public boolean deleteUser(UserBean user);
    public boolean checkifemail_is_unique(String email);
}
