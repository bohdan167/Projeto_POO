package Players;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Striker extends Player{
    /**
     * Construtor Nulo
     */
    public Striker() { super(); }

    /**
     * Construtor Parametrizado
     * @param name Nome do Striker
     * @param number Numero do Striker
     * @param speed Capacidade ce corrida do Striker
     * @param resistance Capacidade de resistência do Striker
     * @param dexterity Capacidade de destreza do Striker
     * @param discharge Capacidade de implusão do Striker
     * @param headerShoot Capacidade de jogo de cabeça do Striker
     * @param shoot Capacidade de remate do Striker
     * @param pass Capacidade de pass do Striker
     * @param overall Habilidade geral do Striker
     * @param history Histórico do Striker, lista de clubes por onde passou
     */
    public Striker(String name, int number,int speed, int resistance, int dexterity,
                   int discharge, int headerShoot, int shoot, int pass, int overall,List<String> history) {
        super(name,number,speed,resistance,dexterity,discharge,headerShoot,shoot,pass, overall,history);
    }

    /**
     * Construtor de Clone
     * @param s Classe Striker
     */
    public Striker(Striker s) { super(s); }

    /**
     * Método que calcula a habiledade geral do Striker,
     * valorizando mais os atributos mais necessários na sua posição
     * @return Valor inteiro de 0 a 99
     */
    @Override
    public int playerOverallValue() {
        return (int) (0.15*getSpeed() + 0.1*getResistance() + 0.1*getDexterity() + 0.1*getImpulsion() + 0.2*getHeadGame() + 0.25*getKick() + 0.1*getPassCapacity());
    }

    /**
     * Metodo que gera um Sider aleatoriamente
     * @return Objeto da Classe Striker
     */
    @Override
    public Striker generateNewPlayer() {
        Striker novo = new Striker();
        novo.setName(novo.namesOfPlayers[ThreadLocalRandom.current().nextInt(0,19)]);
        novo.setNumber(ThreadLocalRandom.current().nextInt(1,99));
        novo.setSpeed(ThreadLocalRandom.current().nextInt(65,100));
        novo.setResistance(ThreadLocalRandom.current().nextInt(55,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(30,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(60,100));
        novo.setKick(ThreadLocalRandom.current().nextInt(70,100));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(55,100));
        novo.setOverall(novo.playerOverallValue());
        return novo;
    }

    /**
     * Compara um objeto com um Striker,
     * @param obj Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Striker
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Transforma a informação do Striker numa String
     * @return String com a informação do Striker
     */
    @Override
    public String toString() {
        return "Striker " + super.toString() + '\n';
    }

    /**
     * Clona um Striker
     * @return Copia do Striker
     */
    public Striker clone(){
        return new Striker(this);
    }
}
