package practice;

//knight tour에서 다음 이동할 위치
//움직일 위치 정보를 저장하기 위한 클래스
public class Offset {
	// 행과 열 필드
	int row;
	int col;
	
	// 생성자
	public Offset(int row, int col) {
		this.row = row;
		this.col = col;
	}
}
