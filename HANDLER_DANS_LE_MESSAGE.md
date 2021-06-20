# On pourrait pousser le BackendHandler directement dans le message

Comme ça on fait un RegisterBackendHandler automatiquement et ça fait moins de boilerplate

Genre 

<pre>
    AddAppointmentMessage extends NtroMessage implements BackendMessageHandler {



        public void handleNowBackend(...){


        }
    }

</pre>

