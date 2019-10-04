import java.util.LinkedList;
import java.util.ListIterator;


public class Group40SolverSorted implements Solver {

    public static int getOptimalValue(AuctionProblemInstance a, int[] assignment, int item, double epsilon) {

        int gamma = Tools.getMax(a.d);
        int revmax = 0;

        LinkedList<AllocationTuple> oldset = new LinkedList<AllocationTuple>();
        oldset.add(new AllocationTuple(a,epsilon));

        for (int j = 0; j < a.k; j++) {

            System.out.println("Starting with set " + j);
            System.out.println("Starting with size " + oldset.size());
            LinkedList<AllocationTuple> newset = new LinkedList<AllocationTuple>();

            ListIterator<AllocationTuple> itoldj = oldset.listIterator(0);
            while(itoldj.hasNext()){

                AllocationTuple tup = itoldj.next();

                for (int i = 0; i < a.n; i++) {

                    AllocationTuple newTup = tup.assign(i, j, gamma, epsilon);

                    ListIterator<AllocationTuple> itnewj = newset.listIterator();
                }
            }

            oldset = newset;
        }

        return revmax;
    }

    @Override
    public AuctionProblemInstance.Solution solve(AuctionProblemInstance a, double epsilon) {
        int[] assignment = new int[a.k];
        return new AuctionProblemInstance.Solution(getOptimalValue(a, assignment, 0, epsilon), epsilon);
    }

    @Override
    public String getName() {
        return "Group 40 Solver";
    }
}