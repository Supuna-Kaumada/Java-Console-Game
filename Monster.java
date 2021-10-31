public class Monster implements BattleReady{

    private String name;
    private int health;
    private int level;


    public Monster(String name, int level) {
        this.name = name;
        this.health = 100*level;
        this.level = level;
    }
    
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    @Override
    public boolean takeDamage(int amount) {
        health -= amount;

        if (health < 1) {
            return false;
        }
        return true;
    }

    @Override
    public int attack() {
        return (int)((Math.random() * 100 * this.level)/3); 
    }

    public int rewardExperience(){
        return this.level*25;
    }

    public Inventory rewardPotions(){
        int smallPotions = (int)(this.level-Math.random()*this.level/6);
        int mediumPotions = (int)(this.level-Math.random()*this.level/2);
        int largePotions =  (int)((this.level-this.level/2)-Math.random()*(this.level-this.level/2));
        Inventory inventory = new Inventory(smallPotions, mediumPotions, largePotions);
        return inventory;
    }
}
