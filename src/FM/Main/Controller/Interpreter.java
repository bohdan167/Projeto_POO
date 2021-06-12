package FM.Main.Controller;

import FM.Main.Model.*;
import FM.Main.View.Menu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Interpreter {
    private League l;
    private final Menu m;
    private Team myTeam;

    private int readOnlyIntegers(Scanner in, int min, int max) {
        String integer = in.nextLine();
        int i;
        try {
            i = Integer.parseInt(integer);
            if (i < min || i > max){
                m.line("Por favor, insira um numero entre " + min + " e " + max + ": ");
                i = readOnlyIntegers(in,min,max);
                return i;
            }
        }
        catch(Exception e) {
            m.line("Por favor, insira um numero: ");
            i = readOnlyIntegers(in,min,max);
        }
        return i;
    }

    public boolean genericInterpreter(Scanner scan) {
        return (readOnlyIntegers(scan,0,0)!= 0);
    }





//////////////////transfers
    public boolean transferInterpreter(Scanner scan) {
        m.header("Transferencias");
        m.optionsMENU(new String[]{"1 - Remover Jogador", "2 - Adicionar Jogador", "3 - Trocar Jogador", "0 - Sair"});
        m.line("Pretende: ");
        int ans = readOnlyIntegers(scan, 0, 3);
        if (ans == 0) return false;
        if (ans == 1) {
            m.genericMENU(myTeam.squadTOSTRING());
            m.line("Insira o número do jogador que pretende remover da sua equipa: ");
            ans = readOnlyIntegers(scan, 0, 100);
            m.line(myTeam.removePLAYER(ans) + "\n");
        } else if (ans == 2) {
            m.genericMENU(l.standingsTOSTRING());

            m.line("Equipa do jogador pretendido (inserir o lugar de classificacao da equipa): ");
            int t = readOnlyIntegers(scan, 1, l.getTeams().size());
            Team team = l.getTeams().get(t - 1);

            if (team.equals(myTeam)) m.line("Equipa tem de ser diferente da tua equipa.\n");
            else {
                m.genericMENU(team.squadTOSTRING());
                m.line("Insira o número do jogador que pretende adicionar à sua equipa: ");
                ans = readOnlyIntegers(scan, 0, 100);
                m.line(myTeam.addPLAYER(ans, team) + "\n");
            }
        } else {
            m.genericMENU(l.standingsTOSTRING());
            m.line("Equipa do jogador pretendido (inserir o lugar de classificacao da equipa): ");
            int t = readOnlyIntegers(scan, 1, l.getTeams().size());
            Team team = l.getTeams().get(t - 1);

            if (team.equals(myTeam)) m.line("Equipa tem de ser diferente da tua equipa.\n");
            else {
                m.genericMENU(team.squadTOSTRING());
                m.line("Insira o número do jogador que pretende adicionar à sua equipa: ");
                int p1 = readOnlyIntegers(scan, 0, 100);
                m.whiteline(1);
                m.genericMENU(myTeam.squadTOSTRING());
                m.line("Insira o número do jogador que pretende remover da sua equipa: ");
                int p2 = readOnlyIntegers(scan, 0, 100);
                m.line(myTeam.addPLAYER(p1, team));
                m.line(team.addPLAYER(p2, myTeam) + "\n");
            }
        }
        return true;
    }




    ///League stats
    public boolean leagueStatsInterpreter(Scanner scan) {
        m.leagueStatsMENU();
        int ans = readOnlyIntegers(scan, 0, 3);
        if (ans == 0) return false;
        if (ans == 1 || ans == 2) {
            if(ans == 1) m.genericMENU(l.standingsTOSTRING());
            else m.genericMENU(l.top10ScorersTOSTRING());
            m.optionsMENU(new String[]{"0 - Sair"});
            m.line("Pretende: ");

            while(genericInterpreter(scan));
        }
        else {
            m.line("Qual jornada?");
            ans = readOnlyIntegers(scan,0,l.getRounds().size());

        }
        return true;
    }

    public boolean myGamesInterpreter(Scanner scan){
        m.myGamesMenu();
        int ans = readOnlyIntegers(scan,0,2);
        if(ans == 0) return false;
        StringBuilder s = new StringBuilder();
        if(ans == 1) {
            for(ArrayList<Game> journey : l.getRounds()){
                for(int i = 0; i < journey.size(); i++){
                    s.append("\n|->").append(i + 1).append("\n").append(journey.get(i).header()).append("\n");
                }
            }
            m.genericMENU(s.toString());
            m.optionsMENU(new String[]{"0 - Sair"});
            genericInterpreter(scan);
            m.line("Pretende:");
        }
        else {
            ArrayList<Game> myfriendly = new ArrayList<>();
            for(Game g : l.getFriendly()){
                if((g.getHomeTEAM().getNameTEAM().equals(myTeam.getNameTEAM()) || g.getAwayTEAM().getNameTEAM().equals(myTeam.getNameTEAM()))){
                    myfriendly.add(g);
                }
            }

            for(int i = 0; i < myfriendly.size(); i++){
                s.append("\n|->").append(i + 1).append("\n").append(myfriendly.get(i).header()).append("\n");
            }
            m.genericMENU(s.toString());
            m.optionsMENU(new String[]{"1 - Ver Jogo Especifico","0 - Sair"});
            ans = readOnlyIntegers(scan,0,1);
            if(ans == 1) while(myFriendlyGameInterpreter(scan,myfriendly));
            else return false;

        }
        return true;
    }

    public boolean myFriendlyGameInterpreter(Scanner scan, List<Game> myfriendly){
        m.line("Qual Jogo? Insira 0 se quiser sair. ");
        int ans = readOnlyIntegers(scan,0,myfriendly.size());
        if (ans == 0) return false;
        m.genericMENU(myfriendly.get(ans-1).toString());
        m.optionsMENU(new String[]{"0 - Sair"});
        m.line("Pretende: ");
        genericInterpreter(scan);
        return true;
    }

    public boolean myTeamInterpreter(Scanner scan){
        m.myTeamMENU();
        int ans = readOnlyIntegers(scan,0,4);
        if (ans == 0) return false;
        if  (ans == 1 || ans == 3) {
            m.header("Minha Equipa");
            m.whiteline(1);
            if(ans == 1) m.genericMENU(myTeam.squadTOSTRING());
            else {
                String [] s = l.statisticsTOSTRING(myTeam);
                for(String ss : s) m.line(ss);
            }
            m.whiteline(1);
            m.optionsMENU(new String[]{"0 - Sair"});
            m.line("Pretende: ");
            while(genericInterpreter(scan));
        }
        else if (ans == 2) {
            while(initial11Interpreter(scan));
        }
        else{
            while(myGamesInterpreter(scan));
        }
        return true;
    }

    public boolean formationInterpreter(Scanner scan){
        m.header("FORMACAO TATICA" + Arrays.toString(myTeam.getFormation()));
        int ans;
        m.optionsMENU(new String[]{"1 - Alterar a Formacao Tatica","0 - Sair"});
        m.line("Pretende: ");
        ans = readOnlyIntegers(scan, 0, 1);

        if (ans == 0) return false;
        m.line("Digite a formacao tatica que pretende implementar: Exemplo: 4 3 3: ");
        try {
            String[] p = scan.nextLine().split(" ", 3);
            int []number = new int[]{Integer.parseInt(p[0]),Integer.parseInt(p[1]),Integer.parseInt(p[2])};
            if(myTeam.setFormation(number))
                m.line("Feito.\n");
            else m.line("Impossivel.\n");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            m.line("Introduza uma formacao tatica. Exemplo: 4 3 3: \n");
        }
        return true;
    }

    public boolean changeinitial11(Scanner scan) {
        m.header("Alterar o 11 inicial");
        m.genericMENU(myTeam.initial11toString());
        m.line("Overall: " + myTeam.getOverall() + "\n\n");
        m.optionsMENU(new String[]{"1 - Melhor 11 inicial", "2 - Personalizado", "0 - Sair"});
        m.line("Pretende: ");
        int ans = readOnlyIntegers(scan, 0, 2);
        if (ans == 0) return false;
        if (ans == 1) {
            myTeam.bestINITIAL11();
            m.line("Feito.\n");
        }
        else {
            m.line("Insira o número do jogador do 11 inicial que quer trocar: ");
            int p1 = readOnlyIntegers(scan, 0, 100);
            Player initial = myTeam.findPLAYER(p1,null);
            if (initial != null) {
                m.line("Insira o número do jogador dos suplentes que quer trocar: ");
                int p2 = readOnlyIntegers(scan, 0, 100);
                Player sub = myTeam.findPLAYER(p2, null);
                if( sub != null)
                    myTeam.tradePlayer(initial,sub,0);
                else m.line("Erro");
            }
            else m.line("Erro");
        }
        return true;
    }

    public boolean initial11Interpreter(Scanner scan) {
        m.header("11 inicial");
        m.optionsMENU(new String[]{"1 - Alterar o 11 inicial", "2 - Formacao Tatica", "0 - Sair"});
        m.line("Pretende: ");
        int ans = readOnlyIntegers(scan, 0, 2);

        if (ans == 0) return false;
        if (ans == 1) {
            while(changeinitial11(scan));
        } else {
            while(formationInterpreter(scan));
        }
        return true;
    }


    public boolean initialInterpreter(Scanner scan) throws LinhaIncorretaException {
        int ans;

        m.line("Deseja carregar algum ficheiro? 1 - Sim  0 - Nao : ");
        ans = readOnlyIntegers(scan, 0, 1);

        if (ans == 1) {
            m.line("Localizacao do ficheiro: ");
            String where = scan.nextLine();
            l = new League(lerFicheiro(where));
        } else l = new League(1);
        if(l.getTeams().size() != 0) {
            m.genericMENU(l.standingsTOSTRING());
            m.line("Escolha uma equipa, digitando o numero correspondente: ");
            ans = readOnlyIntegers(scan, 1, l.getTeams().size());
            myTeam = l.getTeams().get(ans - 1);
            m.setTeamName(myTeam.getNameTEAM());
        }
        else return false;
        return true;
    }



    public boolean mainInterpreter(Scanner scan){
        int ans;
        m.mainMENU();
        ans = readOnlyIntegers(scan,0,4);
        if (ans == 0) return false;
        else if (ans == 1) ans = 3; //modo jogo
        else if (ans == 2) while(transferInterpreter(scan));
        else if (ans == 3) while(leagueStatsInterpreter(scan));
        else if (ans == 4) while(myTeamInterpreter(scan));
        return true;
    }

    public Interpreter() throws IOException, LinhaIncorretaException {
        Scanner scan = new Scanner(System.in);
        m = new Menu();
        l = new League(0);
        myTeam = new Team(0);

        if(initialInterpreter(scan)){
        while(mainInterpreter(scan));
        }

        scan.close();
    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
}
