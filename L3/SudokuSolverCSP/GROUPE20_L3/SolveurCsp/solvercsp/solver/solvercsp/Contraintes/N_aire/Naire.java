package solver.solvercsp.Contraintes.N_aire;

import solver.solvercsp.Contraintes.Contrainte;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.Variable;

import java.util.HashMap;
import java.util.Map;

public abstract class Naire extends Contrainte {
    protected Map<String, Variable> variableMap = new HashMap<>();
    protected int compteurVar = 1;

    /**
     * Constructeur d'équation naire.
     * @param vars les n variables
     */
    public Naire(Variable... vars) {
        for (Variable var: vars){
            this.variableMap.put("var" + this.compteurVar, var);
            this.compteurVar += 1;
        }
    }
}
