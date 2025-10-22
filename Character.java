import java.util.Random;

public class Character implements Attackable, Skill {
    Random rand = new Random();

    private String name;
    private int level;
    private int strength;
    private int health;
    private int agility;
    private boolean savedStrength = false;
    private boolean skillCooldown = false;

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

    public void addLevel() {
        this.level += 1; 
        this.health *= 1.1;
        this.strength *= 1.1;
    }

    public int getDefaultHealth(){
        throw new UnsupportedOperationException("Not supported yet.");
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
        }else if(health <= 0){
            this.health = 0;
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

    public boolean isSkillCooldown() {
        return skillCooldown;
    }

    public void setSkillCooldown(boolean skillCooldown) {
        this.skillCooldown = skillCooldown;
    }

    public String getWeapon() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setWeapon(String weapon) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getSkill() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSkill(String skill) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void characterInfo() {
        System.out.println("\nName: " + this.name + "\nLVL: " + this.level + "\nStrength: " + this.strength + "\nHealth: " + this.health + "\nAgility: " + this.agility);
    }

    @Override
    public int attack(int monsterHealth, int characterStrength) {
        int damage = characterStrength;
        skillCooldown = false;

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
        skillCooldown = false;

        return characterHealth;
    }

    @Override
    public int saveStrength() {
        skillCooldown = false;
        savedStrength = true;
        return getStrength();
    }

    @Override
    public boolean dodge(int characterAgility) {
        int chanceOfDodge = rand.nextInt(20) + 1;

        if (characterAgility > 0 && characterAgility <= 50) {
            return chanceOfDodge % 10 == 0; // 10%
        } else if (characterAgility > 51 && characterAgility <= 100) {
            return chanceOfDodge % 5 == 0; // 20%
        } else if (characterAgility > 101 && characterAgility <= 150) {
            return chanceOfDodge % 4 == 0; // 25%
        }else  {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public int skill(int Health, int Strength) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}