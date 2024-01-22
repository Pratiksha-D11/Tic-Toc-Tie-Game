import java.util.Random;
import java.util.Scanner;

class TicTacToe{
	static char[][] board;

	public TicTacToe() {
		board=new char[3][3];
		initboard();
	}
	void initboard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]=' ';
			}
		}
	}
	static void displayboard() {
		System.out.println("---------------");
		for(int i=0;i<board.length;i++) {
			System.out.print(" | ");
			for(int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println("---------------");
		}

	}
	static void placeMark(int row,int col,char mark) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			board[row][col]=mark;
		}
		else {
			System.out.println("Invalid position");
		}
	}
	static boolean checkcolwin() {
		for (int j=0;j<=2;j++) {
			if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]== board[2][j]) {
				return true;
			}
		}
		return false;
	}
	static boolean checkRow() {
		for(int i=0;i<=2;i++) {
			if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]) {
				return true;
			}
		}
		return false;
	}
	static boolean checkDiagWin() {
		if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1] == board[2][2]
				||board[0][2]!=' '&& board[0][2]==board[1][1] && board[1][1] ==board[2][0]) {
			return true;
		}
		return false;
	}
	
	 static boolean checkDraw() {
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j]==' ') {
					return false;
				}
			}	
		}
		return true;
	}

}
abstract class Player{
	String name;
	char mark;
	
	abstract void makeMove();
	
	boolean isvalidMove(int row,int col) {
		if(row >=0 && row<=2 && col>=0 && col<=2) {
			if(TicTacToe.board[row][col]==' ') {
				return true;
			}

		}
		return false;
	}
	
}

class HumanPlayer extends Player{
	

	HumanPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner scan=new Scanner(System.in);
		int row, col;
		do {
			System.out.println("Enter a row and column");
			row=scan.nextInt();
			col=scan.nextInt();
		}while(!isvalidMove(row,col));
		TicTacToe.placeMark(row, col, mark);

	}

}


class AiPlayer extends Player{
	String name;
	char mark;

	AiPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		Scanner scan=new Scanner(System.in);
		int row, col;
		do {
			Random r=new Random();
			row=r.nextInt(3);
			col=r.nextInt(3);
		}while(!isvalidMove(row,col));
		TicTacToe.placeMark(row, col, mark);

	}

}



public class LaunchGame {

	public static void main(String[] args) {
		TicTacToe T=new TicTacToe();

		HumanPlayer h1=new HumanPlayer("Bob",'x');
		//HumanPlayer h2=new HumanPlayer("Priya",'o');
		AiPlayer h2=new AiPlayer("Megha",'o');
		Player cp;
		cp= h1;

		while(true) {
			System.out.println(cp.name + "  turn");
			cp.makeMove();
			TicTacToe.displayboard();

			if(TicTacToe.checkcolwin() ||TicTacToe.checkRow() ||TicTacToe.checkDiagWin())
			{
				System.out.println(cp.name + " has won");
				break;
			}
			else if(TicTacToe.checkDraw()) {
				System.out.println("Game is Draw");
				break;
			}
			else {
				// Toggle between players
				cp = (cp == h1) ? h2 : h1;
			}
		}

	}
}


