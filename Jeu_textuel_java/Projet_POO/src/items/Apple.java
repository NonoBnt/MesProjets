package items;

public class Apple extends Consumable{
    private final static String NAME = "Apple";
    private final static int WEIGHT = 5;
    private int weight;
    private String name;
    private static final int HEAL = 10;
    private int heal;

    public Apple(){
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