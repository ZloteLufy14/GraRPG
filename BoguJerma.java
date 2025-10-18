import java.util.Random;

public class BoguJerma extends Monster{
    public static final String TLO_CZERWONE = "\u001B[41m";
    public static final String RESET = "\u001B[0m";
    public static final String CZARNY = "\u001B[30m";

    Random rand = new Random();

    public BoguJerma(){
        super("BoguJerma",20,1000);
    }

    @Override
    public void monsterInfo(){
        System.out.println("Name: " + getName() + "\nStrength: " + getStrength() + "\nHP: " + getHealth());
    }

    public String boguJermaLine(){
        int boguJermaLineRand = rand.nextInt(1) + 4;
        String boguJermaLine = "";


        switch (boguJermaLineRand){
            case 1:
                boguJermaLine = TLO_CZERWONE + CZARNY + "Zamknij mordę" + RESET;
                break;
            case 2:
                boguJermaLine = TLO_CZERWONE + CZARNY + "Panie Olku!" + RESET;
                break;
            case 3:
                boguJermaLine = TLO_CZERWONE + CZARNY + "";
                break;
        }

        return boguJermaLine;
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
        System.out.println(boguJermaLine());
        return characterHealth;
    }

    @Override
    public int passiveHealing(int monsterHealth) {
        if (getHealth() <= 495) {
            monsterHealth += 5;
        }

        return monsterHealth;
    }

    @Override
    public int strengthDebuff(int characterStrength) {
        characterStrength -= (characterStrength*0.15);

        return characterStrength;
    }

    @Override
    public int healthDebuff(int characterHealth) {
        characterHealth -= (characterHealth*0.15);

        return characterHealth;
    }
    

    @Override
    public int healthBoost(int characterHealth) {
        int boguJermaHealth = getHealth();
        boguJermaHealth += (characterHealth*0.15);
 
        return boguJermaHealth;
    }

   @Override
   public int strengthBoost(int characterStrength) {
    int boguJermaStrength = getStrength();
       boguJermaStrength += (characterStrength*0.15);

       return boguJermaStrength;
   }

   @Override
    public String getMonsterClass() {
        return "BoguJerma";
    } 

}

