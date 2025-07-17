#pragma once
#include "Unite.h"
#include <string>
#include <iostream>
namespace kpt {
	class Drapeau
	{
	private:
		int pos_init_x;
		int pos_init_y;
		int pos_x;
		int pos_y;
		int lap_left_after_falling;
		Unite* carriedBy;
		std::string color;

	public:
		Drapeau(int x, int y, std::string color) : pos_x(x), pos_y(y), carriedBy(nullptr), pos_init_x(x), pos_init_y(y), lap_left_after_falling(0), color(color) {};
		~Drapeau();
		Drapeau(const Drapeau& other)
			: pos_init_x(other.pos_init_x), pos_init_y(other.pos_init_y),
			pos_x(other.pos_x), pos_y(other.pos_y),
			lap_left_after_falling(other.lap_left_after_falling),
			carriedBy(other.carriedBy) {
		};
		Drapeau& operator=(const Drapeau& other);
		//Indique si le drapeau est porté 
		bool getIsCarried() const;

		int getPos_x() const { return this->pos_x; };
		int getPos_y() const { return this->pos_y; };
		int getPos_init_x() const { return this->pos_init_x; };
		int getPos_init_y() const { return this->pos_init_y; };
		//indique si le drapeau est dans sa base
		bool isInPos_init() const;
		void updateLapLeftAfterFalling(int newvalue);
		int getlapLeftAfterFalling() const { return this->lap_left_after_falling; };
		void setnewPos(int x, int y);
		//Renvoi le drapeau en position initiale
		void goBack_pos_init();
		//permet d'indiqué qui vient de récupérer le drapeau
		void setcarriedBy(Unite* u);
		//met à jour la position du drapeau selon la position de l'unité le portant
		void updatePoswhileCarried();
		Unite* getCarrier() const { return this->carriedBy; };
		//Lance le décompte des 2 tours avant de revenir à sa base
		//Supprime le porteur
		void deathCarryingUnite();
		friend std::ostream& operator<<(std::ostream& os, const Drapeau& d);
	

	};
}
