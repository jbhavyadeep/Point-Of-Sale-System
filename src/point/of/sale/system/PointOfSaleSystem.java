/**
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package point.of.sale.system;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class for the POS system. Ensures the database structure is updated
 * before launching the GUI.
 *
 * @author jbhav
 */
public class PointOfSaleSystem {

    // Method to update sales table by adding 'sales_date' column if it doesn't exist
    private static boolean isExpDateAlreadyDate(Connection conn) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try ( ResultSet rs = metaData.getColumns(null, null, "grn", "Exp_Date")) {
            if (rs.next()) {
                String columnType = rs.getString("TYPE_NAME"); // Get column type
                return columnType.equalsIgnoreCase("DATE"); // Check if it's already DATE
            }
        }
        return false;
    }
    
    
    private static boolean isTableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try (ResultSet rs = metaData.getTables(null, null, tableName, null)) {
            return rs.next(); // Returns true if table exists
        }
    }

    public static void updateSalesTable() {
        String columnName = "sales_date";
        String tableName = "sales";
        String sql = "ALTER TABLE sales ADD COLUMN sales_date DATE";
        String sql1 = "DELETE FROM sales ";

        try ( Connection conn = db.mycon();  Statement stmt = conn.createStatement()) {

            if (!isExpDateAlreadyDate(conn)) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS grn_new ("
                    + "Id INTEGER PRIMARY KEY, "
                    + "GRN_NO VARCHAR(10) NOT NULL, "
                    + "Sid VARCHAR(10) NOT NULL, "
                    + "Barcode VARCHAR(20) NOT NULL, "
                    + "Itm_Name VARCHAR(20) NOT NULL, "
                    + "Qty VARCHAR(20) NOT NULL, "
                    + "Cost_Price VARCHAR(10) NOT NULL, "
                    + "Sell_Price VARCHAR(10) NOT NULL, "
                    + "Exp_Date DATE NOT NULL, "
                    + // Changed type to DATE
                    "Sub_Total VARCHAR(10) NOT NULL, "
                    + "Discount VARCHAR(10) NOT NULL, "
                    + "Net_Total VARCHAR(10) NOT NULL)";

            stmt.execute(createTableSQL);
            System.out.println("New GRN table created with Exp_Date as DATE.");

            // Step 3: Copy data from old table while converting Exp_Date
            String copyDataSQL = "INSERT INTO grn_new "
                    + "SELECT Id, GRN_NO, Sid, Barcode, Itm_Name, Qty, Cost_Price, Sell_Price, "
                    + "IFNULL(DATE(Exp_Date), '2000/01/01 12:00:00.000') AS Exp_Date, Sub_Total, Discount, Net_Total FROM grn";

            stmt.execute(copyDataSQL);
            System.out.println("Data copied to new GRN table with Exp_Date conversion.");

            // Step 4: Remove old table and rename new table to 'grn'
            stmt.execute("DROP TABLE grn");
            stmt.execute("ALTER TABLE grn_new RENAME TO grn");
            System.out.println("Old GRN table replaced successfully.");
                
            }

            if (!columnExists(conn, tableName, columnName) ) {

                stmt.execute(sql1);
                
                stmt.execute(sql);

                System.out.println("Table updated successfully: 'sales_date' column added.");
            }
            if (!isTableExists(conn, "authenticate")) {
                // Create the authenticate table if it doesn't exist
            String createTableSQL = "CREATE TABLE authenticate (" +
                    "uid INTEGER PRIMARY KEY, "+
                    "user_name VARCHAR(10) NOT NULL, " +
                    "password VARCHAR(10) NOT NULL)";
            stmt.execute(createTableSQL);
            

            String insertSQL = "INSERT INTO authenticate (user_name, password) VALUES (?, ?),(?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                        pstmt.setString(1, "kamlesh");
                        pstmt.setString(2, "223084");
                        pstmt.setString(3, "admin");
                        pstmt.setString(4, "admin123");
                        
                        pstmt.executeUpdate();
                        System.out.println("Default admin user added to 'authenticate' table.");
                    }
            System.out.println("Table 'authenticate' created successfully.");
           
 
            }
             if(!columnExists(conn, "cart", "is_returned") && !columnExists(conn, "cart", "return_date")){
                String ALTERTableSQL1 ="ALTER TABLE cart ADD column is_returned BOOLEAN NOT NULL DEFAULT (FALSE)";
                String ALTERTableSQL2 ="ALTER TABLE cart ADD column return_date";
                stmt.execute(ALTERTableSQL1);
                stmt.execute(ALTERTableSQL2);
                System.out.println("is_returned and return_date COLUMN ARE ADDED TO the table cart");
           

                
                
            }
            else {
                System.out.println("Column 'sales_date' already exists. No update needed.");
                System.out.println("Exp_Date column is already of type DATE. No changes needed.");
                System.out.println("Table 'authenticate' already exists.");

            }

        } catch (SQLException e) {
            System.out.println("Database update failed: " + e.getMessage());
        }
    }

    // Method to check if a column exists in the given table
    private static boolean columnExists(Connection conn, String tableName, String columnName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try ( ResultSet rs = metaData.getColumns(null, null, tableName, columnName)) {
            return rs.next();  // Returns true if the column exists
        }
    }

    public static void main(String[] args) {
        // Update the sales table (only if needed)

    }
}
