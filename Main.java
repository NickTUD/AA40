import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            final long startTime = System.currentTimeMillis();
            System.out.println(new Group40SolverUnsorted().solve(AuctionProblemInstance.IO.read(args[0]), Double.parseDouble(args[1])).value);
            final long endTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
