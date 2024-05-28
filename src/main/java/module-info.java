module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires telegrambots;
    requires telegrambots.meta; // Добавленная строка

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports model;
    opens model to javafx.fxml;
    exports strategy;
    opens strategy to javafx.fxml;
}