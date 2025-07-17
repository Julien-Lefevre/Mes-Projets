package tests.solvercsp.Contraintes.Unaire;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.Unaire.XinfC;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class XinfCTest {

    @Test
    void evaluateStructurel() {
        //Dans ces deux cas la méthode doit renvoyer Null
        Variable testNull1 = new Variable("testNull1", new IntDomaine(1,10));
        XinfC testNull1cont = new XinfC(testNull1, 1);
        assertThrows(ExceptionDomNull.class, testNull1cont::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(testNull1.getDomaine().getDomain(), "erreur: XinfC testNull1cont");

        //Le domaine ne doit pas être modifié, la méthode doit donc renvoyer False
        Variable testFalse = new Variable("testFalse", new IntDomaine(1,10));
        XinfC testFalseCont = new XinfC(testFalse, 11);
        try {
            assertFalse(testFalseCont.evaluate());
            assertEquals(new IntDomaine(1, 10).getDomain(), testFalse.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        //Le domaine doit être modifié, la méthode doit donc renvoyer True
        Variable testTrue1 = new Variable("testTrue1", new IntDomaine(1,10));
        XinfC testTrue1Cont = new XinfC(testTrue1, 10);
        try {
            assertTrue(testTrue1Cont.evaluate());
            assertNotEquals(new IntDomaine(1, 10).getDomain(), testTrue1.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }

    @Test
    void evaluateFonctionnel() {

        // valeurs en dehors du domaine
            //1er test
        Variable var1 = new Variable("x", new IntDomaine(1,10));
        XinfC cont1 = new XinfC(var1, 0);
        assertThrows(ExceptionDomNull.class, cont1::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(var1.getDomaine().getDomain(), "erreur: XinfC cont0");


            //2eme test
        Variable var2 = new Variable("x", new IntDomaine(1,10));
        XinfC cont2 = new XinfC(var2, 11);
        try {
            assertFalse(cont2.evaluate(), "Erreur : XinfCTest cont2 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 10).getDomain(), var2.getDomaine().getDomain(), "Erreur : XinfCtest cont2 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        // valeurs en borne du domaine
            //1er test
        Variable var3 = new Variable("x", new IntDomaine(1,10));
        XinfC cont3 = new XinfC(var3, 1);
        assertThrows(ExceptionDomNull.class, cont3::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(var3.getDomaine().getDomain(), "erreur: XinfC cont3");


            //2eme test
        Variable var4 = new Variable("x", new IntDomaine(1,10));
        XinfC cont4 = new XinfC(var4, 10);
        try {
            assertTrue(cont4.evaluate(), "Erreur : XinfCTest cont4 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 9).getDomain(), var4.getDomaine().getDomain(), "Erreur : XinfCtest cont4 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        // valeurs dans le domaine
        Variable var5 = new Variable("x", new IntDomaine(1,10));
        XinfC cont5 = new XinfC(var5, 5);
        try {
            assertTrue(cont5.evaluate(), "Erreur : XinfCTest cont5 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 4).getDomain(), var5.getDomaine().getDomain(), "Erreur : XinfCtest cont5 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }
}