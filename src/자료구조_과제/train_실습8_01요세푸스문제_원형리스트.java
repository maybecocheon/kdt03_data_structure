package 자료구조_과제;

import java.util.Random;

/*
 * 원형 큐 형태인데 Queue[LinkedList]로 쓰면 원형 LinkedList 됨
 * 1. LinkedList 알아야 함
 * 2. 모듈레이션: 원형(%, mod) (size > index += 3 되면 모듈레이션 사용)
 * 3. 재귀(트리는 순회 전/중/후)
 * ==> 그래프(BFS, DFS)
  
 
문제1) 조세푸스 문제(Josephus Problem)

고전적인 수학 퍼즐로, 원형으로 앉아 있는 사람들 중에서 특정 규칙에 따라 사람들을 제거하면서 마지막으로 남는 사람을 찾는 문제이다.
이 문제는 다음과 같은 이야기에서 유래되었다.
1.	문제 배경:
고대 유대의 역사가 요세푸스(Josephus)와 그의 동료들이 로마군에게 포위당한 상황에서 자살을 결심하게 된다.
그들은 자살할 순서를 정하기 위해 원을 만들고, 특정 간격마다 사람을 제거하는 방식으로 마지막 사람을 남기는 방법을 선택한다.
요세푸스는 이 방법을 통해 마지막에 살아남을 위치를 계산해 자신은 자살을 피했다.

문제 설명:
N명의 사람이 원형으로 앉아 있다.
K번째 사람을 반복적으로 제거한다.
이 과정을 N-1명이 제거될 때까지 반복한다. 3번째가 제거 대상이면 2명(A> B)이면 A가 자살 대상이다
마지막으로 남는 사람의 위치를 구하는 문제이다.

입력:
N: 사람의 수(각 사람의 id는 정수 난수로 생성(난수 고정)- 생성 순서로 원형 singly linked list에 id의 올림차순으로 정렬되게 삽입)
K: 제거할 사람의 순서

N: 노드의 수
K: 제거할 노드의 순서

출력:
1.	올림차순으로 정렬된 사람 id 순서를 출력
2.	제거되는 k 번째 id 순서대로 출력
3.	마지막으로 남는 사람의 id를 출력
*/

class Node {
	int id;
	Node next; //next만 있으면 단방향
	
	Node(int id) {
		this.id = id;
		
	}
}

class SinglyLinkedList {
	Node head;
	// head -> null
	// insertSorted(2)
	// head -> 2 -> null
	
	// tail을 잘 다뤄야 함
	// head -> 3 -> 5 -> 8
	// insertSorted(2)
	// head -> 2 -> 3 -> 5 -> 8 -> null
	public void insertSorted(int id) {
		// 연결리스트는 Node 생성 먼저 해야 함
		Node newNode = new Node(id);
		
		// 1) head == null
		// 2) 새 head가 기존의 head 앞에 위치
		// 3) 중간 어딘가에 추가 (제일 끝도 포함)
	}
	
	public void showList() {
		
	}
}

public class train_실습8_01요세푸스문제_원형리스트 {
	
	public static void main(String[] args) {
		// 출력: 조세푸스(7, 3) 문제
		System.out.println("조세푸스 (7, 3)");
		int n = 7;
		int k = 3;
		
		Random rd = new Random(42);
		
		// index/itme(N개가 {Queue, LinkedList}에, K를 선택)
		// => 문제가 연결리스트로 풀라고 했으니 LinkedList로 풀기
		// singlyLinkedList = new singlyLinkendList(); => 노드를 핸들링하기 위한 것 => 즉 연결리스트 쓰려면 노드부터 만들어야 됨
		SinglyLinkedList sll = new SinglyLinkedList();
		
		// 1. for(int i = 0; i < N; i++) {/* id를 정렬해서 추가 */} => add 연습해 보라고 정렬하는 거임
		for (int i = 0; i < n; i++) {
			int id = rd.nextInt(1000) + 1;
			sll.insertSorted(id);
		}
		// 2. 제거 순서를 출력
		sll.showList();
		
		// 3. item solve(K, N) {}
	}
}
