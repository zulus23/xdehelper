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
import ru.zhukov.xde.util.FXUtils;
import ru.zhukov.xde.util.ItemClipBoard;
import ru.zhukov.xde.util.PartnerClipBoard;
import ru.zhukov.xde.util.SetupApplication;
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


            CompletableFuture.supplyAsync(() -> tableView.getItems().stream().map(s -> s.getItem()).collect(Collectors.toList()))
                    .thenApply(s -> dataSelectable.selectItems(s.toArray(new String[]{})))
                    .thenApply(s -> s.stream().map(i -> {
                        ItemsXML itemsXML = new ItemsXML(String.valueOf(System.nanoTime()));
                        itemsXML.getItm().add(new ItemsXML.ItemXML(i.getSite(), i.getItem(), i.getDescription(),
                                i.getRusDescription(), i.getCodeSync(), i.getTax(), i.getProductCode(), i.getComment()));
                        return itemsXML;
                    }).collect(Collectors.toList()))
                    .whenCompleteAsync(this::createItemXML)
                    .whenComplete(this::deleteItemFromView);


        }
        if (control.getText().contains("Экспорт контрагентов")) {
            TableView<PartnerClipBoard> tableView = FXUtils.getChildByID(control.getTabPane(), "itemView");
            RadioButton customerSelected = FXUtils.getChildByID(control.getTabPane(), "rbCustomer");
            if(customerSelected.isSelected()){
                sourceXLS = SetupApplication.getInstance().customerXsl();
                CompletableFuture.supplyAsync(()-> tableView.getItems().stream().map(i -> i.getPartner()).collect(Collectors.toList()))
                                 .thenApply(i -> dataSelectable.selectCustomers(i.toArray(new String[]{})))
                                 .thenApply(customer -> customer.stream()
                                                                .map(i -> {
                                                                            CustomerXML customerXML = new CustomerXML();
                                                                                        customerXML.setSeq(String.valueOf(System.nanoTime()));
                                                                                        customerXML.setAction("C");
                                                                             CustomerXML.CustXML custXML = new CustomerXML.CustXML();
                                                                             custXML.setCustNum(i.getCustNum());
                                                                             custXML.setInn(i.getInn());
                                                                             custXML.setKpp(i.getKpp());
                                                                             custXML.setAddressFull(i.getAddress());
                                                                             custXML.setCountryCode(i.getCountryCode());
                                                                             custXML.setFullName(i.getFullName());
                                                                             custXML.setName(i.getName());
                                                                             custXML.setOkpo(i.getOkpo());
                                                                             custXML.setLinSeq("");
                                                                             customerXML.setCust(custXML);
                                                                            return customerXML;
                                                                           })
                                                                .collect(Collectors.toList()))
                                 .whenComplete(this::createCustomerXML);


            }

        }
    }

    private void createCustomerXML(List<CustomerXML> customerXMLS, Throwable throwable) {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer =  factory.newTransformer(new StreamSource(sourceXLS.toString()));
            transformer.setOutputProperty(OutputKeys.ENCODING,"windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            Writer xml = new StringWriter();
            for (CustomerXML item:customerXMLS) {
                writer.getBuffer().setLength(0);
                marshaller.marshal(item,writer);
                StreamSource xmlsource = new StreamSource(new StringReader(writer.toString()));

                StreamResult output = new StreamResult(new OutputStreamWriter(new FileOutputStream(Paths.get(outputDirectoryXML.toString(),
                        String.format("%10s_cust_1c_gotek.xml",item.getSeq())).toFile()),
                        Charset.forName("windows-1251")));
                transformer.transform(xmlsource,output);
                output.getWriter().close();


            }


        }catch (Exception ex){

        }
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

    private void createItemXML(List<ItemsXML> itemsXMLS, Throwable throwable) {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(ItemsXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer =  factory.newTransformer(new StreamSource(sourceXLS.toString()));
            transformer.setOutputProperty(OutputKeys.ENCODING,"windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            Writer xml = new StringWriter();
            for (ItemsXML item:itemsXMLS) {
                writer.getBuffer().setLength(0);
                marshaller.marshal(item,writer);
                StreamSource xmlsource = new StreamSource(new StringReader(writer.toString()));

                StreamResult output = new StreamResult(new OutputStreamWriter(new FileOutputStream(Paths.get(outputDirectoryXML.toString(),
                                                                                                             String.format("%10s_item_1c.xml",item.getSeq())).toFile()),
                                                                                                   Charset.forName("windows-1251")));
                transformer.transform(xmlsource,output);
                output.getWriter().close();


            }


        }catch (Exception ex){

        }
    }


}


