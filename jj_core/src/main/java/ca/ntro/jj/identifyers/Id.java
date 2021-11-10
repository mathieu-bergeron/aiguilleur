package ca.ntro.jj.identifyers;

public class Id {

	public static final String CATEGORY_ENTITY_SEPARATOR = "¤";
	
	private Path entityPath;

	protected Id() {
	}
	
	public Id(Path path) {
		setEntityPath(path);
	}

	public Id(String id) {
		setEntityPath(Path.fromSingleName(id));
	}

	protected void setEntityPath(Path entityPath) {
		this.entityPath = entityPath;
	}

	protected Path getEntityPath() {
		return this.entityPath;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof Id) {
			Id i = (Id) o;
			
			if(entityPath != null ? !entityPath.equals(i.entityPath) : i.entityPath != null) {
				return false;
			}

			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return getEntityPath().toFilename();
	}
}
