package es.us.lsi.dp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

	@Override
	public void setApplicationContext(final ApplicationContext ctx) throws BeansException {
		AppContext.setApplicationContext(ctx);
	}

}
