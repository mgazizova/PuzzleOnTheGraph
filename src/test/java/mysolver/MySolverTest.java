package mysolver;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MySolverTest {

    @Test
    public void resolveWithoutSwap() {
        int[] initialState = {1, 2, 3, 4, 0, 5, 6, 7};
        int[] expectedState = {};

        ConundrumSolver mySolver = new MySolver();
        int[] actualState = mySolver.resolve(initialState);

        assertArrayEquals(expectedState, actualState);
    }

    @Test
    public void resolveWithFewSteps(){
        int[] initialState = {2, 1, 3, 4, 0, 5, 6, 7};
        int[] expectedState = {5, 3, 2, 1, 2, 3, 5};

        ConundrumSolver mySolver = new MySolver();
        int[] actualState = mySolver.resolve(initialState);

        assertArrayEquals(expectedState, actualState);
    }

    @Test
    public void resolveWithManySteps(){
        int[] initialState = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] expectedState1 = {2, 1, 3, 4, 5, 1, 3, 2, 3, 1, 5, 4, 2, 1, 3, 1, 2, 4};
        int[] expectedState2 = {2, 1, 3, 4, 5, 1, 3, 2, 3, 1, 5, 4, 2, 3, 1, 3, 2, 4};

        ConundrumSolver mySolver = new MySolver();
        int[] actualState = mySolver.resolve(initialState);

        Assert.assertTrue(Arrays.equals(expectedState1, actualState) ||
                Arrays.equals(expectedState2, actualState));
    }
}