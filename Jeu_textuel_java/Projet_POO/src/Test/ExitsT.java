package Test;


import org.junit.Test;
import locations.*;

import static org.junit.Assert.*;

public class ExitsT{
    @Test
    public void testGetNextLoc(){
        Locations loc = new Locations("test");
        Exits exit = new Exits(false, loc);
        assertEquals(exit.getNextloc(),loc);
    }
    @Test
    public void testGetKey1(){
        Locations loc = new Locations("test");
        Exits exit = new Exits(false, loc);
        assertFalse(exit.getkey());
    }
    @Test
    public void testGetKey2(){
        Locations loc = new Locations("test");
        Exits exit = new Exits(true, loc);
        assertTrue(exit.getkey());
    }
    @Test
    public void testUseKey1(){
        Locations loc = new Locations("test");
        Exits exit = new Exits(false, loc);
        exit.useKey();
        assertFalse(exit.getkey());
    }
    @Test
    public void testUseKey2(){
        Locations loc = new Locations("test");
        Exits exit = new Exits(true, loc);
        exit.useKey();
        assertFalse(exit.getkey());
    }

}