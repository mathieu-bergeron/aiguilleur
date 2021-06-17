package ca.ntro.server;

import ca.ntro.jdk.services.LocalStoreMongoDb;

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
