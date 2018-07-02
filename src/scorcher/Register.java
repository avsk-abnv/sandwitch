/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scorcher;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import TravellerBean.*;
import TravellerDao.*;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Abhishek Abhinav
 */
public class Register extends HttpServlet {


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String first_name= request.getParameter("first_name");
        String last_name= request.getParameter("last_name");
        String display_name= request.getParameter("display_name");
        String email_id= request.getParameter("email");
        String password= request.getParameter("password");
        String gender= request.getParameter("gender");
        UserDAO udao= new UserDAO();
        UserBean user = new UserBean();
        user.setFirst_name(first_name);     user.setLast_name(last_name);
        user.setDisplay_name(display_name);     user.setEmail_id(email_id);
        user.setPassword(password); user.setGender(gender);
        boolean success= udao.registerUser(user);
        if(success){
            response.sendRedirect("/");
            user= udao.getUserById(email_id);
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
