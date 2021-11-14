package ca.ntro.core.json;

public interface JsonSerializer {
	
	void fromJsonString(String JsonString);
	String toJsonString();

}
