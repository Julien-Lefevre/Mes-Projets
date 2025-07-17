package tests.solvercsp.Contraintes.Unaire;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.Unaire.XequalsC;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class XequalsCTest {

    @Test
    void evaluateStructurel() {
        //Dans ces deux cas la méthode doit renvoyer Null
        Variable testNull1 = new Variable("testNull1", new IntDomaine(1,10));
        XequalsC testNull1cont = new XequalsC(testNull1, 0);
        assertThrows(ExceptionDomNull.class, testNull1cont::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(testNull1.getDomaine().getDomain(), "erreur: XequalsC testNull1cont");

        Variable testNull2 = new Variable("testNull2", new IntDomaine(1,10));
        XequalsC testNull2cont = new XequalsC(testNull2, 11);
        assertThrows(ExceptionDomNull.class, testNull2cont::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(testNull2.getDomaine().getDomain(), "erreur: XequalsC testNull2cont");

        //Le domaine ne doit pas être modifié, la méthode doit donc renvoyer False
        Variable testFalse = new Variable("testFalse", new IntDomaine(10,10));
        XequalsC testFalseCont = new XequalsC(testFalse, 10);
        try {
            assertFalse(testFalseCont.evaluate());
            assertEquals(new IntDomaine(10, 10).getDomain(), testFalse.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        //Le domaine doit être modifié, la méthode doit donc renvoyer True
        Variable testTrue1 = new Variable("testTrue1", new IntDomaine(1,10));
        XequalsC testTrue1Cont = new XequalsC(testTrue1, 1);
        try {
            assertTrue(testTrue1Cont.evaluate());
            assertNotEquals(new IntDomaine(1, 10).getDomain(), testTrue1.getDomaine().getDomain());
            assertEquals(new IntDomaine(1, 1).getDomain(), testTrue1.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }

    @Test
    void evaluateFonctionnel() {

        // valeurs en dehors du domaine
            //1er test
        Variable var1 = new Variable("x", new IntDomaine(1,10));
        XequalsC cont1 = new XequalsC(var1, 0);
        assertThrows(ExceptionDomNull.class, cont1::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(var1.getDomaine().getDomain());


            //2eme test
        Variable var2 = new Variable("x", new IntDomaine(1,10));
        XequalsC cont2 = new XequalsC(var2, 11);
        assertThrows(ExceptionDomNull.class, cont2::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(var2.getDomaine().getDomain());



        // valeurs en borne du domaine
            //1er test
        Variable var3 = new Variable("x", new IntDomaine(1,10));
        XequalsC cont3 = new XequalsC(var3, 1);
        try {
            assertTrue(cont3.evaluate(), "Erreur : XequalsCTest cont3 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 1).getDomain(), var3.getDomaine().getDomain(), "Erreur : XinfCtest cont3 : Renvoie le mauvais Domaine");
            assertEquals(1, ((IntDomaine) var3.getDomaine()).getCardDomaine(), "erreur: XequalsC cont3 cardinalité incorrecte");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


            //2eme test
        Variable var4 = new Variable("x", new IntDomaine(1,10));
        XequalsC cont4 = new XequalsC(var4, 10);
        try {
            assertTrue(cont4.evaluate(), "Erreur : XequalsCTest cont4 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(10, 10).getDomain(), var4.getDomaine().getDomain(), "Erreur : XinfCtest cont4 : Renvoie le mauvais Domaine");
            assertEquals(1, ((IntDomaine) var4.getDomaine()).getCardDomaine(), "erreur: XequalsC cont4 cardinalité incorrecte");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        // valeurs dans le domaine
        Variable var5 = new Variable("x", new IntDomaine(1,10));
        XequalsC cont5 = new XequalsC(var5, 5);
        try {
            assertTrue(cont5.evaluate(), "Erreur : XequalsCTest cont5 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(5, 5).getDomain(), var5.getDomaine().getDomain(), "Erreur : XinfCtest cont5 : Renvoie le mauvais Domaine");
            assertEquals(1, ((IntDomaine) var5.getDomaine()).getCardDomaine(), "erreur: XequalsC cont5 cardinalité incorrecte");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }
}