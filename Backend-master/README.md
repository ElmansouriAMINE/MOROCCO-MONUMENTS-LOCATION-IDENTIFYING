## Application Web de Gestion des monuments par villes et par zones
Ensaj-Monument est une application itinérante qui permet aux utilisateurs de rechercher dans leur voisinage afin de découvrir les points de repère les plus proches et les plus populaires.
### Principaux concepts théoriques: 
-   Conception et Developement d’une application Web de gestion des villes,monuments et zone. (Partie Backend)


#### Technologies utilisées:
-Le framework Spring Boot pour la partie back-end du projet réalisée en Java
-MySQL pour la base de données
-Java


#### Description

Gestion des flux :
-Gestion des monuments(identifiant,nom,longitude,lattitude,photo,ville)
-Gestion des villes(identifiant,nom,longitude,latitude,zone)
-Gestion des zones(identifiant,nom,longitude,latitude,ville)
Gestion des utilisateurs :
-Gestion de l'authentification par un token Bearer JWT
-Gestion des utilisateurs par l’administrateur

#### Conception UML

Voici la conception UML de notre application
*4.1* Diagramme de cas d'utilisation  | *4.2* Diagramme de classe 
:------------:|:---------------:
![Imgur](https://imgur.com/lPdVXvE.jpg)  |  ![Imgur](https://imgur.com/oa6v6jw.jpg) 
#### Architecture de l'application

Voici l'architecture générale de notre application
*5.1* Architecture générale
:------------:
![Imgur](https://imgur.com/KPrwjjI.jpg) 


### Outils de développement
-IDE : Eclipse
-Gestion de dépendences : Maven

### Comment utiliser cette application ?
Pour utiliser cette application, il suffit juste de modifier le fichier 'application.properties' pour mettre le nom de choix votre base de donnée et les coordonées d'accès à la base de données à savoir le 'username' et le 'password'.
