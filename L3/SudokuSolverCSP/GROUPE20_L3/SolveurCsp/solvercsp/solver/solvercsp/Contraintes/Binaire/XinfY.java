package solver.solvercsp.Contraintes.Binaire;

import solver.solvercsp.Domaine;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

public class XinfY extends Binaire{
    public XinfY(Variable v1, Variable v2){
        super(v1, v2);
    }

    /**
     * Cette méthode modifie le domaine des variables pour le premier soit inferieur au deuxième.
     * @return Si le domaine d'une des variables a été modifié
     * @throws ExceptionDomNull si le domaine devient null après modification
     */
    @Override
    public boolean evaluate() throws ExceptionDomNull {
        //X < Y
        boolean filtre = false;
        filtre = super.var1.infDomaine(((IntDomaine) super.var2.getDomaine()).getMaxDomaine()) || filtre;
        Domaine d1 = super.var1.getDomaine();
        if(d1.getDomain() == null) {
            throw new ExceptionDomNull("La variable est nulle");
        }
        filtre = super.var2.supDomaine(((IntDomaine) super.var1.getDomaine()).getMinDomaine()) || filtre;
        Domaine d2 = super.var2.getDomaine();
        if(d2.getDomain() == null) {
            throw new ExceptionDomNull("La variable est nulle");
        }
        return filtre;
    }
}
