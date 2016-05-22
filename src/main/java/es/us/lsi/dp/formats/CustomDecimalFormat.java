package es.us.lsi.dp.formats;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public class CustomDecimalFormat extends CustomFormat {

	public CustomDecimalFormat(String codePrefix, Class<?> type, String field) {
		super(codePrefix, type, field);
	}

	public CustomDecimalFormat(String codePrefix, Class<?> type) {
		super(codePrefix, type);
	}

	@Override
	public DecimalFormat getFormat() {
		String currencyDecimalMark;
		String currencyGroupingSeparator;
		String numberFormat;
		Locale locale;

		String codeCurrencyDecimalMark = (getCodePrefix() != "" ? getCodePrefix()+".":"") + "decimal-mark";
		String codeCurrencyGroupingSeparator = (getCodePrefix() != "" ? getCodePrefix()+".":"") + "grouping-separator";
		String codeNumberFormat = (getCodePrefix() != "" ? getCodePrefix()+".":"") + "number-format";

		locale = LocaleContextHolder.getLocale();

		currencyDecimalMark = messageSource.getMessage(codeCurrencyDecimalMark, null, locale);
		currencyGroupingSeparator = messageSource.getMessage(codeCurrencyGroupingSeparator, null, locale);
		numberFormat = messageSource.getMessage(codeNumberFormat, null, locale);

		DecimalFormatSymbols decimalFormatSymbols;
		decimalFormatSymbols = DecimalFormatSymbols.getInstance();
		decimalFormatSymbols.setDecimalSeparator(currencyDecimalMark.charAt(0));
		decimalFormatSymbols.setGroupingSeparator(currencyGroupingSeparator.charAt(0));

		DecimalFormat decimalFormat = new DecimalFormat(numberFormat, decimalFormatSymbols);

		return decimalFormat;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Number> getType() {
		return (Class<? extends Number>) super.getType();
	}
}
