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
