
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Divyansh747
 */
public class Validate {
public static boolean checkUser(String email,String pass) 
    {
        boolean st =false;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","root");
            PreparedStatement ps = con.prepareStatement("select * from UserDetails where Email=? and Password=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs =ps.executeQuery();
            st = rs.next();

        }
        catch(ClassNotFoundException | SQLException e) {
        }
        return st;     
}
}
