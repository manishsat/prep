package my.prep.practice.backtracking;

public class WordSearch {

	private char[][] wordMatrix;
	
	public WordSearch(char[][]wordMatroix) {
		this.wordMatrix = wordMatroix;
	}
	public static void main(String[] args) {
		
	}
	//ReferenceL http://www.cnblogs.com/zhuli19901106/p/3570506.html
	//O(N^2 len(word)<-- I don'	t know how?
	//Video: https://www.youtube.com/watch?v=FFQ-nbul6VY
	//O(8 ^n*n)
	public boolean search(String word) {
		
		boolean[][] solutionMatrix = new boolean[wordMatrix.length][wordMatrix[0].length];
		//initialize 
		
		for(int i=0;i<solutionMatrix.length;i++) {
			for(int j=0;j<solutionMatrix[i].length;j++) {
				solutionMatrix[i][j] = false;
			}
		}
		
		
		//start from each and every spot and search for word in all the directions
		for(int i=0;i<wordMatrix.length;i++) {
			for(int j=0;j<wordMatrix[i].length;j++) {
				if(searchInternal(solutionMatrix, word,i, j, 0)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean searchInternal(boolean[][] solutionMatrix,String word,int row,int col,int index) {
		//Base case
		//1. already visited the char
		if(solutionMatrix[row][col]) {
			return false;
		}
		//2. the the row and col with index character is not equal not point starting search from that cell
		if(word.charAt(index) != wordMatrix[row][col]) {
			return false;
			
		}
		
		//3 if we reach the end character
		if(index == word.length() - 1) {
			solutionMatrix[row][col] = true;
			return true;
		}
		
		//mark current cell true
		solutionMatrix[row][col] = true;
		//recursive cases in all 8 directions
		//next row same col
		if(row + 1 < wordMatrix.length && searchInternal(solutionMatrix, word, row + 1, col, index + 1)) {
			return true;
		}
		//previous row same col
		if(row - 1 >= 0 && searchInternal(solutionMatrix, word, row - 1, col, index + 1)) {
			return true;
		}
		
		
		//same row next col
		if(col + 1 < wordMatrix.length && searchInternal(solutionMatrix, word, row, col + 1, index + 1)) {
			return true;
		}
		//same row prev col
		if(col - 1 >= 0 && searchInternal(solutionMatrix, word, row, col - 1, index + 1)) {
			return true;
		}
		
		if(row - 1 >=0 && col-1 >=0 && searchInternal(solutionMatrix, word, row - 1, col - 1, index + 1)) {
			return true;
		}
		
		
		if(row - 1 >=0 && col + 1 < wordMatrix.length && searchInternal(solutionMatrix, word, row - 1, col + 1, index + 1)) {
			return true;
		}
		
		
		if(row + 1 < wordMatrix.length && col - 1 >=0 && searchInternal(solutionMatrix, word, row + 1, col - 1, index + 1)) {
			return true;
		}
		
		if(row + 1 < wordMatrix.length && col + 1 <wordMatrix.length && searchInternal(solutionMatrix, word, row + 1, col + 1, index + 1)) {
			return true;
		}
		
		
		solutionMatrix[row][col] = false;
		return false;
		
	}
}
