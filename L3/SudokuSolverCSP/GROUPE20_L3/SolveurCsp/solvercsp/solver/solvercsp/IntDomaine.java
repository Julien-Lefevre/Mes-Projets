package solver.solvercsp;

//import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class IntDomaine extends Domaine<Integer> {

    /**
     * Constructeur par défault
     */
    public IntDomaine() {
        super();
        super.domaine.put("min0", -1000);
        super.domaine.put("max0", 1000);
    }

    /**
     * Constructeur d'un domaine avec 2 bornes définies
     */
    public IntDomaine(int min, int max) {
        super();
        super.domaine.put("min0", min);
        super.domaine.put("max0", max);
    }

    /**
     * Ccontructeur par copie
     */
    public IntDomaine(IntDomaine other){
        super.domaine = new HashMap<>(other.domaine);
        super.compteur = other.compteur;
    }

    /**
     * Constructeur prenant plusieurs paires de bornes
     */
    public IntDomaine(int... vals) {
        super();
        if (vals.length % 2 == 0){
            for (int i = 0; i < vals.length; i++){
                if (i % 2 == 0){
                    super.domaine.put("min" + (i/2), vals[i]);
                }
                else{
                    super.domaine.put("max" + (i/2), vals[i]);
                }
            }
        }
        else {
            throw new IllegalArgumentException("Nombre de valeurs incorrect");
        }
        super.compteur = vals.length / 2;
    }

    /**
     * Constructeur par liste de bornes.
     * @param vals Liste des bornes.
     */
    public IntDomaine(ArrayList<Integer> vals) {
        super();
        if (vals.size() % 2 == 0){
            for (int i = 0; i < vals.size(); i++){
                if (i % 2 == 0){
                    super.domaine.put("min" + (i/2), vals.get(i));
                }
                else{
                    super.domaine.put("max" + (i/2), vals.get(i));
                }
            }
        }
        else {
            throw new IllegalArgumentException("Nombre de valeurs incorrect");
        }
        super.compteur = vals.size() / 2;
    }

    /**
     * Cette méthode calcule le nombre d'élément dans le domaine.
     * @return Le nombre d'éléments.
     */
    public int getCardDomaine(){
        int size = 0;
        for (int i = 0; i < super.compteur; i++){
            size += (this.domaine.get("max" + i) - this.domaine.get("min" + i)) + 1;
        }
        return size;
    }

    /**
     * Cette méthode récupère le minimum du domaine.
     * @return Le minimum du domaine.
     */
    public int getMinDomaine(){
        return this.domaine.get("min0");
    }

    /**
     * Cette méthode récupère le maximum du domaine.
     * @return Le maximum du domaine.
     */
    public int getMaxDomaine() {
        return this.domaine.get("max" + (super.compteur - 1));
    }

    /**
     * Cette méthode trouve le minimum d'un sous-domaine.
     * @param i L'index du sous domaine.
     * @return Le minimum du sous-domaine.
     */
    public int getMinSousDomaine(int i){
        if (i < super.compteur){
            return this.domaine.get("min" + i);
        }
        return -1001;
    }

    /**
     * Cette méthode trouve le maximum d'un sous-domaine.
     * @param i L'index du sous domaine.
     * @return Le maximum du sous-domaine.
     */
    public int getMaxSousDomaine(int i){
        if (i < super.compteur){
            return this.domaine.get("max" + i);
        }
        return -1001;
    }

    /**
     * Cette méthode modifie le minimum du sous-domaine spécifié.
     * @param i L'index du sous-domaine à modifier.
     * @param val Le nouveau minimum souhaité.
     */
    public void setMinSousDomain(int i, Integer val){
        super.domaine.replace("min"+i, val);
    }

    /**
     * Cette méthode modifie le maximum du sous-domaine spécifié.
     * @param i L'index du sous-domaine à modifier.
     * @param val Le nouveau maximum souhaité.
     */
    public void setMaxSousDomain(int i, Integer val){
        super.domaine.replace("max"+i, val);
    }

    /**
     * Cette méthode modifie le domaine pour qu'il soit inferieur à val.
     * @param val La nouvelle valeur max non atteinte par le domaine.
     * @return Si le domaine a été modifié.
     */
    @Override
    public boolean infDomaine(Integer val) {
        boolean filtre = false;
        for (int i = 0; i < super.compteur; i++) {
            if (super.domaine.get("min" + i) >= val) {
                super.remSousDomaine(i);
                filtre = true;
            } else if (super.domaine.get("max" + i) >= val) {
                super.domaine.replace("max" + i, val - 1);
                filtre = true;
            }
        }
        return filtre;
    }

    /**
     * Cette méthode modifie le domaine pour qu'il soit superieur à val.
     * @param val La nouvelle valeur min non atteinte par le domaine.
     * @return Si le domaine a été modifié.
     */
    @Override
    public boolean supDomaine(Integer val) {
        boolean filtre = false;
        for (int i = 0; i < super.compteur; i++) {
            if (super.domaine.get("max" + i) <= val) {
                super.remSousDomaine(i);
                filtre = true;
            } else if (super.domaine.get("min" + i) <= val) {
                super.domaine.replace("min" + i, val + 1);
                filtre = true;
            }
        }
        return filtre;
    }

    /**
     * Cette méthode modifie le domaine pour qu'il soit différent de val.
     * @param val La nouvelle valeur non atteinte par le domaine.
     * @return Si le domaine a été modifié.
     */
    @Override
    public boolean diffDomaine(Integer val) {
        boolean filtre = false;
        ArrayList<Integer> suppList = new ArrayList<>();
        ArrayList<Integer> addList = new ArrayList<>();
        for (int i = 0; i < super.compteur; i++) {
//            System.out.println("domaine = min" + i + ": " + super.domaine.get("min" + i) + " ; max" + i + ": " + super.domaine.get("max" + i));
            if (super.domaine.get("min" + i).equals(val) && super.domaine.get("max" + i).equals(val)) {
                filtre = true;
                suppList.add(i);
            }
            else if (super.domaine.get("min" + i).equals(val)) {
                super.domaine.replace("min" + i, val + 1);
                filtre =  true;
            }
            else if (super.domaine.get("max" + i).equals(val)) {
                super.domaine.replace("max" + i, val - 1);
                filtre =  true;
            }
            else if (super.domaine.get("min" + i) < val && val < super.domaine.get("max" + i)) {
                filtre =  true;
                addList.add(i);
            }
        }
        for (int i = suppList.size() - 1; i >= 0; i--){
            super.remSousDomaine(suppList.get(i));
        }
        for (int i = addList.size() - 1; i >= 0; i--){
            Integer max = super.domaine.get("max" + addList.get(i));
            super.domaine.replace("max" + addList.get(i), val - 1);
            super.addSousDomaine(addList.get(i) + 1, val + 1, max);
        }
        return filtre;
    }

    /**
     * Cette méthode modifie le domaine pour qu'il soit égal à val.
     * @param val La nouvelle valeur du domaine.
     * @return Si le domaine a été modifié.
     */
    @Override
    public boolean egalDomaine(Integer val) {
        //A voir si on est dans le cas avec une variable
        boolean filtre = false;
        ArrayList<Integer> suppIndex = new ArrayList<Integer>();
        for (int i = 0; i < super.compteur; i++) {
            if ((!(super.domaine.get("min" + i).equals(val))) || (!(super.domaine.get("max" + i).equals(val)))){  //si la valeur est différente d'au moins une des bornes
                if (super.domaine.get("min" + i) <= val && val <= super.domaine.get("max" + i)) {
                    super.domaine.replace("min" + i, val);
                    super.domaine.replace("max" + i, val);
                } else {
                    suppIndex.add(i);
                }
                filtre = true;
            }
        }
        for (int i = suppIndex.size() - 1; i >= 0; i--){
            this.remSousDomaine(suppIndex.get(i));
        }
        return filtre;
    }
}
