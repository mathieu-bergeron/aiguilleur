package ca.test;

import java.util.Locale;

public class TestLocaleNtro implements TestLocale {

	@Override
	public Locale locale() {
		return new Locale("fr");
	}

}
