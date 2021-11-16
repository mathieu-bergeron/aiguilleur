package ca.ntro.core.generic_path;

public interface KeySerializable {
	
	String toKey();
	void fromKey(String key);

	String[] validKeyCharacters();

}
