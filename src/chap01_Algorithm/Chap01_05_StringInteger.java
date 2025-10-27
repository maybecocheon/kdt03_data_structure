package 자료구조_01장_알고리즘이란;

import java.util.Arrays;

public class train_실습01_05_숫자변환_과제 {
/*
 * split(" ")
 * parseInt(stringArray[i])
 */
	public static String[] splitSortString(String input) {
		String[] s = input.split(" ");
		Arrays.sort(s);
		return s;
	}
	
	public static void printStringArray(String[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(String.format("%s\t", array[i]));
		} System.out.println();
	}
	
	public static int[] convertSortToIntArray(String[] arr) {
		int[] num = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			num[i] = Integer.parseInt(arr[i]);
		}
		Arrays.sort(num);
		return num;
	}
	
	public static void printIntArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(String.format("%s\t", arr[i]));
		}
	}

    public static void main(String[] args) {
        String input = "12 111 911 921 94 23 214 222";
        
        // 문자열 배열 정렬 및 출력
        String[] sortedStringArray = splitSortString(input);
        System.out.println("Sorted String Array:");
        printStringArray(sortedStringArray);
        
        // 문자열 배열을 정수 배열로 변환 및 정렬 후 출력 => 문자열 정렬 시 12 112 비교하면 112가 먼저 정렬되기 때문
        int[] sortedIntArray = convertSortToIntArray(sortedStringArray);
        System.out.println("Sorted Integer Array:");
        printIntArray(sortedIntArray);
    }
}
