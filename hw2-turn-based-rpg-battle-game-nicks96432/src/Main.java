import java.util.HashMap;

/**
 * The RPG game will be started from the Main class.
 */
public class Main {
    public final static int countTroop = 2;

    public static void main(String[] args) {
        HashMap<String, Skill> allSkillMap = new HashMap<>();
        allSkillMap.put(BasicAttack.name, new BasicAttack());
        allSkillMap.put(Waterball.name, new Waterball());
        allSkillMap.put(Fireball.name, new Fireball());
        allSkillMap.put(SelfHealing.name, new SelfHealing());
        allSkillMap.put(Petrochemical.name, new Petrochemical());
        allSkillMap.put(Poison.name, new Poison());
        allSkillMap.put(Summon.name, new Summon());
        allSkillMap.put(SelfExplosion.name, new SelfExplosion());
        allSkillMap.put(Cheerup.name, new Cheerup());
        allSkillMap.put(Curse.name, new Curse());
        allSkillMap.put(OnePunch.name, new OnePunch());
        RPG rpg = new RPG(allSkillMap, countTroop);
        rpg.load();
        rpg.start();
    }
}
