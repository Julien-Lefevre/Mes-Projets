package solver.solvercsp.Contraintes.Binaire;

import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

public class XequalsY extends Binaire{
    public XequalsY(Variable v1, Variable v2){
        super(v1, v2);
    }

    /**
     * Cette méthode modifie le domaine des variables qu'ils soient égaux.
     * @return Si le domaine d'une des variables a été modifié
     * @throws ExceptionDomNull si le domaine devient null après modification
     */
    @Override
    public boolean evaluate() throws ExceptionDomNull {
        boolean filtre = false;
        IntDomaine d1 = (IntDomaine) super.var1.getDomaine();
        IntDomaine d2 = (IntDomaine) super.var2.getDomaine();

        if ( d1.getCardDomaine() == 1 ){
        int value = d1.getMinDomaine();
        filtre = super.var2.egalDomaine(value) || filtre;
        }
        else {
            if ( d2.getCardDomaine() == 1 ) {
                int value = d2.getMinDomaine();
                filtre = super.var1.egalDomaine(value) || filtre;
            }
        }
        if(d1.getDomain() == null || d2.getDomain() == null){
            System.out.println("XequalsY : variable est nulle\n");
            throw new ExceptionDomNull("La variable est nulle");
        }
        return filtre;
    }


}
