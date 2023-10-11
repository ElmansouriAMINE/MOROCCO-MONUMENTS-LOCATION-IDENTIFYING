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

