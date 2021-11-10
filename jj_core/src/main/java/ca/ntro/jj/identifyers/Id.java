package ca.ntro.jj.identifyers;

import ca.ntro.jj.exceptions.InvalidCaracterException;
import ca.ntro.jj.validation.Validator;
import ca.ntro.jj.values.Path;

public class Id {

	public static final String CATEGORY_ENTITY_SEPARATOR = "¤";
	
	private Path categoryPath = new Path();
	private Path entityPath = new Path();
	
	private Id() {
	}
	
	public Id(String entityId) {
		entityPath = new Path();
		
		try {

			Validator.mustNotContainCharacter(entityId, new String[] {CATEGORY_ENTITY_SEPARATOR});
			entityPath.addName(entityId);

		} catch (InvalidCaracterException e) {
			
			throw new RuntimeException("Id must not contain character " + e.invalidCharacter());
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

	public static Id fromFilepath(String filepath) {
		Id id = new Id();
		
		return id;
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
		filePath.addName(entityPath.toFileName());
		
		return builder.toString();
	}
}
