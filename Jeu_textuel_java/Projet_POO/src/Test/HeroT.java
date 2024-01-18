package Test;


import org.junit.Test;
import characters.Hero;
import items.*;


import static org.junit.Assert.*;

public class HeroT{
    @Test
    public void testSetHp(){
        Hero h1 = new Hero(null);
        h1.setHP(10);
        assertEquals(h1.getHP(), 10); 
    }
    @Test
    public void testGetShield(){
        Hero h1 = new Hero(null);
        assertEquals(h1.getShield(), null); 
    }
    public void testGetWeapon(){
        Hero h1 = new Hero(null);
        assertEquals(h1.getWeapon(), null); 
    }
    @Test
    public void testSetShieldNull(){
        Hero h1 = new Hero(null);
        h1.setShieldNull();
        assertNull(h1.getShield());
    }
    @Test
    public void testGetInv(){
        Hero h1 = new Hero(null);
        Weapon w1 = new Weapon("test", 20, 20, 20);
        h1.getInv().addItems(w1);
        Inventory i1 = h1.getInv();
        //i1.addItems(w1);
        assertEquals(h1.getInv(), i1);
    }
    @Test
    public void testTheirIsKey1(){
        Hero h1 = new Hero(null);
        assertFalse(h1.theirIskey());
    }
    @Test
    public void testTheirIsKey2(){
        Hero h1 = new Hero(null);
        h1.getInv().addItems(new Exitkey());
        assertTrue(h1.theirIskey());
    }
    @Test
    public void testGetHeroLoc(){
        Hero h1 = new Hero(null);
        assertNull(h1.getHeroLoc());
    }
    @Test
    public void testSetWeapon(){
        Hero h1 = new Hero(null);
        Weapon w1 = new Weapon("test", 20, 20, 20);
        h1.setWeapon(w1);
        assertEquals(h1.getWeapon(),w1);
    }
    @Test
    public void testSetArmor(){
        Hero h1 = new Hero(null);
        Armor a1 = new Armor("test", 20, 20, 20);
        h1.setArmor(a1);
        assertEquals(h1.getShield(),a1);
    }

    
}