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

        //Récupère les commandes du joueur et exécute l'action liée
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

            //Découpe la commande et appel executeCommand
            void parseCommand(const std::string& input, Joueur* j) const;

            //Lance l'exécution de la commande en appelant la méthode concernée dans le singleton Game
            void executeCommand(CommandType command, Joueur* j, std::string argument = "") const;
        };
        //Sélectionne l'Unité en paramètre
        void selectUnite(const std::string arg, const Joueur* j);
        //Vérifie si une unité adverse est dans une case adjacente à l'unité en paramètre
        void checkEnnemy(Joueur* j, Unite* u);
        //Déplace l'unité en paramètre selon un delta sur x et un delta sur y
        void deplacement(Joueur* j, Unite* u, int plus_x, int plus_y);
        //Permet un déplacement dans une case précise, dans le cas d'un retour
        //à la position initiale par exemple
        void deplacementPrecis(Joueur* j, Unite* u, int new_x, int new_y);
        //Renvoi l'Unité à sa position initiale et lui enlève le drapeau si elle l'avait
        void kill(Unite* u);
        //Apres un combat entre 2 troupes de choc, traitement spécifique
        void retreatAfterFight(Unite* u);
        //Désélectionne l'unité en attribut
        void unselectUnite();
        //Regarde les informations sur drapeau et agit en conséquence
        void gestionDrapeau(Drapeau* d, Joueur* j);
        //Vérifie si le drapeau peut être récupéré
        void checkpriseDrapeau(Drapeau* d, Joueur* j);
        //Fait prendre le drapeau à une unité
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
        //Renvoi le nom, la position et le nombre de déplacement restant
        //Pour chaque Unité du joueur passé en paramètre
        std::string getStateUnite(const Joueur* j);
        //Détermine si partie finie
        bool isGameFinished(); 
        //Renvoi le nom du joueur vainqueur
        std::string winner(); 
        //Boucle définissant le tour d'un joueur
        void playerLap(Joueur* j, Parseur& p, bool player); 
        //Met en place un observer attendant une notification de mort de
        //l'unité en paramètre et lance la fonction associée
        void setupUniteDeathObserver(Unite* u);
        //Observer sur combat entre 2 troupes de choc lançant la fonction associée
        void setupUniteRetreatObserver(Unite* u);
        //Permet de vider le terminal lorsqu'un joueur a fini son tour
        void clearTerminal() {
            #ifdef _WIN32
                system("cls");  // Windows
            #elif __APPLE__ || __linux__
                system("clear");  // macOS ou Linux
            #else
                std::cerr << "Platform non supportée!" << std::endl;
            #endif
        }
    public:
        //SINGLETON
        static Game* instance;
        static Game* getInstance();

        //Initialisation du jeu : unités, joueurs, plateau, drapeaux...
        void initGame(const std::string nomJoueur1, const std::string nomJoueur2);
        //Lance le jeu
        void play();
    };
}

