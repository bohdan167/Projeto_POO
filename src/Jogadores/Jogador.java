package Jogadores;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Jogador {
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogoDeCabeca;
    private int remate;
    private int capacidadeDePasse;

    public Jogador(){
        this.setVelocidade(-1);
        this.setResistencia(-1);
        this.setDestreza(-1);
        this.setImpulsao(-1);
        this.setJogoDeCabeca(-1);
        this.setRemate(-1);
        this.setCapacidadeDePasse(-1);
    }

    public Jogador(Jogador jogador){
        this.velocidade = jogador.getVelocidade();
        this.resistencia = jogador.getResistencia();
        this.destreza = jogador.getDestreza();
        this.impulsao = jogador.getImpulsao();
        this.jogoDeCabeca = jogador.getJogoDeCabeca();
        this.remate = jogador.getRemate();
        this.capacidadeDePasse = jogador.getCapacidadeDePasse();
    }

    public Jogador(int velocidade, int resistencia, int destreza, int impulsao,
                   int jogoDeCabeca, int remate, int capacidadeDePasse){

        setVelocidade(velocidade);
        setResistencia(resistencia);
        setDestreza(destreza);
        setImpulsao(impulsao);
        setJogoDeCabeca(jogoDeCabeca);
        setRemate(remate);
        setCapacidadeDePasse(capacidadeDePasse);
    }

    public int getVelocidade() {
        return this.velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getResistencia() {
        return this.resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getDestreza() {
        return this.destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getImpulsao() {
        return this.impulsao;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public int getJogoDeCabeca() {
        return this.jogoDeCabeca;
    }

    public void setJogoDeCabeca(int jogoDeCabeca) {
        this.jogoDeCabeca = jogoDeCabeca;
    }

    public int getRemate() {
        return this.remate;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public int getCapacidadeDePasse() {
        return this.capacidadeDePasse;
    }

    public void setCapacidadeDePasse(int capacidadeDePasse) {
        this.capacidadeDePasse = capacidadeDePasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jogador)) return false;
        Jogador jogador = (Jogador) o;
        return getVelocidade() == jogador.getVelocidade()
                             && getResistencia() == jogador.getResistencia()
                             && getDestreza() == jogador.getDestreza()
                             && getImpulsao() == jogador.getImpulsao()
                             && getJogoDeCabeca() == jogador.getJogoDeCabeca()
                             && getRemate() == jogador.getRemate()
                             && getCapacidadeDePasse() == jogador.getCapacidadeDePasse();
    }

    public double habMapToInt (Map<Double,Integer> habMap){
        double habSize = habMap.size();
        int sum = 0;
        double mediaP;

        for (Map.Entry<Double,Integer> entry : habMap.entrySet())
            sum += entry.getKey()*entry.getValue();

        mediaP = sum/(habSize);
        return mediaP;
    }

    public Map<Double,Integer> getHabMap(int velocidade, int resistencia, int destreza, int impulsao,
                         int jogoDeCabeca, int remate, int capacidadeDePasse){

        Map<Double,Integer> habMap = new HashMap<>();
        habMap.put(0.9, velocidade);
        habMap.put(0.6, resistencia);
        habMap.put(0.4, destreza);
        habMap.put(0.5, impulsao);
        habMap.put(0.3, jogoDeCabeca);
        habMap.put(0.8, remate);
        habMap.put(0.7, capacidadeDePasse);

        return habMap;
    }
}
