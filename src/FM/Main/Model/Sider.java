package FM.Main.Model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Sider extends Player {
    private int crossing;
    private int vision;
    /**
     * Construtor Nulo
     */
    public Sider(){
        super();
        this.crossing = 0;
        this.vision = 0;
    }

    /**
     * Construtor Parametrizado
     * @param name Nome do Sider
     * @param number Número do Sider
     * @param sprint Capacidade de sprint do Sider
     * @param speed Capacidade de corrida do Sider
     * @param strength Força do Sider
     * @param agression Nível de agressividade do Sider
     * @param resistance Capacidade de resistência do Sider
     * @param dexterity Capacidade de destreza do Sider
     * @param impulsion Capacidade de implusão do Sider
     * @param headGame Capacidade de jogo de cabeça do Sider
     * @param kick Capacidade de remate do Sider
     * @param passCapacity Capacidade de passe do Sider
     * @param crossing Capacidade de cruzamento do Sider
     * @param vision Capacidade de visão do Mifdielder
     * @param overall Habilidade geral do Sider
     * @param history Histórico do Sider, ou seja, lista de clubes por onde passou
     * */
    public Sider(String name, int number, int sprint, int speed, int strength, int agression,
                      int resistance, int dexterity, int impulsion, int headGame, int kick,
                      int passCapacity, int crossing,int vision, int goalsScored, int stamina, int overall, List<String> history){
        super(name,number,sprint,speed,strength,agression,resistance,dexterity,impulsion,headGame,kick,passCapacity,goalsScored,stamina,overall,history);
        this.vision = vision;
        this.crossing = crossing;
    }

    public Sider(String name, int number, int speed, int resistance, int dexterity, int impulsion, int headGame, int kick, int passCapacity, int crossing){
        super(name,number,speed,resistance,dexterity,impulsion,headGame,kick,passCapacity);
        this.crossing = crossing;
        this.setSprint(ThreadLocalRandom.current().nextInt(50,100));
        this.setStrength(ThreadLocalRandom.current().nextInt(40,85));
        this.setAgression(ThreadLocalRandom.current().nextInt(40,60));
        this.setVision(ThreadLocalRandom.current().nextInt(65,100));
        this.setOverall(this.playerOverallValue());
    }

    public Sider (String input){
        String[] campos = input.split(",");
        this.setName(campos[0]);
        this.setNumber(Integer.parseInt(campos[1]));
        this.setSpeed(Integer.parseInt(campos[2]));
        this.setResistance(Integer.parseInt(campos[3]));
        this.setDexterity(Integer.parseInt(campos[4]));
        this.setImpulsion(Integer.parseInt(campos[5]));
        this.setHeadGame(Integer.parseInt(campos[6]));
        this.setKick(Integer.parseInt(campos[7]));
        this.setPassCapacity(Integer.parseInt(campos[8]));
        this.setSprint(ThreadLocalRandom.current().nextInt(50,100));
        this.setStrength(ThreadLocalRandom.current().nextInt(40,85));
        this.setAgression(ThreadLocalRandom.current().nextInt(40,60));
        this.setCrossing(ThreadLocalRandom.current().nextInt(70,100));
        this.setVision(ThreadLocalRandom.current().nextInt(65,100));
        this.setStamina(100);
        this.setOverall(this.playerOverallValue());
    }

    /**
     * Getter do atributo Crossing
     * @return Capacidade de cruzamento do Sider
     * */
    public int getCrossing() { return crossing; }

    /**
     * Setter do atributo Crossing
     * @param crossing Nova capacidade de cruzamento do Sider
     * */
    public void setCrossing(int crossing) { this.crossing = crossing; }

    /**
     * Getter do atributo Vision
     * @return Capacidade de visão do Mifdielder
     * */
    public int getVision() { return vision; }

    /**
     * Setter do atributo Vision
     * @param vision Nova capacidade de visão do Mifdielder
     * */
    public void setVision(int vision) { this.vision = vision; }

    /**
     * Construtor de Clone
     * @param s Classe Sider
     */
    public Sider(Sider s) {
        super(s);
        this.crossing = s.getCrossing();
        this.vision = s.getVision();
    }

    /**
     * Método que calcula a habiledade geral do Sider,
     * valorizando mais os atributos mais necessários na sua posição
     * @return Valor inteiro de 0 a 99
     */
    @Override
    public int playerOverallValue() {
        return (int) (0.02*getSprint() + 0.15*getSpeed() + 0.05*getStrength() + 0.05*getAgression() + 0.15*getResistance()
                + 0.05*getDexterity() + 0.02*getImpulsion() + 0.01*getHeadGame() + 0.1*getKick() + 0.1*getPassCapacity()
                + 0.15*getCrossing() + 0.15*getVision());
    }

    /**
     * Metodo que gera um Sider aleatoriamente
     * @return Objeto da Classe Sider
     */
    @Override
    public Sider generateNewPlayer() {
        Sider novo = new Sider();
        novo.setName(novo.namesOfPlayers[ThreadLocalRandom.current().nextInt(0,19)]);
        novo.setNumber(ThreadLocalRandom.current().nextInt(1,99));
        novo.setSprint(ThreadLocalRandom.current().nextInt(50,100));
        novo.setSpeed(ThreadLocalRandom.current().nextInt(75,100));
        novo.setStrength(ThreadLocalRandom.current().nextInt(40,85));
        novo.setAgression(ThreadLocalRandom.current().nextInt(40,60));
        novo.setResistance(ThreadLocalRandom.current().nextInt(60,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(45,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(60,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(20,90));
        novo.setKick(ThreadLocalRandom.current().nextInt(50,100));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(70,100));
        novo.setCrossing(ThreadLocalRandom.current().nextInt(70,100));
        novo.setVision(ThreadLocalRandom.current().nextInt(65,100));
        novo.setOverall(novo.playerOverallValue());
        return novo;
    }

    /**
     * Compara um objeto com um Sider
     * @param obj Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Sider
     */
    @Override
    public boolean equals(Object obj) {
        boolean player = super.equals(obj);
        if(!player)return false;
        Sider s = (Sider) obj;
        return (s.getVision() == vision && s.getCrossing() == crossing);
    }

    /** Coloca todos os atributos de um Sider numa String
     * @return String
     * */
    public String playerTOSTRING(){
        return super.playerTOSTRING() + "\t\t\t\t" + getCrossing() + "\t\t\t" + getVision()
                + "\t\t" + getOverall() + "\n" + "\t\t\t\t\t\tHistory:" + getHistory() + "\n";
    }

    @Override
    public String header() {
        return super.header() + " ".repeat(2) + "Crossing" + " ".repeat(2) + "|" + " ".repeat(2) + "Vision" + " ".repeat(2) + "|" + " ".repeat(2)+ "Overall" + " ".repeat(2) + "|\n";
    }
    /**
     * Transforma a informação do Sider numa String
     * @return String com a informação do Sider
     */
    public String toString() {
        return header() + playerTOSTRING();
    }

    /**
     * Clona um Sider
     * @return Copia do Sider
     */
    public Sider clone(){
        return new Sider(this);
    }
}
