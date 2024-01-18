package Game;

import java.util.ArrayList;
import java.util.Map;

import locations.*;
import characters.*;
import items.*;
import java.util.Scanner;


public class Game{
    private ArrayList<Locations> loc;
    private Hero hero;
    private boolean stop;


    public Game(){
        this.loc = new ArrayList<>(23);
        this.createMap();
        this.hero = new Hero(this.loc.get(0), new Weapon("Rusty_sword", 15, 10, 5), new Armor("Ancient_shield", 15, 5, 1));
        this.hero.getHeroLoc().addChar(this.hero);
        this.hero.getInv().addItems(new HealPotion());
        this.stop = false;
    }
    public Hero getHero(){
        return this.hero;
    }

    public void createMap(){
        Locations S1 = new Locations("Room_1", false);
        this.loc.add(0,S1);
        
        
        Locations S2 = new Locations("Room_2");
        Monster M1 = new Monster(S2);
        S2.addChar(M1);
        this.loc.add(1,S2);

        Locations S3 = new Locations("Room_3");
        Monster M2 = new Monster(S3);
        S3.addChar(M2);
        this.loc.add(2,S3);

        Locations S4 = new Locations("Room_4");
        Monster M3 = new Monster(S4);
        S4.addChar(M3);
        this.loc.add(3,S4);

        Locations S5 = new Locations("Room_5");
        Monster M4 = new Monster(S5);
        S5.addChar(M4);
        this.loc.add(4,S5);

        Locations S6 = new Locations("Room_6");
        Monster M5 = new Monster(S6);
        S6.addChar(M5);
        this.loc.add(5,S6);

        Locations S7 = new Locations("Room_7",false);
        Human H1 = new Human("Isaac",S7);
        S7.addChar(H1);
        this.loc.add(6,S7);
        

        Locations S8 = new Locations("Room_8");
        Monster M6 = new Monster(S8);
        S8.addChar(M6);
        this.loc.add(7,S8);

        Locations S9 = new Locations("Room_9",false);
        Human H2 = new Human("Caïn",S9);
        S9.addChar(H2);
        this.loc.add(8,S9);

        Locations S10 = new Locations("Room_10",false);
        Armor A1 = new Armor("Legendary_Armor", 0, 25, 3);
        Chest C1 = new Chest(S10, A1);
        S10.addChar(C1);
        this.loc.add(9,S10);
        
        Locations S11 = new Locations("Room_11",false);
        Human H3 = new Human("Judas",S11);
        S11.addChar(H3);
        this.loc.add(10,S11);

        Locations S12 = new Locations("Room_12");
        Monster M7 = new Monster(S12);
        S12.addChar(M7);
        this.loc.add(11,S12);

        Locations S13 = new Locations("Room_13",false);
        Human H4 = new Human("??? AKA BLUE BABY",S13);
        S13.addChar(H4);
        this.loc.add(12,S13);

        Locations S14 = new Locations("Room_14");
        Monster M8 = new Monster(S14);
        S14.addChar(M8);
        this.loc.add(13,S14);

        Locations S15 = new Locations("Room_15",false);
        Human H5 = new Human("Magdala",S15);
        S15.addChar(H5);
        this.loc.add(14,S15);
        
        Locations S16 = new Locations("Room_16",false);
        Human H6 = new Human("Samson",S16);
        S16.addChar(H6);
        this.loc.add(15,S16);

        Locations S17 = new Locations("Room_17");
        Monster M9 = new Monster(S17);
        S17.addChar(M9);
        this.loc.add(16,S17);

        Locations S18 = new Locations("Room_18");
        Monster M10 = new Monster(S18);
        S18.addChar(M10);
        this.loc.add(17,S18);
        
        Locations S19 = new Locations("Room_19");
        Boss B1 = new Boss();
        S19.addChar(B1);;
        this.loc.add(18,S19);

        Locations S20 = new Locations("Room_20",false);
        Human H7 = new Human("Lazarus",S20);
        S20.addChar(H7);
        this.loc.add(19,S20);

        Locations S21 = new Locations("Room_21",false);
        Human H8 = new Human("Azazel",S21);
        S21.addChar(H8);
        this.loc.add(20,S21);

        Locations S22 = new Locations("Room_22", false);
        Weapon W1 = new Weapon("Legendary_sword", 0, 50, 10);
        Chest C2 = new Chest(S22, W1);
        S22.addChar(C2);
        this.loc.add(21,S22);

        Locations S23 = new Locations("Room_23");
        Boss B2 = new Boss();
        S23.addChar(B2);
        this.loc.add(22,S23);

        //partie des exits
        Exits EXT1 = new Exits(false, S1);
        S2.addExits(S1, EXT1);
        S3.addExits(S1, EXT1);
        S12.addExits(S1, EXT1);

        Exits EXT2 = new Exits(false, S2);
        S1.addExits(S2, EXT2);
        S4.addExits(S2, EXT2);

        Exits EXT3 = new Exits(false, S3);
        S1.addExits(S3, EXT3);
        S5.addExits(S3, EXT3);
        S6.addExits(S3, EXT3);

        Exits EXT4 = new Exits(false, S4);
        S2.addExits(S4, EXT4);
        S13.addExits(S4, EXT4);

        Exits EXT5 = new Exits(false, S5);
        S3.addExits(S5, EXT5);
        S16.addExits(S5, EXT5);
        S20.addExits(S5, EXT5);
        S21.addExits(S5, EXT5);
        
        Exits EXT6 = new Exits(false, S6);
        S3.addExits(S6, EXT6);
        S7.addExits(S6, EXT6);
        
        Exits EXT7 = new Exits(false, S7);
        S6.addExits(S7, EXT7);
        S8.addExits(S7, EXT7);
        
        Exits EXT8 = new Exits(false, S8);
        S7.addExits(S8, EXT8);
        S10.addExits(S8, EXT8);

        Exits EXT9 = new Exits(false, S9);
        S8.addExits(S9, EXT9);
        S11.addExits(S9, EXT9);

        Exits EXT10 = new Exits(true, S10);
        S8.addExits(S10, EXT10);

        Exits EXT11 = new Exits(false, S11);
        S9.addExits(S11, EXT11);
        S12.addExits(S11, EXT11);

        Exits EXT12 = new Exits(false, S12);
        S11.addExits(S12, EXT12);

        Exits EXT13 = new Exits(false, S13);
        S4.addExits(S13, EXT13);
        S14.addExits(S13, EXT13);

        Exits EXT14 = new Exits(false, S14);
        S13.addExits(S14, EXT14);
        S16.addExits(S14, EXT14);

        Exits EXT15 = new Exits(false, S15);
        S14.addExits(S15, EXT15);

        Exits EXT16 = new Exits(false, S16);
        S5.addExits(S16, EXT16);
        S14.addExits(S16, EXT16);
        S17.addExits(S16, EXT16);

        Exits EXT17 = new Exits(false, S17);
        S15.addExits(S17, EXT17);
        S16.addExits(S17, EXT17);
        S18.addExits(S17, EXT17);

        Exits EXT18 = new Exits(false, S18);
        S17.addExits(S18, EXT18);
        S19.addExits(S18, EXT18);
        
        Exits EXT19 = new Exits(false, S19);
        S18.addExits(S19, EXT19);

        Exits EXT20 = new Exits(false, S20);
        S5.addExits(S20, EXT20);
        S22.addExits(S20, EXT20);

        Exits EXT21 = new Exits(false, S21);
        S5.addExits(S21, EXT21);
        
        Exits EXT22 = new Exits(false, S22);
        S23.addExits(S22, EXT22);
        Exits EXT22_Key = new Exits(true, S22);
        S20.addExits(S22, EXT22_Key);

        Exits EXT23 = new Exits(false, S23);
        S22.addExits(S23, EXT23);
    }

    /*public void take(Items i){
        Inventory Inv = this.hero.getInv();
        if (this.hero.getInv().isFull()){
            System.out.println("There is no more space in your backpack");
        }
        else if(this.hero.getInv().isTooMuchWeight(i)){
            System.out.println("you're not strong enough to lift your backpack");
        }else{
            Inv.addItems(i);
        }
    }*/
    public void quit(){
        this.stop = true;
    }

    
    public void launch(){
        createMap();
        String[] arg;
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------");
        System.out.println(("--- Welcome to The A-MAZE-ing Donjon game ! ---"));
        System.out.println("-----------------------------------------------");
        System.out.println("You are a knight and a Monster has destroyed your village.");
        System.out.println("So you decide to kill the Monster, you take your rusty sword and your ancient shield, the village elder gave you his fabulous healing potion.");
        System.out.println("You go to his lair which is in the Mountain next to the village");
        System.out.println("When you reach it, you see the dark entry of a Cave. This Cave seems really deep and dangerous.");
        System.out.println("You decide to enter to kill the Monster, but maybe there was some special Monster so you need to be carefull!\n");
        System.out.println("Rules are simple enter a command to make an action, you can use the command HELP to know what to do.");
        System.out.println("To win you need to kill the boss. GOOD LUCK !! ;)");
        while(this.stop != true){
            Locations lochero = this.hero.getHeroLoc();
            System.out.print("Please enter your command :");
            String cmd = scanner.nextLine();
            arg = cmd.split(" ");
            switch (arg[0]) {
                case "GO":
                    if((arg.length) == 2){
                        this.hero.enter(arg[1]);
                    } else if((arg.length) == 1 ){
                        System.out.println("Missing argument");
                    }else if((arg.length) > 2){
                        System.out.println("Too much argument");
                    }                    
                    break;
                case "HELP":
                    if((arg.length) == 1 ){
                        System.out.println("In this room you can use command :");
                        if(this.hero.getHeroLoc().getExits() != null){
                            System.out.println("- GO arg1 (arg1 is the number of the next room you want and can go)");    
                        }
                        System.out.println("- LOOK [argument] (argument is optionnal and with it you can have information on anything in the current room. If you don't give argument you will get all the possible argument with their utility with general information of the current room)");
                        if(this.hero.getHeroLoc().isChestInLoc() == true || this.hero.getHeroLoc().isHumanInLoc() == true){
                            System.out.println("- TALK (with this function you can talk with the character in the current room)");
                        }
                        if(lochero.isChestInLoc() == true && lochero.getChestInLoc(lochero).getItem() != null || lochero.isHumanInLoc() == true && lochero.getHumanInLoc(lochero).getInv() != null){
                            System.out.println("- TAKE (You can take the items own by the PNJ in front of you by using this command !)");
                        }
                        if(this.hero.getInv().getItems().size() >0 || this.hero.getInv().getFirstInstanceItems("HealPotion") != null || this.hero.getInv().isWeaponInInv() || this.hero.getInv().isShieldInInv()){
                            System.out.println("- USE (this means you can use one Apple or HealPotion from your inventory or equip an Armor/Sword in your inventory)");
                        }
                        if(lochero.isCharInLoc()){
                            System.out.println("- ATTACK(this function allow you to gave a sword or punch attack to try to kill him but he will respond with an attack too !)");
                        }
                        if(this.hero.getInv().getSpace() != 0){
                            System.out.println("- DELETE(this function is used to delete the item you put in the second arguments and delete it from your inventory)");
                        }
                        System.out.println("- QUIT(left the game. Be careful there is no save !)"); 
                    }else {
                        System.out.println("missing argument item to HELP");
                    }            
                    break;
                case "LOOK": 
                    if((arg.length) <= 2){
                        if((arg.length) == 2){
                            if(arg[1].equals("INVENTORY")){
                                this.hero.getInv().printInv();
                            } else if(arg[1].equals("HERO")){
                                System.out.println(this.hero);
                            } else if(arg[1].equals("MAP")){
                                System.out.println("");
                                System.out.println("");
                                System.out.println("Map :");
                                System.out.println("");
                                lochero.PrintMap();
                            } else {
                                System.out.println("Unknown second argument");
                            }
                        }else{
                            System.out.println("Room information :");
                            System.out.println("hero loc : " + this.hero.getHeroLoc().getName());
                            Map<String,Exits> exits = this.hero.getHeroLoc().getExits();
                            for(String i : exits.keySet()){
                                System.out.println(" -Exits : " + i );
                            }
                            
                            System.out.println("");
                            if(lochero.isCharInLoc()){
                                System.out.println(" -Character : ");
                                if(lochero.isBossInLoc()){
                                    System.out.println(lochero.getBossInLoc(lochero));
                                }   
                                if(lochero.isChestInLoc()){
                                    System.out.println(lochero.getChestInLoc(lochero));
                                
                                } 
                                if(lochero.isHumanInLoc()){
                                    System.out.println(lochero.getHumanInLoc(lochero));
                                    
                                } 
                                if(lochero.isMonsterInLoc()){
                                    System.out.println(lochero.getMonsterInLoc(lochero));
                                }
                                System.out.println("");
                                System.out.println("You can use the command LOOK INVENTORY to check your backpack, LOOK HERO to have all the information on the hero or LOOK MAP to see the MAP of the game");
                            }
                        }
                        
                    }
                    else{
                        System.out.println("Too many argument");
                    }
                    break;
                case "TAKE":
                    if((arg.length) <= 1){
                        if(lochero.isChestInLoc() == true && lochero.getChestInLoc(lochero).getHP() > 0){
                            if (lochero.getChestInLoc(lochero).getItem() != null){
                                if (this.hero.getInv().isFull()){
                                    System.out.println("There is no more space in your backpack you can use DELETE to have more space in it");
                                }
                                else if(this.hero.getInv().isTooMuchWeight(lochero.getChestInLoc(lochero).getItem())){
                                    System.out.println("you're not strong enough to lift your backpack you can use DELETE to have more space in it");
                                }else{
                                    this.hero.getInv().addItems(lochero.getChestInLoc(lochero).getItem());
                                    lochero.getChestInLoc(lochero).delItems();
                                }
                                
                            }
                            else{
                                System.out.println("The Chest is empty !");
                            }
                        }
                        else{
                            if(lochero.isHumanInLoc() == true && lochero.getHumanInLoc(lochero).getHP() > 0){
                                if (lochero.getHumanInLoc(lochero).getInv().getItems().size() > 0){
                                    if (this.hero.getInv().isFull()){
                                        System.out.println("There is no more space in your backpack you can use DELETE to have more space in it");
                                    }
                                    else if(this.hero.getInv().isTooMuchWeight(lochero.getHumanInLoc(lochero).getInv().getItems().get(0))){
                                        System.out.println("you're not strong enough to lift your backpack you can use DELETE to have more space in it");
                                    }else{
                                        this.hero.getInv().addItems(lochero.getHumanInLoc(lochero).getInv().getItems().get(0));
                                        System.out.println("you took the item from the human");
                                    }  
                                }
                                else{
                                    System.out.println("this human has no items to give you for the moment.");
                                }
                            }else{
                                System.out.println("there is no item to take");
                            } 
                        }
                    }
                    else{
                        System.out.println("Too many argument");
                    }
                    
                    break;
                case "USE":
                    if ((arg.length) == 2){
                        if(arg[1].equals("Apple")||arg[1].equals("Heal_Potion")){
                        this.hero.use(arg[1]);
                        }
                        else{
                            if(this.hero.getInv().getFirstInstanceItems(arg[1]) instanceof Weapon || this.hero.getInv().getFirstInstanceItems(arg[1]) instanceof Armor){
                                Items p = this.hero.getInv().getFirstInstanceItems(arg[1]);
                                if(p instanceof Weapon){
                                    this.hero.setWeapon(p);
                                    System.out.println("You equip your" + arg[1]);
                                }
                                if(p instanceof Armor){
                                    this.hero.setArmor(p);
                                    System.out.println("You equip your" + arg[1]);
                                }
                            }
                            else{
                                System.out.println("There is no object like this in your inventory");
                            }
                        }
                    }else{
                        System.out.println("missing argument item to USE");
                    }

                    break;
                case "ATTACK":
                    if((arg.length) == 1){
                        if (lochero.getCharacters().size()==2){
                            int shielddamreduction;
                            if(this.hero.getShield() == null){
                                shielddamreduction = 0;
                            }else {
                                shielddamreduction = this.hero.getShield().getDamageReduction();
                            }

                            this.hero.attack(this.hero.getHeroLoc().getCharacters().get(1).getName());
                            
                            if(lochero.isBossInLoc() && lochero.getBossInLoc(lochero).getHP() > 0 ){
                                lochero.getBossInLoc(lochero).attack(this.hero, shielddamreduction);
                            }
                            if(lochero.isHumanInLoc() && lochero.getHumanInLoc(lochero).getHP() > 0){
                                lochero.getHumanInLoc(lochero).attack(this.hero, shielddamreduction);
                            }
                            if(lochero.isMonsterInLoc() &&  lochero.getMonsterInLoc(lochero).getHP() > 0){
                                lochero.getMonsterInLoc(lochero).attack(this.hero, shielddamreduction);
                            }

                        }else{
                            System.out.println("Nothing happened");
                        }
                    }else{
                        System.out.println("Too much argument");
                    }
                    break;
                case "TALK":
                    if((arg.length) >1){
                        System.out.println("Too much arguments in your command");
                    } else {
                        if(lochero.isHumanInLoc() == true && lochero.getHumanInLoc(lochero).getHP() > 0 || lochero.isChestInLoc() == true && lochero.getChestInLoc(lochero).getHP() > 0){
                            this.hero.talk();
                        }else{
                            if((lochero).isHumanInLoc() == true && lochero.getHumanInLoc(lochero).getHP() <= 0 || lochero.isChestInLoc() == true && lochero.getChestInLoc(lochero).getHP() <= 0){
                                System.out.print("A deafening silence still in this room.");
                            } else {
                                System.out.println("The Monster said GEGRJSyGA, but you didn't understand");
                            }
                        }
                    }
                    break;
                case "DELETE":
                    if((arg.length) <= 2){
                        if ((arg.length) == 2){
                            this.hero.getInv().delFirstInstanceOfItem(arg[1]);
                        }else{
                            System.out.println("missing argument item to DELETE");
                        }
                    }
                    else{
                        System.out.println("Too many argument !");
                    }
                    break;           
                case "QUIT":
                    if(arg.length < 2){
                        System.out.println("End of the Game");
                        quit();
                    }else{
                        System.out.println("Too much argument !");
                    }
                    break;
                    
                default:
                    System.out.println("Unknown command");
                    break;
            }
            System.out.println("");
            if (this.hero.getHP() <= 0){
                System.out.println("Game Over");
                quit();
            }
            if (lochero.isClear(this.loc.get(23-1)) || lochero.isClear(this.loc.get(19-1))){
                System.out.println("You Win !");
                quit();
            }
        }
        scanner.close();
    }

}