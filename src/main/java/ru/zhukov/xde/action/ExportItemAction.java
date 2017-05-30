package ru.zhukov.xde.action;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import ru.zhukov.xde.ui.XDEImportItemController;

import java.io.IOException;

/**
 * Created by Gukov on 30.05.2017.
 */
public class ExportItemAction {

    private TabPane tabPane;

    public ExportItemAction(TabPane tabPane){
        this.tabPane = tabPane;
    }


    public  void action(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/zhukov/xde/ui/XDEImportItemView.fxml"));
        XDEImportItemController itemController = new XDEImportItemController();
        fxmlLoader.setController(itemController);
        try{
            AnchorPane itemView = fxmlLoader.load();
            Tab itemTab = new Tab();
            itemTab.setText("Экспорт изделий");
            itemTab.setContent(itemView);
            tabPane.setTabMaxWidth(160);
            tabPane.getTabs().add(itemTab);

        }catch (IOException ex){

            ex.printStackTrace();
        }
    }
}
