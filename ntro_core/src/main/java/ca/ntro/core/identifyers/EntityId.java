package ca.ntro.core.identifyers;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Path;

public class EntityId extends IdPathNtro {
	
	protected EntityId() {
		super();
	}
	
	public EntityId(Path entityPath) {
		setEntityPath(entityPath);
	}
	
	public EntityId(String rawId) {
		if(rawId.contains(Path.FILENAME_SEPARATOR)
				&& rawId.contains(Path.PATH_SEPARATOR)) {
			
			Ntro.exceptions().throwException(new RuntimeException("rawId cannot contain both " + Path.FILENAME_SEPARATOR + " and " + Path.PATH_SEPARATOR));
		}
		
		if(rawId.contains(Path.FILENAME_SEPARATOR)) {
			
			setEntityPath(Path.fromFilename(rawId));
			
		}else {
			
			setEntityPath(Path.fromRawPath(rawId));
		}
	}

	@Override
	public String toString() {
		return getEntityPath().toRawPath();
	}

}
