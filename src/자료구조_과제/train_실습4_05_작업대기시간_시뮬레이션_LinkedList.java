package 자료구조_과제;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

//내가 만든 큐로 풀고
//큐 인터페이스 활용해 보고
//큐-연결리스트 활용

//(작업 이름, 시간)을 형식으로 한 작업 클래스 생성
class Operation3 {
	private char name;
	private int time;
	
	//생성자 생성
	public Operation3 (char name, int time) {
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
class TQueue3 extends LinkedList<Operation3> {
	private Operation3[] op;
	private int front;
	private int rear;
	private int capacity;
	private int size;
	
	//생성자 생성
	public TQueue3(int capacity) {
		front = 0;
		rear = -1;
		size = 0;
		this.capacity = capacity;
		op = new Operation3[capacity];
	}
	
	//게터 생성
	public Operation3[] getOp() {
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

}

public class train_실습4_05_작업대기시간_시뮬레이션_LinkedList {	
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
		LinkedList<Operation3> tqueue = new TQueue3(count);
	
		//두 번째 줄에 각 작업의 이름과 남은 시간이 (작업 이름, 시간)의 형식으로 주어집니다.		
		//큐에 작업이 배치됨 => 인큐
		// System.out.println("각 작업의 이름을 입력하세요.");
		char name = 'A';
		int time;
		for (int i = 0; i < 4; i++) {
			time = rd.nextInt(1, 100);
			Operation3 opt = new Operation3((char)(name + i), time);
			tqueue.offer(opt);
			System.out.println("배치된 작업은 " + opt + "입니다.");
		}
		
		int currentTime = 0;
		while (!tqueue.isEmpty()) {
			Operation3 currentOpt = tqueue.poll(); //현재 작업 꺼내기
			int processTime = Math.min(T, currentOpt.getTime());
			currentOpt.setTime(currentOpt.getTime() - processTime);
			currentTime += processTime;
			
			System.out.println("처리 중:" + currentOpt);
			
			if (currentOpt.getTime() == 0) {
				System.out.println("완료: " + currentOpt);
			} else {
				tqueue.offer(currentOpt);
			}
		}
	}
}
