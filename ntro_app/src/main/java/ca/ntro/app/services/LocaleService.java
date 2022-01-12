package ca.ntro.app.services;

import ca.ntro.app.Locale;

public interface LocaleService {
	
	Locale newLocale(String language);
	Locale newLocale(String language, String country);
	Locale newLocale(String language, String country, String variant);
	
	Locale currentLocale();

}
