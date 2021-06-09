package FM.Main.Model;

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
        this.marking = 0;
        this.interception = 0;
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
    public Defender(String name, int number, int sprint, int speed, int strength, int agression, int resistance,
                    int dexterity, int impulsion, int headGame, int kick, int passCapacity, int tackle, int marking,
                    int interception, int goalsScored, int stamina, int overall, List<String> history){
        super(name,number,sprint,speed,strength,agression,resistance,dexterity,impulsion,headGame,kick,
                passCapacity,goalsScored,stamina,overall,history);
        this.tackle = tackle;
        this.marking = marking;
        this.interception = interception;
    }

    public Defender(String name, int number, int speed, int resistance, int dexterity, int impulsion, int headGame, int kick, int passCapacity){
        super(name,number,speed,resistance,dexterity,impulsion,headGame,kick, passCapacity);
        this.setSprint(ThreadLocalRandom.current().nextInt(20,75));
        this.setStrength(ThreadLocalRandom.current().nextInt(40,95));
        this.setAgression(ThreadLocalRandom.current().nextInt(40,60));
        this.setTackle(ThreadLocalRandom.current().nextInt(60,100));
        this.setMarking(ThreadLocalRandom.current().nextInt(60,100));
        this.setInterception(ThreadLocalRandom.current().nextInt(60,100));
        this.setOverall(this.playerOverallValue());
    }

    public Defender (String input){
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
        this.setSprint(ThreadLocalRandom.current().nextInt(20,75));
        this.setStrength(ThreadLocalRandom.current().nextInt(40,95));
        this.setAgression(ThreadLocalRandom.current().nextInt(40,60));
        this.setTackle(ThreadLocalRandom.current().nextInt(60,100));
        this.setMarking(ThreadLocalRandom.current().nextInt(60,100));
        this.setInterception(ThreadLocalRandom.current().nextInt(60,100));
        this.setStamina(100);
        this.setOverall(this.playerOverallValue());
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
     * @param o Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Defender
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Defender defender = (Defender) o;
        return tackle == defender.tackle && marking == defender.marking && interception == defender.interception;
    }

    /**
     * Coloca todas os atributos do Defender numa String
     * @return String
     * */
    public String playerTOSTRING(){
        return super.playerTOSTRING() + "\t\t\t\t" + getTackle() + "\t\t" + getMarking()
                + "\t\t\t" + getInterception() + "\t\t\t\t" + getOverall() + "\n" + "\t\t\t\t\t\tHistory:" + getHistory() + "\n";
    }

    @Override
    public String header() {
        return super.header() + " ".repeat(2) + "Tackle" + " ".repeat(2) + "|" + " ".repeat(2) + "Marking" + " ".repeat(2) + "|" + " ".repeat(2) + "Interception" + " ".repeat(2) + "|" + " ".repeat(2) + "Overall" + " ".repeat(2) + "|\n";
    }

    /**
     * Transforma a informação do Defender numa String
     * @return String com a informação do Defender
     */
    @Override
    public String toString() {
        return header() + playerTOSTRING();
    }

    /**
     * Clona um Defender
     * @return Cópia do Defender
     */
    public Defender clone(){
        return new Defender(this);
    }
}
