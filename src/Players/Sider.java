package Players;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Sider extends Player{
    public Sider(){ super(); }

    public Sider(String name, int number,int speed, int resistance, int dexterity,
                 int discharge, int headerShoot, int shoot, int pass, int overall,List<String> history) {
        super(name,number,speed,resistance,dexterity,discharge,headerShoot,shoot,pass, overall,history);
    }

    public Sider(Sider s) { super(s); }

    @Override
    public int playerOverallValue() {
        return (int) (0.25*getSpeed() + 0.2*getResistance() + 0.1*getDexterity() + 0.1*getImpulsion() + 0.05*getHeadGame() + 0.05*getKick() + 0.25*getPassCapacity());
    }

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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Sider " + super.toString();
    }

    public Sider clone(){
        return new Sider(this);
    }
}
