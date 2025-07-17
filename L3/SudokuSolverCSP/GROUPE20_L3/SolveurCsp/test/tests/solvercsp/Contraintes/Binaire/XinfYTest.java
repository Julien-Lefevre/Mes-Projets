package tests.solvercsp.Contraintes.Binaire;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.Binaire.XinfY;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class XinfYTest {

    @Test
    void evaluateStructurel() {
        //Dans ces deux cas la méthode doit renvoyer Null
        Variable testNull1 = new Variable("testNull1", new IntDomaine(1,10));
        Variable testNull2 = new Variable("testNull2", new IntDomaine(-2,1));
        XinfY testNull1cont = new XinfY(testNull1, testNull2);
        assertThrows(ExceptionDomNull.class, testNull1cont::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(testNull1.getDomaine().getDomain(), "erreur: XinfY testNull1cont");

        //Le domaine ne doit pas être modifié, la méthode doit donc renvoyer False
        Variable testFalse1 = new Variable("testFalse", new IntDomaine(1,10));
        Variable testFalse2 = new Variable("testFalse", new IntDomaine(11,13));
        XinfY testFalseCont = new XinfY(testFalse1, testFalse2);
        try {
            assertFalse(testFalseCont.evaluate());
            assertEquals(new IntDomaine(1, 10).getDomain(), testFalse1.getDomaine().getDomain());
            assertEquals(new IntDomaine(11, 13).getDomain(), testFalse2.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        //Le domaine doit être modifié, la méthode doit donc renvoyer True
        Variable testTrue1 = new Variable("testTrue1", new IntDomaine(1,10));
        Variable testTrue2 = new Variable("testTrue1", new IntDomaine(1,10));
        XinfY testTrue1Cont = new XinfY(testTrue1, testTrue2);
        try {
            assertTrue(testTrue1Cont.evaluate());
            assertNotEquals(new IntDomaine(1, 10).getDomain(), testTrue1.getDomaine().getDomain());
            assertNotEquals(new IntDomaine(1, 10).getDomain(), testTrue2.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }


    @Test
    void evaluateFonctionnel() {

        //X a un domaine absolument inférieur au domaine de Y
        Variable varX1 = new Variable("x", new IntDomaine(1,10));
        Variable varY1 = new Variable("y", new IntDomaine(20,30));
        XinfY cont1 = new XinfY(varX1, varY1);
        try {
            assertFalse(cont1.evaluate(), "Erreur : XinfYTest cont1 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 10).getDomain(), varX1.getDomaine().getDomain(), "Erreur : XinfYtest cont1 varX1 : Renvoie le mauvais Domaine");
            assertEquals(new IntDomaine(20, 30).getDomain(), varY1.getDomaine().getDomain(), "Erreur : XinfYtest cont1 varY1 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        //X a un domaine absolument supérieur au domaine de Y
        Variable varX2 = new Variable("x", new IntDomaine(1,10));
        Variable varY2 = new Variable("y", new IntDomaine(-10,0));
        XinfY cont2 = new XinfY(varX2, varY2);
        assertThrows(ExceptionDomNull.class, cont2::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(varX2.getDomaine().getDomain(), "erreur: XinfY cont2");


        //X et Y ont exactement le même domaine
        Variable varX3 = new Variable("x", new IntDomaine(1,10));
        Variable varY3 = new Variable("y", new IntDomaine(1,10));
        XinfY cont3 = new XinfY(varX3, varY3);
        try {
            assertTrue(cont3.evaluate(), "Erreur : XinfYTest cont3 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 9).getDomain(), varX3.getDomaine().getDomain(), "Erreur : XinfYtest cont3 varX3 : Renvoie le mauvais Domaine");
            assertEquals(new IntDomaine(2, 10).getDomain(), varY3.getDomaine().getDomain(), "Erreur : XinfYtest cont3 varY3 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        //X a une partie de son domaine incluse dans Y, le reste est supérieur au domaine Y
        Variable varX4 = new Variable("x", new IntDomaine(1,10));
        Variable varY4 = new Variable("y", new IntDomaine(-5,5));
        XinfY cont4 = new XinfY(varX4, varY4);
        try {
            assertTrue(cont4.evaluate(), "Erreur : XinfYTest cont4 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 4).getDomain(), varX4.getDomaine().getDomain(), "Erreur : XinfYtest cont4 varX4 : Renvoie le mauvais Domaine");
            assertEquals(new IntDomaine(2, 5).getDomain(), varY4.getDomaine().getDomain(), "Erreur : XinfYtest cont4 varY4 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        //X a une partie de son domaine incluse dans Y, le reste est inférieur au domaine Y
        Variable varX5 = new Variable("x", new IntDomaine(1,10));
        Variable varY5 = new Variable("y", new IntDomaine(5,15));
        XinfY cont5 = new XinfY(varX5, varY5);
        try {
            assertFalse(cont5.evaluate(), "Erreur : XinfYTest cont5 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 10).getDomain(), varX5.getDomaine().getDomain(), "Erreur : XinfYtest cont5 varX5 : Renvoie le mauvais Domaine");
            assertEquals(new IntDomaine(5, 15).getDomain(), varY5.getDomaine().getDomain(), "Erreur : XinfYtest cont5 varY5 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }
}