import java.util.Arrays;

public class AllocationTuple {

    // AuctionProblemInstance stored to reference the number of bidders
    private AuctionProblemInstance a;

    // Revenue from each bidder
    private int[] v;

    //Keeps track in which bins v values fall
    private IntervalList bins;

    //Keeps track of total revenue
    private int revenue;

    //Keeps track of the epsilon value for updating the bins
    private double epsilon;


    public AllocationTuple(AuctionProblemInstance a, double epsilon) {
        this(a, new int[a.n], new IntervalList(new int[a.n]), 0, epsilon);
    }

    private AllocationTuple(AuctionProblemInstance a, int[] v, IntervalList bins, int revenue, double epsilon) {
        this.a = a;
        this.v = v;
        this.bins = bins;
        this.revenue = revenue;
        this.epsilon = epsilon;
    }

    private AllocationTuple(AllocationTuple t) {
        this(t.a, t.v.clone(), new IntervalList(t.bins.getIntervals().clone()), t.revenue, t.epsilon);
    }

    /**
     * Assigns item j to bidder i by copying the old allocation and updating the fields  with
     * the new assignment.
     *
     * @param i Bidder i
     * @param j Bidder j
     * @return A new AllocationTuple where item j is assigned to bidder i.
     */
    public AllocationTuple assign(int i, int j, int gamma, double epsilon) {

        AllocationTuple newTup = new AllocationTuple(this);
        int newrev = Math.min(a.d[i], v[i] + a.b[i][j]);

        newTup.revenue = revenue + newrev - v[i];
        newTup.bins.getIntervals()[i] = (int) ((newrev * a.k) / (gamma * epsilon));
        newTup.v[i] = newrev;

        return newTup;
    }

    public int getRevenue(){
        return this.revenue;
    }

    public IntervalList getIntervals(){
        return this.bins;
    }

}
