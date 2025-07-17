package tests.solvercsp.Contraintes.Unaire;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.Unaire.XinfEqualsC;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class XinfEqualsCTest {

    @Test
    void evaluateStructurel() {
        //Dans ces deux cas la méthode doit renvoyer Null
        Variable testNull1 = new Variable("testNull1", new IntDomaine(1,10));
        XinfEqualsC testNull1cont = new XinfEqualsC(testNull1, 0);
        assertThrows(ExceptionDomNull.class, testNull1cont::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(testNull1.getDomaine().getDomain(), "erreur: XsupC testNull1cont");

        //Le domaine ne doit pas être modifié, la méthode doit donc renvoyer False
        Variable testFalse = new Variable("testFalse", new IntDomaine(1,10));
        XinfEqualsC testFalseCont = new XinfEqualsC(testFalse, 11);
        try {
            assertFalse(testFalseCont.evaluate());
            assertEquals(new IntDomaine(1, 10).getDomain(), testFalse.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        //Le domaine doit être modifié, la méthode doit donc renvoyer True
        Variable testTrue1 = new Variable("testTrue1", new IntDomaine(1,10));
        XinfEqualsC testTrue1Cont = new XinfEqualsC(testTrue1, 9);
        try {
            assertTrue(testTrue1Cont.evaluate());
            assertNotEquals(new IntDomaine(1, 10).getDomain(), testTrue1.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        Variable testTrue2 = new Variable("testTrue2", new IntDomaine(1,10));
        XinfEqualsC testTrue2Cont = new XinfEqualsC(testTrue2, 1);
        try {
            assertTrue(testTrue2Cont.evaluate());
            assertNotEquals(new IntDomaine(1, 10).getDomain(), testTrue2.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }



    @Test
    void evaluateFonctionnel() {

        // valeurs en dehors du domaine
            //1er test
        Variable var1 = new Variable("x", new IntDomaine(1,10));
        XinfEqualsC cont1 = new XinfEqualsC(var1, 0);
        assertThrows(ExceptionDomNull.class, cont1::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(var1.getDomaine().getDomain(), "erreur: XinfEqualsC cont1 : Renvoie le mauvais domaine");

            //2eme test
        Variable var2 = new Variable("x", new IntDomaine(1,10));
        XinfEqualsC cont2 = new XinfEqualsC(var2, 11);
        try {
            assertFalse(cont2.evaluate(), "Erreur : Xinf_equalsCTest cont2 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 10).getDomain(), var2.getDomaine().getDomain(), "Erreur : Xinf_equalsCtest cont2");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        // valeurs en borne du domaine
            //1er test
        Variable var3 = new Variable("x", new IntDomaine(1,10));
        XinfEqualsC cont3 = new XinfEqualsC(var3, 1);
        try {
            assertTrue(cont3.evaluate(), "Erreur : Xinf_equalsCTest cont3 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 1).getDomain(), var3.getDomaine().getDomain(), "Erreur : Xinf_equalsCtest cont3");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

            //2eme test
        Variable var4 = new Variable("x", new IntDomaine(1,10));
        XinfEqualsC cont4 = new XinfEqualsC(var4, 10);
        try {
            assertFalse(cont4.evaluate(), "Erreur : Xinf_equalsCTest cont4 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1,10).getDomain(), var4.getDomaine().getDomain(), "Erreur : Xinf_equalsCtest cont4");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        // valeurs dans le domaine
        Variable var5 = new Variable("x", new IntDomaine(1,10));
        XinfEqualsC cont5 = new XinfEqualsC(var5, 5);
        try {
            assertTrue(cont5.evaluate(), "Erreur : Xinf_equalsCTest cont5 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1,5).getDomain(), var5.getDomaine().getDomain(), "Erreur : Xinf_equalsCtest cont5");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }
}