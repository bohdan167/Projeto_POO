package Players;

public class Defender extends Player{
    public Defender(){ super(0,0,0,0,0,0,0); }

    public Defender(int speed, int resistance, int dexterity, int discharge,
                    int headerShoot, int shoot, int pass) {
        super(speed,resistance,dexterity,discharge,headerShoot,shoot,pass);
    }

    @Override
    public int playerOverallValue() {
        return (this.getResistance() + this.getPass() + this.getHeaderShoot() + this.getDischarge())/4;
    }

    public Defender(Defender d) {
        super(d.speed, d.resistance, d.dexterity, d.discharge, d.headerShoot, d.shoot, d.pass);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Defender{} " + super.toString();
    }

    public Defender clone(){
        return new Defender(this);
    }
}
