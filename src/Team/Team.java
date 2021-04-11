package Team;
import Players.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Team {
    String nameTEAM;
    Striker[] striker = new Striker[8];
    Midfielder[] midfielder = new Midfielder[9];
    Sider[] sider = new Sider[5];
    Defender[] defense = new Defender[7];
    Goalkeeper[] goalKeeper = new Goalkeeper[3];
    initial11 initial11;
    int overall;
    int[] formation = new int[3];

    public Team() {
        for (int i = 0; i < 3; i++) {
            this.goalKeeper[i] = new Goalkeeper();
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

        setNameTEAM("Unknown");
        setFormation(new int[]{4, 3, 3});
        setInitial11();
        setOverall(calculateOVERALL());
    }

    public Team(String nameTEAM, Goalkeeper[] goalKeeper, Defender[] defense, Sider[] sider,
                Midfielder[] midfielder, Striker[] striker, int[] formation, int overall, initial11 init11) {
        setNameTEAM(nameTEAM);
        setGoalKeeper(goalKeeper);
        setDefense(defense);
        setSider(sider);
        setMidfielder(midfielder);
        setStriker(striker);
        setFormation(formation);
        setOverall(overall);
        setInitial11(init11);

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
        setInitial11(t.getInitial11());
    }


    /*                                  Getters e Setters
     */
    public String getNameTEAM() { return nameTEAM; }
    public void setNameTEAM(String nameTEAM) { this.nameTEAM = nameTEAM; }

    public Goalkeeper[] getGoalKeeper() { return goalKeeper; }
    public void setGoalKeeper(Goalkeeper[] goalKeeper) { this.goalKeeper = goalKeeper; }

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
    public void setFormation(int[] formation) {
        if (formation.length == 3 && formation[0] + formation[1] + formation[2] == 10 && formation[0] != 0
                && formation[1] != 0 && formation[2] != 0)
            this.formation = formation;
    }

    public initial11 getInitial11() { return initial11; }
    public void setInitial11(initial11 initial11) { this.initial11 = initial11; }
    public boolean setInitial11(Player one,Player two){
        if (getInitial11().findPLAYER(one)) {
            if (one instanceof Goalkeeper)
                getInitial11().setGk((Goalkeeper) two);
            else if (one instanceof Defender)
                getInitial11().setDefender((Defender) one, (Defender) two);
            else if (one instanceof Sider)
                getInitial11().setSider((Sider) one, (Sider) two);
            else if (one instanceof Midfielder)
                getInitial11().setMidfielder((Midfielder) one, (Midfielder) two);
            else if (one instanceof Striker)
                getInitial11().setStriker((Striker) one, (Striker) two);
            return true;
        }
        return false;
    }
    public void setInitial11() {
        Goalkeeper gk = bestGOALKEEPER();
        Defender[] def = bestDEFENDER();
        Sider[] s = bestSIDER();
        Midfielder[] mid = bestMID();
        Striker[] str = bestSTRK();
        setInitial11(new initial11(gk, def, s, mid, str));
    }


    /*                                  Number
     * Funções relacionadas com os números dos jogadores, comparam os números
     * entre todos os jogadores e, se for necessário, altera-os.
     * */
    public boolean equalNUMBERS(int numberPLAYER) {
        for (Goalkeeper gk : getGoalKeeper())
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

    public boolean changeNUMBER(String namePLAYER, int numberPLAYER, int numberCHANGE) {
        Object ans = findPLAYER(namePLAYER, numberPLAYER);
        if (ans == null || !equalNUMBERS(numberCHANGE)) return false;

        if (ans instanceof Goalkeeper)
            ((Goalkeeper) ans).setNumber(numberCHANGE);

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
    public void removeGOALKEEPER(Goalkeeper g) {
        int length = getGoalKeeper().length;
        Goalkeeper[] gNEW = new Goalkeeper[length - 1];

        int k = 0;
        for (int i = 0; i < length; i++) {
            Goalkeeper gk = getGoalKeeper()[i];
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
        Player ans = findPLAYER(namePLAYER, numberPLAYER);

        if (ans == null) return false;

        if (ans instanceof Goalkeeper)
            removeGOALKEEPER((Goalkeeper) ans);

        if (ans instanceof Defender)
            removeDEFENSE((Defender) ans);

        if (ans instanceof Sider)
            removeSIDER((Sider) ans);

        if (ans instanceof Midfielder)
            removeMIDFIELDER((Midfielder) ans);

        if (ans instanceof Striker)
            removeSTRIKER((Striker) ans);

        ans.getHistory().add(getNameTEAM());

        return true;
    }


    /*                          FIND
     * -> Conjunto de funções que devolvem o jogador que pretendem encontrar
     * -> Caso não encontrem, devolvem null
     */
    public Goalkeeper findGOALKEEPER(String nameTEAM, int numberPLAYER) {
        int length = getGoalKeeper().length;
        for (int i = 0; i < length; i++) {
            Goalkeeper gk = getGoalKeeper()[i];
            if (gk.getName().equals(nameTEAM) && gk.getNumber() == numberPLAYER)
                return gk;
        }
        return null;
    }

    public Defender findDEFENDER(String nameTEAM, int numberPLAYER) {
        int length = getDefense().length;
        for (int i = 0; i < length; i++) {
            Defender d = getDefense()[i];
            if (d.getName().equals(nameTEAM) && d.getNumber() == numberPLAYER)
                return d;
        }
        return null;
    }

    public Sider findSIDER(String nameTEAM, int numberPLAYER) {
        int length = getSider().length;
        for (int i = 0; i < length; i++) {
            Sider s = getSider()[i];
            if (s.getName().equals(nameTEAM) && s.getNumber() == numberPLAYER)
                return s;
        }
        return null;
    }

    public Midfielder findMIDFIELDER(String nameTEAM, int numberPLAYER) {
        int length = getMidfielder().length;
        for (int i = 0; i < length; i++) {
            Midfielder m = getMidfielder()[i];
            if (m.getName().equals(nameTEAM) && m.getNumber() == numberPLAYER)
                return m;
        }
        return null;
    }

    public Striker findSTRIKER(String nameTEAM, int numberPLAYER) {
        int length = getStriker().length;
        for (int i = 0; i < length; i++) {
            Striker s = getStriker()[i];
            if (s.getName().equals(nameTEAM) && s.getNumber() == numberPLAYER)
                return s;
        }
        return null;
    }

    public Player findPLAYER(String namePLAYER, int numberPLAYER) {
        Goalkeeper gk = findGOALKEEPER(namePLAYER, numberPLAYER);
        if (gk != null) return gk;

        Defender defender = findDEFENDER(namePLAYER, numberPLAYER);
        if (defender != null) return defender;

        Sider sider = findSIDER(namePLAYER, numberPLAYER);
        if (sider != null) return sider;

        Midfielder midfielder = findMIDFIELDER(namePLAYER, numberPLAYER);
        if (midfielder != null) return midfielder;

        return findSTRIKER(namePLAYER, numberPLAYER);
    }

    public boolean findPlayer(Player player){
        boolean ans = false;

        if (player instanceof Goalkeeper) {
            for(int i = 0; i< getGoalKeeper().length && !(ans = getGoalKeeper()[i].equals(player));i++);
        }
        else if (player instanceof Defender){
            for (int i = 0; i<getDefense().length && !(ans = getDefense()[i].equals(player)); i++);
        }
        else if (player instanceof Sider){
            for (int i = 0; i<getSider().length && !(ans = getSider()[i].equals(player)); i++);
        }
        else if (player instanceof Midfielder){
            for (int i = 0; i<getMidfielder().length && !(ans = getMidfielder()[i].equals(player)); i++);
        }
        else if (player instanceof Striker){
            for (int i = 0; i<getStriker().length && !(ans = getStriker()[i].equals(player)); i++);
        }

        return ans;
    }


    /*                          ADD
     * -> Conjunto de funções que adicionam um jogador a uma equipa e removem na outra equipa
     * -> Tem em atenção se existe algum jogador com o mesmo número
     * */

    public void addGOALKEEPER(Goalkeeper gk) {
        int length = getGoalKeeper().length;
        Goalkeeper[] gNEW = new Goalkeeper[length + 1];

        int i = 0;
        for (; i < length; i++) gNEW[i] = getGoalKeeper()[i];

        Random rand = new Random();
        while (equalNUMBERS(gk.getNumber()))
            gk.setNumber((rand.nextInt(99)) + 1);

        gNEW[i] = gk;
        setGoalKeeper(gNEW);
    }

    public void addDEFENDER(Defender d) {
        int length = getDefense().length;
        Defender[] dNEW = new Defender[length + 1];

        int i = 0;
        for (; i < length; i++)
            dNEW[i] = getDefense()[i];

        Random rand = new Random();
        while (equalNUMBERS(d.getNumber()))
            d.setNumber((rand.nextInt(99)) + 1);

        dNEW[i] = d;
        setDefense(dNEW);
    }

    public void addSIDER(Sider s) {
        int length = getSider().length;
        Sider[] sNEW = new Sider[length + 1];

        int i = 0;
        for (; i < length; i++)
            sNEW[i] = getSider()[i];

        Random rand = new Random();
        while (equalNUMBERS(s.getNumber()))
            s.setNumber((rand.nextInt(99)) + 1);

        sNEW[i] = s;
        setSider(sNEW);
    }

    public void addMIDFIELDER(Midfielder m) {
        int length = getSider().length;
        Midfielder[] mNEW = new Midfielder[length + 1];

        int i = 0;
        for (; i < length; i++)
            mNEW[i] = getMidfielder()[i];

        Random rand = new Random();
        while (equalNUMBERS(m.getNumber()))
            m.setNumber((rand.nextInt(99)) + 1);

        mNEW[i] = m;
        setMidfielder(mNEW);
    }

    public void addSTRIKER(Striker s) {
        int length = getStriker().length;
        Striker[] sNEW = new Striker[length + 1];

        int i = 0;
        for (; i < length; i++)
            sNEW[i] = getStriker()[i];

        Random rand = new Random();
        while (equalNUMBERS(s.getNumber()))
            s.setNumber((rand.nextInt(99)) + 1);

        sNEW[i] = s;
        setStriker(sNEW);
    }

    public boolean addPLAYER(String namePLAYER, int numberPLAYER, @NotNull Team t) {
        Player player = t.findPLAYER(namePLAYER, numberPLAYER);
        if (player == null) return false;

        if (player instanceof Goalkeeper) {
            addGOALKEEPER((Goalkeeper) player);
            t.removeGOALKEEPER((Goalkeeper) player);
        }

        if (player instanceof Defender) {
            addDEFENDER((Defender) player);
            t.removeDEFENSE((Defender) player);
        }

        if (player instanceof Sider) {
            addSIDER((Sider) player);
            t.removeSIDER((Sider) player);
        }

        if (player instanceof Midfielder) {
            addMIDFIELDER((Midfielder) player);
            t.removeMIDFIELDER((Midfielder) player);
        }

        if (player instanceof Striker) {
            addSTRIKER((Striker) player);
            t.removeSTRIKER((Striker) player);
        }
        player.getHistory().add(t.getNameTEAM());
        return true;
    }

    public Goalkeeper bestGOALKEEPER() {
        Arrays.sort(getGoalKeeper(), new overallComparator());
        return getGoalKeeper()[0];
    }

    public Defender[] bestDEFENDER() {
        int length = getFormation()[0] - 2;
        Defender[] novo = new Defender[length];
        Arrays.sort(getDefense(), new overallComparator());
        for (int i = 0; i < length; i++)
            novo[i] = getDefense()[i];
        return novo;
    }

    public Sider[] bestSIDER() {
        int length = 2;
        Sider[] novo = new Sider[length];
        Arrays.sort(getSider(), new overallComparator());
        for (int i = 0; i < length; i++)
            novo[i] = getSider()[i];
        return novo;
    }

    public Midfielder[] bestMID() {
        int length = getFormation()[1];
        Midfielder[] novo = new Midfielder[length];
        Arrays.sort(getMidfielder(), new overallComparator());
        for (int i = 0; i < length; i++)
            novo[i] = getMidfielder()[i];
        return novo;
    }

    public Striker[] bestSTRK() {
        int length = getFormation()[2];
        Striker[] novo = new Striker[length];
        Arrays.sort(getStriker(), new overallComparator());
        for (int i = 0; i < length; i++)
            novo[i] = getStriker()[i];
        return novo;
    }

    public int sumOVERALL() {
        int result = 0;
        result += getInitial11().getGk().playerOverallValue();
        for (Defender d : getInitial11().getDefense())
            result += d.getOverall();

        for (Sider s : getInitial11().getSider())
            result += s.getOverall();

        for (Midfielder m : getInitial11().getMidfielder())
            result += m.getOverall();

        for (Striker s : getInitial11().getStriker())
            result += s.getOverall();

        return result;
    }

    public int overOVERALL(float sum) {
        int result = 0;
        result += getInitial11().getGk().playerOverallValue() > sum ? 1 : 0;
        for (Defender d : getInitial11().getDefense())
            result += d.getOverall() > sum ? 1 : 0;

        for (Sider s : getInitial11().getSider())
            result += s.getOverall() > sum ? 1 : 0;

        for (Midfielder m : getInitial11().getMidfielder())
            result += m.getOverall() > sum ? 1 : 0;

        for (Striker s : getInitial11().getStriker())
            result += s.getOverall() > sum ? 1 : 0;

        return result;
    }

    public int calculateOVERALL() {
        float sum = sumOVERALL();
        float result = sum / 11;
        result += overOVERALL(result);
        return (int) result;
    }



    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder(nameTEAM + "\n");
        ans.append("Position\t\t\tName\t    Number\t\tSpeed\t\tResistance\t\tDexterity\t\tImpulsion\tHead Game\tKick\tPass Capacity\tElasticity\tOverall");

        for(Goalkeeper g :getGoalKeeper()) ans.append(g.playerTOSTRING());
        ans.append("\n\n-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        for(Defender d : getDefense()) ans.append(d.playerTOSTRING());
        ans.append("\n\n-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        for(Sider s : getSider()) ans.append(s.playerTOSTRING());
        ans.append("\n\n-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        for(Midfielder m : getMidfielder()) ans.append(m.playerTOSTRING());
        ans.append("\n\n-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        for(Striker s : getStriker()) ans.append(s.playerTOSTRING());
        ans.append("\n\n-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        return ans.toString();
    }
}


/*                          FALTAS
* meter historial ao adicionar e remover um jogador.
* Calcular o overall da equipa ao alterar um jogador
* Adicionar funções que alteram cada música
* Ao remover jogador, verificar se naquela posição tem pelo menos x jogadores de acordo com a formação
* */

