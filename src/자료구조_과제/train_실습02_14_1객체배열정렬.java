package 자료구조_과제;
// !!!!!!!!!!!!!!!!!!!!제일 중요!!!!!!!!!!!!!!!!!!!

/*
 * 2장 제출 과제 
 * Comparable 인터페이스의 구현 => 가나다 순 정렬
 * 5번 실습 - 2장 실습 2-10를 수정하여 객체 배열의 정렬 구현
 */

//swap 함수 활용해 보기
//sort 써도 됨
import java.util.Arrays;
import java.util.Comparator;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
//원래 이 클래스는 다른 자바 파일로 만들어 줘야 함
class PhyscData2 implements Comparable<PhyscData2>{
	private String name;
	private int height;
	private double vision;
	
	//인터페이스를 구현한 클래스를 생성하기 위해서는 compareTo 추상 메서드를 구현해야 함
	public int compareTo(PhyscData2 a) {
		return this.name.compareTo(a.name);
	}
	
	//생성자
	PhyscData2(String name, int height, double vision){
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	
	//getter, setter
	public String getName() {
		return name;
	}

	public int getHeight() {
		return height;
	}

	public double getVision() {
		return vision;
	}
/*
	@Override
	public String toString() {
		return "PhyscData2 [name=" + name + ", height=" + height + ", vision=" + vision + "]";
	}
*/
}

public class train_실습02_14_1객체배열정렬 {
	//배열을 출력하는 메서드 생성
	static private void showData(String str, PhyscData2[] data) {
		System.out.println(str);
		System.out.println("-".repeat(30));
		for (PhyscData2 pd : data) {
			//System.out.println(pd.getName() + "\t" + pd.getHeight() + "\t" + pd.getVision());
			System.out.printf("%s\t%d\t%.1f\n", pd.getName(), pd.getHeight(), pd.getVision());
			//toString 오버라이딩 해서 써도 됨
		}
		System.out.println();
	}
	
	static private void sortData(PhyscData2[] data) {
		//배열을 정렬하는 메서드 생성
		//사이즈가 작으면 정렬할 때 return을 하고, 사이즈가 크면 정렬할 때 원데이터(매개변수에 있는 것) 건드려서 void 함
		//기초정렬: 거품, 삽입, 선택 => O(n^2) => 복잡도 너무 큼, 최악! => 이런 건 실무에서 안 씀
		int n = data.length;
		for (int i = 0; i < n - 1; i++) { // 선행자 => 몇 번 반복할지
			for (int j = 0; j < n - 1 - i; j++) { //후행자 => 실제 실행
				// 스캔할수록 뒤쪽은 이미 정렬됨 → 더 이상 비교 안 해도 되기 때문에 n - 1 - i
				// n - 1 하는 건 n으로 두면 j + 1일 때 배열 인덱스 초과 오류 뜨기 때문
				if(data[j].compareTo(data[j + 1]) > 0) {
					//swap
					PhyscData2 temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
		//이진정렬, 퀵정렬, 배합정렬 => O(n log n)
		//Arrays.sort(data, (data1, data2) -> data1.getName().compareTo(data2.getName()));
	}
	
	static int binarySearch(PhyscData2[] data, String name) {
		//단점: 정렬돼 있는 배열에서만 검색 가능
		//배열의 값을 통해 인덱스를 찾는 메서드 생성
		int pl = 0;
		int pr = data.length - 1;
		
		//for문은 찾아지는 횟수를 알 수 있을 때
		//while문은 상태값만으로 
		while(pl <= pr) {
			int pc = (pl + pr) / 2;
			if (data[pc].getName().compareTo(name) < 0) {
				pl = pc + 1;
			} else if(data[pc].getName().compareTo(name) > 0) {
				pr = pc - 1;
			} else {
				return pc;
			}
		}
		return -1;
	}
	
	static PhyscData2[] insertObject(PhyscData2[] data, PhyscData2 a) {
		//배열을 insert하는 메서드 생성
		PhyscData2[] newData = new PhyscData2[data.length + 1];
		//새로운 배열의 인덱스로 쓸 변수 생성
		int idx = 0;
		//name의 값대로 정렬되어 있으니 name 값을 비교하기
		for (int i = 0; i < data.length; i++) {
			if (data[i].getName().compareTo(a.getName()) >= 0){
				idx = i;
				newData[idx] = a;
				break;
			}
		}
		//idx보다 작은 i를 인덱스로 하는 data 배열은 그대로 arr에 넣고
		for (int i = 0; i < idx; i++) {
			newData[i] = data[i];
		}
		
		//idx보다 큰 i를 인덱스로 하는 data 배열은 i + 1 해서 arr에 넣기
		for (int i = idx; i < data.length; i++) {
			newData[i + 1] = data[i];
		}
		
		return newData;
	}
	
	public static void main(String[] args) {
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("홍동", 164, 1.3),
				new PhyscData2("홍길", 152, 0.7),
				new PhyscData2("김홍길동", 172, 0.3),
				new PhyscData2("이길동", 182, 0.6),
				new PhyscData2("박길동", 167, 0.2),
				new PhyscData2("최길동", 169, 0.5),
		};
		showData("정렬전",data);
		sortData(data);
		showData("정렬후", data);
		
		//키를 기준으로 정렬
		//compareTo()가 필요하다
		//빼기 하지 말기 절대!!
		Arrays.sort(data, (d1, d2)->Integer.compare(d1.getHeight(), d2.getHeight()));
		showData("키 기준으로 Arrays.sort() 실행후", data);
		
		//시력을 기준으로 정렬
		//익명 클래스 생성
		Arrays.sort(data, new Comparator<PhyscData2>() {
			@Override
			public int compare(PhyscData2 d1, PhyscData2 d2) {
				return Double.compare(d1.getVision(), d2.getVision());
			}
		});
		showData("시력 기준으로 Arrays.sort() 실행후", data);
		
		//정렬 필수
		Arrays.sort(data);
		String name = "이길동";
		int resultIndex = binarySearch(data, name);
		if (resultIndex >= 0)
			System.out.println(name + "(이)가 존재하면 인덱스 = "+resultIndex);
		else
			System.out.println(name + "(이)가 존재하지 않는다");
		System.out.println();
		
		PhyscData2[] newData= insertObject(data, new PhyscData2("소주다", 179, 1.5));
		//배열의 사이즈를 1개 증가시킨후 insert되는 객체 보다 큰 값들은 우측으로 이동, 사이즈가 증가된 객체 배열을 리턴
		showData("삽입후", newData);
	}

}
