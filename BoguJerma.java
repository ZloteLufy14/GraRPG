import java.util.Random;

public class BoguJerma extends Monster{
    Random rand = new Random();

    public BoguJerma(){
        super("BoguJerma",20,1000);
    }

    @Override
    public void monsterInfo(){
        System.out.println("Name: " + getName() + "\nStrength: " + getStrength() + "\nHP: " + getHealth());
    }

    @Override
    public int attack(int characterHealth, int monsterStrength) {
        double damageMultiplier = rand.nextDouble(1) + 1;

        if(getHealth() > 500) {
            monsterStrength *= damageMultiplier;
        }

        if(getHealth() <= 500){
            monsterStrength *= 2;
        }

        characterHealth -= monsterStrength;
        return characterHealth;
    }

    public int passiveHealing(int monsterHealth) {
        if (getHealth() <= 470) {
            monsterHealth += 30;
        }

        return monsterHealth;
    }

    public int strengthDebuff(int characterStrength) {
        characterStrength -= (characterStrength*0.15);

        return characterStrength;
    }

    public int healthDebuff(int characterHealth) {
        characterHealth -= (characterHealth*0.15);

        return characterHealth;
    }

//    public int healthDebuff(int characterHealth) {
//        characterHealth -= (characterHealth*0.15);
//
//        return characterHealth;
//    }
//
//    public int healthDebuff(int characterHealth) {
//        characterHealth -= (characterHealth*0.15);
//
//        return characterHealth;
//    }

}

