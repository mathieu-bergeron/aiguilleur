package ca.ntro.core.reflection.object_graph;

public interface ObjectNodeSimpleValue extends GenericObjectNode {

	boolean isNull();
	boolean isBoolean();
	boolean isNumber();
	boolean isString();
	
	boolean asBoolean();
	String  asString();

	char    asChar();
	int     asInt();
	long    asLong();
	float   asFloat();
	double  asDouble();

}
