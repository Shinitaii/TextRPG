public class Creature {

    public String name;
    public String roleName;
    public int hp, maxHp, mp, maxMp, atk, def, level;
    
    public Creature(String name, String roleName, int hp, int mp, int atk, int def){
        this.name = name;
        this.roleName = roleName;
        this.hp = hp;
        this.maxHp = hp;
        this.mp = mp;
        this.maxMp = mp;
        this.atk = atk;
        this.def = def;
        this.level = 1;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public int getHP() { return hp; }
    public void setHP(int hp) { this.hp = hp; }
    public int getMaxHP() { return maxHp; }
    public void setMaxHP(int maxHp) { this.maxHp = maxHp; }
    public int getMP() { return mp; }
    public void setMP(int mp) { this.mp = mp; }
    public int getMaxMP() { return maxMp; }
    public void setMaxMP(int maxMp) { this.maxMp = maxMp; }
    public int getATK() { return atk; }
    public void setATK(int atk) { this.atk = atk; }
    public int getDEF() { return def; }
    public void setDEF(int def) { this.def = def; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public boolean isDead(){ 
        if(this.hp <= 0) return true;
        else return false;
    }

    private int increaseATK(int atk){
        return getATK() + atk;
    }

    private int increaseDEF(int def){
        return getDEF() + def;
    }

    private int increaseHP(int maxHp){
        return getMaxHP() + maxHp;
    }

    private int increaseMP(int maxMp){
        return getMaxMP() + maxMp;
    }

    public void increaseStats(int atk, int def, int maxHp, int maxMp){
        increaseATK(atk);
        increaseDEF(def);
        increaseHP(maxHp);
        increaseMP(maxMp);
    }

    public void increaseLevel(int atk, int def, int maxHp, int maxMp){
        level++;
        increaseStats(atk, def, maxHp, maxMp);
        setHP(getMaxHP());
        setMP(getMaxMP());
    }
}
