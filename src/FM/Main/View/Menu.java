package FM.Main.View;


public class Menu {
    private String teamName;

    public Menu(){
        teamName = "";
    }

    public String getTeamName() { return teamName; }

    public void setTeamName(String teamName) { this.teamName = teamName; }

    public void line(String ask){
        System.out.print("|-> " + ask);
    }

    public void whiteline(int howMANY){
        for(int i = 0; i < howMANY ; i++)
            System.out.print("\n");
    }

    public void optionsMENU(String [] options){
        StringBuilder s = new StringBuilder("|");
        int length = 0;
        for(String str : options) length += str.length();
        int spaces = (200 - length)/(options.length + 1);
        StringBuilder space = new StringBuilder();
        space.append(" ".repeat(Math.max(0, spaces)));
        for(int i = 0; i < options.length; i++) s.append(space).append(options[i]);
        s.append(space).append(" ".repeat(((200-length) % ((options.length) +1)))).append("|\n|").append("*".repeat(200)).append("|");
        System.out.println(s);
    }

    public void genericMENU(String generic){
        System.out.println(generic);
    }


    public void myTeamMENU(){
        header("Minha Equipa");
        optionsMENU(new String[]{"1 - Plantel","2 - 11 inicial", "3 - Estatisticas","0 - Sair"});
        line("Pretende: ");
    }


    public void header(String menu) {
        System.out.print("\n\n|");
        System.out.print("*".repeat(200));
        System.out.print("|\n");
        System.out.print("|");
        int length = 200 - teamName.length() - menu.length();
        String repeat = " ".repeat(Math.max(0, length / 3));
        System.out.print(repeat);
        System.out.print(teamName);
        System.out.print(repeat);
        System.out.print(menu);
        System.out.print(repeat);
        System.out.print(" ".repeat(Math.max(0,length % 3)));
        System.out.print("|\n");
        System.out.println("|" + "*".repeat(200) + "|");
    }

    public void mainMENU() {
        header("Menu principal");
        optionsMENU(new String[]{"1 - Jogar","2 - Transferencia de jogador","3 - Estatisticas da liga","4 - Minha Equipa","0 - Sair"});
        line("Pretende: ");
    }

    public void playMENU() {

    }

    public void leagueStatsMENU(){
        header("Estatisticas da liga");
        optionsMENU(new String[]{"1 - Classificacao geral","2 - Melhores marcadores","3 - Jornadas","0 - Sair"});
        line("Pretende: ");
    }

}
