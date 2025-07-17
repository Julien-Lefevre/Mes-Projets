package tests.solvercsp.Contraintes.Binaire;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.Binaire.XdiffY;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class XdiffYTest {

    @Test
    void evaluateStructurel() {

        //X et Y ont un domaine de cardinalité supérieure à 1 : on attend false en retour
        Variable varX1 = new Variable("x", new IntDomaine(1, 10));
        Variable varY1 = new Variable("y", new IntDomaine(1, 10));
        XdiffY cont1 = new XdiffY(varX1, varY1);
        try {
            assertFalse(cont1.evaluate());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
        assertEquals(new IntDomaine(1, 10).getDomain(), varX1.getDomaine().getDomain(), "Erreur: XdiffY cont1 : Renvoie le mauvais Domaine");
        assertEquals(new IntDomaine(1, 10).getDomain(), varY1.getDomaine().getDomain(), "erreur: XdiffY cont1 : Renvoie le mauvais Domaine");


        //X a un domaine de cardinalité mais qui n'est pas inclus dans le domaine de Y, on attend False en retour
        Variable varX2 = new Variable("x", new IntDomaine(1, 1));
        Variable varY2 = new Variable("y", new IntDomaine(10, 11));
        XdiffY cont2 = new XdiffY(varX2, varY2);
        try {
            assertFalse(cont2.evaluate());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
        assertEquals(new IntDomaine(1, 1).getDomain(), varX2.getDomaine().getDomain(), "Erreur: XdiffY cont2 : Renvoie le mauvais Domaine");
        assertEquals(new IntDomaine(10, 11).getDomain(), varY2.getDomaine().getDomain(), "Erreur: XdiffY cont2 : Renvoie le mauvais Domaine");


        //X a un domaine de cardinalité qui est inclus dans le domaine de Y, on attend True en retour
        Variable varX3 = new Variable("x", new IntDomaine(2, 2));
        Variable varY3 = new Variable("y", new IntDomaine(1, 8));
        XdiffY cont3 = new XdiffY(varX3, varY3);
        try {
            assertTrue(cont3.evaluate());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
        assertEquals(new IntDomaine(2, 2).getDomain(), varX3.getDomaine().getDomain(), "Erreur: XdiffY cont3 : Renvoie le mauvais Domaine");
        assertEquals(new IntDomaine(1, 1, 3, 8).getDomain(), varY3.getDomaine().getDomain(), "Erreur: XdiffY cont3 : Renvoie le mauvais Domaine");


        //Y a un domaine de cardinalité égal au domaine de X, on attend une exception en retour
        Variable varX4 = new Variable("x", new IntDomaine(1, 1));
        Variable varY4 = new Variable("y", new IntDomaine(1, 1));
        XdiffY cont4 = new XdiffY(varX4, varY4);
        assertThrows(ExceptionDomNull.class, cont4::evaluate, "Expected ExceptionDomNull to be thrown");
    }



//    @Test
//    void evaluateFonctionnel() {
//        Variable varX1 = new Variable("x", new IntDomaine(1, 10));
//        Variable varY1 = new Variable("y", new IntDomaine(20, 30));
//
//        //X a un domaine absolument différent au domaine de Y
//        XdiffY cont1 = new XdiffY(varX1, varY1);
//
//        try {
//            assertFalse(cont1.evaluate(), "erreur: XdiffY cont1 domaine édité");
//        } catch (ExceptionDomNull e) {
//            fail("Unexpected ExceptionDomNull");
//        }
//
//        assertEquals(varX1.getDomaine(), new IntDomaine(1, 10), "erreur: XdiffY cont1 Domaine x incorrecte");
//        assertEquals(new IntDomaine(20, 30), varY1.getDomaine(), "erreur: XdiffY cont1 Domaine y incorrecte");
//
//
//
//
//    }

}