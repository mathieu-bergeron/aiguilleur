package ca.ntro.core.identifyers;

import ca.ntro.core.path.Filepath;
import ca.ntro.core.path.Path;

public class IdPathNtro implements Id {

	private Path entityPath;

	protected IdPathNtro() {
	}
	
	public IdPathNtro(String id) {
		setEntityPath(Path.fromSingleName(id));
	}

	public IdPathNtro(Path path) {
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
		if(o instanceof IdPathNtro) {
			IdPathNtro i = (IdPathNtro) o;
			
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
