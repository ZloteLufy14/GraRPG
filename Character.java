public class Character implements Attackable {
    private String name;
    private int level;
    private int strength;
    private int health;
    private int agility;
    private boolean savedStrength = false;

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

    @Override
    public int attack(int monsterHealth, int characterStrength) {
        int damage = characterStrength;

        if (savedStrength) {
            damage *= 2;
            savedStrength = false;
        }

        monsterHealth -= damage;
        return monsterHealth;
    }

    @Override
    public int defence(int characterHealth, int monsterStrength) {
        characterHealth -= (monsterStrength * 0.5);
        return characterHealth;
    }

    @Override
    public int saveStrength() {
        savedStrength = true;
        return getStrength();
    }
}
