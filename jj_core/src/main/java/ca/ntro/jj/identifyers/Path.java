package ca.ntro.jj.identifyers;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.jj.exceptions.InvalidCaracterException;
import ca.ntro.jj.util.Splitter;
import ca.ntro.jj.validation.Validator;

public class Path {

	public static final String FILENAME_SEPARATOR = "¬";
	public static final String PATH_SEPARATOR = "/";
	public static final String HTML_ID_SEPARATOR = "-";
	public static final String CLASSNAME_SEPARATOR = ".";
		
	private List<String> names = new ArrayList<>();

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}
	
	public Path() {
	}

	private Path(List<String> names) {
		setNames(names);
	}

	protected Path(Path otherPath) {
		append(otherPath);
	}

	public void append(Path otherPath) {
		for(String otherName : otherPath.getNames()) {
			addValidName(otherName);
		}
	}

	public static Path fromRawPath(String rawPath) {
		Path path = new Path();
		
		path.parsePath(rawPath, PATH_SEPARATOR);

		return path;
	}

	public static Path fromKey(String key) {
		Path path = new Path();
		
		path.parsePath(key, FILENAME_SEPARATOR);

		return path;
	}

	public static Path fromFilename(String filename) {
		Path path = new Path();
		
		path.parsePath(filename, FILENAME_SEPARATOR);

		return path;
	}

	public static Path fromClassname(String classname) {
		Path path = new Path();
		
		path.parsePath(classname, CLASSNAME_SEPARATOR);

		return path;
	}

	private void parsePath(String path, String separator) {
		for(String name : Splitter.split(path, separator)){
			if(name.length() > 0) {
				try {
					addName(name);
				}
				catch(InvalidCaracterException e) {

					throw new RuntimeException("A path name must not contain " + e.invalidCharacter());
				}
			}
		}
	}

	public boolean startsWith(String rawPath) {
		return startsWith(Path.fromRawPath(rawPath));
	}
	
	public boolean startsWith(Path path) {
		boolean startsWith = true;
		
		if(nameCount() < path.nameCount()) {

			startsWith = false;

		}else {

			for(int i = 0; i < nameCount(); i++) {
				if(!name(i).equals(path.name(i))) {
					startsWith = false;
					break;
				}
			}
		}

		return startsWith;
	}
	
	public Path clone() {
		return subPath(0, nameCount()-1);
	}

	public Path subPath(int beginIndex) {
		return subPath(beginIndex, nameCount()-1);
	}

	public Path subPath(int beginIndex, int endIndex) {
		Path path = null;
		
		if(ifValidIndices(beginIndex, endIndex)) {
			path = new Path(getNames().subList(beginIndex, endIndex+1));
		}else {
			path = new Path();
		}
		
		return path;
		
	}
	
	private boolean ifValidIndices(int beginIndex, int endIndex) {
		return endIndex < nameCount()
				&& endIndex >= beginIndex
				&& beginIndex >= 0;
	}
	
	public String toString(String separator, boolean startsWithSeparator) {
		StringBuilder builder = new StringBuilder();
		
		if(startsWithSeparator) {
			builder.append(separator);
		}
		
		if(nameCount() > 0) {
			builder.append(name(0));
		}
		
		for(int i = 1; i < nameCount(); i++) {
			builder.append(separator);
			builder.append(name(i));
		}

		return builder.toString();
	}

	@Override
	public String toString() {
		return toRawPath();
	}

	public String toRawPath() {
		return toString(PATH_SEPARATOR, true);
	}

	public String toFilename() {
		return toString(FILENAME_SEPARATOR, false);
	}

	public String toClassname() {
		return toString(CLASSNAME_SEPARATOR, false);
	}

	public String toHtmlId() {
		String htmlId = toString(HTML_ID_SEPARATOR, false);
		htmlId = htmlId.replace(".", HTML_ID_SEPARATOR);
		return htmlId;
	}

	public String toKey() {
		return toFilename();
	}

	public Path removePrefix(String rawPrefix) {
		return removePrefix(Path.fromRawPath(rawPrefix));
	}

	public Path removePrefix(Path prefix) {
		Path remainder = null;
		
		if(startsWith(prefix)) {

			remainder = subPath(prefix.nameCount());
			
		}else {

			remainder = subPath(0);
		}

		return remainder;
	}

	public String name(int index) {
		String name = null;
		
		if(ifIndexValid(index)) {

			name = getNames().get(index);
		}

		return name;
	}

	private boolean ifIndexValid(int index) {
		return index >= 0 && index < nameCount();
	}

	public int nameCount() {
		return names.size();
	}


	public boolean isPrefixOf(Path path) {
		boolean isPrefixOf = true;
		
		if(nameCount() >= path.nameCount()) {

			isPrefixOf = false;

		}else {
			for(int i = 0; i < nameCount(); i++) {
				if(!name(i).equals(path.name(i))) {
					isPrefixOf = false;
					break;
				}
			}
		}

		return isPrefixOf;
	}

	public Path parent() {
		Path parentPath = null;

		if(nameCount() > 1) {

			parentPath = subPath(0, nameCount() - 2);

		}else if(nameCount() == 1) {

			parentPath = new Path();
		}
		
		return parentPath;
	}

	public void addName(String name) throws InvalidCaracterException {
		Validator.mustNotContainCharacter(name, new String[] {FILENAME_SEPARATOR, PATH_SEPARATOR});

		addValidName(name);
	}

	protected void addValidName(String name) {
		getNames().add(name);
	}

	public String lastName() {
		return name(nameCount()-1);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof Path) {
			Path p = (Path) o;
			
			if(p.nameCount() != nameCount()) return false;
			
			for(int i = 0; i < p.nameCount(); i++) {
				if(!name(i).equals(p.name(i))) {
					return false;
				}
			}
			
			return true;
		}

		return false;
	}

	public boolean isRootPath() {
		return nameCount() == 0;
	}

}
