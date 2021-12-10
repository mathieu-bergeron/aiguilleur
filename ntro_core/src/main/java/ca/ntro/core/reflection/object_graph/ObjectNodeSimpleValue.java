package ca.ntro.core.reflection.object_graph;

public interface ObjectNodeSimpleValue extends GenericObjectNode {

	boolean isNull();
	boolean isBoolean();
	boolean isNumber();
	boolean isString();
	
	boolean asBoolean();
	double  asNumber();
	String  asString();

}
