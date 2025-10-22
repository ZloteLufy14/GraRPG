import java.util.Random;

public class Warrior extends Character{
    Random rand = new Random();

    String weapon;
    String skill;

    public Warrior(String name, int level, int strength, int health, int agility, String weapon, String skill) {
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
        System.out.println("Character type: Warrior" + "\nName: " + getName() + "\nLVL: " + getLevel() + "\nStrength: " + getStrength() + "\nHP: " + getHealth() + "\nAgility: " + getAgility() + "\nWeapon: " + this.weapon + "\nSkill: " + this.skill);
    }

    @Override
    public int skill(int monsterHealth, int characterStrength) {
        int damage = characterStrength;
        double damageMultiplier = rand.nextDouble(1) + 1;

        damage *= damageMultiplier;

        monsterHealth -= damage;
        setSkillCooldown(true);
        return monsterHealth;
    }

    @Override
    public int getDefaultHealth(){
        int health = 150;
        for(int i=0;i<getLevel();i++){
            health *= 1.1;
        }

        return health;
    }
     @Override
    public void firstWeapon() {
        setStrength(getStrength()+10);
        setAgility(getAgility()+5);
        setWeapon("Sword");
    }

    @Override
    public void secondWeapon() {
        setStrength(getStrength()+30);
        setAgility(getAgility()+10);
        setWeapon("Axe");
    }

    @Override
    public void thirdWeapon() {
        setStrength(getStrength()+45);
        setAgility(getAgility()+20);
        setWeapon("Lance");
    }

    @Override
    public void fourthWeapon() {
        setStrength(getStrength()+60);
        setAgility(getAgility()+25);
        setWeapon("Pan");
    }
}