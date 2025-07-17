#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

#include "Grille.h"



//Met à 1 le bit "numNote" de la note contenue dans "*cell"
void setNote1(Box *cell, int numNote)
{
    int mask = 1 << (numNote-1);
    cell->notes = cell->notes | mask;
}

int setNote1_int(int numNote)
{
    int x = 0;
    int mask = 1 << (numNote-1);
    return x | mask;
}



//Met à 0 le bit "numNote" de la note contenue dans "*cell"
void setNote0(Box *cell, int numNote)   //Met à 0 le bit "numNote" de la note contenue dans "*cell"
{
    int mask = 1 << (numNote-1);
    cell->notes = cell->notes & ~mask ;  //notetmp = notetmp &~casedeuxvide
}



//Met à 0 le bit "numNote" des notes de toutes les cases de la ligne
void setNote0Line(T_grid grid, int x, int y, int length, int numNote)
{
    for(int i = 0; i<length; i++)
    {
        if(i != x)
        {
            setNote0(&grid[i][y], numNote);
        }
    }
}



//Met à 0 le bit "numNote" des notes de toutes les cases de la colonne
void setNote0Column(T_grid grid, int x, int y, int length, int numNote)
{
    for(int i = 0; i<length; i++)
    {
        if(i != y)
        {
            setNote0(&grid[x][i], numNote);
        }
    }
}



//Met à 0 le bit "numNote" des notes de toutes les cases du bloc
void setNote0Square(T_grid grid, int x, int y, int length, int numNote)
{
    int nbr = (int)sqrt(length);                //nombre de carrés par ligne/colonne
    int xs = (x / nbr) * nbr;
    int ys = (y / nbr) * nbr;
    for(int X = xs; X < xs + 3; X++)
    {
        for(int Y = ys; Y < ys + 3; Y++)
        {
            if(x != X || y != Y)
            {
                setNote0(&grid[X][Y], numNote);
            }
        }
    }
}



//Applique ensembles les 3 précédentes fonctions
void setNote0Zone(T_grid grid, int x, int y,int length, int numNote)
{
    setNote0Line(grid, x, y, length, numNote);
    setNote0Column(grid, x, y, length, numNote);
    setNote0Square(grid, x, y, length, numNote);

}


//Affiche l'entier correspondant à la note d'une case en binaire
void printBinary2(Box cell)
{
    for (int i = sizeof(int) * 8 - 1; i >= 0; i--)
    {
        printf("%d", (cell.notes & (1 << i)) ? 1 : 0);
    }
    printf("\n");
}


//Affiche en binaire un entier "num"
void printBinary(int num) {
    for (int i = sizeof(int) * 8 - 1; i >= 0; i--) {
        printf("%d", (num & (1 << i)) ? 1 : 0);
    }
    printf("\n");
}




//renvoie la version décimale de la note
int getvalNote(int note)
{
    switch(note){
    case 1:
        return 1;
    case 2:
        return 2;
    case 4:
        return 3;
    case 8:
        return 4;
    case 16:
        return 5;
    case 32 :
        return 6;
    case 64 :
        return 7;
    case 128 :
        return 8;
    case 256 :
        return 9;
    case 512 :
        return 10;
    case 1024 :
        return 11;
    case 2048 :
        return 12;
    case 4096 :
        return 13;
    case 8192 :
        return 14;
    case 16384 :
        return 15;
    case 32768 :
        return 16;
    case 65536 :
        return 17;
    case 131072 :
        return 18;
    case 262144 :
        return 19;
    case 524288 :
        return 20;
    case 1048576 :
        return 21;
    case 2097152 :
        return 22;
    case 4194304 :
        return 23;
    case 8388608 :
        return 24;
    case 16777216 :
        return 25;
    default :
        return -1;
    }
}


//verifie qu'il n'y ai plus qu'une valeur possible dans une case
bool oneNoteLeft (int note)
{
    if (getvalNote(note) == -1)
        return false;
    else
        return true;
}



//renvoie le nombre de possibilités restantes dans une case
int getNbrNote(int note)
{
    int p = LENGTH;
    int nbr = 0;
    while (note != 0 && p >= 1){
        if (pow(2, p) >= note){
            note -= p;
            p--;
            nbr++;
        }
    }
    return nbr;
}





//Détermine si la note dans une case peut correspondre avec un K-uplet nus
bool IsInTheTampon(int tmp, int note)
{
    return ((tmp & note) == note);
}



//Applique les calculs nécessaires sur les cases nécessaires afin de supprimer des notes grâce aux règles 5 à 10
bool setNoteRule610(T_grid grid, int x1_zone, int y1_zone, int x2_zone, int y2_zone, int tab[][2], int tablength, int tmp)
{
    int tmp_note;
    bool found = false;
    for(int X = x1_zone; X <= x2_zone ; X++)
    {
        for(int Y = y1_zone; Y <= y2_zone; Y++)           //On parcours la zone travaillée
        {
            bool opere = true;
            for(int i = 0; i < tablength; i++)            //On vérifie que la case ne corresponde pas à une case à ne pas toucher
            {                                             //Ces dernières sont contenues dans la variable "tab[][]"
                if(X == (tab[i][0]) && Y == (tab[i][1]))
                {
                    opere = false;
                }
            }
            tmp_note = grid[X][Y].notes;
            if(opere && getVal(grid, X, Y) == 0)
            {
                tmp_note = grid[X][Y].notes;                    //On garde la note de notre case dans un tampon
                grid[X][Y].notes = grid[X][Y].notes & (~tmp) ;  //On applique le calcul supprimant les notes voulues
                if (tmp_note != grid[X][Y].notes)               //On regarde si la note à été modifiée
                {
                    found = true;
                }
                }
            }
        }
        return found;       //Si une note a été modifiée, on renvoi "true" ce qui permet à la fonction ayant lancé
                    //setNoteRule610 de savoir qu'il y a eu au moins 1 modification (permettra de relancer la boucle des règles)
}



//Met à 1 les bits correspondants aux valeurs contenues dans "tab" (création des tampons des K-uplets)
int setNote1_tab(int *tab, int sizet)
{
    int tmp = 0;
    int mask = 0;
    for(int i = 0; i < sizet; i++)
    {
        int numNote = tab[i];
        mask = 1 << (numNote-1);
        tmp = tmp | mask;
    }
    return tmp;
}
