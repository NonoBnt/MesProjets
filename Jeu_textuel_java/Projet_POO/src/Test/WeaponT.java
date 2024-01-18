package Test;

import items.Weapon;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeaponT{

    @Test
    public void testLoseDurability(){
        Weapon weapon1 = new Weapon("test", 20, 20, 20);
        weapon1.loseDurability();
        assertEquals(weapon1.getDurability(), 19);
    }

    @Test
    public void testIsBroke1(){
        Weapon weapon1 = new Weapon("test", 20, 1, 10);
        assertFalse(weapon1.isBroke());
    }

    @Test
    public void testIsBroke2(){
        Weapon weapon1 = new Weapon("test", 20, 0, 10);
        assertTrue(weapon1.isBroke());
    }

    @Test
    public void testGetDamage(){
        Weapon weapon1 = new Weapon("test", 20, 20, 20);
        assertEquals(weapon1.getDamage(),20);
    }
    @Test
    public void testGetName(){
        Weapon weapon1 = new Weapon("test", 20, 20, 20);
        assertEquals(weapon1.getName(),"test");
    }
    @Test
    public void testGetWeigth(){
        Weapon weapon1 = new Weapon("test", 20, 20, 20);
        assertEquals(weapon1.getWeight(),20);
    }
}