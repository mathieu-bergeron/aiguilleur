package ca.ntro.jj.identifyers;

public class CategorizedId {

	public static final String CATEGORY_ENTITY_SEPARATOR = "¤";
	
	private Path categoryPath = new Path();
	private Path entityPath;
	
	protected CategorizedId() {
	}

	protected void setCategoryPath(Path categoryPath) {
		this.categoryPath = categoryPath;
	}

	protected void setEntityPath(Path entityPath) {
		this.entityPath = entityPath;
	}

	public static CategorizedId parse(String rawId) {
		CategorizedId id = new CategorizedId();

		if(rawId.contains(CATEGORY_ENTITY_SEPARATOR)
				&& rawId.contains(Path.PATH_SEPARATOR)) {
			
			throw new RuntimeException("Id must not contain both " + CATEGORY_ENTITY_SEPARATOR + " and " + Path.PATH_SEPARATOR);
		}
		
		if(rawId.contains(Path.PATH_SEPARATOR)) {

			id.parseFilepath(rawId);

		}else {

			id.parseKey(rawId);
		}
		
		return id;
	}
	
	
	public static CategorizedId fromKey(String key) {
		CategorizedId id = new CategorizedId();
		
		id.parseKey(key);
		
		return id;
	}
	
	private void parseKey(String key) {
		if(key.contains(CATEGORY_ENTITY_SEPARATOR)) {
			String[] segments = key.split(CATEGORY_ENTITY_SEPARATOR);

			setCategoryPath(Path.fromKey(segments[0]));
			setEntityPath(Path.fromKey(segments[1]));

		}else {

			setEntityPath(Path.fromKey(key));
		}
	}

	public static CategorizedId fromFilepath(String filepath) {
		CategorizedId id = new CategorizedId();
		
		id.parseFilepath(filepath);
		
		return id;
	}

	private void parseFilepath(String rawFilepath) {
		
		Path filePath = Path.fromRawPath(rawFilepath);
		
		if(filePath.nameCount() > 1) {
			
			categoryPath = filePath.subPath(0, filePath.nameCount() - 1);
			entityPath = Path.fromFilename(filePath.lastName());

		}else {

			entityPath = Path.fromFilename(filePath.name(0));
		}
	}
	
	public String toKey() {
		StringBuilder builder = new StringBuilder();
		
		if(!categoryPath.isRootPath()) {
			builder.append(categoryPath.toKey());
			builder.append(CATEGORY_ENTITY_SEPARATOR);
		}
		
		builder.append(entityPath.toKey());

		return builder.toString();
	}     

	public String toFilepath() {
		StringBuilder builder = new StringBuilder();
		
		Path filePath = new Path(categoryPath);
		filePath.addValidName(entityPath.toFileName());

		return builder.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof CategorizedId) {
			CategorizedId i = (CategorizedId) o;
			
			if(categoryPath != null ? !categoryPath.equals(i.categoryPath) : i.categoryPath != null) {
				return false;
			}

			if(entityPath != null ? !entityPath.equals(i.entityPath) : i.entityPath != null) {
				return false;
			}

			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return toFilepath();
	}
}
