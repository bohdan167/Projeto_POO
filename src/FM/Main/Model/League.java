package FM.Main.Model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class League {
    private ArrayList<Team> teams;
    private ArrayList<Player> top10Scorers;
    private ArrayList<ArrayList<Game>> round;

    public int manyDIGITS(int ii){
        int stand = 0;
        while (ii != 0) {
            ii /= 10;
            ++stand;
        }
        return stand;
    }

    public League(){
        teams = new ArrayList<>();
        top10Scorers = new ArrayList<>();
        round = new ArrayList<>();
    }

    public void addTeam(Team t){
        teams.add(t);
        teams.sort(TeamComparator);
    }

    public void generateLeague(){
        for(int i = 0; i<18 ; i++) {
            Team t = new Team();
            t.generateTeam();
            t.setNameTEAM(t.namesOfTeams[i]);
            teams.add(t);
        }
        teams.sort(TeamComparator);
        round = new ArrayList<>();
    }

    public League(List<String> lines) throws LinhaIncorretaException {
        teams = new ArrayList<>();
        top10Scorers = new ArrayList<>();
        round = new ArrayList<>();
        Team ultima = null;
        Player j = null;
        String[] linhaPartida;
        for (String linha : lines) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Equipa":
                    if(ultima != null){
                        ultima.randomFormation();
                        if(!Arrays.equals(ultima.getFormation(), new int[]{0, 0, 0})){
                            ultima.bestINITIAL11();
                            ultima.setSubstitutes();
                            ultima.setOverall();
                            teams.add(ultima);}
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
                    if(ultima != null){
                        ultima.randomFormation();
                        if(!Arrays.equals(ultima.getFormation(), new int[]{0, 0, 0})){
                            ultima.bestINITIAL11();
                            ultima.setSubstitutes();
                            ultima.setOverall();
                            teams.add(ultima);}
                        ultima = null;
                    }
                    //    Jogo jo = Jogo.parse(linhaPartida[1]);
                    //    jogos.add(jo);
                    break;
                default:
                    throw new LinhaIncorretaException();

            }

        }
    }

    public ArrayList<Team> getTeams() { return teams; }
    public void setTeams(ArrayList<Team> teams) { this.teams = teams; }

    public ArrayList<Player> getTop10Scorers() { return top10Scorers; }
    public void setTop10Scorers(ArrayList<Player> top10Scorers) { this.top10Scorers = top10Scorers; }


    public Team findTeam(String team){
        return teams.stream().filter(t -> (t.getNameTEAM().equals(team))).findFirst().orElse(null);
    }

    public String[] statisticsTOSTRING(Team t) {
        String[] s = new String[6];
        s[0] = "Posicao na tabela: " + (teams.indexOf(t) + 1) + "   ";
        s[1] = "Pontos: " + t.getPoints() + "   ";
        s[2] = "Golos marcados: " + t.getGoalsScored() + "   ";
        s[3] = "Golos sofridos " + t.getGoalsSuffered() + "   ";
        int dg = (t.getGoalsScored() - t.getGoalsSuffered());
        s[4] = "Diferenca de golos " + dg + "   ";
        List<Player> topScorers = top10Scorers.stream().filter(player -> t.findPLAYER(player.getName(),player.getNumber()) != null).collect(Collectors.toList());
        if(topScorers.size() > 0) {
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
            if(aux > maxscored) maxscored = aux;
            aux = manyDIGITS(team.getGoalsSuffered());
            if(aux > maxsuffered) maxsuffered = aux;
        }

        maxstand = manyDIGITS(teams.size());

        s.append("\n| I").append(" ".repeat(Math.max(0, maxstand))).append("Classificacao").append(" ".repeat(Math.max(0, maxname - "Classificacao".length()+5)))
                .append("Pontos").append(" ".repeat(Math.max(0, maxpoints +5))).append("Golos Marcados").append(" ".repeat(Math.max(0, maxscored +2)))
                .append("Golos Sofridos").append(" ".repeat(Math.max(0, maxsuffered -1))).append(" |\n");

        for (int i = 0; i<teams.size(); i++){
            int spaces = maxstand - manyDIGITS(i+1);
            s.append("| ").append(i+1).append(" ".repeat(Math.max(0, spaces + 1))).append(teams.get(i).getNameTEAM());

            spaces = (maxname - teams.get(i).getNameTEAM().length() + 5);
            s.append(" ".repeat(Math.max(0, spaces))).append(teams.get(i).getPoints());

            spaces = (maxpoints - manyDIGITS(teams.get(i).getPoints()) + 10);
            s.append(" ".repeat(Math.max(0,spaces))).append(teams.get(i).getGoalsScored());

            spaces = (maxpoints - manyDIGITS(teams.get(i).getGoalsScored()) + 15);
            s.append(" ".repeat(Math.max(0,spaces))).append(teams.get(i).getGoalsSuffered());

            spaces = (maxpoints - manyDIGITS(teams.get(i).getGoalsSuffered()) + 14);
            s.append(" ".repeat(Math.max(0,spaces))).append("|\n");
        }
        return s.toString();
    }

//nao testada
    public String top10ScorersTOSTRING(){
        StringBuilder s = new StringBuilder();
        if(top10Scorers.size()>0) {
            int maxname = 0, maxscored;

            for (Player p : top10Scorers) {
                if (p.getName().length() > maxname) maxname = p.getName().length();
            }
            maxscored = manyDIGITS(top10Scorers.get(0).getGoalsScored());

            s.append("| " + "I" + "  " + "Nome").append(" ".repeat(Math.max(0, maxname - 1))).append("Golos").append(" ".repeat(Math.max(0, maxscored - 1)));

            for (int i = 0; i < top10Scorers.size(); i++) {
                s.append("| ").append(i).append(3-manyDIGITS(i)).append(top10Scorers.get(i).getName()).append(" ".repeat(Math.max(0, maxname - top10Scorers.get(i).getName().length() + 1)))
                        .append(top10Scorers.get(i).getGoalsScored()).append(" ".repeat(Math.max(0, maxname - manyDIGITS(top10Scorers.get(i).getGoalsScored() + 1))))
                .append("|\n");
            }
        }
        else s.append("Inexistente");
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
        for(int i = 0; i < teams.size() ;i++)
            ans.append(i + 1).append(" ").append(teams.get(i).getNameTEAM()).append("\n");
        return ans.toString();
    }
}
