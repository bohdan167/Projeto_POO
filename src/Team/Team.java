package Team;

import Players.*;

import java.util.Arrays;
import java.util.Random;

public class Team {
    String nameTEAM;
    Striker[] striker = new Striker[8];
    Midfielder[] midfielder = new Midfielder[9];
    Sider[] sider = new Sider[5];
    Defender[] defense = new Defender[7];
    Goalkepper[] goalKeeper = new Goalkepper[3];
  //  Player [] initial11;
    int overall;
    int [] formation = new int[3];

    public Team() {
        setNameTEAM("Unknown");
        setFormation(new int[]{4, 3, 3});
        setOverall(0);

        for (int i = 0; i < 3; i++) {
            this.goalKeeper[i] = new Goalkepper();
            this.goalKeeper[i] = this.goalKeeper[i].generateNewPlayer();
        }

        for (int i = 0; i < 7; i++) {
            this.defense[i] = new Defender();
            this.defense[i] = this.defense[i].generateNewPlayer();
        }

        for (int i = 0; i < 5; i++) {
            this.sider[i] = new Sider();
            this.sider[i] = this.sider[i].generateNewPlayer();
        }

        for (int i = 0; i < 9; i++) {
            this.midfielder[i] = new Midfielder();
            this.midfielder[i] = this.midfielder[i].generateNewPlayer();
        }

        for (int i = 0; i < 8; i++) {
            this.striker[i] = new Striker();
            this.striker[i] = this.striker[i].generateNewPlayer();
        }
    }
/*
    public void setInitial11(Goalkepper gk, Defender def[]) {
        this.initial11 = new Player[11];
        this.initial11[0] = gk;
    }
*/
    public Team(String nameTEAM, Goalkepper[] goalKeeper, Defender[] defense, Sider[] sider,
                Midfielder[] midfielder, Striker[] striker, int[] formation, int overall) {
        setNameTEAM(nameTEAM);
        setGoalKeeper(goalKeeper);
        setDefense(defense);
        setSider(sider);
        setMidfielder(midfielder);
        setStriker(striker);
        setFormation(formation);
        //int overall = calculateOVERALL();
        setOverall(overall);
    }

    public Team(Team t) {
        setNameTEAM(t.getNameTEAM());
        setGoalKeeper(t.getGoalKeeper());
        setDefense(t.getDefense());
        setSider(t.getSider());
        setMidfielder(t.getMidfielder());
        setStriker(t.getStriker());
        setFormation(t.getFormation());
        setOverall(t.getOverall());
    }

    public String getNameTEAM() { return nameTEAM; }
    public void setNameTEAM(String nameTEAM) { this.nameTEAM = nameTEAM; }

    public Goalkepper[] getGoalKeeper() { return goalKeeper; }
    public void setGoalKeeper(Goalkepper[] goalKeeper) { this.goalKeeper = goalKeeper; }

    public Defender[] getDefense() { return defense; }
    public void setDefense(Defender[] defense) { this.defense = defense; }

    public Sider[] getSider() { return sider; }
    public void setSider(Sider[] sider) { this.sider = sider; }

    public Midfielder[] getMidfielder() { return midfielder; }
    public void setMidfielder(Midfielder[] midfielder) { this.midfielder = midfielder; }

    public Striker[] getStriker() { return striker; }
    public void setStriker(Striker[] striker) { this.striker = striker; }

    public int getOverall() { return overall; }
    public void setOverall(int overall) { this.overall = overall; }

    public int[] getFormation() { return formation; }
    public void setFormation(int[] formation) { this.formation = formation; }


    /*                                  Number
    * Funções relacionadas com os números dos jogadores
    * São importantes na criação das equipas
    * */
    public boolean equalNUMBERS(int numberPLAYER) {
        for (Goalkepper gk : getGoalKeeper())
            if (gk.getNumber() == numberPLAYER) return true;

        for (Defender defender : getDefense())
            if (defender.getNumber() == numberPLAYER) return true;

        for (Sider sider : getSider())
            if (sider.getNumber() == numberPLAYER) return true;

        for (Midfielder midfielder : getMidfielder())
            if (midfielder.getNumber() == numberPLAYER) return true;

        for (Striker striker : getStriker())
            if (striker.getNumber() == numberPLAYER) return true;

        return false;
    }

    public boolean changeNUMBER(String namePLAYER, int numberPLAYER, int numberCHANGE){
        Object ans = findPLAYER(namePLAYER,numberPLAYER);
        if (ans == null || !equalNUMBERS(numberCHANGE)) return false;

        if (ans instanceof Goalkepper)
            ((Goalkepper) ans).setNumber(numberCHANGE);

        if (ans instanceof Defender)
            ((Defender) ans).setNumber(numberCHANGE);

        if (ans instanceof Sider)
            ((Sider) ans).setNumber(numberCHANGE);

        if (ans instanceof Midfielder)
            ((Midfielder) ans).setNumber(numberCHANGE);

        if (ans instanceof Striker)
            ((Striker) ans).setNumber(numberCHANGE);

        return true;
    }


    /*                          REMOVE
    * -> Conjunto de funções que removem um jogador de uma equipa
    * -> Ou encontram esse jogador, é removido e devolvem true
    * -> ou devolvem false
    * */
    public void removeGOALKEEPER(Goalkepper g) {
        int length = getGoalKeeper().length;
        Goalkepper[] gNEW = new Goalkepper[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            Goalkepper gk = getGoalKeeper()[i];
            if (!(gk.equals(g)))
                gNEW[k++] = gk;
        }

        setGoalKeeper(gNEW);
    }

    public void removeDEFENSE(Defender d) {
        int length = getDefense().length;
        Defender[] dNEW = new Defender[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            Defender defender = getDefense()[i];
            if (!(defender.equals(d)))
                dNEW[k++] = defender;
        }

        setDefense(dNEW);
    }

    public void removeSIDER(Sider s) {
        int length = getSider().length;
        Sider[] sNEW = new Sider[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            Sider sider = getSider()[i];
            if (!(sider.equals(s)))
                sNEW[k++] = sider;
        }

        setSider(sNEW);
    }

    public void removeMIDFIELDER(Midfielder m) {
        int length = getMidfielder().length;
        Midfielder[] mNEW = new Midfielder[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            Midfielder midfielder = getMidfielder()[i];
            if (!(midfielder.equals(m)))
                mNEW[k++] = midfielder;
        }

        setMidfielder(mNEW);
    }

    public void removeSTRIKER(Striker s) {
        int length = getStriker().length;
        Striker[] sNEW = new Striker[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            Striker striker = getStriker()[i];
            if (!(striker.equals(s)))
                sNEW[k++] = striker;
        }

        setStriker(sNEW);
    }

    public boolean removePLAYER(String namePLAYER, int numberPLAYER) {
        Object ans = findPLAYER(namePLAYER,numberPLAYER);
        if (ans == null) return false;

        if (ans instanceof Goalkepper)
            removeGOALKEEPER((Goalkepper) ans);

        if (ans instanceof Defender)
            removeDEFENSE((Defender) ans);

        if (ans instanceof Sider)
            removeSIDER((Sider) ans);

        if (ans instanceof Midfielder)
            removeMIDFIELDER((Midfielder) ans);

        if (ans instanceof Striker)
            removeSTRIKER((Striker) ans);

        return true;

    }


    /*                          FIND
    * -> Conjunto de funções que devolvem o jogador que pretendem encontrar
    * -> Caso não encontrem, devolvem null
    */
    public Goalkepper findGOALKEEPER(String nameTEAM, int numberPLAYER){
        int length = getGoalKeeper().length;
        for(int i = 0; i < length; i++){
            Goalkepper gk = getGoalKeeper()[i];
            if (gk.getName().equals(nameTEAM) && gk.getNumber() == numberPLAYER)
                return gk;
        }
        return null;
    }

    public Defender findDEFENDER(String nameTEAM, int numberPLAYER){
        int length = getDefense().length;
        for(int i = 0; i<length; i++){
            Defender d = getDefense()[i];
            if (d.getName().equals(nameTEAM) && d.getNumber() == numberPLAYER)
                return d;
        }
        return null;
    }

    public Sider findSIDER(String nameTEAM, int numberPLAYER){
        int length = getSider().length;
        for(int i = 0; i<length; i++){
            Sider s = getSider()[i];
            if (s.getName().equals(nameTEAM) && s.getNumber() == numberPLAYER)
                return s;
        }
        return null;
    }

    public Midfielder findMIDFIELDER(String nameTEAM, int numberPLAYER){
        int length = getMidfielder().length;
        for(int i = 0; i<length; i++){
            Midfielder m = getMidfielder()[i];
            if (m.getName().equals(nameTEAM) && m.getNumber() == numberPLAYER)
                return m;
        }
        return null;
    }

    public Striker findSTRIKER(String nameTEAM, int numberPLAYER){
        int length = getStriker().length;
        for(int i = 0; i< length; i++){
            Striker s = getStriker()[i];
            if (s.getName().equals(nameTEAM) && s.getNumber() == numberPLAYER)
                return s;
        }
        return null;
    }

    public Object findPLAYER(String namePLAYER, int numberPLAYER){
        Goalkepper gk = findGOALKEEPER(namePLAYER,numberPLAYER);
        if (gk != null) return gk;

        Defender defender = findDEFENDER(namePLAYER,numberPLAYER);
        if (defender != null) return defender;

        Sider sider = findSIDER(namePLAYER,numberPLAYER);
        if (sider != null) return sider;

        Midfielder midfielder = findMIDFIELDER(namePLAYER,numberPLAYER);
        if (midfielder != null) return midfielder;

        return findSTRIKER(namePLAYER, numberPLAYER);
    }


    /*                          ADD
    * -> Conjunto de funções que adicionam um jogador a uma equipa e removem na outra equipa
    * -> Tem em atenção se existe algum jogador com o mesmo número
    * */

    public void addGOALKEEPER(Goalkepper gk){
        int length = getGoalKeeper().length;
        Goalkepper [] gNEW = new Goalkepper[length+1];

        int i = 0;
        for(;i<length;i++)
            gNEW[i]= getGoalKeeper()[i];

        Random rand = new Random();
        while (equalNUMBERS(gk.getNumber()))
            gk.setNumber((rand.nextInt(99))+1);

        gNEW[i] = gk;
        setGoalKeeper(gNEW);
    }

    public void addDEFENDER(Defender d){
        int length = getDefense().length;
        Defender [] dNEW = new Defender[length+1];

        int i = 0;
        for(;i<length;i++)
            dNEW[i] = getDefense()[i];

        Random rand = new Random();
        while(equalNUMBERS(d.getNumber()))
            d.setNumber((rand.nextInt(99))+1);

        dNEW[i] = d;
        setDefense(dNEW);
    }

    public void addSIDER(Sider s){
        int length = getSider().length;
        Sider [] sNEW = new Sider[length+1];

        int i = 0;
        for(;i<length;i++)
            sNEW[i] = getSider()[i];

        Random rand = new Random();
        while(equalNUMBERS(s.getNumber()))
            s.setNumber((rand.nextInt(99))+1);

        sNEW[i] = s;
        setSider(sNEW);
    }

    public void addMIDFIELDER(Midfielder m){
        int length = getSider().length;
        Midfielder [] mNEW = new Midfielder[length+1];

        int i = 0;
        for(;i<length;i++)
            mNEW[i] = getMidfielder()[i];

        Random rand = new Random();
        while(equalNUMBERS(m.getNumber()))
            m.setNumber((rand.nextInt(99))+1);

        mNEW[i] = m;
        setMidfielder(mNEW);
    }

    public void addSTRIKER(Striker s){
        int length = getStriker().length;
        Striker [] sNEW = new Striker[length+1];

        int i = 0;
        for(;i<length;i++)
            sNEW[i] = getStriker()[i];

        Random rand = new Random();
        while(equalNUMBERS(s.getNumber()))
            s.setNumber((rand.nextInt(99))+1);

        sNEW[i] = s;
        setStriker(sNEW);
    }

    public boolean addPLAYER(String namePLAYER, int numberPLAYER, Team t){
        Object player = t.findPLAYER(namePLAYER,numberPLAYER);
        if (player == null) return false;

        if (player instanceof Goalkepper){
            addGOALKEEPER((Goalkepper) player);
            t.removeGOALKEEPER((Goalkepper) player);
        }

        if (player instanceof Defender){
            addDEFENDER((Defender) player);
            t.removeDEFENSE((Defender) player);
        }

        if (player instanceof Sider){
            addSIDER((Sider) player);
            t.removeSIDER((Sider) player);
        }

        if (player instanceof Midfielder){
            addMIDFIELDER((Midfielder) player);
            t.removeMIDFIELDER((Midfielder) player);
        }

        if (player instanceof Striker){
            addSTRIKER((Striker) player);
            t.removeSTRIKER((Striker) player);
        }
        return true;
    }

    public int calculateOVERALL(){
        int result = 0;

        return result;
    }

    @Override
    public String toString() {
        return "Team{" +
                "nameTEAM='" + nameTEAM + '\'' +
                ", striker=" + Arrays.toString(striker) +
                ", midfielder=" + Arrays.toString(midfielder) +
                ", sider=" + Arrays.toString(sider) +
                ", defense=" + Arrays.toString(defense) +
                ", goalKeeper=" + Arrays.toString(goalKeeper) +
                ", overall=" + overall +
                ", formation=" + Arrays.toString(formation) +
                '}';
    }


}

