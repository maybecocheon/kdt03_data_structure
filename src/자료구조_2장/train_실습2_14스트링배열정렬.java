package 자료구조_2장;

import java.util.Arrays;

/*
 * 2장 실습과제4 - 스트링 배열 정렬
 * 정렬된 배열에 insert하면 중간에 끼워 넣으면 큰 값들은 이동해야 하고 크기를 1 증가 처리가 필요 
 */
public class train_실습2_14스트링배열정렬 {
	//showData는 클래스 메서드
	static void showData(String str, String[] data) {
		System.out.println(str);
		for (String idx : data) {
			System.out.println(idx);
		} System.out.println();
	}
	
	//sortData는 클래스 메서드
	static void sortData(String[] data) {
		Arrays.sort(data, (s1, s2) -> s1.compareTo(s2));
	}
	
	//insertString은 클래스 메서드
	static String[] insertString(String[] data, String str) {
		String[] arr = new String[data.length + 1];
		//arr의 인덱스
		int pos = 0;
		//정렬된 data 안에 새로운 str 넣기, 넣을 때 배열 값들과 비교해서 넣어야 함
		for (int i = 0; i < data.length; i++) {
			if (data[i].compareTo(str) >= 0) {
				arr[i] = str;
				pos = i;
				break;
			}
		}
		//arr에 나머지 값들 넣어 주기
		for (int i = 0; i < pos; i++) {
			arr[i] = data[i];
		}
		for (int i = pos; i < data.length; i++) {
			arr[i + 1] = data[i];
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};

		showData("정렬전", data);
		//Arrays.sort(data); // Internally uses String.compareTo()
		Arrays.sort(data, (s1,s2)->s1.compareTo(s2));
		sortData(data);
		showData("정렬후", data);
		String[] newData = insertString(data, "banana");
		showData("삽입후 크기가 증가된 정렬 배열", newData);
		
	}

}
