package ca.ntro.jj.identifyers;

import ca.ntro.jj.values.Path;

public class Id {
	
	private Path categoryPath = new Path();
	private Path entityPath = new Path();
	
	private Id() {
	}
	
	public Id(String entityId) {
		this.entityPath = Path.fromRawPath(entityId);
	}
	
	private void setCategoryPath(Path categoryPath) {
		this.categoryPath = categoryPath;
	}

	private void setEntityPath(Path entityPath) {
		this.entityPath = entityPath;
	}
	
	
	public static Id fromKey(String key) {
		Id id = new Id();
		
		return id;
	}

	public static Id fromFilepath(String filepath) {
		Id id = new Id();
		
		return id;
	}
	
	/**
 	 * e.g. models¬QueueModel¤alice¬2021   where categoryPath is models¬QueueModel and entityPath is alice¬2021
	 */
	public String toKey() {
		StringBuilder builder = new StringBuilder();
		
		return builder.toString();
	}     

	/**
	 * e.g. /models/QueueModel/alice¬2021  where categoryPath is /models/QueueModel and entityPath is alice¬2021
	 */
	
	public String toFilepath() {
		StringBuilder builder = new StringBuilder();
		
		return builder.toString();
	}
}
