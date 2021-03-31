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
        this.setSpeed(0);
        this.setResistance(0);
        this.setDexterity(0);
        this.setImpulsion(0);
        this.setHeadGame(0);
        this.setKick(0);
        this.setPassCapacity(0);
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

        int sum = 0;

        for (Map.Entry<Double,Integer> entry : skillMap.entrySet())
            sum += entry.getKey()*entry.getValue();

        return sum;
    }

    public abstract int playerOverallValue();
}
