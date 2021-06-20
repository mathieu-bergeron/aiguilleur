# Charger le `.js` d'un service au besoinO


1. Si on fait bundle=false en JSweet, on a un `.js` pour chaque classe Java
1. On devrait être en mesure de calculer la liste des `.js` à charger pour un service
1. On peut ensuite charger cette resource `.js` uniquement au moment où c'est nécessaire 
    * selon le graphe de tâche pour l'initialisation

LE GROS AVANTAGE est que le compilateur JS est utilisé uniquement au besoin plutôt que tout compiler le code JSweet (qui peut être volumineux) dès le chargement de la page


