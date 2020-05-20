/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Divyansh747
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        
        String name=request.getParameter("email");  
        String password=request.getParameter("password");  
          
        if("admin".equals(name) && "admin1234".equals(password)) {  
            request.getRequestDispatcher("admin-link.html").include(request, response);
            
            out.println("<br><center><h2>Welcome <b>"+name+"</b></h2></center>");
            out.println("<br><center><h4><b>You are success you are successfully Logged In</b></h2></center>");
            
            HttpSession session=request.getSession();
            session.setAttribute("name",name);
            session.setMaxInactiveInterval(1000);
        } else if(Validate.checkUser(name, password)){  
            request.getRequestDispatcher("link.html").include(request, response);  
       
            out.println("<br><center><h2>Welcome <b>"+name+"</b></h2></center>");
            out.println("<br><center><h4><b>You are successfully Logged In</b></h2></center>");
            
            HttpSession session=request.getSession();
            session.setAttribute("name",name);  
            session.setMaxInactiveInterval(1000);
            
        }
        else{  
            out.print("Sorry, username or password error!");
            out.print("Please Signup to access University Library Portal");
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
