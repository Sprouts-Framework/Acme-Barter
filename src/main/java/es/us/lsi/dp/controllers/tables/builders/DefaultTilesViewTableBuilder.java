package es.us.lsi.dp.controllers.tables.builders;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.tiles.Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import es.us.lsi.dp.controllers.tables.Column;
import es.us.lsi.dp.controllers.tables.Table;
import es.us.lsi.dp.controllers.tables.builders.contracts.TableBuilder;
import es.us.lsi.dp.domain.DomainEntity;
import es.us.lsi.dp.services.SignInService;
import es.us.lsi.dp.utilities.TilesUtils;

@Primary
@Component
public class DefaultTilesViewTableBuilder implements TableBuilder {

	// Attributes --------------------------------------------------------------

	@Autowired
	private MessageSource messageSource;

	// Constants ---------------------------------------------------------------

	private static final String PATH = "path";

	private static final String REGEX = "(\\w+)\\s*=\\s*\"(((\\w|\\/|\\+|\\.)+.?)+)\"";

	private static final String TABLE_OPENING_TAG = ":data-table";
	private static final String TABLE_CLOSING_TAG = ":data-table>";

	private static final String COLUMN_OPENING_TAG = ":data-column";
	private static final String COLUMN_CLOSING_TAG = "/>";

	private static final String SECURITY_AUTHORIZE_OPENING_TAG = "security:authorize";
	private static final String SECURITY_AUTHORIZE_CLOSING_TAG = "</security:authorize>";

	private static final String ARRAY_DELIMITER = ",";

	// Public methods ----------------------------------------------------------

	@Override
	public Table build(final String viewName, final String tableIndex, final HttpServletRequest request, final HttpServletResponse response) {
		List<Column> columns;
		Definition definition;
		String listJspPath;
		String[] unparsedColumns;

		definition = TilesUtils.getTemplateDefinition(viewName, request, response);

		listJspPath = TilesUtils.getJspPath(definition, request, response);

		unparsedColumns = getUnparsedColumns(listJspPath, tableIndex);

		columns = parseColumns(unparsedColumns);

		return new Table(columns);
	}

	@Override
	public String[][] getRows(final List<? extends DomainEntity> entities, final Table table, String locale) {
		String[][] result;

		Column column;
		int iDimension;
		int jDimension;
		int entityId;

		iDimension = entities.size();
		jDimension = table.size() + 1;

		result = new String[iDimension][jDimension];

		for (int i = 0; i < iDimension; i++) {
			final DomainEntity entity = entities.get(i);

			for (int j = 0; j < jDimension - 1; j++) {
				column = table.getColumn(j);
				result[i][j] = resolveColumnValue(column, entity);

				if (column.getToShow() != null) {
					String aux = result[i][j];
					result[i][j] = column.getToShow();
					String res = StringUtils.replace(result[i][j], "+PATH", aux);
					result[i][j] = res;
				}

				if (column.getFormat() != null && column.getFormat().equals("image")) {
					String width = null;
					String height = null;
					if (column.getImgSize() != null) {
						String[] aux = column.getImgSize().split("x");
						width = aux[0];
						height = aux[1];
					}
					if (column.getOutFormat() != null) {

						String str = StringUtils.replace(column.getOutFormat(), "+PATH", resolveColumnValue(column, entity));
						if (width != null && height != null)
							result[i][j] = "<img src='" + str + "' class='img-responsive' width='" + width + "px' height='" + height + "px'/>";
						else
							result[i][j] = "<img src='" + str + "' class='img-responsive'/>";

					} else {
						if (width != null && height != null)
							result[i][j] = "<img src='" + result[i][j] + "' class='img-responsive' width='" + width + "px' height='" + height + "px'/>";
						else
							result[i][j] = "<img src='" + result[i][j] + "' class='img-responsive'/>";
					}

				} else if (column.getFormat() != null && column.getFormat().equals("url")) {
					if (column.getOutFormat() != null) {
						String st = StringUtils.replace(column.getOutFormat(), "+PATH", resolveColumnValue(column, entity));
						result[i][j] = "<a href='" + result[i][j] + "'>" + st + "</a>";
					} else
						result[i][j] = "<a href='" + result[i][j] + "'>" + result[i][j] + "</a>";
				} else if (column.getFormat() != null && column.getFormat().equals("date")) {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
					Date dateAux = null;
					try {
						dateAux = sdf.parse(result[i][j]);
					} catch (Throwable oops) {
						throw new RuntimeException(oops);
					}

					String dateFormatStr;
					Locale local = LocaleContextHolder.getLocale();

					if (column.getOutFormat() != null)
						dateFormatStr = messageSource.getMessage(column.getOutFormat(), null, local);
					else
						dateFormatStr = messageSource.getMessage("date.format", null, local);

					SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);

					result[i][j] = dateFormat.format(dateAux);
				} else if (column.getFormat() != null && column.getFormat().equals("currency")) {
					String decimalMark;
					String groupingSeparator;
					String prefix;
					String suffix;
					Locale local;
					local = LocaleContextHolder.getLocale();

					decimalMark = messageSource.getMessage("decimal-mark", null, local);
					groupingSeparator = messageSource.getMessage("grouping-separator", null, local);
					prefix = messageSource.getMessage("currency.prefix", null, local);
					suffix = messageSource.getMessage("currency.suffix", null, local);

					DecimalFormatSymbols decimalFormatSymbols;
					decimalFormatSymbols = DecimalFormatSymbols.getInstance();
					decimalFormatSymbols.setDecimalSeparator(decimalMark.charAt(0));
					decimalFormatSymbols.setGroupingSeparator(groupingSeparator.charAt(0));

					DecimalFormat numberFormat = new DecimalFormat("###,###.##", decimalFormatSymbols);
					numberFormat.setPositivePrefix(prefix);
					numberFormat.setPositiveSuffix(suffix);

					try {
						result[i][j] = numberFormat.format(Double.valueOf(result[i][j]));
					} catch (Throwable oops) {
						throw new RuntimeException(oops);
					}
				}
			}

			entityId = entity.getId();
			result[i][jDimension - 1] = String.valueOf(entityId);
		}

		return result;
	}

	// Private methods ---------------------------------------------------------

	private String resolveColumnValue(final Column column, final DomainEntity entity) {
		String result;
		Object value;
		String fieldName;

		ExpressionParser parser;
		Expression exp;

		parser = new SpelExpressionParser();

		fieldName = column.getField();

		exp = parser.parseExpression(fieldName);

		try {
			value = exp.getValue(entity);
			// If the resulting attribute value is null, get the message to
			// display
			result = value != null ? value.toString() : getNullMessage(column);
			if (result.equals("true")) {
				result = getTrueMessage(column);
			} else if (result.equals("false")) {
				result = getFalseMessage(column);
			}
		} catch (EvaluationException e) {
			// If some precedent attribute is null, get the message to display
			result = getNullMessage(column);
		}

		return result;
	}

	private String getNullMessage(final Column column) {
		String result;
		String nullCode;
		Locale locale;

		nullCode = column.getNullCode();
		locale = LocaleContextHolder.getLocale();

		result = messageSource.getMessage(nullCode, null, locale);

		return result;
	}

	private String getTrueMessage(final Column column) {
		String result;
		String trueCode;
		Locale locale;

		trueCode = column.getTrueCode();
		locale = LocaleContextHolder.getLocale();

		result = messageSource.getMessage(trueCode, null, locale);

		return result;
	}

	private String getFalseMessage(final Column column) {
		String result;
		String falseCode;
		Locale locale;

		falseCode = column.getFalseCode();
		locale = LocaleContextHolder.getLocale();

		result = messageSource.getMessage(falseCode, null, locale);

		return result;
	}

	private List<Column> parseColumns(final String[] unparsedColumns) {
		List<Column> result;

		Map<String, String> attributes;

		Column column;

		result = new ArrayList<>();

		for (final String unparsedColumn : unparsedColumns) {
			attributes = buildAttributesMap(unparsedColumn);

			column = new Column(attributes.get(PATH));

			setAttributes(column, attributes);

			result.add(column);
		}

		return result;
	}

	private String[] getUnparsedColumns(final String path, final String tableIndex) {
		String[] result;

		byte[] rawJsp;
		String stringJsp;

		result = null;

		try {
			String[] resultAux;
			String[] securities;
			String[] roles;
			String[] datas;

			rawJsp = FileCopyUtils.copyToByteArray(new FileInputStream(path));

			stringJsp = new String(rawJsp, StandardCharsets.ISO_8859_1);

			result = StringUtils.substringsBetween(stringJsp, TABLE_OPENING_TAG, TABLE_CLOSING_TAG);
			securities = StringUtils.substringsBetween(result[Integer.valueOf(tableIndex)], SECURITY_AUTHORIZE_OPENING_TAG, SECURITY_AUTHORIZE_CLOSING_TAG);
			roles = StringUtils.substringsBetween(result[Integer.valueOf(tableIndex)], "hasRole('", "')");
			resultAux = StringUtils.substringsBetween(result[Integer.valueOf(tableIndex)], COLUMN_OPENING_TAG, COLUMN_CLOSING_TAG);

			// Get rid of the columns that aren't allowed to be seen by someone
			// who isn't allowed
			if (securities != null)
				for (int i = 0; i < securities.length; i++) {
					if (!SignInService.checkAuthority(roles[i]) && securities[i].contains(COLUMN_OPENING_TAG)) {
						datas = StringUtils.substringsBetween(securities[i], COLUMN_OPENING_TAG, COLUMN_CLOSING_TAG);
						resultAux = ArrayUtils.removeElements(resultAux, datas);
					}
				}

			result = resultAux;

		} catch (Throwable e) {
			throw new RuntimeException();
		}

		return result;
	}

	private Map<String, String> buildAttributesMap(final String unparsedColumn) {
		Map<String, String> result;

		Pattern pattern;
		Matcher matcher;

		String key;
		String value;

		result = new HashMap<>();

		pattern = Pattern.compile(REGEX);

		matcher = pattern.matcher(unparsedColumn);

		while (matcher.find()) {
			key = matcher.group(1);
			value = matcher.group(2);

			result.put(key, value);
		}

		return result;
	}

	private void setAttributes(final Column column, final Map<String, String> attributes) {
		// path attribute already processed
		try {
			attributes.remove(PATH);
			for (final Entry<String, String> pair : attributes.entrySet()) {
				invokeMethod(column, pair);
			}
		} catch (Throwable e) {
			throw new RuntimeException();
		}
	}

	private void invokeMethod(final Column column, final Entry<String, String> entry) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<? extends Column> cls;
		String methodName;
		String methodArgs;
		Method method;

		boolean isArray;
		cls = column.getClass();
		methodName = entry.getKey();
		methodArgs = entry.getValue();

		isArray = methodArgs.contains(ARRAY_DELIMITER);

		if (isArray) {
			String[] args;
			args = buildArray(methodArgs);
			method = cls.getMethod(methodName, String[].class);
			method.invoke(column, (Object) args);
		} else {
			method = cls.getMethod(methodName, String.class);
			method.invoke(column, methodArgs);
		}
	}

	private String[] buildArray(final String args) {
		String[] result;

		List<String> fieldsList;

		fieldsList = Arrays.asList(args.split(ARRAY_DELIMITER));

		CollectionUtils.transform(fieldsList, new Transformer() {

			@Override
			public Object transform(final Object field) {
				return ((String) field).trim();
			}
		});

		result = (String[]) fieldsList.toArray();

		return result;
	}

}
