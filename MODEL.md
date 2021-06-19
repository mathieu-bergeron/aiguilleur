On a:

* ModelDescription (ou DocumentPath) qui identifie le modèle
* ModelSnapshot: une version read-only du modèle (pour le ViewModel)
* ModelUdpates: une liste des révisions à un modèle

Dans NtroModel, ajouter

* findSubModelById()  : pour que le contrôleur puisser gérer les sous-modèles automatiquement
