/*
 * MessageSource.java Copyright (C) 2013 Universidad de Sevilla The use of this
 * project is hereby constrained to the conditions of the TDG Licence, a copy of
 * which you may download from http://www.tdg-seville.info/License.html
 */

package es.us.lsi.dp.patches;

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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;

public class HackedReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

	private static Log log = LogFactory.getLog(HackedReloadableResourceBundleMessageSource.class);

	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(final ResourceLoader resourceLoader) {
		this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
		super.setResourceLoader(resourceLoader);
	}

	@Override
	protected List<String> calculateFilenamesForLocale(final String basename, final Locale locale) {
		List<String> result;
		PathMatchingResourcePatternResolver resolver;
		List<String> basePatterns;
		String extension, rootPath;

		result = new ArrayList<String>();

		resolver = new PathMatchingResourcePatternResolver(resourceLoader);
		basePatterns = findBasenamePatterns(basename, locale);
		extension = getExtension(basename);
		rootPath = findRootResourcePath();
		for (final String basePattern : basePatterns) {
			List<String> filenames;

			filenames = findResourceFilenames(basePattern, rootPath, extension, resolver);
			result.addAll(filenames);
		}

		return result;
	}

	protected List<String> findBasenamePatterns(final String basename, final Locale locale) {
		List<String> result;
		String language, country, variant;
		String basenamePathNoExtension;
		StringBuilder basenameBuffer;

		basenamePathNoExtension = getPathNoExtension(basename);
		basenameBuffer = new StringBuilder(basenamePathNoExtension);

		result = new ArrayList<String>(3);
		result.add(0, basenameBuffer.toString());

		language = locale.getLanguage();
		if (language.length() > 0) {
			basenameBuffer.append('_');
			basenameBuffer.append(language);
			result.add(0, basenameBuffer.toString());
		}

		country = locale.getCountry();
		if (country.length() > 0) {
			basenameBuffer.append('_');
			basenameBuffer.append(country);
			result.add(0, basenameBuffer.toString());
		}

		variant = locale.getVariant();
		if (variant.length() > 0 && (language.length() > 0 || country.length() > 0)) {
			basenameBuffer.append('_');
			basenameBuffer.append(variant);
			result.add(0, basenameBuffer.toString());
		}

		return result;
	}

	protected String getPathNoExtension(final String basename) {
		String result;

		result = StringUtils.substringBeforeLast(basename, ".");

		return result;
	}

	protected String getExtension(final String basename) {
		String result;
		int dotPosition;

		dotPosition = StringUtils.lastIndexOf(basename, '.');
		if (dotPosition == -1)
			result = ".properties";
		else
			result = StringUtils.substring(basename, dotPosition, basename.length());

		return result;
	}

	protected String findRootResourcePath() {
		Resource root;
		String result;

		result = null;
		try {
			root = resourceLoader.getResource("/");
			result = root.getFile().getCanonicalPath();
		} catch (final IOException oops) {
			log.fatal(String.format("Cannot locate root resource folder: %s", oops.getLocalizedMessage()));
			Assert.isTrue(false, "Does not actually exist the root resource folder?");
		}

		return result;
	}

	private List<String> findResourceFilenames(final String basePattern, final String rootPath, final String extension,
			final PathMatchingResourcePatternResolver resolver) {
		List<String> result;
		Resource[] resources;
		String fullName;

		result = new ArrayList<String>();
		try {
			fullName = String.format("%s%s", basePattern, extension);
			resources = resolver.getResources(fullName);
			for (final Resource resource : resources) {
				String relativeName;

				relativeName = resource.getFile().getCanonicalPath();
				relativeName = StringUtils.substringAfter(relativeName, rootPath);
				relativeName = StringUtils.substringBeforeLast(relativeName, extension);
				relativeName = StringUtils.replace(relativeName, File.separator, "/");
				result.add(relativeName);
			}
		} catch (final Throwable oops) {
			log.debug(String.format("Cannot load `%s': %s", basePattern, oops.getLocalizedMessage()));
		}

		return result;
	}

}
