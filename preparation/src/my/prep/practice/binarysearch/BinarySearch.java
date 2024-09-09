package my.prep.practice.binarysearch;


/* You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.
*/

public class BinarySearch {

    public static void main(String [] args) {
        int[][] matrix = new int[][] {{12, 23, 34, 35 ,40}, {55, 67, 68, 69 ,70}, {72, 75, 82 ,89 ,90}};
        int target = 40;
        boolean = result = searchMatrix(matrix, target);
        Syste,.out.ptintln(result);   
    }

   public boolean searchMatrix(int[][] matrix, int target) {
        // find a row
        int rowIndex = findRow(0, matrix.length - 1, matrix[0].length - 1, matrix, target);
        if(rowIndex == -1) {
            return false;
        }

        return findElement(rowIndex, 0,  matrix[0].length - 1, matrix, target);
    }

    public boolean findElement(int rowIndex, int startIndex, int endIndex, int[][] matrix, int target) 
    {
        if(startIndex > endIndex) {
            return false;
        }

        int midIndex = startIndex + (endIndex - startIndex)/2;

        if(matrix[rowIndex][midIndex] == target) {
            return true;
        }

        if(matrix[rowIndex][midIndex] < target) {
            return findElement(rowIndex, midIndex + 1, endIndex, matrix, target);
        } else {
            return findElement(rowIndex, startIndex, midIndex - 1, matrix, target);
        }

    }
    public int findRow(int firstRow, int lastRow, int lastColIndex, int[][] matrix, int target) {
        
        if(firstRow > lastRow) {
            return -1;
        }

        int midRow = firstRow + (lastRow - firstRow)/2;
        

        if(matrix[midRow][0]<= target && matrix[midRow][lastColIndex] >= target) {
            // row found
            return midRow;
        }

        if(matrix[midRow][0] > target) {
            return findRow(firstRow, midRow - 1, lastColIndex, matrix, target);
        } else {
            return findRow(midRow + 1, lastRow , lastColIndex, matrix, target);
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

