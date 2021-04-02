package Team;

import Player.Player;

public class Team {
    String nameTEAM;
    Player [] striker;
    Player [] midfielder;
    Player [] wing;
    Player [] defense;
    Player [] keeper;


    public Team(){
        setNameTEAM("Unknown");
        this.keeper = new Player[3];
    }

    public Team(String nameTEAM, Player){

    }

    public Team(Team t){
        setNameTEAM(t.getNameTEAM());
       // setPlayers(t.getPlayers());
    }

    public String getNameTEAM() { return nameTEAM; }
    public void setNameTEAM(String nameTEAM) { this.nameTEAM = nameTEAM; }

    public Player[] getKeeper() { return keeper; }
    public void setKeeper(Player[] keeper) { this.keeper = keeper; }

    public Player[] getDefense() { return defense; }

    public void setDefense(Player[] defense) { this.defense = defense; }
}

/*-> Criar equipa aleatória
* -> Criar uma equipa equilibrada
* -> Nome da equipa, jogadores.
* -> Retirar e adicionar o jogador
* -> Média do overall da equipa
* -> Meter as estrelas conforme o overall no printf
*
* Equipa
* -> 3 guarda-redes
* -> 10 defesas
* -> 9 médios
* -> 8 avançados
* ->
*
* -> Define o 11 inicial de acordo com os melhores na posição
* -> */
