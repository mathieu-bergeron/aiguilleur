package ca.ntro.core.path;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.exceptions.InvalidCharacterException;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.json.JsonSerializer;
import ca.ntro.core.json.JsonString;
import ca.ntro.core.util.ListUtils;
import ca.ntro.core.util.Splitter;
import ca.ntro.core.validation.Validator;

public abstract class PathGenericNtro<I extends PathGeneric<I>, IMPL extends PathGenericNtro<I,IMPL>> implements PathGeneric<I>, JsonSerializer {

	@Override
	public void fromJsonString(String jsonString) {
		parseValidPath(JsonString.toRawString(jsonString), Path.FILENAME_SEPARATOR);
	}

	@Override
	public String toJsonString() {
		return JsonString.toJsonString(toRawPath());
	}

	private List<String> names = new ArrayList<>();
	
	protected List<String> getNames() {
		return names;
	}

	protected void setNames(List<String> names) {
		this.names = names;
	}

	protected abstract IMPL newInstance();
	protected abstract String[] validNameCharacters();

	@SuppressWarnings("unchecked")
	@Override
	public I subPath(int beginIndex, int endIndexExclusive) {
		IMPL path = newInstance();

		if(ifValidIndices(beginIndex, endIndexExclusive-1)) {

			path.setNames(ListUtils.subList(getNames(), beginIndex, endIndexExclusive));
		}
		
		return (I) path;
	}

	@Override
	public int nameCount() {
		return names.size();
	}

	private boolean ifIndexValid(int index) {
		return index >= 0 && index < nameCount();
	}

	protected boolean ifValidIndices(int beginIndex, int endIndex) {
		return endIndex < nameCount()
				&& endIndex >= beginIndex
				&& beginIndex >= 0;
	}

	@Override
	public void addName(String name) {
		try {

			Validator.mustContainOnlyValidCharacters(name, validNameCharacters());

		} catch(InvalidCharacterException e) {

			Ntro.exceptionThrower().throwException(new RuntimeException("A path name must not contain " + e.invalidCharacter()));
		}

		addValidName(name);
	}

	protected void addValidName(String name) {
		getNames().add(name);
	}

	@Override
	public String name(int index) {
		String name = null;
		
		if(ifIndexValid(index)) {

			name = getNames().get(index);
		}

		return name;
	}

	@Override
	public void append(I otherPath) {
		for(int i = 0; i < otherPath.nameCount(); i++) {
			addValidName(otherPath.name(i));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isPrefixOf(I path) {
		return path.startsWith((I) this);
	}

	@Override
	public boolean startsWith(I path) {
		boolean startsWith = true;
		
		if(nameCount() < path.nameCount()) {

			startsWith = false;

		} else {

			for(int i = 0; i < path.nameCount(); i++) {
				if(!name(i).equals(path.name(i))) {
					startsWith = false;
					break;
				}
			}
		}

		return startsWith;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean startsWith(String rawPath) {
		IMPL path = newInstance();
		
		path.parsePath(rawPath, Path.PATH_SEPARATOR);

		return startsWith((I) path);
	}

	protected void parsePath(String rawPath, String separator) {
		for(String name : Splitter.split(rawPath, separator)){
			if(name.length() > 0) {
				addName(name);
			}
		}
	}

	protected void parseValidPath(String path, String separator) {
		for(String name : Splitter.split(path, separator)){
			if(name.length() > 0) {
				addValidName(name);
			}
		}
	}

	@Override
	public boolean isRootPath() {
		return nameCount() == 0;
	}

	@Override
	public String lastName() {
		return name(nameCount()-1);
	}

	@Override
	public I clone() {
		return subPath(0, nameCount());
	}

	@Override
	public I subPath(int beginIndex) {
		return subPath(beginIndex, nameCount());
	}

	@SuppressWarnings("unchecked")
	@Override
	public I parent() {
		I parentPath = null;

		if(nameCount() > 1) {

			parentPath = subPath(0, nameCount() - 2);

		}else if(nameCount() == 1) {

			parentPath = (I) newInstance();
		}
		
		return parentPath;
	}

	@Override
	public String toRawPath() {
		return toString(Path.PATH_SEPARATOR, true);
	}

	protected void fromRawPath(String rawPath) {
		parsePath(rawPath, Path.PATH_SEPARATOR);
	}

	@Override
	public String toHtmlId() {
		String htmlId = toString(Path.HTML_ID_SEPARATOR, false);
		htmlId = htmlId.replace(".", Path.HTML_ID_SEPARATOR);
		return htmlId;
	}

	@Override
	public String toFilename() {
		return toString(Path.FILENAME_SEPARATOR, false);
	}

	protected void fromFilename(String rawFilename) {
		parsePath(rawFilename, Path.FILENAME_SEPARATOR);
	}

	@Override
	public String toKey() {
		return toFilename();
	}

	protected void fromKey(String rawKey) {
		fromFilename(rawKey);
	}

	@Override
	public I removePrefix(String rawPrefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public I removePrefix(I prefix) {
		I remainder = null;
		
		if(startsWith(prefix)) {

			remainder = subPath(prefix.nameCount());
			
		}else {

			remainder = clone();
		}

		return remainder;
	}

	@Override
	public String toClassname() {
		return toString(Path.CLASSNAME_SEPARATOR, false);
	}

	protected void fromClassname(String rawClassname) {
		parsePath(rawClassname, Path.CLASSNAME_SEPARATOR);
	}

	protected String toString(String separator, boolean startsWithSeparator) {
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

	@Override
	public String toString() {
		return toRawPath();
	}

	protected void fromSingleName(String id) {
		addName(id);
	}
	
	
}
