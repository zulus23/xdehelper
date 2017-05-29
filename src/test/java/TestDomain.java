import org.junit.Test;
import ru.zhukov.xde.util.Databases;


import static org.junit.Assert.*;
/**
 * Created by Gukov on 29.05.2017.
 */

public class TestDomain {

    @Test
    public  void listEnterpriseMustContainGOTEKName(){
        assertSame("ГОТЭК",Databases.availableDatabases().get("ГОТЭК").getName());
    }

    @Test
    public  void listEnterpriseCountMustBeTwo(){
        assertEquals(2, Databases.availableDatabases().size());
    }

}
