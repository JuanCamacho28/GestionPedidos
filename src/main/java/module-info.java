module vallegrande.edu.pe.gestionpedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens vallegrande.edu.pe.gestionpedidos to javafx.fxml;
    opens vallegrande.edu.pe.gestionpedidos.controller to javafx.fxml;
    opens vallegrande.edu.pe.gestionpedidos.model to javafx.base;

    exports vallegrande.edu.pe.gestionpedidos;
    exports vallegrande.edu.pe.gestionpedidos.controller;
    exports vallegrande.edu.pe.gestionpedidos.view;
    exports vallegrande.edu.pe.gestionpedidos.model;
}