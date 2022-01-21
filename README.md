
# Utilisation 

## Creation de la base de donnée:

```
gradle -PmainClass='eu.telecomnancy.javafx.CreationBD' runApp
```
## Lancement de l'application

gradle run

## Commencement

Vous pouvez vous connecter avec le compte admin/admin afin de créer d'autres comptes utilisateur. Les comptes utilisateurs créent ont pour mot de passe leur nom (initialement, cela peut être changé) et comme identifiant leur email (considéré comme unique).

# Modules ou codes utilisés :

#Back

Pour la partie back, nous avons utilisé la base de données embarquée SQLite (disponible sur github). 
Pour se connecter à celle ci, nous utilisons les méthodes fournies par java.sql.
Notre bdd s'appelle TNRDV.db. Voici donc le chemin d'accès : jdbc:sqlite:BDD/TNRDV.db
Pour interragir avec celle_ci, nous commencons par ouvrir une connection grâce à la méthode getConnection de DriverManager (java.qsl).
Ensuite, on crée une requête avec la méthode createStatement.
On envoie ensuite celle-ci avec un executeQuery/updateQuery, ce qui nous retourne un objet ResultSet, soit un tableau contenant les données de la requête.
On ferme ensuite les connections, dans l'ordre : resultset, statement, connection.

Nous sommes conscients que notre bdd n'est pas sécurisée (injection) et a une structure très sommaire. Néanmoins, vu le temps que nous avions nous avons décidé de ne pas en tenir compte.



## Template Gradle JDK/JavaFX

Ce projet a utilisé un template pour créer un projet JavaFX en utilisant Gradle. (https://gitlab.telecomnancy.univ-lorraine.fr/Gerald.Oster/boilerplate-gradle-jdk15)

Il s'agit d'une version mise à jour du projet  [boilerplategradlejdk13](https://github.com/Typhon0/boilerplategradlejdk13) disponible sur GitHub

# Gestion de projet -> Voir dossier GDP
