package tests.solvercsp.Contraintes.Binaire;

import org.junit.jupiter.api.Test;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class XequalsYTest {

//    @Test
//    void evaluate1() {
//        Variable varX1 = new Variable("x", new IntDomaine(1, 10));
//        Variable varY1 = new Variable("y", new IntDomaine(20, 30));
//
//        //X a un domaine absolument différent au domaine de Y
//        XequalsY cont1 = new XequalsY(varX1, varY1);
//        //assertFalse(cont1.evaluate());
//        //assertTrue(cont1.evaluate(), "erreur: XequalsY cont1 domaine non édité");
//        assertNull(varX1.getDomaine());
//        assertNull(varY1.getDomaine());
//    }
//
//    @Test
//    void evaluate2() {
//        Variable varX2 = new Variable("x", new IntDomaine(1, 10));
//        Variable varY2 = new Variable("y", new IntDomaine(-10, 3));
//
//        //X a un domaine qui est sur une partie du domaine de Y en min ou en max
//        XequalsY cont2 = new XequalsY(varX2, varY2);
//
//        //assertTrue(cont2.evaluate(), "erreur: XequalsY cont2 domaine non édité");
//
//        assertEquals(3, ((IntDomaine) varX2.getDomaine()).getCardDomaine(), "erreur: XequalsY cont2 cardinalité incorrecte");
//        assertEquals(3, ((IntDomaine) varY2.getDomaine()).getCardDomaine(), "erreur: XequalsY cont2 cardinalité incorrecte");
//
//        assertEquals(3,((IntDomaine)varX2.getDomaine()).getMaxDomaine(), "erreur: XequalsY cont2 maximum domaine incorrect");
//        assertEquals(1,((IntDomaine)varX2.getDomaine()).getMinDomaine(), "erreur: XequalsY cont2 minimum domaine incorrect");
//        assertEquals(3,((IntDomaine)varY2.getDomaine()).getMaxDomaine(), "erreur: XequalsY cont2 maximum domaine incorrect");
//        assertEquals(1,((IntDomaine)varY2.getDomaine()).getMinDomaine(), "erreur: XequalsY cont2 minimum domaine incorrect");
//    }
//
//    @Test
//    void evaluate3() {
//        Variable varX3 = new Variable("x", new IntDomaine(1, 10));
//        Variable varY3 = new Variable("y", new IntDomaine(1, 10));
//
//        //X et Y ont exactement le même domaine
//        XequalsY cont3 = new XequalsY(varX3, varY3);
//        //assertFalse(cont3.evaluate());
//
//        assertEquals(varX3.getDomaine(), varY3.getDomaine(), "erreur: XequalsY cont3 Domaine x ou y incorrecte");
//        assertEquals(varY3.getDomaine(), new IntDomaine(1, 10), "erreur: XequalsY cont3 Domaine x et y incorrecte");
//
//        assertEquals(10, ((IntDomaine) varX3.getDomaine()).getCardDomaine(), "erreur: XequalsY cont3 cardinalité incorrecte");
//        assertEquals(10, ((IntDomaine) varY3.getDomaine()).getCardDomaine(), "erreur: XequalsY cont3 cardinalité incorrecte");
//
//        assertEquals(10,((IntDomaine)varX3.getDomaine()).getMaxDomaine(), "erreur: XequalsY cont3 maximum domaine incorrect");
//        assertEquals(1,((IntDomaine)varX3.getDomaine()).getMinDomaine(), "erreur: XequalsY cont3 minimum domaine incorrect");
//        assertEquals(10,((IntDomaine)varY3.getDomaine()).getMaxDomaine(), "erreur: XequalsY cont3 maximum domaine incorrect");
//        assertEquals(1,((IntDomaine)varY3.getDomaine()).getMinDomaine(), "erreur: XequalsY cont3 minimum domaine incorrect");
//    }
//
//    @Test
//    void evaluate4() {
//        Variable varX4 = new Variable("x", new IntDomaine(1, 10));
//        Variable varY4 = new Variable("y", new IntDomaine(3, 7));
//
//        //X a son domaine inclus dans Y
//        XequalsY cont4 = new XequalsY(varX4, varY4);
//
//        //assertTrue(cont4.evaluate(), "erreur: XequalsY cont4 Domaine x ou y incorrecte");
//
//        assertEquals(varX4.getDomaine(), varY4.getDomaine(), "erreur: XequalsY cont4 Domaine x ou y incorrecte");
//        assertEquals(varY4.getDomaine(), new IntDomaine(3, 7), "erreur: XequalsY cont4 Domaine x et y incorrecte");
//
//        assertEquals(5, ((IntDomaine) varX4.getDomaine()).getCardDomaine(), "erreur: XequalsY cont4 cardinalité incorrecte");
//        assertEquals(5, ((IntDomaine) varY4.getDomaine()).getCardDomaine(), "erreur: XequalsY cont4 cardinalité incorrecte");
//
//        assertEquals(7,((IntDomaine)varX4.getDomaine()).getMaxDomaine(), "erreur: XequalsY cont4 maximum domaine incorrect");
//        assertEquals(3,((IntDomaine)varX4.getDomaine()).getMinDomaine(), "erreur: XequalsY cont4 minimum domaine incorrect");
//        assertEquals(7,((IntDomaine)varY4.getDomaine()).getMaxDomaine(), "erreur: XequalsY cont4 maximum domaine incorrect");
//        assertEquals(3,((IntDomaine)varY4.getDomaine()).getMinDomaine(), "erreur: XequalsY cont4 minimum domaine incorrect");
//    }
//
//
//
//    @Test
//    void testEvaluate() {
//    }
}