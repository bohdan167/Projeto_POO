package Players;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Goalkepper extends Player{
    private int elasticity; //elastecidade

    public Goalkepper(){ super(); this.elasticity=0;}

    public Goalkepper(String name, int number,int speed, int resistance, int dexterity, int discharge,
                      int headerShoot, int shoot, int pass,int elasticity, int overall,List<String> history) {
        super(name,number,speed,resistance,dexterity,discharge,headerShoot,shoot,pass, overall,history);
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
                0.1*getResistance() + 0.14*getDexterity() + 0.08*getSpeed() + 0.04*getKick() + 0.04*getHeadGame());
    }

    @Override
    public Goalkepper generateNewPlayer() {
        Goalkepper novo = new Goalkepper();
        novo.setName(novo.namesOfPlayers[ThreadLocalRandom.current().nextInt(0,19)]);
        novo.setNumber(ThreadLocalRandom.current().nextInt(1,99));
        novo.setSpeed(ThreadLocalRandom.current().nextInt(20,60));
        novo.setResistance(ThreadLocalRandom.current().nextInt(55,100));
        novo.setDexterity(ThreadLocalRandom.current().nextInt(30,100));
        novo.setImpulsion(ThreadLocalRandom.current().nextInt(55,100));
        novo.setHeadGame(ThreadLocalRandom.current().nextInt(10,50));
        novo.setKick(ThreadLocalRandom.current().nextInt(10,50));
        novo.setPassCapacity(ThreadLocalRandom.current().nextInt(60,100));
        novo.setElasticity(ThreadLocalRandom.current().nextInt(70,100));
        novo.setOverall(novo.playerOverallValue());
        return novo;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return  "Goalkepper " + super.toString() +
                "\t\t[Elasticity]-----" + this.elasticity;
    }

    public Goalkepper clone(){
        return new Goalkepper(this);
    }

}
