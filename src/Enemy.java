public class Enemy extends Creature {

    public String name, roleName;
    public int hp, mp, atk, def;
    
    public Enemy(String name, String roleName, int hp, int mp, int atk, int def){
        super(name, roleName, hp, mp, atk, def);
    }
    
}
