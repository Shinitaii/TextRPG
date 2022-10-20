public class Player extends Creature {

    public String name;
    public String roleName;
    public int hp, mp, atk, def, level;
    public int healthPotions = 3;
    public double currentEXP, totalEXP;

    public Player(String name, String roleName, int hp, int mp, int atk, int def){
        super(name, roleName, hp, mp, atk, def);
        this.level = 1;
        this.currentEXP = 0;
        this.totalEXP = level * 40;
    }

    public double getEXP() { return currentEXP; }
    public void setEXP(double currentEXP) { this.currentEXP = currentEXP; } 
    public double getTotalEXP() { return totalEXP; }
    public void setTotalEXP(double totalEXP) {this.totalEXP = totalEXP; }

    public int useHealthPotion(){
        healthPotions--;
        if(healthPotions > 0){
            System.out.println(healthPotions + " health potions left.");
            return getHP() + 50;
        } else {
            System.out.println("You do not have health potions anymore.");
            return getHP();
        }
    }

    public double gainEXP(Creature player, Creature enemy){
        int baseXP = (enemy.getLevel() + player.getLevel()) * (int)Math.floor(Math.random()*(enemy.getLevel() + player.getLevel())+1);
        double formulatedXP = baseXP + (baseXP + 0.5);
        System.out.printf("You gained %,.0f EXP!\n", formulatedXP);
        return formulatedXP;
    }

    public boolean checkEXP(int role){
        totalEXP = level * 40;
        System.out.printf("%,.0f / %,.0f\n", currentEXP, totalEXP);
        if(currentEXP >= totalEXP){
            currentEXP -= totalEXP;
            switch(role){
                case 1: increaseLevel(1, 2, 5, 0);
                case 2: increaseLevel(2, 1, 5, 0);
                case 3: increaseLevel(3, 0, 2, 5);
            }
            return true;
        } else return false;
    }

}
