public class Player extends Creature {

    public String name;
    public String roleName;
    public int hp, mp, atk, def, level, gold;
    public int healthPotions;
    public double currentEXP, totalEXP;

    public Player(String name, String roleName, int hp, int mp, int atk, int def){
        super(name, roleName, hp, mp, atk, def);
        this.level = 1;
        this.healthPotions = 3;
        this.gold = 0;
        this.currentEXP = 0;
        this.totalEXP = 0;
    }

    public int getHealthPotions() { return healthPotions; }
    public void setHealthPotions(int healthPotions) {this.healthPotions = healthPotions; }
    public int getGold() { return gold; }
    public void setGold(int gold) { this.gold = gold; }
    public double getEXP() { return currentEXP; }
    public void setEXP(double currentEXP) { this.currentEXP = currentEXP; } 
    public double getTotalEXP() { return totalEXP; }
    public void setTotalEXP(double totalEXP) {this.totalEXP = totalEXP; }

    public int useHealthPotion(){
        healthPotions--;
        if(healthPotions > 0){
            System.out.println(healthPotions + " health potions left.");
            if(getHP() <= 50) return getHP() + 50;
            else return (getHP() - getHP()) + getMaxHP();
        } else {
            System.out.println("You do not have health potions anymore.");
            return getHP();
        }
    }

    public double gainEXP(Creature player, Creature enemy){
        int baseXP = (enemy.getLevel() + player.getLevel()) * (int)Math.floor(Math.random()*(enemy.getLevel() + player.getLevel())+1);
        double formulatedXP = baseXP + (baseXP * 0.2);
        System.out.printf("You gained %,.2f EXP!\n", formulatedXP);
        return formulatedXP;
    }

    public boolean checkEXP(int role, Player player){
        totalEXP = getLevel() * 5;
        System.out.printf("%,.2f / %,.0f\n", currentEXP, totalEXP);
        if(currentEXP >= totalEXP){
            player.setEXP(0);
            System.out.printf("You leveled up to %,d!\n", getLevel()+1);
            switch(role){
                case 1: increaseLevel(1, 2, 5, 0);
                case 2: increaseLevel(2, 1, 5, 0);
                case 3: increaseLevel(3, 0, 2, 5);
            }
            return true;
        } else return false;
    }

    public int gainGold(Creature player, Creature enemy){
        int gold = (enemy.getLevel() + player.getLevel() * (int)Math.floor(Math.random()*((enemy.getLevel() + player.getLevel()) * 2)-1+1)+1);
        System.out.printf("You gained %,d gold!\n", gold);
        return gold;
    }

}
