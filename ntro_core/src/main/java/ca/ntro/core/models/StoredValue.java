package ca.ntro.core.models;

import ca.ntro.jj.ValuePath;
import ca.ntro.jj.json.JsonSerializable;
import ca.ntro.services.ModelStore;
import source.T;

public abstract class StoredValue implements NtroModelValue, JsonSerializable {
	
	private ValuePath valuePath;
	private ModelStore modelStore;
	
	
	ValuePath valuePath() {
		return valuePath;
	}

	// FIXME: this should be package-private
	//        and have "ModelWalker" in the models package
	//        then JsonSerialization can use the ModelWalker
	public void setValuePath(ValuePath valuePath) {
		this.valuePath = valuePath;
	}

	ModelStore modelStore() {
		return modelStore;
	}

	// FIXME: this should be package-private
	//        and have "ModelWalker" in the models package
	//        then JsonSerialization can use the ModelWalker
	public void setModelStore(ModelStore modelStore) {
		this.modelStore = modelStore;
	}

	protected boolean ifStoredConnected() {
		T.call(this);
		return modelStore() != null && valuePath() != null;
	}
}
