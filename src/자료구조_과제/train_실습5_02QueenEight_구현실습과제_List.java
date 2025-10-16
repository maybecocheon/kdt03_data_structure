package 자료구조_과제;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class train_실습5_02QueenEight_구현실습과제_List {
	private static final int BOARD_SIZE = 8;
	private int solutionsCount = 0;
	
	//이진리스트
	private List<List<Integer>> allSolutions = new ArrayList<>();
	
	//i, j에 퀸이 있다는 것을 가정
	//row는 List의 인덱스, col은 List(row)의 값
	private List<Integer> queens = new ArrayList<>();
	
	//충돌 체크
	private Set<Integer> usedCols = new HashSet<Integer>(); // 사용중인 열
	private Set<Integer> usedDiag1 = new HashSet<Integer>(); // 대각선 \ row - col
	private Set<Integer> usedDiag2 = new HashSet<Integer>(); // 대각선 / row + col
	
	public void solve() {
		backtrack(0);
	}
	
	//재귀 구문
	private void backtrack(int row) {
		//기저 조건
		if (row == BOARD_SIZE) {
			solutionsCount++;
			saveSolution();
			printSolution();
			return;
		}
		//현재 행을 기준으로 각 열을 계산 (행 하나 혹은 열 하나만 이동하는 게 이득)
		for (int col = 0; col < BOARD_SIZE; col++) {
			if (isSafe(row, col)) {
				//퀸 배치
				placeQueen(row, col);
				//다음 행으로 진행
				backtrack(row + 1);
				//백트래킹: 퀸 제거
				removeQueen(row, col);
			}
		}
	}
	
	//Set의 가장 큰 특징: 중복 허용하지 않음
	private boolean isSafe(int row, int col) {
		return !usedCols.contains(col)
				&& !usedDiag1.contains(row - col)
				&& !usedDiag2.contains(row + col);
	}
	
	private void placeQueen(int row, int col) {
		queens.add(col);
		usedCols.add(col);
		usedDiag1.add(row - col);
		usedDiag2.add(row + col);
	}
	
	private void removeQueen(int row, int col) {
		queens.remove(queens.size() - 1);
		usedCols.remove(col);
		usedDiag1.remove(row - col);
		usedDiag2.remove(row + col);
	}

	private void saveSolution() {
		allSolutions.add(new ArrayList<>(queens));
	}

	private void printSolution() {
		System.out.println("\n해 " + solutionsCount + ":");
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (queens.get(i) == j) {
					System.out.print("Q ");
				} else {
					System.out.print(". ");
				}
			} System.out.println();
		} System.out.println("퀸의 위치: " + queens);
	}
	
	public List<List<Integer>> getAllSolutions() {
		return new ArrayList<>(allSolutions);
	}
	
	public int getSolutionsCount() {
		return solutionsCount;
	}
	public static void main(String[] args) {
		System.out.println("NQueen");
		train_실습5_02QueenEight_구현실습과제_List solver = new train_실습5_02QueenEight_구현실습과제_List();
		solver.solve();
		List<List<Integer>> solutions = solver.getAllSolutions();
		System.out.println("\n모든 해: " + solutions.size());
	}
}