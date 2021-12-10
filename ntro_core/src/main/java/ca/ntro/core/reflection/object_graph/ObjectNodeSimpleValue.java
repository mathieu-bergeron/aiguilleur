package ca.ntro.core.reflection.object_graph;

public interface ObjectNodeSimpleValue extends ObjectNode {

	boolean isNull();
	boolean isBoolean();
	boolean isNumber();
	boolean isString();
	
	boolean asBoolean();
	double  asDouble();
	String  asString();

}
