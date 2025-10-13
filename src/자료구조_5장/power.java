package 자료구조_5장;

public class power {
	// 기저 조건: 재귀 함수가 더 이상 자신을 호출하지 않고 종료되도록 하는, 즉 재귀 호출을 멈추는 조건
	// n == 0, m == 0
	// n != 0, m == 0
	
	static public int power(int n, int m) {
		// n과 m 값이 올바른지 확인해야 함
		// 재귀에서는 기저조건이 선두에 와야 함
		
		// 1. 잘못되었을 때 반환(종료)
		if (m < 0) {
			throw new IllegalArgumentException("지수는 음수가 될 수 없습니다.");
			// 에러이기 때문에 숫자가 나오는 결과를 반환하면 안 됨
		}
	
		// 2. 기저조건에 따른 반환
		if (m == 0) {
			return 1;
		}
		
		// 3. 계산식
		// 4. 반환
		return n * power(n, m - 1);
	}
	
	public static void main(String[] args) {
		System.out.println(power(2, 5));
	}
}
