package ru.zhukov.xde.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.util.StringConverter;
import ru.zhukov.xde.action.ExitAction;
import ru.zhukov.xde.action.ExportItemViewAction;
import ru.zhukov.xde.action.PasteAction;
import ru.zhukov.xde.action.RunExportAction;
import ru.zhukov.xde.domain.Enterprise;
import ru.zhukov.xde.util.Databases;

import java.net.URL;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.WeakHashMap;

/**
 * Created by Gukov on 26.05.2017.
 */
public class XDEApplicationController implements Initializable {

    private final Map<Tab,AbstractController> controllerMap = new WeakHashMap<>();

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


    private ComboBox<Enterprise> listEnterprise;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        miExit.setOnAction(ExitAction::action);
        miExit.setAccelerator(KeyCombination.keyCombination("Ctrl+F4"));

        miExportItem.setOnAction(e -> new ExportItemViewAction(tabPane,listEnterprise.getSelectionModel().getSelectedItem()).action(e));
        miPaste.setOnAction(e -> new PasteAction(tabPane.getSelectionModel().getSelectedItem()).action(e));





        listEnterprise = new ComboBox<>();
        listEnterprise.setConverter(new StringConverter<Enterprise>() {
            @Override
            public String toString(Enterprise object) {
                return object.getName();
            }

            @Override
            public Enterprise fromString(String name) {
                return listEnterprise.getItems().stream().filter(e -> e.getName().equals(name)).findFirst().orElseThrow(() ->new NoSuchElementException("Don't have enterprise"));
            }
        });
        listEnterprise.getSelectionModel().selectedItemProperty().addListener(e -> {

        } );
        listEnterprise.getItems().addAll(Databases.availableDatabases().values());
        listEnterprise.getSelectionModel().select(0);
        listEnterprise.setMinWidth(150);

        createButtonToolBar();
        toolBarApplication.getItems().add(listEnterprise);




    }

    private void createButtonToolBar(){
        Button runImportFromSL = new Button();

        //showAccountRecordView.setTooltip(new Tooltip(resourceBundle.getString("tooltip.showProvod")));
        runImportFromSL.setGraphic(new ImageView(new Image(getClass().getResource("/ru/zhukov/xde/assests/image32/database-export.png").toExternalForm())));
        runImportFromSL.setOnAction((e)-> new RunExportAction(tabPane.getSelectionModel().getSelectedItem(),
                                                              listEnterprise.getSelectionModel().getSelectedItem()).action(e));
        //runImportFromSL.setOnAction(this::showAccountRecordView);
        toolBarApplication.getItems().add(runImportFromSL);
        toolBarApplication.getItems().add(new Separator(Orientation.VERTICAL));
    }

}
