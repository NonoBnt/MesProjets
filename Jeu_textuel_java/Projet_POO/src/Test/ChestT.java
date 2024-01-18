package Test;


import org.junit.Test;
import characters.Chest;
import items.Weapon;

import static org.junit.Assert.*;

public class ChestT{
    @Test
    public void testGetHp(){
        Chest c1 = new Chest(null, null);
        assertEquals(c1.getHP(), 2000);
    }
    @Test
    public void testSetHp(){
        Chest c1 = new Chest(null, null);
        c1.setHP(10);
        assertEquals(c1.getHP(), 10); 
    }
    @Test
    public void testGetShield(){
        Chest c1 = new Chest(null, null);
        assertEquals(c1.getShield(), null); 
    }
    @Test 
    public void testGetItem1(){
        Chest c1 = new Chest(null, null);
        assertNull(c1.getItem());
    }
    @Test 
    public void testGetItem2(){
        Weapon i = new Weapon("test", 20, 20, 20);
        Chest c1 = new Chest(null, i);
        assertEquals(c1.getItem(), i);
    }
}