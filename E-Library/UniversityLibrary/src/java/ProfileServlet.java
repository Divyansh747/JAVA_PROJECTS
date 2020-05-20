/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Divyansh747
 */
@WebServlet(urlPatterns = {"/ProfileServlet"})
public class ProfileServlet extends HttpServlet {

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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            String name = (String) session.getAttribute("name");
            if ("admin".equals(name)) {
                request.getRequestDispatcher("admin-link.html").include(request, response);
            } else {
                request.getRequestDispatcher("link.html").include(request, response);
            }

            String id = (String) session.getId();
            long t = (long) session.getCreationTime();
            long la = (long) session.getLastAccessedTime();

            out.print("<center><h2>Hello, <b>" + name + "</b> Welcome to Profile Section</h2></center><br><br>");
            
            
             try {
            Class.forName("com.mysql.jdbc.Driver");
        }   catch (ClassNotFoundException ex) {
            System.out.println("Driver not found"+ ex);
            //Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {       
              Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
        PreparedStatement ps = con.prepareStatement("select * from Downloads where Email=?");
             ps.setString(1, name);
           
            ResultSet rs =ps.executeQuery();
             out.println("<h4><b><center>Your Download History</center></b></h4><hr>");
             out.println("<center><table  class='table' border=1>");  
             out.println("<thead class='thead-dark'><tr><th>EMAIL</th><th>BookName</th><th>Date of Purchase</th></tr></thead>");  
             while(rs.next()) 
             {  
                 String EMAIL = rs.getString("EMAIL");  
                 String BookName = rs.getString("BookName");
                 String DOP = rs.getString("DateOfPurchase");
                 out.println("<tr><td>" + EMAIL + "</td><td>" + BookName + "</td><td>" + DOP + "</td>");   
             }
             out.println("</table></center>");  
       } 
       catch(SQLException e) {
           System.out.println(e);
        }
            
        } else {
            request.getRequestDispatcher("login.html").include(request, response);
            out.print("<center><b><h3>Please login first to access Univeristy Library Portal!</h3></b></center>");
        }
        out.close();
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
