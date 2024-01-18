package items;

public class Armor extends Items {
    private static final int MIN_DAMAGE = 1;
    private static final int MAX_DAMAGE = 3;
    private final String name;
    private final int weight;
    private int durability;
    private final int damageReduction;

    public Armor(String n, int w, int d, int dam){
        this.name = n;
        this.weight = w;
        this.durability = d;
        this.damageReduction = dam;
    }

    public Armor(String n, int w, int d){
        this.name = n;
        this.weight = w;
        this.durability = d;
        this.damageReduction = MIN_DAMAGE + (int)(Math.random() * ((MAX_DAMAGE - MIN_DAMAGE )+1));
    }
    

    public boolean isBroke(){
        return this.durability <= 0;
    }
    
    public void loseDurability() {
        this.durability -= 1;
    }

    public int getDurability(){
        return this.durability;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }
    @Override
    public String getName() {
        return this.name;
    }

    public int getDamageReduction(){
        return this.damageReduction;
    }
    @Override
    public String toString(){
        String s = this.name;
        return s;
    }
}
