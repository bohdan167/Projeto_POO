package FM.Main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Team {
    private String nameTEAM;
    private ArrayList<Player> initial11;
    private ArrayList<Player> substitutes;
    private ArrayList<Player> team;
    private int[] formation;
    private int overall;


    /**
     * Construtor nulo
     */
    public Team() {
        team = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Goalkeeper gk = new Goalkeeper().generateNewPlayer();
            team.add(gk);
        }

        for (int i = 0; i < 7; i++) {
            Defender defender = new Defender().generateNewPlayer();
            team.add(defender);
        }

        for (int i = 0; i < 5; i++) {
            Sider sider = new Sider().generateNewPlayer();
            team.add(sider);
        }

        for (int i = 0; i < 9; i++) {
            Midfielder midfielder = new Midfielder().generateNewPlayer();
            team.add(midfielder);
        }

        for (int i = 0; i < 8; i++) {
            Striker striker = new Striker().generateNewPlayer();
            team.add(striker);
        }
        setFormation(new int[]{4, 3, 3});

        team.sort(PlayerComparator);
        initial11 = new ArrayList<>();
        bestINITIAL11();
        substitutes = new ArrayList<>();
        setSubstitutes();
        substitutes.sort(PlayerComparator);

        setNameTEAM(namesOfTeams[ThreadLocalRandom.current().nextInt(0, 17)]);
        setOverall(calculateOVERALL());
    }

    /**
     * COMENTÁRIOS ERRADOS
     * Construtor parametrizado
     *
     * @param nameTEAM  Nome da equipa
     * @param formation Formação tática
     * @param overall   Overall da equipa
     */
    public Team(String nameTEAM, ArrayList<Player> initial11, ArrayList<Player> substitutes, ArrayList<Player> team, int[] formation, int overall) {
        setNameTEAM(nameTEAM);
        setTeam(team);
        setInitial11(initial11);
        setSubstitutes(substitutes);
        setFormation(formation);
        setOverall(overall);
    }

    /**
     * Construtor Parametrizado
     *
     * @param t Equipa
     */
    public Team(Team t) {
        setNameTEAM(t.getNameTEAM());
        setTeam(t.getTeam());
        setInitial11(t.getInitial11());
        setSubstitutes(t.getSubstitutes());
        setFormation(t.getFormation());
        setInitial11(t.getInitial11());
        setOverall(t.getOverall());
    }

    /**
     * Getter do nome da equipa
     *
     * @return Nome da equipa
     */
    public String getNameTEAM() {
        return nameTEAM;
    }

    public void setNameTEAM(String nameTEAM) {
        this.nameTEAM = nameTEAM;
    }

    public ArrayList<Player> getInitial11() {
        return initial11;
    }

    public void setInitial11(ArrayList<Player> initial11) {
        this.initial11 = initial11;
    }

    public ArrayList<Player> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(ArrayList<Player> substitutes) {
        this.substitutes = substitutes;
    }

    public void setSubstitutes() {
        substitutes.addAll(team.stream().filter(player -> !(initial11.contains(player))).collect(Collectors.toList()));
    }

    public ArrayList<Player> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Player> team) {
        this.team = team;
    }


    /**
     * Getter do overall
     *
     * @return Overall da equipa
     */
    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    /**
     * Getter da formação tática
     *
     * @return Formação tática
     */
    public int[] getFormation() {
        return formation;
    }

    public void setFormation(int[] formation) {
        if (formation.length == 3 && formation[0] + formation[1] + formation[2] == 10 && formation[0] != 0
                && formation[1] != 0 && formation[2] != 0)
            this.formation = formation;
    }


    public boolean substitution(Player one, Player two) {
        int i = 0;
        int j = 0;
        for (; !(initial11.get(i).equals(one)); i++) ;
        for (; !substitutes.get(j).equals(two); i++) ;

        if (initial11.get(i).equals(one) && substitutes.get(j).equals(two)) {
            initial11.add(i, two);
            initial11.add(j, one);
            return true;
        } else return false;
    }

    /*                                  Number
     * Funções relacionadas com os números dos jogadores, comparam os números
     * entre todos os jogadores e, se for necessário, altera-os.
     */
    public boolean equalNUMBERS(int numberPLAYER) {
        for (Player p : getTeam())
            if (p.getNumber() == numberPLAYER) return true;
        return false;
    }

    public boolean changeNUMBER(String namePLAYER, int numberPLAYER, int numberCHANGE) {
        Player ans = findPLAYER(namePLAYER, numberPLAYER);
        if (ans == null || !equalNUMBERS(numberCHANGE)) return false;
        ans.setNumber(numberCHANGE);
        return true;
    }


    /*                          REMOVE
     * -> Conjunto de funções que removem um jogador de uma equipa
     * -> Ou encontram esse jogador, é removido e devolvem true
     * -> ou devolvem false
     * */
    public boolean removePLAYER(String namePLAYER, int numberPLAYER) {
        Player ans = findPLAYER(namePLAYER, numberPLAYER);

        if (ans == null) return false;

        if (ans instanceof Goalkeeper) {
            if (team.stream().filter(player -> player instanceof Goalkeeper).count() == 2)
                return false;
        } else if (ans instanceof Defender) {
            if (team.stream().filter(player -> player instanceof Defender).count() == getFormation()[0])
                return false;
        } else if (ans instanceof Sider) {
            if (team.stream().filter(player -> player instanceof Sider).count() == 4)
                return false;
        } else if (ans instanceof Midfielder) {
            if (team.stream().filter(player -> player instanceof Midfielder).count() == getFormation()[1] + 2)
                return false;
        } else if (ans instanceof Striker) {
            if (team.stream().filter(player -> player instanceof Striker).count() == getFormation()[2] + 2)
                return false;
        }

        ans.getHistory().add(getNameTEAM());
        team.remove(ans);

        team.sort(PlayerComparator);
        fillINITIAL11();

        return true;
    }


    /*                          FIND
     * -> Conjunto de funções que devolvem o jogador que pretendem encontrar
     * -> Caso não encontrem, devolvem null
     */
    public Player findPLAYER(String namePLAYER, int numberPLAYER) {
        return team.stream().filter(player -> (player.getName().equals(namePLAYER) && player.getNumber() == numberPLAYER)).findFirst().get();
    }

    public boolean findPlayer(Player player) {
        return team.stream().anyMatch(p -> (p.equals(player)));
    }


    /*                          ADD
     * -> Conjunto de funções que adicionam um jogador a uma equipa e removem na outra equipa
     * -> Tem em atenção se existe algum jogador com o mesmo número
     * */
    public boolean addPLAYER(String namePLAYER, int numberPLAYER, Team t) {
        Player player = t.findPLAYER(namePLAYER, numberPLAYER);
        if (player == null) return false;
        player.getHistory().add(t.getNameTEAM());
        team.add(player);
        team.sort(PlayerComparator);
        return true;
    }


    public Goalkeeper bestGOALKEEPER() {
        return (Goalkeeper) team.get(0);
    }

    public ArrayList<Defender> bestDEFENDER(int howMANY) {
        int i = 0;
        ArrayList<Defender> novo = new ArrayList<>();
        for (; i < team.size() && !(team.get(i) instanceof Defender); i++) ;
        for (int j = 0; i < team.size() && (team.get(i) instanceof Defender) && j < howMANY; i++, j++)
            novo.add((Defender) getTeam().get(i));
        return novo;
    }

    public ArrayList<Sider> bestSIDER(int howMANY) {
        int i = 0;
        ArrayList<Sider> novo = new ArrayList<>();
        for (; i < team.size() && !(team.get(i) instanceof Sider); i++) ;
        for (int j = 0; i < team.size() && (team.get(i) instanceof Sider) && j < howMANY; i++, j++)
            novo.add((Sider) getTeam().get(i));
        return novo;
    }

    public ArrayList<Midfielder> bestMID(int howMANY) {
        int i = 0;
        ArrayList<Midfielder> novo = new ArrayList<>();
        for (; i < team.size() && !(team.get(i) instanceof Midfielder); i++) ;
        for (int j = 0; i < team.size() && (team.get(i) instanceof Midfielder) && j < howMANY; i++, j++)
            novo.add((Midfielder) getTeam().get(i));
        return novo;
    }

    public ArrayList<Striker> bestSTRK(int howMANY) {
        int i = 0;
        ArrayList<Striker> novo = new ArrayList<>();
        for (; i < team.size() && !(team.get(i) instanceof Striker); i++) ;
        for (int j = 0; i < team.size() && (team.get(i) instanceof Striker) && j < howMANY; i++, j++)
            novo.add((Striker) getTeam().get(i));
        return novo;
    }

    public void bestINITIAL11() {
        if (initial11.size() != 0)
            initial11.clear();
        initial11.add(bestGOALKEEPER());
        initial11.addAll(bestDEFENDER(formation[0] - 2));
        initial11.addAll(bestSIDER(2));
        initial11.addAll(bestMID(formation[1]));
        initial11.addAll(bestSTRK(formation[2]));
    }

    public void fillINITIAL11() {
        if (initial11.size() != 11) {
            int gk = 0;
            int defender = 0;
            int sider = 0;
            int midfielder = 0;
            int striker = 0;
            for (Player player : initial11) {
                if (player instanceof Goalkeeper) gk++;
                else if (player instanceof Defender) defender++;
                else if (player instanceof Sider) sider++;
                else if (player instanceof Midfielder) midfielder++;
                else if (player instanceof Striker) striker++;
            }
            if (gk < 1) {
                initial11.add(bestGOALKEEPER());
                substitutes.remove(bestGOALKEEPER());
            }
            if (defender < formation[0] - 2) {
                initial11.addAll(bestDEFENDER(formation[0] - 2 - defender));
                substitutes.removeAll(bestDEFENDER(formation[0] - 2 - defender));
            }
            if (sider < 2) {
                initial11.addAll(bestSIDER(2 - sider));
                substitutes.removeAll(bestSIDER(2 - sider));
            }
            if (midfielder < formation[1]) {
                initial11.addAll(bestMID(formation[1] - midfielder));
                substitutes.removeAll(bestMID(formation[1] - midfielder));
            }
            if (striker < formation[2]) {
                initial11.addAll(bestSTRK(formation[2] - striker));
                substitutes.removeAll(bestSTRK(formation[2] - striker));
            }
        }
    }

    public int sumOVERALL() {
        int result = 0;
        for (int i = 0; i < 11; i++)
            result += initial11.get(i).getOverall();
        return result;
    }

    public int overOVERALL(float sum) {
        int result = 0;
        for (int i = 0; i < 11; i++)
            result += initial11.get(i).getOverall() > sum ? 1 : 0;
        return result;
    }

    public int calculateOVERALL() {
        float sum = sumOVERALL();
        float result = sum / 11;
        result += overOVERALL(result);
        return (int) result;
    }


    public String initial11toString() {
        int max = formation[0]+2;
        if (formation[1] > max) max = formation[1];
        if (formation[2] > max) max = formation[2];

        StringBuilder tabdef = new StringBuilder();
        StringBuilder tabmid = new StringBuilder();
        StringBuilder tabstr = new StringBuilder();
        for(int i = 0; i<max-2-formation[0]; i++) tabdef.append("\t");
        for(int i = 0; i<max-formation[1]; i++) tabmid.append("\t");
        for(int i = 0; i<max-formation[2]; i++) tabstr.append("\t");

        StringBuilder defenders = new StringBuilder();
        StringBuilder midfielders = new StringBuilder();
        StringBuilder strikers = new StringBuilder();

        for(int i = 0; i< initial11.size(); i++){
            if (initial11.get(i) instanceof Defender)
                defenders.append(String.format("%02d",initial11.get(i).getNumber())).append("-").append(initial11.get(i).getName()).append(" ".repeat(Math.max(0, 15 - initial11.get(i).getName().length())));
            else if (initial11.get(i) instanceof Midfielder)
                midfielders.append(String.format("%02d",initial11.get(i).getNumber())).append("-").append(initial11.get(i).getName()).append(" ".repeat(Math.max(0, 15 - initial11.get(i).getName().length())));
            else if (initial11.get(i) instanceof Striker)
                strikers.append(String.format("%02d",initial11.get(i).getNumber())).append("-").append(initial11.get(i).getName()).append(" ".repeat(Math.max(0, 15 - initial11.get(i).getName().length())));
        }

        return "\t\t\t\t\t\t\t" + String.format("%02d",initial11.get(0).getNumber())+"-"+initial11.get(0).getName() + "\n"
                + tabdef + String.format("%02d",initial11.get(formation[0]).getNumber())+"-"+initial11.get(formation[0]).getName()
                + " ".repeat(Math.max(0, 15 - initial11.get(formation[0]).getName().length())) + "\t" + defenders
                + String.format("%02d",initial11.get(formation[0]+1).getNumber())+"-"+initial11.get(formation[0]+1).getName()
                + " ".repeat(Math.max(0, 15 - initial11.get(formation[0]+1).getName().length())) + "\n" + tabmid + midfielders
                + "\n" + tabstr + strikers;
    }

    public String substitutesTOSTRING() {
        StringBuilder ans = new StringBuilder();
        ans.append("Position\t\t\tName\t\tNumber\n");
        for (Player substitute : substitutes) {
            ans.append(substitute.getClass().getSimpleName()).append(" ".repeat(Math.max(0, 20 - substitute.getClass().getSimpleName().length()))).append(substitute.getName()).append(" ".repeat(Math.max(0, 12 - substitute.getName().length()))).append(String.format("%02d", substitute.getNumber())).append("\n");
        }
        return ans.toString();
    }


    public String goalkeepersTOSTRING() {
        int i = 0;

        for (; i < team.size() && !(team.get(i) instanceof Goalkeeper); i++) ;
        StringBuilder ans = new StringBuilder("Position\t\t\tName\t    Number\tSprint\tSpeed\tStrength\tAgression\tResistance\tDexterity\tImpulsion\tHead Game\tKick\tPass Capacity\tElasticity\tHandling\tReflexes\tDiving\tOverall");
        for (; i < team.size() && (team.get(i) instanceof Goalkeeper); i++)
            ans.append(team.get(i).playerTOSTRING());
        return ans + "\n\n";
    }

    public String defendersTOSTRING() {
        int i = 0;

        for (; i < team.size() && !(team.get(i) instanceof Defender); i++) ;
        StringBuilder ans = new StringBuilder("Position\t\t\tName\t    Number\tSprint\tSpeed\tStrength\tAgression\tResistance\tDexterity\tImpulsion\tHead Game\tKick\tPass Capacity\tTackle\tMarking\t\tInterception\tOverall");
        for (; i < team.size() && (team.get(i) instanceof Defender); i++)
            ans.append(team.get(i).playerTOSTRING());
        return ans + "\n\n";
    }

    public String sidersTOSTRING() {
        int i = 0;

        for (; i < team.size() && !(team.get(i) instanceof Sider); i++) ;
        StringBuilder ans = new StringBuilder("Position\t\t\tName\t    Number\tSprint\tSpeed\tStrength\tAgression\tResistance\tDexterity\tImpulsion\tHead Game\tKick\tPass Capacity\tCrossing\tVision\tOverall");
        for (; i < team.size() && (team.get(i) instanceof Sider); i++)
            ans.append(team.get(i).playerTOSTRING());
        return ans + "\n\n";
    }

    public String midfieldersTOSTRING() {
        int i = 0;

        for (; i < team.size() && !(team.get(i) instanceof Midfielder); i++) ;
        StringBuilder ans = new StringBuilder("Position\t\t\tName\t    Number\tSprint\tSpeed\tStrength\tAgression\tResistance\tDexterity\tImpulsion\tHead Game\tKick\tPass Capacity\tBall Recovery\tVision\tOverall");
        for (; i < team.size() && (team.get(i) instanceof Midfielder); i++)
            ans.append(team.get(i).playerTOSTRING());
        return ans + "\n\n";
    }

    public String strikersTOSTRING() {
        int i = 0;

        for (; i < team.size() && !(team.get(i) instanceof Striker); i++) ;
        StringBuilder ans = new StringBuilder("Position\t\t\tName\t    Number\tSprint\tSpeed\tStrength\tAgression\tResistance\tDexterity\tImpulsion\tHead Game\tKick\tPass Capacity\tPositioning\t\tBall Control\tOverall");
        for (; i < team.size() && (team.get(i) instanceof Striker); i++)
            ans.append(team.get(i).playerTOSTRING());
        return ans + "\n\n";
    }

    @Override
    public String toString() {
        return nameTEAM + "\n" + goalkeepersTOSTRING() +
                defendersTOSTRING() +
                sidersTOSTRING() +
                midfieldersTOSTRING() +
                strikersTOSTRING() +
                substitutesTOSTRING();
    }

    protected String[] namesOfTeams = {"FC Porto", "SL Benfica", "Sporting CP", "SC Braga", "Vitória SC",
            "FC Famalicão", "Boavista FC", "Rio Ave FC", "FC Paços de Ferreira",
            "SC Farense", "CS Marítimo", "Portimonense", "CD Santa Clara",
            "Moreirense FC", "Belenenses SAD", "CD Nacional", "Gil Vicente FC",
            "CD Tondela"};

    public static Comparator<Player> PlayerComparator = (p1, p2) -> {
        if (p1.getClass().equals(p2.getClass()))
            return p2.getOverall() - p1.getOverall();
        else {
            if (p1 instanceof Goalkeeper) return -1;
            if (p2 instanceof Goalkeeper) return 1;
            if (p1 instanceof Defender) return -1;
            if (p2 instanceof Defender) return 1;
            if (p1 instanceof Sider) return -1;
            if (p2 instanceof Sider) return 1;
            if (p1 instanceof Midfielder) return -1;
            if (p2 instanceof Midfielder) return 1;
            if (p1 instanceof Striker) return -1;
            return 1;
        }
    };
}