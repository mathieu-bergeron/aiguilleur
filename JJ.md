## JJ: JdkJSweet

1. Couche de compatibilité Jdk-JSweet

ca.ntro.jj


1. Tous les services qui ne sont pas spécifique à Ntro vont là:
    * RegEx
    * Collections
    * Assertion
    * Etc.

1. NOTE: si on met ThreadService dans Jj, alors on doit 
    * aussi mettre NtroMessage (les threads communiquent par message)
    * et aussi Task (?) et un TaskGraph de base (?)
    * après tout, c'est pas spécifique à Ntro (?)


1. NOTE: même l'envoi/réception de messages client/serveur aurait du sens
    * tout sauf Modèle et Vue et Controleur etc.

1. NOTE: même un notion de ResourceLoader, l'équivalent de charger un fichier et l'observer
    * genre Collection et Document
    * observer (watcher) un fichier, avec notion de diff et de ResourceUpdate

