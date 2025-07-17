#pragma once
#include "Cases.h"
#include <iostream>
#include <iomanip>
#include <string>
#include <sstream>
#include <fstream>
#include <vector>
#include <utility>
namespace kpt {
	template<int n, int k>
	class Plateau
	{
		Cases*** plateau = nullptr;
		bool** visitedPlayer1 = nullptr;
		bool** visitedPlayer2 = nullptr;
		bool** player1Vision = nullptr;
		bool** player2Vision = nullptr;
	public:
		Plateau() {
			plateau = new Cases * *[n];
			for (int i = 0; i < n; ++i) {
				plateau[i] = new Cases * [k];
				for (int j = 0; j < k; ++j) {
					plateau[i][j] = nullptr; // Initialise chaque pointeur à nullptr
				}
			}

			std::string const nomFichier("Map.txt");

			std::ifstream monFlux(nomFichier.c_str());

			if (!monFlux) {
				throw new std::runtime_error("Erreur lors de l'ouverture du fichier !");
			}

			std::string ligne;
			int nbline = 0;

			while (std::getline(monFlux, ligne)) {
				if (nbline >= n) {
					throw std::runtime_error("Trop de lignes dans le fichier !");
				}

				for (int i = 0; i < k; ++i) {
					if (i >= ligne.size()) {
						throw std::runtime_error("Ligne trop courte dans le fichier !");
					}

					switch (ligne[i]) {
					case 'N':
						plateau[nbline][i] = new TerrainNu;
						break;
					case 'R':
						plateau[nbline][i] = new Riviere;
						break;
					case 'F':
						plateau[nbline][i] = new Foret;
						break;
					default:
						throw std::runtime_error("Type de case inconnu : " + std::string(1, ligne[i]));
					}
				}
				++nbline;
			}

			if (nbline < n) {
				throw std::runtime_error("Pas assez de lignes dans le fichier !");
			}

			monFlux.close();

			visitedPlayer1 = new bool* [n];
			for (int i = 0; i < n; ++i) {
				visitedPlayer1[i] = new bool[k];
			}


			visitedPlayer2 = new bool* [n];
			for (int i = 0; i < n; ++i) {
				visitedPlayer2[i] = new bool[k];
			}

			player1Vision = new bool* [n];
			for (int i = 0; i < n; ++i) {
				player1Vision[i] = new bool[k];
			}

			player2Vision = new bool* [n];
			for (int i = 0; i < n; ++i) {
				player2Vision[i] = new bool[k];
			}

			for (int x = 0; x < n; ++x) {
				for (int y = 0; y < n; ++y) {
					visitedPlayer1[x][y] = 0;
					visitedPlayer2[x][y] = 0;
					player1Vision[x][y] = 0;
					player2Vision[x][y] = 0;
				}
			}
		}

		~Plateau() {
			for (int i = 0; i < n; ++i) {
				delete[] plateau[i];
				delete[] visitedPlayer1[i];
				delete[] visitedPlayer2[i];
				delete[] player1Vision[i];
				delete[] player2Vision[i];
			}
			delete[] plateau;
			delete[] visitedPlayer1;
			delete[] visitedPlayer2;
			delete[] player1Vision;
			delete[] player2Vision;
		}

		Cases** operator[](int x) const {
			//std::cout << "n vaut : " << n << std::endl;
			//std::cout << "x vaut : " << x << std::endl;
			if (x >= 0 && x < n) {
				//std::cout << "VALIDER\n";
				return plateau[x];
			}
			throw std::out_of_range("Index hors limites");
		}

		void affiche(bool player, std::vector<std::pair<int, int>> pos, int pos_dx, int pos_dy) {

			bool** vision = nullptr;
			bool** visited = nullptr;

			if (player) {
				vision = this->player1Vision;
				visited = this->visitedPlayer1;
			}
			else {
				vision = this->player2Vision;
				visited = this->visitedPlayer2;
			}

			this->setPlayerVision(player, pos);

			std::string numberLine = "      ";
			// Ligne de séparation
			std::string ligneSeparation = "     +";
			for (int i = 0; i < k; ++i) {
				numberLine += (std::ostringstream() << std::setw(2) << std::setfill(' ') << i + 1).str() + "  ";
				ligneSeparation += "---+";
			}

			std::cout << numberLine << std::endl;

			// Affichage du plateau
			for (int x = 0; x < n; ++x) {
				std::cout << ligneSeparation << std::endl; // Ligne de séparation
				std::cout << " " << std::setw(2) << x + 1 << "  |";
				for (int y = 0; y < k; ++y) {
					//std::cout << this->plateau[x][y]->getnbMove() << std::endl;
					//std::cout << "\n" << x << "   " << y << std::endl;
					
					if (visited[x][y]) {
						//std::cout << "APRES VISITED" << std::endl;
						if (vision[x][y]) {
							//std::cout << "APRES VISION 1" << std::endl;
							std::cout << this->plateau[x][y]->affiche(1) << "|"; // Valeurs alignées
						}
						else {
							std::cout << this->plateau[x][y]->affiche(0) << "|"; // Valeurs alignées
						}
					}
					else {
						if (vision[x][y]) {
							//std::cout << "APRES VISION 2" << std::endl;
							std::cout << this->plateau[x][y]->affiche(1) << "|"; // Valeurs alignées
						}
						else
						{
							if (this->plateau[x][y]->isThereDrapeau())
							{
								std::cout << this->plateau[x][y]->affiche(0) << "|";
							}
							else {
								std::cout << std::setw(2) << ' ' << " |";
							}
						}
					}
					
				}
				std::cout << std::endl;
			}
			std::cout << ligneSeparation << std::endl; // Dernière ligne de séparation
		}

		void visitCase(bool player, int x, int y) {
			if (player) {
				this->visitedPlayer1[x][y] = 1;
			}
			else {
				this->visitedPlayer2[x][y] = 1;
			}
		}

		void setPlayerVision(bool player, std::vector<std::pair<int, int>> pos) {
			bool** vision = nullptr;
			bool** visited = nullptr;


			std::vector<std::pair<int, int>> decalages = {
				{-2, 0}, {-1, -1}, {-1, 1}, {0, -2},
				{0, 2}, {1, -1}, {1, 1}, {2, 0}
			};


			if (player) {
				vision = this->player1Vision;
				visited = this->visitedPlayer1;
			}
			else {
				vision = this->player2Vision;
				visited = this->visitedPlayer2;
			}



			// on réinitialise la vision
			for (int x = 0; x < n; ++x) {
				for (int y = 0; y < k; ++y) {
					vision[x][y] = 0;
				}
			}


			// mise a jour de ce qu'on doit voir
			for (int i = 0; i < pos.size(); ++i) {
				int x_pos = pos[i].first;
				int y_pos = pos[i].second;

				for (const auto& decalage : decalages) {

					int nx = x_pos + decalage.first;
					int ny = y_pos + decalage.second;

					// Vérifier les limites pour éviter de dépasser les bords
					if (nx >= 0 && nx < n && ny >= 0 && ny < k) {
						if (visited[nx][ny]) {
							vision[nx][ny] = 1;
						}
					}
				}
				if (x_pos > 0) {
					if (y_pos > 0) {
						vision[x_pos - 1][y_pos] = 1;
						vision[x_pos][y_pos - 1] = 1;
						vision[x_pos][y_pos] = 1;
						if (y_pos < k - 1)
							vision[x_pos][y_pos + 1] = 1;
						if (x_pos < n - 1)
							vision[x_pos + 1][y_pos] = 1;
					}
					else {
						vision[x_pos - 1][y_pos] = 1;
						vision[x_pos][y_pos] = 1;
						vision[x_pos][y_pos + 1] = 1;
						if (x_pos < n - 1)
							vision[x_pos + 1][y_pos] = 1;
					}
				}
				else {
					if (y_pos > 0) {
						vision[x_pos][y_pos] = 1;
						vision[x_pos][y_pos - 1] = 1;
						vision[x_pos + 1][y_pos] = 1;
						if (y_pos < k - 1) {
							vision[x_pos][y_pos + 1] = 1;
						}
					}
					else {
						vision[x_pos][y_pos] = 1;
						vision[x_pos][y_pos + 1] = 1;
						vision[x_pos + 1][y_pos] = 1;
					}
				}



			}
		}

		bool existingCase(int future_x, int future_y) {
			if (future_x < 0 || future_x >= n || future_y < 0 || future_y >= k)
				return false;
			else return true;
		}


		void setUnite(Unite* u, int x, int y, bool player) {
			if (this->existingCase(x, y)) {
				this->plateau[x][y]->setUnite(u);
				this->visitCase(player, x, y);
			}
		}


	};
}
