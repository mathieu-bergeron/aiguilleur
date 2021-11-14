package ca.ntro.core.path;

import ca.ntro.core.json.JsonSerializable;

public interface PaGeneric<I extends PaGeneric<I>> extends JsonSerializable {

	I subPath(int beginIndex, int endIndexExclusive);

}
