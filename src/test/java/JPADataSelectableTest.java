import org.junit.Test;
import ru.zhukov.xde.db.DBConnect;
import ru.zhukov.xde.db.JPADataSelectableImpl;
import ru.zhukov.xde.domain.*;
import ru.zhukov.xde.util.Databases;
import ru.zhukov.xde.xml.ItemsXML;

import java.util.List;

import static org.junit.Assert.*;

public class JPADataSelectableTest {


    @Test
    public void canConnectToDb(){
        Enterprise enterprise = new Enterprise("GOTEK","SL_DB", DBConnect.GOTEK);
        JPADataSelectableImpl selectableJPA = new JPADataSelectableImpl(enterprise);
        List<Item>  itemList = selectableJPA.selectItems("11021000005","11021100534","11021200016");
        assertEquals(3,itemList.size());
    }
    @Test
    public void siteMustBeGotek(){
        Enterprise enterprise = new Enterprise("GOTEK","SL_DB", DBConnect.GOTEK);
        JPADataSelectableImpl selectableJPA = new JPADataSelectableImpl(enterprise);
        List<Item>  itemList = selectableJPA.selectItems("11021000005");
        assertEquals("GOTEK",itemList.get(0).getSite());
    }


    @Test
    public void foundCustomerInDBMustBeOne(){
        Enterprise enterprise = new Enterprise("GOTEK","SL_DB", DBConnect.GOTEK);
        JPADataSelectableImpl selectableJPA = new JPADataSelectableImpl(enterprise);
        List<Customer> customers = selectableJPA.selectCustomers("K000004");
        assertEquals("СОАО БАХУС",customers.get(0).getName());
        assertEquals("643",customers.get(0).getCountryCode());
        assertTrue(customers.get(0).getAddress().contains("СМОЛЕНСК"));

    }

    @Test
    public void foundCustomerInDBMustBeTwo(){
        Enterprise enterprise = new Enterprise("GOTEK","SL_DB", DBConnect.GOTEK);
        JPADataSelectableImpl selectableJPA = new JPADataSelectableImpl(enterprise);
        List<Customer> customers = selectableJPA.selectCustomers("K000004","K000010");
        assertEquals(2,customers.size());
    }

    @Test
    public void foundVendorInDBMustBeOne(){
        Enterprise enterprise = new Enterprise("GOTEK","SL_DB", DBConnect.GOTEK);
        JPADataSelectableImpl selectableJPA = new JPADataSelectableImpl(enterprise);
        List<Vendor> vendors = selectableJPA.selectVendors("П000006");
        assertEquals("УМКА-М ООО",vendors.get(0).getName());
        assertEquals("643",vendors.get(0).getCountryCode());
        assertTrue(vendors.get(0).getAddress().contains("Трубниковский пер"));

    }
    @Test
    public void foundVendorInDBMustBeTwo(){
        Enterprise enterprise = new Enterprise("GOTEK","SL_DB", DBConnect.GOTEK);
        JPADataSelectableImpl selectableJPA = new JPADataSelectableImpl(enterprise);
        List<Vendor> vendors = selectableJPA.selectVendors("П000006","П000005");
        assertEquals(2,vendors.size());


    }
    @Test
    public void customerMustHaveLcr(){
        Enterprise enterprise = new Enterprise("GOTEK","SL_DB", DBConnect.GOTEK);
        JPADataSelectableImpl selectableJPA = new JPADataSelectableImpl(enterprise);
        List<Customer> customers = selectableJPA.selectCustomers("K000004");
        assertTrue(customers.size() == 1);
        List<CustomerLcr> lcrs =  selectableJPA.selectCustomerLcr(String.format("%s=%s",customers.get(0).getCustomerNumber(),"1073-А"));
        assertTrue(lcrs.size() == 1);

    }




}
