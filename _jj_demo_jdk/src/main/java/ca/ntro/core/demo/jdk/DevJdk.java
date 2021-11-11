package ca.ntro.core.demo.jdk;

import ca.jj.demo.core.Demo;
import ca.ntro.core.static_imports.JjJdk;

public class DevJdk {

	public static void main(String[] args) throws Throwable {

		JjJdk.initializer().executeBlocking();

		Demo.main();
	}
}
