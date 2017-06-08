package ru.zhukov.xde.util;

import ru.zhukov.xde.db.DataSelectable;
import ru.zhukov.xde.domain.Vendor;
import ru.zhukov.xde.xml.CustomerXML;
import ru.zhukov.xde.xml.VendorXML;

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
public class CreateXMLForVendor {

    private Path sourceXLS, outputDirectoryXML;
    private List<PartnerClipBoard> listPartners;
    private DataSelectable dataSelectable;

    public CreateXMLForVendor(List<PartnerClipBoard> listPartners, DataSelectable dataSelectable){
        this.listPartners = new ArrayList<>(listPartners);
        this.dataSelectable = dataSelectable;
        outputDirectoryXML = SetupApplication.getInstance().pathOutXml();
        sourceXLS = SetupApplication.getInstance().vendorXsl();
    }

    public CompletableFuture<List<VendorXML>> run(){
        return CompletableFuture.supplyAsync(()-> listPartners.stream().map(i -> i.getPartner()).collect(Collectors.toList()))
                .thenApply(i -> dataSelectable.selectVendors(i.toArray(new String[]{})))
                .thenApply(customer -> customer.stream()
                        .map(i -> {
                            VendorXML vendorXML = new VendorXML();
                            vendorXML.setSeq(String.valueOf(System.nanoTime()));
                            vendorXML.setAction("C");
                            VendorXML.VendXML vendXML = new VendorXML.VendXML();
                            vendXML.setVendNum(i.getVendNum());
                            vendXML.setInn(i.getInn());
                            vendXML.setKpp(i.getKpp());
                            vendXML.setAddressFull(i.getAddress());
                            vendXML.setCountryCode(i.getCountryCode());
                            vendXML.setFullName(i.getFullName());
                            vendXML.setName(i.getName());
                            vendXML.setOkpo(i.getOkpo());
                            vendXML.setLinSeq("");
                            vendorXML.setCust(vendXML);
                            return vendorXML;
                        })
                        .collect(Collectors.toList()))
                .whenComplete(this::createVendorXML);

    }
    private void createVendorXML(List<VendorXML> vendorXMLS, Throwable throwable) {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(VendorXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer =  factory.newTransformer(new StreamSource(sourceXLS.toString()));
            transformer.setOutputProperty(OutputKeys.ENCODING,"windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            Writer xml = new StringWriter();
            for (VendorXML item:vendorXMLS) {
                writer.getBuffer().setLength(0);
                marshaller.marshal(item,writer);
                StreamSource xmlsource = new StreamSource(new StringReader(writer.toString()));

                StreamResult output = new StreamResult(new OutputStreamWriter(new FileOutputStream(Paths.get(outputDirectoryXML.toString(),
                        String.format("%10s_vend_1c_gotek.xml",item.getSeq())).toFile()),
                        Charset.forName("windows-1251")));
                transformer.transform(xmlsource,output);
                output.getWriter().close();


            }


        }catch (Exception ex){

        }
    }
}
