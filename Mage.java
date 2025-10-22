import java.util.Random;

public class Mage extends Character{
    Random rand = new Random();

    String weapon;
    String skill;

    public Mage(String name, int level, int strength, int health, int agility, String weapon, String skill) {
        super(name, level, strength, health, agility); 
        this.weapon = weapon;
        this.skill = skill;
    }

    @Override
    public String getWeapon() {
        return weapon;
    }

    @Override
    public void setWeapon(String weapon) {
        this.weapon = weapon; 
    }

    @Override
    public String getSkill() {
        return skill;
    }

    @Override
    public void setSkill(String skill) {
        this.skill = skill; 
    }

    @Override
    public void characterInfo() {
        System.out.println("Character type: Mage" + "\nName: " + getName() + "\nLVL: " + getLevel() + "\nStrength: " + getStrength() + "\nHP: " + getHealth() + "\nAgility: " + getAgility() + "\nWeapon: " + this.weapon + "\nSkill: " + this.skill);
    }

    @Override
    public int skill(int characterHealth, int characterStrength) {
        int hp = 5;
        double hpMultiplier = rand.nextDouble(1) + 1;

        hp *= hpMultiplier;

        characterHealth += hp;
        setSkillCooldown(true);
        return characterHealth;
    }

    @Override
    public int getDefaultHealth(){
        int health = 70;
        for(int i=0;i<getLevel();i++){
            health *= 1.1;
        }
        return health;
    }
}