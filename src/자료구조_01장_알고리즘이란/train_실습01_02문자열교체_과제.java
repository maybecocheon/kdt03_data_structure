package 자료구조_01장_알고리즘이란;

public class train_실습01_02문자열교체_과제 {

    // 서브스트링을 추가하는 함수
	/*
	 * concat(substring)
	 * replaceAll(target, replacement)
	 */
	public static String addSubstring(String s, String st) {
		return s.concat(st);
	}
    
	public static String replaceString(String newString, String st, String string) {
		return newString.replaceAll(st, string);
	}

    public static void main(String[] args) {
        String s = "자바 코딩, 파이썬 코딩, C 코딩, C# 코딩";
        
        // 서브스트링 추가
        String newString = addSubstring(s, " PCCP 시험: 11월 23일");
        System.out.println(newString);
        
        // 문자열 교체
        String finalString = replaceString(newString, "코딩", "프로그래밍 스킬");
        
        // 결과 출력
        System.out.println(finalString);
    }
}
