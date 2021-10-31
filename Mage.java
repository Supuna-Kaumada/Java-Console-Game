public class Mage extends Hero {

    private int healRate;

    public Mage() {
        super();
        this.healRate = 10;
    }

    public Mage(int health, int level, int experience, int maxHealth, int nextLevelExperience, Inventory inventory, int healRate) {
        super(health, level, experience, maxHealth, nextLevelExperience, inventory);
        this.healRate = healRate;
    }

    public void magicHeal() {
        int newHealth = super.getHealth() + (super.getLevel() * healRate);
        if (newHealth > getMaxHealth()) {
            super.setHealth(getMaxHealth());
        } else {
            super.setHealth(newHealth);
        }
    }


    public int getHealRate() {
        return this.healRate;
    }

    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String getName() {
        return "Mage";
    }

    @Override
    public void upgradeStats() {
        this.healRate += 10;
        int newMaxHealth = super.getMaxHealth() + (super.getLevel() * 9);
        super.setMaxHealth(newMaxHealth);
        super.setHealth(newMaxHealth);
    }

    @Override
    public String toString() {
        String description;

        description = "Type:\t\tMage\n";
        description = description + "Health:\t\t\t" + super.getHealth() + "\n";
        description = description + "Level:\t\t\t" + super.getLevel() + "\n";
        description = description + "Experience:\t\t" + super.getExperience() + "\n";
        description = description + "Max Health:\t\t" + super.getMaxHealth() + "\n";
        description = description + "Potions\t- Small:\t" + super.getInventory().getSmallPotions() + "\n";
        description = description + "\t- Medium:\t" + super.getInventory().getMediumPotions() + "\n";
        description = description + "\t- Large:\t" + super.getInventory().getLargePotions() + "\n";
        description = description + "Heal Rate:\t\t" + getHealRate() + "\n";

        return description;
    }
}
