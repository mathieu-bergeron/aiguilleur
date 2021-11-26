package ca.ntro.app.models;

import ca.ntro.core.identifyers.ValueId;

public interface ModelUpdateVisitor {
	
	void visitUpdate(UpdateType type, ValueId<?> valueId, Object value);

}
