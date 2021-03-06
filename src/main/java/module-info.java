module it.unipv.po.aioobe.trenissimo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.logging;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires itextpdf;
    requires com.jfoenix;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.jetbrains.annotations;


    opens it.unipv.po.aioobe.trenissimo.model.persistence.entity to org.hibernate.orm.core, javafx.fxml;
    opens it.unipv.po.aioobe.trenissimo to javafx.fxml;

    exports it.unipv.po.aioobe.trenissimo;
    exports it.unipv.po.aioobe.trenissimo.controller;
    opens it.unipv.po.aioobe.trenissimo.controller to javafx.fxml;
    exports it.unipv.po.aioobe.trenissimo.view;
    opens it.unipv.po.aioobe.trenissimo.view to javafx.fxml;
}