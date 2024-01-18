package Test;


import org.junit.Test;
import characters.Monster;


import static org.junit.Assert.*;

public class MonsterT{
    @Test
    public void testSetHp(){
        Monster m1 = new Monster(null);
        m1.setHP(10);
        assertEquals(m1.getHP(), 10); 
    }
    @Test
    public void testGetShield(){
        Monster m1 = new Monster(null);
        assertEquals(m1.getShield(), null); 
    }
}