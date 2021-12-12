package ca.ntro.core.services;

import java.util.List;
import java.util.Map;

import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectGraphNull;
import ca.ntro.core.reflection.object_updates.ObjectUpdate;
import ca.ntro.core.stream.Stream;

public class ReflectionServiceNull implements ReflectionService {

	@Override
	public ObjectGraph objectGraph(Object o) {
		return new ObjectGraphNull();
	}

	@Override
	public boolean isList(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMap(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUserDefinedObject(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSimpleValue(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNull(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBoolean(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNumber(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isString(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean asBoolean(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String asString(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char asChar(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int asInt(Object objec) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long asLong(Object objec) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float asFloat(Object objec) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double asDouble(Object objec) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object asUserDefinedObject(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> V asUserDefinedObject(Object object, Class<V> _class) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> asList(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I> List<I> asList(Object object, Class<I> itemClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, ?> asMap(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> Map<String, V> asMap(Object object, Class<V> valueClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<ObjectUpdate> objectDiff(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return null;
	}

}
