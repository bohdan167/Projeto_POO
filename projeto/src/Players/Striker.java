package Players;

public class Striker extends Player{

    public Striker() { super(0,0,0,0,0,0,0); }

    public Striker(int speed, int resistance, int dexterity, int discharge,
                   int headerShoot, int shoot, int pass) {
        super(speed,resistance,dexterity,discharge,headerShoot,shoot,pass);
    }

    @Override
    public int playerOverallValue() {
        return (this.getShoot() + this.getHeaderShoot() + this.getSpeed() + this.getDischarge())/4;
    }

    public Striker(Striker s) { super(s.speed,s.resistance,s.dexterity,s.discharge,s.headerShoot,s.shoot,s.pass); }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Striker{} " + super.toString();
    }

    public Striker clone(){
        return new Striker(this);
    }
}
