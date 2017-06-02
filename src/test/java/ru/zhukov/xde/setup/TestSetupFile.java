package ru.zhukov.xde.setup;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import ru.zhukov.xde.exception.BaseException;
import ru.zhukov.xde.util.SetupApplication;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;
/**
 * Created by Gukov on 02.06.2017.
 */

public class TestSetupFile {
    private static Path setupPath = Paths.get(".").toAbsolutePath().resolve("setup/setup.properties").normalize();

    @Test
    public void canReadItemXSLPath(){
        assertTrue(SetupApplication.getInstance().itemXsl().equals(Paths.get("//srv-slaps//InterfaceSL_1C//XSL/sl8_1C8_Item_30.xsl")));
    }
    @Test
    public void canReadOutXMLPath(){
        assertTrue(SetupApplication.getInstance().pathOutXml().equals(Paths.get("//srv-slaps//InterfaceSL_1C//OUT")));
    }
    @After
    public  void tearDown(){
      try{

         boolean b =  Files.deleteIfExists(setupPath);
          System.out.println(" delete file is  "+b);
      } catch (AccessDeniedException a){
          a.printStackTrace();
      }
      catch ( IOException e) {
          e.printStackTrace();
      }
    }

}
