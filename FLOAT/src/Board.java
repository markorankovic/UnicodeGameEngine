import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board { 
	
	private int width;
	private int height;
	char character;
	char[][] characters;
	
	public boolean contains(int row, int col) {
		return row >= 0 && row < height && col >= 0 && col < width;
	}
	
	public Board(int width, int height, char character) { 
		this.width = width;
		this.height = height;
		this.character = character;
		characters = new char[height][width];
		setAllCharacters();
	}
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		characters = new char[height][width];
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setCharacter(int col, int row) { 
		characters[row][col] = character;
	}
	
	public void setCharacter(int col, int row, char character) { 
		characters[row][col] = character;
	}
	
	void setAllCharacters() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				setCharacter(col, row);
			}
		}
	}
	
	public String makeRow(int row) {
		String column = "";
		for (int col = 0; col < width; col++) {
			column += characters[row][col] + "";
		}
		return column + "\n";
	}
	
	public String makeBoard() {
		String rows = "";
		for (int row = 0; row < height; row++) {
			rows += makeRow(row);
		}
		return rows;
	}
	
	public static Board convertStringToBoard(String text) {
		ArrayList<String> strings = new ArrayList<String>(Arrays.asList(text.split("\n")));
		ArrayList<Integer> sortedStrings = new ArrayList<Integer>();
		for (int i = 0; i < strings.size(); i++) {
			sortedStrings.add(strings.get(i).length());
		}
		Collections.sort(sortedStrings);
		Collections.reverse(sortedStrings);
		Board board = new Board(sortedStrings.get(0), sortedStrings.size());
		for (int row = 0; row < board.height; row++) {
			for (int col = 0; col < board.width; col++) {
				board.characters[row][col] = col >= strings.get(row).length() ?  ' ' : strings.get(row).charAt(col);
			}
		}
		return board;
	}
	
	public static Board convertFileToBoard(File file) throws IOException { // Text files with tab characters can cause problems!
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		String result = "";
		while ((line = br.readLine()) != null) {
			result += line + "\n";
		}
		br.close();
		return convertStringToBoard(result);
	}
	
	public void addBoard(Board board, int row, int col, char alpha) {
		for (int trow = 0; trow < board.height; trow++) {
			for (int tcol = 0; tcol < board.width; tcol++) {
				char pixel = board.characters[trow][tcol];
				if (pixel == alpha) continue;
				int pRow = row + trow;
				int pCol = col + tcol;
				if (!contains(pRow, pCol)) continue;
				this.characters[pRow][pCol] = pixel;  
			}
		}
	}
	
	public void addBoard(Board board, int row, int col) {
		addBoard(board, row, col, ' ');
	}
	
	public String toString() {
		return makeBoard();
	}
	
}

