module it.unipv.po.aioobe.trenissimo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.logging;
    requires org.controlsfx.controls;


    opens it.unipv.po.aioobe.trenissimo.model.persistence.entity to org.hibernate.orm.core, javafx.fxml;
    opens it.unipv.po.aioobe.trenissimo to javafx.fxml;

    exports it.unipv.po.aioobe.trenissimo;
}