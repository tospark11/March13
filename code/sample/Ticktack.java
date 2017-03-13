package com.testing.sample;
import java.util.Scanner;
/**
 * This is TIC TAC TOE game written in Java.
 * @author Hanumantha Rao K
 * @date Feb 24th 2017
 * @version 1
 * 
 */
public class Ticktack {
	public static Scanner in = new Scanner(System.in);
	public static int[][] Board = new int[3][3];
	public static boolean WINNER_FOUND = false;
	public static int current_player = 0;
	public static boolean game_winner = false;
	public static boolean is_draw = false;
    /**
     * This is driver program
     * @param args
     */
	public static void main(String[] args) {
		System.out.println("welcome to the tictack player on is user and player two is computer ");
		// Print empty board with values zero.
		PrintBoard();
		current_player = 1;
		do {
			if (current_player == 1)
				PromptInputandSet(current_player);
			else
				ComputerPromptInputandSet(current_player);
			game_winner = Checkwinner(current_player);
			if (game_winner == true && is_draw == false) {
				System.out.println("Congrats_player" + current_player);
				System.exit(0);
			}
			if (is_draw == true) {
				System.out.println("it is a draw paly once agan");
				System.exit(0);
			}
			if (current_player == 1)
				current_player = 2;
			else if (current_player == 2)
				current_player = 1;
		} while (WINNER_FOUND != true);

	}
    /**
     * This method related to computer player
     * @param current_player2
     */
	private static void ComputerPromptInputandSet(int current_player2) {
		System.out.println("computer chance");

		int possible_row = -1;
		int possible_col = -1;
		int col_correctness = 0;
		int row_correctness = 0;
		int i;
		int j;
		int opp_current_player = 1;

		for (i = 0; i < 3; i++) {
			row_correctness = 0;
			for (j = 0; j < 3; j++) {
				if (Board[i][j] != opp_current_player) {
					possible_row = i;
					possible_col = j;
				} else {
					row_correctness++;
				}
			}
			if (row_correctness == 2
					&& Board[possible_row][possible_col] != current_player2) {
				UpdateBoard(possible_row, possible_col, current_player2);
				return;
			}
		}// for loop end
		int left_diagonal_count = 0;
		if (Board[0][0] == opp_current_player)
			left_diagonal_count++;
		else {
			possible_row = 0;
			possible_col = 0;
		}
		if (Board[1][1] == opp_current_player)
			left_diagonal_count++;
		else {
			possible_row = 1;
			possible_col = 1;
		}
		if (Board[2][2] == opp_current_player)
			left_diagonal_count++;
		else {
			possible_row = 2;
			possible_col = 2;
		}
		if (left_diagonal_count == 2
				&& Board[possible_row][possible_col] != current_player2) {
			UpdateBoard(possible_row, possible_col, current_player);
			return;
		}

		int right_diagonal_count = 0;
		if (Board[2][0] == opp_current_player)
			right_diagonal_count++;
		else {
			possible_row = 2;
			possible_col = 0;
		}
		if (Board[1][1] == opp_current_player)
			right_diagonal_count++;
		else {
			possible_row = 1;
			possible_col = 1;
		}
		if (Board[0][2] == opp_current_player) {
			right_diagonal_count++;
		} else {
			possible_row = 0;
			possible_col = 2;
		}
		if (right_diagonal_count == 2
				&& Board[possible_row][possible_col] != current_player2) {
			UpdateBoard(possible_row, possible_col, current_player2);
			return;
		}

		row_correctness = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				if (Board[i][j] != current_player) {
					possible_row = i;
					possible_col = j;
				} else {
					row_correctness++;
				}
			}
			if (row_correctness == 2) {
				UpdateBoard(possible_row, possible_col, current_player2);
				return;
			}
		}

		possible_row = -1;
		possible_col = -1;

		col_correctness = 0;
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				if (Board[i][j] != current_player) {
					possible_row = i;
					possible_col = j;
				} else {
					col_correctness++;
				}
			}
			if (col_correctness == 2) {
				UpdateBoard(possible_row, possible_col, current_player2);
				return;
			}
		}

		left_diagonal_count = 0;
		if (Board[0][0] == current_player2)
			left_diagonal_count++;
		else {
			possible_row = 0;
			possible_col = 0;
		}
		if (Board[1][1] == current_player2)
			left_diagonal_count++;
		else {
			possible_row = 1;
			possible_col = 1;
		}
		if (Board[2][2] == current_player2)
			left_diagonal_count++;
		else {
			possible_row = 2;
			possible_col = 2;
		}
		if (left_diagonal_count == 2) {
			UpdateBoard(possible_row, possible_col, current_player2);
			return;
		}

		right_diagonal_count = 0;
		if (Board[2][0] == current_player2)
			right_diagonal_count++;
		else {
			possible_row = 2;
			possible_col = 0;
		}
		if (Board[1][1] == current_player2)
			right_diagonal_count++;
		else {
			possible_row = 1;
			possible_col = 1;
		}
		if (Board[0][2] == current_player2)
			right_diagonal_count++;
		else {
			possible_row = 0;
			possible_col = 2;
		}
		if (right_diagonal_count == 2) {
			UpdateBoard(possible_row, possible_col, current_player2);
			return;
		}
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				if (Board[i][j] != 1 && Board[i][j] != 2) {
					UpdateBoard(i, j, current_player2);
					return;
				}
			}
		}

		System.out.println("Computer moved");

	}

	private static boolean Checkwinner(int current_player) {
		boolean row_check = true;
		int i;
		int j;
		for (i = 0; i < 3; i++) {
			row_check = true;
			for (j = 0; j < 3; j++) {
				if (Board[i][j] != current_player) {
					row_check = false;
				}
			}
			if (row_check != false && j == 3) {
				System.out.println("row strike");
				WINNER_FOUND = true;
				return true;
			}

		}

		for (i = 0; i < 3; i++) {
			row_check = true;
			for (j = 0; j < 3; j++) {
				if (Board[i][j] != current_player) {
					row_check = false;
				}
			}
			if (row_check != false && j == 3) {
				System.out.println("column strike");
				WINNER_FOUND = true;
				return true;
			}

		}

		if (Board[0][0] == current_player && Board[1][1] == current_player
				&& Board[2][2] == current_player) {
			WINNER_FOUND = true;
			return true;
		}
		if (Board[2][0] == current_player && Board[1][1] == current_player
				&& Board[0][2] == current_player) {
			WINNER_FOUND = true;
			return true;
		}
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (Board[row][col] != 1 || Board[row][col] != 2) {
					return false;
				}
			}
			if (row == 2)
				is_draw = true;
			return true;
		}
		return false;

	}
    /**
     * This program for User related entry
     * @param cp
     */
	private static void PromptInputandSet(int cp) {
		System.out.println("chance:player" + cp
				+ "enter the row and col as number 1 to 3");
		boolean valid_entry = false;
		do {
			int row = in.nextInt() - 1;
			int col = in.nextInt() - 1;
			if (row >= 0 && row < 3 && col >= 0 && col < 3
					&& Board[row][col] == 0) {
				valid_entry = true;
				UpdateBoard(row, col, cp);
			} else {
				System.out.println("Reenter chance player" + cp
						+ "enther the row and col as 1 to 3");
			}
		} while (valid_entry == false);
	}

	private static void UpdateBoard(int row, int col, int cpr) {
		if (cpr == 1)
			Board[row][col] = 1;
		if (cpr == 2)
			Board[row][col] = 2;
		PrintBoard();
	}

	
	public static void PrintBoard() {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				System.out.print(Board[i][j]  + " ");
			}
			System.out.println();

		}

	}
}
