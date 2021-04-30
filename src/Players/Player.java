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

    /**
     * Construtor Nulo
     */
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

    /**
     * Construtor Parametrizado
     * @param name Nome do Player
     * @param number Numero do Player
     * @param speed Capacidade ce corrida do Player
     * @param resistance Capacidade de resistência do Player
     * @param dexterity Capacidade de destreza do Player
     * @param impulsion Capacidade de implusão do Player
     * @param headGame Capacidade de jogo de cabeça do Player
     * @param kick Capacidade de remate do Player
     * @param passCapacity Capacidade de pass do Player
     * @param overall Habilidade geral do Player
     * @param history Histórico do Player, lista de clubes por onde passou
     */
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

    /**
     * Construtor de Clone
     * @param player Classe Player
     */
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


    /**
     * Getter do atributo name
     * @return Nome do Player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter do atributo name
     * @param name Nome novo do Player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter do atributo number
     * @return Numero do Player
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Setter do atributo number
     * @return Numero novo do Player
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter do atributo speed
     * @return Velociade do Player
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Setter do atributo speed
     * @return Velociade nova do Player
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Getter do atributo resistance
     * @return Resistencia do Player
     */
    public int getResistance() {
        return resistance;
    }

    /**
     * Setter do atributo resistance
     * @return Resistencia nova do Player
     */
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    /**
     * Getter do atributo dexterity
     * @return Destreza do Player
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * Setter do atributo dexterity
     * @return Destreza nova do Player
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * Getter do atributo implusion
     * @return Implusao do Player
     */
    public int getImpulsion() { return this.impulsion; }

    /**
     * Setter do atributo implusion
     * @return Implusao nova do Player
     */
    public void setImpulsion(int impulsion) { this.impulsion = impulsion; }

    /**
     * Getter do atributo headGame
     * @return Jogo de cabeça do Player
     */
    public int getHeadGame() { return this.headGame; }

    /**
     * Setter do atributo headGame
     * @return new Jogo de cabeça  do Player
     */
    public void setHeadGame(int headGame) { this.headGame = headGame; }

    /**
     * Getter do atributo kick
     * @return Remate do Player
     */
    public int getKick() { return this.kick; }

    /**
     * Setter do atributo kick
     * @return Remate novo do Player
     */
    public void setKick(int kick) { this.kick = kick; }

    /**
     * Getter do atributo passCapacity
     * @return Capacidade de pass do Player
     */
    public int getPassCapacity() { return this.passCapacity; }

    /**
     * Setter do atributo passCapacity
     * @return Nova capacidade de pass do Player
     */
    public void setPassCapacity(int passCapacity) { this.passCapacity = passCapacity; }

    /**
     * Getter do atributo overall
     * @return Habilidade Geral do Player
     */
    public int getOverall() { return overall; }

    /**
     * Setter do atributo overall
     * @return Habilidade Geral nova do Player
     */
    public void setOverall(int overall) { this.overall = overall; }

    /**
     * Getter do atributo history
     * @return Historico do Player
     */
    public List<String> getHistory() {
        return this.history;
    }

    /**
     * Setter do atributo history
     * @return Historico novo do Player
     */
    public void setHistory(List<String> history) {
        this.history = new ArrayList<>(history);
    }

    /**
     * Metodo abstrato para calcular a habilidade do Player
     * @return Valor Geral do Player
     */
    public abstract int playerOverallValue();

    /**
     * Método abstrato para gerar um Player com atributos aleatórios
     * @return Class Plauer
     */
    public abstract Player generateNewPlayer();

    /**
     * Compara um objeto com um Player
     * @param o Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Player
     */
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

    /**
     * Metodo hashCode do Player
     * @return Key code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNumber(), getSpeed(), getResistance(), getDexterity(), getImpulsion(), getHeadGame(), getKick(), getPassCapacity(), getOverall(),getHistory());
    }

    /**
     * Transforma a informação do Player numa String
     * @return String com a informação do Player
     */
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

    /**
     * Cola um Player
     * @return Retorna um Player clonado
     */
    public abstract Player clone();

    /**
     * Array de Strings com nomes de Player, que são usados na geração aleatória de um Player
     */
    public final String[] namesOfPlayers = {"Germano", "Jacinto", "Domiciano", "Gustio", "Kleiton",
                                         "Muralhas", "Abilio", "Julio",      "Purp",   "Kleiton",
                                         "Kaufman",  "Gerimbaldo", "Pinga",  "Travassos", "Dimas",
                                         "Costinha", "Maniche",  "Figo",     "Deco",    "Pauleta"};
}
