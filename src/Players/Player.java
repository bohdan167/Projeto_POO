package Players;

import java.util.*;

public abstract class Player{
    private String name;
    private int number;
    private int speed;
    private int resistance;
    private int dexterity;
    private int impulsion;
    private int headGame;
    private int kick;
    private int passCapacity;
    private int overall;
    private List<String> history;

    public Player(){
        this.setName("");
        this.setNumber(0);
        this.setSpeed(0);
        this.setResistance(0);
        this.setDexterity(0);
        this.setImpulsion(0);
        this.setHeadGame(0);
        this.setKick(0);
        this.setPassCapacity(0);
        this.setOverall(0);
        this.setHistory(new ArrayList<>());
    }

    public Player(String name, int number, int speed, int resistance, int dexterity, int impulsion,
                  int headGame, int kick, int passCapacity, int overall, List<String> history){
        setName(name);
        setNumber(number);
        setSpeed(speed);
        setResistance(resistance);
        setDexterity(dexterity);
        setImpulsion(impulsion);
        setHeadGame(headGame);
        setKick(kick);
        setPassCapacity(passCapacity);
        setOverall(overall);
        setHistory(history);
    }

    public Player(Player player){
        this.name = player.getName();
        this.number = player.getNumber();
        this.speed = player.getSpeed();
        this.resistance = player.getResistance();
        this.dexterity = player.getDexterity();
        this.impulsion = player.getImpulsion();
        this.headGame = player.getHeadGame();
        this.kick = player.getKick();
        this.passCapacity = player.getPassCapacity();
        this.overall = player.getOverall();
        this.setHistory(player.getHistory());
    }

    public abstract int playerOverallValue();
    public abstract Player generateNewPlayer();

    //Getters e Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getImpulsion() { return this.impulsion; }

    public void setImpulsion(int impulsion) { this.impulsion = impulsion; }

    public int getHeadGame() { return this.headGame; }

    public void setHeadGame(int headGame) { this.headGame = headGame; }

    public int getKick() { return this.kick; }

    public void setKick(int kick) { this.kick = kick; }

    public int getPassCapacity() { return this.passCapacity; }

    public void setPassCapacity(int passCapacity) { this.passCapacity = passCapacity; }

    public int getOverall() { return overall; }

    public void setOverall(int overall) { this.overall = overall; }

    public List<String> getHistory() {
        return this.history;
    }

    public void setHistory(List<String> history) {
        this.history = new ArrayList<>(history);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getNumber() == player.getNumber()
                && getSpeed() == player.getSpeed()
                && getResistance() == player.getResistance()
                && getDexterity() == player.getDexterity()
                && getImpulsion() == player.getImpulsion()
                && getHeadGame() == player.getHeadGame()
                && getKick() == player.getKick()
                && getPassCapacity() == player.getPassCapacity()
                && getOverall() == player.getOverall()
                && Objects.equals(getName(), player.getName())
                && Objects.equals(getHistory(), player.getHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNumber(), getSpeed(), getResistance(), getDexterity(), getImpulsion(), getHeadGame(), getKick(), getPassCapacity(), getOverall(),getHistory());
    }

    public String playerTOSTRING(){
        String aux2 = "\t\t\t\t";
        if (this instanceof Goalkeeper) aux2 = ((Goalkeeper) this).getElasticity() + aux2;
        return "\n"+ getClass().getSimpleName() + " ".repeat(Math.max(0, 20 - getClass().getSimpleName().length()))
                + getName() + " ".repeat(Math.max(0, 12 - getName().length())) + String.format("%02d",getNumber()) + "\t\t\t" + getSpeed()
                + "\t\t\t" + getResistance() + "\t\t\t\t" + getDexterity() + "\t\t\t\t" + getImpulsion()
                + "\t\t\t" + getHeadGame() + "\t\t\t" + getKick() + "\t\t" + getPassCapacity()
                + "\t\t\t\t" + aux2 + getOverall() + "\n" + "\t\t\t\t\t\tHistory:" + getHistory();
    }

    @Override
    public String toString() {
        return "Position\t\t\tName\t    Number\t\tSpeed\t\tResistance\t\tDexterity\t\tImpulsion\tHead Game\tKick\tPass Capacity\tElasticity\tOverall"
                + playerTOSTRING() + "\n";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    protected String[] namesOfPlayers = {"Germano", "Jacinto", "Domiciano", "Gustio", "Kleiton",
                                         "Muralhas", "Abilio", "Julio",      "Purp",   "Kleiton",
                                         "Kaufman",  "Gerimbaldo", "Pinga",  "Travassos", "Dimas",
                                         "Costinha", "Maniche",  "Figo",     "Deco",    "Pauleta"};
}
