package ca.ntro.core.json;

import java.util.Map;

public abstract class JsonObjectNtro implements JsonObject {

	@Override
	public JsonObjectDiff diff(JsonObject other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, JsonValue<?>> javaValue() {
		return this;
	}

}
