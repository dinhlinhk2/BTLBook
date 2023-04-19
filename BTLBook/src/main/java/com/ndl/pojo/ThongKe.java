/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ndl.pojo;

/**
 *
 * @author Admin
 */
public class ThongKe {
    private Integer nam;
    private Integer tongSL;
    
    public ThongKe(){
        
    }
    
    public ThongKe(Integer nam, Integer tongSL){
        this.nam = nam;
        this.tongSL = tongSL;
    }


    /**
     * @return the nam
     */
    public Integer getNam() {
        return nam;
    }

    /**
     * @param nam the nam to set
     */
    public void setNam(Integer nam) {
        this.nam = nam;
    }

    /**
     * @return the tongSL
     */
    public Integer getTongSL() {
        return tongSL;
    }

    /**
     * @param tongSL the tongSL to set
     */
    public void setTongSL(Integer tongSL) {
        this.tongSL = tongSL;
    }
    
}
