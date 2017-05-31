package ru.zhukov.xde.db;

import ru.zhukov.xde.domain.Item;

import java.util.List;

/**
 * Created by Zhukov on 31.05.2017.
 */
public interface DataSelectable {

    List<Item>  selectItems(String... items);
}
