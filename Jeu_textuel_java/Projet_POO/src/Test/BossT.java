package Test;


import org.junit.Test;
import characters.Boss;

import static org.junit.Assert.*;

public class BossT{
    @Test
    public void testSetHp(){
        Boss b1 = new Boss();
        b1.setHP(10);
        assertEquals(b1.getHP(), 10); 
    }
    @Test
    public void testGetShield(){
        Boss b1 = new Boss();
        assertEquals(b1.getShield(), null); 
    }
}