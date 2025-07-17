#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

#include "Grille.h"
#include "Notes.h"
int power_2_n[] = {1,2,4,8,16,32,64,128,256,512};
//allocation de la memoire nécéssaire pour un tableau à deux dimensions
T_grid createGrid(int length)
{
    T_grid grid;
    grid = (T_grid)malloc(sizeof(Box*) * length);
    for (int i=0; i<length; i++)
    {
        grid[i] = (Box*)malloc(sizeof(Box) * length);
    }
    return grid;  //tab2d representant la grille
}

//initialise toutes les cases de la grille à 0
void initGrid(T_grid grid, int length)
{

    for (int i=0; i<length; i++)
    {
        for (int j=0; j<length; j++)
        {
            grid[i][j].notes = 511;
            grid[i][j].value = 0;
        }
    }
}


//ajoute une valeur dans la case souhaitee
T_grid add_Value(T_grid grid, int pos_line, int pos_column, int value)
{
    grid[pos_line][pos_column].value = value;
    setNote0Zone(grid, pos_line, pos_column, 9, value);
    grid[pos_line][pos_column].notes = 0;

    return grid;
}

//supprime la valeur de la case
T_grid deleteAvalue(T_grid grid, int pos_line, int pos_column)
{
    grid[pos_line][pos_column].value = 0;
    return grid;
}

//renvoie le chiffre de la case x, y
int getVal(T_grid grid, int x, int y)
{
    return grid[x][y].value;
}

// affiche la grille
void Show_grid(T_grid grid, int length)
{
    int sizeSquare = sqrt(length);
    int nbrCharPerLine = 4 * length + 2 + length / sizeSquare; //chaque case contient 2 espaces, un chiffre et un "|", il y a un "|" au debut et a la fin en plus d'un "|" entre chaque carré
    for (int lin = 0; lin < length; lin++)
    {
        if (lin % sizeSquare == 0)
        {
            for (int n = 0; n < nbrCharPerLine; n++)
            {
                printf("=");
            }
            printf("\n||");
            for (int col = 0; col < length; col++)
            {
                if (col % sizeSquare == sizeSquare - 1)
                {
                    if (grid[lin][col].value != 0)
                    {
                        printf(" %d ||",(grid[lin][col].value));
                    }
                    else
                    {
                        printf("   ||");
                    }
                }
                else
                {
                    if (grid[lin][col].value != 0)
                    {
                        printf(" %d |",(grid[lin][col].value));
                    }
                    else
                    {
                        printf("   |");
                    }
                }
            }
            printf("\n");
        }
        else
        {
            for (int n = 0; n < nbrCharPerLine; n++)
            {
                printf("-");
            }
            printf("\n||");
            for (int col = 0; col < length; col++)
            {
                if (col % sizeSquare == sizeSquare - 1)
                {
                    if (grid[lin][col].value != 0)
                    {
                        printf(" %d ||",(grid[lin][col].value));
                    }
                    else
                    {
                        printf("   ||");
                    }
                }
                else
                {
                    if (grid[lin][col].value != 0)
                    {
                        printf(" %d |",(grid[lin][col].value));
                    }
                    else
                    {
                        printf("   |");
                    }
                }
            }
            printf("\n");
        }
    }
    for (int n = 0; n < nbrCharPerLine; n++)
    {
        printf("=");
    }
    printf("\n");
}


//libère la mémoire allouée pour la grille
void freeGrid(T_grid grid, int length)
{
    for (int i=0; i<length; i++)
    {
        free(grid[i]);
    }
    free(grid);
}

//Indique si une case est vide
bool caseVide(Box *cell)
{
    if ((*cell).value == 0)
        return true;
    else
        return false;
}
