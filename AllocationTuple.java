/**
 * Class which represents the tuple (v_1,a_1,v_2,a_2,...,v_n,a_n,t)
 */
public class AllocationTuple {

    // AuctionProblemInstance stored to retrieve the number of bidders, biddings, budgets etc.
    private AuctionProblemInstance a;

    // Revenue from each bidder
    private int[] v;

    //Keeps track in which bins v values fall
    private IntervalList bins;

    //Keeps track of total revenue
    private int revenue;

    //Keeps track of the epsilon value for updating the bins
    private double epsilon;

    /**
     * Creates a new tuple in which no items are allocated to anyone.
     * @param a Instance containing the max amount of bidders, biddings, budgets etc.
     * @param epsilon Epsilon value used for the approximation
     */
    public AllocationTuple(AuctionProblemInstance a, double epsilon) {
        this(a, new int[a.n], new IntervalList(new int[a.n]), 0, epsilon);
    }

    /**
     * Private constructor which only gets called when new items are assigned.
     * @param a Instance containing the max amount of bidders, biddings, budgets etc.
     * @param v
     * @param bins
     * @param revenue
     * @param epsilon
     */
    private AllocationTuple(AuctionProblemInstance a, int[] v, IntervalList bins, int revenue, double epsilon) {
        this.a = a;
        this.v = v;
        this.bins = bins;
        this.revenue = revenue;
        this.epsilon = epsilon;
    }

    /**
     * Copy constructor, makes a deep copy of t.
     * @param t AllocationTuple to make a copy of
     */
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

        //Calculate the new revenue for bidder i
        int newBidderRev = Math.min(a.d[i], v[i] + a.b[i][j]);

        //Update the total revenue
        newTup.revenue = revenue + newBidderRev - v[i];

        //Update the bin for v_i
        newTup.bins.getIntervals()[i] = (int) ((newBidderRev * a.k) / (gamma * epsilon));

        //Update v_i itself
        newTup.v[i] = newBidderRev;

        return newTup;
    }

    public int getRevenue(){
        return this.revenue;
    }

    public IntervalList getIntervals(){
        return this.bins;
    }

}
