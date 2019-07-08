package br.com.icaro.formulaone;


import br.com.icaro.formulaone.model.VoltaPiloto;
import br.com.icaro.formulaone.service.CalculaResutadoCorridaServiceImpl;
import br.com.icaro.formulaone.utils.DateUtils;
import br.com.icaro.formulaone.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormulaOneApplicationTests {
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

    @Test(expected = Exception.class)
    public void deveRetornarFileNotFoundException() throws IOException, ParseException {

        File diretorio = new File("");

        FileUtils.lerArquivo(diretorio);
    }

    @Test(expected = Exception.class)
    public void deveRetornarNoSuchElementException() throws IOException {
        String arquivo = "C:\\";

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
    public void deveRetornarTempoEmMinutos() {
        String timeRet = DateUtils.millisecondEmMinuto(10862852L);
        String time = "01:02.852";
        assertEquals("Conversão de Milisegundos não efetuada", time, timeRet);
    }
}