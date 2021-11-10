package ca.ntro.jj.values;

public class Id {

	public static final String CATEGORY_ENTITY_SEPARATOR = "¤";
	
	private Path categoryPath = new Path();
	private Path entityPath;
	
	protected Id() {
	}

	protected void setCategoryPath(Path categoryPath) {
		this.categoryPath = categoryPath;
	}

	protected void setEntityPath(Path entityPath) {
		this.entityPath = entityPath;
	}

	public static Id parse(String rawId) {
		Id id = new Id();

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
	
	
	public static Id fromKey(String key) {
		Id id = new Id();
		
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

	public static Id fromFilepath(String filepath) {
		Id id = new Id();
		
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
		if(o instanceof Id) {
			Id i = (Id) o;
			
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
