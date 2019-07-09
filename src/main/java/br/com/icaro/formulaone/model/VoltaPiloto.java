package br.com.icaro.formulaone.model;

import java.time.LocalDateTime;

import br.com.icaro.formulaone.utils.DateUtils;

public class VoltaPiloto {
    private int codigoPiloto;
    private String nomePiloto;
    private int numeroVolta;
    private long tempoVolta;
    private float velocidadeMediaVolta;

    public int getCodigoPiloto() {
        return codigoPiloto;
    }

    public void setCodigoPiloto(int codigoPiloto) {
        this.codigoPiloto = codigoPiloto;
    }

    public String getNomePiloto() {
        return nomePiloto;
    }

    public void setNomePiloto(String nomePiloto) {
        this.nomePiloto = nomePiloto;
    }

    public int getNumeroVolta() {
        return numeroVolta;
    }

    public void setNumeroVolta(int numeroVolta) {
        this.numeroVolta = numeroVolta;
    }

    public float getVelocidadeMediaVolta() {
        return velocidadeMediaVolta;
    }

    public void setVelocidadeMediaVolta(float velocidadeMediaVolta) {
        this.velocidadeMediaVolta = velocidadeMediaVolta;
    }

    public long getTempoVolta() {
        return tempoVolta;
    }

    public void setTempoVolta(long tempoVolta) {
        this.tempoVolta = tempoVolta;
    }
}
