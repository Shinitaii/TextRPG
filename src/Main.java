import java.util.Scanner;

public class Main {

    Scanner scan = new Scanner(System.in);

    public Player player;
    public Enemy enemy;

    int role = 0;
    int hp, mp, atk, def;

    private void run(){
        playerSetup();
        gameStart();
    }
    public static void main(String[] args){
        Main main = new Main();
        main.run();
    }

    private void playerSetup(){
        String name = getName();
        do{
            String roleName = getRole();
            switch(roleName){
                case "W": Warrior warrior = new Warrior(name); player = warrior; role = 1; break;
                case "A": Archer archer = new Archer(name); player = archer; role = 2; break;
                case "M": Mage mage = new Mage(name); player = mage; role = 3; break; 
                default: notValidOption(); break;
            }     
        }while(role < 1 || role > 3);
    }

    private String getName(){
        System.out.print("What is your name: ");
        return scan.nextLine();
    }

    private String getRole() {
        System.out.print("Choose a class: [W] Warrior | [A] Archer | [M] Mage: ");
        return scan.next().toUpperCase();
    }

    private void gameStart(){
        String userChoice = "";
        do{
            System.out.println("Welcome, " + player.getName());
            System.out.print("What do you want to do? [B] Battle | [S] Shop | [X] Exit: ");
            userChoice = scan.next().toUpperCase();
            switch(userChoice){
                case "B": createEnemy(); System.out.println("You encountered " + enemy.getName() + ", a " + enemy.getRoleName() + "!\n"); battle(); break;
                case "S": shop(); break;
                case "X": System.out.println("\nSee you!"); System.exit(0); break;
                default: notValidOption(); break;
            }
        } while (userChoice != "X");
    }

    private void createEnemy(){
        int num = (int)Math.floor(Math.random()*(3));
        switch(num){
            case 0: Goblin goblin = new Goblin(randomName()); enemy = goblin; break;
            case 1: Bandit bandit = new Bandit(randomName()); enemy = bandit; break;
            case 2: Slime slime = new Slime(randomName()); enemy = slime; break;
        }
        if(enemy.getLevel() > 1) enemy.levelScale(player);
    }

    private String randomName(){
        String[] names = {"Bob", "Max", "John", "Ooga", "Booga", "Swift"};
        int num = (int)Math.floor(Math.random()*(names.length));
        return names[num];
    }

    private void battle(){
        String userChoice = "";
        while(userChoice != "R"){
            userChoice = battleStatus();
            switch(userChoice){
                case "A": 
                    playerAttack(); enemyAttack();
                    if(player.isDead() && enemy.isDead()){
                        System.out.println("Draw!\n");
                        gameStart();
                    } else if(enemy.isDead()){
                        System.out.println("You win!\n");
                        player.setGold(player.gainGold(player, enemy) + player.getGold());
                        player.setEXP(player.gainEXP(player, enemy) + player.getEXP());
                        player.checkEXP(role, player);
                        gameStart();
                    } else if(player.isDead()){
                        System.out.println("You lost!\n");
                        gameStart();
                    }
                    break;
                case "H": player.setHP(player.useHealthPotion()); break;
                case "R": System.out.println("You ran away!"); gameStart(); break;
                default: notValidOption(); break;
            }
        }
    }

    private void playerAttack(){
        enemy.setHP(enemy.getHP() - player.getATK());
        System.out.println("\nYou attack " + enemy.getName() + " for " + player.getATK() + " damage!");
    }

    private void enemyAttack(){
        player.setHP(player.getHP() - enemy.getATK());
        System.out.println(enemy.getName() + " attacked you for " + enemy.getATK() + " damage!\n");
    }

    private String battleStatus(){
        System.out.printf("%s | HP: %,d / %,d | MP: %,d / %,d | Level %,d\n", player.getName(), player.getHP(), player.getMaxHP(), player.getMP(), player.getMaxMP(), player.getLevel());
        System.out.printf("%s | HP: %,d / %,d | MP: %,d / %,d | Level %,d\n", enemy.getName(), enemy.getHP(), enemy.getMaxHP(), enemy.getMP(), enemy.getMaxMP(), enemy.getLevel());
        System.out.print("What do you want to do? [A] Attack | [H] Heal | [R] Run: ");
        return scan.next().toUpperCase();
    }

    private void shop(){
        String userChoice = "";
        do{
            System.out.printf("\nWelcome to the shop! You have %,d gold.\nWhat brings you here? [B] Buy | [S] Sell | [X] Leave Shop: ", player.getGold());
            userChoice = scan.next().toUpperCase();
            switch(userChoice){
                case "B": buyItems(); break;
                case "C": sellItems(); break;
                case "X": gameStart(); break;
                default: notValidOption(); break;
            }
        }while(userChoice != "X");
    }

    private void buyItems(){
        String userChoice = "";
        do{
            System.out.print("What do you want to buy? [E] Equipments | [W] Weapons | [P] Potions | [X] Back: ");
            userChoice = scan.next().toUpperCase();
            switch(userChoice){
                case "E": updates(); break;
                case "W": updates(); break;
                case "P": buyPotions(); break;
                case "X": shop(); break;
                default: notValidOption(); break;
            }
        }while(userChoice != "X");
    }

    private void sellItems(){

    }

    private void buyPotions(){
        String userChoice;
        do{
            System.out.print("Select potions: [H] Health Potion (10 gold) | [M] Mana Potion | [X] Back: ");
            userChoice = scan.next().toUpperCase();
            switch(userChoice){
                case "H": 
                    int quantity = quantityItem("Health Potion");
                    if(player.getGold() < (quantity * 10)) System.out.println("You do not have enough gold!");
                    else {
                        System.out.printf("You successfully bought %,d %s for %,d gold!\n", quantity, "Health Potion", (quantity * 10));
                        player.setGold(player.getGold() - (quantity * 10));
                        player.setHealthPotions(player.getHealthPotions() + quantity);
                    }
                    break;
                case "M": updates(); break;
                case "X": buyItems(); break;
                default: notValidOption(); break;
            }
        }while(userChoice != "X");
    }

    private int quantityItem(String itemName){
        System.out.printf("How many %s would you like to buy?", itemName);
        return scan.nextInt();
    }

    private void notValidOption(){
        System.out.println("Select a valid option.");
    }

    // placeholder for no functions yet
    private void updates(){
        System.out.println("Coming soon in the later updates!");
        gameStart();
    }
    
}