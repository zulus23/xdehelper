import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.zhukov.xde.domain.Edizm;
import ru.zhukov.xde.domain.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DomainJPATest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setUp(){
        entityManagerFactory = Persistence.createEntityManagerFactory("serviceGotek");
        entityManager =  entityManagerFactory.createEntityManager();
    }

    @Test
     public void getListEdizm(){
        List<Edizm> edizms = entityManager.createQuery("select i from Edizm i").getResultList();
        assertNotEquals(0,edizms.size());
        Edizm  edizm = entityManager.find(Edizm.class,"КГ");
        assertEquals("КГ",edizm.getId());
     }

    @Test
    public void selectItemById(){
        Item item = entityManager.find(Item.class,"11021000005");
        assertEquals("11021000005",item.getItem());
        assertEquals("ТШТ",item.getEdizm().getId());
        assertEquals("798",item.getEdizm().getCodeSyncWint1C());
     }


    @After
    public void tearDown(){
        entityManager.close();
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }

    }

}
