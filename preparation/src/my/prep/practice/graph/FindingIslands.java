package my.prep.practice.graph;

public class FindingIslands {

	public static void main(String[] args) {
			int[][] matrix = {
	                        {1, 0, 1, 0, 1},
	                        {1, 1, 0, 0, 0},
	                        {0, 1, 0, 1, 1},
	                     };
		
			System.out.println("No of Island are "+findIslands(matrix));
		}
	
	public static int findIslands(int[][] matrix) {
		int islandCount = 0;
		
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		
		for(int i=0; i<matrix.length;i++) {
			for(int j =0;j<matrix[0].length;j++) {
				if(matrix[i][j] == 1 && !visited[i][j]) {
					findIslandInternal(i,j,matrix,visited);
					islandCount++;
				}
			}
		}
		return islandCount;
	}
	
	private static void findIslandInternal(int i,int j,int[][] matrix,boolean[][] visited) {
		
		visited[i][j] = Boolean.TRUE;
		
		int[] offSet = {-1,0,+1};
		int iOffSet = 0;
		int jOffSet = 0;
		
		for(int x=0; x<offSet.length;x++)  {
			iOffSet = offSet[x];
			for(int y=0;y<offSet.length;y++) {
				jOffSet = offSet[y];
				
				if(iOffSet == 0 && jOffSet == 0) {
					continue;
				}
				if(isValidNeighbor(i + iOffSet,j + jOffSet,matrix,visited)) {
					if(!visited[i + iOffSet][j + jOffSet]) {
						findIslandInternal(i + iOffSet,j + jOffSet,matrix,visited);	
					}
					
				}
				
			}
		}
		
	}
	
	private static boolean isValidNeighbor(int i,int j,int[][] matrix,boolean[][] visited) {
		if((i>=0) && (i<matrix.length) && (j>=0) && (j<matrix[0].length)) {
			if(matrix[i][j]==1) {
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}
}
