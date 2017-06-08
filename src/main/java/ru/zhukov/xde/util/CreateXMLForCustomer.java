package ru.zhukov.xde.util;

import ru.zhukov.xde.db.DataSelectable;
import ru.zhukov.xde.xml.CustomerXML;

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
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by Gukov on 07.06.2017.
 */
public class CreateXMLForCustomer {

    private Path sourceXLS, outputDirectoryXML;
    private List<PartnerClipBoard> listPartners;
    private DataSelectable dataSelectable;

    public   CreateXMLForCustomer(List<PartnerClipBoard> listPartners, DataSelectable dataSelectable){
        this.listPartners = new ArrayList<>(listPartners);
        this.dataSelectable = dataSelectable;
        outputDirectoryXML = SetupApplication.getInstance().pathOutXml();
        sourceXLS = SetupApplication.getInstance().customerXsl();
    }

    public CompletableFuture<List<CustomerXML>> run(){
        return CompletableFuture.supplyAsync(()-> listPartners.stream().map(i -> i.getPartner()).collect(Collectors.toList()))
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
}
