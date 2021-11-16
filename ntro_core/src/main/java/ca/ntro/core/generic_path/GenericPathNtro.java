package ca.ntro.core.generic_path;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.exceptions.InvalidCharacterException;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.json.JsonSerializer;
import ca.ntro.core.json.JsonString;
import ca.ntro.core.path.Path;
import ca.ntro.core.util.ListUtils;
import ca.ntro.core.util.Splitter;
import ca.ntro.core.validation.Validator;

public abstract class GenericPathNtro<N extends KeySerializable, I extends GenericPath<N,I>, IMPL extends GenericPathNtro<N,I,IMPL>> implements GenericPath<N,I>, JsonSerializer {

	@Override
	public void fromJsonString(String jsonString) {
		parsePath(JsonString.toRawString(jsonString), Path.PATH_SEPARATOR);
	}

	@Override
	public String toJsonString() {
		return JsonString.toJsonString(toKey());
	}

	private List<N> names = new ArrayList<>();
	
	protected List<N> getNames() {
		return names;
	}

	protected void setNames(List<N> names) {
		this.names = names;
	}

	protected abstract IMPL newInstance();
	protected abstract N newName();
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
	public void addName(N name) {
		try {

			Validator.mustContainOnlyValidCharacters(rawName, name.validCharacters());

		} catch(InvalidCharacterException e) {

			Ntro.exceptionThrower().throwException(new RuntimeException("A path name must not contain " + e.invalidCharacter()));
		}

		addValidName(name);
	}

	@Override
	public void addName(String rawName) {
		N name = parseName(rawName);
		
		addName(name);
	}
	
	protected N parseName(String rawName) {
		N name = newName();
		name.fromKey(rawName);
		
		return name;
	}
	
	protected void addValidName(String rawName) {
		N name = parseName(rawName);

		addValidName(name);
	}

	protected void addValidName(N name) {
		getNames().add(name);
	}

	@Override
	public N name(int index) {
		N name = null;
		
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
		for(String rawName : Splitter.split(rawPath, separator)){
			if(rawName.length() > 0) {
				addName(rawName);
			}
		}
	}

	protected void parseValidPath(String path, String separator) {
		for(String rawName : Splitter.split(path, separator)){
			if(rawName.length() > 0) {
				addValidName(rawName);
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

	@SuppressWarnings("unchecked")
	@Override
	public I removePrefix(String rawPrefix) {
		IMPL prefix = newInstance();
		prefix.fromRawPath(rawPrefix);

		return removePrefix((I) prefix);
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

	protected void fromGenericPath(GenericPath<?> path) {
		for(int i = 0; i < path.nameCount(); i++) {
			addName(path.name(i));
		}
	}
	
	
}
