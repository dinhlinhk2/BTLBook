/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.testcase;

import com.ndl.Services.UserService;
import com.ndl.pojo.User;
import com.ndl.utils.JdbcUtils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class LoginTest {
    private static Connection conn;
    private static UserService s;
    @BeforeAll
    public static void beforeAll() {
        s = new UserService();
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
//    @Test
//    @Order(1)
//    public void testLogin() throws SQLException {
//        User user = new User();
//
//        user.setUserName("hai");
//        user.setPassWord("c4ca4238a0b923820dcc509a6f75849b");
//   
//        
//        User newUser = UserService.
//        Assertions.assertNotNull(newUser);
//        
//        Assertions.assertEquals(user.getUserName(), newUser.getUserName());
//        Assertions.assertEquals(user.getPassWord(), newUser.getPassWord());
//        
//    }  
//    @Test
//    @Order(2)
//    public void testLoginErr1() throws SQLException {
//        User user = new User();
//        
//        user.setUserName("hai");
//        user.setPassWord("cccccc4ca4238a0b923820dcc509a6f75849b");
//   
//        
//        User newUser = UserService.getUserByUserName(user.getUserName());
//        Assertions.assertNotNull(newUser);
//       
//        Assertions.assertEquals(user.getUserName(), newUser.getUserName());
//        Assertions.assertNotEquals(user.getPassWord(), newUser.getPassWord());
//        
//    } 
    
}
