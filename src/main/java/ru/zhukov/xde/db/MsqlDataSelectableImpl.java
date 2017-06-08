package ru.zhukov.xde.db;

import ru.zhukov.xde.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhukov on 31.05.2017.
 */
public class MsqlDataSelectableImpl  implements DataSelectable{


    private final Enterprise enterprise;

    public  MsqlDataSelectableImpl(Enterprise enterprise){
        this.enterprise = enterprise;
    }


    @Override
    public List<Item> selectItems(String... items) {

        String selectedItem = collectNameForSelect(items);

        String selecItem = String.format("SELECT '%s' as site, i.item, i.description,i.RUSExtDescription, m.Uf_Code_Sync,i.tax_code1"+
                                         ",i.product_code,comm_code as CommConv  FROM item i "+
                                         "JOIN dbo.u_m m ON m.u_m = i.u_m where i.item  in  (%s)",
                                          enterprise.getDbConnect().getNameDatabase(),selectedItem
                                        );


        try(Connection conn = DriverManager.getConnection(enterprise.getDbConnect().connectString());
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(selecItem);) {

            return createListItem(resultSet);

        }
          catch (SQLException e) {
            e.printStackTrace();
        };
        return null;
    }

    private String collectNameForSelect(String[] items) {
        return Arrays.asList(items).stream().map(s -> String.format("'%s'",s)).collect(Collectors.joining(","));
    }

    @Override
    public List<Customer> selectCustomers(String... items) {
        String selectNumberCustomer = collectNameForSelect(items);
        String sqlSelectCustomer = String.format("SELECT '%s',c.cust_num,c.name,c.RUSExtName,c.RUSinn,c.RUSkpp," +
                                         " dbo.GTKFormatAddress(c.cust_num,0,'custaddr') as address,c.RUSokpo,c.zip,ISNULL(co.Uf_RUS_CountryCode,643)"+
                                         " FROM dbo.custaddr c "+
                                         " LEFT JOIN dbo.country co ON c.country = co.country "+
                                         " WHERE c.cust_seq = 0 and c.cust_num in (%s)",enterprise.getDbConnect().getNameDatabase(),selectNumberCustomer);
        try(Connection conn = DriverManager.getConnection(enterprise.getDbConnect().connectString());
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectCustomer);) {

            return createListCustomer(resultSet);

        }
        catch (SQLException e) {
            e.printStackTrace();
        };

        return null;
    }

    @Override
    public List<CustomerLcr> selectCustomerLcr(String... items) {
        String whereString = Arrays.asList(items).stream().map(e -> {
           String[] temp=  e.split("\\=");
           return  String.format("(c.cust_num = '%s' AND l.lcr_num='%s') OR ",temp[0],temp[1]);
        }).collect(Collectors.joining());

        String sqlSelectLcrCustomer = String.format("SELECT '%s',l.lcr_num,c.RUSinn, c.RUSkpp,l.issue_date,c.cust_num,l.curr_code\n" +
                                      "FROM dbo.cust_lcr l\n" +
                                      "JOIN dbo.custaddr c ON c.cust_num = l.cust_num\n" +
                                      "WHERE %s  1=2", enterprise.getDbConnect().getNameDatabase(),whereString);
        try(Connection conn = DriverManager.getConnection(enterprise.getDbConnect().connectString());
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelectLcrCustomer);) {

            return createListLcrCustomer(resultSet);

        }
        catch (SQLException e) {
            e.printStackTrace();
        };
        return null;
    }

    private List<CustomerLcr> createListLcrCustomer(ResultSet resultSet) throws SQLException {
        List<CustomerLcr> customerLcrs = new ArrayList<>();
        while(resultSet.next()){
            CustomerLcr customerLcr = new CustomerLcr();
            customerLcr.setSite(resultSet.getString(1));
            customerLcr.setLcrNum(resultSet.getString(2));
            customerLcr.setInn(resultSet.getString(3));
            customerLcr.setKpp(resultSet.getString(4));
            customerLcr.setDate(resultSet.getString(5));
            customerLcr.setCustNum(resultSet.getString(6));
            customerLcr.setCurrentCode(resultSet.getString(7));
            customerLcrs.add(customerLcr);
        }
        return customerLcrs;
    }


    private List<Customer> createListCustomer(ResultSet resultSet) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        while(resultSet.next()){
            Customer customer = new Customer();
            customer.setSite(resultSet.getString(1));
            customer.setCustNum(resultSet.getString(2));
            customer.setName(resultSet.getString(3));
            customer.setFullName(resultSet.getString(4));
            customer.setInn(resultSet.getString(5));
            customer.setKpp(resultSet.getString(6));
            customer.setAddress(resultSet.getString(7));
            customer.setOkpo(resultSet.getString(8));
            customer.setZip(resultSet.getString(9));
            customer.setCountryCode(resultSet.getString(10));
          customers.add(customer);
        }

        return customers;
    }

    @Override
    public List<Vendor> selectVendors(String... items) {
        return null;
    }



    private List<Item> createListItem(ResultSet resultSet) throws SQLException {
        List<Item> items = new ArrayList<>();
        while(resultSet.next()){
          Item  item = new Item();
          item.setSite(resultSet.getString(1));
          item.setItem(resultSet.getString(2));
          item.setDescription(resultSet.getString(3));
          item.setRusDescription(resultSet.getString(4));
          item.setCodeSync(resultSet.getString(5));
          item.setTax(resultSet.getString(6));
          item.setProductCode(resultSet.getString(7));
          item.setRusDescription(resultSet.getString(8));
          items.add(item);
        }
        return items;
    }
}
