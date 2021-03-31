package Players;

import java.util.concurrent.ThreadLocalRandom;

public class Goalkepper extends Player{
    private int elasticity; //elastecidade

    public Goalkepper(){ super(); this.elasticity=0;}

    public Goalkepper(int speed, int resistance, int dexterity, int discharge,
                      int headerShoot, int shoot, int pass,int elasticity) {
        super(speed,resistance,dexterity,discharge,headerShoot,shoot,pass);
        this.elasticity = elasticity;
    }
    public Goalkepper(Goalkepper gk){
        super(gk);
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
        return (int) (0.2*getElasticity() + 0.2*getPassCapacity() + 0.2*getImpulsion() +
                0.1*getResistance() + 0.1*getDexterity() + 0.08*getSpeed() + 0.08*getKick() + 0.04*getHeadGame());
    }
    /*
    @Override
    public Player gererateNewPlayer() {
        Goalkepper novo = new Goalkepper();
        novo.setSpeed(ThreadLocalRandom.current().nextInt(20,60));
        novo.setResistance(ThreadLocalRandom.current().nextInt(55,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(30,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(10,40));
        novo.setKick(ThreadLocalRandom.current().nextInt(10,50));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(60,100));
        novo.setElasticity(ThreadLocalRandom.current().nextInt(70,100));
        return novo;
    }*/

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return  "Goalkepper " + super.toString() +
                "\t[Elasticity]-----" + this.elasticity;
    }

    public Goalkepper clone(){
        return new Goalkepper(this);
    }

}
