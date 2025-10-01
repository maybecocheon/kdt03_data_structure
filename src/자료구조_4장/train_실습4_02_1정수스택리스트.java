package 자료구조_4장;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 실습 4_2번 실습으로 배열 대신에 ArrayList을 사용한 구현하기 
 * 교재에 있는 소스코드
 * 입력하여 실행 실습
 * 정수형 스택 리스트
 * 객체스택과 큐에 대한 구현도 정수 스택의 예외처리 방식을 반복 적용함 
 */

import java.util.Scanner;

//int형 고정 길이 스택

class IntStack4 {
	private List<Integer> stk; // 스택용 리스트
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	//--- 생성자(constructor) ---//
	public IntStack4(int maxlen) {
	//추가
		this.capacity = maxlen;
		stk = new ArrayList<>(capacity);
		top = 0;
		try {
		//추가 => 리스트 만들 때 메모리 부족하면 예외 발생
			stk = new ArrayList<>(capacity);
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

//--- 실행시 예외: 스택이 비어있음 ---//
	public class EmptyIntStackException extends RuntimeException {
//추가 => 생성자 생성
		public EmptyIntStackException(String str){
			super(str);
		}
	}

/*
public class RuntimeException extends Exception {
// 생성자 중 하나: 메시지를 받는 생성자
public RuntimeException(String message) {
    // 부모 클래스인 Throwable 클래스의 생성자 호출
    super(message);
}
}
*/
//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowIntStackException extends RuntimeException {
//추가 => 생성자 생성
		public OverflowIntStackException(String str){
			super(str);
		}
	}

//--- 스택에 x를 푸시 ---//
	public void push(int x) throws OverflowIntStackException {
		if (isFull()) { // 스택이 가득 참
			throw new OverflowIntStackException("push: stack overflow");
//추가 => 스택 top(값 넣는 부분) 자리에 인수 x 넣어 주고 top은 1 증가
		} else {
			//stk.get(top) = x; => 왼쪽이 변수 아님
			stk.add(top, x);
			top++;
		}
	}

//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public int pop() throws EmptyIntStackException {
		if (isEmpty()) {// 스택이 빔
			throw new EmptyIntStackException("pop: stack empty");
//추가 => 스택 top 자리에서 값 들고 와서 변수에 저장한 뒤 해당 값 지우고 top은 1 감소
		} else {
			int n = stk.get(top - 1); //top은 개수지 인덱스가 아니기 때문에 -1 해 줌
			stk.remove(top - 1);
			top--;
			return n;
		}
	}

//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public int peek() throws EmptyIntStackException {
		if (isEmpty()) { // 스택이 빔
			throw new EmptyIntStackException("peek: stack empty");
//추가 => top 자리에 있는 값 반환하기 => top은 인덱스가 아니라 요소의 개수이므로 -1 해 줘야 함
		} else {
			return stk.get(top - 1);
		}
	}

//--- 스택을 비움 ---//
	public void clear() throws EmptyIntStackException {
		/*
		 * stack을 empty로 만들어야 한다.
		 * stack이 empty일 때 clear()가 호출된 예외 발생해야 한다 
		 * pop()으로 구현하지 않고 대신에 while 문으로 remove()를 반복 실행한다
		 */
		if (isEmpty()) { // 스택이 빔
			throw new EmptyIntStackException("peek: stack empty");
//추가 => top가 0이 되면 스택이 비었다는 뜻
		} else {
			while (top > 0) {
				stk.remove(top - 1);
				top--;
			}
		}
	}

//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(int x) {
//추가 => 반복문 돌려서 x와 stk 요소의 값 비교한 뒤 값 반환하기
		int n = -1;
		for (Integer num : stk) {
			if (num == x) {
				n = stk.indexOf(num);
			} else {
				n = -1;
			}
		} return n;
	}

//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		//추가
		return capacity;
	}

//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
	//추가
		return top;
	}

//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		//추가
		if (top <= 0) {
			return true;
		} else {
			return false;
		}
	}

//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		//추가
		if (top >= capacity) {
			return true;
		} else {
			return false;
		}
	}
	
//--- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
	public void dump() throws EmptyIntStackException{
		if (isEmpty()) {
			System.out.println("스택이 비어있습니다.");
			throw new EmptyIntStackException("peek: stack empty");
		}
		else {
			//추가할 부분 => for 확장문으로 스택 안 데이터 하나씩 꺼내기
			for (Integer num : stk) {
				System.out.print(num + " ");
			}
		}
	}
}

public class train_실습4_02_1정수스택리스트 {

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntStack4 s = new IntStack4(64); // 최대 64 개를 푸시할 수 있는 스택
		Random rnd = new Random();
		while (true) {
			System.out.println(); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(5)clear  (0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			int x;
			switch (menu) {

			case 1: // 푸시
				System.out.print("데이터: ");
				x = rnd.nextInt(10);
				try {
					s.push(x);
					System.out.print(x);
				} catch (IntStack4.OverflowIntStackException e) {
					System.out.println("스택이 가득 찼습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 2: // 팝
				try {
					x = s.pop();
					System.out.println("팝한 데이터는 " + x + "입니다.");
				} catch (IntStack4.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 3: // 피크
				try {
					x = s.peek();
					System.out.println("피크한 데이터는 " + x + "입니다.");
				} catch (IntStack4.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 4: // 덤프
				try {
					s.dump();
				} catch (IntStack4.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
			case 5: //clear
				try {
					s.clear();
				} catch (IntStack4.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
				
			}
		}
	}
}
