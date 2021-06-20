# On a un ensemble de TaskGraph

1. Chaque TaskGraph maintient un nextTask (prochaine tâche à exécuter)
1. Lorsque `executeOneTask` est appelé sur le graphe
    * le graphe exécute sa `nextTask`
    * le graphe calcule quelle serait la prochaine `nextTask`
    * si elle existe, le graphe appelle `threadService.executeLater(nextTask)`
1. Dans le cas d'un verrou venant de `ModelLocks`
    * le graphe ne peut pas exécuter cette tâche, mais il s'inscrit dans la file d'attente pour le modèle
    * quand la tâche en cours pour ce modèle est enlevé de la file, on fait `threadService.executeLater(nextTaskInQueue)`

