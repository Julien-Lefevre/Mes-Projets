package solver.solvercsp.Contraintes.Unaire;

import solver.solvercsp.Contraintes.Unaire.Unaire;
import solver.solvercsp.Domaine;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.Variable;

public class XdiffC extends Unaire {
    public XdiffC(Variable var, int cst){
        super(var, cst);
    }

    /**
     * Cette méthode supprime la valeur de la constante du domaine de la variable.
     * @return Si le domaine de la variable a été modifié
     * @throws ExceptionDomNull si le domaine devient null après modification
     */
    @Override
    public boolean evaluate() throws ExceptionDomNull {
        //X != C
        boolean filtre = super.var.diffDomaine(super.cst);
        Domaine d = super.var.getDomaine();
        if(d.getDomain() == null){
            throw new ExceptionDomNull("La variable est nulle");
        } else {
            return filtre;
        }
    }
}

