package ca.ntro.core.identifyers;

import java.util.List;

import ca.ntro.core.util.ArrayUtils;
import ca.ntro.core.util.ListUtils;
import ca.ntro.core.validation.Validator;

public class PathPatternNtro extends PathNtro implements PathPattern {
	

	public PathPatternNtro() {
		super();
	}

	protected PathPatternNtro(List<String> names) {
		super(names);
	}

	protected PathPatternNtro(PathPattern otherPattern) {
		super(otherPattern);
	}

	@Override
	protected String[] validNameCharacters() {
		String[] validNameCharacters = ArrayUtils.addString(Validator.validIdCharacters, NAME_WILDCARD);
		validNameCharacters = ArrayUtils.addString(validNameCharacters, SUBPATH_WILDCARD);

		return validNameCharacters;
	}


	@Override
	public PathPattern clone() {
		return subPath(0, nameCount());
	}

	@Override
	public PathPattern subPath(int beginIndex) {
		return subPath(beginIndex, nameCount());
	}

	@Override
	public PathPattern subPath(int beginIndex, int endIndexExclusive) {
		PathPattern path = null;
		
		if(ifValidIndices(beginIndex, endIndexExclusive-1)) {

			path = new PathPatternNtro(ListUtils.subList(getNames(), beginIndex, endIndexExclusive));

		}else {

			path = new PathPatternNtro();

		}
		
		return path;
	}
}
