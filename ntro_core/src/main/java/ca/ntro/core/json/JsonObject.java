package ca.ntro.core.json;

import java.util.Map;

public interface JsonObject extends JsonValue<Map<String,JsonValue<?>>>, Map<String,JsonValue<?>> {
	
	JsonObjectDiff diff(JsonObject other);

}
