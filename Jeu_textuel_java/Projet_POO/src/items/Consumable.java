package items;

public abstract class Consumable extends Items {
    private String name;
    private int weight;
    private int durability;

    public abstract int getWeight();
    public abstract String getName();

}