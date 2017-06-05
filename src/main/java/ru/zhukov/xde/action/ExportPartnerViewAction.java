package ru.zhukov.xde.action;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import ru.zhukov.xde.domain.Enterprise;
import ru.zhukov.xde.ui.XDEImportItemController;
import ru.zhukov.xde.ui.XDEImportPartnerController;
import sun.plugin.dom.views.AbstractView;

import java.io.IOException;

/**
 * Created by Gukov on 05.06.2017.
 */
public class ExportPartnerViewAction extends AbstractViewAction {


    public ExportPartnerViewAction(TabPane tabPane, Enterprise selectedPartner) {
        super(tabPane,selectedPartner);
    }


    public  void action(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ru/zhukov/xde/ui/XDEImportPartnerView.fxml"));
        XDEImportPartnerController itemController = new XDEImportPartnerController(selectedEnterprise);
        fxmlLoader.setController(itemController);
        try{
            AnchorPane itemView = fxmlLoader.load();
            Tab itemTab = new Tab();
            itemTab.setText("Экспорт контрагентов");
            itemTab.setContent(itemView);
            tabPane.setTabMaxWidth(160);
            tabPane.getTabs().add(itemTab);

        }catch (IOException ex){

            ex.printStackTrace();
        }
    }
}
