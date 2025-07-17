#include "Unite.h"
#include <cmath>
#include <random>
#include <iomanip>
namespace kpt {
	Unite::~Unite()
	{
		for (auto& pair : observers) {
			pair.second.clear();
		}
		observers.clear();
	}

	void Unite::reinit_nb_deplacement_restant()
	{
		this->nb_deplacement_restant = this->nb_deplacement_max;
	}

	void Unite::deplacement(int new_x, int new_y)
	{
		this->pos_x = new_x;
		this->pos_y = new_y;
	}

	int Unite::get_posX_init() const
	{
		return this->pos_x_init;
	}

	int Unite::get_posY_init() const
	{
		return this->pos_y_init;
	}


	int Unite::get_nb_deplacement_max() const
	{
		return this->nb_deplacement_max;
	}


	int Unite::getPosX() const
	{
		return this->pos_x;
	}

	int Unite::getPosY() const
	{
		return this->pos_y;
	}

	std::string Unite::getNom() const
	{
		return this->nom;
	}

	void Unite::setNewPos(int x, int y)
	{
		this->pos_x = x;
		this->pos_y = y;
	}

	int Unite::get_nb_deplacement_restant() const
	{
		return this->nb_deplacement_restant;
	}

	void Unite::set_nb_deplacement_restant(int i)
	{
		this->nb_deplacement_restant = i;
	}

	void Unite::addObserver(EventType type, Observer observer)
	{
		observers[type].push_back(observer);
	}

	void Unite::notify(EventType type)
	{
		for (auto& obs : observers[type]) {
			obs(*this); // Appelle tous les observateurs pour ce type d'événement
		}
	}

	Eclaireur::~Eclaireur() {
	};

void Eclaireur::toFight(Unite* u) {
	if (u != nullptr)
		u->fight(*this);
	else std::cerr << "Erreur combat : l'unité combattue n'existe pas." << std::endl;
}
void Eclaireur::fight(Eclaireur& e)
{
	//Rien ne se passe. 
}
void Eclaireur::fight(Troupes_de_choc& tdc)
{
	if (!tdc.hasTheDrapeau())
		this->notify(EventType::Death); 
}
void Eclaireur::fight(Chairs_a_canon& cac)
{
	if (!cac.hasTheDrapeau())
		this->notify(EventType::Death);
}
std::string Eclaireur::toString() const
{
	return "E";
}
;

	Troupes_de_choc::~Troupes_de_choc()
	{
	}

	void Troupes_de_choc::toFight(Unite* u)
	{
		if (u != nullptr)
			u->fight(*this);
		else std::cerr << "Erreur combat : l'unité combattue n'existe pas." << std::endl;
	}

void Troupes_de_choc::fight(Eclaireur& e)
{
	if (!this->hasTheDrapeau())
		e.notify(EventType::Death);
}

void Troupes_de_choc::fight(Troupes_de_choc& tdc)
{
	if (!this->hasTheDrapeau() && !tdc.hasTheDrapeau())
	{
		this->notify(EventType::Retreat);
		tdc.notify(EventType::Retreat);
		return;
	}

	if (this->hasTheDrapeau())
	{
		this->notify(EventType::Death);
		return;
	}

	if (tdc.hasTheDrapeau())
	{
		tdc.notify(EventType::Death);
		return;
	}

}
void Troupes_de_choc::fight(Chairs_a_canon& cac)
{
	if (this->hasTheDrapeau())
	{
		this->notify(EventType::Death);
		return;
	}
	cac.notify(EventType::Death);
}

	std::string Troupes_de_choc::toString() const
	{
		return "T";
	}


	Chairs_a_canon::~Chairs_a_canon()
	{
	}

	void Chairs_a_canon::toFight(Unite* u)
	{
		if (u != nullptr)
			u->fight(*this);
		else std::cerr << "Erreur combat : l'unité combattue n'existe pas." << std::endl;
	}

void Chairs_a_canon::fight(Eclaireur& e)
{
	if (!this->hasTheDrapeau())
		e.notify(EventType::Death);
}

void Chairs_a_canon::fight(Troupes_de_choc& tdc)
{
	if (tdc.hasTheDrapeau())
	{
		tdc.notify(EventType::Death);
		return;
	}
	this->notify(EventType::Death);
}

void Chairs_a_canon::fight(Chairs_a_canon& cac)
{
	if (this->hasTheDrapeau())
	{
		this->notify(EventType::Death);
		return;
	}
	if (cac.hasTheDrapeau())
	{
		cac.notify(EventType::Death);
		return;
	}
	std::default_random_engine re(static_cast<unsigned int>(time(0)));
	std::uniform_int_distribution<int> distrib{ 1,  100 };
	if (distrib(re) <= 50)
	{
		this->notify(EventType::Death);
	}
	else 
	{ 
		cac.notify(EventType::Death);
	}
}

	std::string Chairs_a_canon::toString() const
	{
		return "C";
	}

	std::ostream& operator<<(std::ostream& os, const Unite& u) {
		if (!u.hasTheDrapeau())
			return os << u.teamColor << std::setw(2) << u.toString();
		else return os << u.colorWithDrapeau << std::setw(2) << u.toString();
	}
	
}

