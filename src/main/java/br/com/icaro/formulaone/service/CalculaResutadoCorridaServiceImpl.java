package br.com.icaro.formulaone.service;

import br.com.icaro.formulaone.model.Posicoes;
import br.com.icaro.formulaone.model.VoltaPiloto;
import br.com.icaro.formulaone.utils.DateUtils;
import br.com.icaro.formulaone.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.logging.Level;

public class CalculaResutadoCorridaServiceImpl implements CalcularResultadoCorridaSevice {
    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

    public void calcularResultadoCorrida(String caminhoArquivo) {

        try {
            LOGGER.info("Calculos das voltas dos piltos Iniciadas");
            List<VoltaPiloto> voltasPilotos = FileUtils.lerArquivo(new File(caminhoArquivo));

            VoltaPiloto melhorVoltaCorrida = voltasPilotos.stream().min(Comparator.comparingLong(VoltaPiloto::getTempoVolta)).get();

            Map<Integer, List<VoltaPiloto>> voltasAgrupadasPorPiloto = voltasPilotos.stream()
                    .collect(Collectors.groupingBy(volta -> volta.getCodigoPiloto()));

            List<Posicoes> posicoesPilotos = new ArrayList<>();

            for (Map.Entry<Integer, List<VoltaPiloto>> mapVoltaPiloto : voltasAgrupadasPorPiloto.entrySet()) {

                List<VoltaPiloto> voltasPiloto = mapVoltaPiloto.getValue();

                String tempoTotalProvaPiloto = DateUtils.millisecondEmMinuto(voltasPiloto.stream().mapToLong(volta -> volta.getTempoVolta()).sum());

                VoltaPiloto numeroMelhorVolta = voltasPiloto.stream().min(Comparator.comparingLong(VoltaPiloto::getTempoVolta)).get();

                VoltaPiloto qtdVoltasCompletadas = voltasPiloto.stream().max(Comparator.comparingLong(VoltaPiloto::getNumeroVolta)).get();

                String tempoMelhorVoltaPiloto= DateUtils.millisecondEmMinuto(numeroMelhorVolta.getTempoVolta());

                OptionalDouble velocidadeMediaPiloto = voltasPiloto.stream().mapToDouble(i -> i.getVelocidadeMediaVolta()).average();
                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                Posicoes posicoes = new Posicoes();
                posicoes.setCodigoPiloto(voltasPiloto.get(0).getCodigoPiloto());
                posicoes.setNomePiloto(voltasPiloto.get(0).getNomePiloto());
                posicoes.setNumeroMelhorVolta(numeroMelhorVolta.getNumeroVolta());
                posicoes.setTempoMelhorVolta(tempoMelhorVoltaPiloto);
                posicoes.setQtdVoltasCompletadas(qtdVoltasCompletadas.getNumeroVolta());
                posicoes.setTempoTotalCorrida(tempoTotalProvaPiloto);
                posicoes.setVelocidadeMediaVolta(decimalFormat.parse(decimalFormat.format(velocidadeMediaPiloto.getAsDouble())).toString());

                posicoesPilotos.add(posicoes);
            }

            List<Posicoes> posicoesOrdenadas = posicoesPilotos.stream().sorted((tempo1, tempo2) -> tempo1.getTempoTotalCorrida().compareTo(tempo2.getTempoTotalCorrida()))
                    .collect((Collectors.toList()));

            System.out.println("Melhor volta da Corrida: " + melhorVoltaCorrida);
            System.out.println("--------");
            IntStream.range(0, posicoesOrdenadas.size()).mapToObj(index -> String.format("%d Lugar -> %s", index + 1, posicoesOrdenadas.get(index))).
                    forEach(System.out::println);
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
}
