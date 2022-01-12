package ca.ntro.app;

import java.util.Locale;

public class LocaleJdk implements ca.ntro.app.Locale {
	
	private Locale locale;

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	
	public LocaleJdk() {
		setLocale(Locale.getDefault());
	}

	
	public LocaleJdk(String language) {
		setLocale(new Locale(language));
	}

	public LocaleJdk(String language, String country) {
		setLocale(new Locale(language, country));
	}

	public LocaleJdk(String language, String country, String variant) {
		setLocale(new Locale(language, country, variant));
	}
	
	

	@Override
	public String toString() {
		return getLocale().toString();
	}
	
	

}
