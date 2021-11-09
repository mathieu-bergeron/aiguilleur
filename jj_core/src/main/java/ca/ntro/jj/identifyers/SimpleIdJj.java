package ca.ntro.jj.identifyers;

import ca.ntro.jj.constants.SpecialCharacters;

public class SimpleIdJj implements SimpleId {

	private String id;
	
	public SimpleIdJj(String id) {

		if(id.contains(SpecialCharacters.FILENAME_SEPARATOR)) {
			throw new RuntimeException("SimpleId cannot contain character " + SpecialCharacters.FILENAME_SEPARATOR);
		}

		if(id.contains(SpecialCharacters.PATH_SEPARATOR)) {
			throw new RuntimeException("SimpleId cannot contain character " + SpecialCharacters.PATH_SEPARATOR);
		}

		this.id = id;
	}
	
	@Override
	public String id() {
		return id;
	}

}
