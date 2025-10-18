public class Archer extends Character{
    String weapon;
    String skill;

    public Archer(String name, int level, int strength, int health, int agility, String weapon, String skill) {
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
        System.out.println("Character type: Archer" + "\nName: " + getName() + "\nLVL: " + getLevel() + "\nStrength: " + getStrength() + "\nHP: " + getHealth() + "\nAgility: " + getAgility() + "\nWeapon: " + this.weapon + "\nSkill: " + this.skill);
    }

    @Override
    public int skill(int monsterStrength, int characterHealth) {
        setSkillCooldown(true);
        return characterHealth;
    }
}