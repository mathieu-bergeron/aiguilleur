// Copyright (C) (2020) (Mathieu Bergeron) (mathieu.bergeron@cmontmorency.qc.ca)
//
// This file is part of tutoriels4f5
//
// tutoriels4f5 is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// tutoriels4f5 is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with aquiletour.  If not, see <https://www.gnu.org/licenses/>

package ca.ntro.server.vertx;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.Constants;
import ca.ntro.backend.BackendError;
import ca.ntro.backend.UserInputError;
import ca.ntro.core.mvc.ControllerFactory;
import ca.ntro.core.mvc.NtroContext;
import ca.ntro.jdk.FileLoader;
import ca.ntro.jdk.FileLoaderDev;
import ca.ntro.jj.Path;
import ca.ntro.jj.log.Log;
import ca.ntro.jj.messages.MessageHandler;
import ca.ntro.jj.services.ModelStoreSync;
import ca.ntro.jj.services.Ntro;
import ca.ntro.jj.tasks.GraphTraceConnector;
import ca.ntro.jj.trace.T;
import ca.ntro.messages.ntro_messages.NtroErrorMessage;
import ca.ntro.tmp.NtroUser;
import ca.ntro.users.NtroSession;
import ca.ntro.web.NtroWindowServer;
import io.netty.handler.codec.http.HttpMethod;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.CookieSameSite;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;

public class DynamicHandlerVertx {

	private String privateFilesPrefix;

	public DynamicHandlerVertx(String publicFilesPrefix) {

		T.call(this);

		this.privateFilesPrefix = publicFilesPrefix;
	}
	
	public static void handle(RoutingContext routingContext) {
		T.call(DynamicHandlerVertx.class);

		HttpServerRequest request = routingContext.request();
		HttpServerResponse response = routingContext.response();

		Set<FileUpload> uploads =  routingContext.fileUploads();
		
		
		String rawPath = request.path();
		Path path = Path.fromRawPath(rawPath);
		
		serveView(request, response, path, uploads);
	}

	private static void serveLog(HttpServerRequest baseRequest, 
			                     HttpServerResponse response, 
			                     LogWriter writer) throws BackendError {

		T.call(DynamicHandlerVertx.class);

		ModelStoreSync modelStore = new ModelStoreSync(Ntro.modelStore());
		
		StringBuilder logContent = new StringBuilder();
		
		String fileBasename = writer.write(modelStore, logContent);

		response.putHeader("content-disposition", "attachment; filename=\"" + fileBasename + ".csv\"");
		response.putHeader("content-type", "text/csv; charset=utf-8");
		response.setStatusCode(HttpURLConnection.HTTP_OK);

		response.end(logContent.toString(), "UTF-8");

	}

	private static void serveQueueLog(HttpServerRequest baseRequest, 
			                          HttpServerResponse response, 
			                          String queueId)
			throws BackendError {

		T.call(DynamicHandlerVertx.class);

		/*
		serveLog(baseRequest, response, (modelStore, writer) -> {

			modelStore.readModel(LogModelQueue.class, "admin", queueId, logModel -> {

				logModel.writeCsvFileContent(Constants.CSV_SEPARATOR, writer);
				
			});

			return queueId;
		});
		*/
	}

	/*
	private static void serveCourseLog(HttpServerRequest baseRequest, 
			                           HttpServerResponse response, 
			                           CoursePath coursePath) throws BackendError {

		T.call(DynamicHandlerVertx.class);

		serveLog(baseRequest, response, (modelStore, writer) -> {

			modelStore.readModel(LogModelCourse.class, "admin", coursePath, logModel -> {

				logModel.writeCsvFileContent(Constants.CSV_SEPARATOR, writer);
			});
			
			return coursePath.toFileName();
		});
	}*/


	private static void serveView(HttpServerRequest request, 
			                      HttpServerResponse response, 
			                      Path path,
			                      Set<FileUpload> uploads) {

		T.call(DynamicHandlerVertx.class);
		
		System.out.println("");
		System.out.println("");
		System.out.println("Request for: " + path.toRawPath());
		
		Map<String, String[]> parameters = new HashMap<>();
		MultiMap names = request.params();
		for(Map.Entry<String, String> entry : names.entries()) {
			String paramName = entry.getKey();
			String paramValue = entry.getValue();
			
			String[] paramValues = parameters.get(paramName);
			if(paramValues == null) {
				paramValues = new String[] {};
			}
			
			String[] newValues = Arrays.copyOf(paramValues, paramValues.length+1);
			newValues[paramValues.length] = paramValue;

			parameters.put(paramName, newValues);
		}
		
		for(Map.Entry<String, String[]> entry : parameters.entrySet()) {
			String paramName = entry.getKey();
			System.out.println(paramName + " " + entry.getValue()[0]);
		}

		sendSessionMessagesAccordingToCookies(request);

		executeBackend(path, parameters);

		boolean ifJSweet = ifJsOnlySetCookies(request, response);
		//boolean ifJSweet = false;

		NtroWindowServer window = newWindow(ifJSweet, path);
		//NtroWindowServer window = newWindow(true, path);

		if(!ifJSweet) {

			//processCsvUploadsIfAny(request, uploads);

		}

		executeFrontendOnServer(request, response, path, parameters, window);

		if(ifJSweet) {

			// FIXME: the taskGraph itself should have a notion
			//        of queued messages
			Ntro.messages().sendQueuedMessages();
		}

		if(!path.startsWith(ca.ntro.core.Constants.SOCKET_PREFIX)) {
			setSessionCookie(response);
		}

		// XXX on the server, the taskGraph is sync
		//     writeResponse will execute AFTER 
		//     every non-blocked task in webApp
		
		if(!response.ended()) {

			response.putHeader("content-type", "text/html; charset=utf-8");
			response.setStatusCode(HttpURLConnection.HTTP_OK);
			writeResponse(window, response);
		}
	}

	private static void executeBackend(Path path,
			                           Map<String, String[]> parameters) {

		//NtroContext<User, SessionData> context = AquiletourMain.createNtroContext();
		
		try {

			//AquiletourBackendRequestHandler.sendMessages(context, path, parameters);
			throw new UserInputError();

		}catch(UserInputError e) {

			NtroErrorMessage displayErrorMessage = Ntro.messages().create(NtroErrorMessage.class);
			displayErrorMessage.setMessage(e.getMessage());
			Ntro.messages().send(displayErrorMessage);
		}
	}

	private static void sendCsvMessage(String semesterId, 
			                    String courseId, 
			                    FileUpload upload) {
		
		String fileName = upload.fileName();
		
		File fileOnDisk = new File(upload.uploadedFileName());

		String fileContent = null;
		
		try {

			fileContent = new String(Files.readAllBytes(fileOnDisk.toPath()), "UTF-8");

		} catch (IOException e) {

			Log.error("Cannot read uploaded CSV file  " + e.getMessage());
		}
		
		fileOnDisk.delete();
		
		if(fileContent != null) {

		}
	}

	private static void sendSessionMessagesAccordingToCookies(HttpServerRequest baseRequest) {
		T.call(DynamicHandlerVertx.class);

		//InitializeSessionMessage authenticateSessionUserMessage = Ntro.messages().create(InitializeSessionMessage.class);
		

		if(hasCookie(baseRequest, "session")) {
			
			String sessionString = getCookie(baseRequest, "session");

			//sessionString = UrlEncoded.decodeString(getCookie(baseRequest, "session"));
			
			NtroSession session = Ntro.jsonService().fromString(NtroSession.class, sessionString);
			
			//authenticateSessionUserMessage.setSessionUser(session.getUser());
		}

		//Ntro.backendService().sendMessageToBackend(authenticateSessionUserMessage);
	}

	private static void executeFrontendOnServer(HttpServerRequest baseRequest, 
			                                    HttpServerResponse response, 
			                                    Path path,
			                                    Map<String, String[]> parameters,
			                                    NtroWindowServer window) {
		
		//handleRedirections(baseRequest, response, path);

		//NtroContext<User, SessionData> context = AquiletourMain.createNtroContext();

		// DEBUG
		// RootController rootController =  ControllerFactory.createRootController(RootController.class, "*", newWindow, context);

		// NORMAL
		//RootController rootController =  ControllerFactory.createRootController(RootController.class, path, window, context);
		
		Ntro.messages().sendQueuedMessages();

		// Client controller executes after
		// to make sure modifications to the
		// models are loaded up
		//GraphTraceConnector trace = rootController.execute();

		//trace.addGraphWriter(new GraphTraceWriterJdk(new File("__task_graphs__", path.toFileName())));

		//AquiletourRequestHandler.sendMessages(context, path, parameters);

		// XXX: prepare for next request
		Ntro.reset();
	}


	private static void handleRedirections(HttpServerRequest baseRequest, HttpServerResponse response, Path path) {
	}

	private static void writeRedirect(HttpServerResponse response, String redirectUrl) {
		T.call(DynamicHandlerVertx.class);

		response.putHeader("location", redirectUrl);
		response.setStatusCode(HttpURLConnection.HTTP_MOVED_TEMP);
		response.end();
	}


	private static void setSessionCookie(HttpServerResponse response) {
		T.call(DynamicHandlerVertx.class);
		
		NtroSession session = Ntro.currentSession();
		//NtroUser user = session.getUser();
		//session.setUser(user);

		setCookie(response, "session", Ntro.jsonService().toString(session, false));
	}

	private static boolean ifJsOnlySetCookies(HttpServerRequest baseRequest, HttpServerResponse response) {
		T.call(DynamicHandlerVertx.class);
		
		boolean ifJSweet = false;

		if(baseRequest.getParam("nojsweet") != null) {
			
			setCookie(response, "jsweet" , "false" );
			ifJSweet = false;

		} else if(baseRequest.getParam("jsweet") != null) {
			
			setCookie(response, "jsweet" , "true" );
			ifJSweet = true;
			
		}else if(hasCookie(baseRequest, "jsweet")) {
			
			String jsOnlyCookie = getCookie(baseRequest, "jsweet");
			ifJSweet = Boolean.valueOf(jsOnlyCookie);

		}
		
		return ifJSweet;

	}

	private static NtroWindowServer newWindow(boolean ifJSweet, Path path) {
		T.call(DynamicHandlerVertx.class);

		NtroWindowServer newWindow;

		if(ifJSweet) {

			newWindow = new NtroWindowServer("/private/index.html");
			
		}else {

			newWindow = new NtroWindowServer("/private/nojsweet.html");

		} 

		newWindow.setCurrentPath(path);

		return newWindow;
	}
	
	private static boolean hasCookie(HttpServerRequest request, String name) {
		T.call(DynamicHandlerVertx.class);
		
		return request.getCookie(name) != null;
	}

	private static String getCookie(HttpServerRequest request, String name) {
		T.call(DynamicHandlerVertx.class);
		
		return request.getCookie(name).getValue();
	}

	@SuppressWarnings("unused")
	private static void eraseCookie(HttpServerResponse response, String name) {
		T.call(DynamicHandlerVertx.class);
		
		Cookie cookie = Cookie.cookie(name, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);

		response.addCookie(cookie);
	}

	private static void setCookie(HttpServerResponse response, String name, String value) {
		T.call(DynamicHandlerVertx.class);
		
		String trimmedValue = value.replace(" ", "%20");

		String urlEncodedString = "";
		try {

			urlEncodedString = URLEncoder.encode(trimmedValue, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			
			Log.error("[setCookie] unsupported encoding: UTF-8");

		}
		
		Cookie cookie = Cookie.cookie(name, urlEncodedString);
		cookie.setPath("/");

		response.addCookie(cookie);
	}

	private static void writeResponse(NtroWindowServer window, HttpServerResponse response) {
		T.call(DynamicHandlerVertx.class);

		StringBuilder builder = new StringBuilder();
		window.writeHtml(builder);

		response.end(builder.toString(), "UTF-8");
	}
}

