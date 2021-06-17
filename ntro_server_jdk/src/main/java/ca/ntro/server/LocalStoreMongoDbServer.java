package ca.ntro.server;

import ca.ntro.core.Constants;
import ca.ntro.backend.BackendError;
import ca.ntro.jdk.services.LocalStoreMongoDb;
import ca.ntro.services.ModelIdReader;

public class LocalStoreMongoDbServer extends LocalStoreMongoDb {

	@Override
	protected String connectionString() {
		return null;
		//return Constants.MONGO_DB_CONNECTION_STRING;
	}

	@Override
	protected String databaseName() {
		return null;
		//return Constants.MONGO_DB_DATABASE_NAME;
	}


}
