package tests.solvercsp.Contraintes.Unaire;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.Unaire.XdiffC;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class XdiffCTest {

    @Test
    void evaluateStructurel() {
        //Dans ces deux cas la méthode doit renvoyer Null
        Variable testNull1 = new Variable("testNull1", new IntDomaine(10,10));
        XdiffC testNull1cont = new XdiffC(testNull1, 10);
        assertThrows(ExceptionDomNull.class, testNull1cont::evaluate, "Expected ExceptionDomNull to be thrown");
        assertNull(testNull1.getDomaine().getDomain(), "erreur: XdiffC testNull1cont");

        //Le domaine ne doit pas être modifié, la méthode doit donc renvoyer False
        Variable testFalse = new Variable("testFalse", new IntDomaine(1,10));
        XdiffC testFalseCont = new XdiffC(testFalse, 0);
        try {
            assertFalse(testFalseCont.evaluate());
            assertEquals(new IntDomaine(1, 10).getDomain(), testFalse.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        //Le domaine doit être modifié, la méthode doit donc renvoyer True
        Variable testTrue1 = new Variable("testTrue1", new IntDomaine(1,10));
        XdiffC testTrue1Cont = new XdiffC(testTrue1, 1);
        try {
            assertTrue(testTrue1Cont.evaluate());
            assertNotEquals(new IntDomaine(1, 10).getDomain(), testTrue1.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        Variable testTrue2 = new Variable("testTrue2", new IntDomaine(1,10));
        XdiffC testTrue2Cont = new XdiffC(testTrue2, 5);
        try {
            assertTrue(testTrue2Cont.evaluate());
            assertNotEquals(new IntDomaine(1, 10).getDomain(), testTrue2.getDomaine().getDomain());
            assertEquals(9, (((IntDomaine)testTrue2.getDomaine()).getCardDomaine()));
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }

    @Test
    void evaluateFonctionnel() {
        Variable var = new Variable("x", new IntDomaine(1,10));


        // valeurs en dehors du domaine
            //1er test
        Variable var1 = new Variable("x", new IntDomaine(1,10));
        XdiffC cont1 = new XdiffC(var1, 0);
        try {
            assertFalse(cont1.evaluate(), "Erreur : XdiffCTest cont1 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1,10).getDomain(), var1.getDomaine().getDomain(), "Erreur: XdiffC cont1 édition du domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


            //2eme test
        Variable var2 = new Variable("x", new IntDomaine(1,10));
        XdiffC cont2 = new XdiffC(var, 11);
        //assertFalse(cont2.evaluate(), "erreur: XdiffC cont2 édition du domaine");
        assertEquals(var2.getDomaine().getDomain(), var.getDomaine().getDomain(), "erreur: XdiffC cont2 édition du domaine");
        try {
            assertFalse(cont2.evaluate(), "Erreur : XdiffCTest cont2 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1,10).getDomain(), var2.getDomaine().getDomain(), "Erreur: XdiffC cont2 édition du domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }



        // valeurs en borne du domaine
            //1er test
        Variable var3 = new Variable("x", new IntDomaine(1,10));
        XdiffC cont3 = new XdiffC(var3, 1);
        try {
            assertTrue(cont3.evaluate(), "Erreur : XdiffCTest cont3 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(2,10).getDomain(), var3.getDomaine().getDomain(), "Erreur: XdiffC cont3 édition du domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


            //2eme test
        Variable var4 = new Variable("x", new IntDomaine(1,10));
        XdiffC cont4 = new XdiffC(var4, 10);
        try {
            assertTrue(cont4.evaluate(), "Erreur : XdiffCTest cont4 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1,9).getDomain(), var4.getDomaine().getDomain(), "Erreur: XdiffC cont4 édition du domaine");
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }



        // valeurs dans le domaine
        Variable var5 = new Variable("x", new IntDomaine(1,10));
        XdiffC cont5 = new XdiffC(var5, 5);
        try {
            assertTrue(cont5.evaluate(), "Erreur : XdiffCTest cont5 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1,4, 6,10).getDomain(), var5.getDomaine().getDomain(), "Erreur: XdiffC cont5 édition du domaine");
            assertEquals(9, ((IntDomaine)var5.getDomaine()).getCardDomaine());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        //aller les bleus
        Variable var6 = new Variable("x", new IntDomaine(1,10, 12,17));
        XdiffC cont6 = new XdiffC(var6, 5);
        try {
            assertTrue(cont6.evaluate(), "Erreur : XdiffCTest cont5 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1,4, 6,10, 12,17).getDomain(), var6.getDomaine().getDomain(), "Erreur: XdiffC cont6 édition du domaine");
            assertEquals(15, ((IntDomaine)var6.getDomaine()).getCardDomaine());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

        Variable var7 = new Variable("x", new IntDomaine(1,10, 12,17));
        XdiffC cont7 = new XdiffC(var7, 11);
        try {
            assertFalse(cont7.evaluate(), "Erreur : XdiffCTest cont7 : Renvoie le mauvais Booléen");
            assertEquals(new IntDomaine(1,10, 12,17).getDomain(), var7.getDomaine().getDomain(), "Erreur: XdiffC cont7 édition du domaine");
            assertEquals(16, ((IntDomaine)var7.getDomaine()).getCardDomaine());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
    }
}