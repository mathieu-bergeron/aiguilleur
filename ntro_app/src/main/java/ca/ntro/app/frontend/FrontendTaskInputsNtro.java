package ca.ntro.app.frontend;

import ca.ntro.app.frontend.tasks.FrontendTaskInputs;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptor;
import ca.ntro.core.values.ObjectMap;

public class FrontendTaskInputsNtro implements FrontendTaskInputs {
	
	private ObjectMap inputs;

	public ObjectMap getInputs() {
		return inputs;
	}

	public void setInputs(ObjectMap inputs) {
		this.inputs = inputs;
	}
	
	
	


	public FrontendTaskInputsNtro(ObjectMap inputs) {
		setInputs(inputs);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public <O> O get(TypedFrontendTaskDescriptor<O> task) {
		return (O) getInputs().get(task.id().toKey().toString());
	}

}
