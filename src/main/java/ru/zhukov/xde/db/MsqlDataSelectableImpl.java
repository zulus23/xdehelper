package ru.zhukov.xde.db;

import ru.zhukov.xde.domain.Enterprise;
import ru.zhukov.xde.domain.Item;

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

        String selectedItem =   Arrays.asList(items).stream().map(s -> String.format("'%s'",s)).collect(Collectors.joining(","));

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
