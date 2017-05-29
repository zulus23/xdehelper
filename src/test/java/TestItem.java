import org.junit.Test;

import ru.zhukov.xde.domain.Item;

import static org.junit.Assert.*;

/**
 * Created by Gukov on 29.05.2017.
 */
public class TestItem {

  @Test
  public void equalsMustBeByItem(){
      Item item = new Item();
      item.setItem("Hello");
      Item itemOther = new Item();
      itemOther.setItem("Hello");
      assertEquals(item,itemOther);


  }
}
