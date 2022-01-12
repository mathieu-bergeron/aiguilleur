package ca.ntro.app.services;

import ca.ntro.app.Locale;
import ca.ntro.app.LocaleJdk;

public class LocaleServiceJdk implements LocaleService {

	@Override
	public Locale newLocale(String language) {
		return new LocaleJdk(language);
	}

	@Override
	public Locale newLocale(String language, String country) {
		return new LocaleJdk(language, country);
	}

	@Override
	public Locale newLocale(String language, String country, String variant) {
		return new LocaleJdk(language, country, variant);
	}

	@Override
	public Locale currentLocale() {
		return new LocaleJdk();
	}

}
