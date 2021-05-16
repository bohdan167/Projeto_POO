package FM.Main;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Midfielder extends Player {
    private int vision;
    private int ballRecovery;

    /**
     * Construtor Nulo
     */
    public Midfielder(){ super(); }

    /**
     * Construtor Parametrizado
     * @param name Nome do Midfielder
     * @param number Número do Midfielder
     * @param sprint Capacidade de sprint do Midfielder
     * @param speed Capacidade de corrida do Midfielder
     * @param strength Força do Midfielder
     * @param agression Nível de agressividade do Midfielder
     * @param resistance Capacidade de resistência do Midfielder
     * @param dexterity Capacidade de destreza do Midfielder
     * @param impulsion Capacidade de implusão do Midfielder
     * @param headGame Capacidade de jogo de cabeça do Midfielder
     * @param kick Capacidade de remate do Midfielder
     * @param passCapacity Capacidade de passe do Midfielder
     * @param vision Capacidade de visão do Mifdielder
     * @param ballRecovery Capacidade de recuperação de bola do Midfielder
     * @param overall Habilidade geral do Midfielder
     * @param history Histórico do Midfielder, ou seja, lista de clubes por onde passou
     * */
    public Midfielder(String name, int number, int sprint, int speed, int strength, int agression,
                    int resistance, int dexterity, int impulsion, int headGame, int kick,
                    int passCapacity, int vision, int ballRecovery,int overall, List<String> history){
        super(name,number,sprint,speed,strength,agression,resistance,dexterity,impulsion,headGame,kick,passCapacity,overall,history);
        this.vision = vision;
        this.ballRecovery = ballRecovery;
    }

    /**
     * Construtor de Clone
     * @param mid Classe Midfielder
     */
    public Midfielder(Midfielder mid) {
        super(mid);
        this.vision = mid.getVision();
    }

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
     * Getter do atributo BallRecovery
     * @return Capacidade de recuperação de bola do Midfielder
     * */
    public int getBallRecovery() { return ballRecovery; }

    /**
     * Setter do atributo BallRecovery
     * @param ballRecovery Nova capacidade de recuperação de bola do Midfielder
     * */
    public void setBallRecovery(int ballRecovery) { this.ballRecovery = ballRecovery; }

    /**
     * Método que calcula a habilidade geral do Midfielder,
     * valorizando mais os atributos mais necessários na sua posição
     * @return Valor inteiro de 0 a 99
     */
    @Override
    public int playerOverallValue() {
        return (int) (0.02*getSprint() + 0.1*getSpeed() + 0.05*getStrength() + 0.05*getAgression() + 0.15*getResistance()
                + 0.05*getDexterity() + 0.02*getImpulsion() + 0.01*getHeadGame() + 0.10*getKick() + 0.15*getPassCapacity()
                + 0.15*getVision() + 0.15*getBallRecovery());
    }

    /**
     * Metodo que gera um Midfielder aleatório
     * @return Objeto da Classe Midfielder
     */
    @Override
    public Midfielder generateNewPlayer() {
        Midfielder novo = new Midfielder();
        novo.setName(novo.namesOfPlayers[ThreadLocalRandom.current().nextInt(0,19)]);
        novo.setNumber(ThreadLocalRandom.current().nextInt(1,99));
        novo.setSprint(ThreadLocalRandom.current().nextInt(50,100));
        novo.setSpeed(ThreadLocalRandom.current().nextInt(70,100));
        novo.setStrength(ThreadLocalRandom.current().nextInt(40,85));
        novo.setAgression(ThreadLocalRandom.current().nextInt(35,60));
        novo.setResistance(ThreadLocalRandom.current().nextInt(55,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(45,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(20,90));
        novo.setKick(ThreadLocalRandom.current().nextInt(45,100));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(70,100));
        novo.setVision(ThreadLocalRandom.current().nextInt(65,100));
        novo.setBallRecovery(ThreadLocalRandom.current().nextInt(60,100));
        novo.setOverall(novo.playerOverallValue());
        return novo;
    }

    /**
     * Compara um objeto com um Midfielder
     * @param obj Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Midfielder
     */
    @Override
    public boolean equals(Object obj) {
        boolean player = super.equals(obj);
        if(!player) return false;
        Midfielder m = (Midfielder) obj;
        return (m.getVision() == vision && m.getBallRecovery() == ballRecovery);
    }

    /** Coloca todos os atributos de um Midfielder numa String
     * @return String
     * */
    public String playerTOSTRING(){
        return super.playerTOSTRING() + "\t\t\t\t" + getBallRecovery() + "\t\t\t\t" + getVision()
                + "\t\t" + getOverall() + "\n" + "\t\t\t\t\t\tHistory:" + getHistory();
    }

    /**
     * Transforma a informação do Midfielder numa String
     * @return String com a informação do Midfielder
     */
    @Override
    public String toString() {
        return "Position\t\t\tName\t    Number\tSprint\tSpeed\tStrength\tAgression\tResistance\tDexterity\tImpulsion\tHead Game\tKick\tPass Capacity\tBall Recovery\tVision\tOverall"
                + playerTOSTRING() + "\n";
    }

    /**
     * Clona um Midfielder
     * @return Copia do Midfielder
     */
    public Midfielder clone(){
        return new Midfielder(this);
    }
}
