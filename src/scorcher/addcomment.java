/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scorcher;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import TravellerBean.*;
import TravellerDao.*;

/**
 *
 * @author Abhishek Abhinav
 */
public class addcomment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session= request.getSession();
            UserBean ub= (UserBean)session.getAttribute("currentuser");
            String usercomment= request.getParameter("comment");
            int locid= Integer.parseInt(request.getParameter("locid"));
            String email_id= ub.getEmail_id();
            String username=ub.getDisplay_name();
            LocationCommentsDAO lcd= new LocationCommentsDAO();
            LocationCommentsBean lob= new LocationCommentsBean();
            lob.setEmailId(email_id);
            lob.setLocationComment(usercomment);
            lob.setLocationId(locid);
            lob.setLocationCommentId(0);
            boolean success= lcd.addCommensToLocation(lob);
            if(success)
                out.println(username+"\t"+usercomment);
            else
                out.println("failed\tsad");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
