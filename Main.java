import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    public static void monsterAttack(Monster monster, Character character){
        int characterHealth = monster.attack(character.getHealth(), monster.getStrength());
        character.setHealth(characterHealth);
    }

    public static void playerAttack(Monster monster, Character character){
        int monsterHealth = character.attack(monster.getHealth(), character.getStrength());
        monster.setHealth(monsterHealth);
    }

    public static void playerDefence(Monster monster, Character character){
        int characterHealth = character.defence(character.getHealth(), monster.getStrength());
        character.setHealth(characterHealth);
    }

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void battle(Character character) throws InterruptedException {

        int currentWave = 19;
        int whichMonster = 0;
        int monsterDead = 0;
        boolean jermaLine = false;
        while (character.getHealth() > 0 && Waves.waves[19][0].getHealth() > 0) {
            Scanner scan = new Scanner(System.in);
            int monsterHealth;
            int characterHealth;
    
            System.out.println("Your turn: ");
            System.out.println("1 - Attack");
            System.out.println("2 - Defend yourself");
            System.out.println("3 - Save strength for next attack");
            System.out.println("4 - Use special abillity - " + character.getSkill());

            System.out.print("Select option: ");
            int selectOption = scan.nextInt();

            System.out.println("-----------------------------");

            if(selectOption == 4 && character.isSkillCooldown()){
                do{
                    System.out.println(RED + "You are too weak, to use your special abillity second time in a row." + RESET);
                    System.out.println("-----------------------------");

                    System.out.println("Your turn: ");
                    System.out.println("1 - Attack");
                    System.out.println("2 - Defend yourself");
                    System.out.println("3 - Save strength for next attack");
                    System.out.println("4 - Use special abillity - " + character.getSkill());

                    System.out.print("Select option: ");
                    selectOption = scan.nextInt();

                    System.out.println("-----------------------------");
                }while(selectOption == 4);
            }
            
            Monster[] currentWaveArray = Waves.waves[currentWave - 1];

            System.out.println("Your HP: " + character.getHealth());
            System.out.println("-----------------------------");
            for(int i=0;i<currentWaveArray.length;i++){
                System.out.println(i+1 + " - " + currentWaveArray[i].getName() + " [" + currentWaveArray[i].getHealth() + "]");
            }
            System.out.println("-----------------------------");

            if ((selectOption > 0 && selectOption < 5)) {
                
                switch (selectOption) {
                    case 1:
                    
                        System.out.print("Choose monster to attack: ");
                        selectOption = scan.nextInt();
                        System.out.println("-----------------------------");

                        if(selectOption > 0 && selectOption <= currentWaveArray.length){
                            if(character.dodge(character.getAgility())){
                                System.out.println(BLUE + "dodge" + RESET);
                                if(currentWaveArray[whichMonster] instanceof Rogue) {
                                    currentWaveArray[whichMonster].setRogueDodge(true);
                                }
                            }else{
                                monsterAttack(currentWaveArray[whichMonster], character);
                            }

                            if(currentWaveArray[selectOption - 1].getRogueDodge()){
                                System.out.println(YELLOW + "Drapichrust dodged" + RESET);
                                currentWaveArray[selectOption - 1].setRogueDodge(false);
                            }else{
                                playerAttack(currentWaveArray[selectOption - 1], character);
                            }
                        }else{
                            System.out.println(RED + "Pick correct option." + RESET);
                            System.out.println("-----------------------------");
                        }

                        break;
                    case 2:
                        playerDefence(currentWaveArray[whichMonster], character);

                        break;
                    case 3:
                        character.saveStrength();

                        if(character.dodge(character.getAgility())){
                            System.out.println(BLUE + "dodge" + RESET);
                        }else{
                            monsterAttack(currentWaveArray[whichMonster], character);
                        }

                        break;
                    case 4:
                        if (!character.isSkillCooldown()) {
                            if (character instanceof Warrior) {
                                System.out.print("Choose monster to attack: ");
                                selectOption = scan.nextInt();
                                System.out.println("-----------------------------");

                                if(selectOption > 0 && selectOption <= currentWaveArray.length){
                                    monsterHealth = character.skill(currentWaveArray[selectOption-1].getHealth(), character.getStrength());
                                    currentWaveArray[selectOption-1].setHealth(monsterHealth);
                                }
                            } else if (character instanceof Mage) {
                                characterHealth = character.skill(character.getHealth(), character.getStrength());
                                character.setHealth(characterHealth);
                            } else if (character instanceof Archer) {
                                characterHealth = character.skill(currentWaveArray[whichMonster].getStrength(), character.getHealth());
                                character.setHealth(characterHealth);
                            }
                        }

                        if(character.dodge(character.getAgility())){
                            System.out.println(BLUE + "dodge" + RESET);
                            if(currentWaveArray[whichMonster] instanceof Rogue) {
                                currentWaveArray[whichMonster].setRogueDodge(true);
                            }
                        }else if(character instanceof Archer && selectOption == 4){
                            monsterAttack(currentWaveArray[whichMonster], character);

                            if(currentWaveArray[whichMonster] instanceof Rogue) {
                                currentWaveArray[whichMonster].setRogueDodge(true);
                            }
                        }else{
                            monsterAttack(currentWaveArray[whichMonster], character);
                        }

                        break;
                    default:
                        throw new IllegalArgumentException("Error");
                }
                if(whichMonster >= currentWaveArray.length-1){
                    whichMonster = 0;
                }else{
                    whichMonster++;
                    while(currentWaveArray[whichMonster].getHealth() <= 0 && whichMonster < currentWaveArray.length){
                        whichMonster++;
                    }
                }
            } else {
                System.out.println(RED + "Pick correct option." + RESET);
                System.out.println("-----------------------------");
            }
            if(Waves.waves[18][0].getHealth() <= 0){
                System.out.println(GREEN + "Victory" + RESET);
                TimeUnit.SECONDS.sleep(3);
                System.out.println("You really thought you won...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Time for the real challenge...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Get ready for...");
                TimeUnit.SECONDS.sleep(3);
                clearConsole();
                TimeUnit.SECONDS.sleep(3);
                Display.bogujermaDisplay();
                System.out.println(RED + "██████   ██████   ██████  ██    ██      ██ ███████ ██████  ███    ███  █████   " + RESET);
                System.out.println(RED + "██   ██ ██    ██ ██       ██    ██      ██ ██      ██   ██ ████  ████ ██   ██  " + RESET);
                System.out.println(RED + "██████  ██    ██ ██   ███ ██    ██      ██ █████   ██████  ██ ████ ██ ███████  " + RESET);
                System.out.println(RED + "██   ██ ██    ██ ██    ██ ██    ██ ██   ██ ██      ██   ██ ██  ██  ██ ██   ██  " + RESET);
                System.out.println(RED + "██████   ██████   ██████   ██████   █████  ███████ ██   ██ ██      ██ ██   ██  " + RESET);
                break;
            } else if (character.getHealth() <= 0) {
                System.out.println(RED + "Defeat" + RESET);
            } else {
                TimeUnit.SECONDS.sleep(1);

                System.out.println("Your HP: " + character.getHealth());
                for(int i=0;i<currentWaveArray.length;i++){
                    System.out.println(i+1 + " - " + currentWaveArray[i].getName() + " [" + currentWaveArray[i].getHealth() + "]");
                }
                System.out.println("-----------------------------");
 

                for(int i=0;i<currentWaveArray.length;i++){
                    if(currentWaveArray[i].getHealth() <= 0){
                        monsterDead++;
                    }
                }

                if(monsterDead == currentWaveArray.length){
                    System.out.println(GREEN + "Wave " + currentWave + " Ends " + RESET);
                    currentWave++;
                    monsterDead = 0;
                    System.out.println(GREEN + "Next wave " + currentWave + RESET);
                    character.setHealth(character.getHealth() + 35);
                }else{
                    monsterDead = 0;
                }
            }
        }
    }

    public static void bossBattle(Character character){
        System.out.println("Dzisiaj napiszemy sobie metodę...");


    }

    public static void main(String[] args)  throws InterruptedException {
        Scanner scan = new Scanner(System.in);

//         List<Character> characters = new ArrayList<>();
//         boolean running = true;

        Character testCharacter;
        //testCharacter = new Warrior("Jeremi", 0, 10, 150, 20, "Sword", "Super Attack");
        testCharacter = new Mage("Jeremi", 0, 20, 70, 30, "Magic Wand", "Heal");
        //testCharacter = new Archer("Jeremi", 0, 15, 100, 70, "Sword", "Dodge");

        System.out.println("-----------------------------");
        testCharacter.characterInfo();
        System.out.println("-----------------------------");

        battle(testCharacter);
//        bossBattle(testCharacter);
    
//         while (running) {
//              System.out.println("Select option: ");
//              System.out.println("-----------------------------");
//              System.out.println("1 - Play");
//              System.out.println("2 - Create character");
//              System.out.println("3 - Show all characters");
//              System.out.println("4 - End");
//              System.out.println("-----------------------------");
//              int chooseOption = scan.nextInt();
//              System.out.println("");
//              System.out.println("/////////////////////////////");
//              System.out.println("");
//
//
//              switch (chooseOption) {
//                 case 1:
//                     if (characters.isEmpty()) {
//                         System.out.println("You don't have any characters");
//                         System.out.println("");
//                         System.out.println("/////////////////////////////");
//                         System.out.println("");
//                     } else {
//                         System.out.println("Select character: ");
//                         for (int i = 0; i < characters.size(); i++) {
//                             System.out.println((i + 1) + " - " + characters.get(i).getName());
//                         }
//                         System.out.println("-----------------------------");
//                         int pick = scan.nextInt();
//
//                         while(!(pick > 0 && pick <= characters.size())){
//                             System.out.println("Wrong option!");
//                             pick = scan.nextInt();
//                         }
//                             Character selected = characters.get(pick - 1);
//                             System.out.println("-----------------------------");
//                             System.out.println("You chosen: " + selected.getName());
//                             selected.characterInfo();
//                             System.out.println("");
//                             System.out.println("/////////////////////////////");
//                             System.out.println("");
//                             running = false;
//                     }
//                     break;
//                 case 2:
//                     System.out.println("Select character: ");
//                     System.out.println("-----------------------------");
//                     System.out.println("1 - Warrior");
//                     System.out.println("2 - Mage");
//                     System.out.println("3 - Archer");
//                     System.out.println("-----------------------------");
//                     int selectCharacter = scan.nextInt();
//
//                     while(!(selectCharacter > 0 && selectCharacter < 4)){
//                         System.out.println("Pick between 1 and 3.");
//                         selectCharacter = scan.nextInt();
//                     };
//
//                         System.out.println("-----------------------------");
//                         System.out.print("Type character's name: ");
//                         String name = scan.next();
//
//                         Character newCharacter = null;
//                         switch (selectCharacter) {
//                             case 1:
//                                 newCharacter = new Warrior(name, 0, 10, 150, 20, "Sword", "Super Attack");
//                                 break;
//                              case 2:
//                                 newCharacter = new Mage(name, 0, 20, 70, 30, "Magic Wand", "Heal");
//                                 break;
//                             case 3:
//                                 newCharacter = new Archer(name, 0, 15, 100, 50, "Sword", "Dodge");
//                                 break;
//                             default:
//                                 throw new IllegalArgumentException("Error");
//                         }
//
//                         characters.add(newCharacter);
//                         System.out.println("You created new character");
//                         System.out.println("-----------------------------");
//                         newCharacter.characterInfo();
//                         System.out.println("");
//                         System.out.println("/////////////////////////////");
//                         System.out.println("");
//
//                     break;
//                 case 3:
//                     if (characters.isEmpty()) {
//                         System.out.println("You don't have any characters");
//                         System.out.println("");
//                         System.out.println("/////////////////////////////");
//                         System.out.println("");
//                     } else {
//                         System.out.println("Your characters:");
//                         for (Character character : characters) {
//                             System.out.println("-----------------------------");
//                             character.characterInfo();
//                         }
//                         System.out.println("");
//                         System.out.println("/////////////////////////////");
//                         System.out.println("");
//                     }
//                     break;
//                 case 4:
//                     running = false;
//                     System.out.println("Bye, Bye!");
//                     break;
//                 case 42069:
//                     running = false;
//                     System.out.println("Circular buffer");
//                     break;
//                 case 1908:
//                     running = false;
//                     System.out.println("Klubem Łodzi jest ŁKS!!!!!!!");
//                     break;
//                 default:
//                     System.out.println("Pick correct option");
//                     System.out.println("-----------------------------");
//                     break;
//             }
//         }
    }

}



