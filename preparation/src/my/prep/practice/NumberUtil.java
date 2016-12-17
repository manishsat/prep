package my.prep.practice;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import my.prep.practice.tree.BinaryTreeNode;

public class NumberUtil {
		public static void main(String[] args) {
			int number = 586390350;
			System.out.println("Factors ->" + allFactors(number));
			System.out.println("Prime Factors ->" + allPrimeFactors(number));
			int n = 530751374;
			int num=54;
			num %=1003;
		
			System.out.println("num "+num);
			
			
			Integer[][] twoDArray = {     
			        {3, 3, 11, 12, 14},
			        {16, 17, 30, 34, 35},
			        {45, 48, 49, 50, 52},
			        {56, 59, 63, 63, 65},
			        {67, 71, 72, 73, 79},
			        {80, 84, 85, 85, 88},
			        {88, 91, 92, 93, 94},
			       };
			
			Integer[][] arr = {
				  {1, 1, 2, 2, 5, 6, 6, 6, 7},
				  {9, 10, 10, 12, 12, 13, 14, 21, 21},
				  {23, 26, 26, 29, 29, 31, 32, 35, 37},
				  {38, 39, 39, 39, 41, 41, 42, 42, 43},
				  {45, 45, 46, 46, 46, 47, 48, 48, 51},
				  {51, 51, 54, 54, 54, 54, 56, 58, 59},
				  {60, 61, 61, 62, 63, 64, 65, 66, 67},
				  {67, 67, 70, 70, 71, 73, 73, 73, 74},
				  {74, 79, 79, 80, 82, 84, 84, 84, 87},
				  {89, 93, 94, 94, 97, 98, 98, 98, 98}
				};
			
			Integer[][] arr1 = {
			                    		{2, 3, 4, 6},
			                    		  {16, 19, 33, 36},
			                    		  {37, 38, 38, 41},
			                    		  {47, 47, 50, 51},
			                    		  {55, 57, 58, 62},
			                    		  {63, 65, 66, 66},
			                    		  {68, 70, 75, 77},
			                    		  {78, 84, 84, 86},
			                    		  {86, 87, 88, 92},
			                    		  {94, 95, 96, 97}
			};
			System.out.println("Is there?"+searchMatrixImproved(convert2DToList(arr1), 81));
			
		}
	
		private static ArrayList<ArrayList<Integer>> convert2DToList(Integer[][] twoDArray) {
			ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			for (Integer[] array : twoDArray) {
			    //This will add int values into the new list 
			    // and that list will added to the main list
				ArrayList l = new ArrayList(Arrays.asList(array));
			    list.add(l);      
			}
			return list;
		}
		public static int singleNumber(final List<Integer> a) {
		    int size = a.size();
		    int val = 0;
		    for(int i : a) {
		        val=val ^ i;
		       
		    }
		    return val;
		}
		
		public static List<Integer> allPrimeFactors(int number) {
			List<Integer> primeFactors = new ArrayList<Integer>();
			//first divide by 2 as mush as we can 
			int n = number;
			//O(log N)
			while(n%2 == 0) {
				primeFactors.add(2);
				n = n /2;
			}
		    int sqrt = (int)Math.sqrt(number);
			//after the above loop n has to be odd
			
			
			for(int i=3;i<=sqrt;i=i+2) {
					while(n%i==0) {
						primeFactors.add(i);
						n=n/i;
					}
			}
			
			//here n has to be 1 or prime number
			if(n>2) primeFactors.add(n);
			return primeFactors;
		}
	

	
		public static ArrayList<Integer> allFactors(int a) {
		    
		    int sqrt = (int)Math.sqrt(a);
		    ArrayList list = new ArrayList();
		    for(int num =1;num<=sqrt;num++) {
		        if(a % num == 0) {
		        	System.out.println("1-"+num);
		        	list.add(num);
		        	if(sqrt != a/num) {
		        		System.out.println("2-"+a/num);
			            list.add(a/num);
			        }
		        }
		        
		    }
		   
		    return list;
		}
		
		public static int sqrt(int a) {
		    int sq = 0;
		    for(int i=1;i<=(a/2);i++) {
		        if(i*i <= a) {
		            sq = i; 
		        }else {
		            break;
		        }
		    }
		    return sq;
		}
		
		public boolean isPowerOfTwo(int i) {
			
			if(i==0) {
				return false;
			}
			
			if(i == 1) {
				return true;
			}
			
			if(i % 2 == 1) {
				return false;
			}
			
			return isPowerOfTwo(i/2);
		}
		
		//O(2^n)
		public int fibonacci(int n) {
			if(n ==0)  return 0;
			
			if(n <= 2) return 1;
			
			return fibonacci(n-1) + fibonacci(n-2);
		}
		//median of 2 sorted arrays
		
		public static int median(int[] a,int[] b) {
			return -1;
		}
		//logic is 
		//a. Find median of a -> m1 and median of b->m2
		//if m1==m2 then m1 or m2 is the median
		//if m1<m2 then median is between m1......endA..startB.....m2
		//ifm1>m2 then median is between m2...endB....startA....m1.
		//eventually startA and endA will be adjacent to each other and same for array B 
		//then we can find out 
		private static int median(int[] a,int[] b,int startAIndex,int endAIndex,int startBIndex, int endBIndex) {
			if((endAIndex - startAIndex == 1) && (endBIndex - startBIndex == 1)) {
				return ((Math.max(a[startAIndex], b[startBIndex]) + Math.min(a[endAIndex], b[endBIndex]))/2);
			}
			int midAIndex = (startAIndex + endAIndex)/2;
			int midBIndex = (startBIndex + endBIndex)/2;
			
			int midAValue = a[midAIndex];
			int midBValue = b[midBIndex];
			if(midAValue == midBValue) return midAValue;
			
			if(midAValue < midBValue) {
				startAIndex = midAIndex;
				endBIndex = midBIndex;
			}else{
				endAIndex = midAIndex;
				startBIndex = midBIndex;
			}
			return median (a,b,startAIndex,endAIndex,startBIndex,endBIndex);
		}
		
		//Q: Write an efficient algorithm that searches for a value in an m x n matrix.
		// The naive approach is going to be O(m*n)
		//Dups? Yes dups are there.
		public static int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
			for(ArrayList<Integer> innerArray: a) {
				for(int i : innerArray) {
					if(i == b) return 1;
				}
			}
			return 0;
		}
		//for m*m matrix
		//log (n) + log (n-1) + log (n-2) + log (1)
		//so log a + log b = log (ab)
		//so time complexity O(log n!)
		public static int searchMatrixImproved(ArrayList<ArrayList<Integer>> a, int searchValue) {
			if(a == null) return 0;
		
			int lastIndex = a.get(0).size()-1;
			
			for(int i=0;i<a.size();i++) {
				ArrayList<Integer> innerArray = a.get(i);
				int lastValue = innerArray.get(lastIndex);
				if(lastValue == searchValue) {
					return 1;
				}else if(searchValue < lastValue) {
					if(binarySearchRowWise(innerArray,0,lastIndex,searchValue) == 1)
						return 1;
				}else{
					if(binarySearchColumnWise(a,i,a.size()-1,lastIndex,searchValue)==1)
						return 1;
				}
				lastIndex--;
				if(lastIndex <0) {
					break;
				}
			}
			return 0;
		}
		
		private static int binarySearchRowWise(ArrayList<Integer> arr,int startIndex,int endIndex,int value){
			if(arr == null) return 0;
			if(value<0) return 0;
			
			if(arr.get(startIndex) == value || arr.get(endIndex) == value) return 1;
			while(startIndex<=endIndex) {
				int mid = startIndex + (endIndex-startIndex)/2;
				if(arr.get(mid) == value){
					return 1;
				}else if(arr.get(mid) < value) {
					startIndex = mid + 1;
				}else{
					endIndex = mid-1;
				}
				
			}
			return 0;
		}
		
		private static int binarySearchColumnWise(ArrayList<ArrayList<Integer>> arr, int startIndex,int endIndex, int columnIndex,int value) {
			if(arr == null) return 0;
			if(startIndex < 0 || endIndex > arr.size()) return 0;
			if(arr.get(startIndex).get(columnIndex) == value || arr.get(endIndex).get(columnIndex) == value) {
				return 1;
			}
			while(startIndex<=endIndex) {
				int midIndex = startIndex + (endIndex - startIndex)/2;
				int midValue = arr.get(midIndex).get(columnIndex);
				if(midValue == value) return 1;
				else if(midValue > value) {
					endIndex = midIndex-1;
				}else{
					startIndex = midIndex +1;
				}
			}
			return 0;
			
		}
}
