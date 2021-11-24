/*
 * Name: Rochan Muralitharan
 * Date: February 21 2021
 * Filename: FoxHunterRochan
 * Purpose: Test knowledge on Unit 1 by making the fox hunter game
 */
package foxHunterGame;
import java.util.*;
public class FoxHunterRochan {
	
	//fills board with zeros and then places fox mom and cubs
	//pre: game board array 
	//post:fills array with zeros and places fox mom and cubs
	public static void fillBoard(int [][]board) {
		//fill whole board with zeros
		for(int row=0; row<board.length; row++) {
			for(int col=0; col<board[row].length; col++) {
				board[row][col]=0;
			}
		}
		
		//places cubs in board
		for(int i=0; i<4; i++) {
			boolean unique=true;
			int row, col;
			do {
				//picks random number for row and col to place cub
				row=(int)(Math.random()*6);
				col=(int)(Math.random()*6);
				//if spot is empty, place cub, if not, unique changes to false and new random number for row and col is given
				if(board[row][col]==0)
					board[row][col]=1;
				else
					unique=false;
			}while(!unique);
		}
		
		//places fox mom on board
		int row, col;
		boolean unique=true;
		do {
			//picks random number for row and col to place fox mom
			row=(int)(Math.random()*6);
			col=(int)(Math.random()*6);
			//if spot is empty, place mom, if not, unique changes to false and new random number for row and col is given
			if(board[row][col]==0)
				board[row][col]=2;
			else
				unique=false;
		}while(!unique);
	}
	
	//finds number of foxes in row, column or diagonal
	//pre: game board array
	//post: tells user how many foxes are in the row/column/diagonal they chose
	public static void laser(int [][]board) {
		/*
		 * Variable Table
		 * Scanner
		 * check, String, user input for row, col, or diagonal
		 * num, int, row/col/diagonal number
		 * count, int, number of foxes in row/col/diagonal(initially at 0)
		 */
		Scanner scan = new Scanner(System.in);
		String check;
		int num, count=0;
		
		//user prints if they want row, column or diagonal
		System.out.println("Pick row(r), column(c) or diagonal(d)");
		check=scan.nextLine();//user input
		while(!check.equals("r") && !check.equals("c") && !check.equals("d")) {//if invalid letter is inputted, keep on checking until valid letter is inputted
			System.out.println("Invalid letter, pick row(r), column(c) or diagonal(d)");
			check=scan.nextLine();
		}
		
		if(check.equals("r")) {//if row, user picks row number and count number of foxes in row
			System.out.println("Pick a row number(1-6)");
			num=scan.nextInt();
			while(num<1||num>6) {// if number is not between 1 and 6, reenter number
				System.out.println("Invalid number, re-enter row number");
				num=scan.nextInt();
			}
			
			num-=1;//reduces num by one, array start with 0
			for(int i=0; i<board[0].length; i++) {
				if(board[num][i]!=0)//cycles through row and counts number of foxes
					count++;
			}
			System.out.println("There are " + count + " fox(es) in row " + (num+1));	
		}
		
		else if(check.equals("c")) {//if col, user picks col number and count number of foxes in col
			System.out.println("Pick a column number(1-6)");
			num=scan.nextInt();
			while(num<1||num>6) {// if number is not between 1 and 6, reenter number
				System.out.println("Invalid number, re-enter column number");
				num=scan.nextInt();
			}
			
			num-=1;//reduces num by one, array start with 0
			for(int i=0; i<board.length; i++) {
				if(board[i][num]!=0)//cycles through row and counts number of foxes
					count++;
			}
			System.out.println("There are " + count + " fox(es) in column " + (num+1));
		}
		
		else if(check.equals("d")) {//if diagonal, user picks which diagonal and count number of foxes in diagonal
			System.out.println("Pick the left diagonal(1) or the right diagonal(2)");
			num=scan.nextInt();
			while(num<1||num>2) {// if number is not between 1 and 2, reenter number
				System.out.println("Invalid number, re-enter diagonal number(1-left diagonal, 2-right diagonal)");
				num=scan.nextInt();
			}
			
			if(num==1) {//for left diagonal
				for(int i=0; i<board.length; i++) {
					if(board[i][i]!=0)//cycles through row and counts number of foxes
						count++;
				}
				System.out.println("There are " + count + " fox(es) in the left diagonal");
			}
			
			else if(num==2) {//for right diagonal
				for(int i=0; i<board.length; i++) {
					if(board[i][(board.length-1-i)]!=0)//cycles through row and counts number of foxes
						count++;
				}
				System.out.println("There are " + count + " fox(es) in the right diagonal");
			}
		}
	}
	
	//user makes shot, if successful, spot is cleared of the fox and user is notified
	//pre:game board array
	//post:prints if shot is successful or not and changes game board accordingly(removes fox)
	public static void shot(int [][]board) {
		/*
		 * Variable Table
		 * Scanner
		 * row, int, row number
		 * col, int, col number
		 */
		Scanner scan = new Scanner(System.in);
		int row, col;
		
		System.out.println("Time to make the shot!");
		System.out.println("Enter a row number(1-6)");
		row=scan.nextInt();//user input for row number
		while(row<1||row>6) {//if invalid row num, reenter row num
			System.out.println("Invalid number, re-enter row number(1-6)");
			row=scan.nextInt();
		}
		System.out.println("Enter a column number(1-6)");
		col=scan.nextInt();//user input for col num
		while(col<1||col>6) {//if invalid col num, reenter col num
			System.out.println("Invalid number, re-enter column number(1-6)");
			col=scan.nextInt();
		}
		//array starts at 0, makes sure user input is valid in the array
		row-=1;
		col-=1;
		if(board[row][col]==0) {//if user shot does not hit fox, say shot is unsuccessful
			System.out.println("SHOT UNSUCCESSFUL!");
		}
		else {//if shot hits fox, remove fox and tell user shot is successful
			System.out.println("SHOT SUCCESSFUL!");
			board[row][col]=0;
		}
	}
	
	//moves fox mom randomly to closest empty spot(up, right, left, down)
	//pre: game board array
	//post: moves fox mom to closest empty spot(random)
	public static void moveMom (int [][] board) {
		/*
		 * Variable Table
		 * possible, boolean, checks if moving fox mom is possible
		 * momcount, int, counts if there is mom or not
		 * mrow, int, row that mom resides in
		 * mcol, int, column that mom resides in
		 * upossible, boolean, checks if moving up is possible
		 * dpossible, boolean, checks if moving down is possible
		 * lpossible, boolean, checks if moving left is possible
		 * rpossible, boolean, checks if moving right is possible
		 */
		boolean possible=false;
		int momcount=0;
		int mrow=0;
		int	mcol=0;
		boolean upossible=true, dpossible=true, lpossible=true, rpossible=true;
		
		//checks if there mom is still in the board
		for(int row=0; row<board.length; row++) {
			for(int col=0; col<board[0].length; col++) {
				if(board[row][col]==2) {
					momcount++;
					mrow=row;
					mcol=col;
				}
			}
		}
		
		if(momcount==1) {//if mom is still on board
			
			while(!possible) {//if move is not possible, ran is generated again to get a possible move
				int ran=(int)(Math.random()*4);//generates random number to determine which direction mom moves in(0-up, 1-down, 2-left, 3-right)
				
				if(ran==0 && upossible) {//if move ran is 0 and moving up is possible, continue
					if(mrow!=0) {//if mom is not in the first row, continue
						if(board[mrow-1][mcol]==0) {//if spot above mom is empty, move mom up and set possible to true so while loop is broken
							board[mrow-1][mcol]=2;
							board[mrow][mcol]=0;
							possible=true;
						}
						else//if spot above mom is not empty, moving up is not possible
							upossible=false;
					}
					else//if mom is in first row, moving up is not possible 
						upossible=false;
				}			
				
				else if(ran==1 && dpossible) {//if move ran is 1 and moving down is possible, continue
					if(mrow!=5) {//if mom is not in the last row, continue
						if(board[mrow+1][mcol]==0) {//if spot below mom is empty, move mom down and set possible to true so while loop is broken
							board[mrow+1][mcol]=2;
							board[mrow][mcol]=0;
							possible=true;
						}
						else//if spot below mom is not empty, moving down is not possible
							dpossible=false;
					}
					else//if mom is in last row, moving down is not possible 
						dpossible=false;
				}
				
				else if(ran==2 && lpossible) {//if move ran is 2 and moving left is possible, continue
					if(mcol!=0) {//if mom is not in the first column, continue
						if(board[mrow][mcol-1]==0) {//if spot to the left of mom is empty, move mom left and set possible to true so while loop is broken
							board[mrow][mcol-1]=2;
							board[mrow][mcol]=0;
							possible=true;
						}
						else//if spot to the left of mom is not empty, moving left is not possible
							lpossible=false;
					}
					else//if mom is in first column, moving left is not possible 
						lpossible=false;
				}
				
				else if(ran==3 && rpossible) {//if move ran is 3 and moving right is possible, continue
					if(mcol!=5) {//if mom is not in the last column, continue
						if(board[mrow][mcol+1]==0) {//if spot to the right of mom is empty, move mom left and set possible to true so while loop is broken
							board[mrow][mcol+1]=2;
							board[mrow][mcol]=0;
							possible=true;
						}
						else//if spot to the right of mom is not empty, moving right is not possible
							rpossible=false;
					}
					else//if mom is in last column, moving right is not possible 
						rpossible=false;
				}
				
				else if(!upossible && !dpossible && !lpossible && !rpossible) {//if no moves are possible, tell user mom cannot move and exit loop
					System.out.println("Mom fox cannot move.");
					possible=true;
				}
			}
		}	
	}
	
	//checks the game status(win or in progress)
	//pre: game board array
	//post: boolean for game win, true(if game is won), false(if game in progress)
	public static boolean gameStatus(int [][] board) {
		//count, int, number of foxes in board
		int count=0;
		for(int row=0; row<board.length; row++) {
			for(int col=0; col<board[0].length; col++) {
				//if fox is still on board, increase count
				if(board[row][col]!=0)
					count++;
			}
		}
		
		//if no more foxes left, return true, else, return false
		if(count==0)
			return true;
		else
			return false;
	}
			
	//prints board to console
	//pre: game board array
	//post: prints game board to console
	public static void printBoard(int [][] board) {
		for(int row=0; row<board.length; row++) {
			for(int col=0; col<board[row].length; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Variable Table:
		 * board, 2d int array, holds spots of cubs and fox mom
		 * gameWin, boolean, checks if user won game
		 */
		int [][] board = new int [6][6];
		boolean	gameWin=false;
		
		//fills board
		fillBoard(board);
		
		//prints board
		printBoard(board);
		
		for(int i=0; i<8; i++) {
			System.out.println("You have " + (8-i) + " turn(s) remaining");
			for(int p=0; p<3; p++) {
				laser(board);//uses laser function 3 times
			}
			shot(board);//user makes shot
			gameWin=gameStatus(board);//checks if game has been won
			if(gameWin)
				break;//if won exit the loop
			moveMom(board);//move mom if game is not won
			printBoard(board);//print board again
		}
		
		if(gameWin) {//if game is won, print game board and tell user they won
			printBoard(board);
			System.out.println("CONGRATULATIONS, YOU WON!");
		}
		else if(!gameWin) {//if game is lost, tell user they lost
			System.out.println("YOU LOSE, RESTART PROGRAM TO PLAY AGAIN!");
		}
	}
}
