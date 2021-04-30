package Players;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Sider extends Player{
    /**
     * Construtor Nulo
     */
    public Sider(){ super(); }

    /**
     * Construtor Parametrizado
     * @param name Nome do Sider
     * @param number Numero do Sider
     * @param speed Capacidade ce corrida do Sider
     * @param resistance Capacidade de resistência do Sider
     * @param dexterity Capacidade de destreza do Sider
     * @param discharge Capacidade de implusão do Sider
     * @param headerShoot Capacidade de jogo de cabeça do Sider
     * @param shoot Capacidade de remate do Sider
     * @param pass Capacidade de pass do Sider
     * @param overall Habilidade geral do Sider
     * @param history Histórico do Sider, lista de clubes por onde passou
     */
    public Sider(String name, int number,int speed, int resistance, int dexterity,
                 int discharge, int headerShoot, int shoot, int pass, int overall,List<String> history) {
        super(name,number,speed,resistance,dexterity,discharge,headerShoot,shoot,pass, overall,history);
    }

    /**
     * Construtor de Clone
     * @param s Classe Sider
     */
    public Sider(Sider s) { super(s); }

    /**
     * Método que calcula a habiledade geral do Sider,
     * valorizando mais os atributos mais necessários na sua posição
     * @return Valor inteiro de 0 a 99
     */
    @Override
    public int playerOverallValue() {
        return (int) (0.25*getSpeed() + 0.2*getResistance() + 0.1*getDexterity() + 0.1*getImpulsion() + 0.05*getHeadGame() + 0.05*getKick() + 0.25*getPassCapacity());
    }

    /**
     * Metodo que gera um Sider aleatoriamente
     * @return Objeto da Classe Midfielder
     */
    @Override
    public Sider generateNewPlayer() {
        Sider novo = new Sider();
        novo.setName(novo.namesOfPlayers[ThreadLocalRandom.current().nextInt(0,19)]);
        novo.setNumber(ThreadLocalRandom.current().nextInt(1,99));
        novo.setSpeed(ThreadLocalRandom.current().nextInt(70,100));
        novo.setResistance(ThreadLocalRandom.current().nextInt(65,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(50,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(50,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(10,40));
        novo.setKick(ThreadLocalRandom.current().nextInt(15,50));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(65,100));
        novo.setOverall(novo.playerOverallValue());
        return novo;
    }

    /**
     * Compara um objeto com um Sider,
     * @param obj Objeto para comparar
     * @return Um booleano se o objeto é equivalente ao Sider
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Transforma a informação do Sider numa String
     * @return String com a informação do Sider
     */
    @Override
    public String toString() {
        return "Sider " + super.toString() + '\n';
    }

    /**
     * Clona um Sider
     * @return Copia do Sider
     */
    public Sider clone(){
        return new Sider(this);
    }
}
