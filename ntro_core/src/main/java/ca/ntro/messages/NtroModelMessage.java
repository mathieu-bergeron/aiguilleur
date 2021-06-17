package ca.ntro.messages;

import ca.ntro.core.models.NtroModel;
import ca.ntro.jj.DocumentPath;
import ca.ntro.jj.json.JsonSerializable;

public interface NtroModelMessage extends JsonSerializable {

	DocumentPath getDocumentPath();
	Class<? extends NtroModel> targetClass();

}
