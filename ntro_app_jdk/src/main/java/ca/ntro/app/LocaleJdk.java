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
		Locale currentLocale = Locale.getDefault();
		setLocale(new Locale(currentLocale.getLanguage(), currentLocale.getCountry(), currentLocale.getVariant()));
	}

	
	public LocaleJdk(String language) {
		Locale currentLocale = Locale.getDefault();
		setLocale(new Locale(language, currentLocale.getCountry(), currentLocale.getVariant()));
	}

	public LocaleJdk(String language, String country) {
		Locale currentLocale = Locale.getDefault();
		setLocale(new Locale(language, country, currentLocale.getVariant()));
	}

	public LocaleJdk(String language, String country, String variant) {
		setLocale(new Locale(language, country, variant));
	}
	
	

	@Override
	public String toString() {
		return getLocale().toString();
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		return toString().equals(o.toString());
	}
	
	

}
