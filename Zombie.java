public class Zombie extends Monster {

    public Zombie() {
        super("Zombie", 5, 70);
    }

    @Override
    public void monsterInfo() {
        System.out.println("Name: " + getName() + "\nStrength: " + getStrength() + "\nHP: " + getHealth());
    }

    @Override
    public String getMonsterClass() {
        return "Zombie";
    } 
}