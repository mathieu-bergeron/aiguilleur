package ca.ntro.core.services;

import java.util.List;
import java.util.Map;

import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_updates.ObjectUpdate;
import ca.ntro.core.stream.Stream;

public interface ReflectionService {
	
	ObjectGraph objectGraph(Object o);
	
	Stream<ObjectUpdate> objectDiff(Object o1, Object o2);

	boolean isList(Object object);
	boolean isMap(Object object);
	boolean isUserDefinedObject(Object object);

	boolean isSimpleValue(Object object);

	boolean isNull(Object object);
	boolean isBoolean(Object object);
	boolean isNumber(Object object);
	boolean isString(Object object);

	boolean asBoolean(Object object);

	String asString(Object object);

	char    asChar(Object object);
	int     asInt(Object object);
	long    asLong(Object object);
	float   asFloat(Object object);
	double  asDouble(Object object);

	Object asUserDefinedObject(Object object);
	<V> V asUserDefinedObject(Object object, Class<V> _class);
	
	List<Object> asList(Object object);
	<I> List<I> asList(Object object, Class<I> itemClass);

	Map<String,?> asMap(Object object);
	<V> Map<String,V> asMap(Object object, Class<V> valueClass);

}
