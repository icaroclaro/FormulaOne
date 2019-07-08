package br.com.icaro.formulaone.utils;

import br.com.icaro.formulaone.model.VoltaPiloto;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileUtils {
    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

    public static List<VoltaPiloto> lerArquivo(File arquivo) throws IOException, ParseException {

        LOGGER.info("Iniciando a leitura do arquivo");
        List<VoltaPiloto> voltasPilotos = new ArrayList<>();

        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }

        FileReader fr;
        BufferedReader br = null;

        fr = new FileReader(arquivo);
        br = new BufferedReader(fr);


        System.out.println(br.readLine());

        while (br.ready()) {
            String linha = br.readLine();
            voltasPilotos.add(adicionaVoltaPiloto(linha));
        }
        br.close();
        LOGGER.info("Finalizando a leitura do arquivo");
        return voltasPilotos;

    }

    private  static VoltaPiloto adicionaVoltaPiloto(String linha) throws ParseException {

        VoltaPiloto voltaPiloto = new VoltaPiloto();
        String[] splited = linha.split("\\s+");

        voltaPiloto.setHoraRegistradaVolta(splited[0]);
        voltaPiloto.setCodigoPiloto(Integer.parseInt(splited[1]));
        voltaPiloto.setNomePiloto(splited[3]);
        voltaPiloto.setNumeroVolta(Integer.parseInt(splited[4]));

        voltaPiloto.setTempoVolta(DateUtils.minutoEmMillisecond(splited[5]));
        voltaPiloto.setVelocidadeMediaVolta(Float.parseFloat(splited[6].replace(",", ".")));

        return voltaPiloto;

    }
}
