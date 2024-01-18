package characters;

import java.util.Map;

import Actions.*;
import items.*;
import locations.*;

public class Hero extends Characters implements Attack, Use, Talk{
    private static final int MIN_HP = 100;
    private static final int MAX_HP = 200;
    private int HP;
    private final String name = "Hero";
    private final int damage = 10;
    private Locations pos;
    private Weapon weapon;
    private Armor shield;
    private Inventory backpack;


    public Hero(Locations loc){
        this.HP = (MIN_HP + (int)(Math.random() * (( MAX_HP- MIN_HP )+1)));
        this.HP = 100;
        this.weapon = null;
        this.shield = null;
        this.backpack = new Inventory();
        this.pos = loc;
    }
    public Hero(Locations loc, Weapon w, Armor s){
        this.HP = (MIN_HP + (int)(Math.random() * (( MAX_HP- MIN_HP )+1)));
        this.HP = 100;
        this.weapon = w;
        this.shield = s;
        this.backpack = new Inventory();
        this.pos = loc;
    }
    @Override
    public int getHP() {
        return this.HP;
    }
    @Override
    public void setHP(int i) {
        this.HP = i;
    }

    public void setShieldNull(){
        this.shield = null; 
    }
    public boolean theirIskey(){
        return backpack.isKey();
    }

    public Locations getHeroLoc(){
        return this.pos;
    }

    @Override
    public Armor getShield(){
        return this.shield;
    }
    public Weapon getWeapon(){
        return this.weapon;
    }

    public Inventory getInv(){
        return this.backpack;
    }
    public void attack(Characters ennemi){
        if(ennemi.getHP() <= 0){
            System.out.println("You try to fight against " + ennemi.getClass().getSimpleName() + " but he has no hp.");
            System.out.println("Your attack has choke.");
        } 
        else {
            System.out.println("You are fighting against " + ennemi.getClass().getSimpleName() + ".");
            if(this.weapon == null){
                if(ennemi.getShield() == null){
                    int Newdamage = (this.damage);
                    System.out.println("You hit and hurt him. It cause " + Newdamage + " damage to him.");
                    ennemi.setHP(ennemi.getHP() - Newdamage);
                }
                else {
                    int Newdamage = (this.damage - ennemi.getShield().getDamageReduction());
                    System.out.println("You hit and hurt him. It cause " + Newdamage + " damage to him.");
                    ennemi.getShield().loseDurability();
                    if(ennemi.getShield().isBroke()){
                        ennemi.setShieldNull();
                        System.out.println("Yeah he broke his shield!");
                    }
                    ennemi.setHP(ennemi.getHP() - Newdamage);
                }
            }
            else {
                if(ennemi.getShield() == null){
                    int Newdamage = (this.weapon.getDamage() + this.damage);
                    System.out.println("You hit and hurt him. It cause " + Newdamage + " damage to him.");
                    weapon.loseDurability();
                    if(this.weapon.isBroke()){
                        this.weapon = null;
                        System.out.println("Be careful you broke your weapon.");
                    }
                    ennemi.setHP(ennemi.getHP() - Newdamage);
                }
                else {
                    int Newdamage = ((this.weapon.getDamage() + this.damage) - ennemi.getShield().getDamageReduction());
                    System.out.println("You hit and hurt him. It cause" + " " + Newdamage + " " +"damage to him.");
                    weapon.loseDurability();
                    if(this.weapon.isBroke()){
                        this.weapon = null;
                        System.out.println("Be careful you broke your weapon.");
                    }
                    if(ennemi.getShield().isBroke()){
                        ennemi.setShieldNull();
                        System.out.println("Yeah he broke his shield!");
                    }
                    ennemi.setHP(ennemi.getHP() - Newdamage);
                }
            }
            if(ennemi.getHP() <=0){
                System.out.println("You beat the " + ennemi.getClass().getSimpleName() + "!");
                pos.setState();
            } else if((ennemi instanceof Chest)==false){
                if(this.shield != null){
                    this.shield.loseDurability();
                    if(this.shield.isBroke()){
                        this.shield = null;
                        System.out.println("Your shield broke !");
                    }
                } 
            }
        }
    }
    
    public void enter(String location){
        Map<String,Exits> exits = this.pos.getExits();
        if(pos.getState() == false){
            if(exits.containsKey(location)){
                Exits exit = exits.get(location);
                if(exit.getkey() == true){
                    if(theirIskey() == true){
                        exit.useKey();
                        this.backpack.delFirstKey();
                        pos.delChar(this);
                        this.pos = exit.getNextloc();  
                        pos.addChar(this);
                        System.out.println(pos.getDes());
                    }
                    else{
                        System.out.println("You don’t have the key, you can’t go in! Maybe there’s one in another room ");
                    }
                }
                else {
                    pos.delChar(this);
                    this.pos = exit.getNextloc();
                    pos.addChar(this);
                    System.out.println(pos.getDes());
                }
            }
            else {
            System.out.println("error, please enter a valid exit, use LOOK to know the different exits");
            } 
        }
        else {
            System.out.println("! There’s a monster! You can’t change rooms until you defeat it");
        }   
    }

    
	
    @Override
	public void attack(String ennemy) {
        Characters vilain = pos.getTargetInRoom(ennemy);
        if (vilain == null){
            System.out.println("This target is not in this room");
        }
        else{
            attack(vilain);
        } 
	}
    @Override
    public void use(String item) {
        if (item.equals("Apple")){
            if(this.backpack.getFirstAppleInItem()==null){
                    System.out.println("There's no Apple in your Inventory !");
                } else {
                    this.HP += this.backpack.getFirstAppleInItem().getHeal();
                    this.backpack.delItems(this.backpack.getFirstInstanceItems(item));
                    System.out.println("You use your " + item);
                }  
        }
        else{ 
             if(item.equals("Heal_Potion")){
                if(this.backpack.getFirstHealPotionInItem()==null){
                    System.out.println("There's no Heal_Potion in your Inventory !");
                } else {
                    this.HP += this.backpack.getFirstHealPotionInItem().getHeal();
                    this.backpack.delItems(this.backpack.getFirstInstanceItems(item));
                    System.out.println("You use your " + item);
                }   
             }
             else{
                 System.out.println("you can't use that here !");
             }
        }
    }
    public void setWeapon(Items i){//ajouter un if pour les deux fonction 
        this.weapon = (Weapon)i;
    }
    public void setArmor(Items i){
        this.shield = (Armor)i; 
    }

    @Override
    public void talk() {
       if (this.pos.isHumanInLoc()==true){
            Human human = this.pos.getHumanInLoc(this.pos);
            human.interact();
       }
       if (this.pos.isChestInLoc()){
            Chest chest = this.pos.getChestInLoc(this.pos);
            chest.interact(); 
       }
    }
    @Override
    public String toString(){
        int realDamage;
        String s;
        if(this.weapon == null && this.shield == null){
            realDamage = this.damage;
            s = this.name + " : " + this.HP + " HP, " + realDamage + " damage.\nYou have no sword.\nYou have no shield.";
        } else{ 
            if(this.weapon == null){
            realDamage = this.damage;
            s = this.name + " : " + this.HP + " HP, " + realDamage + " damage.\nYou have no sword.\nYour Shield : "+ this.shield + ", with " + this.shield.getDurability()+" durability";
            }else{
                if(this.shield == null){
                    realDamage = (this.damage + this.weapon.getDamage());
                    s = this.name + " : " + this.HP + " HP, " + realDamage + " damage." + "\nYour Sword : " + this.weapon +", with "+ this.weapon.getDurability()+" durability\nYou have no shield.";
                }else{
                    realDamage = (this.damage + this.weapon.getDamage());
                    s = this.name + " : " + this.HP + " HP, " + realDamage + " damage." + "\nYour Sword : " + this.weapon +", with "+ this.weapon.getDurability()+" durability"+"\nYour Shield : "+ this.shield + ", with " + this.shield.getDurability()+" durability";
                }
            }

        }
        return s;
    }
}
