package com.chatapp.models;
import java.sql.*;

/**
 *
 * @author mac
 */
public class ConnexionModel {
    
    static String url="jdbc:mysql://localhost:3308/isga_mini_project_db";
    static  String driver="com.mysql.jdbc.Driver";
    static String username="isga_mini_project_user";
    static String password="isga_mini_project_pass";

    public ConnexionModel() {
    }
    
     public static Connection getCon() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
 
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    
    
}
