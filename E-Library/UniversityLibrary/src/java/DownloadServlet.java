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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Divyansh747
 */
@WebServlet(urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {

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
            PrintWriter out=response.getWriter();  
           request.getRequestDispatcher("link.html").include(request, response);
    
            out.println("<center><h1><b>Download Section</b></h1></center>");
            Object name=request.getSession().getAttribute("name");  
            //out.println((String)name);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
            LocalDateTime now = LocalDateTime.now();  
            String Date = dtf.format(now);  
             String isbn=request.getParameter("isbn");  

            String BookName = null;
            String DepartName ;
            String CourseName ;
            String Location ;
            String link = null;
            
            try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con;
               
            //creating connection with the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","root");
            PreparedStatement ps = con.prepareStatement("select * from Books where ISBN=?");
            
            ps.setString(1, isbn);
            ResultSet rs=ps.executeQuery();
             while(rs.next()) 
             {
                BookName = rs.getString("BookName");
                DepartName = rs.getString("DepartName");
                CourseName = rs.getString("CourseName");
                Location = rs.getString("Location");
                //link="file:///C:/Users/Divyansh747/Documents/NetBeansProjects/UniversityLibrary/web/Downloads/Books/"+DepartName+"/"+CourseName+"/"+Location;
                link="file:///C:/Users/Divyansh747/Desktop/Downloads/Books/"+DepartName+"/"+CourseName+"/"+Location;
             
                
             }
              out.println("<h2><center>Your Download link generated</center></h2>");
              out.println("<br><h3><center><a href='"+link+"' download target='_blank'>Download</a></center></h3>");
          
            String sql="INSERT INTO Downloads(Email,BookName,DateOfPurchase) VALUES(?,?,?);";
            PreparedStatement ps1 = con.prepareStatement(sql);
           
            ps1.setString(1, (String)name);
            ps1.setString(2, BookName);
            ps1.setString(3, Date);
            ps1.executeUpdate();
        
        
        }
         catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
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
