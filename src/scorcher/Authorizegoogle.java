/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scorcher;

import TravellerBean.UserBean;
import TravellerDao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@WebServlet(name = "Authorizegoogle", urlPatterns = {"/Authorizegoogle"})
public class Authorizegoogle extends HttpServlet {

       @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try (PrintWriter out = response.getWriter()) {
            UserDAO udao= new UserDAO();
            UserBean user= udao.getUserById(request.getParameter("email"));
            response.setContentType("text/plain");
             if(user!=null){
                                    HttpSession session= request.getSession();
                                    session.setAttribute("currentuser", user);
                                    out.println("success");
             }else{
                 user=new UserBean();
                     String first_name= "none";
                        String last_name= "none";
                        String display_name= request.getParameter("name");
                        String email_id= request.getParameter("email");
                        String password= "none";
                        String gender= "none";
                        user.setFirst_name(first_name);     user.setLast_name(last_name);
                        user.setDisplay_name(display_name);     user.setEmail_id(email_id);
                        user.setPassword(password); user.setGender(gender);
                        boolean success= udao.registerUser(user);
                        if(success){
                            user= udao.getUserById(email_id);
                            HttpSession session= request.getSession();
                            session.setAttribute("currentuser", user);
                            out.println("direct");
                        }else{
                            out.println("failure");
                        }
                }
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
