Dans un ViewModel

handle(Model model, ModelUpdates modelUpdates, View view){

    ListUpdates<Appointment> appointmentListUpdates = modelUpdates.listUpdatesFor(Appointment.class, model.getAppointmentList());

    appointmentListUpdates.onItemAdded((index, appointment) -> {


    });

    appointmentListUpdates.onItemRemoved((index, appointment) -> {


    });


}
