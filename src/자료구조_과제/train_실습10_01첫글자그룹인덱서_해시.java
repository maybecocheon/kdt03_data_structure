package 자료구조_과제;
/*
문제: 체인 해시 기반 “첫 글자 그룹 인덱서” (정렬 유지)
문제 설명
모든 키는 소문자 알파벳 문자열이다(정규식: ^[a-z]+$).
각 키는 첫 글자로 그룹을 결정한다(예: apple→그룹 a, zoo→그룹 z).
Separate Chaining(체인 해시) 를 직접 구현하여 다음 연산을 처리하라.
표준 해시맵(unordered_map/HashMap/dict) 사용 금지.

연산
ADD s : 키 s를 저장한다. 이미 존재하면 무시한다.
DEL s : 키 s가 존재하면 삭제한다. 없으면 무시.
FIND c : 그룹 문자 c(단일 소문자 a~z)의 모든 키를 알파벳 오름차순으로 공백 구분 출력. 비면 -
COUNT c: 그룹 c의 키 개수 출력
BUCKETG i: 그룹 해시테이블의 i번 버킷에 들어있는 그룹키들을 "체인 순서"로 공백 구분 출력(없으면 -)
BUCKETI i: 아이템→그룹 해시테이블의 i번 버킷에 들어있는 (key:group)을 체인 순서로 공백 구분(없으면 -)
그룹은 항상 s[0]으로 자동 결정된다. 키는 한 번만 저장되며 중복 없음.

입력 형식
MG MI Q
Q개의 줄: 명령
MG : 그룹 해시테이블 버킷 수 (1 ≤ MG ≤ 200003)
MI : 아이템→그룹 해시테이블 버킷 수 (1 ≤ MI ≤ 200003)
Q : 명령 수 (1 ≤ Q ≤ 200000)
키 s : 정규식 ^[a-z]+$
그룹 c : 단일 문자 a~z

출력 형식
FIND c → k1 k2 ... 또는 -
COUNT c → 정수
BUCKETG i → g1 g2 ... 또는 -
BUCKETI i → a1:g1 a2:g2 ... 또는 -
ADD, DEL 은 출력 없음

예시
ADD apple => 그룹 a에 apple 추가, 해시 충돌 없음
ADD ant	  => 그룹 a에 ant 추가, 정렬 위치에 삽입
ADD ball  => 그룹 b에 ball 추가
ADD cat   => 그룹 c에 cat 추가
FIND a    => "ant apple"
COUNT a   => 2
DEL ant	  => 그룹 a에서 ant 삭제


필수 구현 제약
Separate Chaining만 사용(버킷: 단일 연결 리스트)

정렬 유지: 각 그룹 내부의 연결 리스트는 항상 알파벳 오름차순이 되도록 삽입 시 위치를 찾아 넣기(정렬 유지 삭제 포함)

금지: 표준 해시맵류 / 정렬 컨테이너(map, set 등) 사용 금지. 배열/벡터/연결리스트는 허용.
 */

// 그룹 노드
class GroupNode {
	char groupChar;
	ItemNode items;
	GroupNode next;
}

// 아이템 노드
class ItemNode {
	String key;
	ItemNode next;
}

class KeyNode {
	String key;
	char group;
	KeyNode next;
}

public class train_실습10_01첫글자그룹인덱서_해시 {
	static void solve(int , int , commands) {
		for (int i = 0; i < commands.length; i++) {
			// 1. ADD ant
			// 2. if ADD이면 add 메서드 실행
			switch(commands) {
			case "ADD":
			case "DEL":
			case "COUNT":
			case "FIND":	
			case "COUNT":
			}
		}
		return;
	}
	
	public static void main(String[] args) {
		solve(10, 10, commands);
	}
}
