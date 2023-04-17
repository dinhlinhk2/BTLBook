/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.Services;


import com.ndl.pojo.DoiTuong;
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
public class DoiTuongService {
    public List<DoiTuong> getListDoiTuong() throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM doituong");
            
            ResultSet rs = stm.executeQuery();
            List<DoiTuong> listDT = new ArrayList<>();
            
            while (rs.next()) {                
                int maDT = rs.getInt("MaDoiTuong");
                String tenDT = rs.getString("TenDoiTuong");
                
                listDT.add(new DoiTuong(maDT,tenDT));
            }
            
            return listDT;
        }
    }
    public static DoiTuong getDoiTuongById(Integer maDT) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM `doituong` WHERE `MaDoiTuong` = ?");
            stm.setInt(1, maDT);
            ResultSet rs = stm.executeQuery();
            
            DoiTuong dt = null;
            while (rs.next()) {
                dt = new DoiTuong();
                dt.setId(rs.getInt("MaDoiTuong"));
                dt.setTenDT(rs.getString("TenDoiTuong"));
                break;
            }
            
            return dt;
        }
        
    }
    
}
