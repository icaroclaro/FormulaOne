package br.com.icaro.formulaone;

import br.com.icaro.formulaone.service.CalculaResutadoCorridaServiceImpl;

public class FormulaoneApplication {
    public static void main(String[] args) {
    	CalculaResutadoCorridaServiceImpl calculaResutadoCorridaServiceImpl = new CalculaResutadoCorridaServiceImpl();
    	calculaResutadoCorridaServiceImpl.calcularResultadoCorrida("log.txt");
    }
}
