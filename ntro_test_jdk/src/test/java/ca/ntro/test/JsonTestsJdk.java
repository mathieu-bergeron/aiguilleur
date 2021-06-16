package ca.ntro.test;

import org.junit.BeforeClass;

import ca.ntro.test.json.JsonTests;

public class JsonTestsJdk extends JsonTests {

	@BeforeClass
	public static void initializeNtro() {
		NtroJdk.defaultInitializationTask().execute();

		JsonTests.registerSerializableClasses();
	}

}
