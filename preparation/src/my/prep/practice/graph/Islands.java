public class Islands {

  /*
  int[][] input = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };

        output

        [1, 1, 0, 0, 0]
        [1, 1, 0, 0, 0]
        [0, 0, 0, 0, 0]
        [0, 0, 0, 0, 0]
        [0, 0, 0, 1, 1]
        [0, 0, 0, 1, 1]
  */
  public staic void main(String[] args) {
    int[][] input = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };

        Islands test = new Islands();
        test.removeIslands(input);

        for (int[] a : input) {
            System.out.println(Arrays.toString(a) + '\n');
        }

  }
  
  public int[][] removeIslands(int [][] matrix) {

    //will iterate through border of the matrix and it found 1 will start DFS from that cell to all the connected neighbors and so on so forth
    //all connected cell starting from the border will be saved in a data structure border_island (their row,col)
    //once we complete iterating edge of the matrix the border_island will have all the connected once and we can relace those cell with 0 which are not the part of
    //border_island data structure
    if(matrix == null || matrix.length == 0 ) return matrix;

    int rows = matrix.length;
    int cols = matrix[0].length;

    boolean [][] visited = new boolean[rows][cols];
    //O(row * cols)
    for (int i=0; i<rows;i++) {
      for(int j=0;j<cols;j++)
        if(this.isBorderCell(i,j,rows,cols) && matrix[i][j] == 1) {
          // border cell with 1 we need to find all the neighbors connected with this cell and store in a map
          visited[i][j] = true;
          this.findConnectedNeighbors(i,j,matrix,visited);
        }
    }
    //O(row * cols)
    for (int i=0; i<rows;i++) {
      for(int j=0;j<cols;j++) {
        if(!visited[i][j]) matrix[i][j] = 0;
      }
    }
    return matrix;
  }

  private void findConnectedNeighbors(int row, int col, int [][] matrix, boolean[][] visited) {
    //traverse in all 4 direction and if it anyone has 1 call the function recursive.

    int[][] moves = {
            {0, 1},//right col
            {0, -1},//left col
            {1, 0},//below row
            {-1, 0}//above row
    };

    for(int mov=0; mov<moves.length;mov++) {
      int new_row = row + moves[mov][0];
      int new_col = col + moves[mov][1];
      if(!this.isCellOutside(new_row, new_col,matrix)) {
        if(matrix[new_row][new_col] == 1 && visited[new_row][new_col] == false) {
          visited[new_row][new_col] = true;
          //call recursive
          this.findConnectedNeighbors(new_row, new_col, matrix,visited);
        }
      }

    }

  }
  private boolean isCellOutside(int row, int col, int[][] matrix) {
    if(row<0 || row >= matrix.length || col < 0 || col >= matrix[0].length) return true;
    return false;
  }
  private boolean isBorderCell(int i, int j, int rows, int cols) {
    if(i==0 || i==rows-1 || j==0|| j==cols-1) return true;
    return false;
  }
}
