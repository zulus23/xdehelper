package ru.zhukov.xde.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import ru.zhukov.xde.action.ExitAction;
import ru.zhukov.xde.action.ExportItemAction;
import ru.zhukov.xde.action.PasteAction;
import ru.zhukov.xde.util.Databases;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Gukov on 26.05.2017.
 */
public class XDEApplicationController implements Initializable {

    @FXML
    private ToolBar toolBarApplication;
    @FXML
    private MenuItem miExit;
    @FXML
    private MenuItem miExportItem;
    @FXML
    private MenuItem miPaste;



    @FXML
    private TabPane tabPane;


    private ComboBox listEnterprise;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        miExit.setOnAction(ExitAction::action);
        miExit.setAccelerator(KeyCombination.keyCombination("Ctrl+F4"));

        miExportItem.setOnAction(e -> new ExportItemAction(tabPane).action(e));
        miPaste.setOnAction(e -> new PasteAction(tabPane.getSelectionModel().getSelectedItem()).action(e));

        Map set = Databases.availableDatabases();

        listEnterprise = new ComboBox();
        listEnterprise.getItems().addAll(Databases.availableDatabases().keySet());
        listEnterprise.getSelectionModel().select(0);
        listEnterprise.setMinWidth(150);
        toolBarApplication.getItems().add(listEnterprise);




    }
}
