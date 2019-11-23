public class AllocationCombi {

    public final int subset1;
    public final int subset2;
    public final int subset_combi;

    public AllocationCombi(int subset1, int subset2){

        this.subset1 = subset1;
        this.subset2 = subset2;
        this.subset_combi = subset1+subset2;
    }

    @Override
    public String toString() {
        return "Pair(" + subset1 + "," + subset2 + ")";
    }

}
