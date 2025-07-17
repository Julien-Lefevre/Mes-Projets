package tests.solvercsp;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.Unaire.XinfC;
import solver.solvercsp.Contraintes.Unaire.XsupC;
import solver.solvercsp.Domaine;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class VariableTest {

    @Test
    void getDomaine() {
        //On veux verifier que le domaine renvoyé est le bon
        Variable var = new Variable("var", new IntDomaine(1,10));
        Domaine result = var.getDomaine();
        assertEquals(new IntDomaine(1,10).getDomain(), result.getDomain());
        assertNotEquals(new IntDomaine(1,5).getDomain(), result.getDomain());
    }

    @Test
    void getNom() {
        //On veut vérifier que le nom retourné est le bon
        Variable var = new Variable("x", new IntDomaine(1,10));
        assertEquals("x", var.getNom());
        assertNotEquals("var", var.getNom());
    }

    @Test
    void infDomaine1() {
        Variable var1 = new Variable("x", new IntDomaine(1,10));

        // valeurs en dehors du domaine

        assertTrue(var1.infDomaine(0));
        assertNull(var1.getDomaine().getDomain(), "erreur: infDomaine var1");
    }

    @Test
    void infDomaine2() {
        Variable var2 = new Variable("x", new IntDomaine(1,10));

        // valeurs en dehors du domaine
        assertFalse(var2.infDomaine(11));
        assertEquals(new IntDomaine(1, 10).getDomain(), var2.getDomaine().getDomain(),"erreur: XinfC cont11");
    }

    @Test
    void infDomaine3() {
        Variable var3 = new Variable("x", new IntDomaine(1,10));

        // valeurs en borne du domaine
        assertTrue(var3.infDomaine(1));
        assertNull(var3.getDomaine().getDomain(), "erreur: XinfC cont1");
    }

    @Test
    void infDomaine4() {
        Variable var4 = new Variable("x", new IntDomaine(1,10));

        // valeurs dans le domaine
        assertTrue(var4.infDomaine(10));
        assertEquals(new IntDomaine(1, 9).getDomain(), var4.getDomaine().getDomain(), "erreur: XinfC cont10");
    }

    @Test
    void infDomaine5() {
        Variable var5 = new Variable("x", new IntDomaine(1,10));

        // valeurs dans le domaine
        assertTrue(var5.infDomaine(5));
        assertEquals(new IntDomaine(1, 4).getDomain(), var5.getDomaine().getDomain(), "erreur: XinfC cont5");

    }

//    @Test
//    void supDomaine1() {
//        Variable var1 = new Variable("x1", new IntDomaine(1,10));
//
//        // valeurs en dehors du domaine
//        assertFalse(var1.supDomaine(0));
//        assertEquals(new IntDomaine(1, 10).getDomain(), var1.getDomaine().getDomain(), "erreur: XsupC cont0");
//    }
//
//    @Test
//    void supDomaine2() {
//        Variable var2 = new Variable("x2", new IntDomaine(1,10));
//
//        // valeurs en dehors du domaine
//        XsupC cont11 = new XsupC(var2, 11);
//
//        //assertThrows(NullPointerException.class, var2::supDomaine(11), "Expected NullPointerException to be thrown");
//        assertNull(var2.getDomaine().getDomain(), "erreur: XsupC cont11");
//
//
//        assertTrue(cont1.evaluate());
//        assertEquals(new IntDomaine(2, 10).getDomain(), var3.getDomaine().getDomain(),"erreur: XsupC cont1");
//
//        assertThrows(NullPointerException.class, cont10::evaluate, "Expected NullPointerException to be thrown");
//        assertNull(var4.getDomaine().getDomain(), "erreur: XsupC cont10");
//
//        assertTrue(cont5.evaluate());
//        assertEquals(new IntDomaine(6, 10).getDomain(), var5.getDomaine().getDomain(),"erreur: XsupC cont5");
//    }
//
//    @Test
//    void supDomaine3() {
//        Variable var1 = new Variable("x1", new IntDomaine(1,10));
//        Variable var2 = new Variable("x2", new IntDomaine(1,10));
//        Variable var3 = new Variable("x3", new IntDomaine(1,10));
//        Variable var4 = new Variable("x4", new IntDomaine(1,10));
//        Variable var5 = new Variable("x5", new IntDomaine(1,10));
//
//        // valeurs en dehors du domaine
//        XsupC cont0 = new XsupC(var1, 0);
//        XsupC cont11 = new XsupC(var2, 11);
//
//        // valeurs en borne du domaine
//        XsupC cont1 = new XsupC(var3, 1);
//        XsupC cont10 = new XsupC(var4, 10);
//
//        // valeurs dans le domaine
//        XsupC cont5 = new XsupC(var5, 5);
//
//        assertFalse(cont0.evaluate());
//        assertEquals(new IntDomaine(1, 10).getDomain(), var1.getDomaine().getDomain(), "erreur: XsupC cont0");
//
//        assertThrows(NullPointerException.class, cont11::evaluate, "Expected NullPointerException to be thrown");
//        assertNull(var2.getDomaine().getDomain(), "erreur: XsupC cont11");
//
//
//        assertTrue(cont1.evaluate());
//        assertEquals(new IntDomaine(2, 10).getDomain(), var3.getDomaine().getDomain(),"erreur: XsupC cont1");
//
//        assertThrows(NullPointerException.class, cont10::evaluate, "Expected NullPointerException to be thrown");
//        assertNull(var4.getDomaine().getDomain(), "erreur: XsupC cont10");
//
//        assertTrue(cont5.evaluate());
//        assertEquals(new IntDomaine(6, 10).getDomain(), var5.getDomaine().getDomain(),"erreur: XsupC cont5");
//    }
//
//    @Test
//    void supDomaine4() {
//        Variable var1 = new Variable("x1", new IntDomaine(1,10));
//        Variable var2 = new Variable("x2", new IntDomaine(1,10));
//        Variable var3 = new Variable("x3", new IntDomaine(1,10));
//        Variable var4 = new Variable("x4", new IntDomaine(1,10));
//        Variable var5 = new Variable("x5", new IntDomaine(1,10));
//
//        // valeurs en dehors du domaine
//        XsupC cont0 = new XsupC(var1, 0);
//        XsupC cont11 = new XsupC(var2, 11);
//
//        // valeurs en borne du domaine
//        XsupC cont1 = new XsupC(var3, 1);
//        XsupC cont10 = new XsupC(var4, 10);
//
//        // valeurs dans le domaine
//        XsupC cont5 = new XsupC(var5, 5);
//
//        assertFalse(cont0.evaluate());
//        assertEquals(new IntDomaine(1, 10).getDomain(), var1.getDomaine().getDomain(), "erreur: XsupC cont0");
//
//        assertThrows(NullPointerException.class, cont11::evaluate, "Expected NullPointerException to be thrown");
//        assertNull(var2.getDomaine().getDomain(), "erreur: XsupC cont11");
//
//
//        assertTrue(cont1.evaluate());
//        assertEquals(new IntDomaine(2, 10).getDomain(), var3.getDomaine().getDomain(),"erreur: XsupC cont1");
//
//        assertThrows(NullPointerException.class, cont10::evaluate, "Expected NullPointerException to be thrown");
//        assertNull(var4.getDomaine().getDomain(), "erreur: XsupC cont10");
//
//        assertTrue(cont5.evaluate());
//        assertEquals(new IntDomaine(6, 10).getDomain(), var5.getDomaine().getDomain(),"erreur: XsupC cont5");
//    }
//
//    @Test
//    void supDomaine5() {
//        Variable var1 = new Variable("x1", new IntDomaine(1,10));
//        Variable var2 = new Variable("x2", new IntDomaine(1,10));
//        Variable var3 = new Variable("x3", new IntDomaine(1,10));
//        Variable var4 = new Variable("x4", new IntDomaine(1,10));
//        Variable var5 = new Variable("x5", new IntDomaine(1,10));
//
//        // valeurs en dehors du domaine
//        XsupC cont0 = new XsupC(var1, 0);
//        XsupC cont11 = new XsupC(var2, 11);
//
//        // valeurs en borne du domaine
//        XsupC cont1 = new XsupC(var3, 1);
//        XsupC cont10 = new XsupC(var4, 10);
//
//        // valeurs dans le domaine
//        XsupC cont5 = new XsupC(var5, 5);
//
//        assertFalse(cont0.evaluate());
//        assertEquals(new IntDomaine(1, 10).getDomain(), var1.getDomaine().getDomain(), "erreur: XsupC cont0");
//
//        assertThrows(NullPointerException.class, cont11::evaluate, "Expected NullPointerException to be thrown");
//        assertNull(var2.getDomaine().getDomain(), "erreur: XsupC cont11");
//
//
//        assertTrue(cont1.evaluate());
//        assertEquals(new IntDomaine(2, 10).getDomain(), var3.getDomaine().getDomain(),"erreur: XsupC cont1");
//
//        assertThrows(NullPointerException.class, cont10::evaluate, "Expected NullPointerException to be thrown");
//        assertNull(var4.getDomaine().getDomain(), "erreur: XsupC cont10");
//
//        assertTrue(cont5.evaluate());
//        assertEquals(new IntDomaine(6, 10).getDomain(), var5.getDomaine().getDomain(),"erreur: XsupC cont5");
//    }

    @Test
    void diffDomaine() {
    }

    @Test
    void egalDomaine() {
    }

    @Test
    void doppelganger() {
    }
}