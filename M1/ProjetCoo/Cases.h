#pragma once
#include <string>
#include <iostream>
#include "Unite.h"
#include "Drapeau.h"
#include <iomanip>
namespace kpt {
	class Cases
	{
	protected:
		int nbMove;
		std::string nom;
		Unite* unite;
		Drapeau* d;
		bool isForbiddencase;
	public:
		Cases(int nbMove, const std::string& nom) { this->nbMove = nbMove; this->nom = nom; this->unite = nullptr; this->d = nullptr; this->isForbiddencase = false;  };
		virtual ~Cases();
		Cases(const Cases& other);
		Cases& operator=(const Cases& other);
		virtual std::string affiche(bool visible) = 0;
		//Permet de placer l'unite sur la case
		virtual bool setUnite(Unite* u);
		//permet d'enlever l'unite de la case
		void uniteLeave();
		//permet de placer le drapeau sur la case
		void setDrapeau(Drapeau* drapeau);
		//indique si un drapeau est sur la case
		bool isThereDrapeau() const { return this->d != nullptr; };
		//Permet de retirer le drapeau de la case
		void drapeauLeave();
		//Renvoi le nombre de déplacements nécessaires pour quitter la case
		int getnbMove() const;
		//Indique si la case est prise soit par une unité soit par un drapeau
		//renvoi faux si c'est une case interdite
		bool isEmptycase() const;
		//indique si une unité est sur la case
		bool isEmptycaseUniteOnly() const;

		Unite* getUnite() const;
		bool isCaseForbidden() const { return (this->isForbiddencase == true); };
		void setForbiddencase() { this->isForbiddencase = true; };
	};

	class TerrainNu : public Cases {
	public:
		TerrainNu() : Cases(1, "Nu") {};
		std::string affiche(bool visible);
	};

	class Foret : public Cases {
	public:
		Foret() : Cases(2, "Foret") {};
		std::string affiche(bool visible);
	};

	class Riviere : public Cases {
	public:
		Riviere() : Cases(0, "Riviere") {};
		bool setUnite(Unite* u) override;
		std::string affiche(bool visible);
	};
}



