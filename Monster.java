public class Monster implements Attackable {
    private String name;
    private int strength;
    private int health;

    public Monster(String name, int strength, int health) {
        this.name = name; 
        this.strength = strength;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; 
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if(strength > 0) { 
            this.strength = strength; 
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health > 0) { 
            this.health = health; 
        }
    }

    public void monsterInfo() {
        System.out.println("\nMonster Name: " + this.name  + "\nStrength: " + this.strength + "\nHP: " + this.health);
    }

    @Override
    public int attack(int characterHealth, int monsterStrength) {
        characterHealth -= monsterStrength;
        return characterHealth;
    }

    @Override
    public int defence(int characterHealth, int monsterStrength) {
        return 0;
    }

    @Override
    public int saveStrength() {
        return 0;
    }

}
