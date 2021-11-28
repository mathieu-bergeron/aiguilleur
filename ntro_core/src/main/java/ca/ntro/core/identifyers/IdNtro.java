package ca.ntro.core.identifyers;

import ca.ntro.core.path.Filepath;
import ca.ntro.core.path.Path;

public class IdNtro implements Id {

	private Path entityPath;

	protected IdNtro() {
	}
	
	public IdNtro(String id) {
		setEntityPath(Path.fromSingleName(id));
	}

	public IdNtro(Path path) {
		setEntityPath(path);
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
		if(o instanceof IdNtro) {
			IdNtro i = (IdNtro) o;
			
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

	public Key toKey() {
		return new Key(getEntityPath().toRawPath());
	}

	public String toHtmlId() {
		return toFilepath().toHtmlId();
	}

	public Filepath toFilepath() {
		return Filepath.fromSingleName(getEntityPath().toFilename());
	}
}
