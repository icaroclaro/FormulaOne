package br.com.icaro.formulaone;

import br.com.icaro.formulaone.service.CalcularResultadoCorridaSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FormulaOneApplication {


    @Autowired
    CalcularResultadoCorridaSevice calcularResultadoCorridaSevice;

    public static void main(String[] args) {
        SpringApplication.run(FormulaOneApplication.class, args);
    }

    @Bean
    public void CalcularResultadoCorrida() {
        calcularResultadoCorridaSevice.calcularResultadoCorrida("log.txt");
    }
}
