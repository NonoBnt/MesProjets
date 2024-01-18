package Test;

import items.Exitkey;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExitkeyT{

    @Test
    public void testGetName(){
         Exitkey key1 = new Exitkey();
        assertEquals(key1.getName(),"Key");
    }
    @Test
    public void testGetWeigth(){
         Exitkey key1 = new Exitkey();
        assertEquals(key1.getWeight(),10);
    }
}