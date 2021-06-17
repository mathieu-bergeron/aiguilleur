# Backend sur le client et Backend sur le serveur


1. Sur le client:  
    * modifie le modèle en local
        * conserve une liste de ModelUpdate depuis la dernière version
          envoyé du serveur
    * transfert le message au Backend sur le serveur
    * le Backend sur le serveur modifie le modèle sur le serveur
    * envoie des ModelUpdate aux clients qui observe les modèles modifiés
    * le client reçoit le ModelUpdate et 
        * compare sa liste de ModelUpdate avec ce qui a été envoyé par le serveur
        * applique les Update qui dépasse ce qu'il connaît déjà
        * ré-exécute le ViewModel au besoin
        * sauvegarde la nouvelle version envoyé du serveur
        * (vide sa liste de Update local)

# Appliquer d'abord en local, mais toujours envoyé au serveur

Donc:

1. Le client applique toujours les modifs en local d'abord
1. Mais on envoit toujours le message au serveur pour modifier les modèles sur le serveur aussi
1. Et on reçoit quand même une liste de ModelUpdates (à appliquer ou à fast-fowarder si ça correspond à ce qu'on a déjà appliqué)
