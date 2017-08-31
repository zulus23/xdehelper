package ru.zhukov.xde.util;

import ru.zhukov.xde.db.DataSelectable;
import ru.zhukov.xde.xml.ItemsXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by Gukov on 07.06.2017.
 */
public class CreateXMLForItem {
    private Path sourceXLS, outputDirectoryXML;
    private List<ItemClipBoard> itemClipBoards;
    private DataSelectable dataSelectable;

    public   CreateXMLForItem(List<ItemClipBoard> listImputItem, DataSelectable dataSelectable){
        this.itemClipBoards = new ArrayList<>(listImputItem);
        this.dataSelectable = dataSelectable;
        outputDirectoryXML = SetupApplication.getInstance().pathOutXml();
        sourceXLS = SetupApplication.getInstance().itemXsl();
    }

    public CompletableFuture<List<ItemsXML>> run(){

        return CompletableFuture.supplyAsync(() -> itemClipBoards.stream().map(s -> s.getItem()).collect(Collectors.toList()))
                .thenApply(s -> dataSelectable.selectItems(s.toArray(new String[0]/*{}*/)))
                .thenApply(s -> s.stream().map(i -> {
                    ItemsXML itemsXML = new ItemsXML(String.valueOf(System.nanoTime()));
                    itemsXML.getItm().add(new ItemsXML.ItemXML(i.getSite(), i.getItem(), i.getDescription(),
                            i.getRusDescription(), i.getEdizm().getCodeSyncWint1C(), i.getTax(), i.getProductCode(), i.getComment()));
                    return itemsXML;
                }).collect(Collectors.toList()))

                .whenCompleteAsync(this::createItemXML);

                //.whenComplete(this::deleteItemFromView);
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
                    transformer.transform(xmlsource, output);
                    output.getWriter().close();


            }


        }catch (Exception ex){
            throw  new RuntimeException("Ошибка при формироании xml файла :",ex);
        }
    }

}
