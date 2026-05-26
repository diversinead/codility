package test.java.brownbelt;

import main.java.brownbelt.BallsInBucket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BallsInBucketTest {

    private final BallsInBucket ballsInBucket = new BallsInBucket();

    // === Provided examples ===
    @Test
    public void example1() {
        assertEquals(2, ballsInBucket.solution("..B....B.BB"));
    }

    @Test
    public void example2() {
        assertEquals(4, ballsInBucket.solution("BB.B.BBB..."));
    }

    @Test
    public void example3() {
        assertEquals(-1, ballsInBucket.solution(".BBB.B"));
    }

    @Test
    public void example4() {
        assertEquals(0, ballsInBucket.solution(".......B.B"));
    }

    // === Edge cases ===
    @Test
    public void singleBall() {
        assertEquals(0, ballsInBucket.solution("B"));
    }

    @Test
    public void singleBallwithDots() {
        assertEquals(0, ballsInBucket.solution("...B..."));
    }

    @Test
    public void twoAdjacentBalls() {
        assertEquals(-1, ballsInBucket.solution("BB"));
    }

    @Test
    public void twoCorrectBalls() {
        assertEquals(0, ballsInBucket.solution("B.B"));
    }

    @Test
    public void spaceTwoBalls() {
        assertEquals(1, ballsInBucket.solution(".BB"));
    }

    @Test
    public void twoBallsSpace() {
        assertEquals(1, ballsInBucket.solution("BB."));
    }

    // === Already correct ===
    @Test
    public void alreadyCorrectBdotBdotB() {
        assertEquals(0, ballsInBucket.solution("B.B.B"));
    }

    @Test
    public void alreadyCorrectLeadingDot() {
        assertEquals(0, ballsInBucket.solution(".B.B.B"));
    }

    @Test
    public void longAlternating() {
        assertEquals(0, ballsInBucket.solution("B.B.B.B.B.B"));
    }

    // === Impossible ===
    @Test
    public void tooManyBallsBBBB() {
        assertEquals(-1, ballsInBucket.solution("BBBB"));
    }

    @Test
    public void allBFive() {
        assertEquals(-1, ballsInBucket.solution("BBBBB"));
    }

    // === Parity choice ===
    @Test
    public void clusterLeft() {
        assertEquals(2, ballsInBucket.solution("BBBB......"));
    }

    @Test
    public void clusterRight() {
        assertEquals(2, ballsInBucket.solution("......BBBB"));
    }

    @Test
    public void mixedDotBBdotB() {
        assertEquals(1, ballsInBucket.solution(".BB.B"));
    }

    @Test
    public void mixedBdotBBdot() {
        assertEquals(1, ballsInBucket.solution("B.BB."));
    }

    @Test
    public void threeBallsBBBdotdot() {
        assertEquals(1, ballsInBucket.solution("BBB.."));
    }

    // === Larger tests ===
    @Test
    public void longAllCorrect() {
        assertEquals(0, ballsInBucket.solution("B.B.B.B.B.B.B.B.B.B"));
    }

    // === Stress tests ===
    @Test
    public void largeAlternating() {
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 50000; i++) large.append("B.");
        assertEquals(0, ballsInBucket.solution(large.toString()));
    }

    @Test
    public void largeAllB() {
        StringBuilder allBalls = new StringBuilder();
        for (int i = 0; i < 100000; i++) allBalls.append('B');
        assertEquals(-1, ballsInBucket.solution(allBalls.toString()));
    }
}