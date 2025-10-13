package 자료구조_과제;

import java.util.Random;
import java.util.Scanner;

//내가 만든 큐로 풀고
//큐 인터페이스 활용해 보고
//큐-연결리스트 활용

/* 
 * [라운드 로빈] 문제
 * 작업 시간 난수, 작업 개수 난수
 * 
 
- time slot: 한번에 처리할 수 있는 작업량

문제 예시: timeslot 기반의 작업 scheduling 시스템
문제 설명:
어느 회사에서는 여러 작업이 동시에 들어오며, (리스트에 작업 추가됨)
각 작업의 처리 시간은 난수로 배정된다. (작업 시 처리 시간 5로 고정, 실무에서는 난수로 배정)
각 작업은 주어진 time slot 단위(5)로 나눠서 처리되며,
처리 중인 작업은 완료되지 않았더라도 
타임슬롯이 끝나면 큐의 마지막에 다시 대기해야 한다.
순서대로 돌아가며 타임슬롯을 할당하여 작업을 처리하는 이 시스템에서,
작업이 끝나면 큐에서 제거되고 완료된 시간을 출력한다, (if)
그렇지 않으면 다음 타임슬롯에 다시 처리될 때까지 대기열의 끝으로 이동합니다. (else)

조건:
각 작업에는 고유의 이름과 남은 작업 시간이 주어집니다.
타임슬롯(T)의 크기가 주어집니다.
각 작업은 타임슬롯 단위로 처리되며, 만약 작업이 완료되지 않으면 
남은 시간이 줄어들고 큐의 끝으로 이동합니다.
작업이 완료되면 큐에서 제거됩니다.
모든 작업이 완료될 때까지 반복적으로 처리합니다. (queue가 empty 될 때까지)

입력 형식:
첫 번째 줄에 타임슬롯 크기 T가 주어집니다.
두 번째 줄에는 각 작업의 이름과 남은 시간이 (작업 이름, 시간)의 형식으로 주어집니다.

출력 형식:
각 타임슬롯에서 처리된 작업의 이름과 남은 시간을 출력합니다.
작업이 완료된 경우 "작업 완료"를 출력합니다.

제약 조건:
1 ≤ T ≤ 10
1 ≤ 작업의 개수 ≤ 100
각 작업의 남은 시간은 1 이상 100 이하입니다.
*/

//(작업 이름, 시간)을 형식으로 한 작업 클래스 생성
class Operation {
	private char name;
	private int time;
	
	//생성자 생성
	public Operation (char name, int time) {
		this.name = name;
		this.time = time;
	}
	
	//게터, 세터 생성
	public char getName() {
		return name;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	//toString 오버라이딩
	public String toString() {
		return "(" + name + ", " + time + ")";
	}
}

//T를 크기로 한 원형 큐 생성
//큐는 리스트로 움직임
class TQueue {
	private Operation[] op;
	private int front;
	private int rear;
	private int capacity;
	private int size;
	
	//생성자 생성
	public TQueue(int capacity) {
		front = 0;
		rear = -1;
		size = 0;
		this.capacity = capacity;
		op = new Operation[capacity];
	}
	
	//게터 생성
	public Operation[] getOp() {
		return op;
	}
	public int getRear() {
		return rear;
	}
	public int getFront() {
		return front;
	}
	public int getCapacity() {
		return capacity;
	}
	
	//큐가 비어 있음
	public boolean isEmpty() {
		return size == 0;
	}
	
	//큐가 가득 차 있음
	public boolean isFull() {
		return size == capacity;
	}
	
	
	//인큐
	public void enque(Operation opt) {
		if (isFull()) {
			System.out.println("큐가 가득 찼습니다.");
			return;
		}
		rear++;
		op[rear % capacity] = opt;
		size++;
	}
	
	//디큐
	public Operation deque() {
		if (isEmpty()) {
			return null;
		}
		Operation o = op[front % capacity];
		front++;
		size--;
		return o;
	}
}

public class train_실습4_05_작업대기시간_시뮬레이션 {	
	static public void main(String[] args) {
		// Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		
		//첫 번째 줄에 타임슬롯 크기 T 주어짐
		System.out.println("타임슬롯(T)의 값을 입력하세요. (1 이상 10 이하)");
		int T = 5;
		System.out.println(T);
		
		//작업 개수 난수 생성, 큐 생성
		// int count = rd.nextInt(1, 100);
		int count = 4;
		TQueue tqueue = new TQueue(count);
	
		//두 번째 줄에 각 작업의 이름과 남은 시간이 (작업 이름, 시간)의 형식으로 주어집니다.		
		//큐에 작업이 배치됨 => 인큐
		// System.out.println("각 작업의 이름을 입력하세요.");
		char name = 'A';
		int time;
		for (int i = 0; i < 4; i++) {
			time = rd.nextInt(1, 100);
			Operation opt = new Operation((char)(name + i), time);
			tqueue.enque(opt);
			System.out.println("배치된 작업은 " + opt + "입니다.");
		}
		
		
		int currentTime = 0;
		while (!tqueue.isEmpty()) {
			Operation currentOpt = tqueue.deque(); //현재 작업 꺼내기
			int processTime = Math.min(T, currentOpt.getTime());
			currentOpt.setTime(currentOpt.getTime() - processTime);
			currentTime += processTime;
			
			System.out.println("처리 중:" + currentOpt);
			
			if (currentOpt.getTime() == 0) {
				System.out.println("완료: " + currentOpt);
			} else {
				tqueue.enque(currentOpt);
			}
		}
	}
}

