package tests.solvercsp.Contraintes.Binaire;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.Binaire.XsupEqualsY;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XsupEqualsYTest {

    @Test
    void evaluateStructurel() {
        //Dans ces deux cas la méthode doit renvoyer Null
        Variable testNull1 = new Variable("testNull1", new IntDomaine(1,10));
        Variable testNull2 = new Variable("testNull2", new IntDomaine(11,13));
        XsupEqualsY testNull1cont = new XsupEqualsY(testNull1, testNull2);
        assertThrows(ExceptionDomNull.class, testNull1cont::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(testNull1.getDomaine().getDomain(), "erreur: XsupC testNull1cont");

        //Le domaine ne doit pas être modifié, la méthode doit donc renvoyer False
        Variable testFalse1 = new Variable("testFalse", new IntDomaine(1,10));
        Variable testFalse2 = new Variable("testFalse", new IntDomaine(-5,1));
        XsupEqualsY testFalseCont = new XsupEqualsY(testFalse1, testFalse2);
        try {
            assertFalse(testFalseCont.evaluate());
            assertEquals(new IntDomaine(1, 10).getDomain(), testFalse1.getDomaine().getDomain());
            assertEquals(new IntDomaine(-5, 1).getDomain(), testFalse2.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        //Le domaine doit être modifié, la méthode doit donc renvoyer True
        Variable testTrue1 = new Variable("testTrue1", new IntDomaine(1,10));
        Variable testTrue2 = new Variable("testTrue1", new IntDomaine(2,11));
        XsupEqualsY testTrue1Cont = new XsupEqualsY(testTrue1, testTrue2);
        try {
            assertTrue(testTrue1Cont.evaluate());
            assertNotEquals(new IntDomaine(1, 10).getDomain(), testTrue1.getDomaine().getDomain());
            assertNotEquals(new IntDomaine(2, 11).getDomain(), testTrue2.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }


    @Test
    void evaluateFonctionnnel() {

        //X a un domaine absolument inférieur au domaine de Y
        Variable varX1 = new Variable("x", new IntDomaine(1,10));
        Variable varY1 = new Variable("y", new IntDomaine(20,30));
        XsupEqualsY cont1 = new XsupEqualsY(varX1, varY1);
        assertThrows(ExceptionDomNull.class, cont1::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(varX1.getDomaine().getDomain(), "erreur: XsupEqualsY cont1");

        //X a un domaine absolument supérieur au domaine de Y
        Variable varX2 = new Variable("x", new IntDomaine(1,10));
        Variable varY2 = new Variable("y", new IntDomaine(-10,0));
        XsupEqualsY cont2 = new XsupEqualsY(varX2, varY2);
        try {
            assertFalse(cont2.evaluate(), "Erreur : XsupEqualsYTest cont2 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 10).getDomain(), varX2.getDomaine().getDomain(), "Erreur : XsupEqualsYtest cont2 varX2 : Renvoie le mauvais Domaine");
            assertEquals(new IntDomaine(-10, 0).getDomain(), varY2.getDomaine().getDomain(), "Erreur : XsupEqualsYtest cont2 varY2 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        //X et Y ont exactement le même domaine
        Variable varX3 = new Variable("x", new IntDomaine(1,10));
        Variable varY3 = new Variable("y", new IntDomaine(1,10));
        XsupEqualsY cont3 = new XsupEqualsY(varX3, varY3);
        try {
            assertFalse(cont3.evaluate(), "Erreur : XsupEqualsYTest cont3 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 10).getDomain(), varX3.getDomaine().getDomain(), "Erreur : XsupEqualsYtest cont3 varX3 : Renvoie le mauvais Domaine");
            assertEquals(new IntDomaine(1, 10).getDomain(), varY3.getDomaine().getDomain(), "Erreur : XsupEqualsYtest cont3 varY3 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        //X a une partie de son domaine incluse dans Y, le reste est supérieur au domaine Y
        Variable varX4 = new Variable("x", new IntDomaine(1,10));
        Variable varY4 = new Variable("y", new IntDomaine(-5,5));
        XsupEqualsY cont4 = new XsupEqualsY(varX4, varY4);
        try {
            assertFalse(cont4.evaluate(), "Erreur : XsupEqualsYTest cont4 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 10).getDomain(), varX4.getDomaine().getDomain(), "Erreur : XsupEqualsYtest cont4 varX4 : Renvoie le mauvais Domaine");
            assertEquals(new IntDomaine(-5, 5).getDomain(), varY4.getDomaine().getDomain(), "Erreur : XsupEqualsYtest cont4 varY4 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        //X a une partie de son domaine incluse dans Y, le reste est inférieur au domaine Y
        Variable varX5 = new Variable("x", new IntDomaine(1,10));
        Variable varY5 = new Variable("y", new IntDomaine(5,15));
        XsupEqualsY cont5 = new XsupEqualsY(varX5, varY5);
        try {
            assertTrue(cont5.evaluate(), "Erreur : XsupEqualsYTest cont5 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(5, 10).getDomain(), varX5.getDomaine().getDomain(), "Erreur : XsupEqualsYtest cont5 varX5 : Renvoie le mauvais Domaine");
            assertEquals(new IntDomaine(5, 10).getDomain(), varY5.getDomaine().getDomain(), "Erreur : XsupEqualsYtest cont5 varY5 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

    }
}