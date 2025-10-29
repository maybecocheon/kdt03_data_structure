package chap03_SearchAlgorithm;
/*
* 3장 1번 실습과제: 03-3 정수배열이진검색
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
* 3장 - 정수 배열 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색
*/
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class Chap03_01_IntArrayBinarySearch {
	// 씨드 11
	
	//inputData는 클래스 메서드
	static void inputData(int[] data) {
		//배열 안에 데이터를 넣어 주면 됨
		Random rd = new Random(11);
		for (int i = 0; i < data.length; i++) {
			data[i] = rd.nextInt(101);
		}
	}
	
	//showList는 클래스 메서드
	static void showList(String str, int[] data) {
		System.out.println(str);
		System.out.print("[");
		for (int i = 0; i < data.length; i++) {
			if (i < data.length - 1) {
				System.out.print(data[i] + ", ");
			} else {
				System.out.print(data[i] + "]");
			}
		} System.out.println();
	}
	
	//linearSearch는 int 리턴값을 가지는 스태틱 메서드
		static int linearSearch(int[] data, int key) {
			for (int i = 0; i < data.length; i++) {
				if (data[i] == key) {
					return i;
				} 
			} return -1;
		}
	
	//binarySearch는 int 리턴값을 가지는 스태틱 메서드
	static int binarySearch(int[] data, int key) {
		int pl = 0;
		int pr = data.length - 1;
		
		do {
			int pc = (pl + pr) / 2;
			if (data[pc] == key) {
				return pc;
			} else if (data[pc] < key) {
				pl = pc + 1;
			} else {
				pr = pc - 1;
			}
		} while (pl <= pr);
		
		return -1;
	}
	
	public static void main(String[] args) {
		int []data = new int[30];
		inputData(data);// 구현 반복 숙달 훈련 - 100 이하 난수를 생성

		showList("정렬 전 배열[]:: ", data);
		Arrays.sort(data);
		showList("정렬 후 배열[]:: ", data);// 구현 반복 숙달 훈련
		
		Random rd = new Random(11);
		int key = rd.nextInt(101);
		int resultIndex = linearSearch(data, key);//교재 99-100:실습 3-1 참조, 교재 102: 실습 3-2
		//Arrays 클래스에 linear search는 없기 때문에 구현해야 한다 
		System.out.println("\nlinearSearch(78): key = " + key + ", result = " + resultIndex);

		key = rd.nextInt(101);
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);//함수 구현이 필요
		System.out.println("\nbinarySearch(76): key = " + key + ", result = " + resultIndex);
		
		key = rd.nextInt(101);
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(64): result = " + resultIndex);

	}
}
