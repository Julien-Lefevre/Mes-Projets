Ce projet de 3ème année de Licence a été mené par : 
    - Téo Itsweire-Krebs
    - Antoine Promis
    - Julien Lefevre
L'objectif de ce projet été de créer un jeu textuel. 

# POO
Comment installer le jeu ?

Concernant l’installation, il faudra dans un premier temps lancer un terminal (Windows : Invite de commandes / MAC et Linux : Terminal). Il faudra ensuite se placer dans le répertoire nommé : « Lefevre_Promis_Itsweire-Krebs »

•	Windows :
Saisissez : « javac CLASS\*.java CLASS\ACTIONS\*.java CLASS\Character\*.java CLASS\Door\*.java CLASS\Item\*.java CLASS\Game\*.java CLASS\Room\*.java »
Puis pour exécuter : 
« java -cp . CLASS.Main »

•	Linux : 
Saisissez : « javac CLASS/*.java CLASS/*/*.java»
Puis pour exécuter : 
« java -cp . CLASS.Main »



Comment fonctionne le jeu ?

Le jeu consiste en un agencement de pièces, items et personnages dans lequel l'objectif est de trouver les sujets du BAC pour ne pas le rater et ainsi éviter d'être envoyé à l'école militaire.


Pour jouer, vous déplacer dans l'environnement et intéragir avec celui-ci, vous avez accès à des commandes avec des arguments ou non à saisir dans le terminal:

-ATTACK NomPersonnage
Enclenche une confrontation avec le personnage sélectionné, si le personnage a moins de puissance que vous, alors le personnage est battu, il ne vous adressera plus la parole. En revanche si c'est le cas inverse et que vous perdez le combat, la partie sera perdue.


-GO NomRoom
Vous amène dans la pièce renseignée si cela est possible.

-HELP
Affiche une aide par rapport aux commandes en jeu, à savoir leur nom et une courte description de la commande. 

-INVENTORY
Affiche l'ensemble de votre inventaire à l'écran.

-LOOK élément
LOOK avec 1 argument affiche la description de l'élément mis en argument.
LOOK sans argument affiche la description de la pièce dans laquelle le héros se situe actuellement.

-QUIT
Cette commande permet de mettre fin à la partie en cours et de stopper le programme.

-TAKE Object
Commande qui permet de prendre un objet donné dans une salle donnée si il est présent et que vous aussi.

-USE Argument1 Argument2
USE avec un item en Argument1 permet d'utiliser un item si vous le possédez.
USE avec un item en Argument1 et un autre item en Argument2 fait que les deux Items fusionnent (les items initiaux sont détruits).
USE avec un objet de type weapon fait que le personnage principal l'équipe, refaire cette commande le déséquipe.

-TALK People
Cette commande permet de parler avec le personnage en argument si il est présent dans la même salle.