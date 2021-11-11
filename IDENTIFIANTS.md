# Gérer les identifiants

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




