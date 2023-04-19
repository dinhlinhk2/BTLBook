/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndl.btlbook;

import com.ndl.Services.DangKyDocGiaService;
import com.ndl.Services.PhieuMuonService;
import com.ndl.Services.SachService;
import com.ndl.Services.TacGiaService;
import com.ndl.Services.UserService;
import com.ndl.pojo.DocGia;
import com.ndl.pojo.PhieuMuon;
import com.ndl.pojo.Sach;
import com.ndl.pojo.TacGia;
import com.ndl.pojo.User;
import com.ndl.utils.Utils;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class FXMLPhieuMuonController implements Initializable {

    @FXML
    private TableView<Sach> tbSach;

    @FXML
    private TableView<PhieuMuon> tbPhieuMuon;

    @FXML
    private TextField txtMaSach;
    @FXML
    private TextField txtTenSach;
    @FXML
    private ComboBox<DocGia> cbDocGia;

    @FXML
    private ComboBox<User> cbNV;

    @FXML
    private TextField txtgia;
    @FXML
    private ComboBox<TacGia> cbTacGia;

    @FXML
    private TextField txtSL;

    @FXML
    private Button btnLamMoi;

    @FXML
    private ComboBox cbLoaiTimKiem;

    @FXML
    private TextField txtTimKiem;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPhieuMuonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loadComboBoxMaNM();
        this.loadComboBoxMaNV();
        this.loadComboBoxTacGia();
        this.loadTableSach();
        this.loadTableMuon();

        this.btnLamMoi.setOnAction(e -> {
            try {
                refresh();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLPhieuMuonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.tbSach.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {

                this.txtMaSach.setText(Integer.toString(tbSach.getSelectionModel().getSelectedItem().getId()));
                this.txtTenSach.setText(tbSach.getSelectionModel().getSelectedItem().getTenSach());
//                int maNM = tbSach.getSelectionModel().getSelectedItem().getId();
//                try {
//                    DocGia dg = DangKyDocGiaService.getDocGiaById(maNM);
//                    this.cbDocGia.setValue(dg);
//                } catch (SQLException e) {
//                }
//                int maNV = tbSach.getSelectionModel().getSelectedItem().getId();
//                try {
//                    User u = UserService.getUserById(maNV);
//                    this.cbNV.setValue(u);
//                } catch (SQLException e) {
//                }
                this.txtgia.setText(Float.toString(tbSach.getSelectionModel().getSelectedItem().getGia()));
                int maTG = tbSach.getSelectionModel().getSelectedItem().getId();
                try {
                    TacGia tg = TacGiaService.getTacGiaById(maTG);
                    this.cbTacGia.setValue(tg);
                } catch (SQLException e) {
                }
                this.txtSL.setText(Integer.toString(tbSach.getSelectionModel().getSelectedItem().getSoLuong()));

            }
        });
        this.txtMaSach.setEditable(false);
        this.txtTenSach.setEditable(false);
        this.txtSL.setEditable(false);
        this.txtgia.setEditable(false);
        this.cbDocGia.setEditable(false);
        this.cbNV.setEditable(false);
        this.cbTacGia.setEditable(false);
        txtTimKiem.setPromptText("Tìm kiếm");
        this.txtTimKiem.textProperty().addListener((evt) -> {
            try {
                this.reloadTableSach(this.txtTimKiem.getText(), this.cbLoaiTimKiem.getSelectionModel().getSelectedIndex());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.cbLoaiTimKiem.getItems().addAll("Theo ten sach", "Theo the loai", "Theo nam XB");
        this.cbLoaiTimKiem.getSelectionModel().select(0);
        this.cbLoaiTimKiem.valueProperty().addListener((evt) -> {
            try {
                this.reloadTableSach(this.txtTimKiem.getText(), this.cbLoaiTimKiem.getSelectionModel().getSelectedIndex());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // TODO
    }

    private void refresh() throws SQLException {
        this.reloadTableSach(null, null);
        this.txtMaSach.clear();
        this.txtTenSach.clear();
        this.txtSL.clear();
        this.txtgia.clear();
        this.cbDocGia.setValue(null);
        this.cbNV.setValue(null);
        this.cbTacGia.setValue(null);
        this.txtTimKiem.clear();
        this.cbLoaiTimKiem.getSelectionModel().select(0);

    }

    private void reloadTableSach(String kw, Integer loaiTimKiem) throws SQLException {
        SachService s = new SachService();
        try {
            this.tbSach.setItems(FXCollections.observableList(s.getListSach(kw, loaiTimKiem)));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getRowIndex(TableView<PhieuMuon> table, PhieuMuon phieuMuon) {
        for (int i = 0; i < table.getItems().size(); i++) {
            if (phieuMuon.getMaSach().getId().equals(table.getItems().get(i).getMaSach().getId())) {
                return i;
            }
        }
        return -1;
    }

    private void loadComboBoxMaNM() {
        DangKyDocGiaService dg = new DangKyDocGiaService();
        try {
            this.cbDocGia.setItems(FXCollections.observableArrayList(dg.getListDocGia()));
        } catch (SQLException ex) {
            Logger.getLogger(FXMLSachController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void loadComboBoxMaNV() {
        UserService nv = new UserService();
        try {
            this.cbNV.setItems(FXCollections.observableArrayList(nv.getListUser()));
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
    private User user;

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
            final Button btnChonSach = new Button("Chọn sách");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btnChonSach.setOnAction(event -> {

                        Sach sach = getTableView().getItems().get(getIndex());
                        Window owner = btnChonSach.getScene().getWindow();
                        PhieuMuon pm = new PhieuMuon();
                        pm.setMaPhieu(0);
                        pm.setMaSach(sach);
                        if (cbDocGia.getValue() == null) {
                            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa chọn người mượn");
                            return;
                        } else {
                            pm.setMaDG(cbDocGia.getSelectionModel().getSelectedItem().getId());
                        }
                        if (cbNV.getValue() == null) {
                            Utils.showAlert(Alert.AlertType.ERROR, owner, "Lỗi!", "Chưa chọn nhân viên");
                            return;
                        } else {
                            pm.setMaNV(cbNV.getSelectionModel().getSelectedItem().getId());
                        }
                        pm.setSoLuong(1);
                        pm.setGia(Float.valueOf(txtgia.getText()));
                        pm.setNgayMuon(pm.getNgayMuon());

                        int rowIndex = getRowIndex(tbPhieuMuon, pm);

                        if (rowIndex == -1) {
                            tbPhieuMuon.getItems().add(pm);
                        } else {
                            PhieuMuon currentRow = tbPhieuMuon.getItems().get(rowIndex);
                            currentRow.setSoLuong(currentRow.getSoLuong() + 1);
                            tbPhieuMuon.refresh();
                        }
                    });
                    setGraphic(btnChonSach);
                    setText(null);
                }
            }
        });

        this.tbSach.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void loadTableMuon() {

        TableColumn col1 = new TableColumn("Ma Phieu");
        col1.setCellValueFactory(new PropertyValueFactory("maPhieu"));

        TableColumn col2 = new TableColumn("Ma Nguoi Muon");
        col2.setCellValueFactory(new PropertyValueFactory("maDG"));

        TableColumn col3 = new TableColumn("Ma Sach");
        col3.setCellValueFactory(new PropertyValueFactory("maSach"));

        TableColumn col4 = new TableColumn("So luong");
        col4.setCellValueFactory(new PropertyValueFactory("soLuong"));

        TableColumn col5 = new TableColumn("Gia");
        col5.setCellValueFactory(new PropertyValueFactory("gia"));

        TableColumn col6 = new TableColumn("Ma NV");
        col6.setCellValueFactory(new PropertyValueFactory("maNV"));

        TableColumn col7 = new TableColumn("Ngay Muon");
        col7.setCellValueFactory(new PropertyValueFactory("ngayMuon"));

        TableColumn col8 = new TableColumn("");
        col8.setCellFactory(param -> new TableCell<PhieuMuon, String>() {
            final Button btnXoa = new Button("Xóa");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btnXoa.setOnAction(event -> {
                        PhieuMuon pm = getTableView().getItems().get(getIndex());
                        getTableView().getItems().remove(pm);
                    });
                    setGraphic(btnXoa);
                    setText(null);
                }
            }
        });

        this.tbPhieuMuon.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8);
    }

    public void PhieuMuonHander(ActionEvent evt) throws SQLException {
        Alert a = Utils.getBox("Ban co chac muon mượn sách?", Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = a.showAndWait();

        if (result.get() == ButtonType.OK) {
            ArrayList<PhieuMuon> dsMuon = new ArrayList(tbPhieuMuon.getItems());

            PhieuMuonService s = new PhieuMuonService();

            switch (s.MuonSach(dsMuon)) {

                case -2:
                    Utils.getBox("Ban chua chon sach nao!", Alert.AlertType.WARNING).show();
                    break;
                case 1:
                    Utils.getBox("Muon thanh cong!", Alert.AlertType.INFORMATION).show();
                                        
                    tbPhieuMuon.getItems().clear();
                    break;
                default:
                    Utils.getBox("Da co loi xay ra!", Alert.AlertType.WARNING).show();
                    break;
            }
        }
    }
}
