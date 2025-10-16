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

    public String boguJermaLine(){
        int boguJermaLineRand = rand.nextInt(1) + 4;
        String boguJermaLine = "";


        switch (boguJermaLineRand){
            case 1:
                boguJermaLine = "Zamknij mordę";
                break;
            case 2:
                boguJermaLine = "Panie Olku!";
                break;
            case 3:
                boguJermaLine = "To dzisiaj napiszecie mi program - REAKTOR JĄDROWY w C++!";
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
            System.out.println("To j@ pracowałem w NA$A-AA");
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

    @Override
    public String getMonsterClass() {
        return "BoguJerma";
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

