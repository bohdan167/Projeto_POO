package Players;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Defender extends Player{

    /**
     * Construtor Nulo
     */
    public Defender(){ super(); }

    /**
     * Construtor Parametrizado
     * @param name Nome do Defender
     * @param number Numero do Defender
     * @param speed Capacidade ce corrida do Defender
     * @param resistance Capacidade de resistência do Defender
     * @param dexterity Capacidade de destreza do Defender
     * @param discharge Capacidade de implusão do Defender
     * @param headerShoot Capacidade de jogo de cabeça do Defender
     * @param shoot Capacidade de remate do Defender
     * @param pass Capacidade de pass do Defender
     * @param overall Habilidade geral do Defender
     * @param history Histórico do Defender, lista de clubes por onde passou
     */
    public Defender(String name, int number,int speed, int resistance, int dexterity,
                    int discharge, int headerShoot, int shoot, int pass, int overall,List<String> history) {
        super(name,number,speed,resistance,dexterity,discharge,headerShoot,shoot,pass, overall,history);
    }

    /**
     * Construtor de Clone
     * @param d Classe Defender
     */
    public Defender(Defender d) {
        super(d);
    }

    /**
     * Método que calcula a habiledade geral do Defender,
     * valorizando mais os atributos mais necessários na sua posição
     * @return Valor inteiro de 0 a 99
     */
    @Override
    public int playerOverallValue() {
        return (int) (0.15*getSpeed() + 0.2*getResistance() + 0.2*getDexterity() + 0.1*getImpulsion() + 0.15*getHeadGame() + 0.05*getKick() + 0.15*getPassCapacity());
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
        novo.setSpeed(ThreadLocalRandom.current().nextInt(25,60));
        novo.setResistance(ThreadLocalRandom.current().nextInt(70,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(60,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(60,100));
        novo.setKick(ThreadLocalRandom.current().nextInt(10,50));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(55,100));
        novo.setOverall(novo.playerOverallValue());
        return novo;
    }

    /**
     * Compara um objeto com um Defender,
     * @param obj Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Defender
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Transforma a informação do Defender numa String
     * @return String com a informação do Defender
     */
    @Override
    public String toString() {
        return "Defender " + super.toString() + '\n';
    }

    /**
     * Clona um Defemder
     * @return Copia do Defender
     */
    public Defender clone(){
        return new Defender(this);
    }

}
