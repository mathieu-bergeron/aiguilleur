package ca.ntro.jj.services;


import ca.ntro.core.json.JsonDeserialization;
import ca.ntro.core.json.JsonSerialization;

public abstract class JsonService {
	
	private Logger logger;
	
	public JsonService(Logger logger) {
		logger.trace(this);

		this.logger = logger;
	}
	
	protected Logger logger() {
		return logger;
	}

	private boolean prettyPrinting = true;
	
	public void setPrettyPrinting(boolean prettyPrinting) {
		logger().trace(this);

		this.prettyPrinting = prettyPrinting;
	}

	protected boolean ifPrettyPrinting() {
		logger().trace(this);

		return prettyPrinting;
	}
	
	public String toString(Object javaValue) {
		logger().trace(this);

		return writeJson(JsonSerialization.toJsonValue(javaValue));
	}

	public String toString(Object javaValue, boolean prettyPrinting) {
		logger().trace(this);
		
		return writeJson(JsonSerialization.toJsonValue(javaValue), prettyPrinting);
	}

	public <V extends Object> V fromString(Class<V> targetClass, String jsonString) {
		logger().trace(this);

		return JsonDeserialization.toJavaValue(targetClass, loadJson(jsonString));
	}

	protected abstract String writeJson(Object javaValue);
	protected abstract String writeJson(Object javaValue, boolean prettyPrinting);
	protected abstract Object loadJson(String jsonString);
}
