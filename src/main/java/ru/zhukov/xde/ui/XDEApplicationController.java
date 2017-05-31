package ru.zhukov.xde.ui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        listEnterprise.getSelectionModel().selectedItemProperty().addListener(e -> {
            System.out.println(Databases.availableDatabases().get(listEnterprise.getSelectionModel().getSelectedItem()));
        } );
        listEnterprise.getItems().addAll(Databases.availableDatabases().keySet());
        listEnterprise.getSelectionModel().select(0);
        listEnterprise.setMinWidth(150);

        createButtonToolBar();
        toolBarApplication.getItems().add(listEnterprise);




    }

    private void createButtonToolBar(){
        Button runImportFromSL = new Button();
        //showAccountRecordView.setTooltip(new Tooltip(resourceBundle.getString("tooltip.showProvod")));
        runImportFromSL.setGraphic(new ImageView(new Image(getClass().getResource("/ru/zhukov/xde/assests/image32/database-import.png").toExternalForm())));
        //runImportFromSL.setOnAction(this::showAccountRecordView);
        toolBarApplication.getItems().add(runImportFromSL);
    }

}
