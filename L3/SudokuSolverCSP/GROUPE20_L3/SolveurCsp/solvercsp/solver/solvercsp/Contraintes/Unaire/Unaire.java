package solver.solvercsp.Contraintes.Unaire;

import solver.solvercsp.Contraintes.Contrainte;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.Variable;

import java.util.Map;

public abstract class Unaire extends Contrainte {
    protected Variable var;
    protected int cst;

    /**
     * Constructeur d'équation unaire.
     * @param var la variable de l'équation
     * @param cst la constante de l'équation
     */
    public Unaire(Variable var, int cst){
        this.var = var;
        this.cst = cst;
    }

}
