import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // List<Character> characters = new ArrayList<>();
        // boolean running = true;

            Monster testMonster = null;
            testMonster = new Zombie("Bartek", 10, 100);

            Character testCharacter = null;
            testCharacter = new Warrior("Jeremi", 0, 10, 150, 20, "Sword", "Super Attack");

            int monsterHealth = testMonster.getHealth();
            int characterHealth = testCharacter.getHealth();

        while (characterHealth > 0 && monsterHealth > 0) {
            System.out.println("Your turn: ");
            System.out.println("1 - Attack");
            System.out.println("2 - Defend yourself");
            System.out.println("3 - Save strength for next attack");
            System.out.println("4 - Use special abillity");

            System.out.print("Select option: ");
            int selectOption = scan.nextInt();

            System.out.println("-----------------------------");

            if (!(selectOption > 0 && selectOption < 5)) {
                switch (selectOption) {
                    case 1:  
                        monsterHealth = testCharacter.attack(testMonster.getHealth(), testCharacter.getStrength());
                        testMonster.setHealth(monsterHealth);

                        characterHealth = testMonster.attack(testCharacter.getHealth(), testMonster.getStrength()); 
                        testCharacter.setHealth(characterHealth); 

                        System.out.println("Your HP: " + testCharacter.getHealth());
                        System.out.println("Monster HP: " + testMonster.getHealth());

                        break;
                    case 2:
                        characterHealth = testCharacter.defence(testCharacter.getHealth(), testMonster.getStrength()); 
                        testCharacter.setHealth(characterHealth); 

                        System.out.println("Your HP: " + testCharacter.getHealth());
                        System.out.println("Monster HP: " + testMonster.getHealth());
                        break;
                    case 3:
                        testCharacter.saveStrength(); 

                        characterHealth = testMonster.attack(testCharacter.getHealth(), testMonster.getStrength());
                        testCharacter.setHealth(characterHealth);

                        System.out.println("Your HP: " + testCharacter.getHealth());
                        System.out.println("Monster HP: " + testMonster.getHealth());
                        break;
                    case 4:
                    
                        break;
                    default:
                        throw new IllegalArgumentException("Error");
                }   
            } else {
                System.out.println("Pick correct option.");
                System.out.println("-----------------------------");   
            }
        }
    
        // while (running) {
        //      System.out.println("Select option: ");
        //      System.out.println("-----------------------------");
        //      System.out.println("1 - Play");
        //      System.out.println("2 - Create character");
        //      System.out.println("3 - Show all characters");
        //      System.out.println("4 - End");
        //      System.out.println("-----------------------------");
        //      int chooseOption = scan.nextInt();
        //      System.out.println("");
        //      System.out.println("/////////////////////////////");
        //      System.out.println("");
             
            
        //      switch (chooseOption) {
        //         case 1:
        //             if (characters.isEmpty()) {
        //                 System.out.println("You don't have any characters");
        //                 System.out.println("");
        //                 System.out.println("/////////////////////////////");
        //                 System.out.println("");
        //             } else {
        //                 System.out.println("Select character: ");
        //                 for (int i = 0; i < characters.size(); i++) {
        //                     System.out.println((i + 1) + " - " + characters.get(i).getName());
        //                 }
        //                 System.out.println("-----------------------------");
        //                 int pick = scan.nextInt();

        //                 while(!(pick > 0 && pick <= characters.size())){
        //                     System.out.println("Wrong option!");
        //                     pick = scan.nextInt();
        //                 }
        //                     Character selected = characters.get(pick - 1);
        //                     System.out.println("-----------------------------");
        //                     System.out.println("You chosen: " + selected.getName());
        //                     selected.characterInfo();
        //                     System.out.println("");
        //                     System.out.println("/////////////////////////////");
        //                     System.out.println("");
        //                     running = false;
        //             }
        //             break;
        //         case 2:
        //             System.out.println("Select character: ");
        //             System.out.println("-----------------------------");
        //             System.out.println("1 - Warrior");
        //             System.out.println("2 - Mage");
        //             System.out.println("3 - Archer");
        //             System.out.println("-----------------------------");
        //             int selectCharacter = scan.nextInt();

        //             while(!(selectCharacter > 0 && selectCharacter < 4)){
        //                 System.out.println("Pick between 1 and 3.");
        //                 selectCharacter = scan.nextInt();
        //             };
                        
        //                 System.out.println("-----------------------------");
        //                 System.out.print("Type character's name: ");
        //                 String name = scan.next();
                    
        //                 Character newCharacter = null;
        //                 switch (selectCharacter) {
        //                     case 1:
        //                         newCharacter = new Warrior(name, 0, 10, 150, 20, "Sword", "Super Attack");
        //                         break;
        //                      case 2:
        //                         newCharacter = new Mage(name, 0, 20, 70, 30, "Magic Wand", "Heal");
        //                         break;
        //                     case 3:
        //                         newCharacter = new Archer(name, 0, 15, 100, 50, "Sword", "Dodge");
        //                         break;
        //                     default:
        //                         throw new IllegalArgumentException("Error");
        //                 }   
                            
        //                 characters.add(newCharacter);
        //                 System.out.println("You created new character");
        //                 System.out.println("-----------------------------");
        //                 newCharacter.characterInfo();
        //                 System.out.println("");
        //                 System.out.println("/////////////////////////////");
        //                 System.out.println("");
                    
        //             break;
        //         case 3:
        //             if (characters.isEmpty()) {
        //                 System.out.println("You don't have any characters");
        //                 System.out.println("");
        //                 System.out.println("/////////////////////////////");
        //                 System.out.println("");
        //             } else {
        //                 System.out.println("Your characters:");
        //                 for (Character character : characters) {
        //                     System.out.println("-----------------------------");
        //                     character.characterInfo();
        //                 }
        //                 System.out.println("");
        //                 System.out.println("/////////////////////////////");
        //                 System.out.println("");
        //             }
        //             break;
        //         case 4:
        //             running = false;
        //             System.out.println("Bye, Bye!");
        //             break;
        //         case 42069:
        //             running = false;
        //             System.out.println("Circular buffer");
        //             break;
        //         case 1908:
        //             running = false;
        //             System.out.println("Klubem Łodzi jest ŁKS!!!!!!!");
        //             break;
        //         default:
        //             System.out.println("Pick correct option");
        //             System.out.println("-----------------------------");
        //             break;
        //     }
        // }
    }
}
