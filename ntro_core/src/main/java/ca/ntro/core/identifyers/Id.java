package ca.ntro.core.identifyers;

import ca.ntro.core.path.Filepath;

public interface Id {

	String toString();
	String toKey();
	String toHtmlId();
	Filepath toFilepath();

}
