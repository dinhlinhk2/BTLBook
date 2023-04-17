/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.Services;

import com.ndl.pojo.TheLoai;
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
public class TheLoaiService {
    public List<TheLoai> getListTheLoai() throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM theloai");
            
            ResultSet rs = stm.executeQuery();
            List<TheLoai> listTL = new ArrayList<>();
            
            while (rs.next()) {                
                int maTL = rs.getInt("MaTheLoai");
                String nameTL = rs.getString("TenTheLoai");
                
                listTL.add(new TheLoai(maTL, nameTL));
            }
            
            return listTL;
        }
    }
    public static TheLoai getTheLoaiById(Integer maTL) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM `theloai` WHERE `MaTheLoai` = ?");
            stm.setInt(1, maTL);
            ResultSet rs = stm.executeQuery();
            
            TheLoai tl = null;
            while (rs.next()) {
                tl = new TheLoai();
                tl.setId(rs.getInt("MaSach"));
                tl.setTenTL(rs.getString("TenTheLoai"));
                break;
            }
            
            return tl;
        }
        
    }
    
}
