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
			
			System.out.println("GCD(3,12)=" + gcd(3,11));
			
			int a[] = {1,2,3,4,5,6,7,8,9,10,11,12};
			//rotateArray(a,3);
			int b[] = {1,2,3,4,5,6,7,8,9,10,11,12};
			//rotateArrayFast(b, 3);
			int c[] = {29261,80254,86934,3704,76338,96698,47885,88475,65211,65976,75238,58566,28684,20348,45383,58620,48360,99801,51885,82661,83066,14311,24803,99267,21541,93195,21194,
					20775,64817,42323,7640,10429,38928,94573,30484,15265,7622,78368,3739,72833,60696,95328,31398,5731,15676,93132,64351,64035,9284,32587,46695,92349,46897,87850,7968,84789,
					81044,45513,5563,62212,87836,13202,88993,26763,24127,19476,42028,31748,14196,62118,4580,91243,73798,52329,96973,89473,61812,77675,69859,71095,10261,32905,79796,57157,20754,
					87763,41945,1798,33275,63859,80361,37462,93413,69353,64225,17539,5181,22604,49286,19376,1073,70218,26970,74870,38898,23942,80694,710,1617,50552,88156,11877,83457,67951,
					85386,4210,55713,43682,22359,5340,23893,2720,59153,17305,88424,23377,51195,93604,62332,480,29331,79757,87049,56300,54626,25947,96594,35320,26656,98210,2223,31163,26438,
					85679,99114,28175,89889,71178,88209,12247,76517,12101,31318,35670,45757,19742,75398,96951,29697,54082,13782,75380,33838,831,31679,4815,26777,28272,56486,69784,42833,58709,
					946,85623,44387,59,13797,50627,87589,2005,62874,80457,14105,94191,32478,59861,30284,7876,73163,59981,78309,86945,35360,28498,87775,83390,49664,30903,28014,6150,686,70846,
					81210,17983,56468,41948,34394,86617,92575,21982,88621,71800,2438,19078,82342,34916,95290,12626,59143,68453,88958,37451,71749,24317,82300,59523,24058,31963,90425,52071,54464,
					7462,39269,35673,25444,12088,93973,76189,98704,86547,98170,3677,74698,16960,22754,57039,51875,34395,86016,11017,19199,74973,64819,90947,15641,63470,66821,39699,95432,73597,
					91769,49896,81058,31037,1920,22854,43125,12244,99042,58180,15142,13564,61856,89839,30523,961,63230,98749,51708,49245,26117,70906,24218,90935,78205,39858,54404,45025,95908,
					66187,34974,87677,32434,32383,35065,50706,55236,78189,62949,70630,36369,78091,545,14576,67929,47419,15537,31158,46167,67244,96755,72283,54501,37324,79569,32705,77181,50324,
					94082,73089,16510,45407,77117,65296,77789,12181,16001,49377,6722,78949,36358,59442,73391,36902,74017,41320,84320,5905,88829,46838,89500,1935,19120,44001,39258,98688,93057,
					32791,49011,3490,22231,81872,48896,99347,47167,26685,27879,79519,92413,34600,74820,28770,94041,48210,65671,84410,5881,66342,90314,11062,13179,96166,12996,32298,40166,52254,
					47337,49574,85044,12699,53064,7274,94570,18311,22972,58089,61347,50850,37607,53759,1802,12426,82528,12194,60636,64550,96603,66516,30891,3269,93929,60421,99434,6925,9070,55951,
					59178,56406,5524,60573,69104,74939,84198,80026,93250,7169,38114,54596,74370,92072,24707,76171,4498,7234,88365,81485,71784,84967,64352,19026,4587,58281,79447,20372,65205,88516,
					92674,40734,44922,98198,17658,30377,89488,1855,10402,99089,25375,64867,70037,99744,56939,94743,75915,77788,1976,64279,75624,90111,65597,39975,9137,70184,98255,88583,6907,79811,
					93450,99581,36896,38371,14130,54553,85100,3617,72759,11853,19058,98133,76720,89094,97877,50010,188,73791,44149,18515,54421,19772,
					8626,20017,59746,17762,6552,74353,22696,13459,70515,16145,29391,7411,70868,43520,78315,55967,63488,51074,84171,82545,49206,60890,87990,63434,27251,4529,53576};
			//rotateArrayFast(c, 1);
			
			fastRotationClockwise(c,100000);
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
		//O(n * rotations)
		public static void rotateArray(int a[], int rotations) {
			if(a==null) throw new IllegalArgumentException();
			if(rotations <=0) return;
			
			System.out.println("before rotation:=" +Arrays.toString(a));
			
			for(int i=0; i<rotations;i++) {
				int temp = a[0];
				for(int j=1;j<=a.length-1;j++) {
					a[j-1] = a[j];
				}
				a[a.length-1] = temp;
			}
			
			System.out.println("after rotation:=" +Arrays.toString(a));
		}
		
		public static void rotateArrayFast(int[] a,int rotations) {
			if(a==null) throw new IllegalArgumentException();
			if(rotations <=0) return;
			int gcd = gcd(a.length,rotations);
			int size = a.length;
			System.out.println("before rotation:=" +Arrays.toString(a));
			
			for(int i=0;i<gcd;i++) {
				int temp = a[i];
				
				for(int j=i;j<size-(gcd-i);j+=gcd) {
					a[j] = a[j+gcd];
				}
				a[size-(gcd-i)]=temp;
			}
			
			System.out.println("after rotation:=" +Arrays.toString(a));
		}
		
		public static int gcd(int a,int b) {
			if(b==0) return a;
			return gcd(b,a%b);
		}
		
		 public static void fastRotationClockwise(int a[], int rotations) {
		        if(a==null) return;
		        if(rotations <=0) return ;
		        System.out.println("before rotation:=" +Arrays.toString(a));
		        int gcd = gcd(a.length,rotations);
				int size = a.length;
					
				for(int i=0;i<gcd;i++) {
					int temp = a[size-1-i];
						
					for(int j=size-i;j>i+gcd;j-=gcd) {
						a[j-1] = a[j-gcd-1];
					}
					a[gcd-i-1]=temp;
				}
				
				System.out.println("after rotation:=" +Arrays.toString(a));
		    }
}
