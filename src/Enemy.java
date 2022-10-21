public class Enemy extends Creature {

    public String name, roleName;
    public int hp, mp, atk, def, level;
    
    public Enemy(String name, String roleName, int hp, int mp, int atk, int def){
        super(name, roleName, hp, mp, atk, def);
        this.level = 1;
    }

    public void levelScale(Player player){
        setLevel((int) Math.floor(Math.random()*(player.getLevel()-getLevel()+1)-getLevel()));
        increaseStats(getLevel() * 2, getLevel() * 1, getLevel() * 2, getLevel() * 3);
    }
    
}
