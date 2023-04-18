/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndl.btlbook;

import com.ndl.Services.SachService;
import com.ndl.Services.TheLoaiService;
import com.ndl.pojo.KeSach;
import com.ndl.Services.KeSachService;
import com.ndl.pojo.Sach;
import com.ndl.pojo.TacGia;
import com.ndl.Services.TacGiaService;
import com.ndl.pojo.TheLoai;
import com.ndl.utils.Utils;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXMLSachController implements Initializable {

    @FXML
    private TextField txtMaSach;
    @FXML
    private TextField txtTenSach;
    @FXML
    private ComboBox<TheLoai> cbTheLoai;
    @FXML
    private TextField txtNamXB;
    @FXML
    private TextField txtSL;
    @FXML
    private TextField txtGia;
    @FXML
    private ComboBox<KeSach> cbKeSach;
    @FXML 
    private ComboBox<TacGia> cbTacGia;
    @FXML
    private Button btnThem;

    @FXML
    private Button btnLamMoi;
    @FXML
    private ComboBox cbTimKiem;
    @FXML
    private TextField txtTimKiem;
    @FXML
    private TableView<Sach> tbSach;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loadComboBoxTheLoai();
        this.loadComboBoxKeSach();
        this.loadComboBoxTacGia();
        this.btnLamMoi.setOnAction(e -> {
            try {
                refresh();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.loadTableSach();
        try {
            this.reloadTableSach(null, null);

//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tbSach.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                this.txtMaSach.setText(Integer.toString(tbSach.getSelectionModel().getSelectedItem().getId()));
                this.txtTenSach.setText(tbSach.getSelectionModel().getSelectedItem().getTenSach());
                int maTL = tbSach.getSelectionModel().getSelectedItem().getMaTL();
                try {
                    TheLoai tl = TheLoaiService.getTheLoaiById(maTL);
                    this.cbTheLoai.setValue(tl);
                } catch (SQLException e) {
                }
                this.txtNamXB.setText(Integer.toString(tbSach.getSelectionModel().getSelectedItem().getNamXB()));
                this.txtSL.setText(Integer.toString(tbSach.getSelectionModel().getSelectedItem().getSoLuong()));
                this.txtGia.setText(Float.toString(tbSach.getSelectionModel().getSelectedItem().getGia()));
                int maKe = tbSach.getSelectionModel().getSelectedItem().getMaKS();
                try {
                    KeSach ks = KeSachService.getKeSachById(maKe);
                    this.cbKeSach.setValue(ks);
                } catch (SQLException e) {
                }
                int maTG = tbSach.getSelectionModel().getSelectedItem().getMaTG();
                try {
                    TacGia tg = TacGiaService.getTacGiaById(maTG);
                    this.cbTacGia.setValue(tg);
                } catch (SQLException e) {
                }

            }
        });
        this.txtMaSach.setEditable(false);
        this.txtGia.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtGia.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        txtTimKiem.setPromptText("Tìm kiếm");
        this.txtTimKiem.textProperty().addListener((evt) -> {
            try {
                this.reloadTableSach(this.txtTimKiem.getText(), this.cbTimKiem.getSelectionModel().getSelectedIndex());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.cbTimKiem.getItems().addAll("Theo ten sach", "Theo the loai", "Theo nam XB");
        this.cbTimKiem.getSelectionModel().select(0);
        this.cbTimKiem.valueProperty().addListener((evt) -> {
            try {
                this.reloadTableSach(this.txtTimKiem.getText(), this.cbTimKiem.getSelectionModel().getSelectedIndex());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private Integer parseIntOrNull(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Float parseFloatOrNull(String value) {
        try {
            return Float.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void refresh() throws SQLException {
        this.reloadTableSach(null, null);
        this.txtMaSach.clear();
        this.txtTenSach.clear();
        this.cbTheLoai.setValue(null);
        this.txtNamXB.clear();
        this.txtSL.clear();
        this.txtGia.clear();
        this.cbKeSach.setValue(null);
        this.cbTacGia.setValue(null);
        this.cbTimKiem.getSelectionModel().select(0);
        this.txtTimKiem.clear();
    }

    private void reloadTableSach(String kw, Integer loaiTimKiem) throws SQLException {
        SachService s = new SachService();
        try {
            this.tbSach.setItems(FXCollections.observableList(s.getListSach(kw, loaiTimKiem)));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadComboBoxTheLoai() {
        TheLoaiService s = new TheLoaiService();
        try {
            this.cbTheLoai.setItems(FXCollections.observableArrayList(s.getListTheLoai()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    private void loadComboBoxKeSach() {
        KeSachService s = new KeSachService();
        try {
            this.cbKeSach.setItems(FXCollections.observableArrayList(s.getListKeSach()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    private void loadComboBoxTacGia() {
        TacGiaService s = new TacGiaService();
        try {
            this.cbTacGia.setItems(FXCollections.observableArrayList(s.getListTacGia()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void loadTableSach() {
        TableColumn col1 = new TableColumn("Ma Sach");
        col1.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn col2 = new TableColumn("Ten Sach");
        col2.setCellValueFactory(new PropertyValueFactory("tenSach"));

        TableColumn col3 = new TableColumn("Ma the loai");
        col3.setCellValueFactory(new PropertyValueFactory("maTL"));

        TableColumn col4 = new TableColumn("Nam XB");
        col4.setCellValueFactory(new PropertyValueFactory("namXB"));

        TableColumn col5 = new TableColumn("So luong");
        col5.setCellValueFactory(new PropertyValueFactory("soLuong"));

        TableColumn col6 = new TableColumn("Gia");
        col6.setCellValueFactory(new PropertyValueFactory("gia"));
        
        TableColumn col7 = new TableColumn("Ke Sach");
        col7.setCellValueFactory(new PropertyValueFactory("maKS"));
        
        TableColumn col8 = new TableColumn("Tac Gia");
        col8.setCellValueFactory(new PropertyValueFactory("maTG"));

        TableColumn col9 = new TableColumn("");
        col9.setCellFactory(param -> new TableCell<Sach, String>() {
            final Button btnXoa = new Button("Xóa");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btnXoa.setOnAction(event -> {
                        Alert a = Utils.getBox("Ban co chac muon xoa?", Alert.AlertType.CONFIRMATION);
                        Optional<ButtonType> result = a.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            Sach sach = getTableView().getItems().get(getIndex());

                            try {
                                SachService s = new SachService();

                                if (s.xoaSach(sach.getId()) == true) {
                                    Utils.getBox("Xoa thanh cong!", Alert.AlertType.INFORMATION).show();
                                }
                            } catch (SQLException e) {
                                Utils.getBox("Da co loi xay ra!", Alert.AlertType.WARNING).show();
                            }

                            getTableView().getItems().remove(sach);

                        }
                    });
                    setGraphic(btnXoa);
                    setText(null);
                }
            }
        });

        this.tbSach.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9);
    }

    public void themSachHandler(ActionEvent evt) throws SQLException {

        Sach sach = new Sach();
        sach.setId(0);
        sach.setTenSach(txtTenSach.getText());
        sach.setSoLuong(parseIntOrNull(txtSL.getText()));
        sach.setNamXB(parseIntOrNull(txtNamXB.getText()));
        
        

        Window owner = btnThem.getScene().getWindow();
        if (txtTenSach.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập tên sách");
            return;
        }

        if (cbTheLoai.getValue() == null) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập thể loại");
            return;
        } else {
            sach.setMaTL(cbTheLoai.getSelectionModel().getSelectedItem().getId());
        }
        if (txtNamXB.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập năm XB");
            return;
        }
        if (txtSL.getText().isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập số lượng");
            return;
        }

        if (!txtGia.getText().isEmpty()) {
            boolean flag = true;
            if (parseIntOrNull(txtGia.getText()) == 0) {
                Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Phải nhập giá khác 0");
                flag = false;
                return;
            }
            if (flag = true) {
                sach.setGia(parseFloatOrNull(txtGia.getText()));
            }
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập giá");
        }
        if (cbKeSach.getValue() == null) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập kệ sách");
            return;
        } else {
            sach.setMaKS(cbKeSach.getSelectionModel().getSelectedItem().getMaKe());
        }
        if (cbTacGia.getValue() == null) {
            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa nhập tác giả");
            return;
        } else {
            sach.setMaTG(cbTacGia.getSelectionModel().getSelectedItem().getMaTG());
        }

        SachService s = new SachService();
        if (s.themSach(sach) == true) {
            Utils.getBox("Them sach thanh cong!", Alert.AlertType.INFORMATION).show();
            refresh();
        } else {
            Utils.getBox("Da co loi xay ra!", Alert.AlertType.WARNING).show();
        }
    }
    public void suaSachHandler(ActionEvent evt) throws SQLException {
        if(this.txtTenSach.getText().isBlank() == false) {
            
        
            Sach sach = new Sach();

            sach.setId(parseIntOrNull(txtMaSach.getText()));
            sach.setTenSach(txtTenSach.getText());
            sach.setMaTL(1);
            if(cbTheLoai.getValue() != null)
                sach.setMaTL(cbTheLoai.getSelectionModel().getSelectedItem().getId());
            sach.setNamXB(parseIntOrNull(txtNamXB.getText()));
            sach.setSoLuong(parseIntOrNull(txtSL.getText()));
            sach.setGia(parseFloatOrNull(txtGia.getText()));
            sach.setMaKS(1);
            if(cbKeSach.getValue() != null)
                sach.setMaKS(cbKeSach.getSelectionModel().getSelectedItem().getMaKe());
            if(cbTacGia.getValue() != null)
                sach.setMaTG(cbTacGia.getSelectionModel().getSelectedItem().getMaTG());
            
            SachService s = new SachService();

            if(s.suaSach(sach) == true) {
                refresh();
                Utils.getBox("Sua thanh cong!", Alert.AlertType.INFORMATION).show();
            }
            else {
                Utils.getBox("Da co loi xay ra!", Alert.AlertType.WARNING).show();
            }
        }
        
        else {
            Utils.getBox("Ban chua nhap ten sach!", Alert.AlertType.WARNING).show();
        }
    }

}
