public class Archer extends Hero {
    private double bonusExp;

    pubalic Archer() {
        super();
        this.bonusExp = 0.5;
    }

    public Archer(int health, int level, int experience, int maxHealth, int nextLevelExperience, Inventory inventory, double bonusExp) {
        super(health, level, experience, maxHealth, nextLevelExperience, inventory);
        this.bonusExp = bonusExp;
    }

    public boolean gainExperience(int amount) {
        amount = amount + (int) (amount * bonusExp);
        return super.gainExperience(amount);
    }

    public double getBonusExp() {
        return this.bonusExp;
    }

    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String getName() {
        return "Archer";
    }

    @Override
    public void upgradeStats() {
        bonusExp += 0.1;
        int newMaxHealth = super.getMaxHealth() + (super.getLevel() * 10);
        super.setMaxHealth(newMaxHealth);
        super.setHealth(newMaxHealth);
    }

    @Override
    public String toString() {
        String description;

        description = "Type:\t\tArcher\n";
        description = description + "Health:\t\t\t" + super.getHealth() + "\n";
        description = description + "Level:\t\t\t" + super.getLevel() + "\n";
        description = description + "Experience:\t\t" + super.getExperience() + "\n";
        description = description + "Max Health:\t\t" + super.getMaxHealth() + "\n";
        description = description + "Potions\t- Small:\t" + super.getInventory().getSmallPotions() + "\n";
        description = description + "\t- Medium:\t" + super.getInventory().getMediumPotions() + "\n";
        description = description + "\t- Large:\t" + super.getInventory().getLargePotions() + "\n";
        description = description + "BonusExp:\t\t" + getBonusExp() + "\n";

        return description;
    }

}
