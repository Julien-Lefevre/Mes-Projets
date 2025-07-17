package org.example.sudokusolver.Model;
import solver.solvercsp.Contraintes.Contrainte;
import solver.solvercsp.Contraintes.N_aire.AllDifferent;
import solver.solvercsp.*;
import solver.solvercsp.SolverCSP;
import solver.solvercsp.Variable;
import solver.solvercsp.Contraintes.*;

import java.util.ArrayList;

public class SudokuModel {
    private ArrayList<Variable> listVariable = new ArrayList<>();
    //Création des variables

        //LIGNE 0
    private Variable x00 = new Variable("x00", new IntDomaine(1,9));

    private Variable x01 = new Variable("x01", new IntDomaine(1,9));
    private Variable x02 = new Variable("x02", new IntDomaine(1,9));
    private Variable x03 = new Variable("x03", new IntDomaine(1,9));
    private Variable x04 = new Variable("x04", new IntDomaine(1,9));
    private Variable x05 = new Variable("x05", new IntDomaine(1,9));
    private Variable x06 = new Variable("x06", new IntDomaine(1,9));
    private Variable x07 = new Variable("x07", new IntDomaine(1,9));
    private Variable x08 = new Variable("x08", new IntDomaine(1,9));


        //LIGNE 1
    private Variable x10 = new Variable("x10", new IntDomaine(1,9));
    private Variable x11 = new Variable("x11", new IntDomaine(1,9));
    private Variable x12 = new Variable("x12", new IntDomaine(1,9));
    private Variable x13 = new Variable("x13", new IntDomaine(1,9));
    private Variable x14 = new Variable("x14", new IntDomaine(1,9));
    private Variable x15 = new Variable("x15", new IntDomaine(1,9));
    private Variable x16 = new Variable("x16", new IntDomaine(1,9));
    private Variable x17 = new Variable("x17", new IntDomaine(1,9));
    private Variable x18 = new Variable("x18", new IntDomaine(1,9));


        //LIGNE 2
    private Variable x20 = new Variable("x20", new IntDomaine(1,9));
    private Variable x21 = new Variable("x21", new IntDomaine(1,9));
    private Variable x22 = new Variable("x22", new IntDomaine(1,9));
    private Variable x23 = new Variable("x23", new IntDomaine(1,9));
    private Variable x24 = new Variable("x24", new IntDomaine(1,9));
    private Variable x25 = new Variable("x25", new IntDomaine(1,9));
    private Variable x26 = new Variable("x26", new IntDomaine(1,9));
    private Variable x27 = new Variable("x27", new IntDomaine(1,9));
    private Variable x28 = new Variable("x28", new IntDomaine(1,9));


        //LIGNE 3
    private Variable x30 = new Variable("x30", new IntDomaine(1,9));
    private Variable x31 = new Variable("x31", new IntDomaine(1,9));
    private Variable x32 = new Variable("x32", new IntDomaine(1,9));
    private Variable x33 = new Variable("x33", new IntDomaine(1,9));
    private Variable x34 = new Variable("x34", new IntDomaine(1,9));
    private Variable x35 = new Variable("x35", new IntDomaine(1,9));
    private Variable x36 = new Variable("x36", new IntDomaine(1,9));
    private Variable x37 = new Variable("x37", new IntDomaine(1,9));
    private Variable x38 = new Variable("x38", new IntDomaine(1,9));


        //LIGNE 4
    private Variable x40 = new Variable("x40", new IntDomaine(1,9));
    private Variable x41 = new Variable("x41", new IntDomaine(1,9));
    private Variable x42 = new Variable("x42", new IntDomaine(1,9));
    private Variable x43 = new Variable("x43", new IntDomaine(1,9));
    private Variable x44 = new Variable("x44", new IntDomaine(1,9));
    private Variable x45 = new Variable("x45", new IntDomaine(1,9));
    private Variable x46 = new Variable("x46", new IntDomaine(1,9));
    private Variable x47 = new Variable("x47", new IntDomaine(1,9));
    private Variable x48 = new Variable("x48", new IntDomaine(1,9));


        //LIGNE 5
    private Variable x50 = new Variable("x50", new IntDomaine(1,9));
    private Variable x51 = new Variable("x51", new IntDomaine(1,9));
    private Variable x52 = new Variable("x52", new IntDomaine(1,9));
    private Variable x53 = new Variable("x53", new IntDomaine(1,9));
    private Variable x54 = new Variable("x54", new IntDomaine(1,9));
    private Variable x55 = new Variable("x55", new IntDomaine(1,9));
    private Variable x56 = new Variable("x56", new IntDomaine(1,9));
    private Variable x57 = new Variable("x57", new IntDomaine(1,9));
    private Variable x58 = new Variable("x58", new IntDomaine(1,9));



        //LIGNE 6
    private Variable x60 = new Variable("x60", new IntDomaine(1,9));
    private Variable x61 = new Variable("x61", new IntDomaine(1,9));
    private Variable x62 = new Variable("x62", new IntDomaine(1,9));
    private Variable x63 = new Variable("x63", new IntDomaine(1,9));
    private Variable x64 = new Variable("x64", new IntDomaine(1,9));
    private Variable x65 = new Variable("x65", new IntDomaine(1,9));
    private Variable x66 = new Variable("x66", new IntDomaine(1,9));
    private Variable x67 = new Variable("x67", new IntDomaine(1,9));
    private Variable x68 = new Variable("x68", new IntDomaine(1,9));




        //LIGNE 7
    private Variable x70 = new Variable("x70", new IntDomaine(1,9));
    private Variable x71 = new Variable("x71", new IntDomaine(1,9));
    private Variable x72 = new Variable("x72", new IntDomaine(1,9));
    private Variable x73 = new Variable("x73", new IntDomaine(1,9));
    private Variable x74 = new Variable("x74", new IntDomaine(1,9));
    private Variable x75 = new Variable("x75", new IntDomaine(1,9));
    private Variable x76 = new Variable("x76", new IntDomaine(1,9));
    private Variable x77 = new Variable("x77", new IntDomaine(1,9));
    private Variable x78 = new Variable("x78", new IntDomaine(1,9));



        //LIGNE 8
    private Variable x80 = new Variable("x80", new IntDomaine(1,9));
    private Variable x81 = new Variable("x81", new IntDomaine(1,9));
    private Variable x82 = new Variable("x82", new IntDomaine(1,9));
    private Variable x83 = new Variable("x83", new IntDomaine(1,9));
    private Variable x84 = new Variable("x84", new IntDomaine(1,9));
    private Variable x85 = new Variable("x85", new IntDomaine(1,9));
    private Variable x86 = new Variable("x86", new IntDomaine(1,9));
    private Variable x87 = new Variable("x87", new IntDomaine(1,9));
    private Variable x88 = new Variable("x88", new IntDomaine(1,9));


    private Contrainte ligne0 = new AllDifferent(x00, x01, x02, x03, x04, x05, x06, x07, x08);
    private Contrainte ligne1 = new AllDifferent(x10, x11, x12, x13, x14, x15, x16, x17, x18);
    private Contrainte ligne2 = new AllDifferent(x20, x21, x22, x23, x24, x25, x26, x27, x28);
    private Contrainte ligne3 = new AllDifferent(x30, x31, x32, x33, x34, x35, x36, x37, x38);
    private Contrainte ligne4 = new AllDifferent(x40, x41, x42, x43, x44, x45, x46, x47, x48);
    private Contrainte ligne5 = new AllDifferent(x50, x51, x52, x53, x54, x55, x56, x57, x58);
    private Contrainte ligne6 = new AllDifferent(x60, x61, x62, x63, x64, x65, x66, x67, x68);
    private Contrainte ligne7 = new AllDifferent(x70, x71, x72, x73, x74, x75, x76, x77, x78);
    private Contrainte ligne8 = new AllDifferent(x80, x81, x82, x83, x84, x85, x86, x87, x88);


    private Contrainte colonne0 = new AllDifferent(x00, x10, x20, x30, x40, x50, x60, x70, x80);
    private Contrainte colonne1 = new AllDifferent(x01, x11, x21, x31, x41, x51, x61, x71, x81);
    private Contrainte colonne2 = new AllDifferent(x02, x12, x22, x32, x42, x52, x62, x72, x82);
    private Contrainte colonne3 = new AllDifferent(x03, x13, x23, x33, x43, x53, x63, x73, x83);
    private Contrainte colonne4 = new AllDifferent(x04, x14, x24, x34, x44, x54, x64, x74, x84);
    private Contrainte colonne5 = new AllDifferent(x05, x15, x25, x35, x45, x55, x65, x75, x85);
    private Contrainte colonne6 = new AllDifferent(x06, x16, x26, x36, x46, x56, x66, x76, x86);
    private Contrainte colonne7 = new AllDifferent(x07, x17, x27, x37, x47, x57, x67, x77, x87);
    private Contrainte colonne8 = new AllDifferent(x08, x18, x28, x38, x48, x58, x68, x78, x88);


    private Contrainte carre0 = new AllDifferent(   x00, x01, x02,
                                                    x10, x11, x12,
                                                    x20, x21, x22);

    private Contrainte carre1 = new AllDifferent(   x03, x04, x05,
                                                    x13, x14, x15,
                                                    x23, x24, x25);

    private Contrainte carre2 = new AllDifferent(   x06, x07, x08,
                                                    x16, x17, x18,
                                                    x26, x27, x28);

    private Contrainte carre3 = new AllDifferent(   x30, x31, x32,
                                                    x40, x41, x42,
                                                    x50, x51, x52);

    private Contrainte carre4 = new AllDifferent(   x33, x34, x35,
                                                    x43, x44, x45,
                                                    x53, x54, x55);

    private Contrainte carre5 = new AllDifferent(   x36, x37, x38,
                                                    x46, x47, x48,
                                                    x56, x57, x58);

    private Contrainte carre6 = new AllDifferent(   x60, x61, x62,
                                                    x70, x71, x72,
                                                    x80, x81, x82);

    private Contrainte carre7 = new AllDifferent(   x63, x64, x65,
                                                    x73, x74, x75,
                                                    x83, x84, x85);

    private Contrainte carre8 = new AllDifferent(   x66, x67, x68,
                                                    x76, x77, x78,
                                                    x86, x87, x88);


    /**
     * Constructeur par défaut
     */
    public SudokuModel() {
        listVariable.add(this.x00);
        listVariable.add(this.x01);
        listVariable.add(this.x02);
        listVariable.add(this.x03);
        listVariable.add(this.x04);
        listVariable.add(this.x05);
        listVariable.add(this.x06);
        listVariable.add(this.x07);
        listVariable.add(this.x08);

        listVariable.add(this.x10);
        listVariable.add(this.x11);
        listVariable.add(this.x12);
        listVariable.add(this.x13);
        listVariable.add(this.x14);
        listVariable.add(this.x15);
        listVariable.add(this.x16);
        listVariable.add(this.x17);
        listVariable.add(this.x18);

        listVariable.add(this.x20);
        listVariable.add(this.x21);
        listVariable.add(this.x22);
        listVariable.add(this.x23);
        listVariable.add(this.x24);
        listVariable.add(this.x25);
        listVariable.add(this.x26);
        listVariable.add(this.x27);
        listVariable.add(this.x28);

        listVariable.add(this.x30);
        listVariable.add(this.x31);
        listVariable.add(this.x32);
        listVariable.add(this.x33);
        listVariable.add(this.x34);
        listVariable.add(this.x35);
        listVariable.add(this.x36);
        listVariable.add(this.x37);
        listVariable.add(this.x38);

        listVariable.add(this.x40);
        listVariable.add(this.x41);
        listVariable.add(this.x42);
        listVariable.add(this.x43);
        listVariable.add(this.x44);
        listVariable.add(this.x45);
        listVariable.add(this.x46);
        listVariable.add(this.x47);
        listVariable.add(this.x48);

        listVariable.add(this.x50);
        listVariable.add(this.x51);
        listVariable.add(this.x52);
        listVariable.add(this.x53);
        listVariable.add(this.x54);
        listVariable.add(this.x55);
        listVariable.add(this.x56);
        listVariable.add(this.x57);
        listVariable.add(this.x58);

        listVariable.add(this.x60);
        listVariable.add(this.x61);
        listVariable.add(this.x62);
        listVariable.add(this.x63);
        listVariable.add(this.x64);
        listVariable.add(this.x65);
        listVariable.add(this.x66);
        listVariable.add(this.x67);
        listVariable.add(this.x68);

        listVariable.add(this.x70);
        listVariable.add(this.x71);
        listVariable.add(this.x72);
        listVariable.add(this.x73);
        listVariable.add(this.x74);
        listVariable.add(this.x75);
        listVariable.add(this.x76);
        listVariable.add(this.x77);
        listVariable.add(this.x78);

        listVariable.add(this.x80);
        listVariable.add(this.x81);
        listVariable.add(this.x82);
        listVariable.add(this.x83);
        listVariable.add(this.x84);
        listVariable.add(this.x85);
        listVariable.add(this.x86);
        listVariable.add(this.x87);
        listVariable.add(this.x88);

    }

    /**
     * Cette méthode lance le solver CSP.
     * @return ArrayList contenant les solutions de chaque variable si une solution existe, sinon une ArrayList vide
     */
    public ArrayList<Integer> lauchSolve()
    {
        SolverCSP cspSudoku = new SolverCSP();

        cspSudoku.addVariables(
                               x00 ,x01, x02,       x03, x04, x05,      x06, x07, x08,
                               x10 ,x11, x12,       x13, x14, x15,      x16, x17, x18,
                               x20 ,x21, x22,       x23, x24, x25,      x26, x27, x28,

                               x30 ,x31, x32,       x33, x34, x35,      x36, x37, x38,
                               x40 ,x41, x42,       x43, x44, x45,      x46, x47, x48,
                               x50 ,x51, x52,       x53, x54, x55,      x56,x57, x58,

                               x60 ,x61, x62,       x63, x64, x65,      x66, x67, x68,
                               x70 ,x71, x72,       x73, x74, x75,      x76, x77, x78,
                               x80 ,x81, x82,       x83, x84, x85,      x86, x87, x88);

        cspSudoku.addContraintes(   ligne0, ligne1, ligne2, ligne3, ligne4, ligne5, ligne6, ligne7, ligne8,
                colonne0, colonne1, colonne2, colonne3, colonne4, colonne5, colonne6, colonne7, colonne8,
                carre0, carre1, carre2, carre3, carre4, carre5, carre6, carre7, carre8);

        return cspSudoku.evaluate();
    }


    /**
     * Modifie la variable d'une case pour lui affecter la valeur saisie par l'utilisateur.
     * @param i Numéro de ligne de la case concernée dans la grille.
     * @param j Numéro de la colonne de la case concernée dans la grille.
     * @param value La valeur à affecter dans la case.
     */
    public void modifVariable(int i, int j, int value)
    {
        for(Variable var : listVariable)
        {
            if ((var.getNom()).equals("x"+i+j))
            {
                var.changeDom(new IntDomaine(value, value));
            }
        }
    }

//    public void resetCase(int i, int j)
//    {
//        for(Variable var : listVariable)
//        {
//            if ((var.getNom()).equals("x"+i+j))
//            {
//                var.changeDom(new IntDomaine(1, 9));
//            }
//        }
//    }



    public void resetGrid()
    {
        for(Variable var : listVariable)
        {
            var.changeDom(new IntDomaine(1, 9));
        }
    }

}
