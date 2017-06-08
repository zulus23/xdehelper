package ru.zhukov.xde.action;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import ru.zhukov.xde.db.DataSelectable;
import ru.zhukov.xde.db.MsqlDataSelectableImpl;
import ru.zhukov.xde.domain.Enterprise;
import ru.zhukov.xde.domain.Item;
import ru.zhukov.xde.util.*;
import ru.zhukov.xde.xml.CustomerXML;
import ru.zhukov.xde.xml.ItemsXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by Gukov on 01.06.2017.
 */
public class RunExportAction extends AbstractAction {

    private DataSelectable  dataSelectable;

    private Path sourceXLS, outputDirectoryXML;
    public RunExportAction(Tab selectedTab, Enterprise enterprise) {
        super(selectedTab, enterprise);
        dataSelectable = new MsqlDataSelectableImpl(enterprise);
    }


    public void action(ActionEvent e) {

        outputDirectoryXML = SetupApplication.getInstance().pathOutXml();
        if (control.getText().contains("Экспорт изделий")) {
            sourceXLS = SetupApplication.getInstance().itemXsl();
            TableView<ItemClipBoard> tableView = FXUtils.getChildByID(control.getTabPane(), "itemView");
            List<ItemClipBoard> clipBoards =  tableView.getItems().stream().collect(Collectors.toList());
              new CreateXMLForItem(clipBoards,dataSelectable)
                                  .run()
                                  .whenComplete(this::deleteItemFromView);
        }
        if (control.getText().contains("Экспорт контрагентов")) {
            TableView<PartnerClipBoard> tableView = FXUtils.getChildByID(control.getTabPane(), "itemView");
            RadioButton customerSelected = FXUtils.getChildByID(control.getTabPane(), "rbCustomer");
            CheckBox withLcrSelected = FXUtils.getChildByID(control.getTabPane(), "chWithLcr");
            if(customerSelected.isSelected()){

                new CreateXMLForCustomer(tableView.getItems().stream().collect(Collectors.toList()), dataSelectable)
                                        .run();
                                        //.whenComplete(this::deleteCustomerFromView);
                if (withLcrSelected.isSelected()){
                    new CreateXMLForLcrCustomer(tableView.getItems().stream().collect(Collectors.toList()),dataSelectable).run();
                }


            }

        }
    }

    private void deleteCustomerFromView(List<CustomerXML> customerXMLS, Throwable throwable) {
        TableView<PartnerClipBoard> tableView = FXUtils.getChildByID(control.getTabPane(), "itemView");
        tableView.getItems().clear();
    }



    private void deleteItemFromView(List<ItemsXML> itemsXMLS, Throwable throwable) {
        TableView<ItemClipBoard> tableView = FXUtils.getChildByID(control.getTabPane(), "itemView");
        itemsXMLS. stream().forEach(e -> { findItem(tableView, e);

        });
    }

    private void findItem(TableView<ItemClipBoard> tableView, ItemsXML e) {
        tableView.getItems().stream()
                            .filter(i-> i.getItem().equals(e.getItm().get(0).getItem()))
                            .findFirst()
                            .ifPresent(i -> tableView.getItems().remove(i));
    }

}


