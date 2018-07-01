/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scorcher;

import TravellerBean.LocationBean;
import TravellerBean.LocationCommentsBean;
import TravellerBean.UserBean;
import TravellerDao.LocationCommentsDAO;
import TravellerDao.*;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.util.*;
import java.lang.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Prince
 */
public class Test {
    public static void main(String args[]){
UserDAO udao= new UserDAO();
            UserBean user= udao.getUserById("shhghghghg");
            String first_name= "none";
                        String last_name= "none";
                        String display_name= "kal hal";
                        String email_id= "ral@bal.com";
                        String password= "none";
                        String gender= "none";
                        user.setFirst_name(first_name);     user.setLast_name(last_name);
                        user.setDisplay_name(display_name);     user.setEmail_id(email_id);
                        user.setPassword(password); user.setGender(gender);
                        boolean success= udao.registerUser(user);
                        if(success){
                            user= udao.getUserById(email_id);
                           // HttpSession session= request.getSession();
                            //session.setAttribute("currentuser", user);
                            System.out.println("direct");
                        }else{
                            System.out.println("failure");
                        }
    
    }
}
