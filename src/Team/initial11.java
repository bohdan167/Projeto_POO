package Team;

import Players.*;
import java.util.Arrays;
import java.util.Comparator;

/**
* Comparador de overall entre dois jogadores
* */
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

    /**
    * Getter do elemento Goalkeeper do 11 inicial
    * @return Elemento goalkeeper
    * */
    public Goalkeeper getGk() { return gk; }

    /**
     * Setter do elemento Goalkeeper do 11 inicial
     * @param gk Novo goalkeeper
     * */
    public void setGk(Goalkeeper gk) { this.gk = gk; }

    /**
     * Getter do array Defender do 11 inicial
     * @return array
     * */
    public Defender[] getDefense() { return defender; }

    /**
     * Setter do array Defender do 11 inicial
     * @param defenders Array de jogadores
     * */
    public void setDefender(Defender[] defenders) { this.defender = defenders; }

    /**
     * Substitui um defesa por outro
     * @param player Jogador a ser substituído
     * @param substitute Jogador que vai substituir
     * */
    public void setDefender(Defender player, Defender substitute) {
        int i = 0;
        while(!getDefense()[i].equals(player)) i++;
        getDefense()[i] = substitute;
    }

    /**
     * Getter do array Sider do 11 inicial
     * @return array
     * */
    public Sider[] getSider() { return sider; }

    /**
     * Setter do array Sider do 11 inicial
     * @param sider
     * Array de jogadores
     * */
    public void setSider(Sider[] sider) { this.sider = sider; }

    /**
     * Substitui um lateral por outro
     * @param player Jogador a ser substituído
     * @param substitute Jogador que vai substituir
     * */
    public void setSider(Sider player, Sider substitute){
        int i = 0;
        while(!getSider()[i].equals(player)) i++;
        getSider()[i] = substitute;
    }

    /**
     * Getter do array Midfielder do 11 inicial
     * @return array
     * */
    public Midfielder[] getMidfielder() { return midfielder; }

    /**
     * Setter do array Midfielder do 11 inicial
     * @param midfielder Array de jogadores
     * */
    public void setMidfielder(Midfielder[] midfielder) { this.midfielder = midfielder; }

    /**
     * Substitui um médio por outro
     * @param player Jogador a ser substituído
     * @param substitute Jogador que vai substituir
     * */
    public void setMidfielder(Midfielder player, Midfielder substitute){
        int i = 0;
        while(!getMidfielder()[i].equals(player)) i++;
        getMidfielder()[i] = substitute;
    }

    /**
     * Getter do array Striker do 11 inicial
     * @return array
     * */
    public Striker[] getStriker() { return striker; }

    /**
     * Setter do array Striker do 11 inicial
     * @param strikers Array de jogadores
     * */
    public void setStrikers(Striker[] strikers) { this.striker = strikers; }

    /**
     * Substitui um avançado por outro
     * @param player Jogador a ser substituído
     * @param substitute Jogador que vai substituir
     * */
    public void setStriker(Striker player, Striker substitute){
        int i = 0;
        while(!getStriker()[i].equals(player)) i++;
        getStriker()[i] = substitute;
    }

    /**
     * Construtor Parametrizado
     * @param gk Goalkeeper
     * @param defenders Array de Defenders
     * @param sider Array de Siders
     * @param midfielders Array de Midfielders
     * @param strikers Array de strikers
     * */
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

    /**
     * Substitui um jogador do 11 inicial
     * @param player Jogador do 11 inicial
     * @param substitute Jogador que vai entrar no 11 inicial
     * */
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

    /**
     * Encontra o jogador no 11 inicial
     * @param player Jogado para ser encontrado
     * @return True ou False
     * */
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

    /**
     * Imprime o 11 inicial
     * @return Uma string
     * */
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
