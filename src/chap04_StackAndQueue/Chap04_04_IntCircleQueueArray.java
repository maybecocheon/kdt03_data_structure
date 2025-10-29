package chap04_StackAndQueue;
/*
 * 실습 4_5번 - 정수 배열 원형 큐
 * 교재 148 ~ 157 페이지
 * 교재 소스 코드를 보지 않고 구현 완성 연습 필요 
 * /*
 * num 변수를 사용하지 않고 front == rear 일 때 queue가 full인지 empty 인지를 판단
 * 큐에서는 예외 클래스를 만드는 연습
 */
 
import java.util.Random;
/*
 * 큐 1번 실습 코드 - 정수들의 큐
 */
import java.util.Scanner;

import chap04_StackAndQueue.IntQueue3.EmptyIntQueue3Exception;
import chap04_StackAndQueue.IntQueue3.OverflowIntQueue3Exception;

//int형 고정 길이 큐

class IntQueue3 {
	private int[] que; // 큐용 배열 => ArrayList로 구현 안 됨, ArrayList는 자동 사이즈 조절하기 때문
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서 -> 값을 빼는 쪽
	private int rear; // 맨 끝 요소 커서 -> 값을 넣는 쪽
	boolean isEmptyTag; // 책에서는 int num 쓰고 있음, 더 간단할 것 같아서 isEmptyTag 쓰라고 하심
	//private int num; // 현재 데이터 개수>> 삭제한 후에 queue가 full, empty를 구분하는 실습
	//enque 하기전에 갯수를 세어 front==rear 조건을 체크한다
	//deque도 마찬가지임 
	
//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyIntQueue3Exception extends RuntimeException {
		public EmptyIntQueue3Exception() {
			super();
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowIntQueue3Exception extends RuntimeException {
		public OverflowIntQueue3Exception() {
			super();
		}
	}

//--- 생성자(constructor) ---//
	public IntQueue3(int maxlen) {
		//인수를 받아서 큐 배열 생성
		que = new int[maxlen];
		//총 용량은 큐 배열의 길이
		capacity = maxlen;
		//프론트와 리어는 현재 0
		front=rear = 0;
		//지금 비어 있느냐는 참
		isEmptyTag = true;
	}

//--- 큐에 데이터를 인큐 ---//
	public boolean enque(int x) throws OverflowIntQueue3Exception {
		//rear와 front의 값이 같고 isEmptyTag가 false면 오버플로우 예외 발생
		//하나라도 넣었으면 isEmptyTag가 false인 걸로 바꿔 주기
		if (isFull()) {
			throw new OverflowIntQueue3Exception();
		} else {
			//isEmptyTag가 true거나 rear와 front의 값이 다를 때
			que[rear % capacity] = x;
			rear++;
			isEmptyTag = false;
			return true;
		}
	}

//--- 큐에서 데이터를 디큐 ---//
	public int deque() throws EmptyIntQueue3Exception {
		//rear와 front의 값이 같고 isEmptyTag가 true면 엠프티플로우 예외 발생
		if (isEmpty()) {
			throw new EmptyIntQueue3Exception();
		} else {
			//rear와 front의 값이 같지 않거나 isEmptyTag가 false일 때
			int n = que[front % capacity];
			front++;
			if (rear == front) {
				isEmptyTag = true;
			} return n;
		}
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public int peek() throws EmptyIntQueue3Exception {
		if (isEmpty()) {
			throw new OverflowIntQueue3Exception();
		} else {
			return que[front % capacity];
		}
	}

//--- 큐를 비움 ---//
	public void clear() {
		/*
		 * queue을 empty로 만들어야 한다.
		 * queue이 empty일 때 clear()가 호출된 예외 발생해야 한다 
		 */
		if (isEmpty()) {
			throw new OverflowIntQueue3Exception();
			//둘 다 0이 되면 논리적으로 이 큐는 빈 거임
		} else {
			rear=front = 0;
		}
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(int x) {
		int pl = front % capacity;
		int pr = rear % capacity;
		
		for (int i = pl; i < pr; i++) {
			int pc = (pl + pr) / 2;
			if (Integer.compare(que[pc], x) == 0) {
				return pc;
			} else if (Integer.compare(que[pc], x) < 0) {
				pl = pc + 1;
			} else {
				pr = pc - 1;
			}
		} return -1;
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		return rear - front;
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		if ((front % capacity) == (rear % capacity) && isEmptyTag == true) {
			return true;
		} return false;
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		if ((front % capacity) == (rear % capacity) && isEmptyTag == false) {
			return true;
		} return false;
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		for (int i = front % capacity; i <= (rear - 1) % capacity; i++) {
			System.out.printf("%d\t", que[i]);
		}
	}
}
public class Chap04_04_IntCircleQueueArray {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntQueue3 oq = new IntQueue3(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, p = 0;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");
			int menu = stdIn.nextInt();
			
			// 확장문 빠져 나가는 종료 메뉴
			if (menu == 0) {
				System.out.println("종료되었습니다.");
				break;
			}
			
			switch (menu) {
			case 1: // 인큐
				rndx = random.nextInt(20);
				try {
					oq.enque(rndx);
					System.out.print("입력데이터: (" + rndx +")");
				} catch(OverflowIntQueue3Exception e) {
					System.out.println("큐가 가득 차 있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (EmptyIntQueue3Exception e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (EmptyIntQueue3Exception e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 4: // 덤프
				oq.dump();
				break;
			default:
				break;
			}
		}
	}

}
