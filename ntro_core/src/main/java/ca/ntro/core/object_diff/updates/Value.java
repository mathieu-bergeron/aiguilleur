package ca.ntro.core.object_diff.updates;

public interface Value {
	
	boolean isObjectValue();
	ObjectValue asObjectValue();

	boolean isReferenceValue();
	ReferenceValue asReferenceValue();

}
