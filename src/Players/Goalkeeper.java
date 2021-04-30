package Players;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Goalkeeper extends Player{
    private int elasticity;

    /**
     * Construtor Nulo
     */
    public Goalkeeper(){ super(); this.elasticity=0;}

    /**
     * Construtor Parametrizado
     * @param name Nome do Goalkeeper
     * @param number Numero do Goalkeeper
     * @param speed Capacidade ce corrida do Goalkeeper
     * @param resistance Capacidade de resistência do Goalkeeper
     * @param dexterity Capacidade de destreza do Goalkeeper
     * @param discharge Capacidade de implusão do Goalkeeper
     * @param headerShoot Capacidade de jogo de cabeça do Goalkeeper
     * @param shoot Capacidade de remate do Goalkeeper
     * @param pass Capacidade de pass do Goalkeeper
     * @param elasticity Capacidade de elastecidade do Goalkeeper
     * @param overall Habilidade geral do Goalkeeper
     * @param history Histórico do Goalkeeper, lista de clubes por onde passou
     */
    public Goalkeeper(String name, int number,int speed, int resistance, int dexterity, int discharge,
                      int headerShoot, int shoot, int pass,int elasticity, int overall,List<String> history) {
        super(name,number,speed,resistance,dexterity,discharge,headerShoot,shoot,pass, overall,history);
        this.elasticity = elasticity;
    }

    /**
     * Construtor de Clone
     * @param gk Classe Goalkeeper
     */
    public Goalkeeper(Goalkeeper gk){
        super(gk);
        this.elasticity = gk.elasticity;
    }

    /**
     * Getter do atributo elasticity
     * @return Elestecidade do goalkeeper
     */
    public int getElasticity() {
        return elasticity;
    }

    /**
     * Setter do atributo resistance
     * @return Resistencia nova do Player
     */
    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }

    /**
     * Método que calcula a habiledade geral do Goalkeeper,
     * valorizando mais os atributos mais necessários na sua posição
     * @return Valor inteiro de 0 a 99
     */
    @Override
    public int playerOverallValue() {
        return (int) (0.2*getElasticity() + 0.2*getPassCapacity() + 0.2*getImpulsion() +
                0.1*getResistance() + 0.14*getDexterity() + 0.08*getSpeed() + 0.04*getKick() + 0.04*getHeadGame());
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
        novo.setSpeed(ThreadLocalRandom.current().nextInt(20,60));
        novo.setResistance(ThreadLocalRandom.current().nextInt(55,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(30,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(10,50));
        novo.setKick(ThreadLocalRandom.current().nextInt(10,50));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(60,100));
        novo.setElasticity(ThreadLocalRandom.current().nextInt(70,100));
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
        return super.equals(obj);
    }

    /**
     * Transforma a informação do Goalkeeper numa String
     * @return String com a informação do Goalkeeper
     */
    @Override
    public String toString() {
        return  "Goalkeeper " + super.toString() +
                "\t\t[Elasticity]-----\n" + this.elasticity;
    }

    /**
     * Clona um Goalkeeper
     * @return Copia do Goalkeeper
     */
    public Goalkeeper clone(){
        return new Goalkeeper(this);
    }

}
