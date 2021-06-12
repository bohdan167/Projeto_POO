package FM.Main.Model;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class League {
    private ArrayList<Team> teams;
    private ArrayList<Player> top10Scorers;
    private ArrayList<ArrayList<Game>> rounds;
    private ArrayList<Game> friendly;

    public League(int option) {
        teams = new ArrayList<>();
        top10Scorers = new ArrayList<>();
        rounds = new ArrayList<>();
        friendly = new ArrayList<>();
        if (option == 1) {
            for (int i = 0; i < 18; i++) {
                Team t = new Team(1);
                t.setNameTEAM(t.namesOfTeams[i]);
                addTeam(t);
            }
            teams.sort(TeamComparator);
        }
    }

    public League(List<String> lines) throws LinhaIncorretaException {
        teams = new ArrayList<>();
        top10Scorers = new ArrayList<>();
        rounds = new ArrayList<>();
        friendly = new ArrayList<>();
        Team ultima = null;
        Player j;
        String[] linhaPartida;
        for (String linha : lines) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Equipa":
                    if (ultima != null) {
                        ultima.randomFormation();
                        if (!Arrays.equals(ultima.getFormation(), new int[]{0, 0, 0})) {
                            ultima.bestINITIAL11();
                            ultima.setSubstitutes();
                            ultima.setOverall();
                            addTeam(ultima);
                            teams.sort(TeamComparator);
                        }
                    }
                    ultima = new Team(linhaPartida[1]);
                    break;
                case "Guarda-Redes":
                    j = new Goalkeeper(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException();
                    ultima.addPLAYER(j.clone());
                    break;
                case "Defesa":
                    j = new Defender(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException();
                    ultima.addPLAYER(j.clone());
                    break;
                case "Medio":
                    j = new Midfielder(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException();
                    ultima.addPLAYER(j.clone());
                    break;
                case "Lateral":
                    j = new Sider(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException();
                    ultima.addPLAYER(j.clone());
                    break;
                case "Avancado":
                    j = new Striker(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException();
                    ultima.addPLAYER(j.clone());
                    break;
                case "Jogo":
                    if (ultima != null) {
                        ultima.randomFormation();
                        if (!Arrays.equals(ultima.getFormation(), new int[]{0, 0, 0})) {
                            ultima.bestINITIAL11();
                            ultima.setSubstitutes();
                            ultima.setOverall();
                            addTeam(ultima);
                            teams.sort(TeamComparator);
                        }
                        ultima = null;
                    }

                    String[] campos = linhaPartida[1].split(",");
                    String[] date = campos[4].split("-");

                    Team home = findTeam(campos[0]);
                    Team away = findTeam(campos[1]);
                    ArrayList<Player> initial11HOME = new ArrayList<>();
                    ArrayList<Player> initial11AWAY = new ArrayList<>();
                    ArrayList<Player> goalsHOME = new ArrayList<>();
                    ArrayList<Player> goalsAWAY = new ArrayList<>();
                    Map<Player, Player> subsHOME = new HashMap<>();
                    Map<Player, Player> subsAWAY = new HashMap<>();

                    for (int i = 0; i < Integer.parseInt(campos[2]); i++) {
                        goalsHOME.add(new Goalkeeper(0));
                    }
                    for (int i = 0; i < Integer.parseInt(campos[3]); i++) {
                        goalsAWAY.add(new Goalkeeper(0));
                    }

                    for (int i = 5; i < 16; i++) {
                        initial11HOME.add(home.findPLAYER(Integer.parseInt(campos[i]), new Goalkeeper(Integer.parseInt(campos[i]))));
                    }
                    home.setInitial11(initial11HOME);

                    for (int i = 16; i < 19; i++) {
                        String[] sub = campos[i].split("->");
                        subsHOME.put(home.findPLAYER(Integer.parseInt(sub[0]), new Goalkeeper(Integer.parseInt(sub[0]))), home.findPLAYER(Integer.parseInt(sub[1]), new Goalkeeper(Integer.parseInt(sub[1]))));
                    }

                    ArrayList<Player> substitutesHOME = new ArrayList<>();
                    for (Map.Entry<Player, Player> e : subsHOME.entrySet()) {
                        substitutesHOME.add(e.getValue());
                    }
                    home.setSubstitutes(substitutesHOME);

                    for (int i = 19; i < 30; i++) {
                        initial11AWAY.add(home.findPLAYER(Integer.parseInt(campos[i]), new Goalkeeper(Integer.parseInt(campos[i]))));
                    }
                    away.setInitial11(initial11AWAY);

                    for (int i = 30; i < 33; i++) {
                        String[] sub = campos[i].split("->");
                        subsAWAY.put(home.findPLAYER(Integer.parseInt(sub[0]), new Goalkeeper(Integer.parseInt(sub[0]))), home.findPLAYER(Integer.parseInt(sub[1]), new Goalkeeper(Integer.parseInt(sub[1]))));
                    }

                    ArrayList<Player> substitutesAWAY = new ArrayList<>();
                    for (Map.Entry<Player, Player> e : subsHOME.entrySet()) {
                        substitutesAWAY.add(e.getValue());
                    }
                    away.setSubstitutes(substitutesAWAY);

                    friendly.add(new Game(home, away, goalsHOME, goalsAWAY, LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])), subsHOME, subsAWAY));
                    break;
                default:
                    throw new LinhaIncorretaException();

            }
        }
    }


    public ArrayList<Team> getTeams() {
        ArrayList<Team> clone = new ArrayList<>(teams.size());
        for (Team item : teams) clone.add(item.clone());
        return clone;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Player> getTop10Scorers() {
        ArrayList<Player> clone = new ArrayList<>(top10Scorers.size());
        for (Player item : top10Scorers) clone.add(item.clone());
        return clone;
    }

    public void setTop10Scorers(ArrayList<Player> top10Scorers) {
        this.top10Scorers = top10Scorers;
    }

    public ArrayList<ArrayList<Game>> getRounds() {
        ArrayList<ArrayList<Game>> clone = new ArrayList<>(rounds.size());
        for (ArrayList<Game> round : rounds) {
            ArrayList<Game> cl = new ArrayList<>(round.size());
            for (int j = 0; j < round.size(); j++) {
                cl.add(round.get(j).clone());
            }
            clone.add(cl);
        }
        return clone;
    }

    public void setRounds(ArrayList<ArrayList<Game>> rounds) {
        this.rounds = rounds;
    }

    public ArrayList<Game> getFriendly() {
        ArrayList<Game> clone = new ArrayList<>(friendly.size());
        for (Game item : friendly) clone.add(item.clone());
        return clone;
    }

    public void setFriendly(ArrayList<Game> friendly) {
        this.friendly = friendly;
    }


    public void addTeam(Team t) {
        teams.add(t);
        teams.sort(TeamComparator);
    }

    public Team findTeam(String team) {
        return teams.stream().filter(t -> (t.getNameTEAM().equals(team))).findFirst().orElse(new Team("Unknown")).clone();
    }




    public int manyDIGITS(int ii) {
        int stand = 0;
        while (ii != 0) {
            ii /= 10;
            ++stand;
        }
        return stand;
    }

    public String[] statisticsTOSTRING(Team t) {
        String[] s = new String[6];
        s[0] = "Posicao na tabela: " + (teams.indexOf(t) + 1) + "   ";
        s[1] = "Pontos: " + t.getPoints() + "   ";
        s[2] = "Golos marcados: " + t.getGoalsScored() + "   ";
        s[3] = "Golos sofridos " + t.getGoalsSuffered() + "   ";
        int dg = (t.getGoalsScored() - t.getGoalsSuffered());
        s[4] = "Diferenca de golos " + dg + "   ";
        List<Player> topScorers = top10Scorers.stream().filter(player -> t.findPLAYER(player.getNumber(), new Goalkeeper()) != null).collect(Collectors.toList());
        if (topScorers.size() > 0) {
            s[5] += ("Top Marcadores que estao na equipa:\n");
            for (Player p : topScorers) {
                s[5] += ("\n\t") + (p.getName()) + (" ") + (p.getNumber()) + ("\n");
            }
        } else s[5] = "Nenhum jogador da equipa esta nos top 10 marcadores\n";
        return s;
    }

    public String standingsTOSTRING() {
        StringBuilder s = new StringBuilder();
        int maxstand, maxname = 0, maxpoints = 0, maxscored = 0, maxsuffered = 0;

        for (Team team : teams) {
            if (team.getNameTEAM().length() > maxname)
                maxname = team.getNameTEAM().length();
            int aux = manyDIGITS(team.getPoints());
            if (aux > maxpoints) maxpoints = aux;
            aux = manyDIGITS(team.getGoalsScored());
            if (aux > maxscored) maxscored = aux;
            aux = manyDIGITS(team.getGoalsSuffered());
            if (aux > maxsuffered) maxsuffered = aux;
        }

        maxstand = manyDIGITS(teams.size());

        s.append("\n| I").append(" ".repeat(Math.max(0, maxstand))).append("Classificacao").append(" ".repeat(Math.max(0, maxname - "Classificacao".length() + 5)))
                .append("Pontos").append(" ".repeat(Math.max(0, maxpoints + 5))).append("Golos Marcados").append(" ".repeat(Math.max(0, maxscored + 2)))
                .append("Golos Sofridos").append(" ".repeat(Math.max(0, maxsuffered - 1))).append(" |\n");

        for (int i = 0; i < teams.size(); i++) {
            int spaces = maxstand - manyDIGITS(i + 1);
            s.append("| ").append(i + 1).append(" ".repeat(Math.max(0, spaces + 1))).append(teams.get(i).getNameTEAM());

            spaces = (maxname - teams.get(i).getNameTEAM().length() + 5);
            s.append(" ".repeat(Math.max(0, spaces))).append(teams.get(i).getPoints());

            spaces = (maxpoints - manyDIGITS(teams.get(i).getPoints()) + 10);
            s.append(" ".repeat(Math.max(0, spaces))).append(teams.get(i).getGoalsScored());

            spaces = (maxpoints - manyDIGITS(teams.get(i).getGoalsScored()) + 15);
            s.append(" ".repeat(Math.max(0, spaces))).append(teams.get(i).getGoalsSuffered());

            spaces = (maxpoints - manyDIGITS(teams.get(i).getGoalsSuffered()) + 14);
            s.append(" ".repeat(Math.max(0, spaces))).append("|\n");
        }
        return s.toString();
    }

    //nao testada
    public String top10ScorersTOSTRING() {
        StringBuilder s = new StringBuilder();
        if (top10Scorers.size() > 0) {
            int maxname = 0, maxscored;

            for (Player p : top10Scorers) {
                if (p.getName().length() > maxname) maxname = p.getName().length();
            }
            maxscored = manyDIGITS(top10Scorers.get(0).getGoalsScored());

            s.append("| " + "I" + "  " + "Nome").append(" ".repeat(Math.max(0, maxname - 1))).append("Golos").append(" ".repeat(Math.max(0, maxscored - 1)));

            for (int i = 0; i < top10Scorers.size(); i++) {
                s.append("| ").append(i).append(3 - manyDIGITS(i)).append(top10Scorers.get(i).getName()).append(" ".repeat(Math.max(0, maxname - top10Scorers.get(i).getName().length() + 1)))
                        .append(top10Scorers.get(i).getGoalsScored()).append(" ".repeat(Math.max(0, maxname - manyDIGITS(top10Scorers.get(i).getGoalsScored() + 1))))
                        .append("|\n");
            }
        } else s.append("Inexistente");
        return s.toString();

    }

    public static Comparator<Team> TeamComparator = (p1, p2) -> {
        if (p1.getClass().equals(p2.getClass()))
            return p2.getOverall() - p1.getOverall();
        else {
            if (p1.getPoints() > p2.getPoints()) return -1;
            if (p1.getPoints() < p2.getPoints()) return 1;
            if (p1.getGoalsScored() > p2.getGoalsScored()) return -1;
            if (p1.getGoalsScored() < p2.getGoalsScored()) return 1;
            if (p1.getGoalsSuffered() < p2.getGoalsSuffered()) return 1;
            if (p1.getGoalsSuffered() > p2.getGoalsSuffered()) return -1;
            return 1;
        }
    };


    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < teams.size(); i++)
            ans.append(i + 1).append(" ").append(teams.get(i).getNameTEAM()).append("\n");
        return ans.toString();
    }
}