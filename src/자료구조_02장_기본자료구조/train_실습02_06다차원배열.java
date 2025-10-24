package 자료구조_02장_기본자료구조;

/*
 * 3번째 실습
 * 교재 83 - 배열 처리 + function parameter 전달 숙달 훈련 
 *  함수에서 배열을 리턴할 때 리턴 타입 정의할 수 있어야 한다
 */

import java.util.Arrays;
import java.util.Random;
public class train_실습02_06다차원배열 {
	
	static private void inputData(int[][] arr) {
		//랜덤 클래스 활용해 배열 생성
		Random rd = new Random();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = rd.nextInt(50);
			}
		}
	}
	
	static private void showData(String str, int[][] arr) {
		//배열 요소 출력하기
		System.out.println(str + ": ");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.printf("[%d][%d]=%d\t", i, j, arr[i][j]);
			} System.out.println();
		} System.out.println();
	}
	
	static private int[][] addMatrix(int[][] arr, int[][] arr2) {
		//arr배열과 arr2 배열의 각 요소를 더해서 sum 배열에 넣기
		int[][] sum = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				sum[i][j] = arr[i][j] + arr2[i][j];
			}
		}
		return sum;
	}
	
	static private int[][] multiplyMatrix(int[][] arr, int[][] arr2) {
		//arr배열과 arr2배열의 각 요소를 곱해서 mul 배열에 넣기
		//mul[i][j] = arr[i][0]*arr2[0][j] + arr[i][1]*arr2[1][j] + ...
		//n = 0이면서 n++ 값 하는 거 하나 정하기(arr[0]의 길이==arr2의 길이까지를 조건으로 함)
		int[][] mul = new int[arr.length][arr2[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr2[0].length; j++) {
				for (int n = 0; n < arr[0].length; n++) {
					mul[i][j] += arr[i][n] * arr2[n][j];
				}
			}
		}
		return mul;
	}
	
	static private int[][] transposeMatrix(int[][] arr) {
		//행과 열을 바꾸기
		int[][] tra = new int[arr[0].length][arr.length];
		for (int j = 0; j < arr[0].length; j++) {
			for (int i = 0; i < arr.length; i++) {
				tra[j][i] = arr[i][j];
			}
		}
		return tra;
	}
	
	static private int[][] multiplyMatrixTransposed(int[][] arr, int[][] arr2) {
		//곱해서 결과 낸 뒤 전치하기
		int[][] mul = new int[arr.length][arr2[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr2[0].length; j++) {
				for (int n = 0; n < arr[0].length; n++) {
					mul[i][j] += arr[i][n] * arr2[n][j];
				}
			}
		}
		int[][] multra = new int[mul[0].length][mul.length];
		for (int j = 0; j < mul[0].length; j++) {
			for (int i = 0; i < mul.length; i++) {
				multra[j][i] = mul[i][j];
			}
		}
		return multra;
	}
	
	static private boolean equals(int[][] arr, int[][] arr2) {
		// arr과 arr2의 배열 값이 같은지 비교
		// arr과 arr2는 행과 열의 크기가 같음
		boolean result = false;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == arr2[i][j]) {
					continue;
				} else {
					result = false;
				}
			}
		} result = true;
		return result;
	}
	
	public static void main(String[] args) {
		int [][]A = new int[2][3];
		int [][]B = new int[3][4];
		int [][]C = new int[2][4];

		inputData(A);inputData(B);
		int [][]D = A.clone();//교재83 - 배열 복제
		System.out.println("A[2][3] = ");
		showData("행렬 A", A);
		System.out.println("D[2][3] = ");
		showData("행렬 D", D);
		System.out.println();
		System.out.println("B[3][4] = ");
		showData("행렬 B", B);
		int [][]E = addMatrix(A,D);
		System.out.println("E[2][3] = ");
		showData("행렬 E", E);
		C = multiplyMatrix(A,B);
		System.out.println("C[2][4] = ");
		showData("행렬 C", C);

		int [][]F = transposeMatrix(A);
		System.out.println("F[3][2] = ");
		showData("행렬 F", F);
//		C = multiplyMatrixTransposed(A,F);
//		showData("행렬 곱셈 결과-전치행렬 사용", C);
//		boolean result = equals(A, C);
//		if (result)
//			System.out.println("행렬 A, C는 equal이다");
//		else
//			System.out.println("행렬 A, C는 equal 아니다");
	}
	
}
