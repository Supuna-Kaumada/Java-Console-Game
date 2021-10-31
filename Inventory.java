import jdk.jfr.Description;

public class Inventory {
    private int smallPotions;
    private int mediumPotions;
    private int largePotions;

    public Inventory() {
        this.smallPotions = 2;
        this.mediumPotions = 1;
        this.largePotions = 0;
    }

    public Inventory(int smallPotions, int mediumPotions, int largePotions) {
        this.smallPotions = smallPotions;
        this.mediumPotions = mediumPotions;
        this.largePotions = largePotions;
    }

    public void addPotions(Inventory otherInventory){
        this.smallPotions += otherInventory.getSmallPotions();
        this.mediumPotions += otherInventory.getMediumPotions();
        this.largePotions += otherInventory.getLargePotions();
    }

    public int getSmallPotions() {
        return this.smallPotions;
    }

    public int getMediumPotions() {
        return this.mediumPotions;
    }

    public int getLargePotions() {
        return this.largePotions;
    }

    public boolean usePotion(int choice) {
        switch (choice) {
        case 1:
            if (this.smallPotions == 0) {
                return false;
            } else {
                smallPotions = smallPotions - 1;
                return true;
            }
        case 2:
            if (this.mediumPotions == 0) {
                return false;
            } else {
                mediumPotions = mediumPotions - 1;
                return true;
            }
        case 3:
            if (this.largePotions == 0) {
                return false;
            } else {
                largePotions = largePotions - 1;
                return true;
            }
        default:
            return false;
        }
    }

    public String toString() {
        String description = "Small Potions : " + smallPotions + "\n";
        description = description + "Medium Potions : " + mediumPotions + "\n";
        description = description + "Large Potions : " + largePotions + "\n";
        return description;
    }

}
