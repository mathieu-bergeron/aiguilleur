package ca.ntro.app.models;

import ca.ntro.core.path.ValuePath;

public interface ModelUpdateVisitor {
	
	void visitUpdate(UpdateType type, ValuePath path, Object value);

}
