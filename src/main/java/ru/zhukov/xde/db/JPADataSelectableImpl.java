package ru.zhukov.xde.db;

import ru.zhukov.xde.domain.*;
import ru.zhukov.xde.xml.ItemsXML;


import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JPADataSelectableImpl implements DataSelectable{

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private final Enterprise enterprise;

    public JPADataSelectableImpl(Enterprise enterprise) {
        this.enterprise = enterprise;
        entityManagerFactory = Persistence.createEntityManagerFactory(enterprise.getDbConnect().persistenceName());

    }


    @Override
    public List<Item> selectItems(String... items) {
        try{
            entityManager = entityManagerFactory.createEntityManager();

            List<Item> result = entityManager.createQuery("select  i from Item i where i.item in :listItem")
                    .setParameter("listItem", Arrays.asList(items))
                    .getResultList();
            result.forEach(i -> i.setSite(enterprise.getDbConnect().getNameDatabase()));
            return result;
        } catch (Exception e){
            throw new RuntimeException("При выполнении запроса к БД произошла ошибка",e);
        }
        finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }

    @Override
    public List<Customer> selectCustomers(String... customers) {

        try{
            entityManager = entityManagerFactory.createEntityManager();
            String selectNumberCustomer = collectNameForSelect(customers);
            return  entityManager.createNativeQuery(String.format("SELECT c.cust_num,'%s' as site, c.name,c.RUSExtName,c.RUSinn,c.RUSkpp," +
                    " dbo.GTKFormatAddress(c.cust_num,0,'custaddr') as address,c.RUSokpo,c.zip,ISNULL(co.Uf_RUS_CountryCode,643) as countryCode "+
                    " FROM dbo.custaddr c "+
                    " LEFT JOIN dbo.country co ON c.country = co.country "+
                    " WHERE c.cust_seq = 0 and c.cust_num in (%s)",enterprise.getDbConnect().getNameDatabase(),selectNumberCustomer), Customer.class/*"customer.custAddressForXMLMap"*/)
                    .getResultList();



        } catch (Exception e){
            throw new RuntimeException("При выполнении запроса к БД произошла ошибка",e);
        }
        finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<CustomerLcr> selectCustomerLcr(String... lcr) {

        try{
            entityManager = entityManagerFactory.createEntityManager();
            String whereString = collectLcr(lcr);
            return  entityManager.createNativeQuery(String.format("SELECT '%s' as site,l.lcr_num,c.RUSinn, c.RUSkpp,l.issue_date,c.cust_num,l.curr_code " +
                    " FROM dbo.cust_lcr l " +
                    " JOIN dbo.custaddr c ON c.cust_num = l.cust_num " +
                    " WHERE %s  1=2", enterprise.getDbConnect().getNameDatabase(),whereString),CustomerLcr.class)
                    .getResultList();



        } catch (Exception e){
            throw new RuntimeException("При выполнении запроса к БД произошла ошибка",e);
        }
        finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Vendor> selectVendors(String... vendors) {

        try{
            entityManager = entityManagerFactory.createEntityManager();
            String  selectNumberVendor = collectNameForSelect(vendors);
            return entityManager.createNativeQuery(String.format("SELECT '%s' as site,v.vend_num,v.name,v.RUSExtName,v.RUSinn,v.RUSkpp," +
                    " dbo.GTKFormatAddress(v.vend_num,0,'vendaddr') as address,v.RUSokpo,v.zip,ISNULL(co.Uf_RUS_CountryCode,643) as countryCode"+
                    " FROM dbo.vendaddr v "+
                    " LEFT JOIN dbo.country co ON v.country = co.country "+
                    " WHERE  v.vend_num in (%s)",enterprise.getDbConnect().getNameDatabase(),selectNumberVendor),Vendor.class).getResultList();


        } catch (Exception e){
            throw new RuntimeException("При выполнении запроса к БД произошла ошибка",e);
        }
        finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }

    }

    @Override
    public List<VendorLcr> selectVendorLcr(String... items) {
        return null;
    }




}
