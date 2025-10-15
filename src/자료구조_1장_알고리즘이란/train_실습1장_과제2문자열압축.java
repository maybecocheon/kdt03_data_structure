package 자료구조_1장_알고리즘이란;

/*
* 문자열에서 연속해서 반복되는 문자를 해당 문자와 반복 횟수로 압축요. 
* 단, 압축한 문자열이 원래 문자열보다 작아야 한다.
* 
* 입력: "aabcccccaaa"
* 압축 결과: "a2b1c5a3" (단, 길이 비교 후 결정)
*
* StringBuilder:
* 내부 버퍼를 사용하여 문자열을 직접 수정할 수 있다.
* 객체 자체를 변경하기 때문에 반복적인 문자열 조작에 효율적
*/
public class train_실습1장_과제2문자열압축 {

	// private static String compress(String input) {
	// // String은 일반 인풋, 아웃풋 때, 리턴 타입으로 씀
	// // StringBuilder 동기화 처리 x, 대신 속도 빠름, 리턴 타입으로 안 씀
	// // StringBuffer 동기화 처리 ㅇ

	// // 조기 종료 => String 종료 시 null, isEmpty 꼭 써 주기!!
	// if (input == null || input.isEmpty()) { // null은 계좌가 아예 없다는 뜻, isEmpty는 계좌는
	// 있는데 입금된 돈이 없음
	// return input;
	// }

	// String[] arr = input.split("");
	// StringBuilder compressed = new StringBuilder();
	// int count = 1;

	// for (int i = 1; i < arr.length; i++) {
	// if (arr[i - 1].equals(arr[i])) {
	// count++;
	// } else {
	// compressed.append(arr[i - 1] + count);
	// count = 1;
	// }
	// }
	// String st = compressed.toString();
	// return st;
	// }

	private static String compress(String input) {
		// 조기 종료 체크
		if (input == null || input.isEmpty()) {
			return input;
		}

		StringBuilder compressed = new StringBuilder();
		int count = 1;

		for (int i = 1; i < input.length(); i++) {
			if (input.charAt(i - 1) == input.charAt(i)) {
				count++;
			} else {
				compressed.append(input.charAt(i - 1)).append(count);
				count = 1;
			}
		}

		// 마지막 문자 그룹 처리
		compressed.append(input.charAt(input.length() - 1)).append(count);
		
		String compressedStr = compressed.toString();
		return compressedStr;
	}

	public static void main(String[] args) {
		String input = "aabcccccaaa";
		System.out.println("압축 결과: " + compress(input));
	}
}
