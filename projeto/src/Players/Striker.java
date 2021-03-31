package Players;

public class Striker extends Player{

    public Striker() { super(); }

    public Striker(int speed, int resistance, int dexterity, int discharge,
                   int headerShoot, int shoot, int pass) {
        super(speed,resistance,dexterity,discharge,headerShoot,shoot,pass);
    }

    public Striker(Striker s) { super(s); }

    @Override
    public int playerOverallValue() {
        return (int) (0.2*getSpeed() + 0.1*getResistance() + 0.1*getDexterity() + 0.1*getImpulsion() + 0.2*getHeadGame() + 0.2*getKick() + 0.1*getPassCapacity());
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Striker " + super.toString();
    }

    public Striker clone(){
        return new Striker(this);
    }
}
