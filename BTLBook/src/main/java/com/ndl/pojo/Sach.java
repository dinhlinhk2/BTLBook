/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.pojo;

/**
 *
 * @author Admin
 */
public class Sach {
    private Integer id;
    private String tenSach;
    private Integer maTL;
    private Integer namXB;
    private Integer soLuong;
    private Float gia;
    
    public Sach(){ 
        
    }
    public Sach(Integer id, String tenSach, Integer maTL, Integer namXB, Integer soLuong, Float gia){
        this.id = id;
        this.tenSach = tenSach;
        this.maTL = maTL;
        this.namXB = namXB;
        this.soLuong = soLuong;
        this.gia = gia;
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
     * @return the tenSach
     */
    public String getTenSach() {
        return tenSach;
    }

    /**
     * @param tenSach the tenSach to set
     */
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    /**
     * @return the maTL
     */
    public Integer getMaTL() {
        return maTL;
    }

    /**
     * @param maTL the maTL to set
     */
    public void setMaTL(Integer maTL) {
        this.maTL = maTL;
    }


    /**
     * @return the namXB
     */
    public Integer getNamXB() {
        return namXB;
    }

    /**
     * @param namXB the namXB to set
     */
    public void setNamXB(Integer namXB) {
        this.namXB = namXB;
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
    
}
