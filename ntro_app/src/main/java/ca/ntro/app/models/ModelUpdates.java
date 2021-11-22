package ca.ntro.app.models;


public interface ModelUpdates {

	void forEachUpdate(ModelUpdateVisitor visitor);

}
