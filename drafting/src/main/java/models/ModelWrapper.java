package models;

public class ModelWrapper<M extends Model>{
	
	private M model;
	
	public ModelWrapper(M model) {
		this.model = model;
	}
	
	protected M model() {
		return model;
	}

}
