package solver.solvercsp;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Backtracking {

    Stack<Map<String, Variable>> pileBactrack = new Stack<>();
    private boolean isDomainAsc;
    /**
     * Constructeur par défault
     */
    public Backtracking(){this.isDomainAsc = true;}
    /**
     * Constructeur inversé
     */
    public Backtracking(boolean isDomainAsc){this.isDomainAsc = isDomainAsc;}
    /**
     * Cette méthode ajoute une copie des variables dans la pile de backtracking.
     * @param copy Les variables à ajouter.
     * @param name Le nom de la variable à modifier.
     * @param tmp La valeur qui sera supprimée du domaine.
     * @param size Le nombre de variable.
     */
    public void add(Map<String, Variable> copy, String name, int tmp, int size) {
        copyVars(copy, name, tmp, size);
    }


    public Map<String, Variable> getHead(){
        return pileBactrack.pop();
    }

    public Map<String, Variable> seeHead(){
        return pileBactrack.peek();
    }

    public boolean isEmpty() {
        return this.pileBactrack.empty();
    }

    /**
     * Cette méthode effectue une copie des variables puis modifie cette copie.
     * @param copy Les variables à ajouter.
     * @param name Le nom de la variable à modifier.
     * @param tmp La valeur qui sera supprimée du domaine.
     * @param size Le nombre de variable.
     */
    public void copyVars(Map<String, Variable> copy, String name, int tmp, int size){
        Map<String, Variable> vars = new HashMap<>();
        for (int i = 1; i < size; i++){
            vars.put("var" + i, new Variable(copy.get("var" + i)));
        }
        IntDomaine d = (IntDomaine) vars.get(name).getDomaine();
        if(this.isDomainAsc){
            d.supDomaine(tmp);
        } else {
            d.infDomaine(tmp);
        }


        pileBactrack.add(vars);
    }
}
