package ca.ntro.test.json;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ca.ntro.assertions.Factory.that;

public class JsonTests {
	
	private static JsonService json;
	private static AssertionService _assert;
	
	public static void initialize(JsonService json, AssertionService _assert) {
		JsonTests.json = json;
		JsonTests._assert = _assert;
	}

	public static void registerSerializableClasses() {

	}

	@Test
	private void simpleStringSerialization() {
		String javaString = "test";

		String jsonString = json.toString(javaString);
		
		_assert.verify(that(jsonString.equals("\"" + javaString + "\"")).isTrue());
	}
}
