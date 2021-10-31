import java.util.*;
import java.io.*;

public class Adventure2 {
	// Do not edit attributes
	private Hero hero;
	private Scanner keyboard;

	public static void main(String[] args) throws IOException {
		Adventure2 adventure = new Adventure2();
		System.out.println("***Gayanga Madusha Hewathudallage 20342398***");
		System.out.println("***   Adventure Part 2   ***");
		adventure.run();
	}

	public Adventure2() {
		keyboard = new Scanner(System.in);
		hero = null;
	}

	public void run() throws IOException {

		int choice = -1;
		while (choice != 0) {
			displayMenu();
			System.out.print("Enter choice >> ");
			choice = keyboard.nextInt();
			System.out.println();
			keyboard.nextLine();
			process(choice);
		}

	}

	private void displayMenu() {
		System.out.println();
		System.out.println("~~~ ADVENTURE 2 MENU ~~~");
		System.out.println("1. Create Hero");
		System.out.println("2. Display Hero");
		System.out.println("3. Heal Hero");
		System.out.println("4. Single Battle");
		System.out.println("5. Battle from List");
		System.out.println("6. Save Hero to File");
		System.out.println("7. Load Hero from File");
		System.out.println("0. Quit");
	}

	private void process(int choice) {
		if (choice == 1) {
			createHero();
		} else if (choice == 2) {
			displayHero();
		} else if (choice == 3) {
			healHero();
		} else if (choice == 4) {
			singleBattle();
		} else if (choice == 5) {
			battleFromList();
		} else if (choice == 6) {
			saveHero();
		} else if (choice == 7) {
			loadHero();
		}
	}

	private void healHero() {
		if (this.hero != null) {
			if (this.hero instanceof Mage) {
				System.out.println("Do you want to use Magic Heal?");
				try {
					char inp = readYNChar();
					if (inp == 'Y') {
						((Mage) hero).magicHeal();
						System.out.println("Magic Heal Used");
					} else if (inp == 'N') {
						normalHealHero();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				normalHealHero();
			}
		} else {
			System.out.println("No hero exists");
		}
	}

	private void normalHealHero() {
		System.out.println("What size of potion would you like to use?");
		System.out.println("1. Small");
		System.out.println("2. Medium");
		System.out.println("3. Large");
		try {
			int inp = readInt(1, 3);
			if (hero.usePotion(inp)) {
				System.out.println("Portion Consumed");
			} else {
				System.out.println("This portion is not available");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void giveHeroExperience() {
		if (this.hero != null) {
			System.out.println("How much experience do you want to give the hero <0-100000>?");
			try {
				int inp = readInt(0, 100000);
				if (hero.gainExperience(inp)) {
					System.out.println("Hero has leveled up!");
				} else {
					System.out.println("Hero has recieved some experience");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No hero exists");
		}
	}

	private void damageHero() {
		if (this.hero != null) {
			System.out.println("How much do you want to damage the hero <0-100000>?");
			try {
				int inp = readInt(0, 100000);
				if (hero.takeDamage(inp)) {
					System.out.println("Hero has taken damage");
				} else {
					System.out.println("Hero has fainted");
					System.out.println("You will need a new Hero!");
					this.hero = null;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No hero exists");
		}
	}

	private void saveHero() {
		if (this.hero != null) {

			File dataDir = new File("data");

			if (!dataDir.exists()) {
				System.out.println("Directory does not exist. Will be created");
				dataDir.mkdir();
			}

			File heroFile = new File("data/hero.bin");
			try {
				PrintWriter outfile = new PrintWriter(heroFile);
				outfile.println(hero.getClass().getName());
				outfile.println(hero.getHealth());
				outfile.println(hero.getLevel());
				outfile.println(hero.getExperience());
				outfile.println(hero.getMaxHealth());
				outfile.println(hero.getNextLevelExperience());
				outfile.println(hero.getInventory().getSmallPotions());
				outfile.println(hero.getInventory().getMediumPotions());
				outfile.println(hero.getInventory().getLargePotions());
				if (hero instanceof Archer) {
					outfile.println(((Archer) hero).getBonusExp());
				}
				if (hero instanceof Mage) {
					outfile.println(((Mage) hero).getHealRate());
				}
				outfile.close();
				System.out.println("Hero saved");
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No Hero exists");
		}
	}

	private void loadHero() {
		if (this.hero != null) {
			System.out.println("Do you want to overwrite the current Hero? Y\\N ");
			try {
				char inp = readYNChar();
				if (inp == 'Y') {
					loadNewHero();
				} else if (inp == 'N') {
					System.out.println("Current Hero remains.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			loadNewHero();
		}
	}

	private void loadNewHero() {
		File dataDir = new File("data");

		if (!dataDir.exists()) {
			System.out.println("Data directory does not exist");
		} else {
			File heroFile = new File("data/hero.bin");
			if (!heroFile.exists()) {
				System.out.println("Hero save file does not exist");
			} else {
				try {
					Scanner in = new Scanner(heroFile);
					String type = in.nextLine();
					int health = in.nextInt();
					int level = in.nextInt();
					int experience = in.nextInt();
					int maxHealth = in.nextInt();
					int nextLevelExperience = in.nextInt();

					int smallPotions = in.nextInt();
					int mediumPotions = in.nextInt();
					int largePotions = in.nextInt();

					Inventory inventory = new Inventory(smallPotions, mediumPotions, largePotions);

					if (type.equals("Archer")) {
						double bonusExp = in.nextDouble();
						this.hero = new Archer(health, level, experience, maxHealth, nextLevelExperience, inventory,
								bonusExp);
						System.out.println("Archer loaded");
					} else if (type.equals("Warrior")) {
						this.hero = new Warrior(health, level, experience, maxHealth, nextLevelExperience, inventory);
						System.out.println("Warrior loaded");
					} else if (type.equals("Mage")) {
						int healRate = in.nextInt();
						this.hero = new Mage(health, level, experience, maxHealth, nextLevelExperience, inventory,
								healRate);
						System.out.println("Mage loaded");
					}

					in.close();
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
		}

	}

	private void displayHero() {
		if (this.hero != null) {
			this.hero.display();
		} else {
			System.out.println("No hero exists");
		}
	}

	private void createHero() {
		if (this.hero != null) {
			System.out.println("Do you want to remove the current Hero? Y\\N ");
			try {
				char inp = readYNChar();
				if (inp == 'Y') {
					createTypeHero();
				} else if (inp == 'N') {
					System.out.println("Current Hero remains.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			createTypeHero();
		}
	}

	private void createTypeHero() {
		System.out.println("What type of Hero would you like to create?");
		System.out.println("1. Archer");
		System.out.println("2. Warrior");
		System.out.println("3. Mage");
		try {
			int inp = readInt(1, 3);
			if (inp == 1) {
				this.hero = new Archer();
			} else if (inp == 2) {
				this.hero = new Warrior();
			} else if (inp == 3) {
				this.hero = new Mage();
			}
			System.out.println("Hero Created");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void singleBattle() {
		if (this.hero != null) {
			System.out.print("Enter the monster's name >> ");
			Scanner in = new Scanner(System.in);
			String name = in.nextLine();
			System.out.print("Enter the monster's level <1-20> >> ");
			try {
				int level = readInt(1, 20);
				Monster monster = new Monster(name, level);

				System.out.println("~~~~~~BATTLE~~~~~~");
				battleRun(monster);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No hero exists");
		}
	}

	private void battleFromList() {
		if (this.hero != null) {
			char input = 'N';
			MonsterList monsterList = new MonsterList();
			do {
				System.out.print("Enter the monster's name >> ");
				Scanner in = new Scanner(System.in);
				String name = in.nextLine();
				System.out.print("Enter the monster's level <1-20> >> ");
				int level;
				try {
					level = readInt(1, 20);
					Monster monster = new Monster(name, level);
					monsterList.insertAtFront(monster);
					System.out.print("Enter another monster? ");
					input = readYNChar();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
			} while (input == 'Y');

			int i = 1;
			
			do {
				Monster monster = monsterList.removeEnd();

				System.out.println("\n~~~~~~BATTLE~"+i+"~~~~~");
				battleRun(monster);
				i++;
			} while ((monsterList.getHead()!= null) && (this.hero != null));

			
		} else {
			System.out.println("No hero exists");
		}
	}

	private void battleRun(Monster monster) {
		if (this.hero.getLevel() < monster.getLevel()) {
			if (battle(hero, monster)) {
				// gain exp
				if (this.hero.gainExperience(monster.rewardExperience())) {
					System.out.println("Hero recieved " + monster.rewardExperience() + " EXP");
					System.out.println("Hero has leveled up!");
				} else {
					System.out.println("Hero recieved " + monster.rewardExperience() + " EXP");
				}

				// gain portions
				this.hero.addPotions(monster.rewardPotions());
				System.out.println("Hero recieved " + monster.rewardPotions().getSmallPotions() + " x Small, "
						+ monster.rewardPotions().getMediumPotions() + " x Medium and "
						+ monster.rewardPotions().getLargePotions() + " x Large Potions");
			} else {
				this.hero = null;
				System.out.println("You will need a new Hero!");
			}
		} else {
			if (battle(monster, hero)) {
				this.hero = null;
				System.out.println("You will need a new Hero!");
			} else {
				// gain exp
				if (this.hero.gainExperience(monster.rewardExperience())) {
					System.out.println("Hero recieved " + monster.rewardExperience() + "EXP");
					System.out.println("Hero has leveled up!");
				} else {
					System.out.println("Hero recieved " + monster.rewardExperience() + "EXP");
				}

				// gain portions
				this.hero.addPotions(monster.rewardPotions());
				System.out.println("Hero recieved " + monster.rewardPotions().getSmallPotions() + " x Small, "
						+ monster.rewardPotions().getMediumPotions() + " x Medium and "
						+ monster.rewardPotions().getLargePotions() + " x Large Potions");
			}

		}
	}

	private boolean battle(BattleReady fighter1, BattleReady fighter2) {
		int amount1 = fighter1.attack();
		System.out.println(fighter1.getName() + " does " + amount1 + " damage!");
		if (fighter2.takeDamage(amount1)) {
			System.out.println(fighter2.getName() + " has " + fighter2.getHealth() + " health remaining.");
			System.out.println("-----------------------------------------------------------------------");
		} else {
			System.out.println(fighter2.getName() + " fainted!");
			return true;
		}

		int amount2 = fighter2.attack();
		System.out.println(fighter2.getName() + " does " + amount2 + " damage!");
		if (fighter1.takeDamage(amount2)) {
			System.out.println(fighter1.getName() + " has " + fighter1.getHealth() + " health remaining.");
			System.out.println("-----------------------------------------------------------------------");
		} else {
			System.out.println(fighter1.getName() + " fainted!");
			return false;
		}

		return battle(fighter1, fighter2);
	}

	public int readInt(int min, int max) throws Exception {
		int i = 0;
		while (i < 5) {
			try {
				System.out.print("Enter choice >> ");
				Scanner in = new Scanner(System.in);
				int n = in.nextInt();
				ValueChecker.checkValue(n, min, max);
				return n;
			} catch (InputMismatchException e) {
				System.out.println("Input is not an integer");
			} catch (IntRangeException e) {
				System.out.println(e.getMessage());
			}
			i++;
		}
		throw new Exception("Valid value not entered after 5 attempts, exiting function");
	}

	public char readYNChar() throws Exception {
		int i = 0;
		while (i < 5) {
			try {
				System.out.print("Enter choice >> ");
				Scanner in = new Scanner(System.in);
				char c = in.next().charAt(0);
				ValueChecker.checkValue(c);
				return c;
			} catch (InputMismatchException e) {
				System.out.println("Input is not a character");
			} catch (YNException e) {
				System.out.println(e.getMessage());
			}
			i++;
		}
		throw new Exception("Valid value not entered after 5 attempts, exiting function");
	}

}