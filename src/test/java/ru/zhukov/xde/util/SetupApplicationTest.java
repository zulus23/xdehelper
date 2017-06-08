package ru.zhukov.xde.util;

import org.junit.Test;

import java.nio.file.Path;

import static org.junit.Assert.*;
/**
 * Created by Gukov on 08.06.2017.
 */

public class SetupApplicationTest {

    @Test
    public void  canGetPropertyForCustomerLcrXSL(){
         Path pathCustomerLcrXSL  =  SetupApplication.getInstance().customerLcrXsl();

         assertEquals(2,pathCustomerLcrXSL.getNameCount());
         assertEquals(true,pathCustomerLcrXSL.getName(1).toString().contains("CustLcr"));

    }
}
