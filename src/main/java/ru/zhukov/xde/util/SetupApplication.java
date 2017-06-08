package ru.zhukov.xde.util;

import ru.zhukov.xde.exception.BaseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;



/**
 * Created by Gukov on 02.06.2017.
 */
public class SetupApplication {

    private static  volatile  SetupApplication instance;
    private Properties properties;
    private static Path  setupPath = Paths.get(".").toAbsolutePath().resolve("setup/setup.properties").normalize();

    private SetupApplication(){
      properties = new Properties();
    }
    public static SetupApplication getInstance(){
        if(instance == null){
            synchronized (SetupApplication.class){
                if(instance == null){
                    instance = new SetupApplication();
                    try {
                        instance.createSetupPropertyFile();
                        try(InputStream inputStream = Files.newInputStream(setupPath,StandardOpenOption.READ)){
                            instance.properties.load(inputStream);
                        }

                    } catch (BaseException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }


    private    void createSetupPropertyFile() throws BaseException {
        try {
            if (!Files.exists(setupPath)) {

                if(!Files.exists(setupPath.getParent())) {
                    Files.createDirectory(setupPath.getParent());
                }

                    Files.createFile(setupPath);
                    Properties properties = new Properties();
                    properties.setProperty("itemXsl","//srv-slaps/InterfaceSL_1C/XSL/sl8_1C8_Item_30.xsl");
                    properties.setProperty("outXml","//srv-slaps/InterfaceSL_1C/Out");
                    properties.setProperty("customerLcrXsl","//srv-slaps/InterfaceSL_1C/XSL/sl8_1C8_CustLcr_30.xsl");
                    properties.setProperty("customerXsl","//srv-slaps/InterfaceSL_1C/XSL/Cust.xsl");
                    try(OutputStream output = Files.newOutputStream(setupPath)){
                        properties.store(output,"");
                    }


                }


        }catch (IOException ex){
            throw new BaseException("Файла настроек не существует",ex);
        }


    }



     void writeSetupFile(){

        if(Files.exists(setupPath)){

        }





    }
    public  Path itemXsl(){
               //properties.load(Files.newInputStream(setupPath,StandardOpenOption.READ));
       return Paths.get(properties.getProperty("itemXsl","//srv-slaps/InterfaceSL_1C/XSL/sl8_1C8_Item_30.xsl"));

    }
    public  Path customerXsl(){
        //properties.load(Files.newInputStream(setupPath,StandardOpenOption.READ));
        return Paths.get(properties.getProperty("customerXsl","//srv-slaps/InterfaceSL_1C/XSL/Cust.xsl"));

    }
    public  Path vendorXsl(){
        //properties.load(Files.newInputStream(setupPath,StandardOpenOption.READ));
        return Paths.get(properties.getProperty("itemXsl","//srv-slaps/InterfaceSL_1C/XSL/Vend.xsl"));

    }


    public Path pathOutXml(){
       return Paths.get(properties.getProperty("outXml","//srv-slaps/InterfaceSL_1C/Out"));
     }


    public Path customerLcrXsl() {
        return Paths.get(properties.getProperty("customerLcrXsl","//srv-slaps/InterfaceSL_1C/XSL/sl8_1C8_CustLcr_30.xsl"));
    }
}
