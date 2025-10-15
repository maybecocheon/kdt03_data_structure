package 자료구조_8장_리스트;

//KDT2_1회차 제외 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class train_실습8_0_2_스트링리스트정렬 {
	public static String[] removeElement1(String[] arr, String item) {
		List<String> list = new ArrayList<>(Arrays.asList(arr));
		list.remove(item);
		//기존 배열을 리턴하면 안된다 - 이유는 참조 변수 이므로 
		return list.toArray(String[]::new);//toArray()의 parameter가 함수형 인터페이스를 사용: IntFunction<T[]> g
		//String[]::new는 배열을 생성하는 메소드 reference: 함수형 인터페이스의 구현이다 이것이 람다식으로 사용될 수 있는 이유다 
		//list.toArray(new String[0]); 이전 버전 > new String[0] => toArray가 알맞은 배열 크기로 늘려 줌
	}
	/*
	       // x의 요소를 포함하는 새로운 String 배열을 생성하여 변수 y에 할당합니다.
      String[] y = x.toArray(new IntFunction<String[]>() {
          @Override
          public String[] apply(int value) {
              // 새로운 String 배열을 생성하고 x의 요소를 복사합니다.
              return new String[value];
          }
      }.apply(x.size()));
	 */
	/*
	 * IntFunction<R>은 자바 8에서 도입된 java.util.function 패키지의 함수형 인터페이스
	 *  추상 메소드: R apply(int value);
	 * 역할: 주로 배열의 크기를 입력받아 새로운 배열을 생성하는 공장(Factory) 역할
	 */
	/*
	 * String[]::new는 배열 생성자 참조(Array Constructor Reference)
	 * -> 람다 표현식 (size) -> new String[size]와 정확히 동일한 기능
	 * -> IntFunction<String[]>의 유일한 추상 메서드인 apply(int value)
	 * -> List.toArray(generator) 메서드는 리스트의 크기 N을 generator에 전달하여 스트링 배열 생성
	 */

	static void getList(List<String> list) {
		list.add("서울");	list.add("북경");
		list.add("상해");	list.add("서울");
		list.add("도쿄");	list.add("뉴욕");


		list.add("런던");	list.add("로마");
		list.add("방콕");	list.add("북경");
		list.add("도쿄");	list.add("서울");

		list.add(1, "LA");
	}

	static List<String> sortList(List<String> list) {
		// 리스트를 배열로 변환후 정렬 > 결과 값을 리스트로 만들기 때문에 리턴 값으로 반환해야 
		String cities[] = new String[0];
		cities = list.toArray(cities);
		int count = cities.length;
		for (int i = 0; i < count; i++)

		return lst;
	}
	static void swap(List<String> list, int i, int j) {
		//ArrayList 클래스 속 get(), set() 메서드 사용하여 구현
	}
	static void sortList2(List<String> list) {
		//리스트 자체를 정렬
		int listSize = list.size();

	}
	
	//중복 제거 메서드
	static String[] removeDuplicateList(List<String> list) {
		String cities[] = new String[0];//배열의 크기가 0인 배열
		cities = list.toArray(cities);//list의 결과로서 배열의 크기를 잡은 후에 동적으로 배열의 크기를 정한다 
		int count = cities.length;
		for (int i = 0; i< count; i++) {

		}
		// 대체 코드
		/*
		    int m = 0, n = 1;
		    while (n < count) {
		    	if (cities[m].compareTo(cities[n]) == 0) {
		    		cities = removeElement1(cities, cities[m]);
		    		count--;
		    		continue;
		    	}
		    	m++; n++;

		    }
		 */
		//
		return cities;
	}
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		getList(list);
		showList("입력후", list);
		//sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩

		//		    Collections.sort(list);

		//배열의 정렬
		//List<String> ls =  sortList(list);//배열로 변환하여 정렬
		sortList2(list);//리스트 자체로 정렬
		System.out.println();
		showList("정렬후", list);
		// 배열에서 중복제거
		System.out.println();
		System.out.println("중복제거::");

		String[] cities = removeDuplicateList(list);
		ArrayList<String> lst = new ArrayList<>(Arrays.asList(cities));
		showList("중복제거후", lst);
	}
}



