package Team;

import Players.*;

import java.util.Arrays;

public class Team {
    String nameTEAM;
    Striker[] striker;
    Midfielder[] midfielder;
    Sider[] sider;
    Defender[] defense;
    Goalkepper[] goalKeeper;

    public Team() {
        setNameTEAM("Unknown");

        this.goalKeeper = new Goalkepper[3];
        for (int i = 0; i < 3; i++) this.goalKeeper[i].generateNewPlayer();

        this.defense = new Defender[7];
        for (int i = 0; i < 7; i++) this.defense[i].generateNewPlayer();

        this.sider = new Sider[5];
        for (int i = 0; i < 5; i++) this.sider[i].generateNewPlayer();

        this.midfielder = new Midfielder[9];
        for (int i = 0; i < 9; i++) this.midfielder[i].generateNewPlayer();

        this.striker = new Striker[8];
        for (int i = 0; i < 8; i++) this.striker[i].generateNewPlayer();
    }

    public Team(String nameTEAM, Goalkepper[] goalKeeper, Defender[] defense, Sider[] sider,
                Midfielder[] midfielder, Striker[] striker) {
        setNameTEAM(nameTEAM);
        setGoalKeeper(goalKeeper);
        setDefense(defense);
        setSider(sider);
        setMidfielder(midfielder);
        setStriker(striker);
    }

    public Team(Team t) {
        setNameTEAM(t.getNameTEAM());
        setGoalKeeper(t.getGoalKeeper());
        setDefense(t.getDefense());
        setSider(t.getSider());
        setMidfielder(t.getMidfielder());
        setStriker(t.getStriker());
    }

    public String getNameTEAM() {
        return nameTEAM;
    }

    public void setNameTEAM(String nameTEAM) {
        this.nameTEAM = nameTEAM;
    }

    public Goalkepper[] getGoalKeeper() {
        return goalKeeper;
    }

    public void setGoalKeeper(Goalkepper[] goalKeeper) {
        this.goalKeeper = goalKeeper;
    }

    public Defender[] getDefense() {
        return defense;
    }

    public void setDefense(Defender[] defense) {
        this.defense = defense;
    }

    public Sider[] getSider() {
        return sider;
    }

    public void setSider(Sider[] sider) {
        this.sider = sider;
    }

    public Midfielder[] getMidfielder() {
        return midfielder;
    }

    public void setMidfielder(Midfielder[] midfielder) {
        this.midfielder = midfielder;
    }

    public Striker[] getStriker() {
        return striker;
    }

    public void setStriker(Striker[] striker) {
        this.striker = striker;
    }


    public void removeGOALKEEPER(String namePLAYER, int numberPLAYER) {
        int length = getGoalKeeper().length;
        Goalkepper[] g = new Goalkepper[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            if (!(getGoalKeeper()[i].getName().equals(namePLAYER) && getGoalKeeper()[i].getNumber() == numberPLAYER))
            g[k++] = getGoalKeeper()[i];
        }

        setGoalKeeper(g);
    }

    public void removeDEFENSE(String namePLAYER, int numberPLAYER) {
        int length = getDefense().length;
        Defender[] d = new Defender[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            if (!(getDefense()[i].getName().equals(namePLAYER) && getDefense()[i].getNumber() == numberPLAYER))
                d[k++] = getDefense()[i];
        }

        setDefense(d);
    }

    public void removeSIDER(String namePLAYER, int numberPLAYER) {
        int length = getSider().length;
        Sider[] s = new Sider[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            if (!(getSider()[i].getName().equals(namePLAYER) && getSider()[i].getNumber() == numberPLAYER))
                s[k++] = getSider()[i];
        }

        setSider(s);
    }

    public void removeMIDFIELDER(String namePLAYER, int numberPLAYER) {
        int length = getMidfielder().length;
        Midfielder[] m = new Midfielder[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            if (!(getMidfielder()[i].getName().equals(namePLAYER) && getMidfielder()[i].getNumber() == numberPLAYER))
                m[k++] = getMidfielder()[i];
        }

        setMidfielder(m);
    }

    public void removeSTRIKER(String namePLAYER, int numberPLAYER) {
        int length = getStriker().length;
        Striker[] s = new Striker[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            if (!(getStriker()[i].getName().equals(namePLAYER) && getStriker()[i].getNumber() == numberPLAYER))
                s[k++] = getStriker()[i];
        }

        setStriker(s);
    }

    public boolean removePLAYER(String namePLAYER, int numberPLAYER) {
        String ans = findPLAYER(namePLAYER,numberPLAYER);
        if (ans.equals("GOALKEEPER")) {
            removeGOALKEEPER(namePLAYER,numberPLAYER);
            return true;
        }
        if (ans.equals("DEFENDER")){
            removeDEFENSE(namePLAYER,numberPLAYER);
            return true;
        }
        if (ans.equals("SIDER")){
            removeSIDER(namePLAYER,numberPLAYER);
            return true;
        }
        if (ans.equals("MIDFIELDER")){
            removeMIDFIELDER(namePLAYER,numberPLAYER);
            return true;
        }
        if (ans.equals("STRIKER")){
            removeSTRIKER(namePLAYER,numberPLAYER);
            return true;
        }
        return false;
    }

    public boolean findGOALKEEPER(String nameTEAM, int numberPLAYER){
        for(Goalkepper gk : getGoalKeeper()){
            if (gk.getName().equals(nameTEAM) && gk.getNumber() == numberPLAYER)
                return true;
        }
        return false;
    }

    public boolean findDEFENDER(String nameTEAM, int numberPLAYER){
        for(Defender d : getDefense()){
            if (d.getName().equals(nameTEAM) && d.getNumber() == numberPLAYER)
                return true;
        }
        return false;
    }

    public boolean findSIDER(String nameTEAM, int numberPLAYER){
        for(Sider s : getSider()){
            if (s.getName().equals(nameTEAM) && s.getNumber() == numberPLAYER)
                return true;
        }
        return false;
    }

    public boolean findMIDFIELDER(String nameTEAM, int numberPLAYER){
        for(Midfielder m : getMidfielder()){
            if (m.getName().equals(nameTEAM) && m.getNumber() == numberPLAYER)
                return true;
        }
        return false;
    }

    public boolean findSTRIKER(String nameTEAM, int numberPLAYER){
        for(Striker s : getStriker()){
            if (s.getName().equals(nameTEAM) && s.getNumber() == numberPLAYER)
                return true;
        }
        return false;
    }

    public String findPLAYER(String namePLAYER, int numberPLAYER){
        if (findGOALKEEPER(namePLAYER,numberPLAYER)) return "GOALKEEPER";
        if (findDEFENDER(namePLAYER,numberPLAYER)) return "DEFENDER";
        if (findSIDER(namePLAYER,numberPLAYER)) return "SIDER";
        if (findMIDFIELDER(namePLAYER,numberPLAYER)) return "MIDFIELDER";
        if (findSTRIKER(namePLAYER,numberPLAYER)) return "STRIKER";
        return "NULL";
    }
/*
    public int overall{
        return
    }*/

/*
    public void addPLAYER(String namePLAYER, int numberPLAYER,Team t){
        t.
    }
*/
    @Override
    public String toString() {
        return "Team{" +
                "nameTEAM='" + nameTEAM + '\'' +
                ", striker=" + Arrays.toString(striker) +
                ", midfielder=" + Arrays.toString(midfielder) +
                ", sider=" + Arrays.toString(sider) +
                ", defense=" + Arrays.toString(defense) +
                ", goalKeeper=" + Arrays.toString(goalKeeper) +
                '}';
    }


}

//Gerar um número para o jogador (posiçoes)
//Alterar o número de um jogador (equipa)


/*
* -> Adicionar o jogador e verificar se o número do jogador entra em conflito com outro jogador.
* -> Gerar um onze inicial
* -> Dúvida de criar um array ou uma classe
* -> Média do overall da equipa (https://fifauteam.com/fifa-21-squad-rating-guide/)
* -> Gerar um 11 inicial de acordo com os melhores na posição
* */