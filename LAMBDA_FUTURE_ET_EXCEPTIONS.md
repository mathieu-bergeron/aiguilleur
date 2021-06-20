# La gestion des exceptions est plus compliquée avec les lambda

1. Si le lambda lance une exception directement, il faut le déclaré partout où le lambda est utilisé

1. La solution est que des trucs comme reduceTo(Patate.class) retourne toujours un Future<Patate> 
    * Future<Patate> a une méthode `handle` pour récupérer les donneés
    * Future<Patate> a aussi une méthode `handleException` pour gérer les erreurs
