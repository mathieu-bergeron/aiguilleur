package ca.ntro.app.services;

import ca.ntro.app.Locale;
import ca.ntro.app.LocaleNull;

public class LocaleServiceNull implements LocaleService {

	@Override
	public Locale newLocale(String language) {
		return new LocaleNull();
	}

	@Override
	public Locale newLocale(String language, String country) {
		return new LocaleNull();
	}

	@Override
	public Locale newLocale(String language, String country, String variant) {
		return new LocaleNull();
	}

	@Override
	public Locale currentLocale() {
		return new LocaleNull();
	}

}
