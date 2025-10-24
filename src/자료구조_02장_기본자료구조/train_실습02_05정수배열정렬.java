package 자료구조_02장_기본자료구조;
/*
 * 2장 - 정수 배열 정렬
 */

import java.util.Arrays;
//교재 67 - 실습 2-5
//2번 실습
import java.util.Random;
public class train_실습02_05정수배열정렬 {
	
	static private void inputData(int[] data) {
		//난수 생성해서 data라는 배열에 int 넣기
		Random rd = new Random(11);
		for (int i = 0; i < data.length; i++) {
			data[i] = rd.nextInt(10, 60);
		}
	}
	
	static private void showData(String str, int[] data) {
		//data 보여 주기
		System.out.println(str);
		System.out.println(Arrays.toString(data));
	}
	
	static private void reverse(int[] data) {
		//data 배열 역순으로 저장
		for (int i = 0; i < data.length / 2; i++) {
			data[i] = data[data.length - 1 - i];
		}
	}
	
	static private void reverseSort(int[] data) {
		//역순으로 저장한 data 정렬
		Arrays.sort(data);
	}
	
	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data); //난수를 10 ~ 60 사이에 생성, seed 11 주기
		showData("난수 입력", data);
		/*
		sortData(data);
		showData("정렬후", data);
		*/
		reverse(data);//역순으로 재배치 - 정렬 아님 
		showData("역순 재배치", data);
		
		reverseSort(data);//역순으로 정렬
		showData("역순 정렬후", data);
		int realData[] = {5, 15, 99};
		for (int newData: realData) {
			int []result = insertData(data, newData);//입력 실수보다 큰 숫자를 우측으로 이동
			System.out.print("\n\n"+ newData+ " : ");
			showData("실수 삽입후", result);
		}
	}
	
	/*
	 * 난이도가 매우 높은 알고리즘 구현
	 * 정렬된 기존 배열에 임의 값을 추가하는 알고리즘 > 새 배열의 크기는 기존 배열보다 +1로 만들고 기존 배열을 copy할 때
	 * 삽입된 값이 중간에 들어가는 알고리즘 구현하기
	 * O(n) 알고리즘으로 구현 
	 */
	static int[] insertData(int []data, int value) {//insert되는 실수 값이 insert될 위치를 찾아 보다 큰 값은 우측으로 이동
		int newData[] = new int[data.length+1];
		// value와 data[i]의 값 비교
		//value >= data[i]이면 newData[i]에 data[i]값 삽입
		//삽입한 후 앞에 값들 newData 배열에 넣고
		//뒤에 값들 newData 배열에 넣기
		
		//pos는 value의 인덱스
		int pos = newData.length;
		
		for (int i = 0; i < data.length; i++) {
			if (data[i] <= value) {
				pos = i + 1;
				newData[pos] = value;
			} else {
				pos = i;
				newData[pos] = value;
				break;
			}
		}
			
		for (int i = 0; i < pos; i++) {
			newData[i] = data[i];
		} 
		
		for (int i = pos; i < data.length; i++) {
			newData[i + 1] = data[i];
		}
		
		return newData;
	}


}
