/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.Services;

import com.ndl.pojo.Sach;
import com.ndl.utils.JdbcUtils;
import com.ndl.utils.Utils;
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
public class SachService {

    private List<Sach> addListSach(PreparedStatement stm) throws SQLException {
        ResultSet rs = stm.executeQuery();

        List<Sach> listSach = new ArrayList<>();
            while (rs.next()) {

                Integer maSach = rs.getInt("MaSach");
                String tenSach = rs.getString("TenSach");
                Integer maTL = rs.getInt("MaTheLoai");
                Integer namXB = rs.getInt("NamXB");
                Integer sl = rs.getInt("SoLuong");
                Float gia = rs.getFloat("GiaTien");
                Integer maKS = rs.getInt("MaKeSach");
                Integer maTG = rs.getInt("MaTacGia");

                listSach.add(new Sach(maSach, tenSach, maTL, namXB, sl, gia, maKS, maTG));
            }
        return listSach;
    }

    public List<Sach> getListSach(String kw, Integer loaiTimKiem) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm;
            String sql = "SELECT * FROM `sach` ";
            // OR  OR 
            if (kw == null) {
                kw = "";
            }

            if (loaiTimKiem != null) {
                switch (loaiTimKiem) {
                    case 0:
                        sql += "WHERE TenSach like concat('%',?,'%')";
                        stm = conn.prepareStatement(sql);
                        stm.setString(1, kw);
                        return addListSach(stm);
                    case 1:
                        sql += "WHERE MaTheLoai like concat('%',?,'%')";
                        stm = conn.prepareStatement(sql);
                        stm.setString(1, kw);
                        return addListSach(stm);
                    case 2:
                        sql += "WHERE NamXB like concat('%',?,'%')";
                        stm = conn.prepareStatement(sql);
                        stm.setString(1, kw);
                        return addListSach(stm);
                    default:
                        break;
                }
            }

            stm = conn.prepareStatement(sql);

            return addListSach(stm);
        }
    }

    public static boolean checkSachExist(String tenSach) throws SQLException {
        if (tenSach.isBlank() == false) {
            try (Connection conn = JdbcUtils.getConn()) {
                PreparedStatement stm = conn.prepareStatement("SELECT TenSach FROM sach WHERE TenSach = ?");

                stm.setString(1, tenSach);
                ResultSet rs = stm.executeQuery();

                if (rs.isBeforeFirst()) {
                    Utils.getBox("Trùng tên sách!", Alert.AlertType.INFORMATION).show();
                    return true;
                }

                return false;
            }
        }
        return false;
    }

    public static boolean checkSachExistById(Integer maSach) throws SQLException {
        if (maSach != null) {
            try (Connection conn = JdbcUtils.getConn()) {
                PreparedStatement stm = conn.prepareStatement("SELECT MaSach FROM sach WHERE MaSach = ?");

                stm.setInt(1, maSach);
                ResultSet rs = stm.executeQuery();

                if (rs.isBeforeFirst()) {
                    Utils.getBox("Trùng mã sách!", Alert.AlertType.INFORMATION).show();
                    return true;
                }

                return false;
            }
        }
        return false;
    }

    public static Sach getSachById(int maSach) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM `sach` WHERE MaSach = ?");
            stm.setInt(1, maSach);
            ResultSet rs = stm.executeQuery();

            Sach sach = null;
            while (rs.next()) {
                sach = new Sach();

                sach.setId(rs.getInt("MaSach"));
                sach.setTenSach(rs.getString("TenSach"));
                sach.setMaTL(rs.getInt("MaTheLoai"));
                sach.setNamXB(rs.getInt("NamXB"));
                sach.setSoLuong(rs.getInt("SoLuong"));
                sach.setGia(rs.getFloat("Gia"));
                sach.setMaKS(rs.getInt("MaKeSach"));
                sach.setMaTG(rs.getInt("MaTacGia"));

                break;
            }

            return sach;
        }
    }

    public boolean themSach(Sach sach) throws SQLException {
        if (sach.getId() != null && checkSachExist(sach.getTenSach()) == false) {
            try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);

                PreparedStatement stm = conn.prepareStatement("INSERT INTO `sach` "
                        + "(MaSach, TenSach, MaTheLoai, NamXB, SoLuong, GiaTien, MaKeSach, MaTacGia)"
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"); 

                stm.setInt(1, sach.getId());
                stm.setString(2, sach.getTenSach());
                stm.setInt(3, sach.getMaTL());
                stm.setInt(4, sach.getNamXB());
                stm.setInt(5, sach.getSoLuong());
                stm.setFloat(6, sach.getGia());
                stm.setInt(7, sach.getMaKS());
                stm.setInt(8, sach.getMaTG());
                stm.executeUpdate();
                conn.commit();

                return true;
            }
        }
        return false;
    }

    public boolean suaSach(Sach sach) throws SQLException {
        if (sach.getId() != 0 && checkSachExist(sach.getTenSach()) == false) {
            try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);

                PreparedStatement stm = conn.prepareStatement("UPDATE `sach` "
                                + "SET `TenSach` = ?, "
                                + "`MaTheLoai` = ?, "
                                + "`NamXB` = ?, "
                                + "`SoLuong` = ?, "
                                + "`GiaTien` = ?, "
                                + "`MaKeSach` = ?, "
                                +"`MaTacGia` = ? "
                                + "WHERE `MaSach` = ?");

                stm.setString(1, sach.getTenSach());
                stm.setInt(2, sach.getMaTL());
                stm.setInt(3, sach.getNamXB());
                stm.setInt(4, sach.getSoLuong());
                stm.setFloat(5, sach.getGia());
                stm.setInt(6, sach.getMaKS());
                stm.setInt(7, sach.getMaTG());
                stm.setInt(8, sach.getId());  
                
                int affectedRow = stm.executeUpdate();
                conn.commit();
                return affectedRow != 0;
            }
        }
        return false;
    }

    public boolean xoaSach(Integer maSach) throws SQLException {
        if (maSach != null) {
            try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);

                PreparedStatement stm = conn.prepareStatement("DELETE FROM `sach` WHERE MaSach = ?");

                stm.setInt(1, maSach);

                int rowAffected = stm.executeUpdate();
                conn.commit();

                return rowAffected != 0;
            }
        }
        return false;
    }

}
