package Players;

public class Midfielder extends Player{
    public Midfielder(){ super(); }

    public Midfielder(int speed, int resistance, int dexterity, int discharge,
                      int headerShoot, int shoot, int pass) {
        super(speed,resistance,dexterity,discharge,headerShoot,shoot,pass);
    }

    public Midfielder(Midfielder mid) {
        super(mid);
    }

    @Override
    public int playerOverallValue() {
        return (int) (0.2*getSpeed() + 0.2*getResistance() + 0.1*getDexterity() + 0.1*getImpulsion() + 0.1*getHeadGame() + 0.1*getKick() + 0.2*getPassCapacity());
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Midfielder " + super.toString();
    }

    public Midfielder clone(){
        return new Midfielder(this);
    }
}
