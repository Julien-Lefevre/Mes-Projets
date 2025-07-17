#include "Drapeau.h"
#include <iomanip>
namespace kpt {
	Drapeau::~Drapeau()
	{
		carriedBy = nullptr;
	}
	Drapeau& Drapeau::operator=(const Drapeau& other)
	{
		if (this != &other) { // Éviter l'auto-affectation
			pos_init_x = other.pos_init_x;
			pos_init_y = other.pos_init_y;
			pos_x = other.pos_x;
			pos_y = other.pos_y;
			lap_left_after_falling = other.lap_left_after_falling;
			carriedBy = other.carriedBy; // Copie du pointeur (shallow copy)
		}
		return *this;
	}
	
	bool Drapeau::getIsCarried() const
	{
		return (this->carriedBy != nullptr);
	}

	bool Drapeau::isInPos_init() const
	{
		return (this->pos_x == this->pos_init_x && this->pos_y == this->pos_init_y);
	}

	void Drapeau::updateLapLeftAfterFalling(int newvalue)
	{
		this->lap_left_after_falling = newvalue;
	}

	void Drapeau::setnewPos(int x, int y)
	{
		this->pos_x = x;
		this->pos_y = y;
	}

	void Drapeau::goBack_pos_init()
	{
		this->pos_x = this->pos_init_x;
		this->pos_y = this->pos_init_y;
	}

	void Drapeau::setcarriedBy(Unite* u)
	{
		this->carriedBy = u;
		std::cout << "Le drapeau est pris par " << u->getNom() << std::endl;
	}

	void Drapeau::updatePoswhileCarried()
	{
		if (!this->getIsCarried())
		{
			std::cerr << "Erreur Drapeau.h : updatePoswhileCarried" << std::endl;
		}
		else {
			this->pos_x = this->carriedBy->getPosX();
			this->pos_y = this->carriedBy->getPosY();
		}
	}

	void Drapeau::deathCarryingUnite()
	{
		this->carriedBy = nullptr;
		this->lap_left_after_falling = 2;
	}

	std::ostream& operator<<(std::ostream& os, const Drapeau& d)
	{
		return os << d.color << std::setw(2) << "D";
	}
}