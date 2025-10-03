public class Monster {
    private String name;
    private int health;

    public Monster(String name, int health) {
        this.name = name; 
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name; 
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health > 0) { 
            this.health = health; 
        }
    }

    public void characterInfo() {
        System.out.println("\nName: " + this.name + "\nHP: " + this.health);
    }
}
