import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {

//            int[] n_max = {2,3,4};
//
//            for(int n : n_max){
//
//                for(int i = 0; i < 50; i++) {
//
//                    String filename = args[0] + n + "_k_25_dmax_800_" + (i + 1) + ".txt";
//
//                    int[] times = new int[10];
//
//                    for (int j = 0; j < 10; j++) {
//
//                        long startTime = System.currentTimeMillis();
//
//                        new Group40Solver().solve(AuctionProblemInstance.IO.read(filename), Double.parseDouble(args[1]));
//
//                        long endTime = System.currentTimeMillis();
//
//                        times[j] = (int) (endTime - startTime);
//                    }
//
//                    System.out.println(n + " " + Tools.getAverage(times));
//                }
//            }
//            System.out.println(new Group40ApproxSolver().solve(AuctionProblemInstance.IO.read(args[0]), Double.parseDouble(args[1])).value);
//            System.out.println(new BruteForceSolver().solve(AuctionProblemInstance.IO.read(args[0]), Double.parseDouble(args[1])).value);
              System.out.println(new Group40ExactSolver().solve(AuctionProblemInstance.IO.read(args[0]), 0).value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
