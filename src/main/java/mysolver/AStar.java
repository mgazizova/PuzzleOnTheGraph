package mysolver;

import java.util.*;

public class AStar {
    private final int[] referenceState;
    private final int[][] rules;
    public ArrayList<State> weightedList;

    public AStar(int[][] rules, int[] referenceState){
        this.rules = rules;
        this.referenceState = referenceState;
        weightedList = new ArrayList<>();
    }

    public int[] start(int[] initial){
        State currentState = new State(initial, null, 0, checkErrorsNumber(initial, referenceState));
        if (checkErrorsNumber(currentState.state, referenceState)!=0) {
            int zeroPos = 0;

            do {
                zeroPos = findZero(currentState.state);
                findNext(currentState, zeroPos);
                currentState = new State(first(weightedList));
            }
            while (currentState.getPathLength() != currentState.getMinCost());
            return packResult(currentState);
        }
        else return new int[]{};
    }

    public int[] packResult(State finalState){
        ArrayList<Integer> result = new ArrayList<>();
        while (finalState.getParent()!=null){
            result.add(finalState.getParentValue());
            finalState = finalState.getParent();
        }
        Collections.reverse(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public void findNext(State currentState, int zeroPos){
        for (int i=0; i<referenceState.length; i++){
            if (rules[zeroPos][i]==1 && i!=currentState.getPosition()) {
                State newState = swap(currentState, zeroPos, i);
                weightedList.add(newState);
            }
        }
        try {
            weightedList.remove(currentState);
        }
        catch (NoSuchElementException ex){}
    }

    public int checkErrorsNumber(int[] currentState, int[] referenceState){
        int errorsNum = 0;
        for (int i=0; i<currentState.length; i++)
            if (currentState[i]!=referenceState[i])
                errorsNum++;
        return errorsNum;
    }

    private int findZero(int[] array){
        int index = -1;
        for (int i=0; i<array.length; i++){
            if (array[i]==0){
                index = i;
                break;
            }
        }
        return index;
    }

    private State first(ArrayList<State> list){
        int minError = list.get(0).getPathLength();
        State minState = list.get(0);
        for(State st: list){
            if (st.getPathLength() < minError){
                minError = st.getPathLength();
                minState = st;
            }
        }
        return minState;
    }

    public State swap(State state, int zeroPos, int pos){
        int[] newState = Arrays.copyOf(state.state, state.state.length);

        newState[zeroPos] = state.state[pos];
        newState[pos] = 0;

        return new State(newState, state, zeroPos,state.getMinCost()+1, checkErrorsNumber(newState, referenceState));
    }
}
