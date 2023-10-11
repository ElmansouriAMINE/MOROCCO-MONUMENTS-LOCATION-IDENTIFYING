# MOROCCO-MONUMENTS-LOCATION-IDENTIFYING BY AMINE ELMANSOURI --DEVOPS AND SOFTWARE ENGINEER
MOROCCO-MONUMENTS-LOCATION-IDENTIFYING

##################### 
## PARTIE BACKEND
#####################

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



##################### 
## PARTIE FRONTALE
#####################


# Localisation des monuments-partie Front-End(ReactJs)
Cette partie du projet a pour but de mettre en place des interfaces érgonomiques et sofistiquées permettant d’afficher et localiser les monuments dans une ville choisie avec les détails de chaque monument . pour cet objectif , on a utilisé Reactjs comme framework front-end pour mettre en place les interfaces interéssentes de notre application.  


# Les avantages derrière le choix du ReactJs exactement
1. C’est intuitif
React JS aide à créer un code simple et intuitif afin que vous puissiez éviter les scénarios imprévisibles. La bibliothèque offre une gestion puissante des états, des actions et des événements. Bien que simple, ReactJS donne un contrôle total aux développeurs sur la façon dont il doit se comporter sur les actions des utilisateurs. La bibliothèque est en elle-même intuitive et permet une interactivité dans la disposition de l’interface utilisateur.
2. Flexible
La structure modulaire de ReactJS en fait l’un des meilleurs outils flexibles. Cela facilite la maintenance, ce qui permet d’économiser beaucoup de temps de développement et d’argent à long terme. Les applications créées avec cet outil sont faciles à mettre à l’échelle, à entretenir et deviennent très flexibles.
3. Processus de développement rapide
Les développeurs peuvent accélérer leur processus de développement car ils peuvent utiliser des parties individuelles de l’application et y apporter des modifications, sans affecter la logique de l’ensemble de l’application. Cela accélère plusieurs fois le processus de développement.
4. Des performances enviables
Une autre raison pour laquelle le développement Web et d’applications est plus rapide avec React est sa collaboration avec Virtual DOM. Avec cette fonctionnalité, seule la partie nécessaire du programme sera mise à jour au lieu de l’ensemble. Le DOM virtuel peut comparer les états précédents et réguliers des composants, ce qui aide à développer des résultats plus rapides.
5. La testabilité est un jeu d’enfant
ReactJS a une structure clairement définie, permettant de tester facilement les composants de l’interface utilisateur et le débogage. Donnez-lui le même ensemble de composants, un composant React donnera toujours la même sortie. Placez la sortie n’importe où, elle donnera les mêmes résultats, quel que soit le nombre de fois que vous la rendrez. Vous pouvez exécuter les tests dans un terminal ou sur un serveur d’intégration continue, et même tester sur plusieurs navigateurs simultanément.

# Fonctionnalités
1.	Sélection d'une ville dans la liste des villes disponibles
2.	Affichage des monuments dans la ville sélectionnée, groupées par zone
3.	Possibilité de filtrer les monuments affichées par nom.
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
# Mode d’emploi
Pour démarrer cette partie front-end( à noter il faut démarrer la partie backend en premier , pour consommer les APIS backend avec Axios) , suivez les étapes suivantes :
1.	Téléchargez le projet sur votre ordinateur
2.	Ouvrez-le dans votre éditeur de code (VScode par exemple)
3.	Tapez sur le terminal les commandes de lignes suivantes : npm start 
4.	Cliquer sur les listes des monuments,puis cliquer sur ajouter un monument
5.	Renseigner les champs,puis validé en cliquant sur save.
6.	Voir la liste des monuments
7.	Filterer cette liste avec un nom d’un monument
8.	Puis cliquer sur la carte(card item) qui concerne le monument choisi
9.	Visualiser leur détails avec des images
10.	Puis consulter son localisation exacte.

# Aperçu
Acceuil  |  Liste des monuments
:-------------:|:----------------:
![Imgur](https://imgur.com/bDaWB0Q.jpg)  |  ![Imgur](https://imgur.com/mBykDMz.jpg)

CRUD |  Ajout d'un monument 
:-------------:|:----------------:
![Imgur](https://imgur.com/Gp4bzbf.jpg)  |  ![Imgur](https://imgur.com/qF0OG1V.jpg)  

Détails |  Localisation du monument 
:-------------:|:----------------:
![Imgur](https://imgur.com/1J889kM.jpg)  |  ![Imgur](https://imgur.com/QFrhFxj.jpg)  




##################### 
## PARTIE MOBILE
#####################


## Ensaj-Monument

Ensaj-Monument est une application itinérante qui permet aux utilisateurs de rechercher dans leur voisinage afin de découvrir les points de repère les plus proches et les plus populaires.




### Principaux concepts théoriques :
-  Architecture propre
-  Programmation réactive
-  Modèles architecturaux (MVVM)
-  Développement Android

### Spécification

#### Aperçu

Ensaj-Monument est une application touristique implémentée pour Android créée dans le but de démontrer la manière dont les différents concepts architecturaux et
modèles de conception d'application peuvent être appliqués dans une pratique. L'objectif de cette documentation est d'entrer dans les détails du processus de développement et de l'architecture de l'application, en montrant comment les concepts théoriques ci-dessus ont été utilisés comme principes directeurs de l'application.
#### But

Le but de cette application est de fournir aux utilisateurs un moyen de découvrir les
monuments tout en explorant de nouvelles villes en étant averti lorsque vous êtes à proximité de
un tel point de repère.
Ainsi, la principale incitation de l'application est d'inciter ses utilisateurs à se promener librement
autour et explorer les villes qu'ils visitent plutôt que de garder constamment un œil sur
leurs appareils mobiles ou cartes physiques essayant de découvrir toutes les attractions disponibles.
L'application doit agir comme un GPS et basé sur des capteurs
boussole guidant l'utilisateur vers le monument le plus proche détecté.

#### Exigences fonctionnelles

- L'utilisateur doit pouvoir créer un compte avec une combinaison d'adresse e-mail
    et un mot de passe.
- L'utilisateur doit pouvoir préciser les détails souhaités tels que le rayon de recherche, la limite imposée au nombre de points de repère à trouver, ainsi que les catégories préférées.
    Après avoir recherché des points d'intérêt sur la base des critères ci-dessus, l'application
    doit présenter le nombre de repères découverts. L'utilisateur doit alors avoir le
    possibilité de démarrer la session ou d'ajuster les critères de recherche. 
- L'application doit fournir un mécanisme pour guider l'utilisateur vers la balise installée la plus proche en indiquant la direction générale et la distance de celle-ci.
- Les utilisateurs doivent pouvoir visualiser leurs monuments et leurs points de repère découverts ainsi que leurs images respectives.

#### Exigences non fonctionnelles


*Exigences d'utilisabilité*

- L'application doit fournir des indications claires relatives à son utilisation et des messages d'erreur compréhensibles en cas de pannes inattendues.
- L'application doit être conçue avec la prise en charge de l'accessibilité activée par le système
    fonctionnalités telles que les lecteurs d'écran.
- L'application doit suivre un langage de conception cohérent en termes de polices,
    taille du texte, images et jeu de couleurs.
*Exigences de fiabilité*

- L'application doit être capable de gérer les changements environnementaux tels que les changements
    dans l'orientation de l'écran, la batterie faible de l'appareil ou les changements de préférences à l'échelle du système.


*Exigences de performances*

- L'application doit recevoir des mises à jour de localisation fréquentes afin de fournir les directions les plus précises possibles.
- L'interface utilisateur ne doit jamais être bloquée en raison d'opérations de longue durée telles que
    comme des requêtes réseau ou des actions de lecture/écriture de base de données, mais fournissent des repères visuels de
    leur présence.

*Exigences de prise en charge*

- Les fonctionnalités indépendantes du système doivent être modifiables avec des changements minimes
    à la base de code et sans interférer avec celles qui ne sont pas liées.
- La candidature doit fournir un moyen concis et général de mise en œuvre
    nouvelles fonctionnalités.
- L'application doit être utilisable de la même manière sur n'importe quel téléphone ou
    modèle de tablette fonctionnant sur un niveau d'API Android d'au moins 24.
- L'application doit enregistrer et suivre les erreurs survenues et ne collecter que les
    données pertinentes au contexte de la faute et uniquement de son propre point de vue.
    
    
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
#### Technologies et bibliothèques

En ce qui concerne les technologies et les bibliothèques utilisées lors de la mise en œuvre, les suivantes peuvent être mises en évidence :


- *Retrofit* : Providing a concise interface for creating and managing REST API requests with the possibility of wrapping them into observable sources.
    
    
Acceuil  |  Radius Configuration  | les monuments présents
:-------------:|:----------------:|:----------------:
![Imgur](https://imgur.com/vKYnctQ.jpg)  |  ![Imgur](https://imgur.com/L3ASB4o.jpg) | ![Imgur](https://imgur.com/GBgJluQ.jpg)

informations |  list des monuments 
:-------------:|:----------------:
![Imgur](https://imgur.com/Y6Zak7K.jpg)  |  ![Imgur](https://imgur.com/zrbbb2O.jpg)





