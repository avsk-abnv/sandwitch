/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scorcher;
import TravellerBean.*;
import TravellerDao.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Abhishek Abhinav
 */
public class Authorize extends HttpServlet {
    //Register
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            UserDAO udao= new UserDAO();
            boolean success= udao.loginUser(request.getParameter("email"), request.getParameter("pword"));
            UserBean user;
                    if(success){
                                    response.sendRedirect("/");
                                    user= udao.getUserById(request.getParameter("email"));
                                    HttpSession session= request.getSession();
                                    session.setAttribute("currentuser", user);
                    }else{
                        response.sendRedirect("/");
                    }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
