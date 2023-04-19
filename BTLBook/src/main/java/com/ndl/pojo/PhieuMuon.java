/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.pojo;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class PhieuMuon {
    private Integer maPhieu;
    private Integer maDG;
    private Sach maSach;
    private Integer soLuong;
    private Float gia;
    private Integer maNV;
    private Date ngayMuon;
    
    public PhieuMuon(){
        
    }

    @Override
    public String toString() {
        return maSach.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    

    public PhieuMuon(Integer pm, DocGia dg, Sach sach, Integer sl, Float gia1, User userID, Date date){
        
    }
    
    public PhieuMuon(Integer maPhieu, Integer maDG, Sach maSach, Integer soLuong, Float gia, Integer maNV, Date ngayMuon){
        this.maPhieu = maPhieu;
        this.maDG = maDG;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.gia = gia;
        this.maNV = maNV;
        this.ngayMuon = ngayMuon;
    }

    public PhieuMuon(PhieuMuon pm, DocGia dg, Sach sach, Integer sl, Float gia, User userID, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * @return the maPhieu
     */
    public Integer getMaPhieu() {
        return maPhieu;
    }

    /**
     * @param maPhieu the maPhieu to set
     */
    public void setMaPhieu(Integer maPhieu) {
        this.maPhieu = maPhieu;
    }

    /**
     * @return the maDG
     */
    public Integer getMaDG() {
        return maDG;
    }

    /**
     * @param maDG the maDG to set
     */
    public void setMaDG(Integer maDG) {
        this.maDG = maDG;
    }

    /**
     * @return the maSach
     */
    public Sach getMaSach() {
        return maSach;
    }

    /**
     * @param maSach the maSach to set
     */
    public void setMaSach(Sach maSach) {
        this.maSach = maSach;
    }



    /**
     * @return the soLuong
     */
    public Integer getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the gia
     */
    public Float getGia() {
        return gia;
    }

    /**
     * @param gia the gia to set
     */
    public void setGia(Float gia) {
        this.gia = gia;
    }

    /**
     * @return the maNV
     */
    public Integer getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the ngayMuon
     */
    public Date getNgayMuon() {
        return ngayMuon;
    }

    /**
     * @param ngayMuon the ngayMuon to set
     */
    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }
            
    
}
