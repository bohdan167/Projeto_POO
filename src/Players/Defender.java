package Players;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Defender extends Player{
    public Defender(){ super(); }

    public Defender(String name, int number,int speed, int resistance, int dexterity,
                    int discharge, int headerShoot, int shoot, int pass, int overall,List<String> history) {
        super(name,number,speed,resistance,dexterity,discharge,headerShoot,shoot,pass, overall,history);
    }

    public Defender(Defender d) {
        super(d);
    }

    @Override
    public int playerOverallValue() {
        return (int) (0.15*getSpeed() + 0.2*getResistance() + 0.2*getDexterity() + 0.1*getImpulsion() + 0.15*getHeadGame() + 0.05*getKick() + 0.15*getPassCapacity());
    }

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


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Defender " + super.toString();
    }

    public Defender clone(){
        return new Defender(this);
    }

}
