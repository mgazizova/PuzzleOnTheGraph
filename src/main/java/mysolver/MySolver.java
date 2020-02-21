package mysolver;

public class MySolver implements ConundrumSolver{

    public int[] resolve(int[] initialState){
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
        AStar aStar = new AStar(rules, referenceState);
        return aStar.start(initialState);
    }
}
