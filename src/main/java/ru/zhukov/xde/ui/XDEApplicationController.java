package ru.zhukov.xde.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import ru.zhukov.xde.util.Databases;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by Gukov on 26.05.2017.
 */
public class XDEApplicationController implements Initializable {

    @FXML
    private ToolBar toolBarApplication;

    private ComboBox listEnterprise;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Map set = Databases.availableDatabases();

        listEnterprise = new ComboBox();
        listEnterprise.getItems().addAll(Databases.availableDatabases().keySet());
        listEnterprise.getSelectionModel().select(0);
        listEnterprise.setMinWidth(150);
        toolBarApplication.getItems().add(listEnterprise);

    }
}
