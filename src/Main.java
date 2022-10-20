import java.util.Scanner;

public class Main {

    Scanner scan = new Scanner(System.in);

    public Player player;
    public Enemy enemy;

    int role;
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
            role = getRole();
            switch(role){
                case 1: Warrior warrior = new Warrior(name); player = warrior; break;
                case 2: Archer archer = new Archer(name); player = archer; break;
                case 3: Mage mage = new Mage(name); player = mage; break; 
                default: System.out.println("Select a valid option."); break;
            }
            
        }while(role < 1 || role > 3);
    }

    private String getName(){
        System.out.print("What is your name: ");
        String name = scan.nextLine();
        return name;
    }

    private int getRole(){
        System.out.print("Choose a class: [1] Warrior | [2] Archer | [3] Mage: ");
        int role = scan.nextInt();
        return role;
    }

    private void gameStart(){
        String userChoice = "";
        do{
            System.out.println("Welcome, " + player.getName());
            System.out.print("What do you want to do? [B] Battle | [S] Shop | [X] Exit: ");
            userChoice = scan.next().toUpperCase();
            switch(userChoice){
                case "B": createEnemy(); System.out.println("You encountered " + enemy.getName() + ", a " + enemy.getRoleName() + "!\n"); battle(); break;
                case "S": shop();
                case "X": System.exit(0); break;
                default: System.out.println("Select a valid option."); break;
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
                        player.setEXP(player.gainEXP(player, enemy));
                        player.checkEXP(role);
                        gameStart();
                    } else if(player.isDead()){
                        System.out.println("You lost!\n");
                        gameStart();
                    }
                    break;
                case "H": player.setHP(player.useHealthPotion()); break;
                case "R": System.out.println("You ran away!"); gameStart(); break;
                default: System.out.println("Select a valid option."); break;
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
        System.out.println(player.getName() + " | HP: " + player.getHP() + "/" + player.maxHp + " | MP: " + player.getMP() + "/" + player.maxMp);
        System.out.println(enemy.getName() + " | HP: " + enemy.getHP() + "/" + enemy.maxHp + " | MP: " + enemy.getMP() + "/" + enemy.maxMp);
        System.out.print("What do you want to do? [A] Attack | [H] Heal | [R] Run: ");
        return scan.next().toUpperCase();
    }

    private void shop(){
        
    }
    
}