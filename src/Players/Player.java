package Players;

import java.util.HashMap;
import java.util.Map;

public abstract class Player {
    private int speed;
    private int resistance;
    private int dexterity;
    private int impulsion;
    private int headGame;
    private int kick;
    private int passCapacity;

    public Player(){
        this.setSpeed(-1);
        this.setResistance(-1);
        this.setDexterity(-1);
        this.setImpulsion(-1);
        this.setHeadGame(-1);
        this.setKick(-1);
        this.setPassCapacity(-1);
    }

    public Player(Player player){
        this.speed = player.getSpeed();
        this.resistance = player.getResistance();
        this.dexterity = player.getDexterity();
        this.impulsion = player.getImpulsion();
        this.headGame = player.getHeadGame();
        this.kick = player.getKick();
        this.passCapacity = player.getPassCapacity();
    }

    public Player(int speed, int resistance, int dexterity, int impulsion,
                  int headGame, int kick, int passCapacity){

        setSpeed(speed);
        setResistance(resistance);
        setDexterity(dexterity);
        setImpulsion(impulsion);
        setHeadGame(headGame);
        setKick(kick);
        setPassCapacity(passCapacity);
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getResistance() {
        return this.resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getImpulsion() {
        return this.impulsion;
    }

    public void setImpulsion(int impulsion) {
        this.impulsion = impulsion;
    }

    public int getHeadGame() {
        return this.headGame;
    }

    public void setHeadGame(int headGame) {
        this.headGame = headGame;
    }

    public int getKick() {
        return this.kick;
    }

    public void setKick(int kick) {
        this.kick = kick;
    }

    public int getPassCapacity() {
        return this.passCapacity;
    }

    public void setPassCapacity(int passCapacity) {
        this.passCapacity = passCapacity;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return "Player characteristics:\n" +
                "\t[Speed] " + this.speed + "\n" +
                "\t[Resistance] " + this.resistance + "\n" +
                "\t[Dexterity] " + this.dexterity + "\n" +
                "\t[Impulsion] " + this.impulsion + "\n" +
                "\t[Head Game] " + this.headGame + "\n" +
                "\t[Kick] " + this.kick + "\n" +
                "\t[Pass Capacity] " + this.passCapacity + "\n";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getSpeed() == player.getSpeed()
                          && getResistance() == player.getResistance()
                          && getDexterity() == player.getDexterity()
                          && getImpulsion() == player.getImpulsion()
                          && getHeadGame() == player.getHeadGame()
                          && getKick() == player.getKick()
                          && getPassCapacity() == player.getPassCapacity();
    }

    public double skillMapToInt(Map<Double,Integer> skillMap){
        if (skillMap == null) return -1;

        double skillSize = skillMap.size();
        int sum = 0;
        double PondAverage;

        for (Map.Entry<Double,Integer> entry : skillMap.entrySet())
            sum += entry.getKey()*entry.getValue();

        PondAverage = sum/(skillSize);

        return PondAverage;
    }

    public Map<Double,Integer> getSkillMap(int speed, int resistance, int dexterity, int impulsion,
                                           int headGame, int kick, int passCapacity){

        Map<Double,Integer> skillMap = new HashMap<>();
        skillMap.put(0.9, speed);
        skillMap.put(0.6, resistance);
        skillMap.put(0.4, dexterity);
        skillMap.put(0.5, impulsion);
        skillMap.put(0.3, headGame);
        skillMap.put(0.8, kick);
        skillMap.put(0.7, passCapacity);

        return skillMap;
    }

    public abstract int playerOverallValue();
}
