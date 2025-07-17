package solver.solvercsp.Contraintes.N_aire;

import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

public class AllDifferent extends Naire {
    public AllDifferent(Variable... vars){
        super(vars);
    }

    /**
     * Cette méthode modifie le domaine des variables pour qu'ils soit 2 à 2 différents.
     * @return Si le domaine de la variable a été modifié
     * @throws ExceptionDomNull si le domaine devient null après modification
     */
    public boolean evaluate() throws ExceptionDomNull {
        // x != y != z != ...
        boolean filtre = false;
        for (int i = 1; i < super.compteurVar; i++) {
            Variable var1 = super.variableMap.get("var" + i);
            IntDomaine d1 = (IntDomaine) var1.getDomaine();
            if (d1.getCardDomaine() == 1){
                System.out.println("valeur variable " + var1.getNom() + "= " + d1.getMinDomaine());
                for (int j = 1; j < super.compteurVar; j++) {
                    if (i != j) {
                        Variable var2 = super.variableMap.get("var" + j);
                        int value = d1.getMinDomaine();
                        filtre = var2.diffDomaine(value) || filtre;
//                        System.out.println("Apres " + value + ": \n" + var2.getNom());
                    }
                 }
            }
        }
        for (int i = 1; i < super.compteurVar; i++){
            if (super.variableMap.get("var" + i).getDomaine().getDomain() == null){
                System.out.println(super.variableMap.get("var" + i).getNom());
                System.out.println("C'EST MOOOOAAAA\n");
                throw new ExceptionDomNull("La variable est nulle");
            }
        }
        return filtre;
    }
}