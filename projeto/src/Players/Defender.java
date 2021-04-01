package Players;

import java.util.concurrent.ThreadLocalRandom;

public class Defender extends Player{
    public Defender(){ super(); }

    public Defender(int speed, int resistance, int dexterity, int discharge,
                    int headerShoot, int shoot, int pass) {
        super(speed,resistance,dexterity,discharge,headerShoot,shoot,pass);
    }

    public Defender(Defender d) {
        super(d);
    }

    @Override
    public int playerOverallValue() {
        return (int) (0.1*getSpeed() + 0.2*getResistance() + 0.2*getDexterity() + 0.1*getImpulsion() + 0.2*getHeadGame() + 0.05*getKick() + 0.15*getPassCapacity());
    }

    @Override
    public Defender generateNewPlayer() {
        Defender novo = new Defender();
        novo.setSpeed(ThreadLocalRandom.current().nextInt(25,60));
        novo.setResistance(ThreadLocalRandom.current().nextInt(70,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(60,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(60,100));
        novo.setKick(ThreadLocalRandom.current().nextInt(10,50));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(55,100));
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
