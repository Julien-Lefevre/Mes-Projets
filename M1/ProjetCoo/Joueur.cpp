#include "Joueur.h"
namespace kpt {
    Joueur& Joueur::operator=(const Joueur& other)
    {
        if (this != &other) {
            nom = other.nom;
            unites = other.unites;
            playerid = other.playerid;
            d = other.d;
        }
        return *this;
    }
    std::vector<std::pair<int, int>> Joueur::getUnitPos() const
    {
        std::vector<std::pair<int, int>> res;
        for (int i = 0; i < this->unites.size(); ++i) {
            res.push_back(std::make_pair(unites[i]->getPosX(), unites[i]->getPosY()));
        }
        return res;
    }

    bool Joueur::contains(const std::string nomCherche) const
    {

        for (Unite* unite : unites) {
            if (unite && unite->getNom() == nomCherche) {
                return true; // Retourne True si une correspondance est trouvée
            }
        }
        return false; // Retourne false si aucune unité ne correspond
    }

    Unite* Joueur::getPtrUnite(const std::string nomUnite) const
    {
        for (Unite* unite : unites) {
            if (unite && unite->getNom() == nomUnite) {
                return unite; // Retourne le pointeur si une correspondance est trouvée
            }
        }
        return nullptr; // Retourne nullptr si aucune unité ne correspond
    }

    void Joueur::reboot_unite_deplacements()
    {
        for (Unite* unite : unites)
        {
            unite->reinit_nb_deplacement_restant();
        }
    }
}


