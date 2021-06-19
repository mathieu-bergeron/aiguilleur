package ca.ntro.core.models;

public interface NtroModel extends NtroModelValue {
	
	// XXX: a model has a submodel
	NtroModelValue findSubModelById();

}
