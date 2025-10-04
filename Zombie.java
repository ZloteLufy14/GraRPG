public class Zombie extends Monster {

    public Zombie(String name, int strength, int health) {
        super(name, strength, health); 
    }

    @Override
    public void monsterInfo() {
        System.out.println("Name: " + getName() + "\nStrength: " + getStrength() + "\nHP: " + getHealth());
    }
}
