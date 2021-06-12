package FM.Main.Model;




import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private Team homeTEAM;
    private Team awayTEAM;
    private ArrayList<Player> homeGOALS;
    private ArrayList<Player> awayGOALS;
    private LocalDate date;
    private Map<Player, Player> homeSUBS;
    private Map<Player, Player> awaySUBS;

    public Game(Team homeTEAM, Team awayTEAM, ArrayList<Player> homeGOALS, ArrayList<Player> awayGOALS, LocalDate date, Map<Player, Player> homeSUBS, Map<Player, Player> awaySUBS) {
        this.homeTEAM = homeTEAM;
        this.awayTEAM = awayTEAM;
        this.homeGOALS = homeGOALS;
        this.awayGOALS = awayGOALS;
        this.date = date;
        this.homeSUBS = homeSUBS;
        this.awaySUBS = awaySUBS;
    }

    public Game(Game g) {
        this.setHomeTEAM(g.getHomeTEAM());
        this.setAwayTEAM(g.getAwayTEAM());
        this.setHomeGOALS(g.getHomeGOALS());
        this.setAwayGOALS(g.getAwayGOALS());
        this.setDate(g.getDate());
        this.setHomeSUBS(g.getHomeSUBS());
        this.setAwaySUBS(g.getAwaySUBS());
    }

    public int manyDIGITS(int ii) {
        int stand = 0;
        while (ii != 0) {
            ii /= 10;
            ++stand;
        }
        return stand;
    }

    public Team getHomeTEAM() {
        return new Team(this.homeTEAM);
    }

    public void setHomeTEAM(Team homeTEAM) {
        this.homeTEAM = homeTEAM;
    }

    public Team getAwayTEAM() {
        return new Team(this.awayTEAM);
    }

    public void setAwayTEAM(Team awayTEAM) {
        this.awayTEAM = awayTEAM;
    }

    public ArrayList<Player> getHomeGOALS() {
        ArrayList<Player> clone = new ArrayList<>(homeGOALS.size());
        for (Player item : homeGOALS) clone.add(item.clone());
        return clone;
    }

    public void setHomeGOALS(ArrayList<Player> homeGOALS) {
        this.homeGOALS = homeGOALS;
    }

    public ArrayList<Player> getAwayGOALS() {
        ArrayList<Player> clone = new ArrayList<>(awayGOALS.size());
        for (Player item : awayGOALS) clone.add(item.clone());
        return clone;
    }

    public void setAwayGOALS(ArrayList<Player> awayGOALS) {
        this.awayGOALS = awayGOALS;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<Player, Player> getHomeSUBS() {
        Map<Player, Player> new_map = new HashMap<>();
        for (Map.Entry<Player, Player> entry : homeSUBS.entrySet()) {
            new_map.put(entry.getKey().clone(), entry.getValue().clone());
        }
        return new_map;
    }

    public void setHomeSUBS(Map<Player, Player> homeSUBS) {
        this.homeSUBS = homeSUBS;
    }

    public Map<Player, Player> getAwaySUBS() {
        Map<Player, Player> new_map = new HashMap<>();
        for (Map.Entry<Player, Player> entry : awaySUBS.entrySet()) {
            new_map.put(entry.getKey().clone(), entry.getValue().clone());
        }
        return new_map;
    }

    public void setAwaySUBS(Map<Player, Player> awaySUBS) {
        this.awaySUBS = awaySUBS;
    }

    public Game clone() {
        return new Game(this);
    }



    public String subsNgoals() {
        StringBuilder s = new StringBuilder();
        ArrayList<String> subsHOME = new ArrayList<>();
        ArrayList<String> subsAWAY = new ArrayList<>();

        maptoArrayList(subsHOME, homeSUBS);
        maptoArrayList(subsAWAY, awaySUBS);

        s.append("\n|").append("*".repeat(61)).append(" ".repeat(10)).append("*".repeat(129)).append("|\n");
        s.append("|").append(" ".repeat(27)).append("Golos").append(" ".repeat(28)).append("|").append(" ".repeat(10))
                .append("|").append(" ".repeat(57)).append("Substituicoes").append(" ".repeat(58)).append("|");
        s.append("\n|").append("*".repeat(61)).append(" ".repeat(10)).append("*".repeat(129)).append("|\n");

        s.append("|").append(" ".repeat(13)).append("Casa").append(" ".repeat(13)).append("|").append(" ".repeat(12))
                .append("Fora").append(" ".repeat(13)).append("|").append(" ".repeat(10)).append("|")
                .append(" ".repeat(29)).append("Casa").append(" ".repeat(30)).append("|")
                .append(" ".repeat(30)).append("Fora").append(" ".repeat(30)).append("|");
        s.append("\n|").append("*".repeat(61)).append(" ".repeat(10)).append("*".repeat(129)).append("|\n");

        for (int i = 0; i < homeGOALS.size() || i < subsHOME.size() || i < awayGOALS.size() || i < subsAWAY.size(); i++) {
            if (i < homeGOALS.size()) {
                GoalsToString(s, 31, i, homeGOALS);
            } else s.append("|").append(" ".repeat(30));

            if (i < awayGOALS.size()) {
                GoalsToString(s, 30, i, awayGOALS);
            } else s.append("|").append(" ".repeat(29));

            s.append("|").append(" ".repeat(10));
            if (i < subsHOME.size()) {
                SubsToString(s, subsHOME, i, 63);
            } else s.append("|").append(" ".repeat(63));

            if (i < subsAWAY.size()) {
                SubsToString(s, subsAWAY, i, 64);
                s.append("|\n");
            } else s.append("|").append(" ".repeat(64)).append("\n");
        }
        s.append("\n");


        return s.toString();
    }

    private void SubsToString(StringBuilder s, ArrayList<String> subsHOME, int i, int space) {
        int lengthline;
        lengthline = subsHOME.get(i).length();
        s.append("|").append(" ".repeat(Math.max((space - lengthline) / 2, 0))).append(subsHOME.get(i)).append(" ".repeat(Math.max(((space - lengthline) / 2) + ((space - lengthline) % 2), 0)));
    }

    private void GoalsToString(StringBuilder s, int spaces, int i, ArrayList<Player> homeGOALS) {
        int lengthline;
        lengthline = Math.min(homeGOALS.get(i).getName().length(), 15) + 6;
        s.append("|").append(" ".repeat(Math.max((spaces - lengthline) / 2, 0))).append(homeGOALS.get(i).getName(), 0, Math.min(homeGOALS.get(i).getName().length(), 15))
                .append(" - ").append(String.format("%02d", homeGOALS.get(i).getNumber())).append(" ".repeat(Math.max(((spaces - lengthline) / 2) + ((spaces - lengthline) % 2), 0))).append("");
    }

    private void maptoArrayList(ArrayList<String> subsAWAY, Map<Player, Player> awaySUBS) {
        for (Map.Entry<Player, Player> m : awaySUBS.entrySet()) {
            subsAWAY.add(m.getKey().getName().substring(0, Math.min(m.getKey().getName().length(), 15)) + " - "
                    + String.format("%02d", m.getKey().getNumber()) + "     -------->     " + m.getValue().getName().substring(0, Math.min(m.getValue().getName().length(), 15))
                    + " - " + String.format("%02d", m.getValue().getNumber()));
        }
    }

    public String header() {
        StringBuilder g = new StringBuilder();
        g.append("|").append("*".repeat(200)).append("|\n|");
        int lengthline = homeTEAM.getNameTEAM().length() + awayTEAM.getNameTEAM().length() + manyDIGITS(homeGOALS.size()) + manyDIGITS(awayGOALS.size()) + 25 + date.toString().length();
        g.append(" ".repeat(Math.max((200 - lengthline) / 2, 0))).append(date.toString()).append(" ".repeat(5)).append(homeTEAM.getNameTEAM()).append(" - ").append(homeGOALS.size()).append(" ".repeat(5)).append("vs")
                .append(" ".repeat(5)).append(awayGOALS.size()).append(" - ").append(awayTEAM.getNameTEAM()).append(" ".repeat(Math.max(((200 - lengthline) / 2) + ((200 - lengthline) % 2), 0)));
        g.append("|\n|").append("*".repeat(200)).append("|").append("\n");

        return g.toString();
    }

    @Override
    public String toString() {
        return header() +
                "|-> " + homeTEAM.getNameTEAM() + "\n" + homeTEAM.initial11toString() + "\n" +
                "|-> " + awayTEAM.getNameTEAM() + "\n" + awayTEAM.initial11toString() + "\n" +
                subsNgoals();
    }
}