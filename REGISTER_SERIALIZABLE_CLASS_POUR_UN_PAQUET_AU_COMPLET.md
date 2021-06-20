# Utiliser les paquets pour simplifier le `registerSerializableClass`

1. On peut utiliser `.getClass().getName()` et analyser le nom complet pour trouver le paquet.
1. Ensuite, en Javascript, le paquet est un objet!
    `Object.keys(ca.ntro.messages.ntro_messages)` donnes les clés (donc les noms de classe)
1. On va pouvoir automatiquement appeler `registerSerializableClass` pour toutes les classes du paquet

NOTE: 

* on peut utiliser `packageCa = eval("ca")` pour accéder au paquet `ca` à partir de son nom

## Recommandations

1. Mettre tous les modèles dans un seul paquet, avec une classe vide genre package-info.java
1. Mettre tous les messages dans un seul paquet, avec une classe vide package-info.java


# NOTE: getPackage n'existe pas en JSweet

1. l'idée est de faciliter la déclaration des JsonSerializable en déclarant un paquet au complet à la fois

