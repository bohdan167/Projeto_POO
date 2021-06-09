package FM.Main.Model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Striker extends Player {
    private int positioning;
    private int ballControl;

    /**
     * Construtor Nulo
     */
    public Striker() {
        super();
        this.positioning = 0;
        this.ballControl = 0;
    }

    /**
     * Construtor Parametrizado
     * @param name Nome do Striker
     * @param number Número do Striker
     * @param sprint Capacidade de sprint do Striker
     * @param speed Capacidade de corrida do Striker
     * @param strength Força do Striker
     * @param agression Nível de agressividade do Striker
     * @param resistance Capacidade de resistência do Striker
     * @param dexterity Capacidade de destreza do Striker
     * @param impulsion Capacidade de implusão do Striker
     * @param headGame Capacidade de jogo de cabeça do Striker
     * @param kick Capacidade de remate do Striker
     * @param passCapacity Capacidade de passe do Striker
     * @param positioning Capacidade do Striker se posicionar
     * @param ballControl Capacidade de controlo de bola do Striker
     * @param overall Habilidade geral do Striker
     * @param history Histórico do Striker, ou seja, lista de clubes por onde passou
     * */
    public Striker(String name, int number, int sprint, int speed, int strength, int agression,
                 int resistance, int dexterity, int impulsion, int headGame, int kick,
                 int passCapacity, int positioning,int ballControl, int goalsScored, int stamina ,int overall, List<String> history){
        super(name,number,sprint,speed,strength,agression,resistance,dexterity,impulsion,headGame,kick,passCapacity,goalsScored,stamina,overall,history);
        this.positioning = positioning;
        this.ballControl = ballControl;
    }

    public Striker(String name, int number, int speed, int resistance, int dexterity, int impulsion, int headGame, int kick, int passCapacity){
        super(name,number,speed,resistance,dexterity,impulsion,headGame,kick, passCapacity);
        this.setSprint(ThreadLocalRandom.current().nextInt(20,75));
        this.setStrength(ThreadLocalRandom.current().nextInt(40,95));
        this.setAgression(ThreadLocalRandom.current().nextInt(40,60));
        this.setPositioning(ThreadLocalRandom.current().nextInt(75,100));
        this.setBallControl(ThreadLocalRandom.current().nextInt(70,100));
        this.setOverall(this.playerOverallValue());
    }

    public Striker(String input){
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
        this.setSprint(ThreadLocalRandom.current().nextInt(70,100));
        this.setStrength(ThreadLocalRandom.current().nextInt(35,85));
        this.setAgression(ThreadLocalRandom.current().nextInt(35,60));
        this.setPositioning(ThreadLocalRandom.current().nextInt(75,100));
        this.setBallControl(ThreadLocalRandom.current().nextInt(70,100));
        this.setStamina(100);
        this.setOverall(this.playerOverallValue());
    }

    /**
     * Construtor de Clone
     * @param s Classe Striker
     */
    public Striker(Striker s) {
        super(s);
        this.positioning = s.getPositioning();
        this.ballControl = s.getBallControl();
    }


    /**
     * Getter do atributo Positioning
     * @return Capacidade do Striker se posicionar
     * */
    public int getPositioning() { return positioning; }

    /**
     * Setter do atributo Positioning
     * @param positioning Nova capacidade do Striker se posicionar
     * */
    public void setPositioning(int positioning) { this.positioning = positioning; }

    /**
     * Getter do atributo BallControl
     * @return Capacidade de controlo de bola do Striker
     * */
    public int getBallControl() { return ballControl; }

    /**
     * Setter do atributo BallControl
     * @param ballControl Nova capacidade de controlo de bola do Striker
     * */
    public void setBallControl(int ballControl) { this.ballControl = ballControl; }

    /**
     * Método que calcula a habiledade geral do Striker,
     * valorizando mais os atributos mais necessários na sua posição
     * @return Valor inteiro de 0 a 99
     */
    @Override
    public int playerOverallValue() {
        return (int) (0.1*getSprint() + 0.1*getSpeed() + 0.1*getStrength() + 0.05*getAgression() + 0.1*getResistance()
                + 0.05*getDexterity() + 0.08*getImpulsion() + 0.1*getHeadGame() + 0.1*getKick() + 0.02*getPassCapacity()
                + 0.1*getBallControl() + 0.1*getPositioning());
    }

    /**
     * Metodo que gera um Striker aleatoriamente
     * @return Objeto da Classe Striker
     */
    @Override
    public Striker generateNewPlayer() {
        Striker novo = new Striker();
        novo.setName(novo.namesOfPlayers[ThreadLocalRandom.current().nextInt(0,19)]);
        novo.setNumber(ThreadLocalRandom.current().nextInt(1,99));
        novo.setSprint(ThreadLocalRandom.current().nextInt(70,100));
        novo.setSpeed(ThreadLocalRandom.current().nextInt(70,100));
        novo.setStrength(ThreadLocalRandom.current().nextInt(35,85));
        novo.setAgression(ThreadLocalRandom.current().nextInt(35,60));
        novo.setResistance(ThreadLocalRandom.current().nextInt(70,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(50,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(50,90));
        novo.setKick(ThreadLocalRandom.current().nextInt(50,100));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(50,100));
        novo.setPositioning(ThreadLocalRandom.current().nextInt(75,100));
        novo.setBallControl(ThreadLocalRandom.current().nextInt(70,100));
        novo.setOverall(novo.playerOverallValue());
        return novo;
    }

    /**
     * Compara um objeto com um Striker
     * @param obj Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Striker
     */
    @Override
    public boolean equals(Object obj) {
        boolean player = super.equals(obj);
        if(!player) return false;
        Striker str = (Striker) obj;
        return (str.getPositioning() == positioning && str.getBallControl() == ballControl);
    }

    public String header() {
        return super.header() + " ".repeat(2) + "Positioning" + " ".repeat(2) + "|" + " ".repeat(2) + "Ball Control" + " ".repeat(2) + "|" + " ".repeat(2)+ "Overall" + " ".repeat(2) + "|\n";
    }

    public String playerTOSTRING(){
        return super.playerTOSTRING() + "\t\t\t\t" + getPositioning() + "\t\t\t\t" + getBallControl() + "\t\t\t\t" + getOverall()
                + "\n" + "\t\t\t\t\t\tHistory:" + getHistory() + "\n";
    }



    /**
     * Transforma a informação do Striker numa String
     * @return String com a informação do Striker
     */
    @Override
    public String toString() {
        return header() + playerTOSTRING();
    }

    /**
     * Clona um Striker
     * @return Copia do Striker
     */
    public Striker clone(){
        return new Striker(this);
    }
}