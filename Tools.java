public class Tools {
    public static int powi(int base, int exp) {
        return (int) Math.pow(base, exp);
    }

    /**
     * Assuming non empty lists (valid auctions where there is at least 1 bidder), returns the maximum of the list
     * @param arr List of integer values.
     * @return Maximum value of the list
     */
    public static int getMax(int[] arr){
        int maxValue = arr[0];
        for(int i=1;i < arr.length;i++){
            if(arr[i] > maxValue){
                maxValue = arr[i];
            }
        }
        return maxValue;
    }

    /**
     * Assuming non empty lists (valid auctions where there is at least 1 bidder), returns the sum of the list
     * @param arr List of integer values.
     * @return Sum of the list
     */
    public static int getSum(int[] arr){
        int sum = arr[0];
        for(int i=1;i < arr.length;i++){
            sum += arr[i];
        }
        return sum;
    }
    
    public static int getAverage(int[] arr){
        return getSum(arr) / arr.length;
    }
}
