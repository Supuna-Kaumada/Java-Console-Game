public class Warrior extends Hero {

    public Warrior() {
        super();
    }


    public Warrior(int health, int level, int experience, int maxHealth, int nextLevelExperience, Inventory inventory) {
        super(health, level, experience, maxHealth, nextLevelExperience, inventory);  
    }

    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String getName() {
        return "Warrior";
    }

    @Override
    public void upgradeStats() {
        int newMaxHealth = super.getMaxHealth() + (super.getLevel() * 12);
        super.setMaxHealth(newMaxHealth);
        super.setHealth(newMaxHealth);
    }

    @Override
    public String toString() {
        String description;

        description = "Type:\t\tWarrior\n";
        description = description + "Health:\t\t\t" + super.getHealth() + "\n";
        description = description + "Level:\t\t\t" + super.getLevel() + "\n";
        description = description + "Experience:\t\t" + super.getExperience() + "\n";
        description = description + "Max Health:\t\t" + super.getMaxHealth() + "\n";
        description = description + "Potions\t- Small:\t" + super.getInventory().getSmallPotions() + "\n";
        description = description + "\t- Medium:\t" + super.getInventory().getMediumPotions() + "\n";
        description = description + "\t- Large:\t" + super.getInventory().getLargePotions() + "\n";

        return description;
    }
}
