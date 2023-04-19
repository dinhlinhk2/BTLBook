/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.Services;

import com.ndl.pojo.User;
import com.ndl.utils.JdbcUtils;
import com.ndl.utils.Utils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Admin
 */

public class UserService {
    
    public static boolean checkUsernameExist(String username) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT `Username` FROM `user` WHERE `Username` = ?");
            
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            
            if (rs.isBeforeFirst()) {
//                Utils.getBox("Trùng tên tài khoản!", Alert.AlertType.INFORMATION).show();
                return true;
            }
            return false;
        }
    }
    
    public static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
  
            byte[] messageDigest = md.digest(input.getBytes());
 
            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public User dangNhap(User user) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
                PreparedStatement stm = conn.prepareStatement("SELECT * FROM `user`"
                        + " WHERE `Username` = ? and `Password` = ? ");

                stm.setString(1, user.getUserName());
                stm.setString(2, getMd5(user.getPassWord()));
                
                ResultSet rs = stm.executeQuery();

                User newUser = null;
                while (rs.next()) {
                    newUser = new User();
                     
                    newUser.setId(rs.getInt("ID"));
                    newUser.setUserName(rs.getString("Username"));
                    newUser.setFirstName(rs.getString("FirstName"));
                    newUser.setLastName(rs.getString("LastName"));
                    newUser.setAddress(rs.getString("Address"));  
                    break;
                }
                
                return newUser;
        }
    }
    public boolean dangKy(User user) throws SQLException  {
        if(checkUsernameExist(user.getUserName()) == false){
            try (Connection conn = JdbcUtils.getConn()){
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO `user` "
                    + "(ID, Username, Password, FirstName, LastName, Address)"
                      + "VALUES(?, ?, ?, ?, ?, ?)");
            
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, getMd5(user.getPassWord()));
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.executeUpdate();  
//            Utils.getBox("Đăng ký tài khoản thành công!", Alert.AlertType.INFORMATION).show();
            return true;
        }
        }else{
//            Utils.getBox("Đăng ký tài khoản thất bại!", Alert.AlertType.INFORMATION).show();
            return false;
        }
    }
    public static User getUserById(Integer maUser) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM `user` WHERE `ID` = ?");
            stm.setInt(1, maUser);
            ResultSet rs = stm.executeQuery();
            
            User user = null;
            while (rs.next()) {
                user = new User();
                
                user.setId(rs.getInt("ID"));
                user.setUserName(rs.getString("Username"));
                user.setPassWord(rs.getString("Password"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setAddress(rs.getString("Address"));
                
               
                break;
            }
            
            return user;
        }
    }
    public static User getUserByUserName(String username) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM `user` WHERE `Username` = ?");
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            
            User user = null;
            while (rs.next()) {
                user = new User();
                
                user.setId(rs.getInt("ID"));
                user.setUserName(rs.getString("Username"));
                user.setPassWord(rs.getString("Password"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setAddress(rs.getString("Address"));
                
               
                break;
            }
            
            return user;
        }
    }
    public List<User> getListUser() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM user");
            
            ResultSet rs = stm.executeQuery();
            List<User> listUser = new ArrayList<>();
            
            while (rs.next()) {                
                int maUser = rs.getInt("ID");
                String username = rs.getString("UserName");
                String pass = rs.getString("Password");
                String firstname = rs.getString("FirstName");
                String lastname = rs.getString("LastName");
                String add = rs.getString("Address");
                
                
                listUser.add(new User(maUser, username,pass,firstname,lastname,add));
            }
            
            return listUser;
        }
    }
    
}
