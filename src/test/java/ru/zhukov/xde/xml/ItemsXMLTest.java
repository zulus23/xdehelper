package ru.zhukov.xde.xml;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.junit.Before;
import org.junit.Test;


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


import static  org.junit.Assert.*;
/**
 * Created by Gukov on 31.05.2017.
 */
public class ItemsXMLTest {


    @Before
    public void setUp(){

    }

    @Test
    public void canCreateXMLFromObject(){
        ItemsXML itemsXML = new ItemsXML(String.valueOf(System.currentTimeMillis()));
        itemsXML.getItm().add(new ItemsXML.ItemXML("GOTEK","1520716816","","","","",""));
        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(itemsXML.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(itemsXML,writer);


            assertTrue(writer.toString().contains("itm"));


        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }
    @Test
    public void transferFromWriterXSL(){
        Path sourceXLS = Paths.get("d:/001/sl8_1C8_Item_30.xsl");
        Path outputXML = Paths.get("d:/001/test_trans_.xml");
        ItemsXML itemsXML = new ItemsXML(String.valueOf(System.currentTimeMillis()));
        itemsXML.getItm().add(new ItemsXML.ItemXML("GOTEK","1520716816","","","","",""));
        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(itemsXML.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(itemsXML,writer);
            assertTrue(writer.toString().contains("itm"));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer =  factory.newTransformer(new StreamSource(sourceXLS.toString()));
            transformer.setOutputProperty(OutputKeys.ENCODING,"windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");

            StreamSource xmlsource = new StreamSource(new StringReader(writer.toString()));
            Writer xml = new StringWriter();
            StreamResult output = new StreamResult(xml);
            transformer.transform(xmlsource,output);
            assertTrue(output.getWriter().toString().contains("windows-1251"));







        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }


}
