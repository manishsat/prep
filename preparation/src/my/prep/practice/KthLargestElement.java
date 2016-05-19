package my.prep.practice;

public class KthLargestElement {

	//O(kn)
	public static int largestElement(int[] a,int k) {
		if(a.length == 0) {
			return -1;
		}
		int largest = 0;
		int largestPosition;
		for(int i=0;i<k;i++) {
			largest = a[0];
			largestPosition = 0;
			for(int j=1;j <= (a.length-i-1);j++) {
				if(a[j]>largest) {
					largest = a[j];
					largestPosition = j;
				}
			}
			int temp = a[a.length-i-1];
			 a[a.length-i-1] = largest;
			 a[largestPosition] =temp;
		}
		return largest;
	}
	/*
	 * Quick select is a part of Quick sort, where we select a pivot element and 
	 * bring all smaller number to its left and bigger numbers to the right. 
	 * 
	 */
	public static int largestQuickSelect(int a[],int left,int right,int k) {
		
		if(k >0 && k <=right -left + 1) {
			//right -left is going to gives use the size/number of elements in the range
			//And if K is more then the range then obviously we can not find kth largest element.
			int pos = partition(a,left,right);
			int noofEle_RightSide = right - pos;
			if(noofEle_RightSide == k-1) {
				//k-1 is because pos could point to the 1st element wose index is 0
				//so if array is asc sorted and K = lenght -1 then kth largest element is the first one.
				return a[pos];
			}
			
			if(noofEle_RightSide <= k-1) {
				//mean kth largest is on the left side of pos
				return largestQuickSelect(a,left,right-1,k-1-noofEle_RightSide);
			}else{
				return largestQuickSelect(a, pos +1,right,k);
			}//otherwise kth on right side
			
		}
		return Integer.MAX_VALUE;
	}
	
	private static int partition(int a[], int left,int right) {
		int pivot = a[right];
		int i = left;
		for(int j= left;j<right;j++) {
			if(a[j] <= pivot) {
				swapArrayElement(a,i,j);
				i++;
			}
				
		}
		swapArrayElement(a, i, right);
		return i; //  This is the index where pivot will go and all the element will be smaller then pivot
	}
	
	private static void swapArrayElement(int a[],int i,int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;	
	}
	
	public static void main(String[] args) {
		int arr[] = {12, 3, 5, 7, 4, 19, 26};
		System.out.println("3rd larget element = "+KthLargestElement.largestElement(arr, 3)) ;
		System.out.println("3rd larget element = "+KthLargestElement.largestQuickSelect(arr,0,arr.length-1, 3)) ;
		
		int arr1[] = {12, 3, 5, 7, 19};
		System.out.println("3rd larget element = "+KthLargestElement.largestElement(arr1, 4)) ;
		System.out.println("3rd larget element = "+KthLargestElement.largestQuickSelect(arr1,0,arr1.length-1, 4)) ;
		
		
		int arr2[] = {12, 3, 5, 7, 4, 26, 19};
		System.out.println("3rd larget element = "+KthLargestElement.largestElement(arr2, 1)) ;
		System.out.println("3rd larget element = "+KthLargestElement.largestQuickSelect(arr2,0,arr2.length-1, 1)) ;
		
		int arr3[] = {1, 3, 5, 7, 4, 20, 25};
		System.out.println("3rd larget element = "+KthLargestElement.largestElement(arr3, 7)) ;
		System.out.println("3rd larget element = "+KthLargestElement.largestQuickSelect(arr3,0,arr3.length-1, 7)) ;
		
	}
}
