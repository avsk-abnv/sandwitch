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
import TravellerBean.*;
import TravellerDao.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Abhishek Abhinav
 */
public class likendislike extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //out.println("ye wanna "+ request.getParameter("action")+request.getParameter("locid"));
            LocationDAO dao= new LocationDAO();
            int location_id=Integer.parseInt(request.getParameter("locid"));
            boolean success=dao.likeordislike(request.getParameter("action"),location_id);
            HttpSession session = request.getSession();
            String email_id= ((UserBean)session.getAttribute("currentuser")).getEmail_id();
            RecordBean record= new RecordBean();
            record.setEmail_id(email_id);       record.setLocation_id(location_id);
            LikeDAO ldo= new LikeDAO();
            DislikeDAO ddo= new DislikeDAO();
            LocationBean lb;
            boolean success0=false;
            String action=request.getParameter("action");
            if(action.equalsIgnoreCase("like")){
                success0=ldo.useraction_like(record);
            }else if(action.equalsIgnoreCase("undo_like")){
                success0=ldo.useraction_undolike(record);
            }else if(action.equalsIgnoreCase("dislike")){
                success0= ddo.useraction_dislike(record);
            }else if(action.equalsIgnoreCase("undo_dislike")){
                success0= ddo.useraction_undodislike(record);
            }
            if(success && success0){
                lb= dao.getLocationByLocationId(location_id);
                if(action.equalsIgnoreCase("like") || action.equalsIgnoreCase("undo_like")){
                    
                    out.println(lb.getLocation_likes());
                }else if(action.equalsIgnoreCase("dislike") || action.equalsIgnoreCase("undo_dislike")){
                    out.println(lb.getLocation_dislikes());
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
