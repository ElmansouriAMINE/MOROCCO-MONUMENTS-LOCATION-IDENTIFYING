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
