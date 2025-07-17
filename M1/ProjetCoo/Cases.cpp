#include "Cases.h"
#include <sstream>
namespace kpt {
    Cases::~Cases()
    {
        delete unite;
        delete d;
    }
    Cases::Cases(const Cases& other) : nbMove(other.nbMove),
        nom(other.nom),
        isForbiddencase(other.isForbiddencase) {
        // Duplication profonde des pointeurs
        unite = other.unite ? other.unite->clone() : nullptr;
        d = other.d ? new Drapeau(*other.d) : nullptr;
    }
    
    Cases& Cases::operator=(const Cases& other)
    {
        if (this != &other) {
            // Libérer les ressources actuelles
            delete unite;
            delete d;

            // Copier les valeurs
            nbMove = other.nbMove;
            nom = other.nom;
            isForbiddencase = other.isForbiddencase;

            // Duplication profonde des pointeurs
            unite = other.unite ? other.unite->clone() : nullptr;
            d = other.d ? new Drapeau(*other.d) : nullptr;
        }
        return *this;
    }
    std::string Cases::affiche(bool visible)
    {
        return std::string("erreur");
    }

    bool Cases::setUnite(Unite* u)
    {
        this->unite = u;
        return true;
    }

    void Cases::uniteLeave()
    {
        this->unite = nullptr;
    }


    void Cases::setDrapeau(Drapeau* drapeau)
    {
        this->d = drapeau;
    }

    void Cases::drapeauLeave()
    {
        this->d = nullptr;
    }

    int Cases::getnbMove() const
    {
        return this->nbMove;
    }

    bool Cases::isEmptycase() const
    {
        if (this->unite == nullptr && this->d == nullptr && this->isForbiddencase == false)
        {
            return true;
        }
        else return false;
    }

    bool Cases::isEmptycaseUniteOnly() const
    {
        if (this->unite == nullptr)
        {
            return true;
        }
        else return false;
    }

    Unite* Cases::getUnite() const
    {
        return this->unite;
    }


    std::string TerrainNu::affiche(bool visible)
    {
        std::ostringstream os;
        if (this->d != nullptr)
        {
            if (this->unite == nullptr) {
                os << "\033[48;2;126;71;12m" << std::setw(2) << *(this->d) << " " << "\033[0m";
            }
            else {
                os << "\033[48;2;126;71;12m" << std::setw(2) << *(this->unite) << " " << "\033[0m";
            }

        }
        else {
            if (visible) {
                if (this->unite != nullptr) {
                    // Affichage avec une unité
                    os << "\033[48;2;146;255;52m" << *(this->unite) << " " << "\033[0m";
                }
                else
                {
                    // Case visible mais sans unité
                    os << "\033[48;2;146;255;52m" << std::setw(2) << " " << " " << "\033[0m";
                }
            }
            else {
                // Cas où visible est faux
                os << "\033[48;2;137;165;154m" << std::setw(2) << " " << " " << "\033[0m"; // Exemple d'affichage d'une case non visible
            }
            //std::cout << "je renvoi la fonction affiche." << std::endl;

        }
        return os.str();
    }


    std::string Foret::affiche(bool visible)
    {
        std::ostringstream os;
        if (this->d != nullptr)
        {
            if (this->unite == nullptr) {
                os << "\033[48;2;126;71;12m" << std::setw(2) << *(this->d) << " " << "\033[0m";
            }
            else {
                os << "\033[48;2;126;71;12m" << std::setw(2) << *(this->unite) << " " << "\033[0m";
            }

        }
        else {
            if (visible) {
                if (this->unite != nullptr) {
                    // Affichage avec une unité, texte noir sur fond vert foncé
                    os << "\033[38;5;0m\033[48;2;0;128;32m" << *(this->unite) << " " << "\033[0m";
                }
                else
                {

                    // Case visible mais sans unité
                    os << "\033[48;2;0;128;32m" << std::setw(2) << " " << " " << "\033[0m";

                }
            }
            else {
                // Cas où visible est faux
                os << "\033[48;2;110;131;88m" << std::setw(2) << " " << " " << "\033[0m"; // Gris sombre pour non visible
            }
        }

        return os.str();
    }


    bool Riviere::setUnite(Unite* u)
    {
        this->unite = u;
        this->nbMove = u->get_nb_deplacement_max();
        return true;
    }

    std::string Riviere::affiche(bool visible)
    {
        std::ostringstream os;
        if (this->d != nullptr)
        {
            if (this->unite == nullptr) {
                os << "\033[48;2;126;71;12m" << std::setw(2) << *(this->d) << " " << "\033[0m";
            }
            else {
                os << "\033[48;2;126;71;12m" << std::setw(2) << *(this->unite) << " " << "\033[0m";
            }

        }
        else {
            if (visible) {
                if (this->unite != nullptr) {
                    // Affichage avec une unité, texte noir sur fond bleu
                    os << "\033[38;5;0m\033[48;5;33m" << *(this->unite) << " " << "\033[0m";
                }
                else
                {

                    // Case visible mais sans unité
                    os << "\033[48;5;33m" << std::setw(2) << " " << " " << "\033[0m";


                }
            }
            else {

                // Cas où visible est faux
                os << "\033[48;2;16;9;40m" << std::setw(2) << " " << " " << "\033[0m"; // Gris sombre pour non visible
            }
        }


        return os.str();
    }
}


