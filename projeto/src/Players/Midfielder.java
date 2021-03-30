package Players;

public class Midfielder extends Player{
    public Midfielder(){ super(0,0,0,0,0,0,0); }

    public Midfielder(int speed, int resistance, int dexterity, int discharge,
                      int headerShoot, int shoot, int pass) {
        super(speed,resistance,dexterity,discharge,headerShoot,shoot,pass);
    }

    @Override
    public int playerOverallValue() {
        return (this.getSpeed() + this.getPass() + this.getDexterity() + this.getDischarge())/4;
    }

    public Midfielder(Midfielder mid) {
        super(mid.speed, mid.resistance, mid.dexterity, mid.discharge, mid.headerShoot, mid.shoot, mid.pass);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Midfielder{} " + super.toString();
    }
    public Midfielder clone(){
        return new Midfielder(this);
    }
}
