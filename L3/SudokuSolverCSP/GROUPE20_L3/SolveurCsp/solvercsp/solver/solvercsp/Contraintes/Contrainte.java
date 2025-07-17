package solver.solvercsp.Contraintes;

import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.Variable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Contrainte {

    public Contrainte(){}

    public abstract boolean evaluate() throws ExceptionDomNull;

}
