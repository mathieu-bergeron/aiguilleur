On veut

<form>
    <textarea name="message">{"_C":"AddAppointementMessage", "userId":"bob","coursePath":"/alice/H2021/blah"}</textarea>
    <input type="submit" value="Prendre rendez-vous"></input>
</form>

# Dans le ViewModel

AddAppointmentMessage message = Ntro.messages().create(AddAppointementMessage.class);
message.setUserId("bob");
message.setCoursePath("/alice/H2021/blah");


view.installAddAppointementMessage(message);

# Dans la vue

on n'a même pas à connaître les détails du message. On 
met tout simplement le json du message

HtmlElement addAppointementForm = ...;
HtmlElement messageInput = addAppointementForm.createElement("<textarea></textarea>");
addAppointementForm.append(messageInput);

messageInput.hide();
messageInput.text(Ntro.jsonService().toString(message));

# Sur le serveur

On peut décoder automatiquement les POST de cette forme:

for(String messageText : parameters.get("message")){
    NtroMessage message = Ntro.jsonService().fromString(NtroMessage.class, messageText);
    Ntro.messages().send(message);
}

// Ça couvre beaucoup de cas qu'on a pas à écrire à la main dans AquiletourRequestHandler/AquiletourBackendRequestHandler
