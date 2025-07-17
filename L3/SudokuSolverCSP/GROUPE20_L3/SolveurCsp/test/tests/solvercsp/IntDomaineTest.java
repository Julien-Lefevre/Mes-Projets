package tests.solvercsp;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.Unaire.XdiffC;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class IntDomaineTest {

    IntDomaine d13 = new IntDomaine(-2, 10);
    IntDomaine dNull = new IntDomaine();//testable ?
    IntDomaine d1 = new IntDomaine(0,0);


    Variable triDomaine;
    XdiffC difftriDomaine1;
    XdiffC difftriDomaine2;

    void setDomaine(){
        triDomaine = new Variable("triDomaine", new IntDomaine(0,10));
        d13 = new IntDomaine(-2, 10);
        dNull = new IntDomaine();
        d1 = new IntDomaine(0,0);
    }

    void setmultiDomain(){


        triDomaine = new Variable("triDomaine", new IntDomaine(0,10));
        difftriDomaine1 = new XdiffC(triDomaine, 3);
        difftriDomaine2 = new XdiffC(triDomaine, 7);


        try {
            difftriDomaine1.evaluate();
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        try {
            difftriDomaine2.evaluate();
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }

    void testMaxSousDomaine(){
        //test Max
        assertEquals(2, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(0));
        assertEquals(6, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(1));
        assertEquals(10, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(2));

    };

    void testMinSousDomaine(){
        //test Min
        assertEquals(0, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(0));
        assertEquals(4, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(1));
        assertEquals(8, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(2));
    };

    @Test
    void getCardDomaine() {
        assertEquals(13, d13.getCardDomaine());
        assertEquals(1, d1.getCardDomaine());
    }

    @Test
    void getMinDomaine() {
        assertEquals(-2, d13.getMinDomaine());
        assertEquals(0, d1.getMinDomaine());
    }

    @Test
    void getMaxDomaine() {
        assertEquals(10, d13.getMaxDomaine());
        assertEquals(0, d1.getMaxDomaine());
    }

    @Test
    void getMinSousDomaine() {
        //met en place le multidomaine
        setDomaine();
        setmultiDomain();
        //test sur un domaine unique
        assertEquals(-2, d13.getMinSousDomaine(0));
        assertEquals(0, d1.getMinSousDomaine(0));
        //test multiDomain
        testMinSousDomaine();
    }

    @Test
    void getMaxSousDomaine() {
        //met en place le multidomaine
        setDomaine();
        setmultiDomain();
        //test sur un domaine unique
        assertEquals(10, d13.getMaxSousDomaine(0));
        assertEquals(0, d1.getMaxSousDomaine(0));
        //test multiDomain
        testMaxSousDomaine();
    }

    @Test
    void setMinSousDomain(){
        //besoin de tester ??? pas utiliser
    }

    @Test
    void setMaxSousDomain() {
        //besoin de tester ??? pas utiliser
    }

    @Test
    void infDomaine() {
        setmultiDomain();
        //test domaine inférieur à 11, domaine intact
        triDomaine.infDomaine(11);
        testMinSousDomaine();
        testMaxSousDomaine();
        //test domaine inférieur à 10, domaine perd sa valeur max
        triDomaine.infDomaine(10);
        assertEquals(9, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(2));
        assertEquals(8, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(2));
        //test domaine inférieur à 5, domaine édité
        triDomaine.infDomaine(5);
        assertEquals(4, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(1));
        assertEquals(0, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(0));
        //test domaine inférieur à 0, domaine mis à null

    }

    @Test
    void supDomaine() {

    }

    @Test
    void diffDomaine() {
        setmultiDomain();
        //test domaine inchangé
        triDomaine.diffDomaine(3);
        triDomaine.diffDomaine(7);

        testMinSousDomaine();
        testMaxSousDomaine();
        //test domaine édité supression extremum

        triDomaine.diffDomaine(10);
        assertEquals(2, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(0));
        assertEquals(6, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(1));
        assertEquals(9, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(2));

        triDomaine.diffDomaine(0);
        assertEquals(1, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(0));
        assertEquals(4, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(1));
        assertEquals(8, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(2));

        //test domaine changé, supression valeur 5
        triDomaine.diffDomaine(6);
        assertEquals(2, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(0));
        assertEquals(5, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(1));
        assertEquals(9, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(2));

        assertEquals(1, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(0));
        assertEquals(4, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(1));
        assertEquals(8, ((IntDomaine)triDomaine.getDomaine()).getMinSousDomaine(2));

        //test domaine vaut null
        for(int i = 0; i<10; i++){
            triDomaine.diffDomaine(i);
        }
        assertNull(triDomaine.getDomaine().getDomain());//probleme
    }

    @Test
    void egalDomaine() {
        //test valeur en dehors domaine positive--> domaine null
        setmultiDomain();
        triDomaine.egalDomaine(11);
        assertNull(triDomaine.getDomaine().getDomain());//probleme

        //test valeur en dehors domaine negative--> domaine null
        setmultiDomain();
        triDomaine.egalDomaine(-4);
        assertNull(triDomaine.getDomaine().getDomain());

        //test domaine changé, valeur 6 gardé
        setmultiDomain();
        triDomaine.egalDomaine(6);
        assertEquals(6, ((IntDomaine)triDomaine.getDomaine()).getMaxSousDomaine(0));

    }
}