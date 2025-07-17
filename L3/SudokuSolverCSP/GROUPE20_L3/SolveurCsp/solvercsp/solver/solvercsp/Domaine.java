package solver.solvercsp;

import java.util.HashMap;
import java.util.Map;

public abstract class Domaine<Object> {
    protected Map<String, Object> domaine;
    protected int compteur = 1;
    /**
     * Constructeur par défault
     */
    public Domaine(){
        this.domaine = new HashMap<>();
    }
    /**
     * Constructeur par copie
     */
    public Domaine(Domaine other){
        this.domaine = other.domaine;
        compteur = other.compteur;
    }
    /**
     * Cette méthode change le domaine en un autre.
     * @param domaine Le domaine souhaité.
     */
    public void changeDomain(Domaine<Object> domaine){
        this.domaine.clear();
        this.domaine = domaine.getDomain();
        this.compteur = domaine.getCompteur();
    }

    public Map<String, Object> getDomain(){
        return this.domaine;
    }

    /**
     * Cette méthode ajoute un sous-domaine au domaine.
     * @param index L'index ou l'on veut ajouter le sous-domaine.
     * @param min La borne minimum.
     * @param max La borne maximum.
     */
    public void addSousDomaine(int index, Object min, Object max)
    {
        if (index == this.compteur)
        {
            this.domaine.put("min" + this.compteur, min);
            this.domaine.put("max" + this.compteur, max);
            this.compteur += 1;
        }
        else
        {
            this.domaine.put("min" + this.compteur, this.domaine.get("min" + (this.compteur - 1)));
            this.domaine.put("max" + this.compteur, this.domaine.get("max" + (this.compteur - 1)));
            this.compteur += 1;

            for (int i = this.compteur - 2; i > index; i--)
            {
                this.domaine.replace("min" + i, this.domaine.get("min" + (i-1)));
                this.domaine.replace("max" + i, this.domaine.get("max" + (i-1)));
            }
            this.domaine.replace("min" + index, min);
            this.domaine.replace("max" + index, max);
        }
    }
    /**
     * Cette méthode supprime un sous-domaine au domaine.
     * @param index L'index du sous-domaine à supprimer.
     */
    public void remSousDomaine(int index){
        if (this.compteur == 1){
            this.domaine = null;
        }
        else {
            for (int i = index; i < this.compteur - 1; i++){
                this.domaine.replace("min" + i, this.domaine.get("min" + (i + 1)));
                this.domaine.replace("max" + i, this.domaine.get("max" + (i + 1)));
            }
            this.domaine.remove("min" + this.compteur);
            this.domaine.remove("max" + this.compteur);
        }
        this.compteur -= 1;
    }

    public int getCompteur(){return this.compteur;}
    /**
     * Cette méthode modifie le maximum du domaine spécifié.
     * @param val Le nouveau maximum souhaité.
     * @result Si le domaine a été modifié
     */
    public abstract boolean infDomaine(Object val);    /**
    /**
     * Cette méthode modifie le minimum du domaine spécifié.
     * @param val Le nouveau minimum souhaité.
     * @result Si le domaine a été modifié
     */
    public abstract boolean supDomaine(Object val);    /**
    /**
     * Cette méthode modifie les valeurs possibles du domaine spécifié.
     * @param val La valeur retiré du domaine.
     * @result Si le domaine a été modifié
     */
    public abstract boolean diffDomaine(Object val);    /**
    /**
     * Cette méthode modifie le domaine spécifié pour le rendre égal a une valeur.
     * @param val La nouvelle valeur du domaine souhaitée.
     * @result Si le domaine a été modifié
     */
    public abstract boolean egalDomaine(Object val);
    /**
     * Cette méthode affiche le domaine.
     */
    public void printDomain(){
        for(int i = 0; i < this.compteur; i++){
            System.out.println("min" + i + " : " + this.domaine.get("min" + i));
            System.out.println("max" + i + " : " + this.domaine.get("max" + i));
        }
        System.out.println();
    }
}
