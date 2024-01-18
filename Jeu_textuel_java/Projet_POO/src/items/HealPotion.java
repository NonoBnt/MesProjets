package items;

public class HealPotion extends Consumable{
    private final static String NAME = "Heal_Potion";
    private final static int WEIGHT = 10;
    private int weight;
    private String name;
    
    private static final int HEAL = 30;
    private int heal;

    public HealPotion(){
        this.weight = WEIGHT;
        this.name = NAME;
        this.heal = HEAL;
    }
    
    public int getHeal(){
        return this.heal;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public String getName() {
        return this.name;
    }
}