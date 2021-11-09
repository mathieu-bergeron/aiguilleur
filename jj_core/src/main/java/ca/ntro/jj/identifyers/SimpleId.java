package ca.ntro.jj.identifyers;

public abstract class SimpleId {

	private String id;
	
	public SimpleId(String id) {
		// TODO: validate ID
		// cannot contain: 
		//  /  (path separator)
		//  ¤  (filename separator)

		this.id = id;
	}

}
