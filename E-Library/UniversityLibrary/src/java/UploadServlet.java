/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
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
import javax.tools.DocumentationTool.Location;

/**
 *
 * @author Divyansh747
 */
@WebServlet(urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {

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

        HttpSession session = request.getSession(false);
        if (session != null) {
            String ISBN=request.getParameter("ISBN");  
            String BookName=request.getParameter("BookName");  
            String Author=request.getParameter("Author");  
            String DepartName=request.getParameter("DepartName");  
            String CourseName=request.getParameter("CourseName");  
            String Location=request.getParameter("Location");  

            String id = (String) session.getId();
          
            
            try {

                Class.forName("com.mysql.jdbc.Driver");

                //creating connection with the database
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
                String sql = "INSERT INTO Books (ISBN,BookName,Author,DepartName,CourseName,Location) VALUES(?,?,?,?,?,?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, ISBN);
            statement.setString(2, BookName);
            statement.setString(3, Author);
            statement.setString(4, DepartName);
            statement.setString(5, CourseName);
            statement.setString(6, Location);
            statement.executeUpdate();
            out.print("<h2><center>" + BookName + ", uploaded successfully</center></h2>");

            } catch (ClassNotFoundException | SQLException e) {
            }
        } else {
            out.print("Please login first");
            request.getRequestDispatcher("login.html").include(request, response);
        }
        out.close();
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
