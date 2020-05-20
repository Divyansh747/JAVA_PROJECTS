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

/**
 *
 * @author Divyansh747
 */
@WebServlet(urlPatterns = {"/ActivityServlet"})
public class ActivityServlet extends HttpServlet {
    private Object con;

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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("admin-link.html").include(request, response);

        out.print("<h1><center><b>University Online Library Portal</b></center></h1>");
        out.print("<br><h3><center><b>User Activity Portal</b></center></h3><hr>");
        
        String name = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }   catch (ClassNotFoundException ex) {
            System.out.println("Driver not found"+ ex);
            //Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {       
              Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
        PreparedStatement ps = con.prepareStatement("select * from Downloads");
            ResultSet rs =ps.executeQuery();
            
             out.println("<br><br><center>");
             out.println("<table border=1 class='table'>");  
             out.println("<thead class='thead-dark'><tr><th>EMAIL</th><th>BookName</th><th>Date of Purchase</th></tr></thead>");  
             while(rs.next()) 
             {  
                 String EMAIL = rs.getString("EMAIL");  
                 String BookName = rs.getString("BookName");
                 String DOP = rs.getString("DateOfPurchase");
                 out.println("<tr><td>" + EMAIL + "</td><td>" + BookName + "</td><td>" + DOP + "</td>");   
             }
             out.println("</table>");  
             out.println("</center>");
             
       } 
       catch(SQLException e) {
           System.out.println(e);
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
