package tests.solvercsp.Contraintes.N_aire;

import org.junit.jupiter.api.Test;
import solver.solvercsp.Contraintes.N_aire.AllDifferent;
import solver.solvercsp.ExceptionDomNull;
import solver.solvercsp.IntDomaine;
import solver.solvercsp.Variable;

import static org.junit.jupiter.api.Assertions.*;

class AllDifferentTest {

    @Test
    void evaluateStructurel()
    {
        //Les domaines sont identiques et de cardinalité 1 : on doit avoir une Exception
        Variable testNull1 = new Variable("testNull1", new IntDomaine(1,1));
        Variable testNull2 = new Variable("testNull2", new IntDomaine(2,2));
        Variable testNull3 = new Variable ("testNull3", new IntDomaine(1,1));

        AllDifferent nullCont = new AllDifferent(testNull1, testNull2, testNull3);

        assertThrows(ExceptionDomNull.class, nullCont::evaluate, "Expected ExceptionDomNull to be thrown");

        //Aucun domaine de cardinalité 1 : on attend False en retour
        Variable testFalse1 = new Variable("testFalse1", new IntDomaine(1,3));
        Variable testFalse2 = new Variable("testFalse2", new IntDomaine(8,10));
        Variable testFalse3 = new Variable ("testFalse3", new IntDomaine(1,2));

        AllDifferent falseCont = new AllDifferent(testFalse1, testFalse2, testFalse3);
        try {
            assertFalse(falseCont.evaluate());
            assertEquals(new IntDomaine(1, 3).getDomain(), testFalse1.getDomaine().getDomain());
            assertEquals(new IntDomaine(8, 10).getDomain(), testFalse2.getDomaine().getDomain());
            assertEquals(new IntDomaine(1, 2).getDomain(), testFalse3.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }


        //Un ou plusieurs domaines de taille 1 sans déclencher d'Exception
        Variable testTrue1 = new Variable("testTrue1", new IntDomaine(1,1));
        Variable testTrue2 = new Variable("testTrue2", new IntDomaine(2,10));
        Variable testTrue3 = new Variable ("testTrue3", new IntDomaine(1,2));

        AllDifferent trueCont = new AllDifferent(testTrue1, testTrue2, testTrue3);
        try {
            assertTrue(trueCont.evaluate());
            assertEquals(new IntDomaine(1, 1).getDomain(), testTrue1.getDomaine().getDomain());
            assertEquals(new IntDomaine(3, 10).getDomain(), testTrue2.getDomaine().getDomain());
            assertEquals(new IntDomaine(2, 2).getDomain(), testTrue3.getDomaine().getDomain());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }

    }


    @Test
    void evaluate1() {
        //Aucune variable n'est de cardinalité 1, aucun changement attendu
        Variable varX1 = new Variable("x1", new IntDomaine(1, 10));
        Variable varX2 = new Variable("x2", new IntDomaine(20, 30));
        Variable varX3 = new Variable("x3", new IntDomaine(40, 50));

        AllDifferent cont1 = new AllDifferent(varX1, varX2, varX3);
        try {
            assertFalse(cont1.evaluate());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
        assertEquals(new IntDomaine(1, 10).getDomain(), varX1.getDomaine().getDomain());
        assertEquals(new IntDomaine(20, 30).getDomain(), varX2.getDomaine().getDomain());
        assertEquals(new IntDomaine(40, 50).getDomain(), varX3.getDomaine().getDomain());


        //Une Variable est de cardinalité 1, réduisant les autres domaines à un de cardinalité par cascade
        Variable varY1 = new Variable("y1", new IntDomaine(1, 1));
        Variable varY2 = new Variable("y2", new IntDomaine(1, 2));
        Variable varY3 = new Variable("y3", new IntDomaine(1, 3));

        AllDifferent cont2 = new AllDifferent(varY1, varY2, varY3);
        try {
            assertTrue(cont2.evaluate());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
        assertEquals(new IntDomaine(1, 1).getDomain(), varY1.getDomaine().getDomain());
        assertEquals(new IntDomaine(2, 2).getDomain(), varY2.getDomaine().getDomain());
        assertEquals(new IntDomaine(3, 3).getDomain(), varY3.getDomaine().getDomain());



        //2 variables ont 1 de cardinalité, le dernier domaine est trop petit, on attend une exception
        Variable varZ1 = new Variable("z1", new IntDomaine(1, 1));
        Variable varZ2 = new Variable("z2", new IntDomaine(1, 2));
        Variable varZ3 = new Variable("z3", new IntDomaine(2, 2));

        AllDifferent cont3 = new AllDifferent(varZ1, varZ2, varZ3);
        assertThrows(ExceptionDomNull.class, cont3::evaluate, "Expected ExceptionDomNull to be thrown");



        //1 variable à un de cardinalité, l'un des domaines restants devrait être scindé
        Variable varT1 = new Variable("t1", new IntDomaine(1,1));
        Variable varT2 = new Variable("t2", new IntDomaine(1,10));
        Variable varT3 = new Variable("t3", new IntDomaine(0,5));

        AllDifferent cont4 = new AllDifferent(varT1, varT2, varT3);
        try {
            assertTrue(cont4.evaluate());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
        assertEquals(new IntDomaine(1, 1).getDomain(), varT1.getDomaine().getDomain());
        assertEquals(new IntDomaine(2, 10).getDomain(), varT2.getDomaine().getDomain());
        assertEquals(new IntDomaine(0,0,2,5).getDomain(), varT3.getDomaine().getDomain());


        //1 variable à un de cardinalité, mais aucun domaine n'est touché, on attend false en retour
        Variable varV1 = new Variable("v1", new IntDomaine(-5,0));
        Variable varV2 = new Variable("v2", new IntDomaine(5,10));
        Variable varV3 = new Variable("v3", new IntDomaine(1,1));

        AllDifferent cont5 = new AllDifferent(varV1, varV2, varV3);
        try {
            assertFalse(cont5.evaluate());
        } catch (ExceptionDomNull e) {
            fail("Unexpected ExceptionDomNull");
        }
        assertEquals(new IntDomaine(-5, 0).getDomain(), varV1.getDomaine().getDomain());
        assertEquals(new IntDomaine(5, 10).getDomain(), varV2.getDomaine().getDomain());
        assertEquals(new IntDomaine(1,1).getDomain(), varV3.getDomaine().getDomain());

        //1 variable à un de cardinalité, mais aucun domaine n'est touché, on attend false en retour
        //Variable varA1 = new Variable("a1", new IntDomaine(2,2,4,4,8,8));
        Variable varA2 = new Variable("a2", new IntDomaine(7,7));
        Variable varA3 = new Variable("a3", new IntDomaine(7,7));

        AllDifferent cont6 = new AllDifferent(varA3, varA2);

//        try {
//            assertFalse(cont6.evaluate());
//        } catch (ExceptionDomNull e) {
//            fail("Unexpected ExceptionDomNull");
//        }
//        assertEquals(new IntDomaine(2,2,4,4,8,8).getDomain(), varA1.getDomaine().getDomain());
//        assertEquals(new IntDomaine(7, 7).getDomain(), varA2.getDomaine().getDomain());
//        //assertEquals(new IntDomaine(1,1).getDomain(), varV3.getDomaine().getDomain());
        assertThrows(ExceptionDomNull.class, cont6::evaluate, "Expected ExceptionDomNull to be thrown");



    }

}