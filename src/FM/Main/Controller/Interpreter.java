package FM.Main.Controller;

import FM.Main.Model.League;
import FM.Main.Model.LinhaIncorretaException;
import FM.Main.Model.Team;
import FM.Main.Model.Player;
import FM.Main.View.Menu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interpreter {
    private League l;
    private final Menu m;
    private Team myTeam;

    private int readOnlyIntegers(Scanner in, int min, int max) {
        int integer;
        try {
            integer = in.nextInt();
            if (integer < min || integer > max){
                m.line("Por favor, insira um numero entre " + min + " e " + max + ": ");
                if(in.hasNext()) in.nextLine();
                integer = readOnlyIntegers(in,min,max);
                return integer;
            }
        }
        catch(Exception e) {
            m.line("Por favor, insira um numero: ");
            if(in.hasNext()) in.nextLine();
            integer = readOnlyIntegers(in,min,max);
        }
        in.nextLine();
        return integer;
    }

    public boolean genericInterpreter(Scanner scan) {
        return (readOnlyIntegers(scan,0,0)!= 0);
    }

    public boolean myTeamInterpreter(Scanner scan){
        m.myTeamMENU();
        int ans = readOnlyIntegers(scan,0,3);
        if (ans == 0) return false;
        if  (ans == 1 || ans == 3) {
            m.header("Estatisticas");
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
            m.line("Qual jogador do 11 inicial que quer trocar? Exemplo: Ronaldo 7: ");
            String[] p = scan.nextLine().split(" ", 2);
            Player p1;
            Player p2;
            try {
                int number = Integer.parseInt(p[1]);
                p1 = myTeam.findPLAYER(p[0], number);
                m.line("Qual jogador do substitutos que quer trocar? Exemplo: Ronaldo 7: ");
                p = scan.nextLine().split(" ", 2);
                number = Integer.parseInt(p[1]);
                p2 = myTeam.findPLAYER(p[0], number);
                if (p1 != null && p2 != null && myTeam.tradePlayer(p1, p2, 0))
                    m.line("Feito.\n");
                else m.line("Impossivel.\n");
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                m.line("Introduza um jogador. Exemplo: Ronaldo 7\n");
            }
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


    public void initialInterpreter(Scanner scan) throws LinhaIncorretaException {
        int ans;

        m.line("Deseja carregar algum ficheiro? 1 - Sim  0 - Nao : ");
        ans = readOnlyIntegers(scan, 0, 1);

        if (ans == 1) {
            m.line("Localizacao do ficheiro: ");
            String where = scan.nextLine();
            l = new League(lerFicheiro(where));
        } else l.generateLeague();
        m.genericMENU(l.standingsTOSTRING());
        m.line("Escolha uma equipa, digitando o numero correspondente: ");
        ans = readOnlyIntegers(scan, 1, l.getTeams().size());
        myTeam = l.getTeams().get(ans - 1);
        m.setTeamName(myTeam.getNameTEAM());
    }

    public boolean transferInterpreter(Scanner scan) {
        m.header("Transferencias");
        m.optionsMENU(new String[]{"1 - Remover Jogador","2 - Adicionar Jogador", "3 - Trocar Jogador","0 - Sair"});
        m.line("Pretende: ");
        int ans = readOnlyIntegers(scan, 0, 3);
        if (ans == 0) return false;
        if (ans == 1) {
            m.genericMENU(myTeam.squadTOSTRING());
            m.line("Que jogador pretende retirar (Exemplo: Ronaldo 7)? ");

            String[] p = scan.nextLine().split(" ", 2);

            try {
                int number = Integer.parseInt(p[1]);
                m.line(myTeam.removePLAYER(p[0],number) + "\n");
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                m.line("Introduza um jogador (exemplo: Ronaldo 7).\n");
            }
        }
        else if (ans == 2) {
            m.genericMENU(l.standingsTOSTRING());

            m.line("Equipa do jogador pretendido (inserir o lugar de classificacao da equipa): ");
            int t = readOnlyIntegers(scan,1,l.getTeams().size());
            Team team = l.getTeams().get(t-1);

            if(team.equals(myTeam)) m.line("Equipa tem de ser diferente da tua equipa.\n");
            else {
                m.genericMENU(team.squadTOSTRING());
                m.line("Que jogador pretende adicionar a sua equipa (exemplo: Ronaldo 7): ");
                String[] p = scan.nextLine().split(" ", 2);
                try {
                    int number = Integer.parseInt(p[1]);
                    m.line(team.addPLAYER(p[0],number,team) + "\n");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    m.line("Introduza um jogador (exemplo: Ronaldo 7).\n");
                }
            }
        }
        else {
            m.genericMENU(l.standingsTOSTRING());
            m.line("Equipa do jogador pretendido (inserir o lugar de classificacao da equipa): ");
            int t = readOnlyIntegers(scan,1,l.getTeams().size());
            Team team = l.getTeams().get(t-1);

            if(team.equals(myTeam)) m.line("Equipa tem de ser diferente da tua equipa.\n");
            else {
                m.genericMENU(team.squadTOSTRING());
                m.line("Que jogador pretende adicionar a sua equipa (exemplo: Ronaldo 7): ");
                String[] p1 = scan.nextLine().split(" ", 2);
                m.whiteline(1);
                m.genericMENU(myTeam.squadTOSTRING());
                m.line("Que jogador pretende retirar (Exemplo: Ronaldo 7)? ");
                String[] p2 = scan.nextLine().split(" ", 2);

                try {
                    int n1 = Integer.parseInt(p1[1]);
                    int n2 = Integer.parseInt(p2[1]);
                    m.line(myTeam.addPLAYER(p1[0],n1,team));
                    m.line(team.addPLAYER(p2[0],n2,myTeam) + "\n");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    m.line("Introduza um jogador (exemplo: Ronaldo 7).\n");
                }
            }

        }
        return true;
    }

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
           /* int ans = readOnlyIntegers(scan,0,l.getJornadas().length());
            m.genericMENU();
        */}
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
        l = new League();
        myTeam = new Team();

        initialInterpreter(scan);
        while(mainInterpreter(scan));

        scan.close();
    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
}
