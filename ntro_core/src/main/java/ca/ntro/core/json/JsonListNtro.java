package ca.ntro.core.json;

import java.util.List;

public abstract class JsonListNtro implements JsonList {

	@Override
	public List<JsonValue<?>> javaValue() {
		return this;
	}

}
