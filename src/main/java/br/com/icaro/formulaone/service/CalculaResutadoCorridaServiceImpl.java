package br.com.icaro.formulaone.service;

import br.com.icaro.formulaone.model.Posicoes;
import br.com.icaro.formulaone.model.VoltaPiloto;
import br.com.icaro.formulaone.utils.DateUtils;
import br.com.icaro.formulaone.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.logging.Level;

@Service
public class CalculaResutadoCorridaServiceImpl implements CalcularResultadoCorridaSevice {
    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

    public void calcularResultadoCorrida(String caminhoArquivo) {

        try {
            LOGGER.info("Calculos das voltas dos piltos Iniciadas");
            List<VoltaPiloto> voltasPilotos = FileUtils.lerArquivoCorrida(new File(caminhoArquivo));

            VoltaPiloto melhorVoltaCorrida = voltasPilotos.stream().min(Comparator.comparingLong(VoltaPiloto::getTempoVolta)).get();

            Map<Integer, List<VoltaPiloto>> voltasAgrupadasPorPiloto = voltasPilotos.stream()
                    .collect(Collectors.groupingBy(volta -> volta.getCodigoPiloto()));

            List<Posicoes> posicoesPilotos = new ArrayList<>();

            int quantidadeVoltasCorrida = 4;

            for (Map.Entry<Integer, List<VoltaPiloto>> mapVoltaPiloto : voltasAgrupadasPorPiloto.entrySet()) {

                List<VoltaPiloto> voltasPiloto = mapVoltaPiloto.getValue();

                String tempoTotalProvaPiloto = obterTempoTotalDaProva(voltasPiloto);

                VoltaPiloto numeroMelhorVolta = obterNumeroDaMelhorVolta(voltasPiloto);

                VoltaPiloto qtdVoltasCompletadas = obterQuantidadeDeVoltasCompletadas(voltasPiloto);

                String tempoMelhorVoltaPiloto = obterTempoDaMelhorVoltaDoPiloto(numeroMelhorVolta);

                Posicoes posicoes = preencherObjetoDeEstatisticasDoPilotoParaImpressao(quantidadeVoltasCorrida, voltasPiloto, tempoTotalProvaPiloto, numeroMelhorVolta, qtdVoltasCompletadas, tempoMelhorVoltaPiloto);

                posicoesPilotos.add(posicoes);
            }

            List<Posicoes> posicoesOrdenadas = ordenarPosicoesPilotos(posicoesPilotos);

            Long tempoDeProvaDoPrimeiroLugar = obterTempoDeProvaDoPrimeiroLugar(posicoesOrdenadas);

            preencherTempoDeChegadaAposVencedor(quantidadeVoltasCorrida, tempoDeProvaDoPrimeiroLugar, posicoesOrdenadas);

            imprimirEstatisticasPilotoNaTela(melhorVoltaCorrida, posicoesOrdenadas);


            LOGGER.info("Calculos das voltas dos piltos Finalizadas");
        } catch (ParseException e) {
            LOGGER.log(Level.SEVERE, "ParseException occur", e);
            e.printStackTrace();
        }  catch (FileNotFoundException e){
            LOGGER.log(Level.SEVERE, "FileNotFoundException occur", e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occur", e);
            e.printStackTrace();
        }
    }

    private Long obterTempoDeProvaDoPrimeiroLugar(List<Posicoes> posicoesOrdenadas) {
        return DateUtils.minutoEmMillisecond(posicoesOrdenadas.get(0).getTempoTotalCorrida(), "mm:ss.SSS");
    }

    private void imprimirEstatisticasPilotoNaTela(VoltaPiloto melhorVoltaCorrida, List<Posicoes> posicoesOrdenadas) {
        imprimirMelhorVoltaDaCorrida(melhorVoltaCorrida);

        imprimirEstatisticaDaCorrida(posicoesOrdenadas);
    }

    private void imprimirEstatisticaDaCorrida(List<Posicoes> posicoesOrdenadas) {
        System.out.print(String.format("%4s", "Pos."));
        System.out.print(String.format("%5s", "Cod."));
        System.out.print("-");
        System.out.print(String.format("%-14s", "Nome Piloto"));
        System.out.print(String.format("%11s", "Tot Voltas"));
        System.out.print(String.format("%13s", "Melhor Volta"));
        System.out.print(String.format("%11s", "Tempo Tot"));
        System.out.print(String.format("%7s", "V. Med"));
        System.out.print(String.format("%12s", "Apos Venc"));
        System.out.println();

        for(int i = 0; i < posicoesOrdenadas.size() ; i++){
            System.out.print(String.format("%4d", i+1));
            System.out.print(String.format("%5d", posicoesOrdenadas.get(i).getCodigoPiloto()));
            System.out.print("-");
            System.out.print(String.format("%-14s", posicoesOrdenadas.get(i).getNomePiloto()));
            System.out.print(String.format("%11s", posicoesOrdenadas.get(i).getQtdVoltasCompletadas()));
            System.out.print(String.format("%13s", posicoesOrdenadas.get(i).getNumeroMelhorVolta() + "-" + posicoesOrdenadas.get(i).getTempoMelhorVolta()));
            System.out.print(String.format("%11s", posicoesOrdenadas.get(i).getTempoTotalCorrida()));
            System.out.print(String.format("%7s", posicoesOrdenadas.get(i).getVelocidadeMediaVolta()));
            System.out.print(String.format("%12s", posicoesOrdenadas.get(i).getTempoAposVencedor()));
            System.out.println();
        }
    }

    private void imprimirMelhorVoltaDaCorrida(VoltaPiloto melhorVoltaCorrida) {
        System.out.println("*****************************");
        System.out.println("***Melhor Volta da Corrida***");
        System.out.println("*****************************");
        System.out.print(String.format("%12s", "Cod.Piloto"));
        System.out.print("-");
        System.out.print(String.format("%-20s", "Nome Piloto"));
        System.out.print(String.format("%-20s", "Tempo da volta"));
        System.out.println();
        System.out.print(String.format("%12d", melhorVoltaCorrida.getCodigoPiloto()));
        System.out.print(" - ");
        System.out.print(String.format("%-20s", melhorVoltaCorrida.getNomePiloto()));
        System.out.print(String.format("%-20s", DateUtils.millisecondEmMinuto(melhorVoltaCorrida.getTempoVolta(), "mm:ss.SSS")));
        System.out.println();

        System.out.println("-----------------------------------------------------------------------------");
    }

    private void preencherTempoDeChegadaAposVencedor(int quantidadeVoltasCorrida, Long horaDeChegadaDoPrimeiro, List<Posicoes> posicoesOrdenadas) {
        posicoesOrdenadas
                .stream()
                .forEach(posicoes -> {
                    if (posicoes.getQtdVoltasCompletadas() == quantidadeVoltasCorrida) {
                        posicoes.setTempoAposVencedor(DateUtils.millisecondEmMinuto((horaDeChegadaDoPrimeiro - DateUtils.minutoEmMillisecond(posicoes.getTempoTotalCorrida(), "mm:ss.SSS")) * -1, "mm:ss.SSS"));
                    } else {
                        posicoes.setTempoAposVencedor("--------");
                    }
                });
    }

    private List<Posicoes> ordenarPosicoesPilotos(List<Posicoes> posicoesPilotos) {
        return posicoesPilotos.stream()
                        .sorted((tempo1, tempo2) -> tempo1.getTempoTotalCorrida().compareTo(tempo2.getTempoTotalCorrida()))
                        .collect(Collectors.toList());
    }

    private Posicoes preencherObjetoDeEstatisticasDoPilotoParaImpressao(int quantidadeVoltasCorrida, List<VoltaPiloto> voltasPiloto, String tempoTotalProvaPiloto, VoltaPiloto numeroMelhorVolta, VoltaPiloto qtdVoltasCompletadas, String tempoMelhorVoltaPiloto) throws ParseException {
        Posicoes posicoes = new Posicoes();
        posicoes.setCodigoPiloto(voltasPiloto.get(0).getCodigoPiloto());
        posicoes.setNomePiloto(voltasPiloto.get(0).getNomePiloto());
        posicoes.setNumeroMelhorVolta(numeroMelhorVolta.getNumeroVolta());
        posicoes.setTempoMelhorVolta(tempoMelhorVoltaPiloto);
        posicoes.setQtdVoltasCompletadas(qtdVoltasCompletadas.getNumeroVolta());

        if(qtdVoltasCompletadas.getNumeroVolta() == quantidadeVoltasCorrida){
            posicoes.setTempoTotalCorrida(tempoTotalProvaPiloto);
        }else{
            posicoes.setTempoTotalCorrida("99:99.999");
            posicoes.setHoraChegada(0L);
        }

        OptionalDouble velocidadeMediaPiloto = voltasPiloto.stream().mapToDouble(i -> i.getVelocidadeMediaVolta()).average();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        posicoes.setVelocidadeMediaVolta(decimalFormat.parse(decimalFormat.format(velocidadeMediaPiloto.getAsDouble())).toString());
        return posicoes;
    }

    private String obterTempoDaMelhorVoltaDoPiloto(VoltaPiloto numeroMelhorVolta) {
        return DateUtils.millisecondEmMinuto(numeroMelhorVolta.getTempoVolta(), "mm:ss.SSS");
    }

    private VoltaPiloto obterQuantidadeDeVoltasCompletadas(List<VoltaPiloto> voltasPiloto) {
        return voltasPiloto.stream().max(Comparator.comparingLong(VoltaPiloto::getNumeroVolta)).get();
    }

    private VoltaPiloto obterNumeroDaMelhorVolta(List<VoltaPiloto> voltasPiloto) {
        return voltasPiloto.stream().min(Comparator.comparingLong(VoltaPiloto::getTempoVolta)).get();
    }

    private String obterTempoTotalDaProva(List<VoltaPiloto> voltasPiloto) {
        return DateUtils.millisecondEmMinuto(voltasPiloto.stream().mapToLong(volta -> volta.getTempoVolta()).sum(), "mm:ss.SSS");
    }
}
