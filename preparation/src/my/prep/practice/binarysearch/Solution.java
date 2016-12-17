	package my.prep.practice.binarysearch;

public class Solution {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
//		System.out.println("rotation->"+howManyRotation(arr));
//		int[] rotateArray = {180, 181, 182, 183, 184, 187, 188, 189, 191, 192, 193, 194, 195, 196, 201, 202, 203, 204, 3, 4, 5, 6, 7, 8, 9, 10, 14, 16, 17, 18, 19, 23, 26, 27, 28, 29, 32, 33, 36, 37, 38, 39, 41, 42, 43, 45, 48, 51, 52, 53, 54, 56, 62, 63, 64, 67, 69, 72, 73, 75, 77, 78, 79, 83, 85, 87, 90, 91, 92, 93, 96, 98, 99, 101, 102, 104, 105, 106, 107, 108, 109, 111, 113, 115, 116, 118, 119, 120, 122, 123, 124, 126, 127, 129, 130, 135, 137, 138, 139, 143, 144, 
//				145, 147, 149, 152, 155, 156, 160, 162, 163, 164, 166, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177};
//		
//		System.out.println("rotation->"+searchInSortedArray(rotateArray,42));
		int[] arr1={1,2,3,4,4,4,5,6,7,8,9};
		System.out.println("dup count->"+duplicateCountInSortedArray(arr1, 4));
		int[] A = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 
				4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8,
				8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		int[] B = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
				4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 
				5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 
				6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 
				6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 
				8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 
				9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 
				10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };
		
		System.out.println("dup count->"+duplicateCountInSortedArray(B, 3));		
		
	}
	//calculate x^n
			public static int power(int x, int n) {
				
				//base case
				if(n==0) {
					return 1;
				}
				int subpower = power(x,n/2);
				
				if(n%2 == 0) {
					//even case
					return subpower * subpower;
				}else{
					return x * subpower * subpower;
				}
			}
			//Duplicates?
			//Negative numbers?
			//how big is the array?
			public static int howManyRotation(int[] arr) {
				int leftIndex=0;
				int rightIndex = arr.length-1;
				//when no rotation.
				if(arr[leftIndex] < arr[rightIndex]) {
					return 0;
				}
				
				while(rightIndex -leftIndex > 1) {
					int mid = leftIndex + ((rightIndex-leftIndex)/2);
					if(arr[leftIndex] < arr[mid]) {
						leftIndex = mid;
					}else{
						rightIndex = mid;
					}
				}
				return leftIndex + 1;
			}
			//Suppose a sorted array is rotated at some pivot unknown to you beforehand.
			//Duplicates?
			//You are given a target value to search. If found in the array, return its index, otherwise return -1.
			public static int searchInSortedArray(int[] arr,int value) {
				if(arr == null) return -1;
				int leftIndex = 0;
				int rightIndex = arr.length -1;
				
				while(rightIndex-leftIndex >1) {
					if(arr[leftIndex] == value) {
						return leftIndex;
					}
					
					if(arr[rightIndex] == value) {
						return rightIndex;
					}
					int mid = leftIndex + ((rightIndex-leftIndex)/2);
					if(arr[mid] == value) {
						return mid;
					}
					
					if(value < arr[mid]) {
						
						//now check the pivot point
						if(arr[leftIndex] > arr[mid]) {
							//pivot is in this range and the min element of the set exists here
							rightIndex = mid;
						}else if(value < arr[leftIndex]){
							//mean its on the other side
							leftIndex = mid;
						}else{
							rightIndex = mid;
						}
						
						
					}else{
						//now check the pivot
						if(arr[rightIndex] < arr[mid]) {
							leftIndex = mid;
						}else if(value < arr[rightIndex]) {
							leftIndex = mid;
						}else{
							rightIndex = mid;
						}
					}
					
				}
				return -1;
				
			}
			
			//find the duplicate entries in the sorted array and compute the count.
			
			public static int duplicateCountInSortedArray(int arr[],int value) {
				
				int leftIndex = 0;
				int rightIndex = arr.length - 1;
				
				while(rightIndex-leftIndex > 1) {
					int mid = leftIndex + ((rightIndex-leftIndex)/2);
					if(value < arr[mid]) {
						rightIndex = mid;
					}else{
						leftIndex = mid; //in case of duplicate move leftIndex towards right and it will point to the maxIndex of the search value
					}
					
				}
				
				//not found 
				if(!(arr[leftIndex]==value ||arr[rightIndex]==value)) {
					return -1;
				}
				
				int maxIndex = leftIndex;
				
				leftIndex = 0;
				rightIndex = maxIndex;
				
				while(rightIndex-leftIndex > 1) {
					int mid = leftIndex + ((rightIndex-leftIndex)/2);
					if(value > arr[mid]) {
						leftIndex = mid;
					}else{
						rightIndex = mid;
					}
					
				}
				int minIndex = rightIndex;
				System.out.println("minIndex="+minIndex + " maxIndex="+maxIndex);
				return (maxIndex - minIndex)+1;
			}
}
