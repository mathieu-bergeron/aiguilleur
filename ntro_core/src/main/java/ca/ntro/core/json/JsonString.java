package ca.ntro.core.json;

public class JsonString {
	
	public static String toJsonString(String rawString) {
		StringBuilder builder = new StringBuilder();
		builder.append("\"");
		builder.append(rawString);
		builder.append("\"");

		return builder.toString();
	}

	public static String toRawString(String jsonString) {
		return jsonString.substring(1, jsonString.length());
	}

}
