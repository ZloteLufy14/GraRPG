import java.util.Random;

public class Rogue extends Monster{
    Random rand = new Random();

    public Rogue(){
        super("Drapichrust",2,60);
    }

    @Override
    public void monsterInfo(){
        System.out.println("Name: " + getName() + "\nStrength: " + getStrength() + "\nHP: " + getHealth());
    }

    @Override
    public String getMonsterClass() {
        return "Rogue";
    } 

}
