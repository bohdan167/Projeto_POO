package Players;

public class Goalkepper extends Player{
    private int elasticity; //elastecidade

    public Goalkepper(){ super(0,0,0,0,0,0,0); }

    public Goalkepper(int speed, int resistance, int dexterity, int discharge,
                      int headerShoot, int shoot, int pass,int elasticity) {
        super(speed,resistance,dexterity,discharge,headerShoot,shoot,pass);
        this.elasticity = elasticity;
    }
    public Goalkepper(Goalkepper gk){
        super(gk.speed, gk.resistance, gk.dexterity, gk.discharge, gk.headerShoot, gk.shoot, gk.pass);
        this.elasticity = gk.elasticity;
    }

    public int getElasticity() {
        return elasticity;
    }

    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }

    @Override
    public int playerOverallValue() {
        return (this.elasticity + this.getPass() + this.getDexterity() + this.getResistance())/4;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Goalkepper{" +
                "elasticity=" + elasticity +
                "} " + super.toString();
    }

    public Goalkepper clone(){
        return new Goalkepper(this);
    }
}
