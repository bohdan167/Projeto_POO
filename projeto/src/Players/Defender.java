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
