package com.xminj.printermanagemen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Objects;

public class PrinterManagementController {

    @FXML
    private ComboBox<PrintService> printerComboBox;

    @FXML
    public void initialize() {
        // 获取系统中所有可用的打印机，并添加到 ComboBox 中
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printer : printServices) {
            printerComboBox.getItems().add(printer);
        }
        // 设置选择默认打印机
        if (printServices.length > 0) {
            printerComboBox.getSelectionModel().selectFirst();
        }
    }


    @FXML
    public void handlePrintAction() {
        PrintService selectedPrinter = printerComboBox.getSelectionModel().getSelectedItem();
        if (Objects.isNull(selectedPrinter)) {
            return;
        }

        try {
            DocPrintJob printJob = selectedPrinter.createPrintJob();
            // 打印内容
            String testPageContent = "This is a test page from JavaFX FXML printer management.";
            InputStream inputStream = new ByteArrayInputStream(testPageContent.getBytes());


            // 创建打印文档
            Doc doc = new SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
            // 打印
            printJob.print(doc, null);
            //成功信息弹窗
            showAlert(Alert.AlertType.INFORMATION, "Print Status", "Print job sent successfully to " + selectedPrinter.getName());

        } catch (Exception e) {
            // 错误信息
            showAlert(Alert.AlertType.ERROR, "Print Error", "Failed to print: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}