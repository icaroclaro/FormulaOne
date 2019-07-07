package br.com.icaro.formulaone.model;

import java.time.LocalDateTime;

import br.com.icaro.formulaone.utils.DateUtils;

public class VoltaPiloto {
    private String horaRegistradaVolta;
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

    public String getHoraRegistradaVolta() {
        return horaRegistradaVolta;
    }

    public void setHoraRegistradaVolta(String horaRegistradaVolta) {
        this.horaRegistradaVolta = horaRegistradaVolta;
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

	@Override
	public String toString() {
		return "Hora Registrada da Volta=" + horaRegistradaVolta + ", Codigo do Piloto=" + codigoPiloto
				+ ", Piloto=" + nomePiloto + ", Melhor Volta=" + numeroVolta + ", Tempo da Volta=" + DateUtils.millisecondEmMinuto(tempoVolta)
				+ ", Velocidade Media da Volta=" + velocidadeMediaVolta;
	}

}
