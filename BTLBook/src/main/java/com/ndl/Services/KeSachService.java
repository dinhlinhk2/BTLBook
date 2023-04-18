/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.Services;

import com.ndl.pojo.KeSach;
import com.ndl.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KeSachService {
    public List<KeSach> getListKeSach() throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM kesach");
            
            ResultSet rs = stm.executeQuery();
            List<KeSach> listKS = new ArrayList<>();
            
            while (rs.next()) {                
                int maKS = rs.getInt("MaKeSach");
                String nameKS = rs.getString("TenKe");
                
                listKS.add(new KeSach(maKS, nameKS));
            }
            
            return listKS;
        }
    }
    public static KeSach getKeSachById(Integer maKS) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM `kesach` WHERE `MaKeSach` = ?");
            stm.setInt(1, maKS);
            ResultSet rs = stm.executeQuery();
            
            KeSach ks = null;
            while (rs.next()) {
                ks = new KeSach();
                ks.setMaKe(rs.getInt("MaKeSach"));
                ks.setTenKe(rs.getString("TenKe"));
                break;
            }
            
            return ks;
        }
        
    }
    
}
