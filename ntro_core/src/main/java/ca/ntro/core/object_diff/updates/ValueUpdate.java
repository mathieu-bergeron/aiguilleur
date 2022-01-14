package ca.ntro.core.object_diff.updates;

public interface ValueUpdate extends ObjectUpdate {
	
	// FIXME: this should support references to existing objects
	Object value();

}
