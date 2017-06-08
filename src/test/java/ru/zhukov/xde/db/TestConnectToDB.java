package ru.zhukov.xde.db;

import org.junit.Test;
import ru.zhukov.xde.domain.*;
import ru.zhukov.xde.util.Databases;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Gukov on 01.06.2017.
 */

public class TestConnectToDB {




    @Test
    public void connetToEnterprisePOLYPACK(){
        Enterprise enterprise = Databases.availableDatabases().get("ПОЛИПАК");
        assertNotNull(enterprise);

        DataSelectable dataSelectable = new MsqlDataSelectableImpl(enterprise);
        assertNotNull(dataSelectable);

        List<Item> list =  dataSelectable.selectItems("9200900187","9200900187");
        assertEquals(1,list.size());
        assertEquals("92009",list.get(0).getProductCode());


    }

    @Test
    public void selectCustomer(){
        Enterprise enterprise = Databases.availableDatabases().get("ПРИНТ");
        assertNotNull(enterprise);
        DataSelectable dataSelectable = new MsqlDataSelectableImpl(enterprise);
        assertNotNull(dataSelectable)       ;
        List<Customer> list =  dataSelectable.selectCustomers("K011806");
        assertEquals(list.size(),1);

    }
    @Test
    public void selectVendorForBasePolypack(){
        Enterprise enterprise = Databases.availableDatabases().get("ПОЛИПАК");
        assertNotNull(enterprise);
        DataSelectable dataSelectable = new MsqlDataSelectableImpl(enterprise);
        assertNotNull(dataSelectable)       ;
        List<Vendor> list =  dataSelectable.selectVendors("П001787");
        assertEquals(list.size(),1);
        assertEquals("П001787",list.get(0).getVendNum());
    }
    @Test
    public void selectVendorLcrForBasePolypack(){
        Enterprise enterprise = Databases.availableDatabases().get("ПОЛИПАК");
        assertNotNull(enterprise);
        DataSelectable dataSelectable = new MsqlDataSelectableImpl(enterprise);
        assertNotNull(dataSelectable)       ;
        List<VendorLcr> list =  dataSelectable.selectVendorLcr("П001787=БЕЗ ДОГОВОРА");
        assertEquals(list.size(),1);
        assertEquals("П001787",list.get(0).getVendNum());
        assertEquals("БЕЗ ДОГОВОРА",list.get(0).getLcrNum());
    }



}
