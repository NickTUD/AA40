import java.util.*;

// O(n * 4^k) exact algorithm for auctions with budget constraints
public class Group40ExactSolver implements Solver {

    public static int getOptimalValue(AuctionProblemInstance a, int[] assignment, int item) {

        int amountOfItemSubsets = (int) Math.pow(2, a.k);

        //ArrayList containing the valid combinations of subsets of the items.
        //Main choice here is to have a list which has O(1) additions.
        ArrayList<AllocationCombi> validCombi = new ArrayList<AllocationCombi>();

        //Generates all valid subset divisions such that no item is shared amongst buyers.
        //The first item (index 0 in the bidding matrix) is the least significant bit.
        //Example:
        //Buyer 1 buys the first and third item = 101 (binary) = 5 decimal
        //Buyer 2 buys the first and second item = 011 (binary) = 3 decimal
        //This is not a valid division since both buy the first item.
        //These valid combinations are used to combine the optimal profit for the first n bidders
        //with the biddings of bidder n+1 to get the optimal profit for the first n+1 bidders.
        //Thus we consider every non identical combination.
        for(int i = 0; i < amountOfItemSubsets; i++) {
            for(int j = i+1; j < amountOfItemSubsets; j++) {

                //If the item subsets share no item, e.g. an AND between their binary representations is equal to 0,
                //we add them to our list.
                if((i & j) == 0){
                    validCombi.add(new AllocationCombi(i,j));
                }
            }
        }

        //Note that the bidding matrix of the input instance only stores biddings per individual item
        //and we're interested in subsets of all items.
        //Since we don't want to calculate their biddings for such a subset multiple times,
        //we calculate them per bidder for each subset once and store it in this 2d array for O(1) access.
        int[][] subsetPricesByBidder = new int[a.n][amountOfItemSubsets];

        for(int bidder = 0; bidder < a.n; bidder++){
            for(int exp = 0; exp < a.k; exp++){
                int pow2 = (int) Math.pow(2, exp);
                subsetPricesByBidder[bidder][pow2] = Math.min(a.b[bidder][exp], a.d[bidder]);
                for(int rest = 1; rest < pow2; rest++){
                    subsetPricesByBidder[bidder][pow2+rest] = Math.min(subsetPricesByBidder[bidder][pow2] + subsetPricesByBidder[bidder][rest], a.d[bidder]);
                }
            }
        }

        int[][] profitFirstNBidders = new int[(a.n + 1)][amountOfItemSubsets];


        for(int bidder = 0; bidder < a.n; bidder++){
            for (AllocationCombi combination : validCombi) {
                int profit1 = profitFirstNBidders[bidder][combination.subset1] + subsetPricesByBidder[bidder][combination.subset2];
                int profit2 = profitFirstNBidders[bidder][combination.subset2] + subsetPricesByBidder[bidder][combination.subset1];
                profitFirstNBidders[bidder+1][combination.subset_combi] = Math.max(Math.max(profit1,profit2), profitFirstNBidders[bidder+1][combination.subset_combi]);
            }
        }

//        System.out.println(Arrays.deepToString(profitFirstNBidders));

        return profitFirstNBidders[a.n][amountOfItemSubsets - 1];
    }

    @Override
    public AuctionProblemInstance.Solution solve(AuctionProblemInstance a, double epsilon) {
        int[] assignment = new int[a.k];
        return new AuctionProblemInstance.Solution(getOptimalValue(a, assignment, 0), epsilon);
    }

    @Override
    public String getName() {
        return "Group 40 Solver";
    }
}