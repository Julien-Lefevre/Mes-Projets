#pragma once
#include "Joueur.h"
#include "Plateau.h"
#include <string>
#include <unordered_map>
#include <iostream>
#include "Unite.h"
#include <sstream>
#include "Drapeau.h"
#include <cstdlib>
namespace kpt {
    class Game
    {
    private:
        Joueur* J1;
        Joueur* J2;
        Plateau<20, 20> plateau;
        //Unite sur laquelle les actions s'effectuent
        Unite* selectedUnite;
        std::string help;

        //Toutes les commandes existantes
        enum class CommandType {
            SELECT,
            UP,
            DOWN,
            LEFT,
            RIGHT,
            UP_LEFT,
            UP_RIGHT,
            DOWN_LEFT,
            DOWN_RIGHT,
            END_UNITE_LAP,
            END_LAP,
            HELP
        };

        //R�cup�re les commandes du joueur et ex�cute l'action li�e
        class Parseur
        {
        private:
            std::unordered_map<std::string, CommandType> commandMap;
            Game* game;

        public:
            Parseur(); // Constructeur pour initialiser la map des commandes
            Parseur(const Parseur& other)
                : commandMap(other.commandMap),
                game(other.game)
            {};
            Parseur& operator=(const Parseur& other);
            ~Parseur() {};

            //D�coupe la commande et appel executeCommand
            void parseCommand(const std::string& input, Joueur* j) const;

            //Lance l'ex�cution de la commande en appelant la m�thode concern�e dans le singleton Game
            void executeCommand(CommandType command, Joueur* j, std::string argument = "") const;
        };
        //S�lectionne l'Unit� en param�tre
        void selectUnite(const std::string arg, const Joueur* j);
        //V�rifie si une unit� adverse est dans une case adjacente � l'unit� en param�tre
        void checkEnnemy(Joueur* j, Unite* u);
        //D�place l'unit� en param�tre selon un delta sur x et un delta sur y
        void deplacement(Joueur* j, Unite* u, int plus_x, int plus_y);
        //Permet un d�placement dans une case pr�cise, dans le cas d'un retour
        //� la position initiale par exemple
        void deplacementPrecis(Joueur* j, Unite* u, int new_x, int new_y);
        //Renvoi l'Unit� � sa position initiale et lui enl�ve le drapeau si elle l'avait
        void kill(Unite* u);
        //Apres un combat entre 2 troupes de choc, traitement sp�cifique
        void retreatAfterFight(Unite* u);
        //D�s�lectionne l'unit� en attribut
        void unselectUnite();
        //Regarde les informations sur drapeau et agit en cons�quence
        void gestionDrapeau(Drapeau* d, Joueur* j);
        //V�rifie si le drapeau peut �tre r�cup�r�
        void checkpriseDrapeau(Drapeau* d, Joueur* j);
        //Fait prendre le drapeau � une unit�
        void takedrapeau(Drapeau* d, Unite* u);
        //Renvoi le message d'aide pour jouer (les commandes possibles)
        std::string gethelp() const { return this->help; };

        //SINGLETON
        Game(const Game&) = delete;
        Game& operator=(const Game&) = delete;
        Game();
        ~Game() {
            delete J1;
            delete J2;
            this->selectedUnite = nullptr;
        };
        //Renvoi le nom, la position et le nombre de d�placement restant
        //Pour chaque Unit� du joueur pass� en param�tre
        std::string getStateUnite(const Joueur* j);
        //D�termine si partie finie
        bool isGameFinished(); 
        //Renvoi le nom du joueur vainqueur
        std::string winner(); 
        //Boucle d�finissant le tour d'un joueur
        void playerLap(Joueur* j, Parseur& p, bool player); 
        //Met en place un observer attendant une notification de mort de
        //l'unit� en param�tre et lance la fonction associ�e
        void setupUniteDeathObserver(Unite* u);
        //Observer sur combat entre 2 troupes de choc lan�ant la fonction associ�e
        void setupUniteRetreatObserver(Unite* u);
        //Permet de vider le terminal lorsqu'un joueur a fini son tour
        void clearTerminal() {
            #ifdef _WIN32
                system("cls");  // Windows
            #elif __APPLE__ || __linux__
                system("clear");  // macOS ou Linux
            #else
                std::cerr << "Platform non support�e!" << std::endl;
            #endif
        }
    public:
        //SINGLETON
        static Game* instance;
        static Game* getInstance();

        //Initialisation du jeu : unit�s, joueurs, plateau, drapeaux...
        void initGame(const std::string nomJoueur1, const std::string nomJoueur2);
        //Lance le jeu
        void play();
    };
}

