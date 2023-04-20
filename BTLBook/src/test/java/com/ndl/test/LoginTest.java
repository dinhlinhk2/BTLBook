/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.test;


import com.ndl.Services.UserService;
import com.ndl.pojo.TheLoai;
import com.ndl.Services.TheLoaiService;
import com.ndl.pojo.User;
import com.ndl.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    private static TheLoaiService tls;

    @BeforeAll
    public static void beforeAll() {
        tls = new TheLoaiService();
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

    @Test
    @Order(1)
    public void testLogin() throws SQLException {
        User user = new User();

        user.setUserName("admin");
        user.setPassWord("c4ca4238a0b923820dcc509a6f75849b");

        User newUser = UserService.getUserByUserName(user.getUserName());
        Assertions.assertNotNull(newUser);

        Assertions.assertEquals(user.getUserName(), newUser.getUserName());
        Assertions.assertEquals(user.getPassWord(), newUser.getPassWord());

    }

    @Test
    @Order(2)
    public void testLoginErr1() throws SQLException {
        User user = new User();

        user.setUserName("admin");
        user.setPassWord("cccccc4ca4238a0b923820dcc509a6f75849b");

        User newUser = UserService.getUserByUserName(user.getUserName());
        Assertions.assertNotNull(newUser);

        Assertions.assertEquals(user.getUserName(), newUser.getUserName());
        Assertions.assertNotEquals(user.getPassWord(), newUser.getPassWord());

    }
//    @Test
//    @Order(3)
//    public void testTheLoai() throws SQLException{
//        
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery("SELECT * FROM theloai");
//        List<String> kq = new ArrayList<>();
//        
//        while(rs.next()){
//            String name = rs.getString("TenTheLoai");
//            kq.add(name);
//        
//        }
//        Set<String> kq2 = new HashSet<>(kq);
//        Assertions.assertNotEquals(kq.size(), kq2.size());
//        
//    }

//    @Test
//    public void testTL() throws SQLException {
//        List<TheLoai> tl = tls.getListTheLoai();
//        long r = tl.stream().filter(c -> c.getTenTL() == null).count();
//        Assertions.assertTrue(r == 0);
//    }
//     @Test
//    public void testNameUnique() {
//        try {
//            List<TheLoai> cates = tls.getListTheLoai();
//            
//            List<String> names = cates.stream().flatMap(c -> Stream.of(c.getTenTL())).collect(Collectors.toList());
//            Set<String> testNames = new HashSet<>(names);
//            Assertions.assertEquals(names.size(), testNames.size());
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
//    @Test
//    public void testCheckUserNameExist() throws SQLException {
//        Assertions.assertTrue(UserService.checkUsernameExist("admin"));
//    }
//    @Test
//    @Order(1)
//    public void testRegister() throws SQLException {
//        User user = new User();
//        
//        user.setId(0);
//        user.setUserName("nguyenanhdo2");
//        user.setPassWord("c4ca4238a0b923820dcc509a6f75849b");
//        user.setFirstName("hai");
//        user.setLastName("Phan");
//        user.setAddress("hcm");
//        
//        
//        Assertions.assertTrue(s.dangKy(user));
//        
//        User newUser = UserService.getUserByUserName(user.getUserName());
//        Assertions.assertNotNull(newUser);
//        Assertions.assertNotEquals(user.getUserName(), newUser.getUserName());
//    }  
    @Test
    @Order(1)
    public void testRegister() throws SQLException {
        User user = new User();
        
        user.setId(0);
        user.setUserName("nguyenanhdo2");
        user.setPassWord("c4ca4238a0b923820dcc509a6f75849b");
        user.setFirstName("hai");
        user.setLastName("Phan");
        user.setAddress("hcm");
        
        
        Assertions.assertTrue(s.dangKy(user));
        
//        User newUser = UserServices.getUserByUserName(user.getUserName());
//        Assertions.assertNotNull(newUser);
//        Assertions.assertNotEquals(user.getUserName(), newUser.getUserName());
    }  

    private static class UserTest {

        public UserTest() {
        }
    }

}
