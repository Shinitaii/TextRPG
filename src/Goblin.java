public class Goblin extends Enemy {
    public Goblin(String name) {
        super(name, "Goblin", 0,0,0,0);
        setHP((int)Math.floor(Math.random()*(100-90+1)+90)); 
        this.maxHp = getHP(); 
        setMP((int)Math.floor(Math.random()*(100-90+1)+90));
        this.maxMp = getMP(); 
        setATK((int)Math.floor(Math.random()*(15-5+1)+5));
        setDEF((int)Math.floor(Math.random()*(5+1)));
    }
}
