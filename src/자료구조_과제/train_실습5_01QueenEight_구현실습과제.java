package 자료구조_과제;
//print로 변수 값 확인하는 디버깅 노동 피하자
//이클립스 디버깅 실습 필요 : variables tab, Expressions tab 사용하기
//92개 해 확인 필요
import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
//N Queen problem / backtracking
//모든 해가 나오는 버젼 만들기 
/*
* 체스판은 8 x 8 체스의 기물:
* king/가로세로대각선 1칸만 이동,
* queen/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
* Rook/가로,세로 이동/다른 기물을 넘을 수없다,
* bishop/대각선,
* knight/1-2칸 이동/다른 기물을 넘을 수 있다,
* pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능
* 체스판 최대 배치 문제 : king/16, Queen/8, rook/8, bishop/?,
* knight/? rook 2개/a, h, knight 2개/b, g, bishop 2개/c, f, queen 1개/black queen은 black 칸에, 폰 8개
*/
/*
* 8-Queen 문제는 체스판 위에 8개의 퀸을 배치하되, 서로 공격할 수 없도록 배치하는 문제입니다. 
* 이 문제를 해결하기 위한 비재귀적(스택 기반) 알고리즘을 구현하려면, 다음과 같은 방법을 사용할 수 있습니다.

개요
1. 스택을 사용하여 백트래킹을 구현합니다. 각 스택의 요소는 체스판의 각 열에 대한 퀸의 배치 상태를 나타냅니다.
2. 퀸을 한 줄씩 배치한 후, 유효한지 확인하고, 다음 줄로 이동합니다.
3. 유효하지 않으면 스택을 이용해 이전 상태로 돌아가서 다른 경로를 시도합니다.

알고리즘
1. 스택을 이용하여 백트래킹을 구현하기 때문에, 현재 상태를 스택에 저장합니다. 
 스택의 각 원소는 퀸의 배치를 나타냅니다.
2. 체스판의 각 열에 대해 가능한 위치를 하나씩 확인하면서 퀸을 배치하고, 
 충돌이 발생하지 않는다면 다음 열로 넘어갑니다.
3. 더 이상 유효한 위치가 없으면, 스택에서 이전 상태로 되돌아가서 새로운 경로를 탐색합니다.
4. 퀸을 8개 다 배치하면, 해를 찾은 것이므로 스택을 이용해 해결책을 저장합니다.
*/

class Position {
	private int row;
	private int col;

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	@Override
	public String toString() {
		return "[row: " + (row + 1) + ", col: " + (col + 1) + "]";
	}
}

class Stack4 {
	// --- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		public EmptyGenericStackException(String message) {
			super(message);
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException(String message) {
			super(message);
		}
	}

	private List<Position> data; // 스택용 배열
	// private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 생성자(constructor) ---//
	public Stack4(int capacity) {
		this.capacity = capacity;
		data = new ArrayList<Position>(capacity);
		top = 0;
	}

	// --- 스택에 x를 푸시 ---//
	public boolean push(Position x) throws OverflowGenericStackException {
		if (isFull()) {
			throw new OverflowGenericStackException("스택이 가득 찼습니다.");
		} else {
			data.add(x);
			top++;
			return true;
		}
	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Position pop() throws EmptyGenericStackException {
		if (isEmpty()) {
			throw new EmptyGenericStackException("스택이 비어 있습니다.");
		} else {
			top--; // 먼저 증감하고 remove 해야 함
			Position p = data.get(top);
			data.remove(top);
			return p;
		}
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Position peek() throws EmptyGenericStackException {
		if (isEmpty()) {
			throw new EmptyGenericStackException("스택이 비어 있습니다.");
		} else {
			return data.get(top);
		}
	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(Position x) { // 92 되어야 함
		for (int i = top; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	// --- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	// --- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	// --- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	// --- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() throws EmptyGenericStackException{
		if (top < 0)
			throw new EmptyGenericStackException("stack:: dump - empty");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

public class train_실습5_01QueenEight_구현실습과제 {
	private static final int BOARD_SIZE = 8;
	private int[][] board;
	private Stack4 queens;
	private int solutionsCount;
	
	public int getSolutionsCount() {
		return solutionsCount;
	}

	public train_실습5_01QueenEight_구현실습과제() {
		this.board = new int[BOARD_SIZE][BOARD_SIZE];
		this.queens = new Stack4(100);
		this.solutionsCount = 0;
		intializeBoard();
	}
	
	private void intializeBoard() {
		for (int i = 0; i < BOARD_SIZE; i++)
			for (int j = 0; j < BOARD_SIZE; j++)
				board[i][j] = 0;
	}
	
	private void solve() {
		// 시작 위치가 필요(행과 열)
		// 반복(무한히)
		// - 행과 열에서 퀸을 배치할 수 있는 위치를 찾아야 함
		// - 퀸을 배치했으면 다른 형태의 배치 방법이 있는지 확인
		// - 퀸을 배치했는데 다른 형태의 배치 방법이 없으면 => 백트래킹 => 백트래킹 성공하면 멈추고 나가기
		
		int row = 0;
		int col = 0;
		
		while(true) {
			boolean placed = false;
			
			// 현재 행에서 퀸을 배치할 수 있는 "열" 찾기
			while (col < BOARD_SIZE) {
				if (isSafe(row, col)) {
					board[row][col] = 1;
					queens.push(new Position(row, col));
					placed = true;
					break;
				}
				col++;
			}
			
			//퀸을 배치했으면
			if (placed) {
				//모든 퀸을 다 찾았으면
				if (queens.size() == BOARD_SIZE) {
					//해를 하나 찾으면 +1
					solutionsCount++;
					printSolve();
					
					//다른 해를 찾기 위한 백트래킹
					try {
						Position last = queens.pop();
						board[last.getRow()][last.getCol()] = 0;
						row = last.getRow();
						col = last.getCol() + 1;
					} catch (Exception e) {
						System.out.println("스택이 비어 있습니다.");
						break;
					}
					
				} else {
					row++;
					col = 0;
				}
			} else {
				if (queens.isEmpty()) {
					break;
				}
				try {
					Position last = queens.pop();
					board[last.getRow()][last.getCol()] = 0;
					row = last.getRow();
					col = last.getCol() + 1;
				} catch (Exception e) {
					System.out.println("스택이 비어 있습니다.");
					break;
				}
			}
		}
	}

	private boolean isSafe(int row, int col) {
		//열
		for (int i = 0; i < row; i++) {
			if (board[i][col] == 1) {
				return false;
			}
		}
		// 왼쪽 위 대각선
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		// 오른쪽 위 대각선
		for (int i = row - 1, j = col + 1; i >= 0 && j < BOARD_SIZE; i--, j++) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		return true;
	}
	
	private void printSolve() {
		System.out.println("\n해 " + solutionsCount + ":");
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == 1) {
					System.out.printf("Q ", board[i][j]);
				} else {
					System.out.printf(". ", board[i][j]);
				}
			} System.out.println();
		}
		System.out.print("해: ");
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == 1) {
					System.out.printf("[%d, %d] ", i, j);
				}
			}
		} System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("NQueen");
		train_실습5_01QueenEight_구현실습과제 solver = new train_실습5_01QueenEight_구현실습과제();
		solver.solve();
	}
}
	


	
