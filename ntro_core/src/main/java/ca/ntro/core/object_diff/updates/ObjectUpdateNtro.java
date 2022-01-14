package ca.ntro.core.object_diff.updates;

import ca.ntro.core.path.Path;

public abstract class ObjectUpdateNtro implements ObjectUpdate {
	
	private Path valuePath;

	public Path getValuePath() {
		return valuePath;
	}

	public void setValuePath(Path valuePath) {
		this.valuePath = valuePath;
	}
	
	
	
	public ObjectUpdateNtro() {
	}
	
	public ObjectUpdateNtro(Path valuePath) {
		setValuePath(valuePath);
	}
	
	
	
	


	@Override
	public Insert asInsert() {
		return (Insert) this;
	}

	@Override
	public Modify asModify() {
		return (Modify) this;
	}

	@Override
	public Delete asDelete() {
		return (Delete) this;
	}

	@Override
	public Path valuePath() {
		return getValuePath();
	}

}
