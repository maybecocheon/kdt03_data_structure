package 자료구조_3장;

/*
 * 3장 3번 실습과제 - 객체 배열의 정렬과 이진검색 - Comparable 구현
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 * 
 * Comparable Interface
 * 
 * public interface Comparable<T> {
 *     int compareTo(T o);
 * }
 */

/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 * 
 * binarySearch(T[], T key)는 API에서 직접 지원하지 않음.
 * 하지만 binarySearch(Comparable<T>[] a, T key)를 사용할 수 있음.
 * 배열이 Comparable<T>을 구현하는 객체라면 정상적으로 동작함.
 * 만약 비교 기준을 다르게 설정하고 싶다면 binarySearch(T[], T key, Comparator<? super T> c)를 사용해야 함
 * 
 * Arrays.sort(Object[])가 존재하는 이유 > 
 * 배열의 원소가 Comparable을 구현하면, Object[] 배열에서도 정렬 가능
 * String[], Integer[], Double[] 등 Comparable을 구현한 기본 래퍼 클래스의 배열을 정렬하는 데 유용
 * 
 *  Object[] numbers = {5, 2, 9, 1, 3}; // Integer 배열 (Integer는 Comparable<Integer> 구현)
 *  Arrays.sort(numbers); // 정상 작동
 *  
 *  Arrays.sort(T[]) (제네릭 버전) => 이렇게는 불가함, T는 Comparable의 compareTo()를 구현하지 않은 상태
 *  제네릭 배열 T[]을 정렬할 때 더 적합한 방식.
 *  T가 Comparable<T>를 구현했을 경우, 자동으로 compareTo()가 호출됨.
 *  T[] 배열의 원소가 Comparable<T>를 구현하면 Arrays.sort(T[])를 사용할 수 있음.
 *  따라서 Arrays.sort(T[], Comparable<? super T> c)는 가능
 */
import java.util.Arrays;
import java.util.Comparator;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class Fruit5 {
	String name;
	int price;
	String expire;
	
	public Fruit5(String name, int price, String expire) {
		this.name = name;
		this.price = price;
		this.expire = expire;
	}
}

class FruitName2 implements Comparator<Fruit5>{
	//이름 기준으로 정렬하는 클래스
	//compare 메서드 오버라이딩
	public int compare(Fruit5 fru1, Fruit5 fru2) {
		return fru1.name.compareTo(fru2.name);
	}
}

class FruitPrice2 implements Comparator<Fruit5>{
	//가격 기준으로 정렬하는 클래스
	//compare 메서드 오버라이딩
	public int compare(Fruit5 fru1, Fruit5 fru2) {
		return fru1.price - fru2.price;
	}
}


public class train_실습3_06_1객체배열탐색_Comparable {
	//showData 스태틱 메서드
	static void showData(String str, Fruit5[] arr) {
		System.out.println(str);
		for (Fruit5 fruit : arr) {
			System.out.println(fruit.name + " " + fruit.price);
		} System.out.println();
	}
	
	//reverse 스태틱 메서드
	static void reverse(Fruit5[] arr) {
		for (int i = 0; i < arr.length / 2; i++) {
			Fruit5 fru = arr[i];
			arr[i] = arr[arr.length - 1 - i];
			arr[arr.length - 1 -i] = fru;
		}
	}
	
	//sortData 스태틱 메서드 (이름 기준으로 정렬)
	static void sortData(Fruit5[] arr, FruitName2 cc) {
		Arrays.sort(arr, cc);
	}
	
	//sortData2 스태틱 메서드 (가격 기준으로 정렬)
	static void sortData2(Fruit5[] arr, Comparator<Fruit5> cc_price) {
		Arrays.sort(arr, cc_price);
	}
	
	//교재 123~129 페이지 참조하여 구현
	//binarySearch 메서드 구현하기
	//search니까 for루프 돌리기
	//for루프 안에 if문 compare 조건문 쓰기
	//binarySearch 스태틱 메서드
	static int binarySearch(Fruit5[] arr, Fruit5 newFruit5, Comparator<Fruit5> cc_name) {
		int pl = 0;
		int pr = arr.length - 1;
		
		for (int i = 0; i < arr.length; i++) {
			int pc = (pl + pr) / 2;
			if (cc_name.compare(arr[pc], newFruit5) == 0) {
				return pc;
			} else if (cc_name.compare(arr[pc], newFruit5) < 0) {
				pl = pc + 1;
			} else {
				pr = pc - 1;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Fruit5[] arr = {new Fruit5("사과", 200, "2023-5-8"), 
				new Fruit5("감", 500, "2023-6-8"),
				new Fruit5("대추", 200, "2023-7-8"), 
				new Fruit5("복숭아", 50, "2023-5-18"), 
				new Fruit5("수박", 880, "2023-5-28"),
				new Fruit5("산딸기", 10, "2023-9-8") };
		
		System.out.println("\n정렬전 객체 배열: ");
		showData("정렬전 객체", arr);
		
		FruitName2 cc = new FruitName2();
		System.out.println("\n comparator cc 객체를 사용:: ");
		Arrays.sort(arr, cc);
		showData("Arrays.sort(arr, cc) 정렬 후", arr);
		
		reverse(arr);
		showData("역순 재배치 후", arr);
		
		sortData(arr, cc);
		showData("sortData(arr,cc) 실행후", arr);
		
		// 람다식은 익명클래스 + 익명 객체이다 - Comparator 객체를 사용하려면 compare() 메소드를 구현해야 하는 데 이것이 람다식이다
		//compare()를 구현해야 하므로 (a,b)이다 왜 람다식에서 parameter가 2개인지 이유를 이해 필요 
		Comparator<Fruit5> cc_price = (fru1, fru2) -> fru1.price - fru2.price;
		
		Arrays.sort(arr, cc_price); // 람다식으로 만들어진 객체를 사용
		showData("람다식 변수 cc_price2을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);
		
		Arrays.sort(arr, (fru1, fru2) -> fru1.price - fru2.price); // Fruit5에 compareTo()가 있어도 람다식 우선 적용
		showData("람다식: (a, b) -> a.getPrice() - b.getPrice()을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);

		System.out.println("\n익명클래스 객체로 정렬(가격)후 객체 배열: ");
		Arrays.sort(arr, new Comparator<Fruit5>() {//여기서 comparator가 필요한 이유를 알아야 
			// => Comparator를 구현한 익명 클래스를 만드는 것
			public int compare(Fruit5 fru1, Fruit5 fru2) {
				return fru1.price - fru2.price;
			}
		});
		
		Comparator<Fruit5> cc_name = new Comparator<Fruit5>() {// 익명클래스 사용
			public int compare(Fruit5 fru1, Fruit5 fru2) {
				return fru1.name.compareTo(fru2.name);
			}
		};
		Arrays.sort(arr, cc_name);
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		showData("comparator 객체를 사용한 정렬:", arr);
		
		Fruit5 newFruit5 = new Fruit5("수박", 880, "2023-5-18");
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		int result3Index = Arrays.binarySearch(arr, newFruit5, cc_name);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit5, cc_name);
		System.out.println("\nbinarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);

		sortData2(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData("comparator를 사용한 정렬후:", arr);
		
		result3Index = Arrays.binarySearch(arr, newFruit5, cc_price);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit5, cc_price);
		System.out.println("\nbinarySearch() 조회결과::" + result3Index);
	
	}
}
