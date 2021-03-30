package Players;

public class Sider extends Player{
    public Sider(){ super(0,0,0,0,0,0,0); }

    public Sider(int speed, int resistance, int dexterity, int discharge,
                 int headerShoot, int shoot, int pass) {
        super(speed,resistance,dexterity,discharge,headerShoot,shoot,pass);
    }

    @Override
    public int playerOverallValue() {
        return (this.getSpeed() + this.getPass() + this.getResistance() + this.getDexterity())/4;
    }

    public Sider(Sider s) { super(s.speed,s.resistance,s.dexterity,s.discharge,s.headerShoot,s.shoot,s.pass); }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Sider{} " + super.toString();
    }

    public Sider clone(){
        return new Sider(this);
    }
}
