import java.util.Arrays;

/**
 * Represents the list of intervals the revenue of each bidder v_i falls into.
 * Mainly, a seperate class is made for this as we want to use a hash table to lookup whether or not
 * the new set S_j contains an item with the same intervals already. As we're using a hash table, we need to therefore
 * adapt the hashcode function to not use references but use the actual interval values. On top, this means that we have
 * to change the equals method.
 */
public class IntervalList {

    private int[] intervals;

    public IntervalList(int[] intervals){

        this.intervals = intervals;
    }

    public int[] getIntervals(){ return this.intervals; }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        IntervalList list = (IntervalList) obj;

        // Check whether intervals are equal
        return Arrays.equals(this.intervals, list.intervals);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.intervals);
    }

}
