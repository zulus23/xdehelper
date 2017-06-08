package ru.zhukov.xde.xml;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.zhukov.xde.util.SetupApplication;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * Created by Gukov on 05.06.2017.
 */
public class VendorXMLTest {

    VendorXML vendorXML;

    @Before
    public void setUp(){
        vendorXML = new VendorXML();
        vendorXML.setAction("C");
        vendorXML.setSeq("1");

        VendorXML.VendXML vendXML = new VendorXML.VendXML();
        vendXML.setInn("1111");
        vendXML.setKpp("1111");
        vendXML.setVendNum("1111");
        vendorXML.setCust(vendXML);

    }
    @After
    public void dreanDown(){
        vendorXML = null;
    }



    @Test
    public void xmlContainsElementVend(){

        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(VendorXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(vendorXML,writer);


            assertTrue(writer.toString().contains("vend"));


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferToXMLFile(){
        Path sourceXLS = SetupApplication.getInstance().vendorXsl();


        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(vendorXML,writer);
            assertTrue(writer.toString().contains("vend"));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer =  factory.newTransformer(new StreamSource(sourceXLS.toString()));
            transformer.setOutputProperty(OutputKeys.ENCODING,"windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");

            StreamSource xmlsource = new StreamSource(new StringReader(writer.toString()));
           // StreamSource xmlsource = new StreamSource(new FileInputStream(Paths.get("d://001/CustomerT.xml").toFile()));
            Writer xml = new StringWriter();
            StreamResult output = new StreamResult(xml);
            transformer.transform(xmlsource,output);
            assertTrue(output.getWriter().toString().contains("Справочник"));


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }




}
