package es.us.lsi.dp.formats.contracts;

import java.text.Format;

public interface CustomizeFormat {

	public String getCodePrefix();

	public String getField();

	public Class<?> getType();

	public Format getFormat();
}
