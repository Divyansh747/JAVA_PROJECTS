/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
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
@WebServlet(urlPatterns = {"/BookServlet"})
public class BookServlet extends HttpServlet {

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
        PrintWriter out=response.getWriter();  
        
          
        HttpSession session=request.getSession(false);  
        if(session!=null){  
        String name=(String)session.getAttribute("name");  
        if("admin".equals(name)){
            request.getRequestDispatcher("admin-link.html").include(request, response);  
        }
        else {
        request.getRequestDispatcher("link.html").include(request, response);  
        }
        
        String id = (String)session.getId();
        long t = (long)session.getCreationTime();
        long la = (long)session.getLastAccessedTime();
        
        out.print("<center><b><h3>Hello,<b> "+name+"</b><br> Welcome to Book Download Section</h3></b></center><br><hr>");  
        
          
       
       try {
   
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","root");
            PreparedStatement ps = con.prepareStatement("select * from Books where DepartName=?");
            
            
            String department = request.getParameter("department");
            ps.setString(1, department);
            ResultSet rs =ps.executeQuery();
            out.println("<center><h4><b>Department Selected-> "+ department+"</b></h4></center><hr>");

             out.println("<center><table border=1 class='table'>");  
             out.println("<thead class='thead-dark'><tr><th>ISBN</th><th>BookName</th><th>Author</th><th>DepartName</th><th>CourseName</th></tr></thead>");  
             while(rs.next()) 
             {  
                 String ISBN = rs.getString("ISBN");  
                 String BookName = rs.getString("BookName");
                 String Author = rs.getString("Author");
                 String DepartName = rs.getString("DepartName");
                 String CourseName = rs.getString("CourseName");
                 String Location = rs.getString("Location");
                 out.println("<tr><td>" + ISBN + "</td><td>" + BookName + "</td><td>" + Author + "</td><td>" + DepartName +"</td><td>" + CourseName +"</td></tr>");   
                 
                 String link="C:\\Users\\Divyansh747\\Documents\\NetBeansProjects\\UniversityLibrary\\web\\Downloads\\Books\\"+DepartName+"\\"+CourseName+"\\"+Location;
                 
                 //out.println("<td>");
                 //out.println("<a href='"+link+"' download '>Download</a>");
                // out.println("</td></tr>");

             }
             
             out.println("</table></center><br>"); 
             
             out.println("<hr><center><h2>Please enter ISBN number to download book</h2></center>");
             request.getRequestDispatcher("Download.html").include(request, response);
       } 
       catch(ClassNotFoundException | SQLException e) {
        }
        }  
        else{  
            out.print("Please login first");  
            request.getRequestDispatcher("login.html").include(request, response);  
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
