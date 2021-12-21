package ca.ntro.core.values;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.identifyers.IdNtro;

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

	public void registerObject(Id id, Object object) {
		registerObject(id.toKey().toString(), object);
	}

	public void registerObject(String id, Object object) {
		getObjects().put(id, object);
	}

	@Override
	public boolean contains(Id id) {
		return contains(id.toKey().toString());
	}

	@Override
	public boolean contains(String id) {
		return getObjects().containsKey(id);
	}

	@Override
	public void merge(ObjectMap other) {
		// TODO Auto-generated method stub
		
	}
	


}
