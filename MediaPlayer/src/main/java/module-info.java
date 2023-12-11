module br.ufrn.imd {

    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;

    requires javafx.fxml;
    requires javafx.web;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.media;

    exports br.ufrn.imd;
    exports br.ufrn.imd.controllers;

    opens br.ufrn.imd to javafx.fxml;
    opens br.ufrn.imd.controllers to javafx.fxml;
}