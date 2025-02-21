/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package point.of.sale.system;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
            } else {
                System.out.println("Column 'sales_date' already exists. No update needed.");
                System.out.println("Exp_Date column is already of type DATE. No changes needed.");
                
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
