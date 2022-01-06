package ca.ntro.app.messages;

public interface MessageRegistrar {
	
	<MSG extends Message> void registerMessageClass(Class<MSG> messageClass);

}
