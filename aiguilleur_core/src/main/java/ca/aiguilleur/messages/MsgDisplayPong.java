package ca.aiguilleur.messages;

import ca.ntro.app.messages.Message;
import ca.ntro.core.identifyers.ModelId;

public class MsgDisplayPong extends Message {
	
	private ModelId modelId;

	public ModelId getModelId() {
		return modelId;
	}

	public void setModelId(ModelId modelId) {
		this.modelId = modelId;
	}

}
