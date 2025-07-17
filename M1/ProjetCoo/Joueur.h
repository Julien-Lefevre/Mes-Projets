#pragma once
#include <string>
#include <vector>
#include "Unite.h"
#include <utility>
#include "Drapeau.h"
namespace kpt {
	class Joueur
	{
		std::string nom;
		std::vector<Unite*> unites;
		bool playerid;
		Drapeau* d;
		std::string savedinfos;
	public:
		Joueur(const std::string& nom, const std::vector<Unite*>& unite, bool playerid, Drapeau* d) : nom(nom), unites(unite), playerid(playerid), d(d), savedinfos("") {};
		Joueur(const Joueur& other)
			: nom(other.nom),
			unites(other.unites),
			playerid(other.playerid),
			d(other.d)
		{};
		~Joueur() {
			delete d;  // Suppression du pointeur sur le Drapeau
		};
		Joueur& operator=(const Joueur& other);
		std::vector<std::pair<int, int>> getUnitPos() const;
		//indique si l'Unité portant nomCherche appartient à ce joueur
		bool contains(const std::string nomCherche) const;
		//Renvoi l'unité si elle appartient au joueur
		Unite* getPtrUnite(const std::string nomUnite) const;
		//Remet les déplacements à jour sur un nouveau tour
		void reboot_unite_deplacements();
		bool getboolid() const { return this->playerid; };
		Drapeau* getDrapeau() const { return this->d; };
		std::string getNom() const { return this->nom; };
		//Efface les informations sauvegardées
		void clearSavedinfos() { this->savedinfos = ""; };
		std::string getSavedInfos() { return this->savedinfos; };
		//Ajoute des informations à sauvegarder 
		void addInfos(const std::string newinfo)
		{
			savedinfos.append(newinfo);
		};
		std::vector<Unite*> getAllUnite() const { return this->unites; };
	};
}
