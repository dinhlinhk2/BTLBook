/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.pojo;

/**
 *
 * @author Admin
 */
public class TheLoai {
    private Integer id;
    private String tenTL;
    
    public TheLoai(){
        
    }
    public TheLoai(Integer id, String tenTL){
        this.id = id;
        this.tenTL = tenTL;
    }

    @Override
    public String toString() {
        return this.tenTL; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
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
     * @return the tenTL
     */
    public String getTenTL() {
        return tenTL;
    }

    /**
     * @param tenTL the tenTL to set
     */
    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }
}
