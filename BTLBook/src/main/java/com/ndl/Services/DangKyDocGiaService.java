/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.Services;

import com.ndl.pojo.DocGia;
import com.ndl.utils.JdbcUtils;
import com.ndl.utils.Utils;
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
public class DangKyDocGiaService {
    public static int docgiaRegister(DocGia dg) throws SQLException {
        
        
        if (Utils.isNullOrEmpty(dg.getTenDG())){
            return -1;
        }
        
        if (!Utils.isNullOrEmpty(dg.getNamSinh().toString())){
        } else {
            return -2;
        }
        
        if (Utils.isNullOrEmpty(dg.getGioiTinh())){
            return -3;
        }
        if (Utils.isNullOrEmpty(dg.getDiaChi())){
            return -3;
        }
        if (Utils.isNullOrEmpty(dg.getSDT())){
            return -3;
        }
        
        
        if (!dg.getSDT().matches("(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}")) {
            return -1;
        }
        if (Utils.isNullOrEmpty(dg.getDoiTuong().toString())){
            return -3;
        }
        
        if (Utils.isNullOrEmpty(dg.getBoPhan())){
            return -1;
        }
        
        try(Connection connect = JdbcUtils.getConn()) {
            String sql = "INSERT INTO `docgia` "
                    + "(ID_NM, TenNguoiMuon, NamSinh, GioiTinh, DiaChi, SDT, DoiTuong, BoPhan)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connect.prepareCall(sql);
            statement.setInt(1, dg.getId());
            statement.setString(2, dg.getTenDG());
            statement.setDate(3, dg.getNamSinh());
            statement.setString(4, dg.getGioiTinh());
            statement.setString(5, dg.getDiaChi());
            statement.setString(6, dg.getSDT());
            statement.setInt(7, dg.getDoiTuong());
            statement.setString(8, dg.getBoPhan());
            return statement.executeUpdate();
        }
    }
    
    public static int CheckInValid(DocGia dg) {
        
        if (Utils.isNullOrEmpty(dg.getTenDG())){
            return 1;
        }
        
        if (!Utils.isNullOrEmpty(dg.getNamSinh().toString())){
        } else {
            return 2;
        }
        
        if (Utils.isNullOrEmpty(dg.getGioiTinh())){
            return 3;
        }
        if (Utils.isNullOrEmpty(dg.getDiaChi())){
            return 4;
        }
        if (Utils.isNullOrEmpty(dg.getSDT())){
            return 5;
        }
        
        
        if (!dg.getSDT().matches("(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}")) {
            return 6;
        }
        if (Utils.isNullOrEmpty(dg.getDoiTuong().toString())){
            return 7;
        }
        
        if (Utils.isNullOrEmpty(dg.getBoPhan())){
            return 8;
        }
        
        return 0;
    }
    public List<DocGia> getListDocGia() throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM docgia");
            
            ResultSet rs = stm.executeQuery();
            List<DocGia> listDG = new ArrayList<>();
            
            while (rs.next()) {                
                int maDG = rs.getInt("ID_NM");
                String tenDG = rs.getString("TenNguoiMuon");
                Date ngaySinh = rs.getDate("NamSinh");
                String gioiTinh = rs.getString("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String sdt = rs.getString("SDT");
                Integer dt = rs.getInt("DoiTuong");
                String bp = rs.getString("BoPhan");
                
                
                listDG.add(new DocGia(maDG,tenDG,ngaySinh,gioiTinh,diaChi,sdt,dt,bp));
            }
            
            return listDG;
        }
    }
    public static DocGia getDocGiaById(Integer maDG) throws SQLException{
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM `docgia` WHERE `ID_NM` = ?");
            stm.setInt(1, maDG);
            ResultSet rs = stm.executeQuery();
            
            DocGia dg= null;
            while (rs.next()) {
                dg = new DocGia();
                dg.setId(rs.getInt("ID_NM"));
                dg.setTenDG(rs.getString("TenNguoiMuon"));
                break;
            }
            
            return dg;
        }
        
    }
    
}
