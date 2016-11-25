package my.prep.practice.heap;

public class MinHeap {

	private int MAX_SIZE = 100;
	private int[] heap = new int[MAX_SIZE];
	private int size = 0;
	
	public void add(int value) {
		if (size > MAX_SIZE) return;
		size++;
		heap[size] = value;
		upHeap(size);
	}
	private void upHeap(int index) {
		while(index > 1 && heap[index] < heap[index/2]) {
			swapElement(index,index/2);
			index = index/2;
		}
	}
	
	private void swapElement(int x,int y) {
		int z = x;
		x = y;
		y = z;
	}
	
	public int extractMin() {
		if(size ==0) return -1;
		int result = heap[1];
		heap[1] = heap[size];
		size --;
		downHeap(1,size);
		return result;
	}
	
	private void downHeap(int startIndex,int endIndex) {
		
		while(2*startIndex <= endIndex) {
			int child = 2 * startIndex;
			if(child < size) {
				//mean right child is there now the check the right child value
				if(heap[child+1] < heap[child]) {
					child = child +1;
				}
			}
			
			if(heap[child] < heap[startIndex]) {
				swapElement(child,startIndex);
				startIndex = child;
			}
		}
	}
	public int findValue(int v) {
		//Heap is not like BST so we need to search full heap-> O(n)
		for(int i=1;i<=size;i++) {
			if(heap[i]==v) return i;
		}
		return -1;
	}
}
