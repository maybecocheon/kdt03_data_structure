package 자료구조_과제;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

//내가 만든 큐로 풀고
//큐 인터페이스 활용해 보고
//큐-연결리스트 활용

//(작업 이름, 시간)을 형식으로 한 작업 클래스 생성
class Operation {
	private String name;
	private int time;
	
	//생성자 생성
	public Operation (String name, int time) {
		this.name = name;
		this.time = time;
	}
	
	//게터, 세터 생성
	public String getName() {
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
class TQueue implements Queue<Operation> {
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

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean add(Operation e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offer(Operation e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Operation remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation peek() {
		// TODO Auto-generated method stub
		return null;
	}
}

public class train_실습4_05_작업대기시간_시뮬레이션_queue_interface {	
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
		int time = rd.nextInt(1, 100);
		Operation opt = new Operation("A", time);
		tqueue.enque(opt);
		System.out.println("배치된 작업은 " + opt + "입니다.");
		
		time = rd.nextInt(1, 100);
		opt = new Operation("B", time);
		tqueue.enque(opt);
		System.out.println("배치된 작업은 " + opt + "입니다.");
		
		time = rd.nextInt(1, 100);
		opt = new Operation("C", time);
		tqueue.enque(opt);
		System.out.println("배치된 작업은 " + opt + "입니다.");
		
		time = rd.nextInt(1, 100);
		opt = new Operation("D", time);
		tqueue.enque(opt);
		System.out.println("배치된 작업은 " + opt + "입니다.");
		
		
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
