package FM.Main;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Goalkeeper extends Player {
    private int elasticity;
    private int handling;
    private int reflexes;
    private int diving;

    /**
     * Construtor Nulo
     */
    public Goalkeeper(){
        super();
        this.elasticity = 0;
        this.handling = 0;
        this.reflexes = 0;
        this.diving = 0;
    }

    /**
     * Construtor Parametrizado
     * @param name Nome do Goalkeeper
     * @param number Número do Goalkeeper
     * @param sprint Capacidade de sprint do Goalkeeper
     * @param speed Capacidade de corrida do Goalkeeper
     * @param strength Força do Goalkeeper
     * @param agression Nível de agressividade do Goalkeeper
     * @param resistance Capacidade de resistência do Goalkeeper
     * @param dexterity Capacidade de destreza do Goalkeeper
     * @param impulsion Capacidade de implusão do Goalkeeper
     * @param headGame Capacidade de jogo de cabeça do Goalkeeper
     * @param kick Capacidade de remate do Goalkeeper
     * @param passCapacity Capacidade de passe do Goalkeeper
     * @param elasticity Nível de elasticidade do Goalkeeper
     * @param handling Capacidade de defender com as mãos do Goalkeeper
     * @param reflexes Capacidade de reação do Goalkeeper
     * @param diving Capacidade do Goalkeeper se atirar
     * @param overall Habilidade geral do Goalkeeper
     * @param history Histórico do Goalkeeper, ou seja, lista de clubes por onde passou
     * */
    public Goalkeeper(String name, int number, int sprint, int speed, int strength, int agression,
                      int resistance, int dexterity, int impulsion, int headGame, int kick,
                      int passCapacity, int elasticity, int handling, int reflexes, int diving,
                      int overall, List<String> history) {
        super(name,number,sprint,speed,strength,agression,resistance,dexterity,impulsion,headGame,kick,passCapacity,overall,history);
        this.elasticity = elasticity;
        this.handling = handling;
        this.reflexes = reflexes;
        this.diving = diving;
    }

    /**
     * Construtor de Clone
     * @param gk Classe Goalkeeper
     */
    public Goalkeeper(Goalkeeper gk){
        super(gk);
        this.elasticity = gk.getElasticity();
        this.handling = gk.getHandling();
        this.reflexes = gk.getReflexes();
        this.diving = gk.getDiving();
    }

    /**
     * Getter do atributo Elasticity
     * @return Elasticidade do Goalkeeper
     */
    public int getElasticity() {
        return elasticity;
    }

    /**
     * Setter do atributo Elasticity
     * @param elasticity Novo nível de elasticidade do Goalkeeper
     */
    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }

    /**
     * Getter do atributo Handling
     * @return Handling do Goalkeeper
     * */
    public int getHandling() { return handling; }

    /**
     * Setter do atributo Handling
     * @param handling Novo nível de Handling do Goalkeeper
     * */
    public void setHandling(int handling) { this.handling = handling; }

    /**
     * Getter do atributo Reflexes
     * @return Capacidade de reflexos do Goalkeeper
     * */
    public int getReflexes() { return reflexes; }

    /**
     * Setter do atributo Reflexes
     * @param reflexes Novo nível de reflexos do Goalkeeper
     * */
    public void setReflexes(int reflexes) { this.reflexes = reflexes; }

    /**
     * Getter do atributo Diving
     * @return Capacidade de "mergulhar" para a defesa do Goalkeeper
     * */
    public int getDiving() { return diving; }

    /**
     * Setter do atributo Diving
     * @param diving Novo nível de Diving do Goalkeeper
     * */
    public void setDiving(int diving) { this.diving = diving; }

    /**
     * Método que calcula a habiledade geral do Goalkeeper,
     * valorizando mais os atributos mais necessários na sua posição
     * @return Valor inteiro de 0 a 99
     */
    @Override
    public int playerOverallValue() {
        return (int) (0.01*getSprint() + 0.01*getSpeed() + 0.02*getStrength() + 0.01*getAgression() + 0.01*getResistance()
                + 0.01*getDexterity() + 0.02*getImpulsion() + 0.01*getHeadGame() + 0.15*getKick() + 0.15*getPassCapacity()
                + 0.15*getElasticity() + 0.15*getHandling() + 0.15*getReflexes() + 0.15*getDiving());

    }

    /**
     * Metodo que gera um Goalkeeper aleatoriamente
     * @return Objeto da Classe Goalkeeper
     */
    @Override
    public Goalkeeper generateNewPlayer() {
        Goalkeeper novo = new Goalkeeper();
        novo.setName(novo.namesOfPlayers[ThreadLocalRandom.current().nextInt(0,19)]);
        novo.setNumber(ThreadLocalRandom.current().nextInt(1,99));
        novo.setSprint(ThreadLocalRandom.current().nextInt(20,60));
        novo.setSpeed(ThreadLocalRandom.current().nextInt(20,60));
        novo.setStrength(ThreadLocalRandom.current().nextInt(20,95));
        novo.setAgression(ThreadLocalRandom.current().nextInt(20,60));
        novo.setResistance(ThreadLocalRandom.current().nextInt(55,80));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(30,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(10,50));
        novo.setKick(ThreadLocalRandom.current().nextInt(40,85));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(60,100));
        novo.setElasticity(ThreadLocalRandom.current().nextInt(60,100));
        novo.setHandling(ThreadLocalRandom.current().nextInt(70,100));
        novo.setReflexes(ThreadLocalRandom.current().nextInt(65,100));
        novo.setDiving(ThreadLocalRandom.current().nextInt(65,100));
        novo.setOverall(novo.playerOverallValue());
        return novo;
    }

    /**
     * Compara um objeto com um Goalkeeper,
     * @param obj Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Goalkeeper
     */
    @Override
    public boolean equals(Object obj) {
        boolean player = super.equals(obj);
        if (!player) return false;
        Goalkeeper goalkeeper = (Goalkeeper) obj;
        return ((elasticity == goalkeeper.getElasticity() && handling == goalkeeper.getHandling() &&
                reflexes == goalkeeper.getReflexes() && diving == goalkeeper.getDiving()));
    }

    /**
     * Coloca todas os atributos de um Goalkeeper numa String
     * @return String
     * */
    public String playerTOSTRING() {
        return super.playerTOSTRING() + "\t\t\t\t" + getElasticity() + "\t\t\t" + getHandling()
                + "\t\t\t" + getReflexes() + "\t\t\t" + getDiving() + "\t\t" + getOverall() + "\n"
                + "\t\t\t\t\t\tHistory:" + getHistory();
    }

    /**
     * Transforma a informação do Goalkeeper numa String
     * @return String com a informação do Goalkeeper
     */
    public String toString() {
        return "Position\t\t\tName\t    Number\tSprint\tSpeed\tStrength\tAgression\tResistance\tDexterity\tImpulsion\tHead Game\tKick\tPass Capacity\tElasticity\tHandling\tReflexes\tDiving\tOverall"
                + playerTOSTRING() + "\n";
    }

    /**
     * Clona um Goalkeeper
     * @return Copia do Goalkeeper
     */
    public Goalkeeper clone(){
        return new Goalkeeper(this);
    }
}
