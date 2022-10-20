public class Bandit extends Enemy{
    public Bandit(String name){
        super(name, "Bandit", 0, 0, 0, 0);
        setHP((int)Math.floor(Math.random()*(100-90+1)+90));  
        this.maxHp = getHP(); 
        setMP((int)Math.floor(Math.random()*(100-90+1)+90));
        this.maxMp = getMP();
        setATK((int)Math.floor(Math.random()*(20-5+1)+5));
        setDEF((int)Math.floor(Math.random()*(3+1)));
    }
}
