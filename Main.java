import java.util.*;

public class Main {
    public void battle(){

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

//         List<Character> characters = new ArrayList<>();
//         boolean running = true;

        Monster testMonster;
        testMonster = new BoguJerma();
        boolean rogueDodge = false;

        Character testCharacter;
        // testCharacter = new Warrior("Jeremi", 0, 10, 150, 20, "Sword", "Super Attack");
        testCharacter = new Mage("Jeremi", 0, 20, 70, 30, "Magic Wand", "Heal");
        //testCharacter = new Archer("Jeremi", 0, 15, 100, 70, "Sword", "Dodge");

        System.out.println("-----------------------------");
        testCharacter.characterInfo();
        System.out.println("-----------------------------");

        int monsterHealth = testMonster.getHealth();
        int characterHealth = testCharacter.getHealth();


        while (characterHealth > 0 && monsterHealth > 0) {
            System.out.println("Your turn: ");
            System.out.println("1 - Attack");
            System.out.println("2 - Defend yourself");
            System.out.println("3 - Save strength for next attack");
            System.out.println("4 - Use special abillity - " + testCharacter.getSkill());

            System.out.print("Select option: ");
            int selectOption = scan.nextInt();

            System.out.println("-----------------------------");

            if ((selectOption > 0 && selectOption < 5)) {
                switch (selectOption) {
                    case 1:

                        if(testCharacter.dodge(testCharacter.getAgility())){
                            System.out.println("dodge");
                            if(testMonster instanceof Rogue) {
                                rogueDodge = true;
                            }
                        }else{
                            characterHealth = testMonster.attack(testCharacter.getHealth(), testMonster.getStrength());
                            testCharacter.setHealth(characterHealth);
                        }

                        if(rogueDodge){
                            rogueDodge = false;
                        }else{
                            monsterHealth = testCharacter.attack(testMonster.getHealth(), testCharacter.getStrength());
                            testMonster.setHealth(monsterHealth);
                        }

                        break;
                    case 2:
                        characterHealth = testCharacter.defence(testCharacter.getHealth(), testMonster.getStrength());
                        testCharacter.setHealth(characterHealth);

                        break;
                    case 3:
                        testCharacter.saveStrength();

                        if(testCharacter.dodge(testCharacter.getAgility())){
                            System.out.println("dodge");
                        }else{
                            characterHealth = testMonster.attack(testCharacter.getHealth(), testMonster.getStrength());
                            testCharacter.setHealth(characterHealth);
                        }

                        break;
                    case 4:
                        if (!testCharacter.isSkillCooldown()) {
                            if (testCharacter instanceof Warrior) {
                                monsterHealth = testCharacter.skill(testMonster.getHealth(), testCharacter.getStrength());
                                testMonster.setHealth(monsterHealth);

                            } else if (testCharacter instanceof Mage) {
                                characterHealth = testCharacter.skill(testCharacter.getHealth(), testCharacter.getStrength());
                                testCharacter.setHealth(characterHealth);

                            } else if (testCharacter instanceof Archer) {
                                characterHealth = testCharacter.skill(testMonster.getStrength(), testCharacter.getHealth());
                                testCharacter.setHealth(characterHealth);
                            }
                        } else {
                            System.out.println("You are too weak, to use your special abillity second time in a row.");
                            System.out.println("-----------------------------");
                        }

                        if(testCharacter.dodge(testCharacter.getAgility())){
                            System.out.println("dodge");
                            if(testMonster instanceof Rogue) {
                                rogueDodge = true;
                            }
                        }else if(testCharacter instanceof Archer && selectOption == 4){
                            characterHealth = testMonster.attack(testCharacter.getHealth(), testMonster.getStrength());
                            testCharacter.setHealth(characterHealth);

                            if(testMonster instanceof Rogue) {
                                rogueDodge = true;
                            }
                        }else{
                            characterHealth = testMonster.attack(testCharacter.getHealth(), testMonster.getStrength());
                            testCharacter.setHealth(characterHealth);
                        }

                        break;
                    default:
                        throw new IllegalArgumentException("Error");
                }
            } else {
                System.out.println("Pick correct option.");
                System.out.println("-----------------------------");
            }
            if(monsterHealth <= 0){
                System.out.println("Victory");
            } else if (characterHealth <= 0) {
                System.out.println("Defeat");
            } else {
                System.out.println("Your HP: " + testCharacter.getHealth());
                System.out.println("Monster HP: " + testMonster.getHealth());
                System.out.println("-----------------------------");
            }
        }
    
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