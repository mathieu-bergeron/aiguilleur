# La gestion des exceptions est plus compliquée avec les lambda

1. Si le lambda lance une exception directement, il faut le déclaré partout où le lambda est utilisé

1. La solution est que des trucs comme reduceTo(Patate.class) retourne toujours un Future<Patate> 
    * Future<Patate> a une méthode `handle` pour récupérer les donneés
    * Future<Patate> a aussi une méthode `handleException` pour gérer les erreurs

On a donc typiquement:

<pre>

    Future<Patate> result = blah.reduceTo(Patate.class, null, (index, item, accumulator) -> {

        // [...]

        return accumulator;
    });

    result.handleResult( patate -> {

        // [...]

    });

    result.handleException( e -> {

        // [...]

    });
</pre>

## Note sur les Futures

1. Un Future est un wrapper sur un graphe de tâches
    * dans certain cas c'est une graphe à une seules tâches
    * mais p.ex. quand on requiert un service, on peut imaginer
        * une tâche pour récupérer la resource du service (le `.js` du service)
        * une tâche pour évaluer le `.js`
        * une tâche pour initialiser
        * et après on est prêt

1. Quand le graphe de tâche a terminé de s'exécuter, le Future appelle `handleResult`
    * s'il y a une exception durant l'exécution du graphe, le Future appelle `handleException`


