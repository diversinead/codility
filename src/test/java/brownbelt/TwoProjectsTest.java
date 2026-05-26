package test.java.brownbelt;

import main.java.brownbelt.TwoProjects;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoProjectsTest {

    private final TwoProjects twoProjects = new TwoProjects();
   
    // === Provided examples ===
    @Test
    public void example1_twoProjectChainViaDependency() {
     assertEquals(9, twoProjects.solution(
             new int[]{-3, 5, 7, 2, 3},
             new int[]{3, 1},
             new int[]{2, 4}));
    }
   
    @Test
    public void example2_projectWithTwoPrereqsIsUnreachable() {
     assertEquals(2, twoProjects.solution(
             new int[]{1, 1, 5},
             new int[]{0, 1},
             new int[]{2, 2}));
    }
   
    @Test
    public void example3_cycleBlocksProjectsAndNegativeIsSkipped() {
     assertEquals(5, twoProjects.solution(
             new int[]{5, 6, 6, 7, -10},
             new int[]{0, 0, 0, 1, 2, 3},
             new int[]{1, 2, 3, 2, 3, 1}));
    }
   
    // === Trivial / boundary ===
   
    @Test
    public void singleProjectNoRequirements() {
     assertEquals(42, twoProjects.solution(
             new int[]{42},
             new int[]{},
             new int[]{}));
    }
   
    @Test
    public void singleNegativeProject_doNothingIsBetter() {
     assertEquals(0, twoProjects.solution(
             new int[]{-7},
             new int[]{},
             new int[]{}));
    }
   
    @Test
    public void noRequirements_sumOfTopTwo() {
     assertEquals(15, twoProjects.solution(
             new int[]{1, 2, 8, 7, 3},
             new int[]{},
             new int[]{}));
    }
   
    @Test
    public void twoCycleHasNoFreeProject() {
     assertEquals(0, twoProjects.solution(
             new int[]{10, 20},
             new int[]{0, 1},
             new int[]{1, 0}));
    }
   
    // === Branch selection: free+free vs free+dependent ===
   
    @Test
    public void twoFreeBeatsDependentPair() {
     // Free: 0 (10), 1 (9). Project 2 depends on 0 (5).  Best = 10+9 = 19.
     assertEquals(19, twoProjects.solution(
             new int[]{10, 9, 5},
             new int[]{0},
             new int[]{2}));
    }
   
    @Test
    public void dependentPairBeatsTwoFree() {
     // Free: 0 (10), 1 (1). Project 2 depends on 0 (100). Best = 10+100 = 110.
     assertEquals(110, twoProjects.solution(
             new int[]{10, 1, 100},
             new int[]{0},
             new int[]{2}));
    }
   
    @Test
    public void negativeDependentSkippedInFavorOfSingle() {
     // Free 0 (10), dependent 1 (-50). Best = 10 alone.
     assertEquals(10, twoProjects.solution(
             new int[]{10, -50},
             new int[]{0},
             new int[]{1}));
    }
   
    @Test
    public void picksBestDependentWhenSeveralShareSamePrereq() {
     // Free 0 (1). Dependents 1,2,3 each need only 0; values 4, 9, 2.
     assertEquals(10, twoProjects.solution(
             new int[]{1, 4, 9, 2},
             new int[]{0, 0, 0},
             new int[]{1, 2, 3}));
    }
   
    // === All-negative / partial-negative ===
   
    @Test
    public void allNegativeFreeProjects_returnZero() {
     assertEquals(0, twoProjects.solution(
             new int[]{-5, -3},
             new int[]{},
             new int[]{}));
    }
   
    @Test
    public void mixedSigns_takeOnlyPositive() {
     assertEquals(7, twoProjects.solution(
             new int[]{7, -3},
             new int[]{},
             new int[]{}));
    }
   
    // === Structural cases ===
   
    @Test
    public void chainOnlyFirstTwoReachable() {
     // 0 -> 1 -> 2 -> 3. Only 0 free; 1 reachable via 0.
     // Project 2's only prereq is 1 (not free), so 2 not reachable in 2 steps.
     assertEquals(9, twoProjects.solution(
             new int[]{5, 4, 100, 100},
             new int[]{0, 1, 2},
             new int[]{1, 2, 3}));
    }
   
    @Test
    public void diamondTopHasTwoPrereqsSoUnreachable() {
     // 0 free; 1 needs {0}; 2 needs {0}; 3 needs {1,2}.
     // Best = V[0] + max(V[1], V[2]) = 1 + 8 = 9.
     assertEquals(9, twoProjects.solution(
             new int[]{1, 8, 3, 1000},
             new int[]{0, 0, 1, 2},
             new int[]{1, 2, 3, 3}));
    }
   
    // === Numeric range ===
   
    @Test
    public void largeValues_noIntOverflow() {
     assertEquals(1_999_999_998, twoProjects.solution(
             new int[]{1_000_000_000, 999_999_998, -1_000_000_000},
             new int[]{},
             new int[]{}));
    }
}