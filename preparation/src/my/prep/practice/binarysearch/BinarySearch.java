package my.prep.practice.binarysearch;


// you can also use imports, for example:
// import java.util.*;

//12 23 34 35 40

//55 67 68 69 70

//72 75 82 89 90

public class BinarySearch {

    public static void main(String [] args) {

        int[][] matrix = new int[][] {{12, 23, 34, 35 ,40}, {55, 67, 68, 69 ,70}, {72, 75, 82 ,89 ,90}};
        Tuple t = findIndex(matrix, 104);
        if(t != null) {
            System.out.println(t.i);
            System.out.println(t.j);
        }else {
            System.out.println("element does not exists");
        }
       
    }

    public static Tuple findIndex(int[][] matrix, int value) {
        //assume matrix is sorted
        int col = matrix[0].length -1;

        for (int row =0 ; row < matrix.length; row++) {
            if(matrix[row][0] < value && matrix[row][col] > value) {
                // element is in this row
                return binarySearch(row, col, matrix, value);
            }
        } // O (row) + log N
        
        return null;
    }

    public static Tuple binarySearch(int startIndex, int endIndex, int[][] matrix, int value) {
        if(startIndex >= endIndex) {
            return null;
        }

        if(matrix[startIndex][endIndex] == value) {
            return  new Tuple(startIndex,  endIndex);
        }

        int midIndex =  startIndex + ((endIndex - startIndex)/2); //TODO
        //int midIndex = (startIndex + endIndex )/2;
        if(matrix[startIndex][midIndex] >= value) {
            return binarySearch(startIndex, midIndex, matrix, value);
        } else {
            return binarySearch(midIndex, endIndex, matrix, value);
        }
    }
}

class Tuple{
     int i;
     int j;
    public Tuple(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

