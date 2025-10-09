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
        if(getHealth() <= 500){
            monsterStrength *= 2;
        }

        characterHealth -= monsterStrength;
        return characterHealth;
    }
}

