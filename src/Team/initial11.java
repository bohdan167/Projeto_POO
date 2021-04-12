package Team;

import Players.*;
import java.util.Arrays;
import java.util.Comparator;

class overallComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p1.getOverall() - p2.getOverall();
    }
}

public class initial11 {
    private Goalkeeper gk;
    private Defender [] defender;
    private Sider [] sider;
    private Midfielder [] midfielder;
    private Striker [] striker;

    public Goalkeeper getGk() { return gk; }
    public void setGk(Goalkeeper gk) { this.gk = gk; }

    public Defender[] getDefense() { return defender; }
    public void setDefender(Defender[] defenders) { this.defender = defenders; }
    public void setDefender(Defender player, Defender substitute) {
        int i = 0;
        while(!getDefense()[i].equals(player)) i++;
        getDefense()[i] = substitute;
    }

    public Sider[] getSider() { return sider; }
    public void setSider(Sider[] sider) { this.sider = sider; }
    public void setSider(Sider player, Sider substitute){
        int i = 0;
        while(!getSider()[i].equals(player)) i++;
        getSider()[i] = substitute;
    }

    public Midfielder[] getMidfielder() { return midfielder; }
    public void setMidfielder(Midfielder[] midfielder) { this.midfielder = midfielder; }
    public void setMidfielder(Midfielder player, Midfielder substitute){
        int i = 0;
        while(!getMidfielder()[i].equals(player)) i++;
        getMidfielder()[i] = substitute;
    }

    public Striker[] getStriker() { return striker; }
    public void setStrikers(Striker[] strikers) { this.striker = strikers; }
    public void setStriker(Striker player, Striker substitute){
        int i = 0;
        while(!getStriker()[i].equals(player)) i++;
        getStriker()[i] = substitute;
    }


    public initial11(Goalkeeper gk, Defender[] defenders, Sider[] sider, Midfielder [] midfielders, Striker[] strikers){
        int dLENGTH = defenders.length;
        int siderLENGTH = sider.length;
        int midLENGTH = midfielders.length;
        int strLENGTH = strikers.length;
        if(dLENGTH>=1 && siderLENGTH>=1 && midLENGTH >=1 && strLENGTH>=1 && dLENGTH+siderLENGTH+midLENGTH+strLENGTH==10){
            this.gk = gk;
            this.defender = defenders;
            this.sider = sider;
            this.midfielder = midfielders;
            this.striker = strikers;
        }
    }

    public void subsINICIAL11(Object player,Object substitute){
        if(player instanceof Goalkeeper)
            setGk((Goalkeeper) player);
        if(player instanceof Defender)
           setDefender((Defender) player, (Defender) substitute);
        if(player instanceof Sider)
            setSider((Sider) player, (Sider) substitute);
        if(player instanceof Midfielder)
            setMidfielder((Midfielder) player, (Midfielder) substitute);
        if(player instanceof Striker)
            setStriker((Striker) player, (Striker) substitute);
    }

    public boolean findPLAYER(Player player){
        boolean ans = false;

        if (player instanceof Goalkeeper)
            ans = getGk().equals(player);
        else if (player instanceof Defender)
            for (int i = 0; i<getDefense().length && !(ans = getDefense()[i].equals(player)); i++);
        else if (player instanceof Sider)
            for (int i = 0; i<getSider().length && !(ans = getSider()[i].equals(player)); i++);
        else if (player instanceof Midfielder)
            for (int i = 0; i<getMidfielder().length && !(ans = getMidfielder()[i].equals(player)); i++);
        else if (player instanceof Striker)
            for (int i = 0; i<getStriker().length && !(ans = getStriker()[i].equals(player)); i++);

        return ans;
    }

    @Override
    public String toString() {
        int [] formation = new int[3];
        formation[0] = getDefense().length;
        formation[1] = getMidfielder().length;
        formation[2] = getStriker().length;

        int max = formation[0]+2;
        if (formation[1] > max) max = formation[1];
        if (formation[2] > max) max = formation[2];

        StringBuilder tabdef = new StringBuilder();
        StringBuilder tabmid = new StringBuilder();
        StringBuilder tabstr = new StringBuilder();
        for(int i = 0; i<max-2-formation[0]; i++) tabdef.append("\t");
        for(int i = 0; i<max-formation[1]; i++) tabmid.append("\t\t");
        for(int i = 0; i<max-formation[2]; i++) tabstr.append("\t\t");

        StringBuilder defenders = new StringBuilder();
        for(Defender d : getDefense()) defenders.append(String.format("%02d",d.getNumber())).append("-").append(d.getName()).append(" ".repeat(Math.max(0, 15 - d.getName().length())));

        StringBuilder midfielders = new StringBuilder();
        for(Midfielder m : getMidfielder()) midfielders.append(String.format("%02d",m.getNumber())).append("-").append(m.getName()).append(" ".repeat(Math.max(0, 15 - m.getName().length())));

        StringBuilder strikers = new StringBuilder();
        for(Striker s : getStriker()) strikers.append(String.format("%02d",s.getNumber())).append("-").append(s.getName()).append(" ".repeat(Math.max(0, 15 - s.getName().length())));

        return "\t\t\t\t\t\t\t" + String.format("%02d",getGk().getNumber())+"-"+getGk().getName() + "\n"
                + tabdef + String.format("%02d",getSider()[0].getNumber())+"-"+getSider()[0].getName()
                + " ".repeat(Math.max(0, 15 - getSider()[0].getName().length())) + "\t" + defenders
                + String.format("%02d",getSider()[1].getNumber())+"-"+getSider()[1].getName()
                + " ".repeat(Math.max(0, 15 - getSider()[1].getName().length())) + "\n" + tabmid + midfielders
                + "\n" + tabstr + strikers;
    }
}
