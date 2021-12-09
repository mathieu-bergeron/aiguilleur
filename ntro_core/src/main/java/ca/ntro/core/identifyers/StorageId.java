package ca.ntro.core.identifyers;

import ca.ntro.core.path.Filepath;
import ca.ntro.core.path.Path;

public class StorageId extends EntityId {
	
	private Path categoryPath = Path.emptyPath();
	
	protected void setCategoryPath(Path categoryPath) {
		this.categoryPath = categoryPath;
	}

	protected Path getCategoryPath() {
		return categoryPath;
	}

	protected StorageId() {
	}
	
	protected StorageId(Path entityPath) {
		super(entityPath);
	}

	public StorageId(Path categoryPath, Path entityPath) {
		super(entityPath);
		
		setCategoryPath(categoryPath);
	}

	public StorageId(String rawId) {
		super();
		
		Path filePath = Path.fromRawPath(rawId);
		
		if(filePath.nameCount() > 1) {
			
			setCategoryPath(filePath.subPath(0, filePath.nameCount() - 1));
			setEntityPath(Path.fromFilename(filePath.lastName()));

		}else {

			setEntityPath(Path.fromFilename(filePath.name(0)));
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof StorageId) {
			StorageId i = (StorageId) o;
			
			if(categoryPath != null ? !categoryPath.equals(i.categoryPath) : i.categoryPath != null) {
				return false;
			}

			return super.equals(i);
		}
		
		return false;
	}

	@Override
	public String toString() {
		return toFilepath().toRawPath();
	}

	@Override
	public String toHtmlId() {
		Path htmlPath = getCategoryPath().clone();
		htmlPath.append(getEntityPath());
		
		return htmlPath.toHtmlId();
	}
	
	@Override
	public Filepath toFilepath() {
		Filepath filePath = Filepath.fromPath(getCategoryPath());
		
		filePath.addName(getEntityPath().toFilename());

		return filePath;
	}
}
