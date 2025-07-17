package solver.solvercsp.Contraintes.Unaire;

import solver.solvercsp.Domaine;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.Variable;

public class XinfEqualsC extends Unaire {
    public XinfEqualsC(Variable var, int cst){
        super(var, cst);
    }

    /**
     * Cette méthode modifie le domaine de la variable pour qui soit inferieur ou égal à la constante.
     * @return Si le domaine de la variable a été modifié
     * @throws ExceptionDomNull si le domaine devient null après modification
     */
    @Override
    public boolean evaluate() throws ExceptionDomNull {
        //X <= C
        boolean filtre = super.var.infDomaine(super.cst + 1);
        Domaine d = super.var.getDomaine();
        if(d.getDomain() == null){
            throw new ExceptionDomNull("La variable est nulle");
        } else {
            return filtre;
        }
    }
}
