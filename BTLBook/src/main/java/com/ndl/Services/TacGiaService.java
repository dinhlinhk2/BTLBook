/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.Services;

import com.ndl.pojo.TacGia;
import com.ndl.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TacGiaService {
    public List<TacGia> getListTacGia() throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM tacgia");
            
            ResultSet rs = stm.executeQuery();
            List<TacGia> listTG = new ArrayList<>();
            
            while (rs.next()) {                
                int maTG = rs.getInt("MaTacGia");
                String nameTG = rs.getString("TenTacGia");
                Date ngaySinh = rs.getDate("NgaySinh");
                
                listTG.add(new TacGia(maTG, nameTG, ngaySinh));
            }
            
            return listTG;
        }
    }
    public static TacGia getTacGiaById(Integer maTG) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM `tacgia` WHERE `MaTacGia` = ?");
            stm.setInt(1, maTG);
            ResultSet rs = stm.executeQuery();
            
            TacGia tg = null;
            while (rs.next()) {
                tg = new TacGia();
                tg.setMaTG(rs.getInt("MaTacGia"));
                tg.setTenTG(rs.getString("TenTacGia"));
                tg.setNgaySinh(rs.getDate("NgaySinh"));
                break;
            }
            
            return tg;
        }
        
    }
}
