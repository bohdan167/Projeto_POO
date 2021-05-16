package FM.Main;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Defender extends Player {
    private int tackle;
    private int marking;
    private int interception;

    /**
     * Construtor Nulo
     */
    public Defender(){
        super();
        this.tackle = 0;
    }

    /**
     * Construtor Parametrizado
     * @param name Nome do Defender
     * @param number Número do Defender
     * @param sprint Capacidade de sprint do Defender
     * @param speed Capacidade de corrida do Defender
     * @param strength Força do Defender
     * @param agression Nível de agressividade do Defender
     * @param resistance Capacidade de resistência do Defender
     * @param dexterity Capacidade de destreza do Defender
     * @param impulsion Capacidade de implusão do Defender
     * @param headGame Capacidade de jogo de cabeça do Defender
     * @param kick Capacidade de remate do Defender
     * @param passCapacity Capacidade de passe do Defender
     * @param tackle Capacidade de enfrentar o adversário do Defender
     * @param marking Capacidade de marcação ao adversário do Defender
     * @param interception Capacidade de interceptar passes do adversário do Defender
     * @param overall Habilidade geral do Defender
     * @param history Histórico do Defender, ou seja, lista de clubes por onde passou
     * */
    public Defender(String name, int number, int sprint, int speed, int strength, int agression,
                    int resistance, int dexterity, int impulsion, int headGame, int kick,
                    int passCapacity, int tackle, int marking, int interception, int overall, List<String> history){
        super(name,number,sprint,speed,strength,agression,resistance,dexterity,impulsion,headGame,kick,passCapacity,overall,history);
        this.tackle = tackle;
        this.marking = marking;
        this.interception = interception;
    }

    /**
     * Construtor de Clone
     * @param d Classe Defender
     */
    public Defender(Defender d) {
        super(d);
        this.tackle = d.getTackle();
        this.interception = d.getInterception();
        this.marking = d.getMarking();
    }

    /**
     * Getter do atributo Tackle
     * @return Capacidade de enfrentar o adversário por parte do Defender
     * */
    public int getTackle() { return tackle; }

    /**
     * Setter do atributo Tackle
     * @param tackle Nova capacidade de enfrentar o adversário por parte do Defender
     * */
    public void setTackle(int tackle) { this.tackle = tackle; }

    /**
     * Getter do atributo Marking
     * @return Capacidade de marcação ao adversário do Defender
     * */
    public int getMarking() { return marking; }

    /**
     * Setter do atributo Marking
     * @param marking Nova capacidade de marcação ao adversário do Defender
     * */
    public void setMarking(int marking) { this.marking = marking; }

    /**
     * Getter do atributo Interception
     * @return Capacidade de interceptar passes do adversário do Defender
     * */
    public int getInterception() { return interception; }

    /**
     * Setter do atributo Interception
     * @param interception Nova capacidade de interceptar passes do adversário do Defender
     * */
    public void setInterception(int interception) { this.interception = interception; }

    /**
     * Método que calcula a habiledade geral do Defender,
     * valorizando mais os atributos mais necessários na sua posição
     * @return Valor inteiro de 0 a 99
     */
    @Override
    public int playerOverallValue() {
        return (int) (0.1*getSprint() + 0.05*getSpeed() + 0.1*getStrength() + 0.1*getAgression() + 0.1*getResistance()
                + 0.05*getDexterity() + 0.05*getImpulsion() + 0.05*getHeadGame() + 0.05*getKick() + 0.05*getPassCapacity()
                + 0.1*getTackle() + 0.1*getMarking() + 0.1*getInterception());
    }

    /**
     * Metodo que gera um Defender aleatoriamente
     * @return Objeto da Classe Defender
     */
    @Override
    public Defender generateNewPlayer() {
        Defender novo = new Defender();
        novo.setName(novo.namesOfPlayers[ThreadLocalRandom.current().nextInt(0,19)]);
        novo.setNumber(ThreadLocalRandom.current().nextInt(1,99));
        novo.setSprint(ThreadLocalRandom.current().nextInt(20,75));
        novo.setSpeed(ThreadLocalRandom.current().nextInt(20,70));
        novo.setStrength(ThreadLocalRandom.current().nextInt(40,95));
        novo.setAgression(ThreadLocalRandom.current().nextInt(40,60));
        novo.setResistance(ThreadLocalRandom.current().nextInt(55,90));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(30,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(10,50));
        novo.setKick(ThreadLocalRandom.current().nextInt(40,80));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(60,100));
        novo.setTackle(ThreadLocalRandom.current().nextInt(60,100));
        novo.setMarking(ThreadLocalRandom.current().nextInt(60,100));
        novo.setInterception(ThreadLocalRandom.current().nextInt(60,100));
        novo.setOverall(novo.playerOverallValue());
        return novo;
    }

    /**
     * Compara um objeto com um Defender
     * @param obj Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Defender
     */
    @Override
    public boolean equals(Object obj) {
        boolean player = super.equals(obj);
        if (!player) return false;
        Defender d = (Defender) obj;
        return this.marking == d.getMarking() && this.tackle == d.getTackle() && this.interception == d.getInterception();
    }

    /**
     * Coloca todas os atributos do Defender numa String
     * @return String
     * */
    public String playerTOSTRING(){
        return super.playerTOSTRING() + "\t\t\t\t" + getTackle() + "\t\t" + getMarking()
                + "\t\t\t" + getInterception() + "\t\t\t\t" + getOverall() + "\n" + "\t\t\t\t\t\tHistory:" + getHistory();
    }

    /**
     * Transforma a informação do Defender numa String
     * @return String com a informação do Defender
     */
    @Override
    public String toString() {
        return "Position\t\t\tName\t    Number\tSprint\tSpeed\tStrength\tAgression\tResistance\tDexterity\tImpulsion\tHead Game\tKick\tPass Capacity\tTackle\tMarking\t\tInterception\tOverall"
                + playerTOSTRING() + "\n";
    }

    /**
     * Clona um Defemder
     * @return Cópia do Defender
     */
    public Defender clone(){
        return new Defender(this);
    }
}
