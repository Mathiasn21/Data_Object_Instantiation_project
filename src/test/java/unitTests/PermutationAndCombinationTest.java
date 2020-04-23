package unitTests;

import doiframework.statistics.calculations.Combinations;
import doiframework.statistics.calculations.Permutations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PermutationAndCombinationTest {
    private final Permutations perm = new Permutations();
    private final Combinations comb = new Combinations();
    private final Combinations comb2 = new Combinations();

    double p;
    double p2;

    @Test
    public void permutationsWithRepitionTest(){
         p = perm.withRepetition(4, 4);
         assertEquals(256, p);
         assertNotEquals(255, p);
    }
    @Test
    public void permutationsWithoutRepitionTest(){
         p = perm.withoutRepetition(4, 4);
         assertEquals(24, p);
         assertNotEquals(25,p);
    }
    @Test
    public void combinationsWithRepitionTest(){
         p = comb.withRepetition(5,3);
         assertEquals(35, p);
         assertNotEquals(32, p);
    }

    @Test
    public void combinationsWithoutRepitionTest(){
        p = comb.binominalCoefficient(5,3);
        p2 = comb2.binominalCoefficient(25,14);
        assertEquals(10, p);
        assertNotEquals(11, p);
        assertEquals(4457400, p2);
    }
}
