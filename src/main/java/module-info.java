module coit12200.wis {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens coit12200.wis to javafx.fxml;
    exports coit12200.wis;
    opens coit12200.wis.view to javafx.fxml;
    exports coit12200.wis.view;
}
