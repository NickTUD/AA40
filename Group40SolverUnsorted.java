import java.util.LinkedList;
import java.util.ListIterator;


public class Group40SolverUnsorted implements Solver {

    public static int getOptimalValue(AuctionProblemInstance a, int[] assignment, int item, double epsilon) {

        int gamma = Tools.getMax(a.d);
        int revmax = 0;

        LinkedList<AllocationTuple> oldset = new LinkedList<AllocationTuple>();
        oldset.add(new AllocationTuple(a,epsilon));

        for (int j = 0; j < a.k; j++) {

            LinkedList<AllocationTuple> newset = new LinkedList<AllocationTuple>();

            ListIterator<AllocationTuple> it = oldset.listIterator(0);
            while(it.hasNext()){

                AllocationTuple tup = it.next();

                for (int i = 0; i < a.n; i++) {

                    AllocationTuple newTup = tup.assign(i, j, gamma, epsilon);
                    int ind = newset.indexOf(newTup);

                    if(ind == -1){
                        newset.add(newTup);
                        revmax = Math.max(revmax, newTup.getRevenue());
                    } else {
                        AllocationTuple exisTup = newset.get(ind);
                        if(newTup.getRevenue() >= exisTup.getRevenue()) {
                            newset.set(ind, newTup);
                            revmax = Math.max(revmax, newTup.getRevenue());
                        }
                    }
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