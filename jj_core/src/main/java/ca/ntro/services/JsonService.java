package ca.ntro.services;


import ca.ntro.core.json.JsonDeserialization;
import ca.ntro.core.json.JsonSerialization;
import ca.ntro.jj_services.Tracer;

public abstract class JsonService {
	
	private Tracer tracer;
	private Logger logger;
	
	public JsonService(Tracer tracer, Logger logger) {
		tracer.call(this);

		this.tracer = tracer;
		this.logger = logger;
	}
	
	protected Tracer tracer() {
		return tracer;
	}

	protected Logger logger() {
		return logger;
	}

	private boolean prettyPrinting = true;
	
	public void setPrettyPrinting(boolean prettyPrinting) {
		tracer().call(this);

		this.prettyPrinting = prettyPrinting;
	}

	protected boolean ifPrettyPrinting() {
		tracer().call(this);

		return prettyPrinting;
	}
	
	public String toString(Object javaValue) {
		tracer().call(this);

		return writeJson(JsonSerialization.toJsonValue(javaValue));
	}

	public String toString(Object javaValue, boolean prettyPrinting) {
		tracer().call(this);
		
		return writeJson(JsonSerialization.toJsonValue(javaValue), prettyPrinting);
	}

	public <V extends Object> V fromString(Class<V> targetClass, String jsonString) {
		tracer().call(this);

		return JsonDeserialization.toJavaValue(targetClass, loadJson(jsonString));
	}

	protected abstract String writeJson(Object javaValue);
	protected abstract String writeJson(Object javaValue, boolean prettyPrinting);
	protected abstract Object loadJson(String jsonString);
}
