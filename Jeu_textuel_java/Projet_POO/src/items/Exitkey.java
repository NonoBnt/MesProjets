package items;

public class Exitkey extends Consumable{
    private final static String NAME = "Key";
    private final static int WEIGHT = 10;
    private int weight;
    private String name;
    
    public Exitkey(){
        this.weight = WEIGHT;
        this.name = NAME;
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