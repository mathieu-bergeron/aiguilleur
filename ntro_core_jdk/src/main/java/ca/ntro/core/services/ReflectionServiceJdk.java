package ca.ntro.core.services;


import ca.ntro.core.reflection.ObjectGraphJdk;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_updates.ObjectUpdate;
import ca.ntro.core.stream.Stream;

public class ReflectionServiceJdk extends ReflectionServiceNtro { 

	@Override
	public ObjectGraph objectGraph(Object o) {
		return new ObjectGraphJdk(o);
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
		// TODO Auto-generated method stub
		return null;
	}
}
