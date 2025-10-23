import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    // DEKLARACJA KOLORÓW
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String TLO_CZERWONE = "\u001B[41m";
    public static final String CZARNY = "\u001B[90m";


    // METODA ZAPISUJĄCA POSTACIE
    public static void saveCharacters(List<Character> characters) {
        Path path = Paths.get("saves.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
            for (int i = 0; i < characters.size(); i++) {
                Character character = characters.get(i);
                writer.write(character.getClass().getSimpleName() + ";" +
                        character.getName() + ";" +
                        character.getLevel() + ";" +
                        character.getStrength() + ";" +
                        character.getHealth() + ";" +
                        character.getAgility() + ";" +
                        character.getWeapon() + ";" +
                        character.getSkill());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // METODA WCZYTUJĄCA POSTACIE
    public static List<Character> loadCharacters() {
        List<Character> characters = new ArrayList<>();
        Path path = Paths.get("saves.txt");

        if (!Files.exists(path)) {
            return characters;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 8) continue;

                String type = parts[0];
                String name = parts[1];
                int level = Integer.parseInt(parts[2]);
                int strength = Integer.parseInt(parts[3]);
                int health = Integer.parseInt(parts[4]);
                int agility = Integer.parseInt(parts[5]);
                String weapon = parts[6];
                String skill = parts[7];

                Character character = null;

                switch (type) {
                    case "Warrior":
                        character = new Warrior(name, level, strength, health, agility, weapon, skill);
                        break;
                    case "Mage":
                        character = new Mage(name, level, strength, health, agility, weapon, skill);
                        break;
                    case "Archer":
                        character = new Archer(name, level, strength, health, agility, weapon, skill);
                        break;
                    default:
                        System.out.println("Nieznany typ postaci: " + type);
                        continue;
                }

                characters.add(character);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return characters;
    }


    // METODY ATAKU I OBRONY GRACZA I POTWORÓW {
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
    //}

    // PRYMITYWNE "CZYSZCZENIE" KONSOLI
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


    // METODA WALKI
    public static void battle(Character character) throws InterruptedException {

        // ZMIENNE UŻYWANE PÓŹNIEJ {
        int currentWave = 1;
        int whichMonster = 0;
        int monsterDead = 0;
        boolean bossStart = true;
        //}
        System.out.println(GREEN + "First wave" + RESET);

        // RESETOWANIE HP POTWORÓW PRZED FALĄ
        for(int i=0;i<Waves.waves.length;i++){
            for(int j=0;j<Waves.waves[i].length;j++){
                if(Waves.waves[i][j] instanceof Zombie){
                  Waves.waves[i][j].setHealth(70);
                }
                if(Waves.waves[i][j] instanceof Rogue){
                  Waves.waves[i][j].setHealth(60);
                }
                if(Waves.waves[i][j] instanceof Ork){
                  Waves.waves[i][j].setHealth(100);
                }
                if(Waves.waves[i][j] instanceof BoguJerma){
                  Waves.waves[i][j].setHealth(1000);
                }
            }
        }

        // PĘTLA GŁÓWNA
        while (character.getHealth() > 0 && Waves.waves[18][0].getHealth() > 0) {
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
            System.out.println(" ");

            if(selectOption == 4 && character.isSkillCooldown()){
                do{
                    System.out.println(RED + "You are too weak, to use your special abillity second time in a row." + RESET);
                    System.out.println("-----------------------------");
                    System.out.println(" ");

                    System.out.println("Your turn: ");
                    System.out.println("1 - Attack");
                    System.out.println("2 - Defend yourself");
                    System.out.println("3 - Save strength for next attack");
                    System.out.println("4 - Use special abillity - " + character.getSkill());

                    System.out.print("Select option: ");
                    selectOption = scan.nextInt();

                    System.out.println("-----------------------------");
                    System.out.println(" ");
                }while(selectOption == 4);
            }
            
            Monster[] currentWaveArray = Waves.waves[currentWave - 1];

            // WYŚWIETLANIE HP GRACZA I POTWORÓW
            System.out.println("Your HP: " + character.getHealth());
            for(int i=0;i<currentWaveArray.length;i++){
                System.out.println(i+1 + " - " + currentWaveArray[i].getClass().getSimpleName() + " [" + currentWaveArray[i].getHealth() + "]");
            }
            System.out.println("-----------------------------");
            System.out.println(" ");

            if ((selectOption > 0 && selectOption < 5)) {
                
                switch (selectOption) {
                    case 1: // ATTACK
                    
                        System.out.print(BLUE + "Choose monster to attack: " + RESET);
                        selectOption = scan.nextInt();
                        System.out.println("-----------------------------");
                        System.out.println(" ");

                        if(selectOption > 0 && selectOption <= currentWaveArray.length){
                            if(character.dodge(character.getAgility())){
                                System.out.println(BLUE + "dodge" + RESET);
                                if(currentWaveArray[whichMonster] instanceof Rogue) {// ROGUE DODGE
                                    currentWaveArray[whichMonster].setRogueDodge(true);
                                }
                            }else{
                                monsterAttack(currentWaveArray[whichMonster], character);
                            }

                            if(currentWaveArray[selectOption - 1].getRogueDodge()){// ROGUE DODGE
                                System.out.println(YELLOW + "Rogue dodged" + RESET);
                                currentWaveArray[selectOption - 1].setRogueDodge(false);
                            }else{
                                playerAttack(currentWaveArray[selectOption - 1], character);
                            }
                        }else{
                            System.out.println(RED + "Pick correct option." + RESET);
                            System.out.println("-----------------------------");
                            System.out.println(" ");
                        }

                        break;
                    case 2: // DEFENCE
                        if(character.dodge(character.getAgility())){
                            System.out.println("You " + BLUE + "dodged" + RESET + ", so you " + YELLOW + "healed" + RESET);
                            character.setHealth(character.getHealth() + 3);
                        }else{
                            playerDefence(currentWaveArray[whichMonster], character);
                        }
                        break;
                    case 3: // SAVE STRENGTH
                        character.saveStrength();

                        if(character.dodge(character.getAgility())){
                            System.out.println(BLUE + "dodge" + RESET);
                        }else{
                            monsterAttack(currentWaveArray[whichMonster], character);
                        }

                        break;
                    case 4: // SPECIAL SKILL
                        if (!character.isSkillCooldown()) {
                            if (character instanceof Warrior) { // WARRIOR STRONGER ATTACK
                                System.out.println(PURPLE + "You used strong attack skill" + RESET);
                                System.out.print("Choose monster to attack: ");
                                selectOption = scan.nextInt();
                                System.out.println("-----------------------------");
                                System.out.println(" ");

                                if(selectOption > 0 && selectOption <= currentWaveArray.length){
                                    monsterHealth = character.skill(currentWaveArray[selectOption-1].getHealth(), character.getStrength());
                                    currentWaveArray[selectOption-1].setHealth(monsterHealth);
                                }
                            } else if (character instanceof Mage) { // MAGE HEAL
                                System.out.println(PURPLE + "You used heal skill" + RESET);
                                System.out.println("-----------------------------");
                                System.out.println(" ");
                                characterHealth = character.skill(character.getHealth(), character.getStrength());
                                character.setHealth(characterHealth);
                            }
                        }

                        if(character.dodge(character.getAgility())){
                            System.out.println(BLUE + "dodge" + RESET);
                            if(currentWaveArray[whichMonster] instanceof Rogue) { // ROGUE DODGE
                                currentWaveArray[whichMonster].setRogueDodge(true);
                            }
                        }else if(character instanceof Archer && selectOption == 4){ // ARCHER 100% DODGE
                            System.out.println(PURPLE +"You used dodge skill" + RESET);
                            System.out.println("-----------------------------");
                            System.out.println(" ");
                            character.setSkillCooldown(true);
                            if(currentWaveArray[whichMonster] instanceof Rogue) { // ROGUE DODGE
                                currentWaveArray[whichMonster].setRogueDodge(true);
                            }
                        }else{
                            monsterAttack(currentWaveArray[whichMonster], character);
                        }

                        break;
                    default:
                        throw new IllegalArgumentException("Error");
                }
                // KOLEJNY POTWÓR ATAKUJE {
                if(whichMonster >= currentWaveArray.length-1){
                    whichMonster = 0;
                }else{
                    whichMonster++;
                    while(currentWaveArray[whichMonster].getHealth() <= 0 && whichMonster < currentWaveArray.length-1){
                        if(whichMonster >= currentWaveArray.length-1){
                            whichMonster = 0;
                        }else{
                            whichMonster++;
                        }
                    }
                }
                // }
            } else { // WALIDACJA
                System.out.println(RED + "Pick correct option." + RESET);
                System.out.println("-----------------------------");
                System.out.println(" ");
            }
            if(Waves.waves[18][0].getHealth() <= 0){ // INICJALIZACJA WALKI Z BOSSEM
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
                bossStart = true;
                break;
            } else if (character.getHealth() <= 0) { // PORAŻKA
                System.out.println(RED + "Defeat" + RESET);
                bossStart = false;
                break;
            } else {
                TimeUnit.MILLISECONDS.sleep(500);

                // WYŚWIETLANIE HP GRACZA I POTWORA
                System.out.println("Your HP: " + character.getHealth());
                for(int i=0;i<currentWaveArray.length;i++){
                    System.out.println(i+1 + " - " + currentWaveArray[i].getClass().getSimpleName() + " [" + currentWaveArray[i].getHealth() + "]");
                }
                System.out.println("-----------------------------");
                System.out.println(" ");
 
                // SPRAWDZANIE ILE POTWORÓW ZABITYCH
                for(int i=0;i<currentWaveArray.length;i++){
                    if(currentWaveArray[i].getHealth() <= 0){
                        monsterDead++;
                    }
                }

                // KOLEJNA FALA JEŚLI WSZYTSKIE ZGINĘŁY
                if(monsterDead == currentWaveArray.length){
                    character.setHealth(character.getDefaultHealth()); // RESET HP GRACZA
                    character.addLevel(); // DODAWANIE LEVELA PO KAŻDEJ FALI

                    System.out.println(GREEN + "+1 level\n" + "Wave " + currentWave + " Ends " + RESET);
                    currentWave++;
                    monsterDead = 0;
                    System.out.println(GREEN + "Next wave " + currentWave + RESET);
                    currentWaveArray = Waves.waves[currentWave - 1];

                    TimeUnit.MILLISECONDS.sleep(750);

                    System.out.println("");
                    // WYŚWIETLANIE HP POTWORÓW W NASTĘPNEJ FALI
                    System.out.println("Monsters in next wave: ");
                    for(int i=0;i<currentWaveArray.length;i++){
                        System.out.println(i+1 + " - " + currentWaveArray[i].getClass().getSimpleName() + " [" + currentWaveArray[i].getHealth() + "]");
                    }
                    System.out.println("");

    
                }else{
                    monsterDead = 0;
                }
            }
        }
        // WYWOŁANIE WALKI Z BOSSEM
        if(bossStart){
            bossBattle(character, Waves.waves[19][0]);
        }
    }

    // METODA WALKI Z BOSSEM
    public static void bossBattle(Character character, Monster boguJerma) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println();
        System.out.println(TLO_CZERWONE + CZARNY + "Dzisiaj napiszemy sobie metodę..." + RESET);

        // ZMIENNE UŻYWANE PÓŹNIEJ {
        boolean debuffed = false;
        boolean saidLine = false;
        // }

        // GÓWNA PĘTLA
        while (character.getHealth() > 0 && boguJerma.getHealth() > 0) {
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
            System.out.println(" ");

            if(selectOption == 4 && character.isSkillCooldown()){
                do{
                    System.out.println(RED + "You are too weak, to use your special abillity second time in a row." + RESET);
                    System.out.println("-----------------------------");
                    System.out.println(" ");

                    System.out.println("Your turn: ");
                    System.out.println("1 - Attack");
                    System.out.println("2 - Defend yourself");
                    System.out.println("3 - Save strength for next attack");
                    System.out.println("4 - Use special abillity - " + character.getSkill());

                    System.out.print("Select option: ");
                    selectOption = scan.nextInt();

                    System.out.println("-----------------------------");
                    System.out.println(" ");
                }while(selectOption == 4);
            }
            

            System.out.println("Your HP: " + character.getHealth());
            System.out.println("BoguJerma HP: " + boguJerma.getHealth());
            System.out.println("-----------------------------");
            System.out.println(" ");

            if ((selectOption > 0 && selectOption < 5)) {
                
                switch (selectOption) {
                    case 1: // ATTACK
                        if(character.dodge(character.getAgility())){
                            System.out.println(BLUE + "dodge" + RESET);
                            System.out.println(TLO_CZERWONE + CZARNY + "Nienawidze tej Javy..." + RESET);
                        }else{
                            monsterAttack(boguJerma, character);
                        }

                        playerAttack(boguJerma, character);
                        
                        break;
                    case 2: // DEFENCE
                        if(character.dodge(character.getAgility())){
                            System.out.println("You " + BLUE + "dodged" + RESET + ", so you " + YELLOW + "healed" + RESET);
                            System.out.println(TLO_CZERWONE + CZARNY + "Nienawidze tej Javy..." + RESET);
                            character.setHealth(character.getHealth() + 3);
                            boguJerma.setHealth(boguJerma.passiveHealing(boguJerma.getHealth()));
                        }else{
                            playerDefence(boguJerma, character);
                            boguJerma.setHealth(boguJerma.passiveHealing(boguJerma.getHealth()));
                        }
                        break;
                    case 3: // SAVE STRENGTH
                        character.saveStrength();

                        if(character.dodge(character.getAgility())){
                            System.out.println(BLUE + "dodge" + RESET);
                            System.out.println(TLO_CZERWONE + CZARNY + "Nienawidze tej Javy..." + RESET);
                        }else{
                            monsterAttack(boguJerma, character);
                        }
                        boguJerma.setHealth(boguJerma.passiveHealing(boguJerma.getHealth()));
                        break;
                    case 4: // SKILL
                        if (!character.isSkillCooldown()) {
                            if (character instanceof Warrior) { // // WARRIOR STRONGER ATTACK
                                System.out.println(PURPLE + "You used strong attack skill" + RESET);
                                monsterHealth = character.skill(boguJerma.getHealth(), character.getStrength());
                                boguJerma.setHealth(monsterHealth);
                            } else if (character instanceof Mage) { // MAGE HEAL
                                System.out.println(PURPLE + "You used heal skill" + RESET);
                                characterHealth = character.skill(character.getHealth(), character.getStrength());
                                character.setHealth(characterHealth);
                                boguJerma.setHealth(boguJerma.passiveHealing(boguJerma.getHealth()));
                            }
                        }

                        if(character.dodge(character.getAgility())){
                            System.out.println(BLUE + "dodge" + RESET);
                            System.out.println(TLO_CZERWONE + CZARNY + "Nienawidze tej Javy..." + RESET);
                        }else if(character instanceof Archer && selectOption == 4){ // ARCHER DODGE
                            System.out.println(PURPLE + "You used dodge skill" + RESET);
                            System.out.println(TLO_CZERWONE + CZARNY + "Nienawidze tej Javy..." + RESET);
                            character.setSkillCooldown(true);
                            boguJerma.setHealth(boguJerma.passiveHealing(boguJerma.getHealth()));
                        }else{
                            monsterAttack(boguJerma, character);
                        }

                        break;
                    default:
                        throw new IllegalArgumentException("Error");
                }
            } else { // WALIDACJA
                System.out.println(RED + "Pick correct option." + RESET);
                System.out.println("-----------------------------");
                System.out.println(" ");
            }
            if(boguJerma.getHealth() <= 0){ // ZWYCIĘSTWO
                System.out.println(GREEN + "Victory" + RESET);
                System.out.println();
                System.out.println(TLO_CZERWONE + CZARNY + "J4k to-oo? Przeci3ż C++ jest lep-$zy-y-y-y" + RESET);
                break;
            } else if (character.getHealth() <= 0) { // PORAŻKA
                System.out.println(RED + "Defeat" + RESET);
                System.out.println();
                System.out.println(TLO_CZERWONE + CZARNY + "Kto nie ma brzucha, ten kiepsko r... pisze w C++" + RESET);
                break;
            } else {
                TimeUnit.MILLISECONDS.sleep(500);

                System.out.println("Your HP: " + character.getHealth());
                System.out.println("BoguJerma HP: " + boguJerma.getHealth());
                System.out.println("-----------------------------");
                System.out.println(" ");

                if(!saidLine && (boguJerma.getHealth() < 500)){
                    System.out.println(TLO_CZERWONE + CZARNY + "TO JA PRACOWAŁEM W N@SA" + RESET);
                    saidLine = true;
                }

                // DEBUFFY JAK BOSS STRACI 3/4 HP
                if(!debuffed && (boguJerma.getHealth() < 250)){
                    debuffed = true;
                    System.out.println(TLO_CZERWONE + CZARNY + "To dzisiaj napiszecie mi program - 'Reaktor jądrowy' w C++" + RESET);
                    System.out.println("BoguJerma took " + (int)(character.getHealth()*0.15) + " HP from you");
                    System.out.println("BoguJerma took " + (int)(character.getStrength()*0.15) + " strength from you");

                    boguJerma.setHealth(boguJerma.healthBoost(character.getHealth()));
                    character.setHealth(boguJerma.healthDebuff(character.getHealth()));
                    boguJerma.setStrength(boguJerma.strengthBoost(character.getStrength()));
                    character.setStrength(boguJerma.strengthDebuff(character.getStrength()));
                }
            }
        }
    }

    // GŁÓWNA METODA MAIN
    public static void main(String[] args)  throws InterruptedException {
        Scanner scan = new Scanner(System.in);

         List<Character> characters = loadCharacters();
         boolean running = true;

        while (running) {
             System.out.println("Select option: ");
             System.out.println("-----------------------------");
             System.out.println("1 - Play");
             System.out.println("2 - Create character");
             System.out.println("3 - Show all characters");
             System.out.println("4 - End");
             System.out.println("-----------------------------");
            System.out.println(" ");
             int chooseOption = scan.nextInt();
             System.out.println("");
             System.out.println("/////////////////////////////");
             System.out.println("");


             switch (chooseOption) {
                case 1:
                    if (characters.isEmpty()) {
                        System.out.println("You don't have any characters");
                        System.out.println("");
                        System.out.println("/////////////////////////////");
                        System.out.println("");
                    } else {
                        System.out.println("Select character: ");
                        for (int i = 0; i < characters.size(); i++) {
                            System.out.println((i + 1) + " - " + characters.get(i).getName() + " (" + characters.get(i).getClass().getSimpleName() + ", "  +  characters.get(i).getLevel() + " lvl)");
                        }
                        System.out.println("-----------------------------");
                        System.out.println(" ");
                        int pick = scan.nextInt();

                        while(!(pick > 0 && pick <= characters.size())){
                            System.out.println("Wrong option!");
                            pick = scan.nextInt();
                        }
                        Character selected = characters.get(pick - 1);
                        System.out.println("-----------------------------");
                        System.out.println("You chosen: ");
                        selected.characterInfo();
                        System.out.println("");
                        System.out.println("/////////////////////////////");
                        System.out.println("");


                        System.out.println("Select ur weapon: ");
                        System.out.println("-----------------------------");
                        System.out.println(" ");
                    
                        if(selected instanceof Warrior){
                            System.out.println("1 - Sword (default)");
                            System.out.println("2 - Axe (10 lvl)");
                            System.out.println("3 - Lance (20 lvl)");
                            System.out.println("4 - Pan (30 lvl)");
                        }else if(selected instanceof Mage){
                            System.out.println("1 - Wand (default)");
                            System.out.println("2 - Staff (10 lvl)");
                            System.out.println("3 - Spell book (20 lvl)");
                            System.out.println("4 - Super duper spell book (30 lvl)");
                        }else if(selected instanceof Archer){
                            System.out.println("1 - Bow (default)");
                            System.out.println("2 - Better bow, lmao (10 lvl)");
                            System.out.println("3 - Rock (throwable btw) (20 lvl)");
                            System.out.println("4 - Unusual looking stick (kinda sexy) (30 lvl)");
                        }

                        System.out.println("-----------------------------");
                        int chooseWeapon = scan.nextInt();
                        System.out.println("");
                        System.out.println("/////////////////////////////");
                        System.out.println("");
                        

                        boolean isWeaponSelected = false;

                        
                        while(!isWeaponSelected){
                            while(!(chooseWeapon > 0 && chooseWeapon <= 4)){
                            System.out.println("Wrong option!");
                            chooseWeapon = scan.nextInt();
                        }

                         switch (chooseWeapon) {
                            case 1:
                                selected.firstWeapon();
                                isWeaponSelected = true;
                                break;
                            case 2:
                                if (selected.getLevel() >= 10) {
                                    selected.secondWeapon();
                                    isWeaponSelected = true;
                                } else {
                                    System.out.println(RED + "Not enough level!" + RESET);
                                    chooseWeapon = scan.nextInt();
                                }
                                break;
                            case 3:
                                if (selected.getLevel() >= 20) {
                                    selected.thirdWeapon();
                                    isWeaponSelected = true;
                                } else {
                                    System.out.println(RED + "Not enough level!" + RESET);
                                    chooseWeapon = scan.nextInt();
                                }
                                break;
                            case 4:
                                if (selected.getLevel() >= 30) {
                                    selected.fourthWeapon();
                                    isWeaponSelected = true;
                                } else {
                                    System.out.println(RED + "Not enough level!" + RESET);
                                    chooseWeapon = scan.nextInt();
                                }
                                break;
                            default:
                                selected.firstWeapon();
                        }
                    }
                        selected.setHealth(selected.getDefaultHealth());
                        selected.characterInfo();
                        
                        battle(selected);

                        characters.set(pick-1, selected);
                        
                        saveCharacters(characters);
                        
                    }
                    break;
                case 2:
                    System.out.println("Select character: ");
                    System.out.println("-----------------------------");
                    System.out.println("1 - Warrior");
                    System.out.println("2 - Mage");
                    System.out.println("3 - Archer");
                    System.out.println("-----------------------------");
                    System.out.println(" ");
                    int selectCharacter = scan.nextInt();

                    while(!(selectCharacter > 0 && selectCharacter < 4)){
                        System.out.println("Pick between 1 and 3.");
                        selectCharacter = scan.nextInt();
                    };

                        System.out.println("-----------------------------");
                        System.out.println(" ");
                        System.out.print("Type character's name: ");
                        String name = scan.next();

                        Character newCharacter = null;
                        switch (selectCharacter) {
                            case 1:
                                newCharacter = new Warrior(name, 0, 10, 150, 20, "Sword", "Super Attack");
                                break;
                             case 2:
                                newCharacter = new Mage(name, 0, 20, 70, 30, "Wand", "Heal");
                                break;
                            case 3:
                                newCharacter = new Archer(name, 0, 15, 100, 50, "Bow", "Dodge");
                                break;
                            default:
                                throw new IllegalArgumentException("Error");
                        }

                        characters.add(newCharacter);
                        saveCharacters(characters);

                        System.out.println("You created new character");
                        System.out.println("-----------------------------");
                        newCharacter.characterInfo();
                        System.out.println("");
                        System.out.println("/////////////////////////////");
                        System.out.println("");

                    break;
                case 3:
                    if (characters.isEmpty()) {
                        System.out.println("You don't have any characters");
                        System.out.println("");
                        System.out.println("/////////////////////////////");
                        System.out.println("");
                    } else {
                        System.out.println("Your characters:");
                        for (Character character : characters) {
                            System.out.println("-----------------------------");
                            System.out.println(" ");
                            character.characterInfo();
                        }
                        System.out.println("");
                        System.out.println("/////////////////////////////");
                        System.out.println("");
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println("Ahja");
                    System.exit(0);
                    break;
                case 22:
                     Display.kotDisplay();
                     break;
                case 42069:
                    running = false;
                    System.out.println("Circular buffer");
                    break;
                case 1908:
                    running = false;
                    System.out.println("Klubem Łodzi jest...");
                     Display.LKSDisplay();
                    break;
                default:
                    System.out.println("Pick correct option");
                    System.out.println("-----------------------------");
                    System.out.println(" ");
                    break;
            }
        }
    }

}




