package es.us.lsi.dp.formats;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public class CustomCurrencyFormat extends CustomFormat {

	public CustomCurrencyFormat(String codePrefix, Class<?> type, String field) {
		super(codePrefix, type, field);
	}

	public CustomCurrencyFormat(String codePrefix, Class<?> type) {
		super(codePrefix, type);
	}

	@Override
	public DecimalFormat getFormat() {
		String currencyDecimalMark;
		String currencyGroupingSeparator;
		String numberFormat;
		String prefix;
		String suffix;
		Locale locale;

		String codeCurrencyDecimalMark = (getCodePrefix() != "" ? getCodePrefix()+".":"")+ "currency.decimal-mark";
		String codeCurrencyGroupingSeparator = (getCodePrefix() != "" ? getCodePrefix()+".":"") + "currency.grouping-separator";
		String codeNumberFormat = (getCodePrefix() != "" ? getCodePrefix()+".":"") + "currency.number-format";
		String codePrefix = (getCodePrefix() != "" ? getCodePrefix()+".":"") + "currency.prefix";
		String codeSuffix = (getCodePrefix() != "" ? getCodePrefix()+".":"") + "currency.suffix";

		locale = LocaleContextHolder.getLocale();

		currencyDecimalMark = messageSource.getMessage(codeCurrencyDecimalMark, null, locale);
		currencyGroupingSeparator = messageSource.getMessage(codeCurrencyGroupingSeparator, null, locale);
		numberFormat = messageSource.getMessage(codeNumberFormat, null, locale);
		prefix = messageSource.getMessage(codePrefix, null, locale);
		suffix = messageSource.getMessage(codeSuffix, null, locale);

		DecimalFormatSymbols decimalFormatSymbols;
		decimalFormatSymbols = DecimalFormatSymbols.getInstance();
		decimalFormatSymbols.setDecimalSeparator(currencyDecimalMark.charAt(0));
		decimalFormatSymbols.setGroupingSeparator(currencyGroupingSeparator.charAt(0));

		DecimalFormat decimalFormat = new DecimalFormat(numberFormat, decimalFormatSymbols);
		decimalFormat.setPositivePrefix(prefix);
		decimalFormat.setPositiveSuffix(suffix);

		return decimalFormat;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Number> getType() {
		return (Class<? extends Number>) super.getType();
	}

}
