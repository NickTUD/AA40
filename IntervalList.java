import java.util.Arrays;

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
        // type check and cast
        if (getClass() != obj.getClass())
            return false;
        IntervalList list = (IntervalList) obj;
        // field comparison
        return Arrays.equals(this.intervals, list.intervals);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.intervals);
    }

}
