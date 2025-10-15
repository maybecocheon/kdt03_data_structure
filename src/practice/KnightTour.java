package practice;

import java.util.Stack;

//기사의 여행에서는 백트래킹과 경계 조건이 가장 중요함!!!!!!!!

/*
* Knight's Tour 문제는 체스판에서 나이트(Knight) 말이 모든 체스판의 칸을 한 번씩만 방문하면서
* 체스판의 모든 방을 방문하면 종료. 
* 나이트는 체스에서 "L" 모양으로 움직이는데, 두 칸 직진하고 한 칸 옆으로 이동하는 방식입니다.
* 임의 위치에서 시작

문제 설명
체스판은 보통 8x8 크기이지만, 이 문제는 임의의 N x N 체스판에서 해결할 수 있습니다.
목표는 나이트가 시작점에서 출발하여 모든 칸을 한 번씩만 방문하면서 끝나는 경로를 찾는 것입니다.
종료조건: 모든 칸이 방문하였을 때 종료 > 방문한 순서를 출력

구현조건:
(x,y)를 저장하는 point 객체를 사용하여 스택으로 non-recursive backtracking 알고리즘으로 구현
*/

public class KnightTour {
	// 움직일 위치 정보 저장하는 클래스를 이용해 배열 생성
	// static으로 선언해야 static 메서드 안에서 쓰일 수 있음
	static Offset[] moves = new Offset[8];
	
	// 체스판 크기 static final로 지정
	static final int N = 6;
	
	// static int 타입의 체스판 배열 생성
	// static으로 선언해야 static 메서드 안에서 쓰일 수 있음
	static int[][] board = new int[N][N];
	
	// Point 객체로 나이트의 현재 좌표를 저장
	// static으로 선언해야 static 메서드 안에서 쓰일 수 있음
	static class Point {
		// 움직일 x좌표, y좌표, 방향 필드 생성
		int x;
		int y;
		int moveToward;
		
		public Point(int x, int y, int moveToward) {
			this.x = x;
			this.y = y;
			this.moveToward = moveToward;
		}
	}
	
	// 체스판을 초기화 (-1로 설정)
    // 한 번도 안 간 곳을 -1로 설정하는 것임
	static void initializeBoard(){
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = -1;
			}
		}
	}
	
	// 체스판의 범위 내에서 유효한 움직임인지 확인
	static boolean isSafe(int x, int y){
		// x, y 좌표가 체스판을 벗어나는 경우 invalid => false
		// 매개변수 인자로 받은 행과 열에 이미 값이 있는 경우 invalid => false
		// 즉, 행과 열이 체스판 내부에 있고 행과 열에 값이 없는 경우는 valid => true
		return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == -1);
	}
	
	// 나이트 투어 알고리즘 (비재귀적으로 하기 위해 스택 사용)
	// 매개변수로 받은 시작 x좌표, y좌표를 기준으로 나이트 투어 함
	static boolean solveKnightTracking(int startX, int startY) {
		//배열에 아무런 정보가 저장되어 있지 않은 Offsets 객체를 대입해 주어야 한다.
		for (int i = 0; i < 8; i++){
			moves[i] = new Offset(0, 0);
		}
		// 나이트가 이동할 수 있는 8가지 방향을 배열에 저장
		moves[0].row = -2; moves[0].col = -1; //남남서
		moves[1].row = -2; moves[1].col = 1; //남남동
		moves[2].row = 2; moves[2].col = -1; //북북서
		moves[3].row = 2; moves[3].col = 1; //북북동
		moves[4].row = -1; moves[4].col = -2; //남서서
		moves[5].row = 1; moves[5].col = -2; //북서서
		moves[6].row = -1; moves[6].col = 2; //남동동
		moves[7].row = 1; moves[7].col = 2; //북동동
		
		// 현재 좌표를 저장할 스택 생성
		Stack<Point> st = new Stack<>();
		
		// 시작 위치를 스택에 푸시
		st.push(new Point(startX, startY, 0));
		
		// 체스판에 knight 채워졌다는 걸 +1 해서 0으로 표시
		board[startX][startY] = 0;
		
		// 이동한 횟수 변수 생성
		// 체스판 한 자리 채워졌기 때문에 이동한 횟수를 1로 설정
		int moveCount = 1;
		
		// 스택이 비어 있지 않은 동안 반복문
		while(!st.isEmpty()) {
			//현재 knight의 좌표 확인
			Point current = st.peek();
			
			// 기저 조건
        		// 1. 체스판의 모든 곳 방문하면 값 반환(true)
			if (moveCount == N * N){
				return true;
			}
			
			// 2. 8가지 방향으로 knight 이동 시도했는데 이동하지 못하면 값 반환(false)
			// boolean 타입의 flag 변수 생성
			boolean moved = false;
			
			// 8가지 방향으로 knight 이동 시도(반복문)
			for (int i = current.moveToward; i < 8; i++){	
				// 다음 이동할 x좌표, y좌표 설정
				// 현재 좌표의 x,y좌표에서 8가지 방향으로 이동하는 경우의 수 생성 
				int nextX = current.x + moves[i].row;
				int nextY = current.y + moves[i].col;
				
				// 체스판 범위 내에서 유효한 움직임인지 확인한 후 knight 이동
				if (isSafe(nextX, nextY)) {
					// 현재 Point 객체의 이동 방향
					// 백트래킹의 복귀 시점에서 이어서 탐색하기 위함 => 없으면 무한루프 빠짐
					current.moveToward = i + 1;
					// 체스판을 채운 knight의 개수만큼 board에 저장
					board[nextX][nextY] = moveCount;
					// 스택에 좌표 저장
					st.push(new Point(nextX, nextY, 0));
					// 체스판 채운 knight의 개수 증가
					moveCount++;
					moved = true;
					break;
				}
			}
			
			// 더 이상 이동할 곳이 없을 경우 백트래킹 (safe하지 않다는 뜻)
			if (!moved) {
				Point backtrack = st.pop();
				// 3. 시작 지점에서 더 이상 움직일 수 없는 경우 false 반환
				if (backtrack.x == startX && backtrack.y == startY) {
					board[backtrack.x][backtrack.y] = -1;
					return false;
				}
				board[backtrack.x][backtrack.y] = -1;
				moveCount--;
			}
			
		}
		//해결하지 못한 경우 값 반환
		return false;
	}
	
	//결과 출력 메서드
	static void showTracking() {
		System.out.println("기사의 여행 경로: ");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%3d", board[i][j]);
			} System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// 초기화 메서드
        initializeBoard();
        
        // 나이트가 (0, 0)에서 시작
        if (solveKnightTracking(0, 0)) {
        	//결과 출력 메서드
            showTracking();
        } else {
            System.out.println("해결할 수 없습니다.");
        }
    }
}
