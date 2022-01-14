package ca.ntro.core.edit_distance.edits;

public abstract class EditValueNtro 

       extends        EditNtro 
       
       implements     EditValue {
	
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	public EditValueNtro(int index, Object value) {
		super(index);
		setValue(value);
	}



	@Override
	public Object value() {
		return getValue();
	}

}
