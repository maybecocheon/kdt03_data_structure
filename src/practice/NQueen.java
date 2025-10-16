package practice;
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

import java.util.Stack;

public class NQueen {
	//필드 생성
	// 체스판의 사이즈 static final로 지정
	static final int N = 8;
	// 체스판 이진 배열 생성
	int[][] board;
	// 퀸의 이동 경로를 저장하는 스택 생성
	Stack<Offset> st;
	// 퀸의 해결책 개수 생성
	int solutionCount;
	
	//생성자 생성
	public NQueen(){
		this.board = new int[N][N];
		this.st = new Stack<>();
		this.solutionCount = 0;
	}
	
	//메서드 생성
	// 체스판을 초기화하는 메서드
	void intializeBoard(){
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = -1;
			}
		}
	}
	
	// 해를 찾아 나가는 메서드
	boolean solve(){
		// 시작 위치가 필요(행과 열)
		// 반복(무한히)
		// - 행과 열에서 퀸을 배치할 수 있는 위치를 찾아야 함
		// - 퀸을 배치했으면 다른 형태의 배치 방법이 있는지 확인
		// - 퀸을 배치했는데 다른 형태의 배치 방법이 없으면 => 백트래킹 => 백트래킹 성공하면 멈추고 나가기
		
		int startX = 0;
		int startY = 0;
		
		while(true) {
			Offset current = st.peek();
			
			// 조건 만족하면
			if (isSafe(startX, startY)) {
				// 체스판 해당 자리 0으로 변경
				board[startX][startY] = 0;
				// 스택에 값 넣기
				st.push(new Offset(startX, startY));
				// 해결한 개수 +1
				solutionCount++;
			}
		}
		return false;
	}
	// 퀸이 체스판 내부에 있고 다른 퀸과 중복되지 않는지 확인하는 메서드
	boolean isSafe(int row, int col){
		// 열
		for (int i = col; i < N; i++) {
			if (board[row][i] != -1) {
				return false;
			}
		}
		// 왼쪽 위 대각선
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] != -1) {
				return false;
			}
		}
		// 오른쪽 위 대각선
		for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) {
			if (board[i][j] != -1) {
				return false;
			}
		} return true;
	}
	
	// 출력 메서드
	printSolve(){
		
	}
	
	public static void main(String[] args) {
		System.out.println("NQueen");
		NQueen solver = new NQueen();
		solver.solve();
	}
}
