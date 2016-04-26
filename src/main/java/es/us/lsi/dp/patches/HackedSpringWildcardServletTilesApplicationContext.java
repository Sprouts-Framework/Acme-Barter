/*
 * Copyright 2002-2012 the original author or authors. Licensed under the Apache
 * License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package es.us.lsi.dp.patches;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.tiles.request.ApplicationResource;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;

/**
 * Spring-specific subclass of the Tiles ServletApplicationContext.
 * 
 * @author Rossen Stoyanchev
 * @since 3.2
 */
public class HackedSpringWildcardServletTilesApplicationContext extends ServletApplicationContext {

	/**
	 * The pattern resolver.
	 */
	protected ResourcePatternResolver resolver;

	/**
	 * Constructor.
	 * 
	 * @param servletContext
	 *            The servlet context.
	 */
	public HackedSpringWildcardServletTilesApplicationContext(final ServletContext servletContext) {
		super(servletContext);
		resolver = new ServletContextResourcePatternResolver(servletContext);
	}

	@Override
	public ApplicationResource getResource(final String localePath) {
		ApplicationResource retValue = null;
		final Collection<ApplicationResource> urlSet = getResources(localePath);
		if (urlSet != null && !urlSet.isEmpty()) {
			retValue = urlSet.iterator().next();
		}
		return retValue;
	}

	@Override
	public ApplicationResource getResource(final ApplicationResource base, final Locale locale) {
		ApplicationResource retValue = null;
		final Collection<ApplicationResource> urlSet = getResources(base.getLocalePath(locale));
		if (urlSet != null && !urlSet.isEmpty()) {
			retValue = urlSet.iterator().next();
		}
		return retValue;
	}

	@Override
	public Collection<ApplicationResource> getResources(final String path) {
		Resource[] resources;
		try {
			resources = resolver.getResources(path);
		} catch (final IOException e) {
			return Collections.<ApplicationResource> emptyList();
		}
		final Collection<ApplicationResource> resourceList = new ArrayList<ApplicationResource>();
		if (resources != null && resources.length > 0) {
			for (final Resource resource : resources) {
				URL url;
				try {
					url = resource.getURL();
					resourceList.add(new HackedURLApplicationResource(url.toExternalForm(), url));
				} catch (final IOException e) {
					// shouldn't happen with the kind of resources we're using
					throw new IllegalArgumentException("no URL for " + resource.toString(), e);
				}
			}
		}
		return resourceList;
	}

}
