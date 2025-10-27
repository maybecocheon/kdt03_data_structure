package chap01_Algorithm;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Chap01_06_StringToken {
/*
 * StringTokenizer(input), countTokens(), hasMoreTokens(), nextToken()
 * parseDouble(stringArray[i])
 */
  
	public static String[] extractSortStrings(String msg) {
		//토큰 단위로 나눠서 배열 생성
		StringTokenizer st = new StringTokenizer(msg);
		String[] arr = new String[st.countTokens()];
		for (int i = 0; i < arr.length; i++) { // i < st.countTokens()로 줘 버리면 토큰의 개수가 계속 바뀌므로 안 됨
												// countTokens()는 아직 읽지 않은 토큰의 개수를 출력함
			if(st.hasMoreTokens())
				arr[i] = st.nextToken();
		}
		//배열 정렬
		Arrays.sort(arr);
		return arr;
	}
	
	public static void printStringArray(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(String.format("%s  ", arr[i]));
		} System.out.println();
	}
	
	public static double[] convertSortToDouble(String[] arr) {
		double[] db = new double[arr.length];
		for (int i = 0; i < arr.length; i++) {
			db[i] = Double.parseDouble(arr[i]);
		}
		return db;
	}

	public static void printDoubleArray(double[] arr) {
		for (double d : arr) {
			System.out.print(String.format("%s  ", d));
		}
	}
	
    public static void main(String[] args) {
        String msg = "3.24 3.34156 1141.56 214. 0.0314156 54.12f";
        
        // 실수를 문자열로 추출하고 정렬 후 출력
        String[] sortedStringArray = extractSortStrings(msg);
        System.out.println("정렬 스트링 배열:");
        printStringArray(sortedStringArray);
        
        // 문자열 배열을 실수 배열로 변환하고 정렬 후 출력
        double[] sortedDoubleArray = convertSortToDouble(sortedStringArray);
        System.out.println("정렬 실수 배열:");
        printDoubleArray(sortedDoubleArray);
    }
}

