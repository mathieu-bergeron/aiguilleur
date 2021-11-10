package ca.ntro.jj.values;

public class Id {

	public static final String CATEGORY_ENTITY_SEPARATOR = "¤";
	
	private Path categoryPath = new Path();
	private Path entityPath = new Path();
	
	private Id() {
	}
	
	public Id(String id) {
		if(id.contains(CATEGORY_ENTITY_SEPARATOR)
				&& id.contains(Path.PATH_SEPARATOR)) {
			
			throw new RuntimeException("Id must not contain both " + CATEGORY_ENTITY_SEPARATOR + " and " + Path.PATH_SEPARATOR);

		}
		
		if(id.contains(CATEGORY_ENTITY_SEPARATOR)) {

			parseKey(id);

		}else if(id.contains(Path.PATH_SEPARATOR)) {

			parseFilepath(id);
		}
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
	
	private void parseKey(String key) {
		
	}

	public static Id fromFilepath(String filepath) {
		Id id = new Id();
		
		return id;
	}

	private void parseFilepath(String filepath) {
		
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
}
