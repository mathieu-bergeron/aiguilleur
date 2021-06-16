package ca.ntro.test.json;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ca.ntro.assertions.Factory.that;

public class JsonTests {

	public static void registerSerializableClasses() {

	}

	@Test
	private void simpleStringSerialization() {
		String javaString = "test";

		String jsonString = Ntro.jsonService().toString(javaString);
		
		Ntro.verify(that(jsonString.equals("\"" + javaString + "\"")).isTrue());
	}

}
