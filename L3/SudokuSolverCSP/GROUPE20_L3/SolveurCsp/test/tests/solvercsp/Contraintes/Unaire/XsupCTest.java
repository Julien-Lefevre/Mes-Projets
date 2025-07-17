package tests.solvercsp.Contraintes.Unaire;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.Unaire.XsupC;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class XsupCTest {


    @Test
    void evaluateStructurel() {
        //Dans ces deux cas la méthode doit renvoyer Null
        Variable testNull1 = new Variable("testNull1", new IntDomaine(1,10));
        XsupC testNull1cont = new XsupC(testNull1, 10);
        assertThrows(ExceptionDomNull.class, testNull1cont::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(testNull1.getDomaine().getDomain(), "erreur: XsupC testNull1cont");

        //Le domaine ne doit pas être modifié, la méthode doit donc renvoyer False
        Variable testFalse = new Variable("testFalse", new IntDomaine(1,10));
        XsupC testFalseCont = new XsupC(testFalse, 0);
        try {
            assertFalse(testFalseCont.evaluate());
            assertEquals(new IntDomaine(1, 10).getDomain(), testFalse.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        //Le domaine doit être modifié, la méthode doit donc renvoyer True
        Variable testTrue1 = new Variable("testTrue1", new IntDomaine(1,10));
        XsupC testTrue1Cont = new XsupC(testTrue1, 1);
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
        Variable var1 = new Variable("x1", new IntDomaine(1,10));
        XsupC cont1 = new XsupC(var1, 0);
        try {
            assertFalse(cont1.evaluate(), "Erreur : XsupCTest cont1 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1, 10).getDomain(), var1.getDomaine().getDomain(), "Erreur : XsupCtest cont1 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


            //2eme test
        Variable var2 = new Variable("x2", new IntDomaine(1,10));
        XsupC cont2 = new XsupC(var2, 11);
        assertThrows(ExceptionDomNull.class, cont2::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(var2.getDomaine().getDomain(), "erreur: XsupC cont2");


        // valeurs en borne du domaine
            //1er test
        Variable var3 = new Variable("x3", new IntDomaine(1,10));
        XsupC cont3 = new XsupC(var3, 1);
        try {
            assertTrue(cont3.evaluate(), "Erreur : XsupCTest cont3 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(2, 10).getDomain(), var3.getDomaine().getDomain(), "Erreur : XsupCtest cont3 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


            //2eme test
        Variable var4 = new Variable("x4", new IntDomaine(1,10));
        XsupC cont4 = new XsupC(var4, 10);
        assertThrows(ExceptionDomNull.class, cont4::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(var4.getDomaine().getDomain(), "erreur: XsupC cont4");



        // valeurs dans le domaine
        Variable var5 = new Variable("x5", new IntDomaine(1,10));
        XsupC cont5 = new XsupC(var5, 5);
        try {
            assertTrue(cont5.evaluate(), "Erreur : XsupCTest cont5 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(6, 10).getDomain(), var5.getDomaine().getDomain(), "Erreur : XsupCtest cont5 : Renvoie le mauvais Domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

    }
}