package ca.ntro.jj.identifyers;

public class StorageId extends Id {
	
	private Path categoryPath = new Path();

	protected void setCategoryPath(Path categoryPath) {
		this.categoryPath = categoryPath;
	}
	
	protected StorageId() {
	}

	public StorageId(Path categoryPath, Path entityPath) {
		super(entityPath);
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
		StringBuilder builder = new StringBuilder();
		
		Path filePath = new Path(categoryPath);
		filePath.addValidName(getEntityPath().toFilename());

		return builder.toString();
	}
}
