package ca.ntro.server.vertx;

import java.net.HttpURLConnection;

import ca.ntro.core.Constants;
import ca.ntro.server.registered_sockets.RegisteredSocketsSockJS;
import ca.ntro.core.models.ModelLoader;
import ca.ntro.core.models.NtroModel;
import ca.ntro.jj.DocumentPath;
import ca.ntro.jj.log.Log;
import ca.ntro.jj.messages.NtroMessage;
import ca.ntro.jj.services.Ntro;
import ca.ntro.jj.trace.T;
import ca.ntro.messages.ntro_messages.NtroGetModelMessage;
import ca.ntro.messages.ntro_messages.NtroSetModelMessage;
import ca.ntro.users.NtroUser;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class ModelHandlerVertx {

    public static void handle(RoutingContext routingContext) {
    	T.call(ModelHandlerVertx.class);
    	
    	HttpServerRequest request = routingContext.request();
    	HttpServerResponse response = routingContext.response();

        if (request.method().equals(HttpMethod.POST)) {

			NtroMessage message = Ntro.jsonService().fromString(NtroMessage.class, routingContext.getBodyAsString());
			
			if(message instanceof NtroGetModelMessage) {

				handleGetModelMessage(request, response, (NtroGetModelMessage) message);

			} else if(message instanceof NtroSetModelMessage) {

				handleSetModelMessage(request, response, (NtroSetModelMessage) message);
				
			}else {

				Log.error("[ModelHandlerVertx] Unsupported message '" + Ntro.introspector().ntroClassFromObject(message).simpleName() + "'");
				response.setStatusCode(HttpURLConnection.HTTP_BAD_REQUEST);
				response.end();

			}

        }else {

            Log.error("[ModelHandlerVertx] Invalid HTTP method '" + request.method() + "'!");
            response.setStatusCode(HttpURLConnection.HTTP_BAD_METHOD);
            response.end();
        }

        // XXX: prepare for next request
        Ntro.reset();
    }

	private static void handleModelResponse(HttpServerRequest baseRequest, 
			                         HttpServerResponse response, 
			                         DocumentPath documentPath, 
			                         String authToken, 
			                         NtroModel model) {

		T.call(ModelHandlerVertx.class);

        // FIXME: user observation needs to be global (not specific to a single modelStore as there is one per thread!)
        // NOTE:  we do not need to connect that model to the store
        //        only models in the backend
        RegisteredSocketsSockJS.registerModelObserver(authToken, documentPath);
        // ??
        // Ntro.backendService().registerThatUserObservesModel(user, documentPath);

        response.setStatusCode(HttpURLConnection.HTTP_OK);

        response.end(Ntro.jsonService().toString(model));
        
	}

	@SuppressWarnings("unchecked")
	private static void handleGetModelMessage(HttpServerRequest baseRequest, 
			                           HttpServerResponse response,
			                           NtroGetModelMessage getModelNtroMessage) {

		T.call(ModelHandlerVertx.class);

		DocumentPath documentPath = getModelNtroMessage.getDocumentPath();
		NtroUser user = getModelNtroMessage.getUser();

		Class<? extends NtroModel> modelClass = (Class<? extends NtroModel>) Ntro.serializableClass(documentPath.getCollection());
		
        ModelLoader modelLoader = Ntro.modelStore().getLoader(modelClass, getModelNtroMessage.getAuthToken(), documentPath.getDocumentId());
        modelLoader.execute();
        
        NtroModel model = modelLoader.getModel();
        
        handleModelResponse(baseRequest, response, documentPath, user.getAuthToken(), model);
	}

	private static void handleSetModelMessage(HttpServerRequest baseRequest, 
			                           HttpServerResponse response,
			                           NtroSetModelMessage setModelNtroMessage) {

		T.call(ModelHandlerVertx.class);

		DocumentPath documentPath = setModelNtroMessage.getDocumentPath();
		NtroModel model = setModelNtroMessage.getModel();

        Ntro.modelStore().saveDocument(documentPath, Ntro.jsonService().toString(model));

        response.setStatusCode(HttpURLConnection.HTTP_OK);
	}
}
