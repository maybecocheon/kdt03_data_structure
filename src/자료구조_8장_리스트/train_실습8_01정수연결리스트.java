package 자료구조_8장_리스트;

//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class Node1 {
	int data; 	// 데이터 저장 변수
	Node1 link; // 다른 노드를 참조할 링크 노드

	public Node1(int element) {
		data = element;
		link = null;
	}
}

class LinkedList1 {
	Node1 head; // Node1 타입의 head 노드 인스턴스 변수

	public LinkedList1() {
		head = null;
	}

	public boolean Delete(int element) { //전달된 element 값이 존재 하면 삭제하고 true로 리턴
		Node1 p = head;
		Node1 q = null; // q는 p를 따라다닌다 => 연결 끊기 전에 q에 p가 가리키는 다음 노드 값을 저장해 주어야 함
		while (p != null) { // p가 연결되어 있을 때까지 => p가 tail일 때까지
			if (element == p.data) {
				if (q == null) {
					// case1 : 삭제할 노드가 head인 경우
					head = p.link; // head는 p의 다음 노드를 참조하도록 함
				} else {
					// case2 : 중간 또는 마지막 노드 삭제
					q.link = p.link;
				}
				p.link = null; // p의 link는 null을 할당하여 연결을 끊음
				return true;
			}
			q = p;
			p = p.link;
		}
		return false;
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node1 p = head;
//		int num = 0;
		if (head == null) {
		    System.out.println("리스트가 비어 있습니다.");
		    return;
		}
		System.out.println("== 결과 출력 ==");
		while (p != null) {
			
			System.out.print(p.data + " ");
			p = p.link;
		}
		System.out.println();
	}

	public void Add(int element) { // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
		Node1 newNode = new Node1(element);
		if (head == null) { // insert into empty list
			head = newNode; // head.link = newNode로 쓰면 NP Exception 에러 뜸
			return;
		} else {
			//올림차순으로 정렬되게 삽입
			Node1 p = head;
			Node1 q = null; // q는 p를 따라다닌다
			while (p != null) {
				if (element < p.data) {
					//1. 맨 처음에 삽입되는 경우
					if (p == head) {
						newNode.link = p;
						head = newNode;
						break;
					}
					//2. 중간에 삽입되는 경우
					else {
						q.link = newNode;
						newNode.link = p;
						break;
					}
				} else {
					q = p;
					p = p.link;
				}
			}
			//3. 끝에 삽입되는 경우
			if (q != null) { // 마지막에 삽입
				q.link = newNode;
				return;
			}
		}
	}

	public boolean Search(int data) { // 전달된 data 값을 찾아 존재하면 true로 리턴, 없으면 false로 리턴
		Node1 ptr = head;
		while (ptr != null) {
			if (data == ptr.data) {
				return true;
			} else {
				ptr = ptr.link; // 연결리스트의 핵심, 다음 노드 가리키기
			}
		}
		return false;
	}
	void Merge(LinkedList1 b) {
		/*
		 * 연결리스트 a,b에 대하여 a = a + b
		 * merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지 않고 합병하는 알고리즘 구현 => 제자리 병합
		 * 난이도 등급: 최상
		 * a = (3, 5, 7), b = (2,4,8,9)이면 a = (2,3,4,5,8,9)가 되도록 구현하는 코드
		 */
		
		if (b.head == null) return;
		
		Node1 p = head;	// 리스트 a의 포인터
		Node1 q = null;
		Node1 bp = b.head;	// 리스트 b의 포인터
		
		// a와 b의 노드 개수 같을 떄
		while (p != null && bp != null) {
			// p <= bp
			if (p.data <= bp.data) {
				// p를 그대로 두면 됨
				q = p;
				p = p.link;
			// p > bp
			} else {
				// bp를 p 앞(q 뒤)에 삽입
				Node1 temp = bp.link;
				if (q == null) {
					head = bp;
					bp.link = p;
					q = head;
				} else {
					q.link = bp;
					bp.link = p;
					q = bp;
				}
				bp = temp;
			}
		}
		
		// 남는 노드 있을 때
		if (bp != null) {
			q.link = bp;
		}
		
		// b 리스트 초기화 => 메모리 절약
		b.head = null;
	}
}

public class train_실습8_01정수연결리스트 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("합병"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}
		//"Add" 상수가 정의될 때 Menu("삽입") 생성자가 호출되어 message 필드가 "삽입"으로 초기화
		//생성자는 각 상수가 정의될 때 호출되며, 해당 상수의 message 필드를 초기화하는 역할
		//enum 상수가 언제 정의되는가를 찾아 보아야 한다 
		Menu(String string) { // 생성자(constructor)가 언제 호출되는지 파악하는 것이 필요하다 
			message = string;
			System.out.println("\nMenu 생성자 호출:: " + string);
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				//n%3은 3으로 나누어 나머지를 계산한다 
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())//메뉴 출력시에 다음행에 출력하라는 의미
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();//메뉴 선택 번호로 입력된 값이 key이다 
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());//입력된 key가 음수이거나 Exit에 대한 enum 숫자보다 크면 다시 입력받는다 
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴 참조 변수일 뿐이다 
		Random rand = new Random(11);
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		System.out.println("추가할 난수 숫자 개수::");
		int count = sc.nextInt(); //난수 생성 갯수

		int data = 0;
		do {
			switch (menu = SelectMenu()) {//Menu 생성자 호출 - menu 객체를 리턴한다 
			case Add: // 난수를 삽입하는데 올림차순으로 정렬되도록 구현
				for (int i = 0; i < count; i++) {
					data = rand.nextInt(100);
					l.Add(data);
					System.out.println("== 삽입 완료 ==");
				}
				break;
			case Delete:
				System.out.println("삭제할 값을 입력: ");
				data = sc.nextInt();
				boolean tag = l.Delete(data);
				System.out.println("삭제 데이터 존재여부: " + tag);
				break;
			case Show: //리스트 전체를 출력
				l.Show();
				break;
			case Search: //입력 숫자 n을 검색한다.
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (!result)
					System.out.println("검색 값 = " + n + " 데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + n + " 데이터가 존재합니다.");
				break;
			case Merge://리스트 l과 l2를 합병하여 올림차순 정렬이 되게 구현한다 
				LinkedList1 l2 = new LinkedList1();
				for (int i = 0; i < count; i++) {
					data = rand.nextInt(20);
					l2.Add(data);
				}
				System.out.println("리스트 l::");
				l.Show();
				System.out.println("리스트 l2::");
				l2.Show();
				l.Merge(l2);//merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
				System.out.println("병합 리스트 l::");
				l.Show();
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}

