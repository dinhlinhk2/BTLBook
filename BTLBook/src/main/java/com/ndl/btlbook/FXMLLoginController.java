/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndl.btlbook;

import com.ndl.Services.UserService;
import com.ndl.pojo.User;
import com.ndl.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXMLLoginController implements Initializable {

    @FXML private TextField txtuserName;
    @FXML private PasswordField txtpass;
    @FXML private Button btnLogin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void dangNhapHandler(ActionEvent evt) throws IOException, SQLException {
        Window owner = btnLogin.getScene().getWindow();

        User user = new User();
        user.setUserName(txtuserName.getText());
        user.setPassWord(txtpass.getText());


        if (txtuserName.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!",
                "Chưa nhập UserName");
            return;
        }

        if (txtpass.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!",
                "Chưa nhập mật khẩu");
            return;
        }

        UserService us = new UserService();
        User newUser = us.dangNhap(user);

        if(newUser != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLChucNang.fxml"));
            Parent root = fxmlLoader.load();

            FXMLChucNangController chucnang = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Chức Năng");
            stage.show();
        }

        else {
            Utils.getBox("Thong tin dang nhap khong hop le!", Alert.AlertType.WARNING).show();
        }
    }
    public void dangKyHandler(ActionEvent evt) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLRegister.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Đăng ký");
        stage.show();
    }

        }
