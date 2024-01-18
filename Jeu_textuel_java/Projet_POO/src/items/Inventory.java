package items;
import java.util.ArrayList;


public class Inventory {
    private ArrayList<Items> items;
    private int length;
    private static final int MAX_WEIGHT = 100;
    private static final int MAX_LENGTH = 15;
    private int max_weight;

    public Inventory(){
        this.length = 0;
        this.items = new ArrayList<>();
        this.max_weight = MAX_WEIGHT;
    }
    
    public boolean isFull(){
        return this.length >= MAX_LENGTH;
    }
    public int getSpaceLeft(){
        return MAX_LENGTH - this.length;
    }
    public int getTotalWeight(){
        int res = 0;
        for(int i = 0; i < length ; i++){
            res += this.items.get(i).getWeight();
        }
        return res;
    }
    public boolean isTooMuchWeight(){
        return getTotalWeight() > this.max_weight;
    }
    public boolean isTooMuchWeight(Items i){
        return (getTotalWeight() + i.getWeight()) >= this.max_weight;
    }
    public int getSpace(){
        return this.length;
    }
    public int getWeightLeft(){
        return this.max_weight - getTotalWeight();
    }
    public boolean isKey(){
        boolean flag = false;
        for (int i = 0; i < this.length;i++){
            if (this.items.get(i) instanceof Exitkey){
                flag = true;       
            }
        }
		return flag;
		
    }

    public Apple getFirstAppleInItem(){
        Apple ret = null;
        for (int i = 0; i < this.length;i++){
            if (this.items.get(i) instanceof Apple && ret == null){
                ret = (Apple) this.items.get(i);      
            }
        }return ret;
    }

    public HealPotion getFirstHealPotionInItem(){
        HealPotion ret = null;
        for (int i = 0; i < this.length;i++){
            if (this.items.get(i) instanceof HealPotion && ret == null){
                ret = (HealPotion) this.items.get(i);      
            }
        }return ret;
    }

    public Items getFirstInstanceItems(String name){
        Items ret = null;
        for (int i = 0; i < this.length;i++){
            if (this.items.get(i).getName().equals(name) && ret == null){
                ret = this.items.get(i);      
            }
        }return ret;
    }

    public void delFirstKey(){
        Items key = getFirstInstanceItems("Key");
        if (this.items.remove(key) == false){
            System.out.println("There is not an item like this in your backpack");
        }else{
            this.items.remove(key);
            length -= 1;
        }
    }

    public boolean isWeaponInInv(){
        boolean ret = false;
        for (int i = 0; i < this.length;i++){
            if (this.items.get(i) instanceof Weapon){
                ret = true;      
            }
        }return ret;
    }

    public boolean isShieldInInv(){
        boolean ret = false;
        for (int i = 0; i < this.length;i++){
            if (this.items.get(i) instanceof Armor){
                ret = true;      
            }
        }return ret;
    }
        
    
    public void delItems(Items i){
        if (this.items.remove(i) == false){
            System.out.println("There is not an item like this in your backpack");
        }else{
            this.items.remove(i);
            length -= 1;
            System.out.println("You erase " + i.getName() + " from your inventory");
        }

    }

    public void delFirstInstanceOfItem(String name){
        delItems(getFirstInstanceItems(name));
    }
    public void addItems(Items i){
        this.items.add(i);
        length += 1;
    }
    public ArrayList<Items> getItems(){
        return this.items;
    }
    public void printInv() {
        System.out.println("Backpack :");
        for(int i = 0 ; i < length ; i++){
            System.out.println(this.items.get(i).getName());
        }
        System.out.println("There is still " + getSpaceLeft() + " places in your backpack.");
        System.out.println("You can carry " + getWeightLeft() + " more pounds");
    }
}
