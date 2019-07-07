package br.com.icaro.formulaone.tests;

import br.com.icaro.formulaone.model.VoltaPiloto;
import br.com.icaro.formulaone.service.CalculaResutadoCorridaServiceImpl;
import br.com.icaro.formulaone.utils.DateUtils;
import br.com.icaro.formulaone.utils.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FormulaOneApplicationTest {
    CalculaResutadoCorridaServiceImpl calculaResutadoCorridaServiceImpl = new CalculaResutadoCorridaServiceImpl();
    @Test
    public void calcularResultadoCorrida() {
        calculaResutadoCorridaServiceImpl.calcularResultadoCorrida("log.txt");
        assert true;
    }

    @Test
    public void deveRetornarListaDeVoltasDoPiloto() throws IOException, ParseException {

        File diretorio = new File("log.txt");

        List<VoltaPiloto> voltaPilotos = FileUtils.lerArquivo(diretorio);

        assertEquals("arquivos encontrados", 23, voltaPilotos.size());
    }
    @Test (expected = FileNotFoundException.class)
    public void deveRetornarException() throws IOException, ParseException {

        File diretorio = new File("c:\\");

        List<VoltaPiloto> voltaPilotos = FileUtils.lerArquivo(diretorio);
    }

    @Test (expected = java.util.NoSuchElementException.class)
    public void deveRetornarNoSuchElementException() throws IOException {
        String arquivo = "C:\\Users\\Probook\\Desktop\\";

        new File(arquivo, "arquivo-1.txt").createNewFile();

        arquivo += "arquivo-1.txt";

        calculaResutadoCorridaServiceImpl.calcularResultadoCorrida(arquivo);
    }

    @Test
    public void deveRetornarTempoEmMilissegundos() throws ParseException {
        Long milisegundosRet = DateUtils.minutoEmMillisecond("1:02.852");
        Long milisegundos = 10862852L;
        assertEquals("Conversão de Minuto não efetuada", milisegundos, milisegundosRet);
    }
    @Test
    public void deveRetornarTempoEmMinutos(){
        String timeRet = DateUtils.millisecondEmMinuto(10862852L);
        String time = "01:02.852";
        assertEquals("Conversão de Milisegundos não efetuada", time, timeRet);
    }
}