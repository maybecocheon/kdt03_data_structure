package 자료구조_과제;
/*
 * 6장 구현과제3
 */

// 이 문제 연결리스트로 해결 가능 혹은 arraylist

public class train_실습6_03_Merge정렬다항식 {
	// --- 배열 요소 p[idx1]와 p[idx2]의 값을 교환 ---//
	static void merge(Term[] p, int lefta, int righta, int leftb, int rightb ) {
		//body를 지우고 작성 훈련 연습이 도움이 된다 
		Term temp[] = new Term[30];
		//구현코드
		int i = lefta,j = leftb,k=0;
	}
	
	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Term[] p, int left, int right) {
		// 지수를 기준으로 오름차순
		int mid = (left + right) / 2;
		if (p[left].exp > p[right].exp) {
			
		}
	}
	static void ShowPolynomial(String msg, Term[] x, int count) {
		//str 변수는 다항식 이름으로 스트링이다
		//count가 -1이면 항의 숫자를 다항식 x의 length로 계산하고, -1이 아니면 count가 다항식 항의 숫자이다 
		//정렬후 다항식 x = 2.5x**7  + 3.8x**5  + 3.1x**4  + 1.5x**3  + 3.3x**2  + 4.0x**1  + 2.2x**0 
		System.out.println("\n" + msg+ " : ");

		//구현코드
		int n = (count == -1? x.length : count);
		for (int i = 0; i < n; i++) {
			if (i < n - 1 && i != 0) {
				System.out.print(x[i] + " + ");
			} else if (i == n - 1) {
				System.out.print(x[i]);
			}
		}
	}
	static int AddPolynomial(Term[]p1,Term[]p2,Term[]result) {
		//result = p1 + p2, 다항식 덧셈 결과를 result로 주고 result의 항의 수 terms을 리턴한다 
		int p=0,q=0,r=0;
		int terms = 0;
		//구현코드
	

	}
	static int addTerm(Term[]p, Term term, int currentTerms) {
		//다항식 p에 새로운 항 term을 추가한다. 지수가 같은 항이 있으면 계수만 합한다
		//추가된 항의 숫자를 리턴한다. 마지막 배열에 추가하므로 정렬이 되지 않는다.
		//구현코드


	}
	

	static int MultiplyPolynomial(Term[]p1,Term[]p2,Term[]result) {
		//result = p1 * p2, 다항식 result의 항의 수는 terms으로 리턴한다 
		//terms = addTerm(result, term, terms);사용하여 곱셈항을 추가한다.
		
	}
	static double EvaluatePolynomial(Term[]p, int pTerms, double value) {
		//pTerms는 다항식 p의 항의 수, value는 f(x)를 계산하기 위한 x 값
		//다항식 계산 결과를 double로 리턴한다 
		double result = 0.0;
		//구현 코드

	}
	public static void main(String[] args) {
		Term[] p1 = {
				new Term(1.5, 3),
				new Term(2.5, 7),
				new Term(3.3, 2),
				new Term(4.0, 1),
				new Term(2.2, 0),
				new Term(3.1, 4),
				new Term(3.8, 5),
		};
		Term[] p2 = {
				new Term(1.5, 1),
				new Term(2.5, 2),
				new Term(3.3, 3),
				new Term(4.0, 0),
				new Term(2.2, 4),
				new Term(3.1, 5),
				new Term(3.8, 6),
		};
		int nx = p1.length;


		ShowPolynomial("다항식 p1 = ", p1, -1);
		ShowPolynomial("다항식 p2 = ", p2, -1);
		MergeSort(p1, 0, p1.length - 1); // 배열 p1를 퀵정렬
		MergeSort(p2, 0, p2.length - 1); // 배열 p1를 퀵정렬
//		ShowPolynomial("정렬후 다항식 p1 = ", p1, -1);
//		ShowPolynomial("정렬후 다항식 p2 = ", p2, -1);
//
//		Term[] result = new Term[20];
//
//		for (int i =0; i < result.length; i++)
//			result[i] = new Term();
//
//		int resultTerms = AddPolynomial(p1,p2,result);//다항식 덧셈 result = p1 + p2
//		ShowPolynomial("덧셈후 다항식 result = ", result, resultTerms);
//		
//		Term[] result2 = new Term[30];
//
//		resultTerms = MultiplyPolynomial(p1,p2,result2);//다항식 곱셈 result = p1 * p2
//		MergeSort(result2, 0, resultTerms-1); // 배열 p1를 퀵정렬
//		ShowPolynomial("곱셈후 다항식 result = ", result2, resultTerms);
//		
//		double resultValue = EvaluatePolynomial(result2, resultTerms, 1.5);//다항식 값 계산 함수 z(10) 값 계산한다 
//		System.out.println("\n result = " + resultValue );
	}
}

