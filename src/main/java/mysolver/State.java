package mysolver;

import java.util.Arrays;

public class State {
    public int[] state;

    private int position;
    private int parentValue;
    private State parent;
    private int minCost;    //g(x)
    private int heuristicApprox;    //h(x)
    private Integer pathLength;     //f(x)

    public State(int[] state, State parent, int cost, int heuristic){
        this.state = state;
        this.minCost = cost;
        this.heuristicApprox = heuristic;
        this.parent = parent;
        setPathLength();
    }

    public State(int[] state, State parent, int position, int cost, int heuristic){
        this.state = state;
        this.parent = parent;
        this.position = position;
        this.minCost = cost;
        this.heuristicApprox = heuristic;
        setPathLength();
        parentValue = state[position];
    }

    public State(State state){
        this.state = Arrays.copyOf(state.state, state.state.length);
        this.parent = state.getParent();
        this.position = state.getPosition();
        this.minCost = state.getMinCost();
        this.heuristicApprox = state.getHeuristicApprox();
        this.pathLength = state.getPathLength();
        this.parentValue = state.getParentValue();
    }

    public int getMinCost(){
        return minCost;
    }

    public int getPathLength(){
        return pathLength;
    }

    public int getHeuristicApprox(){
        return heuristicApprox;
    }

    public int getPosition(){
        return position;
    }

    public State getParent(){
        return parent;
    }

    public int getParentValue(){
        return parentValue;
    }

    public int compareTo(State s){
        return pathLength.compareTo(s.getPathLength());
    }

    @Override
    public  boolean equals(Object o){
        State state = (State) o;
        return
                Arrays.equals(this.state, state.state) &&
                this.getParent() == state.getParent() &&
                this.getPathLength() == state.getPathLength() &&
                this.getPosition() == state.getPosition();
    }

    private void setPathLength(){
        this.pathLength = this.minCost + this.heuristicApprox;
    }
}
