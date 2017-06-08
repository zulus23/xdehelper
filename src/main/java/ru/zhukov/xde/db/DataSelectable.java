package ru.zhukov.xde.db;

import ru.zhukov.xde.domain.*;

import java.util.List;

/**
 * Created by Zhukov on 31.05.2017.
 */
public interface DataSelectable {

    List<Item>  selectItems(String... items);
    List<Customer>  selectCustomers(String... items);
    List<CustomerLcr>  selectCustomerLcr(String... items);
    List<Vendor>  selectVendors(String... items);
    List<VendorLcr>  selectVendorLcr(String... items);





}
