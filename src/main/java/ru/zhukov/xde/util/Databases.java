package ru.zhukov.xde.util;

import ru.zhukov.xde.domain.Enterprise;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Gukov on 29.05.2017.
 */
public class Databases {


    public static Map<String,Enterprise> availableDatabases(){
        Map<String,Enterprise> temp = new HashMap<>();
        temp.computeIfAbsent("ГОТЭК",key-> {
            return new Enterprise(key,"SL_GOTEK");
        });
        temp.computeIfAbsent("ЛИТАР",key-> {
            return new Enterprise(key,"SL_LITAR");
        });
        return temp;
    }
}
