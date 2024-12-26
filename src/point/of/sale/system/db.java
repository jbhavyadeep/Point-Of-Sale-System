/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package point.of.sale.system;

import java.sql.*;
/**
 *
 * @author jbhav
 */
public class db {
    public static Connection mycon(){
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/pos","root","");
            return con;
        }catch (ClassNotFoundException | SQLException e){
            
            System.out.println(e);
            return null;
        }
    }
}
