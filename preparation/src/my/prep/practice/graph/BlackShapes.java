package my.prep.practice.graph;
/*
 * 

Given N * M field of O's and X's, where O=white, X=black
Return the number of black shapes. A black shape consists of one or more adjacent X's (diagonals not included)
OOOXOOO
OOXXOXO
OXOOOXO

answer is 3 shapes are  :
(i)    X
     X X
(ii)
      X
 (iii)
      X
     
 * 
 * */
public class BlackShapes {
	public static void main(String[] args) {
		String[] A = { "XOOOOOXXOX", "OOXXXXOOXX", "XXOXXOOXXO", "OXOXXXXXXO", "XOXXOXOXXX", "OOOOOOOXOO", "XOXXXOOXOX", "XXXOXOXXXO" };
		System.out.println("No of black shapes are " + black(A) );
	}
	public static int black(String[] a) {
        if(a == null || a .length == 0) return 0;
        
        boolean[][] matrix = getMatrix(a);
        
        boolean [][] visited = new boolean[matrix.length][matrix[0].length]; //default is false;
         int shapes=0;
        for(int i=0;i<visited.length;i++) {
            for(int j=0;j<visited[0].length;j++) {
                if(!visited[i][j]) {
                   
                    if(matrix[i][j]) {
                    	System.out.println(i + "," + j);
                        dfs(i,j,visited,matrix); 
                        shapes++;
                    }
                }
            }
        }
        return shapes;
        
    }
    
    private static void dfs(int i,int j,boolean[][] visited,boolean[][] matrix) {
        
        if(visited[i][j]) return;
        visited[i][j] = Boolean.TRUE;
        //all condition to go to next cell
        if(j + 1 < matrix[0].length && matrix[i][j+1]) dfs(i,j+1,visited,matrix);
        
        if(j - 1 >= 0 && matrix[i][j-1] ) dfs (i, j -1, visited,matrix);
        
        if(i + 1 < matrix.length && matrix[i+1][j]) dfs(i + 1, j , visited,matrix);
        
        if(i - 1 >= 0 && matrix[i-1][j]) dfs(i-1,j,visited,matrix);
        
    }
    
    
    private static boolean[][] getMatrix(String[] list) {
        boolean[][] matrix = new boolean[list.length] [list[0].length()];
        for(int i=0;i<list.length;i++) {
            int j = 0;
            for(char c : list[i].toCharArray()) {
                if(c == 'X') {
                    matrix[i][j] = Boolean.TRUE;
                }
                j++;
            }
        }
        return matrix;
    }
	
}
