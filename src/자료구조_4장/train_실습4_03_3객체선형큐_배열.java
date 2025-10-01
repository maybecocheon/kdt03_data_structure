package 자료구조_4장;
/*
 * 실습 4_6번 객체 원형 큐를 배열로 구현
 * 교재 148 실습 4_3은 정수 원형 큐를 배열로 구현한 코드임 > 객체 버젼으로 구현
 */

import java.util.Random;
import java.util.Scanner;

/*
* Queue of ArrayList of Point
*/

//큐의 값이 될 클래스
class Point3 {
	private int ix;
	private int iy;

	//생성자 생성
	public Point3 (int rndx, int rndy) {
		ix = rndx;
		iy = rndy;
	}
	
	//게터, 세터 생성
	
}

//int형 고정 길이 큐
class objectQueue2 {
  private Point3[] que;
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서


//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException() {
			super();
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException() {
			super();
		}
	}

//--- 생성자(constructor) ---//
public objectQueue2(int maxlen) {
	que = new Point3[maxlen];
	capacity = maxlen;
	front=rear = 0;
}

//--- 큐에 데이터를 인큐 ---//
	public Point3 enque(Point3 x) throws OverflowQueueException {
		if (isFull()) {
			throw new OverflowQueueException();
		} else {
			return que[rear++] = x;
		}
	}

//--- 큐에서 데이터를 디큐 ---//
	public Point3 deque() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			return que[front++];
		}
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public Point3 peek() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			return que[front - 1];
		}
	}

//--- 큐를 비움 ---//
	public void clear() {
		front = rear = 0;
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		return rear;
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		//front = 0, rear = 0
		//front = capacity, rear = capacity
		if ((front == 0 && rear == 0) || (front == capacity && rear == capacity)) {
			
		}
		
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		//front = 0, rear = capacity

	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		for (int i = 0; i < rear; i++) {
			System.out.printf("(%s, %s)", que[i].);
		}
	}
}
public class train_실습4_03_3객체선형큐_배열 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		objectQueue2 oq = new objectQueue2(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point3 p = null;
		
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(0)종료: ");
			int menu = stdIn.nextInt();
			
			if (menu == 0) {
				break;
			}
			
			switch (menu) {
			case 1: // 인큐

				rndx = random.nextInt(20);
				rndy = random.nextInt(20);
				
				System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
				p = new Point3(rndx, rndy);
				try {
					oq.enque(p);
				} catch(objectQueue2.OverflowQueueException e) {
					System.out.println("stack이 가득 차 있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (objectQueue2.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (objectQueue2.EmptyQueueException e) {
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
