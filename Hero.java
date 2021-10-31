public abstract class Hero implements BattleReady{
    private int health;
    private int level;
    private int experience;
    private int maxHealth;
    private int nextLevelExperience;
    private Inventory inventory;

    public Hero() {
        this.health = 100;
        this.level = 1;
        this.experience = 0;
        this.maxHealth = 100;
        this.nextLevelExperience = 100;
        this.inventory = new Inventory();
    }

    public Hero(int health, int level, int experience, int maxHealth, int nextLevelExperience, Inventory inventory) {
        this.health = health;
        this.level = level;
        this.experience = experience;
        this.maxHealth = maxHealth;
        this.nextLevelExperience = nextLevelExperience;
        this.inventory = inventory;
    }

    public boolean usePotion(int size) {
        boolean out = inventory.usePotion(size);
        if (out == true) {
            if (size == 1) {
                health += 10;
            } else if (size == 2) {
                health += 50;
            } else if (size == 3) {
                health += 100;
            }

            if (health > maxHealth) {
                health = maxHealth;
            }
        }
        return out;
    }

    public boolean takeDamage(int amount) {
        health -= amount;

        if (health < 1) {
            return false;
        }
        return true;
    }

    public boolean gainExperience(int amount) {
        experience += amount;

        if (experience > this.nextLevelExperience) {
            experience = 0;
            incrementLevel();
            return true;
        }
        return false;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }   

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void incrementLevel()
    {
        level += 1;
        upgradeStats();
        this.nextLevelExperience = this.nextLevelExperience * 2;
    }

    public void addPotions(Inventory otherInventory){
        this.inventory.addPotions(otherInventory);
    }

    public int getHealth() {
        return this.health;
    }

    public int getLevel() {
        return this.level;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getExperience() {
        return this.experience;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
    
    public int getNextLevelExperience() {
        return nextLevelExperience;
    }

    public int attack()
    {
        return (int)(Math.random() * 100 * this.level); 
    }

    public abstract void display();

    public abstract void upgradeStats();

    public abstract String getName();
}
