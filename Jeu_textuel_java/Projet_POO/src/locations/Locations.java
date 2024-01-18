package locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import characters.*;

public class Locations {
    private static final boolean DEFAULT_STATE = true;
    private final String Name;
    private final String Description;
    private Map<String,Exits> exits ;
    private ArrayList<Characters> peoples ;
    private boolean state;

    public Locations(String name){
        this.exits = new HashMap<>();
        this.Name = name;
        this.Description ="You’re in "+name+", what do you want to do?";
        this.state = DEFAULT_STATE;
        this.peoples = new ArrayList<>(2);
    }

    public Locations(String name, boolean state){
        this.Name = name;
        this.Description ="You’re in "+name+", what do you want to do?";
        this.state = state;
        this.peoples = new ArrayList<>(2);
        this.exits = new HashMap<>();
    }

    // Les fonctions add/del 
    //----------------------------------------------------------------------------------
    public void addChar(Characters people){
        if(people instanceof Monster || people instanceof Human || people instanceof Boss){
            this.peoples.add(people);
        } else this.peoples.add(0, people);
    }
    public void delChar(Characters people){
        if(this.exits.size() > 0){
            if(people instanceof Monster || people instanceof Human || people instanceof Boss){
                this.peoples.remove(1);
            } else this.peoples.remove(0);
        }
    }
    public void addExits(Locations loc,Exits exit){
        exits.put(loc.getName(),exit);
    }

    //Les fonctions getter
    //----------------------------------------------------------------------------------
    public String getName(){
        return this.Name;
    }
    public String getDes(){
        return this.Description;
    }
    public boolean getState(){
        return this.state;
    }
    public Map<String,Exits> getExits(){
        return exits;
    }
    public ArrayList<Characters> getCharacters(){
        return this.peoples;
    }
    public Hero getHero(){
        return (Hero)this.peoples.get(0);
    }
    public Human getHumanInLoc(Locations loc){
        Human ret = null;
		for (int i = 0; i < this.peoples.size(); i++){
            if (peoples.get(i) instanceof Human){
                ret = (Human) peoples.get(i);
            }
        }
        return ret;
    }
    public Chest getChestInLoc(Locations loc){
        Chest ret = null;
		for (int i = 0; i < this.peoples.size(); i++){
            if (peoples.get(i) instanceof Chest){
                ret = (Chest) peoples.get(i);
            }
        }
        return ret;
    }
    public Characters getTargetInRoom(String name){
        Characters ret = null;
        for(int i = 0; i < this.peoples.size();i++){
            if (this.peoples.get(i).getName() == name){
                ret = this.peoples.get(i);
            }
        }
        return ret;
    }
    public Monster getMonsterInLoc(Locations loc){
        Monster ret = null;
		for (int i = 0; i < this.peoples.size(); i++){
            if (peoples.get(i) instanceof Monster){
                ret = (Monster) peoples.get(i);
            }
        }
        return ret;
    }
    public Boss getBossInLoc(Locations loc){
        Boss ret = null;
		for (int i = 0; i < this.peoples.size(); i++){
            if (peoples.get(i) instanceof Boss){
                ret = (Boss) peoples.get(i);
            }
        }
        return ret;
    }
    


    // Les autres fonctions
    //----------------------------------------------------------------------------------
    public boolean isHumanInLoc(){
        boolean ret = false;
		for (int i = 0; i < this.peoples.size(); i++){
            if (peoples.get(i) instanceof Human ){
                ret = true;
            }
        }
        return ret;
    }
    public boolean isChestInLoc(){
        boolean ret = false;
		for (int i = 0; i < this.peoples.size(); i++){
            if (peoples.get(i) instanceof Chest ){
                ret = true;
            }
        }
        return ret;
    }

    public boolean isCharInLoc(){
        boolean ret = false;
		for (int i = 0; i < this.peoples.size(); i++){
            if (peoples.get(i) instanceof Monster || peoples.get(i) instanceof Human || peoples.get(i) instanceof Chest || peoples.get(i) instanceof Boss){
                ret = true;
            }
        }
        return ret;
    }
     public boolean isMonsterInLoc(){
        boolean ret = false;
		for (int i = 0; i < this.peoples.size(); i++){
            if (peoples.get(i) instanceof Monster){
                ret = true;
            }
        }
        return ret;
    }
     public boolean isBossInLoc(){
        boolean ret = false;
		for (int i = 0; i < this.peoples.size(); i++){
            if (peoples.get(i) instanceof Boss){
                ret = true;
            }
        }
        return ret;
    }
    public void setState(){
        this.state = false;
    }

    public boolean isClear(Locations loc){
        if(loc.state == true){
            return false;
        } else {return true;}
    }

    public void PrintMap(){
        System.out.println("                    ----------          ----------          ----------          ----------");
        System.out.println("                    |        |          |        |          |        |          |        |");
        System.out.println("                    | Room_4 | ======== | Room_13| ======== | Room_14| ======== | Room_15|");
        System.out.println("                    |        |          |        |          |        |          |        |");
        System.out.println("                    ----------          ----------          ----------          ----------");
        System.out.println("                        ||                                      ||                  ||    ");
        System.out.println("                        ||                                      ||                  ||    ");
        System.out.println("                        ||                                      ||                  ||    ");
        System.out.println("                    ----------                              ----------          ----------          ----------          ----------");
        System.out.println("                    |        |                              |        |          |        |          |        |          |        |");
        System.out.println("                    | Room_2 |                              | Room_16| ======== | Room_17| ======== | Room_18| ======== | Room_19|");
        System.out.println("                    |        |                              |        |          |        |          |        |          |        |");
        System.out.println("                    ----------                              ----------          ----------          ----------          ----------");
        System.out.println("                        ||                                      ||                        ");
        System.out.println("                        ||                                      ||                        ");
        System.out.println("                        ||                                      ||                        ");
        System.out.println("----------          ----------          ----------          ----------          ----------");
        System.out.println("|        |          |        |          |        |          |        |          |        |");
        System.out.println("| Room_12| ======== | Room_1 | ======== | Room_3 | ======== | Room_5 | ======== | Room_21|");
        System.out.println("|        |          |        |          |        |          |        |          |        |");
        System.out.println("----------          ----------          ----------          ----------          ----------");
        System.out.println("    ||                                      ||                  ||                        ");
        System.out.println("    ||                                      ||                  ||                        ");
        System.out.println("    ||                                      ||                  ||                        ");
        System.out.println("----------          ----------          ----------          ----------          ----------");
        System.out.println("|        |          |        |          |        |          |        |          |        |");
        System.out.println("| Room_11| ======== | Room_9 |          | Room_6 |          | Room_20| ======== | Room_22|");
        System.out.println("|        |          |        |          |        |          |        |          |        |");
        System.out.println("----------          ----------          ----------          ----------          ----------");
        System.out.println("                        ||                  ||                                      ||    ");
        System.out.println("                        ||                  ||                                      ||    ");
        System.out.println("                        ||                  ||                                      ||    ");
        System.out.println("----------          ----------          ----------                              ----------");
        System.out.println("|        |          |        |          |        |                              |        |");
        System.out.println("| Room_10| ======== | Room_8 | ======== | Room_7 |                              | Room_23|");
        System.out.println("|        |          |        |          |        |                              |        |");
        System.out.println("----------          ----------          ----------                              ----------");
    }
}
