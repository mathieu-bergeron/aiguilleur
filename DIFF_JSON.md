Dans un updateModel:

JsonObject beforeUpdate = jsonService().toJsonObject(model);

updater.update(model);

JsonObject afterUpdate = jsonService().toJsonObject(model);

ModelUpdates updates = afterUpdate.modelUpdatesFrom(beforeUpdate);



// NOTE: dans le cas où le modèle est «neuf», on peut imaginer

JsonObject jsonModel = jsonService().toJsonObject(model);

// Tous les updates à partir de vide
ModelUpdates allUpdates = jsonModel.modelUpdatesFrom(new JsonObject());

