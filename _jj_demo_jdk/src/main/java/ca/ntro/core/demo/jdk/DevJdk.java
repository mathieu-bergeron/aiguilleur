package ca.ntro.core.demo.jdk;

import ca.ntro.core.demo.Demo;
import ca.ntro.core.static_imports.NtroJdk;

public class DevJdk {

	public static void main(String[] args) throws Throwable {

		NtroJdk.initializer().executeBlocking();

		Demo.main();
	}
}
