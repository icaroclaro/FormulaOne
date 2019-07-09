package br.com.icaro.formulaone.model;

public class Posicoes {

    private long horaChegada;
    private int posicaoChegada;
    private int codigoPiloto;
    private String nomePiloto;
    private int qtdVoltasCompletadas;
    private int numeroMelhorVolta;
    private String tempoMelhorVolta;
    private String tempoTotalCorrida;
    private String velocidadeMediaVolta;
    private String tempoAposVencedor;


    public int getPosicaoChegada() {
        return posicaoChegada;
    }

    public void setPosicaoChegada(int posicaoChegada) {
        this.posicaoChegada = posicaoChegada;
    }

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

    public int getQtdVoltasCompletadas() {
        return qtdVoltasCompletadas;
    }

    public void setQtdVoltasCompletadas(int qtdVoltasCompletadas) {
        this.qtdVoltasCompletadas = qtdVoltasCompletadas;
    }

    public int getNumeroMelhorVolta() {
        return numeroMelhorVolta;
    }

    public void setNumeroMelhorVolta(int numeroMelhorVolta) {
        this.numeroMelhorVolta = numeroMelhorVolta;
    }

    public String getTempoMelhorVolta() {
        return tempoMelhorVolta;
    }

    public void setTempoMelhorVolta(String tempoMelhorVolta) {
        this.tempoMelhorVolta = tempoMelhorVolta;
    }

    public String getTempoTotalCorrida() {
        return tempoTotalCorrida;
    }

    public void setTempoTotalCorrida(String tempoTotalCorrida) {
        this.tempoTotalCorrida = tempoTotalCorrida;
    }

    public String getVelocidadeMediaVolta() {
        return velocidadeMediaVolta;
    }

    public void setVelocidadeMediaVolta(String velocidadeMediaVolta) {
        this.velocidadeMediaVolta = velocidadeMediaVolta;
    }

    @Override
    public String toString() {

        return
                codigoPiloto +
                " - " + nomePiloto + 
                " | Voltas Completadas = " + qtdVoltasCompletadas +
                "  Melhor Volta = " + numeroMelhorVolta +
                        " - " + tempoMelhorVolta +
                        "  Tempo Total Corrida = " + tempoTotalCorrida +
                "  Velocidade Media  = " + velocidadeMediaVolta +
                        "Tempo apos o Vencedor: " + tempoAposVencedor
                ;
    }

    public long getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(long horaChegada) {
        this.horaChegada = horaChegada;
    }

    public String getTempoAposVencedor() {
        return tempoAposVencedor;
    }

    public void setTempoAposVencedor(String tempoAposVencedor) {
        this.tempoAposVencedor = tempoAposVencedor;
    }
}
