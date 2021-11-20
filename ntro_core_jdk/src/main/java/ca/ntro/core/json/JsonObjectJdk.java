package ca.ntro.core.json;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;

public class JsonObjectJdk extends JsonObjectNtro implements JsonObject {
	
	private BSONObject bsonObject = new BasicBSONObject();

	@Override
	public int size() {
		return bsonObject.toMap().size();
	}

	@Override
	public boolean isEmpty() {
		return bsonObject.toMap().isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return bsonObject.toMap().containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return bsonObject.toMap().containsValue(value);
	}

	@Override
	public JsonValue<?> get(Object key) {
		return (JsonValue<?>) bsonObject.get((String) key);
	}

	@Override
	public JsonValue<?> put(String key, JsonValue<?> value) {
		return (JsonValue<?>) bsonObject.put(key, value);
	}

	@Override
	public JsonValue<?> remove(Object key) {
		return (JsonValue<?>) bsonObject.removeField((String) key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putAll(Map<? extends String, ? extends JsonValue<?>> m) {
		bsonObject.toMap().putAll(m);
	}

	@Override
	public void clear() {
		bsonObject.toMap().clear();
	}

	@Override
	public Set<String> keySet() {
		return bsonObject.keySet();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<JsonValue<?>> values() {
		return bsonObject.toMap().values();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Entry<String, JsonValue<?>>> entrySet() {
		return bsonObject.toMap().entrySet();
	}
}
