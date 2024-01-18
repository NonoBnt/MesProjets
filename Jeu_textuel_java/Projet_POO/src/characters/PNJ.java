package characters;

import items.*;
import locations.*;

public abstract class PNJ extends Characters{
    private int HP;
    private String name;
    private int damage;
    private Locations pos;
    private Weapon weapon;
    private Armor shield;


    public abstract int getHP();

    public abstract void setHP(int i);

}

