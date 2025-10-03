public class Character {
    private String name;
    private int level;
    private int strength;
    private int health;
    private int agility ;

    public Character(String name, int level, int strength, int health, int agility) {
        this.name = name; 
        this.level = level;
        this.strength = strength;
        this.health = health;
        this.agility = agility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; 
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level >= 0) {
            this.level = level; 
        }
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

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        if(agility > 0) { 
            this.agility = agility; 
        }
    }

    public void characterInfo() {
        System.out.println("\nName: " + this.name + "\nLVL: " + this.level + "\nStrength: " + this.strength + "\nHealth: " + this.health + "\nAgility: " + this.agility);
    }
}
