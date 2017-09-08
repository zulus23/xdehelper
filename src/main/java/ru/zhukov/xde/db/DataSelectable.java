package ru.zhukov.xde.db;

import ru.zhukov.xde.domain.*;
import ru.zhukov.xde.xml.ItemsXML;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhukov on 31.05.2017.
 */
public interface DataSelectable {

    List<Item>  selectItems(String... items);
    List<Customer>  selectCustomers(String... items);
    List<CustomerLcr>  selectCustomerLcr(String... items);
    List<Vendor>  selectVendors(String... items);
    List<VendorLcr>  selectVendorLcr(String... items);

    default String collectNameForSelect(String[] items) {
        return Arrays.asList(items).stream().map(s -> String.format("'%s'",s)).collect(Collectors.joining(","));
    }
    default String collectLcr(String[] items) {
        return Arrays.asList(items).stream().map(e -> {
            String[] temp=  e.split("\\=");
            return  String.format("(c.cust_num = '%s' AND l.lcr_num='%s') OR ",temp[0],temp[1]);
        }).collect(Collectors.joining());
    }

    default String collectVendorLcr(String[] items) {
        return Arrays.asList(items).stream().map(e -> {
            String[] temp=  e.split("\\=");
            return  String.format("(v.vend_num = '%s' AND l.vend_lcr_num='%s') OR ",temp[0],temp[1]);
        }).collect(Collectors.joining());
    }

}
