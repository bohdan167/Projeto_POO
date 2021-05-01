package FM.Main;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Midfielder extends Player {

    /**
     * Construtor Nulo
     */
    public Midfielder(){ super(); }

    /**
     * Construtor Parametrizado
     * @param name Nome do Midfielder
     * @param number Numero do Midfielder
     * @param speed Capacidade ce corrida do Midfielder
     * @param resistance Capacidade de resistência do Midfielder
     * @param dexterity Capacidade de destreza do Midfielder
     * @param discharge Capacidade de implusão do Midfielder
     * @param headerShoot Capacidade de jogo de cabeça do Midfielder
     * @param shoot Capacidade de remate do Midfielder
     * @param pass Capacidade de pass do Midfielder
     * @param overall Habilidade geral do Midfielder
     * @param history Histórico do Midfielder, lista de clubes por onde passou
     */
    public Midfielder(String name, int number,int speed, int resistance, int dexterity,
                      int discharge, int headerShoot, int shoot, int pass, int overall,List<String> history) {
        super(name,number,speed,resistance,dexterity,discharge,headerShoot,shoot,pass, overall,history);
    }

    /**
     * Construtor de Clone
     * @param mid Classe Midfielder
     */
    public Midfielder(Midfielder mid) {
        super(mid);
    }

    /**
     * Método que calcula a habiledade geral do Midfielder,
     * valorizando mais os atributos mais necessários na sua posição
     * @return Valor inteiro de 0 a 99
     */
    @Override
    public int playerOverallValue() {
        return (int) (0.2*getSpeed() + 0.2*getResistance() + 0.15*getDexterity() + 0.05*getImpulsion() + 0.05*getHeadGame() + 0.1*getKick() + 0.25*getPassCapacity());
    }

    /**
     * Metodo que gera um Midfielder aleatoriamente
     * @return Objeto da Classe Midfielder
     */
    @Override
    public Midfielder generateNewPlayer() {
        Midfielder novo = new Midfielder();
        novo.setName(novo.namesOfPlayers[ThreadLocalRandom.current().nextInt(0,19)]);
        novo.setNumber(ThreadLocalRandom.current().nextInt(1,99));
        novo.setSpeed(ThreadLocalRandom.current().nextInt(70,100));
        novo.setResistance(ThreadLocalRandom.current().nextInt(55,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(45,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(20,90));
        novo.setKick(ThreadLocalRandom.current().nextInt(45,100));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(70,100));
        novo.setOverall(novo.playerOverallValue());
        return novo;
    }

    /**
     * Compara um objeto com um Midfielder,
     * @param obj Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Midfielder
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Transforma a informação do Midfielder numa String
     * @return String com a informação do Midfielder
     */
    @Override
    public String toString() {
        return "Midfielder " + super.toString() + '\n';
    }

    /**
     * Clona um Midfielder
     * @return Copia do Midfielder
     */
    public Midfielder clone(){
        return new Midfielder(this);
    }
}
