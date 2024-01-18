package characters;

import items.*;
import locations.*;

public class Chest extends PNJ {
    private int HP = 2000;
    private final String name = "Chest";
    private Locations pos;
    private Items chestItems;

    public Chest (Locations loc,  Items items){
        this.pos = loc;
        this.chestItems = items;
    }
    @Override
    public int getHP() {
        return this.HP;
    }
    @Override
    public void setHP(int i) {
        this.HP = i;
    }
    @Override
    public Armor getShield(){
        return null;
    }

    public Items getItem(){
        if (this.chestItems == null){
            System.out.println("coffre vide");
            return this.chestItems;
        }
        else{
            return this.chestItems;
        }
    }

    public void delItems(){
        if(this.chestItems != null){
            this.chestItems = null;
            System.out.println("you took the Chest item");
        } else{
            System.out.println("The chest still has the item");
        }
    }

	public void interact() {
		System.out.println("Cling Cling(Traduction : this item is yours only if you want!)");
	}
    @Override
    public String toString(){
        String s;
        if(this.HP <= 0){
            s = "DEAD";
        }else{
            s = this.name + " : " + this.HP + " HP ";
        }
        return s;
    }
}
