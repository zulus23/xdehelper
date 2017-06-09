package ru.zhukov.xde.action;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import ru.zhukov.xde.domain.Enterprise;
import ru.zhukov.xde.ui.AbstractController;
import ru.zhukov.xde.ui.XDEImportItemController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Gukov on 30.05.2017.
 */
public class ExportItemViewAction extends AbstractViewAction {

    public ExportItemViewAction(TabPane tabPane, Enterprise selectedEnterprise, Map<Tab,AbstractController> controllerMap){
        super(tabPane, selectedEnterprise,controllerMap);

    }


    @Override
    public  void action(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/zhukov/xde/ui/XDEImportItemView.fxml"));
        Optional<Tab> tabOptional = super.mapController.keySet()
                          .stream()
                          .filter(e-> e.getText()
                                              .contains("Экспорт изделий"))

                          .findFirst();

        if (tabOptional.isPresent()){
           super.tabPane.getSelectionModel().select(tabOptional.get());
        } else {
            XDEImportItemController itemController = new XDEImportItemController(selectedEnterprise);
            fxmlLoader.setController(itemController);
            try {
                AnchorPane itemView = fxmlLoader.load();
                Tab itemTab = new Tab();
                itemTab.setText("Экспорт изделий");
                itemTab.setContent(itemView);
                tabPane.setTabMaxWidth(160);
                tabPane.getTabs().add(itemTab);
                tabPane.getSelectionModel().select(itemTab);
              super.mapController.putIfAbsent(itemTab,itemController);

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
    }
}
