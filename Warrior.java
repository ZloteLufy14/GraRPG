public class Warrior extends Character implements Attackable {
    String weapon;
    String skill;

    public Warrior(String name, int level, int strength, int health, int agility, String weapon, String skill) {
        super(name, level, strength, health, agility); 
        this.weapon = weapon;
        this.skill = skill;
    }

    @Override
    public void characterInfo() {
        System.out.println("Name: " + getName() + "\nLVL: " + getLevel() + "\nStrength: " + getStrength() + "\nHP: " + getHealth() + "\nAgility: " + getAgility() + "\nWeapon: " + this.weapon + "\nSkill: " + this.skill);
    }

    @Override
    public void attack() {
        System.out.println("Attack!");
    }

    @Override
    public void defence() {
    }

    @Override
    public void dodge() {
    }

}
