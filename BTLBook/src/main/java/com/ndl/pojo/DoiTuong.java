/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.pojo;

/**
 *
 * @author Admin
 */
public class DoiTuong {
    private Integer id;
    private String tenDT;
    
    public DoiTuong(){
        
    }
    public DoiTuong(Integer id, String tenDT){
        this.id = id;
        this.tenDT = tenDT;
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
     * @return the tenDT
     */
    public String getTenDT() {
        return tenDT;
    }

    /**
     * @param tenDT the tenDT to set
     */
    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    @Override
    public String toString() {
        return this.tenDT; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}
