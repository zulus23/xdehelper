package ru.zhukov.xde.util;

import ru.zhukov.xde.db.ConnectString;
import ru.zhukov.xde.domain.Enterprise;

import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by Gukov on 29.05.2017.
 */
public class Databases {


    public static Map<String,Enterprise> availableDatabases(){
        Map<String,Enterprise> temp = new HashMap<>();
        temp.computeIfAbsent("ГОТЭК",key-> {
            return new Enterprise(key,"SL_GOTEK", ConnectString.GOTEK);
        });
        temp.computeIfAbsent("ЛИТАР",key-> {
            return new Enterprise(key,"SL_LITAR", ConnectString.GOTEK);
        });
        temp.computeIfAbsent("ЦПУ",key-> {
            return new Enterprise(key,"SL_CPU", ConnectString.GOTEK);
        });
        temp.computeIfAbsent("ПОЛИПАК",key-> {
            return new Enterprise(key,"SL_POLYPACK", ConnectString.GOTEK);
        });
        temp.computeIfAbsent("ПРИНТ",key-> {
            return new Enterprise(key,"SL_PRINT", ConnectString.GOTEK);
        });
        temp.computeIfAbsent("НОВОМОСКОВСК",key-> {
            return new Enterprise(key,"SL_CENTER", ConnectString.GOTEK);
        });
        return temp;
    }

    private  int p = 4;

}
