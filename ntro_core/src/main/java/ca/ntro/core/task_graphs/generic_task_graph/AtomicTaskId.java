package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.identifyers.Key;

public class AtomicTaskId implements Id {
	
	private Key key;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public static AtomicTaskId fromKey(Key key) {
		AtomicTaskId id = new AtomicTaskId();

		id.setKey(key);
		
		return id;
	}

	public static AtomicTaskId fromKey(String key) {
		return fromKey(new Key(key));
	}

	@Override
	public Key toKey() {
		return getKey();
	}

}
