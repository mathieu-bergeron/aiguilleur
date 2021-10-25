# Reconception du graphe des tâches

1. Une `Task` a la méthode suivante:
    * `notifyOfNewResult(TaskId resultFrom, ResultReader resultReader)`

1. Un `ResultReader` a les méthodes suivantes:
    * `hasNextResult()`
    * `nextResult()`

1. Une tâche maintient un `ResultReader` pour *chacune des tâches précédentes*
    * sur appel à `notifyOfNewResult(resultFrom, resultReader)`, la tâche mémorise le `resultReader`
    * si on a des résultats pour toutes les tâches précédentes, on peut s'exécuter, sinon il faut attendre
        * quand on s'éxécute, on consome un résultat avec `nextResult()`

1. Dès qu'elle est exécutée, une tâche "normale" appelle `notifyOfNewResult(this, new InfiniteReader(result))` de toutes ses tâches suivantes 
    * le `ResultReader` d'une tâche "normale" a toujours `hasNextResult` et retourne toujours le même résultat

1. Une tâche de type `MessageHandlerTask` va plutôt appeler `notifyOfNewResult(this, new SingleReader(result))` à chaque fois qu'un message est reçu et traité.
    * NOTE: si le message ne peut pas être traité, il est placé dans une file d'attente de messages.
    * le `ResultReader` d'une `MessageHandlerTask` va réellement consomer le résultat (1 seul résultat par message)
        * NOTE: il faut alors créer un nouveau `ResultReader` pour chaque tâche suivante (parce que chaque tâche suivante a l'opportunité de consomer 1 résultat)

# Exécution du graphe par "point fixe"

1. Le graphe pourrait avoir les méthodes
    * `canExecuteStep()`
    * `executeStep()`

1. Quand on exécute le graphe, on veut évaluer chaque noeud au moins une fois avant de recommencer
    * on veut propager partout dans le graphe avant de revenir à des noeuds déjà visités

1. Si l'évaluation ne change pas l'état du graphe, on peut arrêter 
    * (jusqu'au prochain message et/ou événement)

1. Dans un serveur HTTP, on va donc exécuter le graphe de l'App jusqu'à point fixe, et ensuite on sait qu'on peut répondre à la requête.
    * on va donc appeler `executeStep()` dès que possible, et lorsque ce n'est plus possible, on peut répondre à la requête.
    * l'exécution est aussi compatible avec Vertx, on peut exécuter de façon asynchrone: on appelle `executeStep()` dès que possible et ainsi on bloque sur au plus une tâche à la fois
