package Test;


import org.junit.Test;
import items.Apple;

import static org.junit.Assert.*;

public class AppleT{
    @Test
    public void testGetHeal(){
        Apple apple1 = new Apple();
        assertEquals(apple1.getHeal(),10);
    }
    @Test
    public void testGetName(){
        Apple apple1 = new Apple();
        assertEquals(apple1.getName(),"Apple");
    }
    @Test
    public void testGetWeigth(){
        Apple apple1 = new Apple();
        assertEquals(apple1.getWeight(),5);
    }
}