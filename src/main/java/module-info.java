module com.xminj.printermanagemen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.xminj.printermanagemen.controller to javafx.fxml;
    exports com.xminj.printermanagemen.controller;
    exports com.xminj.printermanagemen;
}