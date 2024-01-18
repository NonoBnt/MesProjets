package Test;


import org.junit.Test;
import characters.Human;
import items.*;


import static org.junit.Assert.*;

public class HumanT{
    @Test
    public void testSetHp(){
        Human h1 = new Human("test", null);
        h1.setHP(10);
        assertEquals(h1.getHP(), 10); 
    }
    @Test
    public void testGetShield(){
        Human h1 = new Human("test", null);
        assertEquals(h1.getShield(), null); 
    }
    @Test
    public void testGetInv(){
        Human h1 = new Human("test", null);
        Weapon w1 = new Weapon("test", 20, 20, 20);
        h1.getInv().addItems(w1);
        Inventory i1 = h1.getInv();
        //i1.addItems(w1);
        assertEquals(h1.getInv(), i1);
    }
}