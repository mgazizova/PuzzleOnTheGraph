package mysolver;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        MySolver solver = new MySolver();
        int [] initState = new int[]{1, 2, 3, 4, 0, 5, 6, 7};
        System.out.println(Arrays.toString(solver.resolve(initState)));
    }
}
