# Gérer les identifiants

## StorageId (aussi ModelId)

* `categoryPath`:    `/models/QueueModel`                  (comme un répertoire)
* `entityPath`:      `alice¤2021¤strudon`                  (comme un nom de fichier)
* `fileExtension`:  `.json`                 

## ModelValueId

* `categoryPath`:    `/models/QueueModel`                  (comme un répertoire)
* `entityPath`:      `alice¤2021¤strudon`                  (comme un nom de fichier)
* `fileExtension`:  `.json`                 
* `valuePath`:       `appointmentsInOrder.1.studentId`          (comme un chemin dans le graphe d'objet)
                 OU: `appointmentsInOrder/1/studentId`          (comme un chemin dans un fichier Json)
                 OU: `appointmentsInOrder[1].studentId`         (comme un chemin dans un fichier Json)


## Chemin dans graphe

* `edgeWalk`: `asd.asdf.asdf`

## Est-ce qu'on a besoin d'un chemin dans un chemin?

* ex: `1¤3/a¤b¤c/d¤d`
* p.ex. si `1¤3` est un HierarchicalNodeId et que `1¤3/a¤b¤c` est donc un chemin dans un graphe hiearchique






## 








* SimpleId: une chaîne dans ¤ ou /
* PathId: entityPath où chaque segment est un SimpleId
* StorageId: categoryPath + entityPath, p.ex.  `/models/QueueModel/alice¤test¤2021`


## Dans MongoDB (pour la prod, les logs en binaire)

Collection: `models/QueueModel`
EntityId:    `alice¤test¤2021`

## Dans des fichiers (pour le DEV)

Chemin: `_storage/models/QueueModel/alice¤test¤2021.json`

## Dans `window.localStorage`

Clé: `/models/QueueModel/alice¤test¤2021`




