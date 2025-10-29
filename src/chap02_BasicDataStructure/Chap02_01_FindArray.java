package chap02_BasicDataStructure;
/*
 * 2장: 메소드 함수에 parameter 전달
 * 메소드에 배열 전달 실습: 교재 59 - 메소드의 매개변수로 배열 사용하기
 * function parameters를 작성할 수 있어야 한다 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Chap02_01_FindArray {
	static int top = 0;
	static final int MAX_LENGTH = 20;
	
	private static int[] inputData(int[] data){//교재 63 - 난수의 생성
		//top이 배열에 저장된 갯수를 저장
		
		//data 배열에 MAX_LENGTH를 값으로 해서 난수를 넣는다
		Random rd = new Random();
		//난수가 각 인덱스마다 들어가도록 반복문을 쓴다
		for (int i = 0; i < data.length; i++) {
			data[i] = rd.nextInt(MAX_LENGTH);
			top++;
		}
		return data;
	}
	
	private static void showData(String s, int[] data) {
		//top 갯수까지 출력한다 [1,2,3]등으로 출력하도록 작성
		
		//s를 출력하고 data의 값을 출력한다
		//data 각 인덱스의 값을 출력하기 위해 반복문을 쓴다
		//근데! Arrays.toString을 쓰면 훨~씬 간단
		System.out.print(s + ": ");
//		System.out.println(Arrays.toString(data));
		System.out.print("[");
		for (int i = 0; i < data.length; i++) {
			if (i < data.length - 1) {
				System.out.print(data[i] + ", ");
			} else {
				System.out.print(data[i] + "]");
			}
		} System.out.print("\ntop의 개수: " + top);
	}
	
	private static int findMax(int[] data) {
		//data 배열에서 최댓값을 찾는다
		//인덱스 0과 1을 비교하고 그중 더 큰 값을 2와 비교하기 위해 반복문을 쓴다
		//근데! 반복문에서 i와 i+1을 비교해 버리면 배열 크기 초과하는 오류 발생! 따라서 따로 변수를 생성해 준다
		int max = data[0];
		for (int i = 0; i < data.length; i++) {
			if (max < data[i]) {
				max = data[i];
			}
		}
		return max;
	}
	
	private static boolean findValue(int[] data, int n) {
		// n 값을 data 배열 안에 있는지 없는지를 조사한다
		// 인덱스 0일 때부터 비교하는 반복문을 쓴다
		// 근데! 비교 결과에 따라 불린 값이 달라지므로 불린 변수를 선언한다
		boolean flag = false;
		for (int i = 0; i < data.length; i++) {
			if (data[i] == n) {
				flag = true;
			} else flag = false;
		}
		return flag;
	}
	
	private static int[] reverse(int[] data) {
		// data 배열을 뒤에서부터 꺼내서 새로운 배열에 앞에서부터 넣는다
		// data 배열의 크기와 똑같은 새로운 배열을 생성한다
		int[] arr = new int[data.length];
		// data 배열 맨뒤를 arr 배열 맨앞에 넣는 것을 반복한다
		for (int i = 0; i < data.length; i++) {
			arr[i] = data[data.length - 1 - i];
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);
		showData("소스데이터",data);
		int max = findMax(data);
		System.out.println("\nmax = " + max);
		boolean existValue = findValue(data, 3);
		System.out.println("찾는 값 = " + 3 + ", 존재여부 = " + existValue);
		reverse(data);// 역순으로 출력 
		showData("역순 데이터", reverse(data));
		
	}
}

