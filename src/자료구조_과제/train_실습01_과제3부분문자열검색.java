package 자료구조_과제;

/*
 * 주어진 긴 문자열 text에서 패턴 문자열 pattern이 등장하는 모든 시작 인덱스를 찾으세요.
 *
 * 입력: text = "abxabcabcabyabcaby", pattern = "abcaby"
 * 출력: [6,12]
 * 
 * 패턴이 나타나는 인덱스를 반환 => for 중첩문 활용
 */
public class train_실습01_과제3부분문자열검색 {
		
		public static void searchSubstring(String text, String pattern) {
			//조기 종료
//			if (text == null || text.isEmpty() || pattern == null || pattern.isEmpty()) {
//				return 0;
//			}
			int j = 0;
			// 만약 텍스트의 인덱스와 패턴 시작 인덱스가 같으면 이후 텍스트 인덱스에서 한 번 더 찾기
			for (int i = 0; i < text.length(); i++) {
				int count = i;
				while (j < pattern.length()) {
					if (text.charAt(count) == pattern.charAt(j)) {
						count++;
						j++;
					} else {
						j = 0;
						break;
					} if (j == pattern.length()) {
						System.out.println(i);
						j = 0;
						break;
					}
				}
			} System.out.println();
		} 
		
	    public static void main(String[] args) {
	        String text = "ababcabcabababd";
	        String pattern = "ababd";
	        searchSubstring(text, pattern);
	        
	        text = "abxabcabcabyabcaby";
	        pattern = "abcaby";
	        searchSubstring(text, pattern);
	    }
	}


