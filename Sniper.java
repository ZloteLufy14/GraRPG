import java.util.Random;

public class Sniper extends Monster{
    Random rand = new Random();

    public Sniper(){
        super("SilverLufa",7,100);
    }

    @Override
    public void monsterInfo(){
        System.out.println("Name: " + getName() + "\nStrength: " + getStrength() + "\nHP: " + getHealth());
    }

    @Override
    public int attack(int characterHealth, int monsterStrength) {
        if(getHealth() <= 80 && getHealth() > 60){
            monsterStrength += 2;
        }else if(getHealth() <= 60 && getHealth() > 40){
            monsterStrength += 6;
        }else if(getHealth() <= 40 && getHealth() > 20){
            monsterStrength += 14;
        }else if(getHealth() <= 20){
            monsterStrength += 22;
        }

        characterHealth -= monsterStrength;
        return characterHealth;
    }
}
