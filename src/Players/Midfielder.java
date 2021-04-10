package Players;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Midfielder extends Player{
    public Midfielder(){ super(); }

    public Midfielder(String name, int number,int speed, int resistance, int dexterity,
                      int discharge, int headerShoot, int shoot, int pass, int overall,List<String> history) {
        super(name,number,speed,resistance,dexterity,discharge,headerShoot,shoot,pass, overall,history);
    }

    public Midfielder(Midfielder mid) {
        super(mid);
    }

    @Override
    public int playerOverallValue() {
        return (int) (0.2*getSpeed() + 0.2*getResistance() + 0.15*getDexterity() + 0.05*getImpulsion() + 0.05*getHeadGame() + 0.1*getKick() + 0.25*getPassCapacity());
    }

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


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Midfielder " + super.toString() + '\n';
    }

    public Midfielder clone(){
        return new Midfielder(this);
    }
}
