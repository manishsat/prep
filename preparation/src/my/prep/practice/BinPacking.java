package my.prep.practice;
import java.util.*;

public class BinPacking {
    /*
    Given n items of different weights and bins each of capacity c, assign each item to a bin such that number of total used bins is minimized. 
    It may be assumed that all items have weights smaller than bin capacity.
    */
    public static void main(String[] args) {
        int weight[] = { 2, 5, 4, 7, 1, 3, 8 };
        
        int c = 10;
        BinPacking bp = new BinPacking();
        System.out.println("Number of bins required in Next Fit : "
                    + bp.getMinBinCountNextFit(weight, c));
        
        System.out.println("Number of bins required in First Fit : "
                    + bp.getMinBinCountFirstFit(weight, c));
        
        System.out.println("Number of bins required in Best Fit : "
                    + bp.getMinBinCountBestFit(weight, c));
        
        System.out.println("Number of bins required in Best Fit : "
                    + bp.getMinBinFirstFitDecreasing(weight, c));
        
        System.out.println("Number of bins required in Best Fit : "
                    + bp.getMinBinBestFitDecreasing(weight, c));
        
    }
    
    /* Fit in the bin and if not able to fit use next bin*/
    public int getMinBinCountNextFit(int[] weights, int capacity) {
        
        int minBins = 0;
        //assign remaining bin capacity equals to bin capacity
        int remainingBinCapacity = capacity;
        for(int i=0; i < weights.length; i++) {
            if(weights[i] > remainingBinCapacity) {
                minBins += 1; // use new bin so increase bin count and 
                remainingBinCapacity = capacity - weights[i];
            }else{
                // dont increase bin count we can stil use the same bin
                remainingBinCapacity = remainingBinCapacity - weights[i];
            }
        }
        
        return minBins;
    }
    /*
        When processing the next item, scan the previous bins in order and place the item in the first bin that fits. Start a new bin only if it does not fit in any of the existing bins. 
        
        The below implementation of First Fit requires O(n2) time, but First Fit can be implemented in O(n Log n) time using Self-Balancing Binary Search Trees
    */
    public int getMinBinCountFirstFit(int[] weights, int capacity) {
        
        int[] prevBinCapacity = new int[weights.length];
        int minBins = 0;
        for(int i =0 ; i < weights.length; i++) {
            //iterate over all existing bins and try to find out spot
            boolean binFound = false;
            for(int j=0; j < minBins; j++) {
                if(prevBinCapacity[j] >= weights[i]) {
                    //ok previous bin has capacity to accomodate 
                    prevBinCapacity[j] -= weights[i];
                    binFound = true;
                    break;
                }
            }
            
            if(!binFound) {
                // we iterate over all the bins
                minBins += 1;
                prevBinCapacity[i] = capacity - weights[i];
            }
        }
        return minBins;
    }
    /*
    The idea is to places the next item in the *tightest* spot. That is, put it in the bin so that the smallest empty space is left. 
    */
    public int getMinBinCountBestFit(int[] weights, int capacity) {
        
        int[] prevBinCapacity = new int[weights.length];
        int minBins = 0;
        
        for(int i =0 ; i < weights.length; i++) {
            //iterate over all existing bins and try to find out spot which bin will leave min un used space
            int minCapacityleft = capacity;
            int prevBinIndex = 0;
            for(int j=0 ; j < minBins; j++) {
                if(prevBinCapacity[j] >= weights[i] && ((prevBinCapacity[j] -  weights[i]) < minCapacityleft )) {
                    //ok previous bin has capacity to accomodate 
                   minCapacityleft = prevBinCapacity[j] - weights[i];
                   prevBinIndex = j; 
                }
            }
            
            if(minCapacityleft == capacity) {
                //we haven' find any exising bin which can accomodate current weight
                minBins += 1;
                prevBinCapacity[minBins] = capacity - weights[i];
            }else{
                //use the exiting best fit bin
                prevBinCapacity[prevBinIndex] -= weights[i];
            }
        }
        return minBins;
    }
    /*
    A trouble with online algorithms is that packing large items is difficult, especially 
    if they occur late in the sequence. We can circumvent this by *sorting* the input sequence, and placing the large items first. With sorting, 
    we get First Fit Decreasing and Best Fit Decreasing, as offline analogues of online First Fit and Best Fit. 
    */
    public int getMinBinFirstFitDecreasing(int[] weights, int capacity) {
        // First sort all weights in decreasing order
        int[] sortedWeights = Arrays.stream(weights).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        return this.getMinBinCountFirstFit(sortedWeights, capacity);
    }
    
    public int getMinBinBestFitDecreasing(int[] weights, int capacity) {
        // First sort all weights in decreasing order
        int[] sortedWeights = Arrays.stream(weights).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        return this.getMinBinCountBestFit(sortedWeights, capacity);
    }
}
