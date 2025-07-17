package solver.solvercsp.Contraintes.Binaire;

import solver.solvercsp.Contraintes.Contrainte;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.Variable;

import java.util.Map;

public abstract class Binaire extends Contrainte{
    protected Variable var1;
    protected Variable var2;

    /**
     * Constructeur des équation binaires.
     * @param var1 première variable de l'équation
     * @param var2 deuxième variable de l'équation
     */
    Binaire (Variable var1, Variable var2){
        this.var1 = var1;
        this.var2 = var2;
    }

}
