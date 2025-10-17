package 자료구조_과제;
//정렬의 기본 원칙	
// 	- 원소를 선택
//	- 원소를 비교
// 	- 자리를 배치

//분할 정복 알고리즘을 사용한 반복문
// - 순열[a]의 끝에 갈 때까지
// 	- 원소를 선택
//	- 원소를 비교
// 	- 자리를 배치
// - 순열[b]의 끝에 갈 때까지
// 	- 원소를 선택
//	- 원소를 비교
// 	- 자리를 배치
// - 합병(a,b)
//	- 제일 좋은 조건: a.length == b.length
public class sort {
	public static Term[] iMergeSort(Term[] terms) {
		if (terms == null || terms.length <= 1) {
			return terms;
		}
		
		int n = terms.length;
		Term[] temp = new Term[n]; //mergesort의 단점: 시간을 절약하는 대신 공간을 사용함 => 현대에는 그렇게 단점이 안 됨 //어떤 정렬은 공간을 절약하는 대신 시간을 더 씀
		
		//size = 1, n = 5;
		//2개씩 나눠야 해서 2 곱하는 거임
		for (int size = 1; size < n; size *= 2) {
			// left = 0, n = 5
			for (int left = 0; left < n; left += 2 * size) {
				// 0, 0, 4 => 0
				int mid = Math.min(left + size - 1, n - 1);
				// 0, 4 => 0
				int right = Math.min(left + 2 * size - 1, n - 1);
				// terms, temp, 0, 0, 0
				merge(terms, temp, left, mid, right);
			}
		}
		return terms;
	}
	
	private static void merge(Term[] terms, Term[] tmep, int left, int mid, int right) {
		int i = left; //왼쪽 부분의 시작 인덱스
		int j = mid + 1; //오른쪽 부분의 시작 인덱스
		int k = left; // 병합된 배열의 인덱스
		
		//0,0,0
		while (i <= mid && j <= right) {
			
		}
	}
	
	public static void main(String[] args) {
		
	}
}
