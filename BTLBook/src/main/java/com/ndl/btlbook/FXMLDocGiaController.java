/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndl.btlbook;

import com.ndl.Services.DangKyDocGiaService;
import com.ndl.Services.DoiTuongService;
import com.ndl.pojo.DocGia;
import com.ndl.pojo.DoiTuong;
import com.ndl.utils.Utils;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXMLDocGiaController implements Initializable {
    
    @FXML private TextField txtId;
    @FXML private TextField txttenDG;
    @FXML private TextField txtnamSinh;
    @FXML private RadioButton rdNam;
    @FXML private RadioButton rdNu;
    @FXML private TextField txtDiaChi;
    @FXML private TextField txtSDT;
    @FXML private ComboBox<DoiTuong> cbDoiTuong;
    @FXML private TextField txtboPhan;
    @FXML private Button btnDangKy;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadComboBoxDoiTuong();
        this.txtId.setEditable(false);
    }   
    private void loadComboBoxDoiTuong() {
        DoiTuongService s = new DoiTuongService();
        try {
            this.cbDoiTuong.setItems(FXCollections.observableArrayList(s.getListDoiTuong()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }
    public void dkDocGiaHandler(ActionEvent event) throws SQLException {
        Window owner = btnDangKy.getScene().getWindow();
        
        DocGia dg = new DocGia();
        
        dg.setId(0);
        dg.setTenDG(txttenDG.getText());
        dg.setNamSinh(Date.valueOf(txtnamSinh.getText()));
        dg.setGioiTinh(chooseGT());
        dg.setDiaChi(txtDiaChi.getText());
        dg.setSDT(txtSDT.getText());
        dg.setBoPhan(txtboPhan.getText());
        
        
        if (Utils.isNullOrEmpty(txttenDG.getText())){
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi", "Vui lòng nhập Tên.");
            return;
        }
        
        if (Utils.isNullOrEmpty(txtnamSinh.getText())){
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi", "Vui lòng nhập năm sinh.");
            return;
        }
        if ((!rdNam.isSelected()&&!rdNu.isSelected())) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa chọn giới tính của bạn");
            return;
        }
        
        if (Utils.isNullOrEmpty(txtDiaChi.getText())){
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi", "Vui lòng nhập địa chỉ.");
            return;
        }
        if (Utils.isNullOrEmpty(txtSDT.getText())){
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi", "Vui lòng nhập Số điện thoại.");
            return;
        }
        if (!txtSDT.getText().matches("(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}")) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi", "Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            return;
        }
        if (cbDoiTuong.getValue() == null) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập đối tượng");
            return;
        } else {
            dg.setDoiTuong(cbDoiTuong.getSelectionModel().getSelectedItem().getId());
        }
        
        if (Utils.isNullOrEmpty(txtboPhan.getText())){
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi", "Vui lòng nhập bộ phận");
            return;
        }
        if (DangKyDocGiaService.docgiaRegister(dg) > 0) {
            Utils.getBox("Dang ky thanh cong!", Alert.AlertType.INFORMATION).show();
        } else {
            Utils.getBox("Da co loi xay ra!", Alert.AlertType.WARNING).show();
        }

    }
    private String chooseGT(){
        if(rdNam.isSelected()){
            return rdNam.getText();
        }else
            return rdNu.getText();
    }
    
}
