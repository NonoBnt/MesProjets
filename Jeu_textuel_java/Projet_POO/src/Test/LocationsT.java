package Test;

import locations.*;
import characters.*;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationsT{
    @Test
    public void testGetName(){
        Locations loc = new Locations("test");
        assertEquals(loc.getName(), "test");
    }
    @Test
    public void testGetDes(){
        Locations loc = new Locations("test");
        assertEquals(loc.getDes(), "You’re in " + loc.getName() +", what do you want to do?");
    }
    @Test
    public void testGetState(){
        Locations loc = new Locations("test", false);
        assertFalse(loc.getState());
    }
    @Test
    public void testGetHero(){
        Locations loc = new Locations("test");
        loc.addChar(new Hero(loc));
        assertTrue(loc.getHero() instanceof Hero);
    }
    @Test
    public void testGetEtGetCharacters(){
        Locations loc = new Locations("test");
        ArrayList<Monster> c = new ArrayList<Monster>();
        Monster m = new Monster(loc);
        c.add(m);
        loc.addChar(m);
        assertEquals(loc.getCharacters(), c);
    }/*
    @Test
    public void testDellChar(){
        Locations loc = new Locations("test");
        Exits ex = new Exits(false, loc);
        loc.addExits(loc, ex);
        Hero h = new Hero(loc);
        loc.addChar(h);
        loc.delChar(h);
        assertNull(loc.getCharacters().get(1));
    }*/
    @Test
    public void testGetHumanInLoc(){
        Locations loc = new Locations("test");
        Human m = new Human("test", loc);
        loc.addChar(m);
        assertEquals(loc.getHumanInLoc(loc), m);
    }
    @Test
    public void testGetMonsterInLoc(){
        Locations loc = new Locations("test");
        Monster m = new Monster(loc);
        loc.addChar(m);
        assertEquals(loc.getMonsterInLoc(loc), m);
    }
    @Test
    public void testGetBossInLoc(){
        Locations loc = new Locations("test");
        Boss m = new Boss();
        loc.addChar(m);
        assertEquals(loc.getBossInLoc(loc), m);
    }
    @Test
    public void testGetChestInLoc(){
        Locations loc = new Locations("test");
        Chest m = new Chest(loc, null);
        loc.addChar(m);
        assertEquals(loc.getChestInLoc(loc), m);
    }
    @Test
    public void testGetTargetInLoc(){
        Locations loc = new Locations("test");
        Human m = new Human("test", loc);
        loc.addChar(m);
        assertEquals(loc.getTargetInRoom(m.getName()), m);
    }
    @Test
    public void testIsHumanInLoc1(){
        Locations loc = new Locations("test");
        assertFalse(loc.isHumanInLoc());
    }
    @Test
    public void testIsHumanInLoc2(){
        Locations loc = new Locations("test");
        Human m = new Human("test", loc);
        loc.addChar(m);
        assertTrue(loc.isHumanInLoc());
    }
    @Test
    public void testIsBossInLoc1(){
        Locations loc = new Locations("test");
        assertFalse(loc.isBossInLoc());
    }
    @Test
    public void testIsBossInLoc2(){
        Locations loc = new Locations("test");
        Boss m = new Boss();
        loc.addChar(m);
        assertTrue(loc.isBossInLoc());
    }
    @Test
    public void testIsChestInLoc1(){
        Locations loc = new Locations("test");
        assertFalse(loc.isChestInLoc());
    }
    @Test
    public void testIsChestInLoc2(){
        Locations loc = new Locations("test");
        Chest m = new Chest(loc, null);
        loc.addChar(m);
        assertTrue(loc.isChestInLoc());
    }
    @Test
    public void testIsMonsterInLoc1(){
        Locations loc = new Locations("test");
        assertFalse(loc.isMonsterInLoc());
    }
    @Test
    public void testIsMonsterInLoc2(){
        Locations loc = new Locations("test");
        Monster m = new Monster(loc);
        loc.addChar(m);
        assertTrue(loc.isMonsterInLoc());
    }
    @Test
    public void testIsCharInLoc(){
        Locations loc = new Locations("test");
        assertFalse(loc.isCharInLoc());
    }
    @Test
    public void testSetState(){
        Locations loc = new Locations("test",true);
        loc.setState();
        assertFalse(loc.getState());
    }
    @Test
    public void testIsClear1(){
        Locations loc = new Locations("test", true);
        assertFalse(loc.isClear(loc));
    }
    @Test
    public void testIsClear2(){
        Locations loc = new Locations("test", false);
        assertTrue(loc.isClear(loc));
    }
}
