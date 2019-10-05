import java.util.*;


public class Group40Solver implements Solver {

    public static int getOptimalValue(AuctionProblemInstance a, int[] assignment, int item, double epsilon) {

        // Calculate d_max
        int gamma = Tools.getMax(a.d);

        // Variable which keeps track of the max revenue
        int revmax = 0;

        // Queue which is equal to S_{j-1} according to the algorithm
        Deque<AllocationTuple> queue = new ArrayDeque<>();

        // Initialize S_0, the initial set. S_0 only contains one item, the allocation where no one has been allocated
        // an item yet.
        queue.add(new AllocationTuple(a,epsilon));

        // For every item j
        for (int j = 0; j < a.k; j++) {

            // Hashtable which represents the set S_j according to the algorithm.
            // Revenues which belong to the same intervals are hashed to the same bucket.
            // This means that hashtable contains only one entry for every interval combination.
            Hashtable<IntervalList, AllocationTuple> map = new Hashtable<>();

            // For every allocation in set S_{j-1}
            while(!queue.isEmpty()) {

                AllocationTuple tup = queue.pop();

                // For every bidder i
                for (int i = 0; i < a.n; i++) {

                    // Allocate item j to bidder i
                    AllocationTuple newTup = tup.assign(i, j, gamma, epsilon);

                    // Check if there exists a tuple already in S_j where the intervals are the same.
                    AllocationTuple oldTup = map.get(newTup.getIntervals());

                    if (oldTup == null) {

                        //If it doesn't, we can store this tuple.
                        map.put(newTup.getIntervals(), newTup);
                    } else {

                        //If there does exist a tuple with the same interval, we only want to keep the new one
                        //if it's at least better in terms of total revenue.
                        if (newTup.getRevenue() >= oldTup.getRevenue()) {
                            map.put(newTup.getIntervals(), newTup);
                        }
                    }
                }
            }

            // Now we need to update our queue variable for the next iteration so that S_j in this iteration is equal
            // to S_{j-1} in the next iteration. Also update the maximum revenue.
            for(IntervalList list : map.keySet()){
                AllocationTuple tup = map.get(list);
                queue.add(tup);
                revmax = Math.max(revmax, tup.getRevenue());
            }
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