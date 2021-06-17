package ca.ntro.jj.services;

import ca.ntro.jj.messages.MessageHandler;
import ca.ntro.jj.messages.NtroMessage;

public abstract class BackendService {

	public abstract void sendMessageToBackend(NtroMessage message);
	public abstract <M extends NtroMessage> void handleMessageFromBackend(Class<M> messageClass, MessageHandler<M> handler);
	public abstract <MSG extends NtroMessage> boolean handlerExistsFor(MSG message);

}
