package br.com.icaro.formulaone.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;

public class DateUtils {
	private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

	public static Long minutoEmMillisecond(String tempoVolta) throws ParseException {
		LOGGER.info("Name: "+LOGGER.getName());
		LOGGER.info("Tempo Formatado: " + tempoVolta);
		Date data = new SimpleDateFormat("mm:ss.SSS").parse(tempoVolta);
		LOGGER.info("Tempo Convertido para Milisegundos: " + data.getTime());
		return data.getTime();

	}
	
	public static String millisecondEmMinuto(Long millisecondVolta) {
		LOGGER.info("Name: "+LOGGER.getName());
		LOGGER.info("Tempo em millisegundo: " + millisecondVolta);
		LocalDateTime melhorVoltaMinutos =
				Instant.ofEpochMilli(millisecondVolta).atZone(ZoneId.systemDefault()).toLocalDateTime();
		LOGGER.info("Tempo Formatado: " + melhorVoltaMinutos);
		return melhorVoltaMinutos
				.format(DateTimeFormatter.ofPattern("mm:ss.SSS"));

	}
	
}
