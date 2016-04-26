package es.us.lsi.dp.formats;

import org.springframework.context.MessageSource;

import es.us.lsi.dp.formats.contracts.CustomizeFormat;

public abstract class CustomFormat implements CustomizeFormat {

	protected String codePrefix;
	protected String field;
	protected Class<?> type;
	protected MessageSource messageSource;

	public CustomFormat(String codePrefix, Class<?> type, String field) {
		super();
		this.codePrefix = codePrefix;
		this.field = field;
		this.type = type;
	}

	public CustomFormat(String codePrefix, Class<?> type) {
		super();
		this.codePrefix = codePrefix;
		this.field = "";
		this.type = type;
	}

	public String getCodePrefix() {
		return codePrefix;
	}

	public String getField() {
		return field;
	}

	public Class<?> getType() {
		return type;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
