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
public class TacGia {
    private Integer maTG;
    private String tenTG;
    private Date ngaySinh;
    
    public TacGia(){
        
    }
    
    public TacGia(Integer maTG, String tenTG, Date ngaySinh){
        this.maTG = maTG;
        this.tenTG = tenTG;
        this.ngaySinh = ngaySinh;
    }

    @Override
    public String toString() {
        return this.tenTG; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    

    /**
     * @return the maTG
     */
    public Integer getMaTG() {
        return maTG;
    }

    /**
     * @param maTG the maTG to set
     */
    public void setMaTG(Integer maTG) {
        this.maTG = maTG;
    }

    /**
     * @return the tenTG
     */
    public String getTenTG() {
        return tenTG;
    }

    /**
     * @param tenTG the tenTG to set
     */
    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    
    
}
