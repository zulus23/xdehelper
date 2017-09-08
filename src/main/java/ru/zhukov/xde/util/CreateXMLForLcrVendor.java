package ru.zhukov.xde.util;

import ru.zhukov.xde.db.DataSelectable;
import ru.zhukov.xde.xml.CustomerLcrXML;
import ru.zhukov.xde.xml.VendorLcrXML;

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
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by Gukov on 07.06.2017.
 */
public class CreateXMLForLcrVendor {
    private Path sourceXLS, outputDirectoryXML;
    private List<PartnerClipBoard> listPartners;
    private DataSelectable dataSelectable;

    public CreateXMLForLcrVendor(List<PartnerClipBoard> collect, DataSelectable dataSelectable) {
        this.listPartners = new ArrayList<>(collect);
        this.dataSelectable = dataSelectable;
        outputDirectoryXML = SetupApplication.getInstance().pathOutXml();
        sourceXLS = SetupApplication.getInstance().vendorLcrXsl();
    }


    public CompletableFuture<? extends  List<VendorLcrXML>> run() {
         return CompletableFuture.supplyAsync(() -> listPartners.stream()
                                                               .filter(e -> Objects.nonNull(e.getLcr()))
                                                               .map(i -> { return  i.getPartner()+"="+i.getLcr();})
                                                               .collect(Collectors.toList()))
                .thenApply(i -> dataSelectable.selectVendorLcr(i.toArray(new String[]{})))
                .thenApply(customerLcrs -> customerLcrs.stream()
                                                       .map(e -> {
                                                            VendorLcrXML lcrXML = new VendorLcrXML();
                                                            lcrXML.setSeq(String.valueOf(System.nanoTime()));
                                                            lcrXML.setAction("C");
                                                            VendorLcrXML.VendLcr lcr = new VendorLcrXML.VendLcr();
                                                            lcr.setSite(e.getSite());
                                                            lcr.setDate(e.getDate());
                                                            lcr.setCurrCode(e.getCurrentCode());
                                                            lcr.setKpp(e.getKpp());
                                                            lcr.setInn(e.getInn());
                                                            lcr.setVendNum(e.getVendNum());
                                                            lcr.setLcrNum(e.getLcrNum());
                                                            lcrXML.setVendLcr(lcr);
                                                            return lcrXML;
                                                           })
                                                       .collect(Collectors.toList()))
                .whenComplete(this::createVendorLcrXML);

      }

    private void createVendorLcrXML(List<VendorLcrXML> vendorLcrXMLS, Throwable throwable) {
        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(VendorLcrXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer =  factory.newTransformer(new StreamSource(new FileInputStream(sourceXLS.toFile())));
            transformer.setOutputProperty(OutputKeys.ENCODING,"windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            Writer xml = new StringWriter();
            for (VendorLcrXML item:vendorLcrXMLS) {
                writer.getBuffer().setLength(0);
                marshaller.marshal(item,writer);
                StreamSource xmlsource = new StreamSource(new StringReader(writer.toString()));

                StreamResult output = new StreamResult(new OutputStreamWriter(new FileOutputStream(Paths.get(outputDirectoryXML.toString(),
                        String.format("%10s_vendlcr_1c_gotek.xml",item.getSeq())).toFile()),
                        Charset.forName("windows-1251")));
                transformer.transform(xmlsource,output);
                output.getWriter().close();


            }


        }catch (Exception ex){
          ex.printStackTrace();
        }
    }

    }
