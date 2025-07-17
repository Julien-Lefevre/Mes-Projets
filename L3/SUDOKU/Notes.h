#ifndef SUDOKU_PROJECT_ERREUR_ANTOINE_NOTES_H
#define SUDOKU_PROJECT_ERREUR_ANTOINE_NOTES_H
#include "Grille.h"
//Vous retrouverez ces fonctions dans le fichier "Notes.c"


//*****************************DEBUT LIGNE 10******************************************
//Met à 1 le bit "numNote" de la note contenue dans "*cell"
void setNote1(Box *cell, int numNote);
int setNote1_int(int numNote);

//Met à 0 le bit "numNote" de la note contenue dans "*cell"
void setNote0(Box *cell, int numNote);

//Met à 0 le bit "numNote" des notes de toutes les cases de la ligne
void setNote0Line(T_grid grid, int x, int y, int length, int numNote);

//Met à 0 le bit "numNote" des notes de toutes les cases de la colonne
void setNote0Column(T_grid grid, int x, int y, int length, int numNote);

//Met à 0 le bit "numNote" des notes de toutes les cases du bloc
void setNote0Square(T_grid grid, int x, int y, int length, int numNote);

//Applique ensembles les 3 précédentes fonctions
void setNote0Zone(T_grid grid, int x, int y,int length, int numNote);
//**************************FIN LIGNE 90********************************************

// int getNote(Box *cell, int numNote);




//**************************DEBUT LIGNE 93***********************************************
//Affiche l'entier correspondant à la note d'une case en binaire
void printBinary2(Box cell);

//Affiche en binaire un entier "num"
void printBinary(int num);
//**************************FIN LIGNE 110**********************************************






//**************************DEBUT LIGNE 115**********************************************
//renvoie la version décimale de la note
int getvalNote(int note);

//verifie qu'il n'y ai plus qu'une valeur possible dans une case (Donc un seul bit restant en écriture binaire de l'entier)
bool oneNoteLeft (int note);

//renvoie le nombre de possibilités restantes dans une case
int getNbrNote(int note);
//**************************FIN LIGNE 199**************************************************





//**************************DEBUT LIGNE 205************************************************
//Détermine si la note dans une case peut correspondre avec un K-uplet nus
bool IsInTheTampon(int tmp, int note);

//Applique les calculs nécessaires sur les cases nécessaires afin de supprimer des notes grâce aux règles 6 à 10
bool setNoteRule610(T_grid grid, int x1_zone, int y1_zone, int x2_zone, int y2_zone, int tab[][2], int tablength, int tmp);

//Met à 1 les bits correspondants aux valeurs contenues dans "tab" (création des tampons des K-uplets)
int setNote1_tab(int *tab, int sizet);
//**************************FIN LIGNE 260*****************************************************

#endif
