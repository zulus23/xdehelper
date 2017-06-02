package ru.zhukov.xde.util;

import ru.zhukov.xde.db.DBConnect;
import ru.zhukov.xde.domain.Enterprise;

import java.util.*;

/**
 * Created by Gukov on 29.05.2017.
 */
public class Databases {


    public static Map<String,Enterprise> availableDatabases(){
        Map<String,Enterprise> temp = new HashMap<>();
        temp.computeIfAbsent("ГОТЭК",key-> {
            return new Enterprise(key,"SL_GOTEK", DBConnect.GOTEK);
        });
        temp.computeIfAbsent("ЛИТАР",key-> {
            return new Enterprise(key,"SL_LITAR", DBConnect.Litar);
        });
        temp.computeIfAbsent("ЦПУ",key-> {
            return new Enterprise(key,"SL_CPU", DBConnect.CPU);
        });
        temp.computeIfAbsent("ПОЛИПАК",key-> {
            return new Enterprise(key,"SL_POLYPACK", DBConnect.Polypack);
        });
        temp.computeIfAbsent("ПРИНТ",key-> {
            return new Enterprise(key,"SL_PRINT", DBConnect.Print);
        });
       /* temp.computeIfAbsent("НОВОМОСКОВСК",key-> {
            return new Enterprise(key,"SL_CENTER", ConnectString.GOTEK);
        });*/
        return temp;
    }

    private  int p = 4;

}
