
module com.ndl.btlbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.base;
    requires java.sql;

    opens com.ndl.btlbook to javafx.fxml;
    exports com.ndl.btlbook;

    exports com.ndl.pojo;
}
