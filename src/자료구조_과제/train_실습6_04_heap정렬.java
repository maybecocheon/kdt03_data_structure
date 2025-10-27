package 자료구조_과제;

//heap의 full, empty에 대한 예외처리 구현 권장 
import java.util.Random;
// import java.util.Scanner;

interface Heap {
	public void insert(int x);
	public int deleteMax();
}

class MaxHeap implements Heap {
	final int heapSize = 100;
	private int[] heap; // data를 넣는 힙 배열
	private int n; // MaxHeap의 현재 입력된 element 개수
	private int MaxSize; // Maximum allowable size of MaxHeap
	
	public MaxHeap(int sz) {
		// 속성은 초기화 꼭 해 주기
		MaxSize = sz;
		n = 0;
		heap = new int[MaxSize + 1];
	}

	public void display() {//heap 배열을 출력한다. 배열 인덱스와 data[]의 값을 출력한다.
		int i;
		System.out.println("힙 배열 상태: ");
		for (i = 1; i <= n; i++) {
			System.out.print("[" + i + "] : " + heap[i] + " ");
		}
		System.out.println();
	}
	
	@Override
	public void insert(int x) {//max heap이 되도록 insert한다. 삽입후 complete binary tree가 유지되어야 한다.
		int i;
		if (n == MaxSize) {
			heapFull();
			return;
		}
		n++;
		// 새 원소를 마지막 위치에 추가 후 위로 올라가면서 정렬
		for (i = n; i > 1;) {
			int parent = i / 2; // 부모 노드 인덱스
			if (x <= heap[parent]) { // 부모가 더 크거나 같으면 종료
				break;
			}
			heap[i] = heap[parent];
			i = parent;
		}
		heap[i] = x;
	}
	
	@Override
	public int deleteMax() {//heap에서 가장 큰 값을 삭제하여 리턴한다. 
		int x;
		int i, j;
		if (n == 0) {
			heapEmpty();
			int elm = 0; //인덱스 0 안 쓰기 때문에 가능
			return elm;
		}
		x = heap[1];
		int temp = heap[n]; //마지막 원소를 임시 저장
		n--;
		
		// 마지막 원소를 적절한 위치로 내려보냄
		i = 1; //루트
		j = 2; //왼쪽
		
		while (j <= n) {
			// 오른쪽이 존재하고 더 크면 오른족 선택
			if (j < n && heap[j] < heap[j  + 1]) {
				j++;
			}
			// 마지막 원소가 자식보다 크거나 같으면 종료
			if (temp >= heap[j]) {
				break;
			}
			heap[i] = heap[j];
			i = j;
			j = 2 * i;
			// 자식을 위로 올림
		}
		heap[i] = temp;
		return x;
	}

	private void heapEmpty() {
		System.out.println("Heap Empty");
	}

	private void heapFull() {
		System.out.println("Heap Full");
	}
}
public class train_실습6_04_heap정렬 {
	 static void showData(int[] d) {
		 for (int i = 1; i < d.length; i++) {
		    	System.out.print(d[i] + " ");
		    }
		 System.out.println();
	 }
	public static void main(String[] args) {
		/* <프로그램 구조>
		 * 데이터 호출
		 * 데이터 삽입 
		 * 데이터 삽입 출력하여 확인
		 * 데이터 처리(프로세스)
		 * 데이터 처리 출력하여 확인
		 */
		Random rnd = new Random(42);
//		int select = 0;
//		Scanner stdIn = new Scanner(System.in);
		MaxHeap heap = new MaxHeap(20);
	    final int count = 10; //난수 생성 갯수
	    int[] x = new int[count + 1]; //x[0]은 사용하지 않으므로 11개 정수 배열을 생성한다 
	    int []sorted = new int[count]; //heap을 사용하여 deleted 정수를 배열 sorted[]에 보관후 출력한다 => heap을 사용해서 정렬
	    
	    System.out.println("== 1단계: 난수 생성 및 힙 삽입 ==");
	    for (int i = 1; i <= count; i++) {
	    	x[i] = rnd.nextInt(100); 
	    }
	    showData(x);
	    
	    for (int i = 1; i <= count; i++) {
	    	heap.insert(x[i]);
	    	System.out.println(x[i] + " 삽입");
	    }
	    System.out.println("힙에 추가 완료");
	    
	    System.out.println("== 2단계: 힙 상태 출력 ==");
	    heap.display();
	    
	    System.out.println("== 3단계: 힙 정렬 ==");
	    System.out.println("deletMax를 반복 호출하여 정렬");
	    for (int i = 0; i < count; i++) {
	    	sorted[i] = heap.deleteMax();
	    	System.out.println("삭제된 최대값: " + sorted[i]);
	    }
	    
	    System.out.println("== 4단계: 확인 ==");
	    for (int i = 0; i < count; i++) {
	    	System.out.print(sorted[i] + " ");
	    }
	    System.out.println("\n== 종료 ==");

//		do {
//			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
//			select = stdIn.nextInt();
//			switch (select) {
//			case 1://난수를 생성하여 배열 x에 넣는다 > heap에 insert한다.
//
//			     showData(x);
//				break;
//			case 2:	//heap 트리구조를 배열 인덱스를 사용하여 출력한다.
//				heap.display();
//				break;
//			case 3://heap에서 delete를 사용하여 삭제된 값을 배열 sorted에 넣는다 > 배열 sorted[]를 출력하면 정렬 결과를 얻는다 
//	
//				break;
//
//			case 4:
//				return;
//
//			}
//		} while (select < 5);
//
//		return;
	}
}


