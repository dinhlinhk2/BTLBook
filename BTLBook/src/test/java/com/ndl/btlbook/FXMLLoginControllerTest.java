/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ndl.btlbook;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Admin
 */
public class FXMLLoginControllerTest {
    
    public FXMLLoginControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class FXMLLoginController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        FXMLLoginController instance = new FXMLLoginController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dangNhapHandler method, of class FXMLLoginController.
     */
    @Test
    public void testDangNhapHandler() throws Exception {
        System.out.println("dangNhapHandler");
        ActionEvent evt = null;
        FXMLLoginController instance = new FXMLLoginController();
        instance.dangNhapHandler(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dangKyHandler method, of class FXMLLoginController.
     */
    @Test
    public void testDangKyHandler() throws Exception {
        System.out.println("dangKyHandler");
        ActionEvent evt = null;
        FXMLLoginController instance = new FXMLLoginController();
        instance.dangKyHandler(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
