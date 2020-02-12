# BibBatch : Batch de relances usagers de la bibliothéque municipale

##Contexte
Cette application BibBatch a été developpée par Alexis Dumay dans le cadre de son parcours developpeur d'application Java effectué avec OpenClassrooms en 2020.

##Contenu
Il s'agit d'une application Spring Boot qui consomme l'API de gestion des usagers et des ouvrages de la bibliothèque municipale afin d'envoyer des mails de relance aux usagers ayant des prets en retard.

##Pré-requis
Version de Java : 1.8
JDK : jdk1.8.0_201
Maven 3.6
Avoir lancé BibApp, l'API associée : https://github.com/dumaya/BIB-APIweb
Lancer le serveur SMTP : pour ce projet, un serveur local (port 25) smtp a été utilisé : fakeSMTP 
cf http://nilhcem.com/FakeSMTP/
une fois téléchargé, se lance par 'java -jar fakeSMTP-2.0.jar'

##Installation et déploiement
Installer et déployer l'application :
`mvn clean install spring-boot:run`

ou, en ligne de commande sur le jar : `java -jar BibBatch-0.0.1-SNAPSHOT.jar`

Sources disponibles sur : https://github.com/dumaya/BibBatch
