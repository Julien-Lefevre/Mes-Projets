#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

#include "Grille.h"
#include "Resolution.h"
#include "Notes.h"

int main()
{
    T_grid grille = createGrid(LENGTH);
    initGrid(grille, LENGTH);
// TEST 2-uplet CACHES OU NUS
/*2 uplet nus (5,7) 1er bloc
  2 uplet caché (2,8) 2eme bloc*/
/*
    grille = add_Value(grille, 0, 0, 1);
    grille = add_Value(grille, 0, 3, 2);
    grille = add_Value(grille, 0, 4, 4);
    grille = add_Value(grille, 0, 5, 9);
    grille = add_Value(grille, 1, 1, 6);
    grille = add_Value(grille, 2, 1, 3);
    grille = add_Value(grille, 2, 2, 8);


    grille = add_Value(grille, 3, 8, 2);
    grille = add_Value(grille, 4, 8, 8);
    grille = add_Value(grille, 6, 7, 5);
    grille = add_Value(grille, 6, 8, 7);
    grille = add_Value(grille, 7, 7, 3);
    grille = add_Value(grille, 8, 4, 2);
    grille = add_Value(grille, 8, 5, 8);
    grille = add_Value(grille, 8, 6, 1);

    Show_grid(grille, LENGTH);

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }
    for (int i = 6; i < 9; i++)
    {
        for (int j = 6; j < 9; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }

    rules_610(grille);  //A MODIFIER

    Show_grid(grille,LENGTH);

    verifGrid(grille, LENGTH);
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }
    for (int i = 6; i < 9; i++)
    {
        for (int j = 6; j < 9; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }

    */




//TEST 3-uplets CACHES OU NUS
/* 3-uplet nus (5,7,9) premier bloc
   3-uplet caché (2,8,9) dernier bloc
*/

/*
    grille = add_Value(grille, 0, 0, 1);
    grille = add_Value(grille, 0, 3, 2);
    grille = add_Value(grille, 0, 4, 4);
    grille = add_Value(grille, 1, 1, 6);
    grille = add_Value(grille, 1, 5, 5);
    grille = add_Value(grille, 2, 1, 3);
    grille = add_Value(grille, 2, 2, 8);
    grille = add_Value(grille, 3, 2, 2);
    grille = add_Value(grille, 4, 1, 7);
    grille = add_Value(grille, 4, 2, 4);


    grille = add_Value(grille, 3, 6, 9);
    grille = add_Value(grille, 4, 6, 8);
    grille = add_Value(grille, 5, 6, 2);
    grille = add_Value(grille, 6, 7, 5);
    grille = add_Value(grille, 6, 8, 7);
    grille = add_Value(grille, 7, 5, 2);
    grille = add_Value(grille, 7, 7, 3);
    grille = add_Value(grille, 8, 4, 8);
    grille = add_Value(grille, 8, 6, 1);

    Show_grid(grille, LENGTH);

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }
    for (int i = 6; i < 9; i++)
    {
        for (int j = 6; j < 9; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }

    rules_610(grille);//A MODIFIER

    Show_grid(grille,LENGTH);

    verifGrid(grille, LENGTH);
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }
    for (int i = 6; i < 9; i++)
    {
        for (int j = 6; j < 9; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }

*/




//TEST POUR FAIRE TOURNER CHAQUE REGLE INDIVIDUELLEMENT EN BOUCLE
/*

    grille = add_Value(grille, 0, 0, 8);
    grille = add_Value(grille, 0, 1, 4);
    grille = add_Value(grille, 0, 3, 2);
    grille = add_Value(grille, 0, 6, 5);
    grille = add_Value(grille, 0, 7, 9);
    grille = add_Value(grille, 0, 8, 7);
    grille = add_Value(grille, 1, 0, 5);
    grille = add_Value(grille, 1, 1, 6);
    grille = add_Value(grille, 1, 2, 9);
    grille = add_Value(grille, 1, 4, 3);
    grille = add_Value(grille, 1, 8, 1);
    grille = add_Value(grille, 2, 2, 7);
    grille = add_Value(grille, 2, 5, 5);
    grille = add_Value(grille, 3, 2, 2);
    grille = add_Value(grille, 3, 3, 6);
    grille = add_Value(grille, 3, 6, 8);
    grille = add_Value(grille, 3, 8, 9);
    grille = add_Value(grille, 4, 4, 8);
    grille = add_Value(grille, 5, 0, 9);
    grille = add_Value(grille, 5, 2, 1);
    grille = add_Value(grille, 5, 5, 2);
    grille = add_Value(grille, 5, 6, 4);
    grille = add_Value(grille, 6, 3, 9);
    grille = add_Value(grille, 6, 6, 6);
    grille = add_Value(grille, 7, 0, 6);
    grille = add_Value(grille, 7, 4, 2);
    grille = add_Value(grille, 7, 6, 1);
    grille = add_Value(grille, 7, 7, 7);
    grille = add_Value(grille, 7, 8, 3);
    grille = add_Value(grille, 8, 0, 1);
    grille = add_Value(grille, 8, 1, 7);
    grille = add_Value(grille, 8, 2, 4);
    grille = add_Value(grille, 8, 5, 6);
    grille = add_Value(grille, 8, 7, 2);
    grille = add_Value(grille, 8, 8, 8);


    Show_grid(grille, LENGTH);

    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }
    bool theend = true;
    while (theend)
    {
        if (!rules_610(grille)) ;//A MODIFIER POUR CHAQUE REGLE A FAIRE TOURNER EN BOUCLE
        {
            theend = false;
        }
    }
    Show_grid(grille,LENGTH);

    verifGrid(grille, LENGTH);
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }

*/





//TEST LES 6 REGLES TOURNENT EN BOUCLE
/*
    grille = add_Value(grille, 0, 0, 8);
    grille = add_Value(grille, 0, 1, 4);
    grille = add_Value(grille, 0, 6, 5);
    grille = add_Value(grille, 0, 7, 9);
    grille = add_Value(grille, 0, 8, 7);
    grille = add_Value(grille, 1, 0, 5);
    grille = add_Value(grille, 1, 1, 6);
    grille = add_Value(grille, 1, 4, 3);
    grille = add_Value(grille, 2, 2, 7);
    grille = add_Value(grille, 2, 5, 5);
    grille = add_Value(grille, 3, 2, 2);
    grille = add_Value(grille, 3, 3, 6);
    grille = add_Value(grille, 3, 6, 8);
    grille = add_Value(grille, 3, 8, 9);
    grille = add_Value(grille, 4, 4, 8);
    grille = add_Value(grille, 5, 0, 9);
    grille = add_Value(grille, 5, 2, 1);
    grille = add_Value(grille, 5, 5, 2);
    grille = add_Value(grille, 5, 6, 4);
    grille = add_Value(grille, 6, 3, 9);
    grille = add_Value(grille, 6, 6, 6);
    grille = add_Value(grille, 7, 4, 2);
    grille = add_Value(grille, 7, 6, 1);
    grille = add_Value(grille, 7, 7, 7);
    grille = add_Value(grille, 8, 0, 1);
    grille = add_Value(grille, 8, 1, 7);
    grille = add_Value(grille, 8, 2, 4);
    grille = add_Value(grille, 8, 7, 2);
    grille = add_Value(grille, 8, 8, 8);
    grille = add_Value(grille, 3,4,3);

    Show_grid(grille, LENGTH);


    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }

    run_rules(grille, LENGTH);   //VERIFIER QU'ON A BIEN REMIS LES FONCTIONS EN ETAT

    Show_grid(grille,LENGTH);

    verifGrid(grille, LENGTH);
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }

    */





//TEST PAIRE et TRIPLET POINTANTS   //La note 4 doit etre supprimée dans la case [7][0]
/*
    grille = add_Value(grille, 0, 2, 9);
    grille = add_Value(grille, 0, 4, 7);
    grille = add_Value(grille, 1, 1, 8);
    grille = add_Value(grille, 1, 3, 4);
    grille = add_Value(grille, 2, 2, 3);
    grille = add_Value(grille, 2, 7, 2);
    grille = add_Value(grille, 2, 8, 8);
    grille = add_Value(grille, 3, 0, 1);
    grille = add_Value(grille, 3, 6, 6);
    grille = add_Value(grille, 3, 7, 7);
    grille = add_Value(grille, 4, 1, 2);
    grille = add_Value(grille, 4, 4, 1);
    grille = add_Value(grille, 4, 5, 3);
    grille = add_Value(grille, 4, 7, 4);
    grille = add_Value(grille, 5, 1, 4);
    grille = add_Value(grille, 5, 5, 7);
    grille = add_Value(grille, 5, 6, 8);
    grille = add_Value(grille, 6, 0, 6);
    grille = add_Value(grille, 6, 4, 3);
    grille = add_Value(grille, 7, 1, 1);
    grille = add_Value(grille, 8, 6, 2);
    grille = add_Value(grille, 8, 7, 8);
    grille = add_Value(grille, 8, 8, 4);

    //Pour engendrer une grille fausse :
    //grille = add_Value(grille, 7, 8, 8);

    Show_grid(grille, LENGTH);


    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 1; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }

    rules_1112_launch(grille);

    Show_grid(grille,LENGTH);

    verifGrid(grille, LENGTH);
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 1; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }

    */





//TEST POUR BOX2 ET/OU BOX3 REDUCTION
//Les notes 7 doivent etre supprimees dans les cases [0][2] et [2][0]
/*
    grille = add_Value(grille, 0, 0, 3);
    grille = add_Value(grille, 0, 3, 5);
    grille = add_Value(grille, 0, 5, 8);
    grille = add_Value(grille, 0, 7, 2);
    grille = add_Value(grille, 1, 0, 8);
    grille = add_Value(grille, 1, 2, 2);
    grille = add_Value(grille, 1, 3, 9);
    grille = add_Value(grille, 1, 5, 3);
    grille = add_Value(grille, 1, 7, 7);
    grille = add_Value(grille, 2, 2, 5);
    grille = add_Value(grille, 2, 3, 2);
    grille = add_Value(grille, 2, 5, 4);
    grille = add_Value(grille, 2, 6, 3);
    grille = add_Value(grille, 2, 7, 8);
    grille = add_Value(grille, 2, 8, 1);
    grille = add_Value(grille, 3, 0, 4);
    grille = add_Value(grille, 3, 1, 8);
    grille = add_Value(grille, 3, 2, 3);
    grille = add_Value(grille, 4, 1, 2);
    grille = add_Value(grille, 5, 0, 5);
    grille = add_Value(grille, 5, 1, 6);
    grille = add_Value(grille, 5, 2, 9);
    grille = add_Value(grille, 6, 1, 3);
    grille = add_Value(grille, 7, 1, 5);
    grille = add_Value(grille, 7, 2, 4);
    grille = add_Value(grille, 8, 2, 8);
    grille = add_Value(grille, 8, 8, 7);

    Show_grid(grille, LENGTH);


    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }

    rules_1112_launch(grille);

    Show_grid(grille,LENGTH);

    verifGrid(grille, LENGTH);
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            printf("\ncase [%d][%d]\n", i, j);
            printBinary2(grille[i][j]);
        }
    }
*/





//TEST COMPLET
    grille = add_Value(grille, 0, 4, 8);
    grille = add_Value(grille, 1, 6, 4);
    grille = add_Value(grille, 1, 8, 6);
    grille = add_Value(grille, 2, 0, 9);
    grille = add_Value(grille, 2, 2, 7);
    grille = add_Value(grille, 2, 3, 5);
    grille = add_Value(grille, 2, 5, 2);
    grille = add_Value(grille, 3, 1, 2);
    grille = add_Value(grille, 3, 5, 1);
    grille = add_Value(grille, 3, 8, 8);
    grille = add_Value(grille, 4, 2, 9);
    grille = add_Value(grille, 4, 3, 8);
    grille = add_Value(grille, 4, 4, 3);
    grille = add_Value(grille, 4, 6, 5);
    grille = add_Value(grille, 5, 1, 6);
    grille = add_Value(grille, 5, 5, 4);
    grille = add_Value(grille, 5, 8, 9);
    grille = add_Value(grille, 6, 0, 1);
    grille = add_Value(grille, 6, 2, 8);
    grille = add_Value(grille, 6, 3, 3);
    grille = add_Value(grille, 6, 5, 9);
    grille = add_Value(grille, 7, 6, 8);
    grille = add_Value(grille, 7, 8, 2);
    grille = add_Value(grille, 8, 4, 7);

    Show_grid(grille, LENGTH);

    run_rules(grille,LENGTH);

    Show_grid(grille,LENGTH);

    verifGrid(grille, LENGTH);






    freeGrid(grille,LENGTH);




    return 0;
}
