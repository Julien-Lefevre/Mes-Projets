#ifndef RESOLUTION_H_INCLUDED
#define RESOLUTION_H_INCLUDED

#include <stdbool.h>

#include "Grille.h"

//**********************DEBUT LIGNE 11**************************************************************************************
bool verifCase(bool *res, int val);                             //fonction qui vérifie si la valeur val est déjà apparue dans la ligne/case/colonne
bool gridVerif(T_grid grid, int X1, int X2, int Y1, int Y2, int lenght);   //fonction qui vérifie si la colonne/case/ligne ne posède pas plusieurs chiffre identiques

bool verifLine(T_grid grid, int x, int length);                 //fonction vérifiant la validité d'une ligne
bool verifColumn(T_grid grid, int y, int length);               //fonction vérifiant la validité d'une colonne
bool verifSquare(T_grid grid, int x, int y, int length);        //fonction vérifiant la validité d'un carré

bool verifGrid(T_grid grid, int length);                        //fonction vérifiant la validité de la grille complète
//**********************FIN LIGNE 95****************************************************************************************



// void setNote(int* cell, int numNote)

//**********************DEBUT LIGNE 100**************************************************************************************
bool rule_1and3 (T_grid grid, int sizet);                       //fonction appliquant les règles de résolution 1 et 3                                            //Variable vérifiant si la fonction "rule_2" à trouvé une solution
bool rule_2 (T_grid grid, int sizet);
bool rule_2_line(T_grid grid, int sizet, int X, int Y, int tmp);//Fonction appliquant la règle de résolution 2
bool rule_2_column (T_grid grid, int sizet, int X, int Y, int tmp);//fonction appliquant rule_2 à une colonne
bool rule_2_square(T_grid grid, int sizet, int X, int Y, int tmp);//fonction appliquant rule_2 à un bloc
bool verifbetweenrules(T_grid grid);
void run_rules (T_grid grid, int sizet);                        //fonction appliquant rule_2 à une ligne
//**********************FIN LIGNE 278***************************************************************************************



//**********************DEBUT LIGNE 282*************************************************************************************
int* availableValues(T_grid grid, int X1, int Y1, int X2, int Y2, int* length);     //fonction renvoyant un tableau remplie des valeurs non présentes dans une zone et sa taille
bool rules_67_zone (T_grid grid, int X1, int Y1, int X2, int Y2, int K, int * kuplet);  //fonction envoyant les coordonnées des cases correspondantes au kuplet donnée (si elles existent) à setNoteRule610
bool rules_89_zone(T_grid grid, int X1, int Y1, int X2, int Y2, int K, int * tab);      //fonction envoyant les coordonnées des cases correspondantes au kuplet donnée (si elles existent) à setNoteRule610
bool generateKtuples(T_grid grid, int possibleValues[], int sizet, int k, int kuplet[], int index, int bfor, int X1, int Y1, int X2, int Y2);   //cette fonction génère et envoie tous les kuplets possible aux fonctions gérant les règles 6 à 10
int max4(int a);    //fonction renvoyant le minimum entre le paramètre a et 4
bool rules_610(T_grid grid);    //cette fonction lance les règles 6 à 10 sur toutes les lignes, colonnes et carrés de la grille
//**********************FIN LIGNE 480***************************************************************************************


//**********************DEBUT LIGNE 487*************************************************************************************
//Cette fonction lance les règles 11, 11', 12 et 12' sur les zones nécessaires
bool rules_1112_launch(T_grid grid);

//Fonction qui applique la logique des paires et triplets pointants(règle 11 et 12)
bool rules_1112(T_grid grid, int X1, int Y1, int X2, int Y2, int testValue);

//Fonction qui applique la logique des box reduction sur un bloc
bool rules_box_reduction(T_grid grid, int X1, int Y1, int X2, int Y2, int testValue);
//**********************FIN LIGNE 657***************************************************************************************
#endif // RESOLUTION_H_INCLUDED
