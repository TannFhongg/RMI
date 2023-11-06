/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bchat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Blacky
 */
public class Datasource {

    private static Connection con = null;

    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bchat", "root", "");
              
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;
                DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bchat;shutdown=true");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}