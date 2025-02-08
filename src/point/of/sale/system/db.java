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
            String dbDir = Paths.get(System.getenv("APPDATA"), "POS_Patel_Provision_Store").toString();
            String dbPath = Paths.get(dbDir, "database.db").toString();

            // Ensure the directory exists
            File dir = new File(dbDir);
            if (!dir.exists()) {
                dir.mkdirs(); // Create directory if it doesn't exist
            }

            // SQLite connection string
            String url = "jdbc:sqlite:" + dbPath;
            //String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\database.db";       
            con = DriverManager.getConnection(url);

            // Set a busy timeout
            Statement stmt = con.createStatement();
            stmt.execute("PRAGMA busy_timeout = 5000;");  // Timeout in milliseconds (5 seconds)
            stmt.close();

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

    public static void main(String[] args) {
        Connection con = mycon();
        // Perform database operations here
        closeConnection(con);
    }
}
