import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // List<Character> characters = new ArrayList<>();
        // boolean running = true;

        System.out.println("Your turn: ");
        System.out.println("1 - Attack");
        System.out.println("2 - Defend yourself");
        System.out.println("3 - Save strength for next attack");
        System.out.println("4 - Use special abillity");

        System.out.print("Select option: ");
        int selectOption = scan.nextInt();

        System.out.println("-----------------------------");

        Monster testMonster = null;
        testMonster = new Zombie("Bartek", 10, 100);

        Character testCharacter = null;
        testCharacter = new Warrior("Jeremi", 0, 10, 150, 20, "Sword", "Super Attack");

        int monsterHealth = testMonster.getHealth();

        if (selectOption == 1 || selectOption == 2 || selectOption == 3 || selectOption == 4) {
            switch (selectOption) {
                case 1:
                    System.out.println("Monster HP: " + testMonster.getHealth());
                    
                    monsterHealth = testCharacter.attack(testMonster.getHealth(), testCharacter.getStrength());
                    testMonster.setHealth(monsterHealth);
                    System.out.println("Monster HP: " + testMonster.getHealth());

                    monsterHealth = testCharacter.attack(testMonster.getHealth(), testCharacter.getStrength());
                    testMonster.setHealth(monsterHealth);
                    System.out.println("Monster HP: " + testMonster.getHealth());
                    break;
                case 2:
                    
                    break;
                case 3:
                    
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
    
        // while(running) {

        //     System.out.println("Options: ");
        //     System.out.println("1 - Play");
        //     System.out.println("2 - Create character");
        //     System.out.println("3 - Show all characters");
        //     System.out.println("4 - End");
        //     System.out.print("Select option: ");
        //     int chooseOption = scan.nextInt();
        //     System.out.println("-----------------------------");
            
        //     switch (chooseOption) {
        //         case 1:
        //             if (characters.isEmpty()) {
        //                 System.out.println("You don't have any characters");
        //                 System.out.println("-----------------------------");
        //             } else {
        //                 System.out.println("Your characters:");
        //                 for (int i = 0; i < characters.size(); i++) {
        //                     System.out.println((i + 1) + " - " + characters.get(i).getName());
        //                 }
        //                 System.out.print("Select character: ");
        //                 int pick = scan.nextInt();
        //                 if (pick > 0 && pick <= characters.size()) {
        //                     Character selected = characters.get(pick - 1);
        //                     System.out.println("-----------------------------");
        //                     System.out.println("You chosen: " + selected.getName());
        //                     selected.characterInfo();
        //                     System.out.println("-----------------------------");
        //                     running = false;
        //                 } else {
        //                     System.out.println("Wrong option!");
        //                     System.out.println("-----------------------------");
        //                 }
        //             }
        //             break;
        //         case 2:
        //             System.out.println("Characters: ");
        //             System.out.println("1 - Warrior");
        //             System.out.println("2 - Mage");
        //             System.out.println("3 - Archer");

        //             System.out.print("Select character: ");
        //             int selectCharacter = scan.nextInt();

        //             System.out.println("-----------------------------");

        //             if (selectCharacter == 1 || selectCharacter == 2 || selectCharacter == 3) {

        //                 System.out.print("Type character's name: ");
        //                 String name = scan.next();
                    
        //                 Character newCharacter = null;

        //                 switch (selectCharacter) {
        //                     case 1:
        //                         newCharacter = new Warrior(name, 0, 10, 150, 20, "Sword", "Super Attack");
        //                         break;
        //                     case 2:
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
        //                 System.out.println("-----------------------------");
        //             } else {
        //                 System.out.println("Pick between 1 and 3.");
        //                 System.out.println("-----------------------------");   
        //             }
        //             break;
        //         case 3:
        //             if (characters.isEmpty()) {
        //                 System.out.println("You don't have any characters");
        //                 System.out.println("-----------------------------");
        //             } else {
        //                 System.out.println("Your characters:");
        //                 for (Character character : characters) {
        //                     character.characterInfo();
        //                     System.out.println("-----------------------------");
        //                 }
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
        //         default:
        //             System.out.println("Pick correct option");
        //             System.out.println("-----------------------------");
        //             break;
        //     }
        // }
    }
}
