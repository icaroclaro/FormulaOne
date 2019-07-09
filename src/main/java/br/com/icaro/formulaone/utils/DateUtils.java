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

	public static Long minutoEmMillisecond(String tempoVolta, String formato) {

		Date data = null;
		try {
			data = new SimpleDateFormat(formato).parse(tempoVolta);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return data.getTime();

	}
	
	public static String millisecondEmMinuto(Long millisecondVolta, String formato) {
		LocalDateTime tempoEmMilisegundos =
				Instant.ofEpochMilli(millisecondVolta).atZone(ZoneId.systemDefault()).toLocalDateTime();

		return tempoEmMilisegundos
				.format(DateTimeFormatter.ofPattern(formato));

	}
	
}
