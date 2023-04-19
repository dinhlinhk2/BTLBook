/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ndl.btlbook;

import com.ndl.Services.PhieuMuonService;
import com.ndl.pojo.ThongKe;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXMLThongKeController implements Initializable {
    
    @FXML private ComboBox<Integer> cbNam;
    
    @FXML private BorderPane tkNam;
    
    @FXML private ComboBox<Integer> cbGia;
    
    @FXML private BorderPane tkGia;
    @FXML private Label lbgia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Integer currYear = LocalDate.now().getYear();
            for(int i = currYear; i >= currYear-10; i--) {
                cbNam.getItems().add(i);
            }
            cbNam.setValue(currYear);
            
            this.thongKeTheoNam(cbNam.getValue());
         
            
            cbNam.valueProperty().addListener(evt -> {
                try {
                    this.thongKeTheoNam(cbNam.getValue());
                    
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Integer gia = 10000;
            for(int i = gia; i >= gia; i--) {
                cbGia.getItems().add(i--);
            }
            cbGia.setValue(gia);
            
            this.thongKeTheoGia(cbGia.getValue());
         
            
            cbGia.valueProperty().addListener(evt -> {
                try {
                    this.thongKeTheoGia(cbGia.getValue());
                    
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }    
    private void thongKeTheoNam(Integer nam) throws SQLException {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Năm");
        
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Số mượn theo năm");
        
        BarChart barChart = new BarChart(xAxis, yAxis);
        
        PhieuMuonService s = new PhieuMuonService();
        ThongKe tk = s.sumSellByYear(nam);
        
        XYChart.Series data = new XYChart.Series();
        data.setName("Thong ke theo nam");
        
        data.getData().add(new XYChart.Data(tk.getNam().toString(), tk.getTongSL()));
        
        barChart.getData().add(data);
        barChart.setLegendVisible(false);
        tkNam.setCenter(barChart);
    }
    private void thongKeTheoGia(Integer gia) throws SQLException {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Giá");
        
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("tổng giá mượn");
        
        BarChart barChart = new BarChart(xAxis, yAxis);
        
        PhieuMuonService s = new PhieuMuonService();
        ThongKe tk = s.sumSellByGia(gia);
        
        XYChart.Series data = new XYChart.Series();
        data.setName("Thong ke theo gia tien");
        
        data.getData().add(new XYChart.Data(tk.getTongSL().toString(), tk.getTongSL()));
        
        barChart.getData().add(data);
        barChart.setLegendVisible(false);
        tkGia.setCenter(barChart);
        

            
          
    }
    
}
