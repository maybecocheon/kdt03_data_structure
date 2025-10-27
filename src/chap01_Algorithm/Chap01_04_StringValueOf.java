package chap01_Algorithm;

import java.util.Arrays;

public class Chap01_04_StringValueOf {
/*
 * valueOf(boolVal)
 * Arrays.sort(array)
 */

    // 값을 문자열로 변환하여 배열에 저장하는 함수
    public static String[] convertValuesToString(boolean boolVal, char charVal, double doubleVal, int intVal, float floatVal) {
    	String[] arr = new String[5];
    	arr[0] = String.valueOf(boolVal);
    	arr[1] = String.valueOf(charVal);
    	arr[2] = String.valueOf(doubleVal);
    	arr[3] = String.valueOf(intVal);    	
    	arr[4] = String.valueOf(floatVal);
    	return arr;
    }

    // 배열을 정렬하는 함수
    public static void sortStringArray(String[] array) {
    	Arrays.sort(array);
    }

    // 배열을 출력하는 함수
    public static void showAllString(String[] array) {
    	for(int i = 0; i < array.length; i++) {
    		System.out.print(String.format("%s\t", array[i]));
    	}
    }
 
    public static void main(String[] args) {
        // 정수, float, double, boolean 값을 문자열로 변환하여 배열에 저장
        String[] stringArray = convertValuesToString(true, 'A', 3.14, 123, 45.67f);
        
        // 정렬 전 배열 출력
        System.out.println("정렬전:");
        showAllString(stringArray);
        
        // 배열 정렬
        sortStringArray(stringArray);
        
        // 정렬 후 배열 출력
        System.out.println("\n정렬후:");
        showAllString(stringArray);
    }
}


