package solver.solvercsp;

/**
 *
 */
public class Variable{
    private Domaine domaine;
    private final String nom;

    /**
     * Constructeur par default
     * @param nom Le nom de la variable.
     */
    public Variable(String nom) {
        this.domaine = new IntDomaine();
        this.nom = nom;
    }

    /**
     * Constructeur par domaine
     * @param nom Le nom de la variable.
     * @param domaine Le domaine d ela variable
     */
    public Variable(String nom, Domaine domaine) {
        this.domaine = domaine;
        this.nom = nom;
    }
    /**
     * Constructeur par copie
     * @param other La variable à copier.
     */
    public Variable(Variable other){
        this.nom = other.nom;
        this.domaine = new IntDomaine((IntDomaine) other.domaine);
    }

    public Domaine getDomaine() {
        return this.domaine;
    }

    public String getNom() {
        return this.nom;
    }

    /**
     * Cette méthode modifie le domaine pour qu'il soit inferieur à val.
     * @param val La nouvelle valeur max non atteinte par le domaine.
     * @return Si le domaine a été modifié.
     */
    public boolean infDomaine(Object val){
        return this.domaine.infDomaine(val);
    }

    /**
     * Cette méthode modifie le domaine pour qu'il soit superieur à val.
     * @param val La nouvelle valeur min non atteinte par le domaine.
     * @return Si le domaine a été modifié.
     */
    public boolean supDomaine(Object val){
        return this.domaine.supDomaine(val);
    }

    /**
     * Cette méthode modifie le domaine pour qu'il soit different à val.
     * @param val La non atteinte par le domaine.
     * @return Si le domaine a été modifié.
     */
    public boolean diffDomaine(Object val){
        return this.domaine.diffDomaine(val);
    }

    /**
     * Cette méthode modifie le domaine pour qu'il soit égal à val.
     * @param val La atteinte par le domaine.
     * @return Si le domaine a été modifié.
     */
    public boolean egalDomaine(Object val) {return  this.domaine.egalDomaine(val);}

    /**
     * Cette méthode modifie le domaine pour qu'il soit égal à dom.
     * @param dom Le nouveau domaine.
     */
    public void changeDom(Domaine dom){
        this.domaine = dom;
    }

}