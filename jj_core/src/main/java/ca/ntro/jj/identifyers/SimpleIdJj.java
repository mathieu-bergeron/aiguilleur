package ca.ntro.jj.identifyers;

import ca.ntro.jj.values.Path;

public class SimpleIdJj implements SimpleId {

	private String id;
	
	public SimpleIdJj(String id) {
		
		mustNotContainSpecialCharacter(id, Path.CATEGORY_ENTITY_SEPARATOR);
		mustNotContainSpecialCharacter(id, Path.FILENAME_SEPARATOR);
		mustNotContainSpecialCharacter(id, Path.KEY_SEPARATOR);
		mustNotContainSpecialCharacter(id, Path.PATH_SEPARATOR);

		this.id = id;
	}
	
	private void mustNotContainSpecialCharacter(String id, String specialCharacter) {
		if(id.contains(specialCharacter)) {
			throw new RuntimeException("Id must not contain character " + specialCharacter);
		}
	}
	
	
	@Override
	public String id() {
		return id;
	}

}
