package ca.ntro.app.messages;

public interface MessageRegistrar {
	
	<MSG extends Message> void registerMessage(Class<MSG> messageClass);

}
