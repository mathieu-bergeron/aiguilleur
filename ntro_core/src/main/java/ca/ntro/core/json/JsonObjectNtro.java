package ca.ntro.core.json;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

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
