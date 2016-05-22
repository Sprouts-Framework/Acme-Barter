package es.us.lsi.dp.formats;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public class CustomDateFormat extends CustomFormat {

	public CustomDateFormat(String codePrefix, Class<?> type, String field) {
		super(codePrefix, type, field);
	}

	public CustomDateFormat(String codePrefix, Class<?> type) {
		super(codePrefix, type);
	}

	@Override
	public SimpleDateFormat getFormat() {
		String dateFormatStr;
		String codeDateFormatStr = (getCodePrefix() != "" ? getCodePrefix()+".":"") + "date.format";
		Locale locale;

		locale = LocaleContextHolder.getLocale();

		dateFormatStr = messageSource.getMessage(codeDateFormatStr, null, locale);

		return new SimpleDateFormat(dateFormatStr);
	}

}
