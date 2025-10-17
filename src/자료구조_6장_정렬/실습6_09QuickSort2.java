package 자료구조_6장_정렬;

import java.util.Stack;

//stack 1개를 사용한 non-recursve QuickSort() 구현

class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}
}
public class 실습6_09QuickSort2 {
//퀵 정렬(비재귀 버전)

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void swap(int[] a, int idx1, int idx2) {
		int t = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = t;
	}

	// --- 퀵 정렬(비재귀 버전)---//
	//1. (left, right) 구간을 스택에 push
	//2. 스택에서 꺼내서 피벗 기준으로 분할
	//3. 왼쪽/오른쪽 부분배열 구간을 다시 push
	//4. 스택이 빌 때까지 반복
	static void quickSort(int[] a, int left, int right) {
        Stack<Point> st = new Stack<>();//java API의 Stack 클래스를 사용

		Point pt = new Point(left, right);
		st.push(pt);
		
		while (!st.isEmpty()) {
			Point point = st.pop();
			int pl = point.getX();         // 왼쪽 커서
		    int pr = point.getY();        // 오른쪽 커서
		    int mid = a[(pl + pr) / 2];      // 피벗(가운데 요소)
			
			while(pl <= pr) {
				//중앙에 있는 요소보다 작으면 그대로 두기
				while (a[pl] < mid) {
					pl++;
				}
				//중앙에 있는 요소보다 크면 그대로 두기
				while (a[pr] > mid) {
					pr--;
				}
				//그렇지 않으면 left, right에 있는 각각의 요소 자리 변경하기
				if (pl <= pr) {
					swap(a, pl++, pr--);
				}
			}
			// 분할 완료 후, 양쪽 부분 배열을 스택에 push
			// 0부터 pr까지
			if (point.getX() < pr) {
				st.push(new Point(point.getX(), pr));
			}
			// pl부터 a.length - 1까지
			if (pl < point.getY()) {
				st.push(new Point(pl, point.getY()));
			}
		}
	}

	public static void main(String[] args) {
		int nx = 10;
		int[] x = new int[10];
		for (int ix = 0; ix < 10; ix++) {
			double d = Math.random();
			x[ix] = (int) (d * 20);
		}
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
		System.out.println();

		quickSort(x, 0, nx - 1); // 배열 x를 퀵정렬

		System.out.println("오름차순으로 정렬했습니다.");
		for (int i = 0; i < nx; i++)
			System.out.print(" " + x[i]);
	}
}

