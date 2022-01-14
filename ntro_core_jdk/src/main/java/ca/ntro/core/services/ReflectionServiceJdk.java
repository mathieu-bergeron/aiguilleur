package ca.ntro.core.services;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.object_diff.ObjectDiff;
import ca.ntro.core.object_diff.updates.ObjectUpdate;
import ca.ntro.core.reflection.ObjectGraphJdk;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.stream.Stream;

public class ReflectionServiceJdk extends ReflectionServiceNtro { 

	@Override
	public ObjectGraph objectGraph(Object o) {
		return new ObjectGraphJdk(o);
	}

	@Override
	public ObjectGraph objectGraph(Object o, String graphName) {
		return new ObjectGraphJdk(o, graphName);
	}

	@Override
	public char asChar(Object object) {
		if(object == null) return 0;
		
		double real = asDouble(object);
		long rounded = Math.round(real);
		
		return (char) rounded;
	}

	@Override
	public int asInt(Object object) {
		if(object == null) return 0;

		double real = asDouble(object);
		long rounded = Math.round(real);
		
		return (int) rounded;
	}

	@Override
	public long asLong(Object object) {
		if(object == null) return 0;

		double real = asDouble(object);
		long rounded = Math.round(real);
		
		return rounded;
	}

	@Override
	public float asFloat(Object object) {
		if(object == null) return 0;

		return Float.parseFloat(object.toString());
	}

	@Override
	public double asDouble(Object object) {
		if(object == null) return 0;

		return Double.parseDouble(object.toString());
	}

	@Override
	public Stream<ObjectUpdate> objectDiff(Object o1, Object o2) {
		return ObjectDiff.diff(o1, o2);
	}

	@Override
	public boolean isList(Object object) {
		return object instanceof List 
				|| (object != null && object.getClass().isArray());
	}

	@Override
	public List<Object> asList(Object object) {
		List<Object> list;
		
		if(object != null 
				&& object.getClass().isArray()) {

			list = new ArrayList<>();
			
			for(int i = 0; i < Array.getLength(object); i++) {
				list.add(Array.get(object, i));
			}
			
		}else {
			
			list = (List<Object>) object;
		}
		
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <I> List<I> asList(Object object, Class<I> itemClass) {
		List<I> list;
		
		if(object != null
				&& object.getClass().isArray()) {

			list = new ArrayList<>();
			
			for(int i = 0; i < Array.getLength(object); i++) {
				list.add((I) Array.get(object, i));
			}
			
		}else {
			
			list = (List<I>) object;
		}
		
		return list;
	}

	@Override
	public String simpleName(Class<?> _class) {
		return _class.getSimpleName();
	}

}
