package 자료구조_과제;

//doubly circular linked list
// 이중 연결리스트 =>

/*
 * 헤드 노드가 있는 원형 리스트, 헤드 노드가 없는 원형 리스트 구현
 * merge 구현: in-place 구현, 새로운 노드를 생성하여 합병 구현 
 * 원형 이중 리스트로 동일하게 적용
 */
import java.util.Comparator;
//import java.util.Scanner;

class SimpleObject {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?
	String no; // 회원번호
	String name; // 이름
	String expire;//  유효기간 필드를 추가

	public SimpleObject(String sno, String sname, String expire) {
		this.no = sno;
		this.name = sname;
		this.expire = expire;
	}
	public SimpleObject() {
		this.no = null;
		this.name = null;
	}
	// --- 문자열 표현을 반환 ---//
	@Override
	public String toString() {
		return "(" + no + ") " + name;
	}
	// --- 데이터를 읽어 들임 ---//
//	void scanData(String guide, int sw) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println(guide + "할 데이터를 입력하세요."+ sw);
//
//		if ((sw & NO) == NO) { //& 는 bit 연산자임
//			System.out.print("번호: ");
//			no = sc.next();
//		}
//		if ((sw & NAME) == NAME) {
//			System.out.print("이름: ");
//			name = sc.next();
//		}
//	}
	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject> {
		@Override
		public int compare(SimpleObject d1, SimpleObject d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : ((d1.no.compareTo(d2.no) < 0)) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject> {
		@Override
		public int compare(SimpleObject d1, SimpleObject d2) {
			return (d1.name.compareTo(d2.name) > 0) ? 1 : ((d1.name.compareTo(d2.name) < 0)) ? -1 : 0;
		}
	}
}

class Node4 {
	SimpleObject data; // 데이터
	Node4 llink; // 좌측포인터(앞쪽 노드에 대한 참조)
	Node4 rlink; // 우측포인터(뒤쪽 노드에 대한 참조)

}

class DoubledLinkedList {
	private Node4 first; // 머리 포인터(참조하는 곳은 더미노드)

	// --- 생성자(constructor) ---//
	public DoubledLinkedList() {
		first = new Node4(); // dummy(first) 노드를 생성 => first는 head와 동일한 의미
		first.llink = first;
		first.rlink = first;
	}
	
	// --- 올림차순으로 정렬이 되도록 insert ---//
	public void add(SimpleObject obj, Comparator<? super SimpleObject> c) {
		Node4 newNode = new Node4();
		newNode.data = obj;
		
		Node4 ptr = first.rlink;
		Node4 prev = first;
		
		// 추가되는 위치 찾기 (오름차순 정렬 유지)
		while (ptr != first && c.compare(ptr.data, obj) < 0) {
			prev = ptr;
			ptr = ptr.rlink;
		}
		
		//newNode를 prev와 ptr 사이에 추가
		newNode.rlink = ptr;
		newNode.llink = prev;
		prev.rlink = newNode;
		ptr.llink = newNode;
	}
	
	// --- 리스트가 비어있는가? ---//
	// 원형임을 상징
	public boolean isEmpty() {
		return first.rlink == first;
	}

	// --- 노드를 검색 ---//
	public boolean search(SimpleObject obj, Comparator<? super SimpleObject> c) {
		// 첫 번째 노드부터 검색
		Node4 ptr = first.rlink;
		
		// 순회하면(first 만나면) 검색 종료
		while (ptr != first) {
			if (c.compare(ptr.data, obj) == 0) {
				System.out.println("검색 성공: " + ptr.data);
				return true;
			}
			ptr = ptr.rlink;
		}
		// 만약에 찾는 데이터 있으면 true 반환 => 메서드 종료
		return false;
	}

	// --- 전체 노드 표시 ---//
	public void show() {
		Node4 ptr = first.rlink;
		if (isEmpty()) {
			System.out.println("리스트가 비어 있습니다.");
			return;
		}
		System.out.println("리스트 내용: ");
		while (ptr != first) {
			System.out.print(ptr.data + " -> ");
			ptr = ptr.rlink;
		}
		System.out.println("처음으로");
	}

	// --- list에 삭제할 데이터가 있으면 해당 노드를 삭제 ---//
	public void delete(SimpleObject obj, Comparator<? super SimpleObject> c) {
		Node4 ptr = first.rlink;
		
		// 찾아서 연결 끊기
		while (ptr != first) {
			if (c.compare(ptr.data, obj) == 0) { // 여기서 삭제
				ptr.llink.rlink = ptr.rlink;
				ptr.rlink.llink = ptr.llink;
				ptr.rlink = null;
				ptr.llink = null;
				System.out.println("삭제 완료: " + ptr.data);
				return;
			}
			ptr = ptr.rlink;
		}
		System.out.println("삭제할 데이터를 찾지 못했습니다.");
	}
	
	public DoubledLinkedList merge_NewList(DoubledLinkedList lst2, Comparator<SimpleObject> cc) {
		//병합 정렬(merge sort) 활용!
		//l3 = l1.merge(l2); 실행하도록 리턴 값이 리스트임 
		//l.add(객체)를 사용하여 구현
		//기존 리스트의 노드를 변경하지 않고 새로운 리스트의 노드들을 생성하여 구현 
		DoubledLinkedList lst3 = new DoubledLinkedList();
		Node4 ai = this.first.rlink;
		Node4 bi = lst2.first.rlink;



		return lst3;

	}
	void mergeInPlace(DoubledLinkedList b, Comparator<SimpleObject> cc) {
		/*
		 * 연결리스트 a,b에 대하여 a = a + b
		 * merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지 않고 합병하는 알고리즘 구현
		 * 난이도 등급: 최상급
		 * 회원번호에 대하여 a = (3, 5, 7), b = (2,4,8,9)이면 a = (2,3,4,5,8,9)가 되도록 구현하는 코드
		 */
		if (b.isEmpty()) return;
		
		Node4 p = first.rlink; // this 노드
		Node4 prevP = first; // add 하기 위해서
		
		Node4 q = b.first.rlink; // b list 노드
		
		// p와 q의 노드 개수가 같음
		while (p != first && q != b.first) {
			if (cc.compare(p.data, q.data) <= 0) {
				// p가 q보다 작거나 같으면 => p는 그대로 두고 다음으로 진행한다
				prevP = p;
				p = p.rlink;
			} else {
				// p가 q보다 크면 => q를 p 앞에 추가한다
				Node4 temp = q.rlink;
				
				// b list에서 q 제거
				q.llink.rlink = q.rlink;
				q.rlink.llink = q.llink;
				
				// this list의 prev와 p 사이에 q 추가
				q.llink = prevP;
				q.rlink = p;
				prevP.rlink = q;
				p.llink = q;
				
				prevP = q;
				q = temp;
			}
		}
		
		// 남은 노드 있으면
		if (q != b.first) {
			prevP.rlink = q;
			q.llink = prevP;
			
			Node4 lastB = b.first.llink;
			lastB.rlink = first;
			first.llink = lastB;
		}
		
		b.first.rlink = b.first; // b 리스트 초기화
		b.first.llink = b.first;
		return;
		// 메모리 절약
	}
}

public class train_실습8_06객체이중리스트 {

	public static void main(String[] args) {
		DoubledLinkedList lst1 = new DoubledLinkedList();
		DoubledLinkedList lst2 = new DoubledLinkedList();
		DoubledLinkedList lst3 = new DoubledLinkedList();
		DoubledLinkedList lst4 = new DoubledLinkedList();
		
		System.out.println("== 1. 리스트 추가 (lst1) ==");
		SimpleObject[] simpleObjects = new SimpleObject[10];
		makeSimpleObjects(simpleObjects);
		for (int i = 0; i < simpleObjects.length; i++) {
			lst1.add(simpleObjects[i], SimpleObject.NO_ORDER);
		}
		
		System.out.println("\n== 2. 리스트 출력 (lst1) ==");
		lst1.show();
		
		System.out.println("\n== 3. 리스트 검색 (lst1) ==");
		SimpleObject simpleObject1 = new SimpleObject("s8", "hong", "240618");
		System.out.println("+ 검색할 데이터: " + simpleObject1);
		boolean result1 = lst1.search(simpleObject1, SimpleObject.NO_ORDER);
		if (!result1) {
			System.out.println("데이터가 없습니다.");
		} else {
			System.out.println("데이터가 존재합니다.");
		}
		
		System.out.println("\n== 4. 리스트 삭제 (lst1) ==");
		SimpleObject simpleObject2 = new SimpleObject("s8", null, null);
		System.out.println("+ 삭제할 데이터: " + simpleObject1);
		lst1.delete(simpleObject2, SimpleObject.NO_ORDER);
		
		System.out.println("\n== 5. 리스트 병합 (lst1, lst2, lst3) ==");
		System.out.println("\n== 6. 제자리 병합 (lst1, lst2, lst4) ==");
		SimpleObject[] simpleObjects2 = new SimpleObject[10];
		makeSimpleObjects2(simpleObjects2);
		for (int i = 0; i < simpleObjects2.length; i++) {
			lst2.add(simpleObjects2[i], SimpleObject.NO_ORDER);
		}
		lst1.mergeInPlace(lst2, SimpleObject.NO_ORDER);
		lst1.show();
	}
	
	static void makeSimpleObjects(SimpleObject []simpleobjects) {
        simpleobjects[0] = new SimpleObject("s8", "hong", "240618");
        simpleobjects[1] = new SimpleObject("s2", "kim", "240619");
        simpleobjects[2] = new SimpleObject("s3", "lee", "240601");
        simpleobjects[3] = new SimpleObject("s1", "park", "240621");
        simpleobjects[4] = new SimpleObject("s4", "choi", "240622");
        simpleobjects[5] = new SimpleObject("s6", "jung", "240611");
        simpleobjects[6] = new SimpleObject("s7", "kang", "240624");
        simpleobjects[7] = new SimpleObject("s5", "jo", "240615");
        simpleobjects[8] = new SimpleObject("s19", "oh", "240606");
        simpleobjects[9] = new SimpleObject("s10", "jang", "240607");
 
	}
	static void makeSimpleObjects2(SimpleObject []simpleobjects) {
        simpleobjects[0] = new SimpleObject("s5", "song", "240608");
        simpleobjects[1] = new SimpleObject("s2", "Lim", "240609");
        simpleobjects[2] = new SimpleObject("s3", "kee", "240601");
        simpleobjects[3] = new SimpleObject("s1", "park", "240611");
        simpleobjects[4] = new SimpleObject("s8", "choo", "240612");
        simpleobjects[5] = new SimpleObject("s9", "jong", "240618");
        simpleobjects[6] = new SimpleObject("s4", "jang", "240614");
        simpleobjects[7] = new SimpleObject("s7", "go", "240605");
        simpleobjects[8] = new SimpleObject("s11", "na", "240616");
        simpleobjects[9] = new SimpleObject("s10", "you", "240617");
 
	}
	static void makeSimpleObjects3(SimpleObject []simpleobjects) {
        simpleobjects[0] = new SimpleObject("s5", "song", "240608");
        simpleobjects[1] = new SimpleObject("s2", "Lim", "240609");
        simpleobjects[2] = new SimpleObject("s3", "kee", "240601");
        simpleobjects[3] = new SimpleObject("s1", "park", "240611");
        simpleobjects[4] = new SimpleObject("s8", "choo", "240612");
        simpleobjects[5] = new SimpleObject("s9", "jong", "240618");
        simpleobjects[6] = new SimpleObject("s4", "jang", "240614");
        simpleobjects[7] = new SimpleObject("s7", "go", "240605");
        simpleobjects[8] = new SimpleObject("s11", "na", "240616");
        simpleobjects[9] = new SimpleObject("s10", "you", "240617");
 
	}
}
