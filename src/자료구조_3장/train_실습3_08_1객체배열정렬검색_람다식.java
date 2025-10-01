package 자료구조_3장;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 3장 과제2 : 객체 배열 정렬/검색 - 람다식사용
 * 
* Comparator를 사용하는 방법
* MyComparator implements Comparator<>
* MyComparator myComparator = new MyComparator();
* 
* Arrays.sort(array, myComparator);
* Collections.sort(list, new MyComparator());
*/

class Fruit4 {
	String name;
	int price;
	String expire;
	
	//생성자
	public Fruit4(String name, int price, String expire) {
		this.name = name;
		this.price = price;
		this.expire = expire;
	}
}
//교재 123~129 페이지 참조하여 구현
class FruitName implements Comparator<Fruit4>{
	//추상 메서드 구현
	public int compare(Fruit4 f1, Fruit4 f2) {
		return f1.name.compareTo(f2.name);
	}
}
class FruitPrice implements Comparator<Fruit4>{
	//추상 메서드 구현
	public int compare(Fruit4 f1, Fruit4 f2) {
		return f1.price - f2.price;
	}
}

public class train_실습3_08_1객체배열정렬검색_람다식 {

	//sortData는 static 클래스 
	private static void sortData(Fruit4[] arr, Comparator<Fruit4> cc_price) {
		//comparator을 구현하고 있는 클래스의 정렬 기준으로 arr 정렬
		Arrays.sort(arr, cc_price = (f1, f2) -> f1.price - f2.price);
	}
	
	//showData는 static 클래스
	private static void showData(String str, Fruit4[] arr) {
		System.out.println(str);
		for (Fruit4 fruit : arr) {
			System.out.println(fruit.name + ", " + fruit.price + ", " + fruit.expire);
		} System.out.println();
	}
	
	//binarySearch는 static 클래스
	private static int binarySearch(Fruit4[] arr, Fruit4 newFruit4, Comparator<Fruit4> cc_price) {
		//Fruit4 인스턴스를 검색할 첫 인덱스와 끝 인덱스
		int pl = 0;
		int pr = arr.length - 1;
		
		//찾을 때까지 반복, 못 찾으면 -1 리턴
		for (int i = 0; i < arr.length; i++) {
			int pc = (pl + pr) / 2; //pl과 pr의 값이 계속 바뀌기 때문에 여기 안에서 선언
			
			if (cc_price.compare(arr[pc], newFruit4) == 0) {
				return pc;
			} else if (cc_price.compare(arr[pc], newFruit4) < 0) {
				pl = pc + 1;
			} else {
				pr = pc - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		Fruit4[] arr = {new Fruit4("사과", 200, "2023-5-8"), 
				new Fruit4("감", 500, "2023-6-8"),
				new Fruit4("대추", 200, "2023-7-8"), 
				new Fruit4("복숭아", 50, "2023-5-18"), 
				new Fruit4("수박", 880, "2023-5-28"),
				new Fruit4("산딸기", 10, "2023-9-8") };
		
		System.out.println("\n정렬전 객체 배열: ");
		showData("정렬전 객체", arr);
		
		FruitName cc = new FruitName();
		System.out.println("\n comparator cc 객체를 사용:: ");
		Arrays.sort(arr, cc);
		showData("Arrays.sort(arr, cc) Name 정렬 후", arr);
		
		sortData(arr, new FruitPrice());
		showData("Arrays.sort(arr, cc)  Price 실행후", arr);
		
		// 람다식은 익명클래스 + 익명 객체이다
		Comparator<Fruit4> cc_expire = (f1, f2) -> f1.price - f2.price;
		Arrays.sort(arr, cc_expire); // 람다식으로 만들어진 객체를 사용
		showData("람다식 변수 cc_expire을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);
		
		Arrays.sort(arr, (f1, f2) -> f1.price - f2.price); 
		showData("람다식: (a, b) -> a.getPrice() - b.getPrice()을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);

		System.out.println("\n익명클래스 객체로 정렬(가격)후 객체 배열: ");
		Arrays.sort(arr, new Comparator<Fruit4>() {
			public int compare(Fruit4 f1, Fruit4 f2) {
				return f1.name.compareTo(f2.name);
			}
		});
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		showData("name comparator - 익명 객체를 사용한 정렬:", arr);
		
		//익명 클래스를 사용한 comparator 객체
		Comparator<Fruit4> cc_name = new Comparator<Fruit4>() {// 익명클래스 사용
			public int compare(Fruit4 f1, Fruit4 f2) {
				return f1.name.compareTo(f2.name);
			}
		};
		Comparator<Fruit4> cc_price = new Comparator<Fruit4>() {
			public int compare(Fruit4 f1, Fruit4 f2) {
				return f1.price - f2.price;
			}
		};

		Fruit4 newFruit4 = new Fruit4("수박", 880, "2023-5-18");
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		int result3Index = Arrays.binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit4, cc_price);//교재 113 binSearch() 함수를 고쳐서 구현 
		System.out.println("\nbinarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);

		sortData(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData("comparator를 사용한 정렬후:", arr);	
	}
}

