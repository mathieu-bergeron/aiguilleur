package ca.ntro.core.values;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.identifyers.IdNtro;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class ObjectMapNtro implements ObjectMap {
	
	private Map<String, Object> objects = new HashMap<>();

	public Map<String, Object> getObjects() {
		return objects;
	}

	public void setObjects(Map<String, Object> objects) {
		this.objects = objects;
	}
	
	
	
	public ObjectMapNtro() {
	}


	@SuppressWarnings("unchecked")
	@Override
	public <O> O get(Class<O> _class, Id id) {
		return (O) getObjects().get(id.toKey().toString());
	}

	@Override
	public <O> O get(Class<O> _class, String id) {
		return get(_class, new IdNtro(id));
	}

	public Object get(Id id) {
		return getObjects().get(id.toKey().toString());
	}

	@Override
	public Object get(String id) {
		return get(new IdNtro(id));
	}

	public void registerObject(Id id, Object object) {
		getObjects().put(id.toKey().toString(), object);
	}

	public void registerObject(String id, Object object) {
		registerObject(new IdNtro(id), object);
	}

	@Override
	public boolean contains(Id id) {
		return contains(id.toKey().toString());
	}

	@Override
	public boolean contains(String id) {
		return getObjects().containsKey(id);
	}

	public void addAll(ObjectMap other) {
		other.ids().forEach(id -> {
			getObjects().put(id, other.get(id));
		});
	}

	@Override
	public Stream<String> ids() {
		return new StreamNtro<String>() {
			@Override
			public void forEach_(Visitor<String> visitor) throws Throwable {
				for(String key : objects.keySet()) {
					visitor.visit(key);
				}
			}
		};
	}

	@Override
	public Stream<Object> objects() {
		return new StreamNtro<Object>() {
			@Override
			public void forEach_(Visitor<Object> visitor) throws Throwable {
				for(Object object : objects.values()) {
					visitor.visit(object);
				}
			}
		};
	}

}
