package Test;

import items.HealPotion;
import org.junit.Test;
import static org.junit.Assert.*;

public class HealPotionT{
    @Test
    public void testGetHeal(){
        HealPotion healpotion1 = new HealPotion();
        assertEquals(healpotion1.getHeal(),30);
    }
    @Test
    public void testGetName(){
        HealPotion healpotion1 = new HealPotion();
        assertEquals(healpotion1.getName(), "Heal_Potion");
    }
    @Test
    public void testGetWeigth(){
        HealPotion healpotion1 = new HealPotion();
        assertEquals(healpotion1.getWeight(),10);
    }
}