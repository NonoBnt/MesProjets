package locations;

public class Exits {
    private Locations nextLoc;
    private boolean key;

    public Exits(boolean key,Locations loc) {
        this.key = key;
        this.nextLoc = loc;
    }
    public Locations getNextloc(){
        return this.nextLoc;
    }
    public boolean getkey(){
        return this.key;
    }
    public void useKey(){
        if(key == true){
            this.key = false; 
        }
    }
}
