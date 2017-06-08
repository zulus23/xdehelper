package ru.zhukov.xde.xml;

import org.junit.Before;
import org.junit.Test;
import ru.zhukov.xde.domain.CustomerLcr;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * Created by Gukov on 07.06.2017.
 */
public class CustomerLcrXMLTest {

    private CustomerLcrXML customerLcrXML;

    @Before
    public void setUp(){
      customerLcrXML = new CustomerLcrXML();
      customerLcrXML.setAction("C");
      customerLcrXML.setSeq("11111111111111111");
      CustomerLcrXML.CustLcr custLcr = new CustomerLcrXML.CustLcr();
      custLcr.setInn("111111");
      custLcr.setKpp("2");
      custLcr.setCurrCode("RUB");
      custLcr.setSite("GOTEK");
      custLcr.setDate("2098-03-01");
      customerLcrXML.setCustLcr(custLcr);

    }


    @Test
    public void generateCorrectXML(){
        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerLcrXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(customerLcrXML,writer);


            assertTrue(writer.toString().contains("curr_code"));


        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void generateCorrectTransferForXSLXML() {
        Path sourceXLS = Paths.get("\\\\srv-slaps\\InterfaceSL_1C\\XSL\\sl8_1C8_CustLcr_30.xsl");
        Path outputXML = Paths.get("d:/001/test_cust_.xml");

        try {
            StringWriter writer = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerLcrXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(customerLcrXML, writer);
            assertTrue(writer.toString().contains("curr_code"));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new FileInputStream(sourceXLS.toFile())));
            transformer.setOutputProperty(OutputKeys.ENCODING, "windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamSource xmlsource = new StreamSource(new StringReader(writer.toString()));
            // StreamSource xmlsource = new StreamSource(new FileInputStream(Paths.get("d://001/CustomerT.xml").toFile()));
            Writer xml = new StringWriter();
            StreamResult output = new StreamResult(xml);
            transformer.transform(xmlsource, output);
            assertTrue(output.getWriter().toString().contains("Дата"));


        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    }
