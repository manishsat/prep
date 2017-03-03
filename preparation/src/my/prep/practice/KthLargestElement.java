package my.prep.practice;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestElement {
	
	public static void main(String[] args) {
		int arr[] = {12, 3, 5, 7, 4, 19, 26};
		System.out.println("3rd larget element = "+KthLargestElement.largestElement(arr, 3)) ;
		System.out.println("3rd larget element = "+KthLargestElement.largestQuickSelect(arr,0,arr.length-1, 3)) ;
		System.out.println("3rd larget element (min Heap) = "+KthLargestElement.findKthLargest(arr,3));
		int arr1[] = {12, 3, 5, 7, 19};
		System.out.println("3rd larget element = "+KthLargestElement.largestElement(arr1, 4)) ;
		System.out.println("3rd larget element = "+KthLargestElement.largestQuickSelect(arr1,0,arr1.length-1, 4)) ;
		System.out.println("3rd larget element (min Heap) = "+KthLargestElement.findKthLargest(arr1,4));
		
		int arr2[] = {12, 3, 5, 7, 4, 26, 19};
		System.out.println("3rd larget element = "+KthLargestElement.largestElement(arr2, 1)) ;
		System.out.println("3rd larget element = "+KthLargestElement.largestQuickSelect(arr2,0,arr2.length-1, 1)) ;
		System.out.println("3rd larget element (min Heap) = "+KthLargestElement.findKthLargest(arr2,1));
		
		int arr3[] = {5, 3, 7, 1,  4, 20, 25};
		System.out.println("3rd larget element = "+KthLargestElement.largestElement(arr3, 7)) ;
		System.out.println("3rd larget element = "+KthLargestElement.largestQuickSelect(arr3,0,arr3.length-1, 7)) ;
		System.out.println("3rd larget element (min Heap) = "+KthLargestElement.findKthLargest(arr3,3));
		System.out.println("3rd smallest element (min Heap) = "+KthLargestElement.findKthSmallest(arr3,3));
	}

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
	 * Worst case O (n^2) Avg case is O(n)
	 */
	public static int largestQuickSelect(int a[],int left,int right,int k) {
		
		if(k >0 && k <=right -left + 1) {
			//right -left is going to gives use the size/number of elements in the range
			//And if K is more than the range. Then obviously we can not find kth largest element.
			int pos = partition(a,left,right);
			
			int noofEle_RightSide = right - pos;
			
			if(noofEle_RightSide == k-1) {
				//k-1 is because partition index could be at 0  and right -0 would be right on above equation
				//pos could point to the 1st element whose index is 0
				//so if array is asc sorted and K = lenght -1 then kth largest element is the first one.
				return a[pos];
			}
			
			if(noofEle_RightSide <= k-1) {
				//mean kth largest is on the left side of pos
				// and we need to reduce the K by number of elemen which are on right side
				// let say we need to find 3rd larget and we got 2 elements on right side means now we need to find 1st largent on left side 
				//which will be 3rd largest in the array.
				return largestQuickSelect(a,left,pos-1,k-1-noofEle_RightSide);
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
	
	// Using minHeap
	//To construct min heap we need to go through the whole array and for every element we need to insert into heap 
	//which may require log(k) since maximum total k elements are in the heap.
	//O(nlogk)
	public static int findKthLargest(int[] nums, int k) {
	    PriorityQueue<Integer> q = new PriorityQueue<Integer>(k);
	    for(int i: nums){
	        q.offer(i);
	 
	        if(q.size()>k){
	            q.poll();
	        }
	    }
	 
	    return q.peek();
	}
	
	public static int findKthSmallest(int[] nums, int k) {
		 PriorityQueue<Integer> q = new PriorityQueue<Integer>(k,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if(o1 > o2) {
					return -1;
				}else if (o1 < o2) {
					return 1;
				}else {
					return 0;
				}
					
			}
		}) ;
		 
		 for(int i:nums) {
			 if(q.offer(i)) {
				 if(q.size() > k) {
					 q.poll();
				 }
			 }else{
				 throw new RuntimeException("queue is full");
			 }
		 }
		 
		 return q.peek();
	}
	
	
	
	private static void swapArrayElement(int a[],int i,int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;	
	}
	
	
}
