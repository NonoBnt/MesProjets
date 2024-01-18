package characters;

import items.*;

public class Boss extends Characters{
    private static final int MIN_HP = 75;
    private static final int MAX_HP = 200;
    private static final int MIN_DAMAGE = 15;
    private static final int MAX_DAMAGE = 35;
    private int HP;
    private final String name = "Boss";
    private int damage;
    private Weapon weapon;
    private Armor shield;


    public Boss(){
        this.HP = (MIN_HP + (int)(Math.random() * ((MAX_HP - MIN_HP )+1)));
        this.damage = ( MIN_DAMAGE + (int)(Math.random() * ((MAX_DAMAGE - MIN_DAMAGE )+1)));
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
        return this.shield;
    }

    public void attack(Characters ennemi, int damageRed){
        if(this.weapon == null){
            if(ennemi.getShield() == null){
                int Newdamage = (this.damage);
                System.out.println("hit you and cause " + Newdamage + " damage to you.");
                ennemi.setHP(ennemi.getHP() - Newdamage);
            }
            else {
                int Newdamage = (this.damage - damageRed);
                System.out.println("hit you and cause " + Newdamage + " damage to you.");
                ennemi.getShield().loseDurability();
                if(ennemi.getShield().isBroke()){
                    ennemi.setShieldNull();
                    System.out.println("Be careful he broke your shield.");
                }
                ennemi.setHP(ennemi.getHP() - Newdamage);
                
            }
        }
        else {
            if(ennemi.getShield() == null){
                int Newdamage = (this.weapon.getDamage() + this.damage);
                System.out.println("hit you and cause" + " "+ Newdamage + " " + "damage to you.");
                weapon.loseDurability();
                if(this.weapon.isBroke()){
                    this.weapon = null;
                    System.out.println("Yeah he broke his weapon!.");
                }
                ennemi.setHP(ennemi.getHP() - Newdamage);
            }
            else {
                int Newdamage = ((this.weapon.getDamage() + this.damage) - damageRed);
                System.out.println("hit you and cause " + Newdamage + " damage to you.");
                weapon.loseDurability();
                if(this.weapon.isBroke()){
                    this.weapon = null;
                    System.out.println("Yeah he broke his weapon!.");
                }
                if(ennemi.getShield().isBroke()){
                    ennemi.setShieldNull();
                    System.out.println("Be careful he broke your shield.");
                }
                ennemi.setHP(ennemi.getHP() - Newdamage);   
            }
        }
        if(ennemi.getHP() <=0){
            System.out.println(this.getClass().getSimpleName() + " beat you!\n");
        }
    }
    @Override
    public String toString(){
        int realDamage;
        String s;
        if(this.weapon == null){
            realDamage = this.damage;
        } else {
            realDamage = (this.damage + this.weapon.getDamage());
        }
        if(this.HP <= 0){
            s = "DEAD";
        }else{
            s = this.name + " : " + this.HP + " HP " + realDamage + " damage.";
        }
        return s;
    }
}     
