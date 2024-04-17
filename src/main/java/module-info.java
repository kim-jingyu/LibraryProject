module example {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires org.mybatis;
    requires java.sql;

    opens example.view to javafx.fxml;
    exports example.view;
    opens example.controller to javafx.fxml;
    exports example.controller;
    opens example.service to javafx.fxml;
    exports example.service;
    opens example.vo to javafx.fxml;
    exports example.vo;
    opens example.dao to javafx.fxml;
    exports example.dao;
    opens example.dto to javafx.fxml;
    exports example.dto;
    opens example.mybatis to javafx.fxml;
    exports example.mybatis;

}