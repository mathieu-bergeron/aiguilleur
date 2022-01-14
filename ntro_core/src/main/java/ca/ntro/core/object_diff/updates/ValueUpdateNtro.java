package ca.ntro.core.object_diff.updates;

import ca.ntro.core.path.Path;

public abstract class ValueUpdateNtro 

       extends ObjectUpdateNtro 
       
       implements ValueUpdate {
	
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	public ValueUpdateNtro(Path valuePath, Object value) {
		super(valuePath);
		setValue(value);
	}




	@Override
	public Object value() {
		return getValue();
	}

}
