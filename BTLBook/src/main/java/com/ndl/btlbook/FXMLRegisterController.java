/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndl.btlbook;

import com.ndl.pojo.User;
import com.ndl.Services.UserService;
import com.ndl.utils.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXMLRegisterController implements Initializable {

    
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassWord;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtAddress;    
    @FXML
    private PasswordField txtNhapLaiMatKhau;
    @FXML
    private Button btnDangKy;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void dangKy(ActionEvent event) throws SQLException, IOException {
        Window owner = btnDangKy.getScene().getWindow();

        User user = new User();
        
        user.setId(0);
        user.setUserName(txtUserName.getText());
        user.setPassWord(txtPassWord.getText());
        user.setFirstName(txtFirstName.getText());
        user.setLastName(txtLastName.getText());
        user.setAddress(txtAddress.getText());
        UserService u = new UserService();
        if (txtUserName.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập tên UserName");
            return;
        }
        
        if (txtLastName.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập họ của bạn");
            return;
        }

        if (txtFirstName.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập tên của bạn");
            return;
        }
        

        if (txtAddress.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập địa chỉ của bạn");
            return;
        }

        if (txtPassWord.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Nhập mật khẩu");
            return;
        }
          
        if (!txtPassWord.getText().equals(txtNhapLaiMatKhau.getText()) ) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!","Mật khẩu không khớp");
            return;
        }
        
        u.dangKy(user);
           

    }
    
}
