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
public class DocGia {
    
    private Integer id;
    private String tenDG;
    private Date namSinh;
    private String gioiTinh;
    private String diaChi;
    private String SDT;
    private Integer doiTuong;
    private String boPhan;
    
    
    public DocGia(){
        
    }
    public DocGia(Integer id, String tenDG, Date namSinh, String gioiTinh,
            String diaChi, String SDT, Integer doiTuong, String boPhan) {
        this.id = id;
        this.tenDG = tenDG;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.doiTuong = doiTuong;
        this.boPhan = boPhan;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the tenDG
     */
    public String getTenDG() {
        return tenDG;
    }

    /**
     * @param tenDG the tenDG to set
     */
    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    /**
     * @return the namSinh
     */
    public Date getNamSinh() {
        return namSinh;
    }

    /**
     * @param namSinh the namSinh to set
     */
    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the SDT
     */
    public String getSDT() {
        return SDT;
    }

    /**
     * @param SDT the SDT to set
     */
    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    /**
     * @return the doiTuong
     */
    public Integer getDoiTuong() {
        return doiTuong;
    }

    /**
     * @param doiTuong the doiTuong to set
     */
    public void setDoiTuong(Integer doiTuong) {
        this.doiTuong = doiTuong;
    }

    /**
     * @return the boPhan
     */
    public String getBoPhan() {
        return boPhan;
    }

    /**
     * @param boPhan the boPhan to set
     */
    public void setBoPhan(String boPhan) {
        this.boPhan = boPhan;
    }
    
    
}
