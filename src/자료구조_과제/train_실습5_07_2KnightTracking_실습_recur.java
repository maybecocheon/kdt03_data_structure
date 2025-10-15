package 자료구조_과제;

import java.util.Stack;

//큐를 사용해서 체스판 더 작게 만들어서 풀기

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


// enum knightMoves {NW, NE, EN, ES, SE, SW, WW, WN} // knight가 움직일 때의 차례(순서)


public class train_실습5_07_2KnightTracking_실습_recur {
	static Offsets4[] moves = new Offsets4[8];//static을 선언하는 이유를 알아야 한다
    static final int N = 6; // 체스판 크기

    // 체스판 배열
    private static int[][] board = new int[N][N];

    // Point 객체로 나이트의 현재 좌표를 저장
    static class Point {
        int x, y, moveToward;

        Point(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.moveToward = move; // 어느 방향으로 움직였는지, 움직인 이력
        }
    }

    // 체스판을 초기화 (-1로 설정)
    // 한 번도 안 간 곳을 -1로 설정하는 것임
    private static void initializeBoard() {
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			board[i][j] = -1;
    		}
    	}
    }

    // 체스판의 범위 내에서 유효한 움직임인지 확인
    private static boolean isSafe(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == -1);
    }

    // 나이트 투어 알고리즘 (재귀)
    private static boolean solveKnightTrackingRecursive(int startX, int startY, int moveCount) {
    	// 기저 조건
    	if (moveCount == N * N) {
    		return true;
    	}
    	
        // 나이트가 이동할 수 있는 8가지 방향
        for (int i = 0; i < 8; i++) {
    		int nextX = startX + moves[i].a;
    		int nextY = startY + moves[i].b;
    		
    		// 8가지 방향으로 나이트 이동 시도
    		if (isSafe(nextX, nextY)) {
    			board[nextX][nextY] = moveCount;
    			if (solveKnightTrackingRecursive(nextX, nextY, moveCount + 1)) {
    				return true;
        	}
    		board[nextX][nextY] = -1;
    	}	
     }
        return false; // 해결하지 못함
   }
    
    // 재귀는 1) 모든 경계 조건 완화 2) 반복문처럼 움직임
    
    // 결과 출력
    private static void showTracking() {
    	System.out.println("기사의 여행 경로 : ");
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			System.out.printf("%3d", board[i][j]);
    		}
    		System.out.println();
    	}
    }
    
    private static void initializeMoves() {
		for (int ia = 0; ia < 8; ia++) {
    		moves[ia] = new Offsets4(0, 0); //배열에 Offsets4 객체를 치환해야 한다. for문 여기서 닫힌 거임.
    	} 
    	moves[0].a = -2;	moves[0].b = -1;//NW으로 이동
    	moves[1].a = -2;	moves[1].b = 1;//NE
    	moves[2].a = -1;	moves[2].b = 2;//EN
    	moves[3].a = 1;		moves[3].b = 2;//ES
    	moves[4].a = 2;		moves[4].b = 1;//SE
    	moves[5].a = 2;		moves[5].b = -1;//SW
    	moves[6].a = 1;		moves[6].b = -2;//WS
    	moves[7].a = -1;	moves[7].b = -2;//WN
	}
    
    private static boolean solveKnightTracking(int startX, int startY) {
    	//초기화
    	initializeMoves();
    	board[startX][startY] = 0;
    	//재귀식
    	if (solveKnightTrackingRecursive(startX, startY, 1)) {
    		//종료 조건
    		return true;
    	}
    	//종료 조건
    	return false;
    }

    public static void main(String[] args) {
        initializeBoard();

        // 나이트가 (0, 0)에서 시작
        if (solveKnightTracking(0, 0)) {
            showTracking();
        } else {
            System.out.println("해결할 수 없습니다.");
        }
    }
}

