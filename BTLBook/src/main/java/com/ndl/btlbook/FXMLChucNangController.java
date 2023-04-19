/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndl.btlbook;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXMLChucNangController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void quanLySachHandler(ActionEvent evt) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLSach.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quan Ly Sach");
        stage.show();
        
    }
    public void muonSachHandler(ActionEvent evt) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLPhieuMuon.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Muon Sach");
        stage.show();
        
    }
    public void dkDGHandler(ActionEvent evt) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLDocGia.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Doc Gia");
        stage.show();
        
    }
}
