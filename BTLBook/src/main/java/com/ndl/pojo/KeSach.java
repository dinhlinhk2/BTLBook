/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.pojo;

/**
 *
 * @author Admin
 */
public class KeSach {
    private Integer maKe;
    private String tenKe;

    public KeSach(){
        
    }
    
    public KeSach(Integer maKe, String tenKe){
        this.maKe = maKe;
        this.tenKe = tenKe;
    }

    @Override
    public String toString() {
        return this.tenKe; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
    /**
     * @return the maKe
     */
    public Integer getMaKe() {
        return maKe;
    }

    /**
     * @param maKe the maKe to set
     */
    public void setMaKe(Integer maKe) {
        this.maKe = maKe;
    }

    /**
     * @return the tenKe
     */
    public String getTenKe() {
        return tenKe;
    }

    /**
     * @param tenKe the tenKe to set
     */
    public void setTenKe(String tenKe) {
        this.tenKe = tenKe;
    }
}
