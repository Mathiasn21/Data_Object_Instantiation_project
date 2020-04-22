package unitTests;

import doiframework.statistics.calculations.Combinations;
import doiframework.statistics.calculations.Permutations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PermutationAndCombinationTest {
    private final Permutations perm = new Permutations(4, 4);
    private final Combinations comb = new Combinations(5,3);
    double p;

    @Test
    public void permutationsWithRepitionTest(){
         p = perm.withRepition();
         assertEquals(256, p);
         assertNotEquals(255, p);
    }
    @Test
    public void permutationsWithoutRepitionTest(){
         p = perm.withoutRepition();
         assertEquals(24, p);
         assertNotEquals(25,p);
    }
    @Test
    public void combinationsWithRepitionTest(){
         p = comb.withReputition();
         assertEquals(35, p);
         assertNotEquals(32, p);
    }

    @Test
    public void combinationsWithoutRepitionTest(){
        p = comb.withoutReputition();
        System.out.println(p);
        assertEquals(10, p);
        assertNotEquals(11, p);
    }
}
