/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.Services;

import com.ndl.pojo.DocGia;
import com.ndl.pojo.Sach;
import com.ndl.pojo.PhieuMuon;
import com.ndl.pojo.ThongKe;
import com.ndl.pojo.User;
import com.ndl.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhieuMuonService {
    public int MuonSach(ArrayList<PhieuMuon> dsMuon) throws SQLException {
        
        if(dsMuon.isEmpty()) {
            return -2;
        }
       
        int tongSoLuong = 0;

        for(PhieuMuon phieuMuon: dsMuon) {
            tongSoLuong += phieuMuon.getSoLuong();
        }

        try (Connection conn = JdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm;

            for(PhieuMuon phieuMuon: dsMuon) {
                stm = conn.prepareStatement("INSERT INTO `phieumuon` "
                        + "(MaPhieu, ID_NM, MaSach, SoLuongMuon, GiaTien, MaNV, NgayMuon)"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?)"); 

                stm.setInt(1, phieuMuon.getMaPhieu());
                stm.setInt(2, phieuMuon.getMaDG());
                stm.setInt(3, phieuMuon.getMaSach().getId());
                stm.setInt(4, phieuMuon.getSoLuong());
                stm.setFloat(5, phieuMuon.getGia());
                stm.setInt(6, phieuMuon.getMaNV());
                if(phieuMuon.getNgayMuon()!= null)
                    stm.setDate(7, phieuMuon.getNgayMuon());
                else 
                    stm.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));

                stm.executeUpdate();
            }

            conn.commit();

            return 1;
        }
    }
    public List<PhieuMuon> getListMuon() throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * "
                    + "FROM `phieumuon` "
                    + "WHERE `NgayMuon` IS NULL ");
            
            ResultSet rs = stm.executeQuery();
            
            List<PhieuMuon> listMuon = new ArrayList<>();
            while (rs.next()) {  
                Integer pm = rs.getInt("MaPhieu");
                Sach sach = SachService.getSachById(rs.getInt("MaSach"));
                DocGia dg = DangKyDocGiaService.getDocGiaById(rs.getInt("ID_NM"));
                Integer sl = rs.getInt("SoLuongMuon");
                Float gia = rs.getFloat("Gia");
                User userID = UserService.getUserById(rs.getInt("ID"));
                Date date = rs.getDate("NgayMuon");
                
                listMuon.add(new PhieuMuon(pm, dg, sach, sl, gia,userID, date));
            }
            
            return listMuon;
        }
    }
    public ThongKe sumSellByYear(Integer nam) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            ThongKe tk = null;
            
            PreparedStatement stm = conn.prepareStatement(
                    "SELECT SUM(`SoLuongMuon`) AS 'tong_so_luong', "
                            + "EXTRACT(YEAR FROM `NgayMuon`) AS 'year' "
                            + "FROM `phieumuon` "
                            + "WHERE EXTRACT(YEAR FROM `NgayMuon`) = ? ");
            
            stm.setInt(1, nam);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                tk = new ThongKe();
                tk.setTongSL(rs.getInt("tong_so_luong"));
                tk.setNam(rs.getInt("year"));
            }
            return tk;
        }
    }
    public ThongKe sumSellByGia(Integer gia) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            ThongKe tk = null;
            
            PreparedStatement stm = conn.prepareStatement(
                    "SELECT SUM(`GiaTien`) AS 'tonggia', "
                            + "FROM `phieumuon` "
                            + "WHERE `GiaTien` >0 ");
            
            stm.setInt(1, gia);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                tk = new ThongKe();
                tk.setTongSL(rs.getInt("tonggia"));
            }
            return tk;
        }
    }

    
}
