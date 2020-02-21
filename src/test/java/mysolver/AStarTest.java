package mysolver;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class AStarTest {
    int[][] rules = {
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0}
    };

    int[] referenceState = {1, 2, 3, 4, 0, 5, 6, 7};

    @Test
    public void findNext() {
        State state = new State(new int[]{1, 2, 3, 0, 4, 5, 6, 7}, null, 0, 1);
        AStar aStar = new AStar(rules, referenceState);
        aStar.findNext(state, 3);

        State st1 = new State(new int[]{1, 0, 3, 2, 4, 5, 6, 7}, state, 3, 1, 3);
        State st2 = new State(new int[]{1, 2, 3, 4, 0, 5, 6, 7}, state, 3,1, 0);
        State st3 = new State(new int[]{1, 2, 3, 6, 4, 5, 0, 7}, state, 3,1, 3);

        assertTrue(aStar.weightedList.contains(st1));
        assertTrue(aStar.weightedList.contains(st2));
        assertTrue(aStar.weightedList.contains(st3));
    }

    @Test
    public void checkErrorsNumber() {
        State state = new State(new int[]{1,2,3,0,4,5,7,6}, null, 0, 1);
        AStar aStar = new AStar(rules, referenceState);
        Assert.assertEquals(aStar.checkErrorsNumber(state.state, referenceState), 4);
    }

    @Test
    public void swap() {
        State state = new State(new int[]{1,2,3,0,4,5,6,7}, null, 0, 1);
        AStar aStar = new AStar(rules, referenceState);
        State actualState = aStar.swap(state, 3,4);
        Assert.assertArrayEquals(actualState.state, referenceState);
    }

    @Test
    public void start(){
        AStar aStar = new AStar(rules, referenceState);
        int[] actualRes = aStar.start(new int[]{1,2,3,0,4,5,6,7});
        Assert.assertArrayEquals(new int[]{4}, actualRes);
    }
}