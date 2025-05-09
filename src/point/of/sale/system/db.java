/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package point.of.sale.system;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.*;



public class db {
    public static Connection mycon(){
        Connection con = null;

        try {
            
            String url = "";    
            String user = "";
            String password = "";
            
            con = DriverManager.getConnection(url,user,password);

            // Set a busy timeout
//            

            System.out.println("Connected to SQLite database!");
        } catch (SQLException e) {
            System.out.println("Error connecting to SQLite database: " + e.getMessage());
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
    }

    
}
