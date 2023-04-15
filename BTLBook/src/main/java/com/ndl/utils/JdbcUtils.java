/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Admin
 */
public class JdbcUtils {
    static {
        try {
            Class.forName("com.ndl.cj.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JdbcUtils.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
     public static Connection getConn() throws SQLException {
        // B2 Thiet lap ket noi
        return DriverManager.getConnection("jdbc:mysql://localhost/dbbook", "root", "Admin@123");
    }
    
    
}
