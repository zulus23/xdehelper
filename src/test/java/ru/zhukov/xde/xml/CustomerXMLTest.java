package ru.zhukov.xde.xml;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static  org.junit.Assert.*;

/**
 * Created by Gukov on 05.06.2017.
 */
public class CustomerXMLTest {

    CustomerXML customerXML;

    @Before
    public void setUp(){
        customerXML = new CustomerXML();
        customerXML.setAction("C");
        customerXML.setSeq("1");

        CustomerXML.CustXML custXML = new CustomerXML.CustXML();
        custXML.setInn("1111");
        custXML.setKpp("1111");
        custXML.setCustNum("1111");
        customerXML.setCust(custXML);

    }
    @After
    public void dreanDown(){
        customerXML = null;
    }



    @Test
    public void xmlContainsElementCust(){

        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(customerXML,writer);


            assertTrue(writer.toString().contains("cust"));


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferToXMLFile(){
        Path sourceXLS = Paths.get("d:/001/cust.xsl");
        Path outputXML = Paths.get("d:/001/test_cust_.xml");

        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(customerXML,writer);
            assertTrue(writer.toString().contains("cust"));
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
