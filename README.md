
# Utilisation 

## Creation de la base de donnée:

```
gradle -PmainClass='eu.telecomnancy.javafx.CreationBD' runApp
```
## Lancement de l'application

```
gradle run 
```

## Commencement

Vous pouvez vous connecter avec le compte admin/admin afin de créer d'autres comptes utilisateur. Les comptes utilisateurs créent ont pour mot de passe leur nom (initialement, cela peut être changé) et comme identifiant leur email (considéré comme unique).

# Modules ou codes utilisés :

Nous avons décidé de créer une application uniquement locale bien qu'elle serait portable sur un serveur avec les bonnes interfaces de requettes HTTPS. Nous avons chercher à respecté au mieux les normes de création (vue,modèle) bien que nous avons pris de la liberté pour des questions d'optimisation de temps. (Temps qui a beaucoup été utilisé pour du débug.)

## Front

Pour la partie front, on a tout d'abord dessiné les "captures d'écran" des différentes fenêtres constituant l'application. Pour implémenter l'interface, on a utilisé javafx et scene builder. Ainsi, on a tout d'abord créé les fichiers fxml avec scene builder pour chaque fenêtre et ensuite on a créé les contrôleurs correspondants. Les contrôleurs sont liés au modèle. En ce qui concerne les informations a à afficher, on peut les obtenir grâces au modèle divisé en plusieurs fichiers dont certains vont faire des appels à la base de donnée. Nous avons une instance principale ProfRdv (très couplé avec les controlleurs) qui nous permet d'accéder à un certaine nombre de classes, l'utilisateur actuel...


## Back

Pour la partie back, nous avons utilisé la base de données embarquée SQLite (disponible sur github). 
Pour se connecter à celle ci, nous utilisons les méthodes fournies par java.sql.
Notre bdd s'appelle TNRDV.db. Voici donc le chemin d'accès : jdbc:sqlite:BDD/TNRDV.db
Pour interragir avec celle_ci, nous commencons par ouvrir une connection grâce à la méthode getConnection de DriverManager (java.qsl).
Ensuite, on crée une requête avec la méthode createStatement.
On envoie ensuite celle-ci avec un executeQuery/updateQuery, ce qui nous retourne un objet ResultSet, soit un tableau contenant les données de la requête.
On ferme ensuite les connections, dans l'ordre : resultset, statement, connection.

Nous sommes conscients que notre bdd n'est pas sécurisée (injection, mot de passe non haché...) et a une structure très sommaire. Néanmoins, vu le temps que nous avions nous avons décidé de ne pas en tenir compte.



## Template Gradle JDK/JavaFX

Ce projet a utilisé un template pour créer un projet JavaFX en utilisant Gradle. (https://gitlab.telecomnancy.univ-lorraine.fr/Gerald.Oster/boilerplate-gradle-jdk15)

Il s'agit d'une version mise à jour du projet  [boilerplategradlejdk13](https://github.com/Typhon0/boilerplategradlejdk13) disponible sur GitHub

# Gestion de projet et documents de conception -> Voir dossier GDP
